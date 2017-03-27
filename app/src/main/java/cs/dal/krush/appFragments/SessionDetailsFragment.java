package cs.dal.krush.appFragments;

import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import cs.dal.krush.R;
import cs.dal.krush.models.DBHelper;
import cs.dal.krush.tutorFragments.TutorLocationFragment;

/**
 * This fragment displays the details of a tutoring session.
 * It is passed the SESSION_ID that corresponds to the session clicked on the home fragment
 */
public class SessionDetailsFragment extends Fragment {

    static int USER_ID;
    static int SESSION_ID;
    DBHelper mydb;
    Cursor sessionCursor;
    TextView titleView, studentIdView, tutorIdView;
    Button locationButton;

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

        // Get Views
        titleView = (TextView) view.findViewById(R.id.session_title);
        studentIdView = (TextView) view.findViewById(R.id.session_student_id);
        tutorIdView = (TextView) view.findViewById(R.id.session_tutor_id);
        locationButton = (Button) view.findViewById(R.id.sessionDetailLocation);

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

        sessionCursor.close();
        mydb.close();

        return view;
    }
}
