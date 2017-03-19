package cs.dal.krush.studentFragments;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import cs.dal.krush.StudentCursorAdapters.SessionCursorAdapter;
import cs.dal.krush.R;
import cs.dal.krush.models.DBHelper;

/**
 * Sets up the Student Sessions History fragment. This fragment belongs to the StudentMainActivity class
 * and is accessed through the student's bottom navigation bar.
 *
 * The student can view their previous sessions history along with audio recordings using this fragment.
 */

public class StudentSessionsFragment extends Fragment {

    static int USER_ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_sessions, container, false);
        USER_ID = getArguments().getInt("USER_ID");

        //get Context:
        Context C = getActivity().getApplicationContext();

        //init DB connection:
        DBHelper mydb = new DBHelper(C);

        Cursor student = mydb.tutor.getData(USER_ID);
        student.moveToFirst();

        //fetch UI elements:
        ListView sessionHistoryListView = (ListView)view.findViewById(R.id.sessionHistoryListView);
        TextView pageTitle = (TextView)view.findViewById(R.id.sessionHistoryTitle);

        //fetch custom app font:
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set font style:
        pageTitle.setTypeface(typeFace);

        //get all tutoring sessions by the student:
        Cursor cursorSessionsResponse = mydb.tutoringSession.getSessionHistoryByStudentIdForCursorAdapter(USER_ID);

        //set sessions listview adapter:
        SessionCursorAdapter sessionsAdapter = new SessionCursorAdapter(C, cursorSessionsResponse);
        sessionHistoryListView.setAdapter(sessionsAdapter);

        return view;
    }
}
