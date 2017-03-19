package cs.dal.krush.studentFragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cs.dal.krush.R;
import cs.dal.krush.StudentCursorAdapters.HomeQuickBookCursorAdapter;
import cs.dal.krush.StudentCursorAdapters.HomeUpcomingSessionsCursorAdapter;
import cs.dal.krush.appFragments.SessionDetailsFragment;
import cs.dal.krush.models.DBHelper;

import static android.R.attr.id;

/**
 * Sets up the Student Home fragment. This fragment belongs to the StudentMainActivity class
 * and is accessed through the student's bottom navigation bar.
 *
 * Source:
 * [5] List View. (n.d.). Retrieved March 12, 2017,
 * from https://developer.android.com/guide/topics/ui/layout/listview.html
 */
public class StudentHomeFragment extends Fragment {

    static int USER_ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.student_home, container, false);
        USER_ID = getArguments().getInt("USER_ID");

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
        final Cursor cursorSessionsResponse = mydb.tutoringSession.getDataByStudentIdForCursorAdapter(USER_ID);

        //set sessions listview adapter:
        HomeUpcomingSessionsCursorAdapter sessionsAdapter = new HomeUpcomingSessionsCursorAdapter(C, cursorSessionsResponse);
        upcomingSessionsListView.setAdapter(sessionsAdapter);

        //get all distinct tutors that the user has previously had a tutoring session with:
        final Cursor cursorTutorResponse = mydb.tutor.getPreviouslyUsedTutorsForCursorAdapter(USER_ID);

        //set tutor's listview adapter:
        HomeQuickBookCursorAdapter quickBookAdapter = new HomeQuickBookCursorAdapter(C, cursorTutorResponse);
        tutorsListView.setAdapter(quickBookAdapter);

        // Click listeners for listviews
        upcomingSessionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {

                //Get id of session clicked on
                cursorSessionsResponse.moveToPosition(position);
                int SESSION_ID = cursorSessionsResponse.getInt(cursorSessionsResponse.getColumnIndex("id"));

                // Add USER_ID and SESSION_ID to session details fragment for displaying
                Bundle bundle = new Bundle();
                bundle.putInt("USER_ID", USER_ID);
                bundle.putInt("SESSION_ID", SESSION_ID);

                // Swap into new fragment
                SessionDetailsFragment session = new SessionDetailsFragment();
                session.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.student_fragment_container, session);
                transaction.addToBackStack(null);
                transaction.commit();

            }

        });

        tutorsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                cursorTutorResponse.moveToPosition(position);
                int TUTOR_ID = cursorTutorResponse.getInt(cursorTutorResponse.getColumnIndex("_id"));

                // Add USER_ID and SESSION_ID to session details fragment for displaying
                Bundle bundle = new Bundle();
                bundle.putInt("USER_ID", USER_ID);
                bundle.putInt("TUTOR_ID", TUTOR_ID);

                // Swap into new fragment
                StudentTutorDetailsFragment tutor = new StudentTutorDetailsFragment();
                tutor.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.student_fragment_container, tutor);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });


        return view;
    }

}