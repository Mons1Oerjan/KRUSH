package cs.dal.krush.studentFragments;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cs.dal.krush.R;
import cs.dal.krush.appFragments.SessionLocationFragment;
import cs.dal.krush.models.DBHelper;
import cs.dal.krush.tutorFragments.TutorHomeFragment;

/**
 * This fragment displays the details of a tutoring session.
 * It is passed the SESSION_ID that corresponds to the session clicked on the home fragment
 */
public class StudentUpcSessionsDetailsFragment extends Fragment {

    static int USER_ID;
    static int SESSION_ID;
    DBHelper mydb;
    Cursor sessionCursor;
    TextView titleField, tutorNameField, tutorEmailField, locationField,
            startField, endField, tutorLabel, sessionInfoLabel, schoolField;
    ImageView tutorPicture;
    Button cancelButton, sessionDetailLocation;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_home_upc_session_details, container, false);

        // Get USER_ID and SESSION_ID
        USER_ID = getArguments().getInt("USER_ID");
        SESSION_ID = getArguments().getInt("SESSION_ID");

        // Initialize db connection
        mydb = new DBHelper(getContext());

        // Get session
        sessionCursor = mydb.tutoringSession.getSessionHistoryDetailsBySessionIdForTutorCursorAdapter(SESSION_ID);
        sessionCursor.moveToFirst();

        // Get Views
        tutorPicture = (ImageView) view.findViewById(R.id.student_upc_details_tutor_picture);
        titleField = (TextView) view.findViewById(R.id.student_upc_details_title);
        tutorNameField = (TextView) view.findViewById(R.id.student_upc_details_tutor_name);
        tutorEmailField = (TextView) view.findViewById(R.id.student_upc_details_tutor_email);
        locationField = (TextView) view.findViewById(R.id.student_upc_details_location);
        startField = (TextView) view.findViewById(R.id.student_upc_details_start);
        endField = (TextView) view.findViewById(R.id.student_upc_details_end);
        tutorLabel = (TextView) view.findViewById(R.id.student_upc_details_tutor_label);
        sessionInfoLabel = (TextView) view.findViewById(R.id.student_upc_details_session_label);
        schoolField = (TextView) view.findViewById(R.id.student_upc_details_school);
        cancelButton = (Button) view.findViewById(R.id.student_cancel_session_button);
        sessionDetailLocation = (Button) view.findViewById(R.id.sessionDetailLocation);


        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //Set custom app font
        titleField.setTypeface(typeFace);
        tutorNameField.setTypeface(typeFace);
        tutorEmailField.setTypeface(typeFace);
        locationField.setTypeface(typeFace);
        startField.setTypeface(typeFace);
        endField.setTypeface(typeFace);
        tutorLabel.setTypeface(typeFace);
        sessionInfoLabel.setTypeface(typeFace);
        schoolField.setTypeface(typeFace);
        cancelButton.setTypeface(typeFace);

        // Get values from database
        String title = sessionCursor.getString(sessionCursor.getColumnIndex("title"));
        String tutorName = sessionCursor.getString(sessionCursor.getColumnIndex("f_name")) + " " +
                sessionCursor.getString(sessionCursor.getColumnIndex("l_name"));
        String tutorEmail = sessionCursor.getString(sessionCursor.getColumnIndex("email"));
        final String startTime = sessionCursor.getString(sessionCursor.getColumnIndex("start_time"));
        String endTime = sessionCursor.getString(sessionCursor.getColumnIndex("end_time"));
        String location = sessionCursor.getString(sessionCursor.getColumnIndex("location"));
        String imgPath = sessionCursor.getString(sessionCursor.getColumnIndex("profile_pic"));
        String school = sessionCursor.getString(sessionCursor.getColumnIndex("name")) + " " +
                sessionCursor.getString(sessionCursor.getColumnIndex("type"));
        final String locationId = sessionCursor.getString(sessionCursor.getColumnIndex("location_id"));

        // Set values to view
        titleField.setText(title);
        tutorNameField.setText(tutorName);
        tutorEmailField.setText(tutorEmail);
        startField.setText("Starts At: " + startTime);
        endField.setText("Ends At: " + endTime);
        locationField.setText("Meeting Location: " + location);
        schoolField.setText("School: " + school);
        if (imgPath != null && !imgPath.isEmpty()) {
            Bitmap profilePic = BitmapFactory.decodeFile(imgPath);
            tutorPicture.setImageBitmap(profilePic);
        }

        //setup OnClickListeners:
        sessionDetailLocation.setOnClickListener(new View.OnClickListener() {
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

        //Build the "Are you sure?" dialog window:
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to cancel this session?").setTitle("Cancel Session");
        builder.setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked on "Yes" - destroy the tutoring session
                mydb.tutoringSession.deleteTutoringSession(SESSION_ID);

                // Set tutors available time booked to 0
                ContentValues cv = new ContentValues();
                cv.put("booked", "0");
                String start = "\"" + startTime + "\"";
                mydb.getWritableDatabase().update("available_time", cv, "start_time="+start, null);

                // return to home fragment
                Bundle bundle = new Bundle();
                bundle.putInt("USER_ID", USER_ID);
                StudentHomeFragment home = new StudentHomeFragment();
                home.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.student_fragment_container, home);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        builder.setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //user clicked on "No" - return to the view

            }
        });
        final AlertDialog dialog = builder.create();

        // Set on click listener for cancel button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // display "are you sure" dialog window:
                dialog.show();
            }
        });

        sessionCursor.close();

        return view;
    }
}
