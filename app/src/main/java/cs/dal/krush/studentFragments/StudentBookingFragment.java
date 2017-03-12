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

import cs.dal.krush.R;
import cs.dal.krush.models.DBHelper;

/**
 * Sets up the Student Booking fragment. This fragment belongs to the StudentMainActivity class
 * and is accessed through the student's bottom navigation bar.
 *
 * The student can book a tutoring session through this fragment.
 *
 * Source:
 * [5] List View. (n.d.). Retrieved March 12, 2017,
 * from https://developer.android.com/guide/topics/ui/layout/listview.html
 */
public class StudentBookingFragment extends Fragment {

    private ListView tutorsListView;
    private TextView pageTitle;
    private DBHelper mydb;
    private Cursor cursorTutorResponse;
    private ProfileCursorAdapter profileAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_booking, container, false);

        //get Context:
        Context C = getActivity().getApplicationContext();

        //init DB connection:
        mydb = new DBHelper(C);

        //fetch UI elements:
        tutorsListView = (ListView)view.findViewById(R.id.availableTutorsListView);
        pageTitle = (TextView)view.findViewById(R.id.titleLabel);

        //fetch custom app font:
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set font style:
        pageTitle.setTypeface(typeFace);

        //get all tutors from DB:
        cursorTutorResponse = mydb.tutor.getAllForCursorAdapter();

        //set tutor's listview adapter:
        profileAdapter = new ProfileCursorAdapter(C, cursorTutorResponse);
        tutorsListView.setAdapter(profileAdapter);

        return view;
    }

}
