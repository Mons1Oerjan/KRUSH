package cs.dal.krush;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fetch UI elements:
        final Button tutor_home_button = (Button) findViewById(R.id.tutor_login_button);
        final Button student_home_button = (Button) findViewById(R.id.student_login_button);

        //setup OnClickListeners:
        tutor_home_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TutorMainActivity.class);
                startActivity(i);
            }
        });
        student_home_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StudentMainActivity.class);
                startActivity(i);
            }
        });
    }
}
