package cs.dal.krush.appFragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import cs.dal.krush.R;
import cs.dal.krush.models.DBHelper;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * This fragment displays the details of a tutoring session.
 * It is passed the SESSION_ID that corresponds to the session clicked on the home fragment
 */
public class SessionDetailsFragment extends Fragment {

    static int USER_ID;
    static int SESSION_ID;
    String AudioSavePathInDevice = null;
    DBHelper mydb;
    Cursor sessionCursor, student;
    TextView titleView, studentIdView, tutorIdView;
    Button locationButton, sessionRecordStart, sessionRecordStop, sessionPlayRecording, sessionStopRecording;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    public static final int RequestPermissionCode = 1;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.session_details, container, false);

        // Get USER_ID and SESSION_ID
        USER_ID = getArguments().getInt("USER_ID");
        SESSION_ID = getArguments().getInt("SESSION_ID");

        // Initialize db connection
        mydb = new DBHelper(getContext());

        // Get session
        sessionCursor = mydb.tutoringSession.getData(SESSION_ID);
        sessionCursor.moveToFirst();

        //Get student
        student = mydb.student.getData(USER_ID);
        student.moveToFirst();

        // Get Views
        titleView = (TextView) view.findViewById(R.id.session_title);
        studentIdView = (TextView) view.findViewById(R.id.session_student_id);
        tutorIdView = (TextView) view.findViewById(R.id.session_tutor_id);
        locationButton = (Button) view.findViewById(R.id.sessionDetailLocation);
        sessionRecordStart = (Button) view.findViewById(R.id.sessionRecordStart);
        sessionRecordStop = (Button) view.findViewById(R.id.sessionRecordStop);
        sessionPlayRecording = (Button) view.findViewById(R.id.sessionPlayRecording);
        sessionStopRecording = (Button) view.findViewById(R.id.sessionStopRecording);
        sessionRecordStop.setEnabled(false);
        sessionStopRecording.setEnabled(false);

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //Set custom app font
        titleView.setTypeface(typeFace);
        studentIdView.setTypeface(typeFace);
        tutorIdView.setTypeface(typeFace);

        // Get values from database
        String title = sessionCursor.getString(sessionCursor.getColumnIndex("title"));
        String studentId = sessionCursor.getString(sessionCursor.getColumnIndex("student_id"));
        String tutorId = sessionCursor.getString(sessionCursor.getColumnIndex("tutor_id"));
        final String locationId = sessionCursor.getString(sessionCursor.getColumnIndex("location_id"));

        // Set values to view
        titleView.setText(title);
        studentIdView.setText(studentId);
        tutorIdView.setText(tutorId);

//        //request permission to use audio if not already given
//        if (ContextCompat.checkSelfPermission(getActivity(), RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), RECORD_AUDIO)) {
//                //Set alert message if audio permission was denied
//                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
//                alertDialog.setTitle("Warning");
//                alertDialog.setMessage("You will need to give KRUSH audio permission in your Android settings");
//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                alertDialog.show();
//            } else {
//                ActivityCompat.requestPermissions(getActivity(), new String[]{RECORD_AUDIO}, 1);
//            }
//        }


        //setup OnClickListeners:
        locationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Add LOCATION_ID to be passed to new view
                Bundle bundle = new Bundle();
                bundle.putInt("LOCATION_ID", Integer.parseInt(locationId));

                // Swap into new fragment
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                SessionLocationFragment newFragment = new SessionLocationFragment();

                newFragment.setArguments(bundle);

                ft.replace(R.id.student_fragment_container, newFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        sessionRecordStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkPermission()){
                    mydb = new DBHelper(getContext());

                    // Get session
                    sessionCursor = mydb.tutoringSession.getData(SESSION_ID);
                    sessionCursor.moveToFirst();

                    //Get student
                    student = mydb.student.getData(USER_ID);
                    student.moveToFirst();
                    AudioSavePathInDevice =
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + student.getString(student.getColumnIndex("f_name")) +
                                    student.getString(student.getColumnIndex("l_name")) + sessionCursor.getString(sessionCursor.getColumnIndex("start_time")) + ".3gp";


                    MediaRecorderReady();

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    sessionRecordStart.setEnabled(false);
                    sessionRecordStop.setEnabled(true);
                    sessionCursor.close();
                    student.close();
                    mydb.close();
                }else {
                    requestPermission();
                }
            }

        });

        sessionRecordStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.stop();
                sessionRecordStop.setEnabled(false);
                sessionPlayRecording.setEnabled(true);
                sessionRecordStart.setEnabled(true);
                sessionStopRecording.setEnabled(false);

            }
        });

        sessionPlayRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException {

                sessionRecordStart.setEnabled(false);
                sessionRecordStop.setEnabled(false);
                sessionStopRecording.setEnabled(true);

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(AudioSavePathInDevice);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();

            }
        });

        sessionCursor.close();
        student.close();
        mydb.close();

        return view;
    }

    public void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);

    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }
}
