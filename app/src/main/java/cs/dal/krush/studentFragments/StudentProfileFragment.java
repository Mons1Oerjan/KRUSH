package cs.dal.krush.studentFragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cs.dal.krush.R;

import static android.R.attr.onClick;


/**
 * Student Profile view.
 */
public class StudentProfileFragment extends Fragment implements View.OnClickListener
{
    ImageView edit_btn;
    TextView profile_name, email, school;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.student_profile, container, false);

        // Get TextViews
        profile_name = (TextView) myView.findViewById(R.id.profile_name);
        email = (TextView) myView.findViewById(R.id.profile_email);
        school = (TextView) myView.findViewById(R.id.profile_school);

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //Set custom app font
        profile_name.setTypeface(typeFace);
        email.setTypeface(typeFace);
        school.setTypeface(typeFace);

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
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            StudentProfileEditFragment edit = new StudentProfileEditFragment();
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
