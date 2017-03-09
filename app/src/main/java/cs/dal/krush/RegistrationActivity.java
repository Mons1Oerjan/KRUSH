package cs.dal.krush;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * RegistrationActivity is used to register a new user to
 * the Krush application. It performs basic form validation
 * (i.e. required fields) and creates a new user in the DB
 * upon succesfully filling out the registration form.
 *
 */
public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //fetch UI elements:
        final TextView page_title_textView = (TextView) findViewById(R.id.text_view_page_header);
        final TextView radio_label_textView = (TextView) findViewById(R.id.radio_label);
        final EditText first_name_input = (EditText) findViewById(R.id.input_first_name);
        final EditText last_name_input = (EditText) findViewById(R.id.input_last_name);
        final EditText  email_input = (EditText) findViewById(R.id.input_email);
        final EditText  input_password = (EditText) findViewById(R.id.input_password);
        final EditText  input_password_confirm = (EditText) findViewById(R.id.input_password_confirm);
        final Button register_button = (Button) findViewById(R.id.submit_registration);

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set logo font style
        page_title_textView.setTypeface(typeFace);
        radio_label_textView.setTypeface(typeFace);
    }
}
