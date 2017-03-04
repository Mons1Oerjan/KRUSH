package cs.dal.krush;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fetch UI elements:
        final Button tutor_home_button = (Button) findViewById(R.id.tutor_login_button);
        final Button student_home_button = (Button) findViewById(R.id.student_login_button);
        final TextView krush_logo_textView = (TextView) findViewById(R.id.krushLogo);
        final TextView krush_slogan_textView = (TextView) findViewById(R.id.krushSlogan);

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set logo font style
        krush_logo_textView.setTypeface(typeFace);
        krush_slogan_textView.setTypeface(typeFace);

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
