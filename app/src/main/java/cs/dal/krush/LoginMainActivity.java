package cs.dal.krush;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginMainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        //get UI elements
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button login_button = (Button) findViewById(R.id.submitLogin);
        final TextView krush_logo_textView = (TextView) findViewById(R.id.krushLogo);

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set logo font style
        krush_logo_textView.setTypeface(typeFace);

    }
}
