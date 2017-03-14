package cs.dal.krush.tutorFragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import cs.dal.krush.R;
import cs.dal.krush.models.AvailableTime;
import cs.dal.krush.models.DBHelper;

/**
 * Tutor calendar view.
 */
public class TutorCalendarFragment extends Fragment {

    Button btnDatePicker, btnStartTimePicker, btnEndTimePicker, btnSubmit;
    EditText txtDate, txtStartTime, txtEndTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int sYear, sMonth, sDay, sStartHour, sStartMinute, sEndHour, sEndMinute;
    private String startTime,endTime;
    private DBHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.tutor_calendar_fragment, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        db = new DBHelper(getContext());

        List<String> dates = new ArrayList<String>();
        List<String> times = new ArrayList<String>();
        String temp = "";

        Cursor rs;
        rs = db.availableTime.getAllOrderedByDay();
        try {
            while (rs.moveToNext()) {
                String s;
                s = rs.getString(rs.getColumnIndex("start_time"));
                String[] result = s.split("\\s");
                if(temp.equals(result[0])){
                    System.out.println("Same date, time is: " + result[1] );
                }else{
                    System.out.println("NEW date, time is: " + result[1] );
                    temp = result[0];
                }

                System.out.println(result[0]);
            }
        } finally {
            rs.close();
        }

        btnDatePicker=(Button)getView().findViewById(R.id.btnDate);
        btnStartTimePicker=(Button)getView().findViewById(R.id.btnStartTime);
        btnEndTimePicker=(Button)getView().findViewById(R.id.btnEndTime);
        btnSubmit=(Button)getView().findViewById(R.id.btnSubmit);
        txtDate=(EditText)getView().findViewById(R.id.txtDate);
        txtStartTime=(EditText)getView().findViewById(R.id.txtStartTime);
        txtEndTime=(EditText)getView().findViewById(R.id.txtEndTime);


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

                                String nameMonth = formatMonth(monthOfYear,Locale.CANADA);
                                String nameDay = getFullDayName(dayOfMonth,Locale.CANADA);
                                String selectedDate = getDate(dayOfMonth,monthOfYear,year);
                                System.out.println("selected date" + selectedDate);
                                sYear = year;
                                sMonth = monthOfYear;
                                sDay = dayOfMonth;

                                txtDate.setText( nameMonth + " " + dayOfMonth + ", " + nameDay );

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
                                txtStartTime.setText(hourOfDay + ":" + minute);

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
                                txtEndTime.setText(hourOfDay + ":" + minute);

                                endTime = concatenateDateTime(sEndHour,sEndMinute);

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(db.availableTime.insert(startTime,endTime,1)){
                    System.out.println("worked!");
                }

            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    public String formatMonth(int month, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("MMMM", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month);
        return formatter.format(calendar.getTime());
    }

    public static String getFullDayName(int day, Locale locale) {

        DateFormat formatter = new SimpleDateFormat("EEEE", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return formatter.format(calendar.getTime());
    }

    private String getDate(int day, int month, int year) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);

        return formatter.format(calendar.getTime());
    }

    public String concatenateDateTime(int hour, int minute){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, sDay-1);
        calendar.set(Calendar.MONTH, sMonth);
        calendar.set(Calendar.YEAR, sYear);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.HOUR, hour);

        return formatter.format(calendar.getTime());

    }

}
