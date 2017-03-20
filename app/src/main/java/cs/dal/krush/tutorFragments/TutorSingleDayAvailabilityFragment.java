package cs.dal.krush.tutorFragments;

import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cs.dal.krush.R;
import cs.dal.krush.models.DBHelper;

/**
 * Created by greg on 19/03/17.
 */

public class TutorSingleDayAvailabilityFragment extends Fragment {

    private DBHelper db;
    private ListView lvTutorDaySchedule;

    static int USER_ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_single_day_availability, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        db = new DBHelper(getActivity().getBaseContext());
        lvTutorDaySchedule=(ListView)getView().findViewById(R.id.lvTutorDaySchedule);

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        loadSchedule();

        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Loads the ListView of the tutor's availability
     */
    public void loadSchedule(){
        String previousDate = "";
        String tempDate = "";
        List<String> dateTimes = new ArrayList<String>();
        boolean firstRun = true;
        Cursor rs;
        String time;
        rs = db.availableTime.getByDay("2017","03","20");
        try {
            while (rs.moveToNext()) {

                time = rs.getString(rs.getColumnIndex("start"));
                time += " - ";
                time += rs.getString(rs.getColumnIndex("end"));

                dateTimes.add(time);

                time = "";
            }
        } finally {
            rs.close();
        }



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                dateTimes );

        lvTutorDaySchedule.setAdapter(arrayAdapter);
    }
}
