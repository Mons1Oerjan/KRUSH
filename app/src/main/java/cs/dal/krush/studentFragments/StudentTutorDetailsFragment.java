package cs.dal.krush.studentFragments;

import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import cs.dal.krush.R;
import cs.dal.krush.models.DBHelper;

/**
 * This fragment displays the details of a tutor when a student clicks on their profile
 */
public class StudentTutorDetailsFragment extends Fragment {

    static int USER_ID;
    static int TUTOR_ID;
    DBHelper mydb;
    Cursor tutorCursor;
    TextView nameView, schoolView, rateView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_tutor_details, container, false);

        // Get USER_ID and TUTOR_ID
        USER_ID = getArguments().getInt("USER_ID");
        TUTOR_ID = getArguments().getInt("TUTOR_ID");

        // Initialize db connection
        mydb = new DBHelper(getContext());

        // Get tutor
        tutorCursor = mydb.tutor.getData(TUTOR_ID);
        tutorCursor.moveToFirst();

        // Get Views
        nameView = (TextView) view.findViewById(R.id.tutor_details_name);
        schoolView = (TextView) view.findViewById(R.id.tutor_details_school);
        rateView = (TextView) view.findViewById(R.id.tutor_details_rate);

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //Set custom app font
        nameView.setTypeface(typeFace);
        schoolView.setTypeface(typeFace);
        rateView.setTypeface(typeFace);

        // Get values from database
        String name = tutorCursor.getString(tutorCursor.getColumnIndex("f_name")) + " " + tutorCursor.getString(tutorCursor.getColumnIndex("l_name"));
        int school_id = tutorCursor.getInt(tutorCursor.getColumnIndex("school_id"));
        Cursor schoolCursor = mydb.school.getData(school_id);
        schoolCursor.moveToFirst();
        String school = schoolCursor.getString(schoolCursor.getColumnIndex("name"));
        String rate = tutorCursor.getString(tutorCursor.getColumnIndex("rate"));

        final RatingBar tutorRating = (RatingBar) view.findViewById(R.id.rating);
        final Cursor hasRatedBefore = mydb.tutorRating.getTutorRatingByTutorAndStudentId(TUTOR_ID, USER_ID);
        hasRatedBefore.moveToFirst();

        if(hasRatedBefore.getCount() > 0)
            tutorRating.setRating(Float.parseFloat(hasRatedBefore.getString(hasRatedBefore.getColumnIndex("rating"))));

        tutorRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(fromUser) {
                    mydb = new DBHelper(getContext());
                    if (hasRatedBefore.getCount() > 0)
                        mydb.tutorRating.updateTutorRating(rating, USER_ID, TUTOR_ID);
                    else
                        mydb.tutorRating.insert(rating, USER_ID, TUTOR_ID);
                    Cursor tutorRatingFromDB = mydb.tutorRating.getTutorRatingByTutorId(TUTOR_ID);
                    Cursor tutor = mydb.tutor.getData(TUTOR_ID);
                    tutorRatingFromDB.moveToFirst();
                    tutor.moveToFirst();
                    int n = tutorRatingFromDB.getCount();
                    float newTutorRating = 0;
                    for (int i = 0; i < n; i++) {
                        newTutorRating += Float.parseFloat(tutorRatingFromDB.getString(tutorRatingFromDB.getColumnIndex("rating")));
                        tutorRatingFromDB.move(1);
                    }
                    newTutorRating = newTutorRating/n;
                    newTutorRating = Float.parseFloat(String.format("%.1f", newTutorRating));
                    mydb.tutor.updateTutorRating(TUTOR_ID, newTutorRating);
                    tutorRating.setRating(rating);
                    mydb.close();
                }
            }
        });

        // Set values
        nameView.setText(name);
        schoolView.setText(school);
        rateView.setText(rate);

        //Close connections
        schoolCursor.close();
        tutorCursor.close();
        mydb.close();

        return view;
    }
}
