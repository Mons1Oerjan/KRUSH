package cs.dal.krush;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import cs.dal.krush.helpers.BottomNavigationViewHelper;

public class TutorHomeActivity extends AppCompatActivity
{
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_home);

        /* Custom bottom nav bar with disabled shifting*/
        bottomNav = (BottomNavigationView) findViewById(R.id.tutor_nav);
        BottomNavigationViewHelper.disableShiftMode(bottomNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch(item.getItemId())
                {
                    Intent i;
                    case R.id.menu_home:
                        i = new Intent(TutorHomeActivity.this, TutorHomeActivity.class);
                        startActivity(i);
                        break;
                    case R.id.menu_profile:
                        i = new Intent(TutorHomeActivity.this, TutorProfileActivity.class);
                        startActivity(i);
                        break;
                    case R.id.menu_sessions:
                        i = new Intent(TutorHomeActivity.this, TutorSessuibsActivity.class);
                        startActivity(i);
                        break;
                    case R.id.menu_calendar:
                        i = new Intent(TutorHomeActivity.this, TutorCalendarActivity.class);
                        startActivity(i);
                        break;

                }
                return false;
            }
        });
    }

}
