package cs.dal.krush.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by greg on 27/02/17.
 */

public class TutoringSession extends Table{

    public TutoringSession(SQLiteDatabase dbWrite, SQLiteDatabase dbRead){
        super(dbWrite,dbRead);
    }

    @Override
    public boolean insert(Object[] args){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", (String) args[0]);
        contentValues.put("location", (String) args[1]);
        dbWrite.insert("tutoring_sessions", null, contentValues);
        return true;
    }

    @Override
    public Cursor getData(int id){
        Cursor res = dbRead.rawQuery("SELECT * FROM tutoring_sessions WHERE id="+id+"",null);
        return res;
    }

}
