package cs.dal.krush.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Available Time Model class.
 */
public class AvailableTime extends Table{

    private String startTime;
    private String endTime;

    public AvailableTime(SQLiteDatabase dbWrite, SQLiteDatabase dbRead){
        super(dbWrite,dbRead);
    }

    /**
     * Insert a record into the available_time table
     * @param startTime
     * @param endTime
     * @return
     */
    public boolean insert(String startTime, String endTime, int tutor_id){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tutor_id", tutor_id);
        contentValues.put("start_time", startTime);
        contentValues.put("end_time", endTime);
        dbWrite.insert("available_time", null, contentValues);
        return true;
    }

    /**
     * Gets all available times for given tutor
     * @param tutorId
     * @return
     */
    public Cursor getDataByTutorId(int tutorId) {
        return dbRead.rawQuery("SELECT * FROM available_time WHERE tutor_id="+tutorId, null);
    }


    @Override
    public Cursor getData(int id) {
        res = dbRead.rawQuery("SELECT * FROM available_time WHERE id="+id+"",null);
        return res;
    }

    @Override
    public Cursor getAll() {
        res = dbRead.rawQuery("SELECT * FROM available_time",null);
        return res;
    }

    public Cursor getByDay(String year, String month, String day){
        //get all records with the same day, month and year to display the times.
        res = dbRead.rawQuery("SELECT strftime('%H:%M',start_time) as start, " +
                "strftime('%H:%M',end_time) as end, " +
                "strftime('%Y', start_time) as year, " +
                "strftime('%m', start_time) as month, " +
                "strftime('%d', start_time) as day " +
                "FROM available_time " +
                "WHERE year='"+year+"' " +
                "AND month='"+month+"' " +
                "AND day='"+day+"'",null);
        return res;
    }

    public Cursor getAllOrderedByDay(){
        res = dbRead.rawQuery("SELECT id,tutor_id,start_time, start_time FROM available_time " +
                "WHERE start_time > datetime('now','-1 day') " +
                "ORDER BY start_time",null);
        return res;
    }

}
