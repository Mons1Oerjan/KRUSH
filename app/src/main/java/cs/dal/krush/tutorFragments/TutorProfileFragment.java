package cs.dal.krush.tutorFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs.dal.krush.R;

/**
 * Sets up the Tutor Profile fragment. This fragment belongs to the TutorMainActivity class
 * and is accessed through the tutor's bottom navigation bar.
 *
 * The tutor can view and edit their user profile using this fragment.
 */
public class TutorProfileFragment extends Fragment {

    private int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutor_profile, container, false);
        userId = Integer.parseInt(getArguments().getString("UserID"));


        return view;
    }
}
