package cs.dal.krush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    }
}
