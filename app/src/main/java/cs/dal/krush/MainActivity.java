package cs.dal.krush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cs.dal.krush.seeders.Initial;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initial seederInitial = new Initial(getApplicationContext());
        seederInitial.insertData();
    }
}
