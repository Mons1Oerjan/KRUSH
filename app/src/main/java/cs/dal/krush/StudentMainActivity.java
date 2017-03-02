package cs.dal.krush;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import cs.dal.krush.helpers.BottomNavigationViewHelper;


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
                    case R.id.menu_quick_book:
                        StudentQuickBookFragment quickbook = new StudentQuickBookFragment();
                        transaction.replace(R.id.student_fragment_container, quickbook);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        return true;
                }
                return false;
            }
        });
    }
}
