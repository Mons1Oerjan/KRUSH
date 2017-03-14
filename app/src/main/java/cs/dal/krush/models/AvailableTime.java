package cs.dal.krush.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by greg on 13/03/17.
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

    public Cursor getAllOrderedByDay(){
        res = dbRead.rawQuery("SELECT id,tutor_id,strftime('%d', start_time) as valDay, start_time FROM available_time " +
                "ORDER BY valDay",null);
        return res;
    }

}
