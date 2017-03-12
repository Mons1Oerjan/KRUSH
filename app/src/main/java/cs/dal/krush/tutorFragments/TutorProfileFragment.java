package cs.dal.krush.tutorFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cs.dal.krush.R;
import cs.dal.krush.studentFragments.StudentProfileEditFragment;

public class TutorProfileFragment extends Fragment implements View.OnClickListener
{
    ImageView edit_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.tutor_profile, container, false);

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
