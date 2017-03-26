package cs.dal.krush.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import cs.dal.krush.R;
import cs.dal.krush.models.DBHelper;

/**
 * Created by greg on 25/03/17.
 */

public class CustomTutorDayTimeAdapter extends ArrayAdapter<TutorDayTimeRowitem>{

    Context context;
    List<TutorDayTimeRowitem> items;
    String date;
    int USER_ID;

    public CustomTutorDayTimeAdapter(Context context, int resourceId,
                                     List<TutorDayTimeRowitem> items,
                                     String date,
                                     int USER_ID){
        super(context,resourceId,items);
        this.items = items;
        this.context = context;
        this.date = date;
        this.USER_ID = USER_ID;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtText;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        TutorDayTimeRowitem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.tutor_single_day_availability_row_layout, null);
            holder = new ViewHolder();
            holder.txtText = (TextView) convertView.findViewById(R.id.tvAvailabilityTimeOfDay);
            holder.imageView = (ImageView) convertView.findViewById(R.id.ivDeleteTimeOfDay);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtText.setText(rowItem.getText());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TutorDayTimeRowitem item = items.get(position);
                Toast.makeText(context, item.getText(), Toast.LENGTH_SHORT).show();

                //get the full date for sqlite
                String formattedDate = formatDate(date,item.getText());

                DBHelper db = new DBHelper(context);
                db.availableTime.deleteRecord(formattedDate,USER_ID);

                items.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public String formatDate(String date, String time){
        //I have 2017-03-25 and 11:30 - 12:30
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        String[] splitDate = date.split("[-]");
        int year = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int day = Integer.parseInt(splitDate[2]);

        String[] splitTime = time.split("\\s");
        String[] splitStartTime = splitTime[0].split("[:]");
        int hourOfDay = Integer.parseInt(splitStartTime[0]);
        int minute = Integer.parseInt(splitStartTime[1]);

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        return formatter.format(calendar.getTime());
    }
}
