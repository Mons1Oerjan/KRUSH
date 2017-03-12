package cs.dal.krush;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import cs.dal.krush.helpers.BottomNavigationViewHelper;
import cs.dal.krush.studentFragments.StudentHomeFragment;
import cs.dal.krush.studentFragments.StudentProfileFragment;
import cs.dal.krush.studentFragments.StudentQuickBookFragment;
import cs.dal.krush.studentFragments.StudentSessionsFragment;


public class StudentMainActivity extends FragmentActivity {
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main);

        //Set initial fragment to student home page
        StudentHomeFragment homeFragment = new StudentHomeFragment();
        homeFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.student_fragment_container, homeFragment).commit();

        //Custom bottom nav bar with disabled shifting
        bottomNav = (BottomNavigationView) findViewById(R.id.student_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNav);

        //Nav bar listener
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menu_item = item.getItemId();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch(menu_item) {
                    case R.id.menu_home:
                        StudentHomeFragment home = new StudentHomeFragment();
                        transaction.replace(R.id.student_fragment_container, home);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        return true;
                    case R.id.menu_booking:
                        StudentQuickBookFragment quickbook = new StudentQuickBookFragment();
                        transaction.replace(R.id.student_fragment_container, quickbook);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        return true;
                    case R.id.menu_profile:
                        StudentProfileFragment profile = new StudentProfileFragment();
                        transaction.replace(R.id.student_fragment_container, profile);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        return true;
                    case R.id.menu_sessions:
                        StudentSessionsFragment sessions = new StudentSessionsFragment();
                        transaction.replace(R.id.student_fragment_container, sessions);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        return true;
                }
                return false;
            }
        });
    }
}
