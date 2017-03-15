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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import cs.dal.krush.R;
import cs.dal.krush.StudentCursorAdapters.BookingTutorCursorAdapter;
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

    private DBHelper mydb;
    private ListView tutorsListView;
    private Context C;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_booking, container, false);
        int userId = Integer.parseInt(getArguments().getString("UserID"));

        //get Context:
        C = getActivity().getApplicationContext();

        //init DB connection:
        mydb = new DBHelper(C);

        //fetch UI elements:
        tutorsListView = (ListView)view.findViewById(R.id.availableTutorsListView);
        TextView pageTitle = (TextView)view.findViewById(R.id.bookingTitleLabel);
        Switch filterByCourses = (Switch)view.findViewById(R.id.filterByCoursesSwitch);

        //fetch custom app font:
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set font style:
        pageTitle.setTypeface(typeFace);
        filterByCourses.setTypeface(typeFace);

        //Set OnCheckListener:
        filterByCourses.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //get tutors filtered by the student's school:
                    //TODO: Pass in the logged in student's ID:
                    Cursor cursorTutorResponse = mydb.tutor.getTutorsFilteredBySchoolForCursorAdapter(1);
                    //set tutor's listview adapter:
                    BookingTutorCursorAdapter profileAdapter = new BookingTutorCursorAdapter(C, cursorTutorResponse);
                    tutorsListView.setAdapter(profileAdapter);
                } else {
                    //get all tutors from DB:
                    Cursor cursorTutorResponse = mydb.tutor.getAllForCursorAdapter();
                    //set tutor's listview adapter:
                    BookingTutorCursorAdapter profileAdapter = new BookingTutorCursorAdapter(C, cursorTutorResponse);
                    tutorsListView.setAdapter(profileAdapter);
                }
            }
        });

        //get all tutors from DB:
        Cursor cursorTutorResponse = mydb.tutor.getAllForCursorAdapter();

        //set tutor's listview adapter:
        BookingTutorCursorAdapter profileAdapter = new BookingTutorCursorAdapter(C, cursorTutorResponse);
        tutorsListView.setAdapter(profileAdapter);

        return view;
    }
}
