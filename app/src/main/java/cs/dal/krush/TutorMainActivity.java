package cs.dal.krush;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import cs.dal.krush.helpers.BottomNavigationViewHelper;
import cs.dal.krush.studentFragments.StudentProfileEditFragment;
import cs.dal.krush.studentFragments.StudentProfileFragment;
import cs.dal.krush.tutorFragments.TutorCalendarFragment;
import cs.dal.krush.tutorFragments.TutorHomeFragment;
import cs.dal.krush.tutorFragments.TutorProfileEditFragment;
import cs.dal.krush.tutorFragments.TutorProfileFragment;
import cs.dal.krush.tutorFragments.TutorSessionsFragment;


public class TutorMainActivity extends FragmentActivity {
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_main);

        //Set initial fragment to tutor home page
        TutorHomeFragment homeFragment = new TutorHomeFragment();
        homeFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.tutor_fragment_container, homeFragment).commit();

         //Custom bottom nav bar with disabled shifting
        bottomNav = (BottomNavigationView) findViewById(R.id.tutor_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNav);

        //Nav bar listener
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menu_item = item.getItemId();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch(menu_item) {
                    case R.id.menu_home:
                        TutorHomeFragment home = new TutorHomeFragment();
                        transaction.replace(R.id.tutor_fragment_container, home);
                        transaction.commit();
                        return true;
                    case R.id.menu_profile:
                        TutorProfileFragment profile = new TutorProfileFragment();
                        transaction.replace(R.id.tutor_fragment_container, profile);
                        transaction.commit();
                        return true;
                    case R.id.menu_sessions:
                        TutorSessionsFragment sessions = new TutorSessionsFragment();
                        transaction.replace(R.id.tutor_fragment_container, sessions);
                        transaction.commit();
                        return true;
                    case R.id.menu_calendar:
                        TutorCalendarFragment calendar = new TutorCalendarFragment();
                        transaction.replace(R.id.tutor_fragment_container, calendar);
                        transaction.commit();
                        return true;
                }
                return false;
            }
        });
    }

    public void editProfile(View view)
    {
        try
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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

    public void saveProfile(View view)
    {
        // Save information in fields to database when functionality is hooked up

        // Return to profile view
        try
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            TutorProfileFragment profile = new TutorProfileFragment();
            transaction.replace(R.id.tutor_fragment_container, profile);
            transaction.commit();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
