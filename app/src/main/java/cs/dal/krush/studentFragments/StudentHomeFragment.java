package cs.dal.krush.studentFragments;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import cs.dal.krush.R;
import cs.dal.krush.StudentCursorAdapters.HomeQuickBookCursorAdapter;
import cs.dal.krush.StudentCursorAdapters.HomeUpcomingSessionsCursorAdapter;
import cs.dal.krush.models.DBHelper;

/**
 * Sets up the Student Home fragment. This fragment belongs to the StudentMainActivity class
 * and is accessed through the student's bottom navigation bar.
 *
 * Source:
 * [5] List View. (n.d.). Retrieved March 12, 2017,
 * from https://developer.android.com/guide/topics/ui/layout/listview.html
 */
public class StudentHomeFragment extends Fragment {

    private int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_home, container, false);
        userId = Integer.parseInt(getArguments().getString("UserID"));

        //get Context:
        Context C = getActivity().getApplicationContext();

        //init DB connection:
        DBHelper mydb = new DBHelper(C);

        //fetch UI elements:
        ListView upcomingSessionsListView = (ListView)view.findViewById(R.id.upcomingSessionsListView);
        ListView tutorsListView = (ListView)view.findViewById(R.id.availableTutorsListView);
        TextView pageTitle = (TextView)view.findViewById(R.id.homeTitleLabel);
        TextView sessionsLabel = (TextView)view.findViewById(R.id.upcomingSessionsLabel);
        TextView bookTutorLabel = (TextView)view.findViewById(R.id.bookTutorLabel);

        //fetch custom app font:
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set font style:
        pageTitle.setTypeface(typeFace);
        sessionsLabel.setTypeface(typeFace);
        bookTutorLabel.setTypeface(typeFace);

        //get all tutoring sessions by the student:
        Cursor cursorSessionsResponse = mydb.tutoringSession.getDataByStudentIdForCursorAdapter(userId);

        //set sessions listview adapter:
        HomeUpcomingSessionsCursorAdapter sessionsAdapter = new HomeUpcomingSessionsCursorAdapter(C, cursorSessionsResponse);
        upcomingSessionsListView.setAdapter(sessionsAdapter);

        //get all distinct tutors that the user has previously had a tutoring session with:
        Cursor cursorTutorResponse = mydb.tutor.getPreviouslyUsedTutorsForCursorAdapter(userId);

        //set tutor's listview adapter:
        HomeQuickBookCursorAdapter quickBookAdapter = new HomeQuickBookCursorAdapter(C, cursorTutorResponse);
        tutorsListView.setAdapter(quickBookAdapter);

        return view;
    }
}