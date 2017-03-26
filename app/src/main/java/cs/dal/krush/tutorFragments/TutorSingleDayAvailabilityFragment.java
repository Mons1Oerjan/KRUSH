package cs.dal.krush.tutorFragments;

import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cs.dal.krush.R;
import cs.dal.krush.adapters.RecyclerDataAdapter;
import cs.dal.krush.helpers.OnStartDragListener;
import cs.dal.krush.helpers.SimpleItemTouchHelperCallback;
import cs.dal.krush.models.DBHelper;

/**
 * Created by greg on 19/03/17.
 */

public class TutorSingleDayAvailabilityFragment extends Fragment implements OnStartDragListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    protected RecyclerDataAdapter mAdapter;
    private DBHelper db;
    private ListView lvTutorDaySchedule;
    private String year,month,day; // ex: 2017 03 20
    static int USER_ID;
    private ItemTouchHelper mItemTouchHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutor_single_day_availability, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        String date = getArguments().getString("DATE");

        db = new DBHelper(getActivity().getBaseContext());

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        loadSchedule(date);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {


        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Loads the ListView of the tutor's availability
     */
    public void loadSchedule(String date){
        ArrayList<String> dateTimes = new ArrayList<String>();

        Cursor rs;
        String time;

        String[] splitDate = date.split("[-]");

        year = splitDate[0];
        month = splitDate[1];
        day = splitDate[2];

        rs = db.availableTime.getByDay(year, month, day);
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

        mAdapter = new RecyclerDataAdapter();
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}