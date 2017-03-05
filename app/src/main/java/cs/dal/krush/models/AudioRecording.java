package cs.dal.krush.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by greg on 27/02/17.
 */

public class AudioRecording extends Table {

    public AudioRecording(SQLiteDatabase dbWrite, SQLiteDatabase dbRead){
        super(dbWrite,dbRead);
    }


    @Override
    public boolean insert(Object[] args){
        ContentValues contentValues = new ContentValues();
        contentValues.put("location_id", (int) args[0]);
        contentValues.put("student_id", (int) args[1]);
        dbWrite.insert("audio_recording", null, contentValues);
        return true;
    }

    @Override
    public Cursor getData(int id){
        Cursor res = dbRead.rawQuery("SELECT * FROM audio_recording WHERE id="+id+"",null);
        return res;
    }
}
