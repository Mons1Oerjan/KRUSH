package cs.dal.krush.tutorFragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import cs.dal.krush.R;
import cs.dal.krush.models.DBHelper;

/**
 * Sets up the Tutor Availability fragment. This fragment belongs to the TutorMainActivity class
 * and is accessed through the tutor's bottom navigation bar.
 *
 * The tutors can set their availability and schedule_view using this fragment.
 */
public class TutorAvailabilityFragment extends Fragment {

    Button btnDatePicker, btnStartTimePicker, btnEndTimePicker, btnSubmit, btnViewCalendar;
    EditText txtDate, txtStartTime, txtEndTime;
    TextView txtTitle, txtSelectAvailability, txtYourAvailability;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int sYear, sMonth, sDay, sStartHour, sStartMinute, sEndHour, sEndMinute;
    private String startTime,endTime;
    private DBHelper db;
    private RelativeLayout rl;
    private ListView lvTutorScheduleListView;
    private DateFormat timeFormatter = new SimpleDateFormat("h:mm a", Locale.getDefault());
    private GregorianCalendar startTimeCalendar = new GregorianCalendar();
    private GregorianCalendar endTimeCalendar = new GregorianCalendar();

    static int USER_ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutor_availability, container, false);
        USER_ID = getArguments().getInt("USER_ID");


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        db = new DBHelper(getContext());
        txtTitle=(TextView)getView().findViewById(R.id.txtAvailabilityTitle);
        txtSelectAvailability=(TextView)getView().findViewById(R.id.txtSelectAvailability);
        txtYourAvailability=(TextView)getView().findViewById(R.id.txtViewAvailability);
        btnDatePicker=(Button)getView().findViewById(R.id.btnDate);
        btnStartTimePicker=(Button)getView().findViewById(R.id.btnStartTime);
        btnEndTimePicker=(Button)getView().findViewById(R.id.btnEndTime);
        btnSubmit=(Button)getView().findViewById(R.id.btnSubmit);
        btnViewCalendar=(Button)getView().findViewById(R.id.btnViewCalendar);
        txtDate=(EditText)getView().findViewById(R.id.txtDate);
        txtStartTime=(EditText)getView().findViewById(R.id.txtStartTime);
        txtEndTime=(EditText)getView().findViewById(R.id.txtEndTime);
        lvTutorScheduleListView=(ListView)getView().findViewById(R.id.lvTutorScheduleListView);
        rl = (RelativeLayout)getView().findViewById(R.id.activity_tutor_calendar);

        btnStartTimePicker.setEnabled(false);
        txtStartTime.setEnabled(false);
        txtStartTime.setText("Select a date first");
        txtEndTime.setText("Select a date first");
        btnEndTimePicker.setEnabled(false);
        txtEndTime.setEnabled(false);
        txtDate.setFocusable(false);
        txtStartTime.setFocusable(false);
        txtEndTime.setFocusable(false);

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set custom app fonts
        txtTitle.setTypeface(typeFace);
        txtSelectAvailability.setTypeface(typeFace);
        txtYourAvailability.setTypeface(typeFace);

        loadSchedule();

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String nameMonth = formatMonth(monthOfYear, Locale.CANADA);
                                String nameDay = getFullDayName(dayOfMonth,Locale.CANADA);
                                sYear = year;
                                sMonth = monthOfYear;
                                sDay = dayOfMonth;

                                txtDate.setText( nameMonth + " " + dayOfMonth + ", " + nameDay );

                                btnStartTimePicker.setEnabled(true);
                                txtStartTime.setEnabled(true);
                                btnEndTimePicker.setEnabled(true);
                                txtEndTime.setEnabled(true);
                                txtStartTime.setText("");
                                txtEndTime.setText("");

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnStartTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                sStartHour = hourOfDay;
                                sStartMinute = minute;

                                startTimeCalendar.set(Calendar.HOUR, sStartHour);
                                startTimeCalendar.set(Calendar.MINUTE, sStartMinute);
                                startTimeCalendar.set(Calendar.HOUR_OF_DAY, sStartHour);

                                txtStartTime.setText(timeFormatter.format(startTimeCalendar.getTime()));

                                startTime = concatenateDateTime(sStartHour,sStartMinute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });

        btnEndTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                sEndHour = hourOfDay;
                                sEndMinute = minute;

                                endTimeCalendar.set(Calendar.HOUR, sEndHour);
                                endTimeCalendar.set(Calendar.MINUTE, sEndMinute);
                                endTimeCalendar.set(Calendar.HOUR_OF_DAY, sEndHour);

                                txtEndTime.setText(timeFormatter.format(endTimeCalendar.getTime()));

                                endTime = concatenateDateTime(sEndHour,sEndMinute);

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.availableTime.insert(startTime,endTime,1);
                txtDate.setText("");
                txtStartTime.setText("");
                txtEndTime.setText("");
                startTime = null;
                endTime = null;
                sYear = 0;
                sMonth = 0;
                sDay = 0;
                sStartHour = 0;
                sStartMinute = 0;
                sEndHour = 0;
                sEndMinute = 0;

                btnStartTimePicker.setEnabled(false);
                txtStartTime.setEnabled(false);
                btnEndTimePicker.setEnabled(false);
                txtEndTime.setEnabled(false);
                txtStartTime.setText("Select a date");
                txtEndTime.setText("Select a date");
                loadSchedule();

            }
        });

        btnViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                TutorCalendarFragment newFragment = new TutorCalendarFragment();

                ft.replace(R.id.tutor_fragment_container, newFragment);
                ft.addToBackStack(null);

                ft.commit();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Get the name of a month from integer
     * @param month to convert to String
     * @param locale default locale
     * @return formatted month name
     */
    public String formatMonth(int month, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("MMMM", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month);
        return formatter.format(calendar.getTime());
    }

    /**
     * Get the name of a day from integer
     * @param day to convert to String
     * @param locale default locale
     * @return formatted day name
     */
    public static String getFullDayName(int day, Locale locale) {

        DateFormat formatter = new SimpleDateFormat("EEEE", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return formatter.format(calendar.getTime());
    }

    /**
     * Get the full date String from params
     * @param day to convert to String
     * @param month to convert to String
     * @param year to convert to String
     * @return formatted DateTime
     */
    private String getDate(int day, int month, int year) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);

        return formatter.format(calendar.getTime());
    }

    /**
     * Construct a full date String in the format of "yyyy-MM-dd HH:mm:ss"
     * using the currently set day of month and year.
     * @param hour conversion helper to use selected hour
     * @param minute conversion helper to use selected minute
     * @return concatendated DateTime
     */
    public String concatenateDateTime(int hour, int minute){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, sDay);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MONTH, sMonth);
        calendar.set(Calendar.YEAR, sYear);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.HOUR, hour);

        return formatter.format(calendar.getTime());

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
        rs = db.availableTime.getAllOrderedByDay();
        try {
            while (rs.moveToNext()) {
                String s;
                s = rs.getString(rs.getColumnIndex("start_time"));
                String[] result = s.split("\\s");

                //set first run data to prevent missing first item
                if(firstRun){
                    tempDate = result[0] + ": " + result[1] + result[2] + ", ";
                    previousDate = result[0];
                    firstRun = false;
                }else{
                    //check if the date is the same (e.g. check if temp is 2017-01-10 against the result)
                    if(previousDate.equals(result[0])){
                        //same date, new time. So append the time.
                        tempDate = tempDate + result[1] + result[2] + ", ";
                    }else{
                        //new date, so add the old date, reset, then re-assign new date
                        dateTimes.add(tempDate);
                        tempDate = "";
                        tempDate = result[0] + ": " + result[1] + result[2] + ", ";

                        previousDate = result[0];

                    }
                }

            }
        } finally {
            rs.close();
        }

        //dump data
        dateTimes.add(tempDate);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                dateTimes );

        lvTutorScheduleListView.setAdapter(arrayAdapter);
    }

}
