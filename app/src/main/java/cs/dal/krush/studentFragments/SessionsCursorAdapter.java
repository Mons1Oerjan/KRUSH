package cs.dal.krush.studentFragments;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cs.dal.krush.R;

/**
 * This is the adapter class for the customized rows in the Upcoming Sessions list view
 */
public class SessionsCursorAdapter extends CursorAdapter {

    /**
     * Constructor
     *
     * @param context
     * @param cursor
     */
    public SessionsCursorAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }

    /**
     * Overrides the newView method used to inflate a new view and return it.
     *
     * @param context
     * @param cursor
     * @param parent
     * @return
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.student_home_listentry, parent, false);
    }

    /**
     * Overrides the bindView method used to bind all data to a given view.
     *
     * @param view
     * @param context
     * @param cursor
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor){
        //fetch UI components:
        TextView text1 = (TextView)view.findViewById(R.id.firstLine);
        TextView text2 = (TextView)view.findViewById(R.id.secondLine);
        ImageView image = (ImageView)view.findViewById(R.id.icon);

        //Get the tutor's profile image:
        //String imageFileName = cursor.getString(cursor.getColumnIndexOrThrow(""));

        //Get the tutor's name:
        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        text1.setText(title);

        //Get the tutor's rate and subject:
        String studentId = cursor.getString(cursor.getColumnIndexOrThrow("student_id"));
        String tutorId = cursor.getString(cursor.getColumnIndexOrThrow("tutor_id"));
        String text2content = "studentId: " + studentId + ", tutorId: " + tutorId;
        text2.setText(text2content);
    }
}
