package cs.dal.krush.studentFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs.dal.krush.R;

/**
 * Sets up the Student Sessions History fragment. This fragment belongs to the StudentMainActivity class
 * and is accessed through the student's bottom navigation bar.
 *
 * The student can view their previous sessions history along with audio recordings using this fragment.
 */
public class StudentSessionsFragment extends Fragment {

    private int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_sessions, container, false);
        userId = Integer.parseInt(getArguments().getString("UserID"));


        return view;
    }
}
