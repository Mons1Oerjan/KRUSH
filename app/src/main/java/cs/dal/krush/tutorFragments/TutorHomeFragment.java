package cs.dal.krush.tutorFragments;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import cs.dal.krush.R;
import cs.dal.krush.StudentCursorAdapters.TutorHomeUpcomingSessionsCursorAdapter;
import cs.dal.krush.models.DBHelper;

/**
 * Sets up the Tutor Home fragment. This fragment belongs to the TutorMainActivity class
 * and is accessed through the tutor's bottom navigation bar.
 *
 *  Source:
 * [5] List View. (n.d.). Retrieved March 12, 2017,
 * from https://developer.android.com/guide/topics/ui/layout/listview.html
 */
public class TutorHomeFragment extends Fragment {

    static int USER_ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutor_home, container, false);
        USER_ID = getArguments().getInt("USER_ID");

        //get Context:
        Context C = getActivity().getApplicationContext();

        //init DB connection:
        DBHelper mydb = new DBHelper(C);

        Cursor tutor = mydb.tutor.getData(USER_ID);
        tutor.moveToFirst();

        //fetch UI elements:
        ListView upcomingSessionsListView = (ListView)view.findViewById(R.id.upcomingSessionsListView);
        RatingBar tutorRating = (RatingBar) view.findViewById(R.id.rating);
        TextView pageTitle = (TextView)view.findViewById(R.id.homeTitleLabel);
        TextView sessionsLabel = (TextView)view.findViewById(R.id.upcomingSessionsLabel);
        TextView ratingTitle = (TextView) view.findViewById(R.id.tutorRating);

        String currentTutorRating = tutor.getString(tutor.getColumnIndex("rating"));
        if (currentTutorRating != null)
            tutorRating.setRating(Float.parseFloat(currentTutorRating));

        //fetch custom app font:
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set font style:
        pageTitle.setTypeface(typeFace);
        sessionsLabel.setTypeface(typeFace);
        ratingTitle.setTypeface(typeFace);

        //get all tutoring sessions by the tutor:
        Cursor cursorSessionsResponse = mydb.tutoringSession.getDataByTutorIdForCursorAdapter(USER_ID);

        //set sessions listview adapter:
        TutorHomeUpcomingSessionsCursorAdapter sessionsAdapter = new TutorHomeUpcomingSessionsCursorAdapter(C, cursorSessionsResponse);
        upcomingSessionsListView.setAdapter(sessionsAdapter);

        return view;
    }
}
