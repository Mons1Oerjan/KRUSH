package cs.dal.krush.studentFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs.dal.krush.R;


/**
 * Sets up the Student Profile fragment. This fragment belongs to the StudentMainActivity class
 * and is accessed through the student's bottom navigation bar.
 */
public class StudentProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.student_profile, container, false);
    }

}
