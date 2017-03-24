package cs.dal.krush.tutorFragments;

import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cs.dal.krush.R;
import cs.dal.krush.models.DBHelper;

/**
 * This fragment displays the details of a session when a tutor clicks on a
 * completed session in the tutor Sessions History list view
 */
public class TutorHistoryDetailsFragment extends Fragment {
    static int USER_ID;
    static int SESSION_ID;
    DBHelper mydb;
    Cursor sessionCursor;
    TextView titleView, studentIdView, tutorIdView;

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

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/FredokaOne-Regular.ttf");

        //Set custom app font
        titleView.setTypeface(typeFace);
        studentIdView.setTypeface(typeFace);
        tutorIdView.setTypeface(typeFace);

        // Get values from database
        String title = sessionCursor.getString(sessionCursor.getColumnIndex("title"));
        String studentId = sessionCursor.getString(sessionCursor.getColumnIndex("student_id"));
        String tutorId = sessionCursor.getString(sessionCursor.getColumnIndex("tutor_id"));

        // Set values to view
        titleView.setText(title);
        studentIdView.setText(studentId);
        tutorIdView.setText(tutorId);

        sessionCursor.close();
        mydb.close();

        return view;
    }
}
