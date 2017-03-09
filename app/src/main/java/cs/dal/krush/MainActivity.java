package cs.dal.krush;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cs.dal.krush.seeders.Initial;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //seed data
        Initial seederInitial = new Initial(getApplicationContext());
        seederInitial.insertData();

        //fetch UI elements:
        final Button signup_home_button = (Button) findViewById(R.id.signup_button);
        final Button login_home_button = (Button) findViewById(R.id.login_button);
        final TextView krush_logo_textView = (TextView) findViewById(R.id.krushLogo);
        final TextView krush_slogan_textView = (TextView) findViewById(R.id.krushSlogan);

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set logo font style
        krush_logo_textView.setTypeface(typeFace);
        krush_slogan_textView.setTypeface(typeFace);

        //setup OnClickListeners:
        signup_home_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });
        login_home_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginMainActivity.class);
                startActivity(i);
            }
        });
    }
}
