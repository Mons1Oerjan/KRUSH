package cs.dal.krush.studentFragments;

import android.database.Cursor;
import android.graphics.Typeface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cs.dal.krush.R;
import cs.dal.krush.models.DBHelper;
import static cs.dal.krush.R.id.profile_name;


/**
 * Sets up the Student Profile fragment. This fragment belongs to the StudentMainActivity class
 * and is accessed through the student's bottom navigation bar.
 *
 * The student can view and edit their user profile using this fragment.
 */
public class StudentProfileFragment extends Fragment implements View.OnClickListener
{
    private ImageView edit_btn;
    private TextView profile_name_view, email_view, school_view;
    private DBHelper mydb;
    private Cursor cursor;
    int user_id = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.student_profile, container, false);

        //initialize database connection
        mydb = new DBHelper(getContext());
        cursor = mydb.student.getData(user_id);
        cursor.moveToFirst();

        // Get TextViews
        profile_name_view = (TextView) myView.findViewById(profile_name);
        email_view = (TextView) myView.findViewById(R.id.profile_email);
        school_view = (TextView) myView.findViewById(R.id.profile_school);

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //Set custom app font
        profile_name_view.setTypeface(typeFace);
        email_view.setTypeface(typeFace);
        school_view.setTypeface(typeFace);

        //Set data
        String name = cursor.getString(cursor.getColumnIndex("f_name")) + " " + cursor.getString(cursor.getColumnIndex("l_name"));
        String email = cursor.getString(cursor.getColumnIndex(("email")));
        int school_id = cursor.getInt(cursor.getColumnIndex("school_id"));
        Cursor schoolCursor = mydb.school.getData(school_id);
        schoolCursor.moveToFirst();
        String school = schoolCursor.getString(schoolCursor.getColumnIndex("name"));

        profile_name_view.setText(name);
        email_view.setText(email);
        school_view.setText(school);

        //Edit profile button listener
        edit_btn = (ImageView) myView.findViewById(R.id.edit_profile_button);
        edit_btn.setOnClickListener(this);
        return myView;

    }

    // Edit profile button listener, starts StudentProfileEditFragment
    @Override
    public void onClick(View v)
    {
        try
        {
            StudentProfileEditFragment edit = new StudentProfileEditFragment();

            // Set user_id for edit fragment
            edit.setUser_id(user_id);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.student_fragment_container, edit);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
