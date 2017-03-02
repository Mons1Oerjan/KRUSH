package cs.dal.krush;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import cs.dal.krush.helpers.BottomNavigationViewHelper;


public class TutorMainActivity extends FragmentActivity
{
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int menu_item = item.getItemId();
                if(menu_item == R.id.menu_home)
                {
                    TutorHomeFragment home = new TutorHomeFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.tutor_fragment_container, home);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                else if (menu_item == R.id.menu_profile)
                {
                    TutorProfileFragment profile = new TutorProfileFragment();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.tutor_fragment_container, profile);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                else
                    return false;
            }
        });
    }
}
