package cs.dal.krush.tutorFragments;

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
import cs.dal.krush.studentFragments.StudentProfileEditFragment;

/**
 * Sets up the Tutor Profile fragment. This fragment belongs to the TutorMainActivity class
 * and is accessed through the tutor's bottom navigation bar.
 *
 * The tutor can view and edit their user profile using this fragment.
 */
public class TutorProfileFragment extends Fragment implements View.OnClickListener
{
    ImageView edit_btn;
    TextView profile_name, email, school, schedule, rate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.tutor_profile, container, false);

        // Get TextViews
        profile_name = (TextView) myView.findViewById(R.id.profile_name);
        email = (TextView) myView.findViewById(R.id.tutor_email);
        school = (TextView) myView.findViewById(R.id.tutor_school);
        schedule = (TextView) myView.findViewById(R.id.tutor_schedule);
        rate = (TextView) myView.findViewById(R.id.tutor_rate);

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //Set custom app font
        profile_name.setTypeface(typeFace);
        email.setTypeface(typeFace);
        school.setTypeface(typeFace);
        schedule.setTypeface(typeFace);
        rate.setTypeface(typeFace);

        //Edit profile button listener
        edit_btn = (ImageView) myView.findViewById(R.id.edit_profile_button);
        edit_btn.setOnClickListener(this);
        return myView;
    }

    // Edit profile button listener, starts TutorProfileEditFragment
    @Override
    public void onClick(View v)
    {
        try
        {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            TutorProfileEditFragment edit = new TutorProfileEditFragment();
            transaction.replace(R.id.tutor_fragment_container, edit);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
