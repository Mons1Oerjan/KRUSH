package cs.dal.krush.tutorFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs.dal.krush.R;

/**
 * Sets up the Tutor Home fragment. This fragment belongs to the TutorMainActivity class
 * and is accessed through the tutor's bottom navigation bar.
 *
 * The tutor can view their session history using this fragment.
 */
public class TutorSessionsFragment extends Fragment {

    private int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutor_sessions, container, false);
        userId = Integer.parseInt(getArguments().getString("UserID"));


        return view;
    }
}
