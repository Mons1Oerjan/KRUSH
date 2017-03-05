package cs.dal.krush;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class LoginMainActivity extends AppCompatActivity {
    //student = 1 || student = 2
    private int profileSelected = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        //get UI elements
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button login_button = (Button) findViewById(R.id.submitLogin);
        final TextView krush_logo_textView = (TextView) findViewById(R.id.krushLogo);
        final RadioButton student_select = (RadioButton) findViewById(R.id.radio_student);
        final RadioButton tutor_select = (RadioButton) findViewById(R.id.radio_tutor);


        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set logo font style
        krush_logo_textView.setTypeface(typeFace);

        //radio button click listeners
        student_select.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                profileSelected = 1;
            }
        });
        tutor_select.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                profileSelected = 2;
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LoginMainActivity.this, LoginMainActivity.class);

                if(profileSelected == 1){
                    i = new Intent(LoginMainActivity.this, StudentMainActivity.class);
                }
                else{
                    i = new Intent(LoginMainActivity.this, TutorMainActivity.class);
                }
                startActivity(i);
            }
        });
    }
}
