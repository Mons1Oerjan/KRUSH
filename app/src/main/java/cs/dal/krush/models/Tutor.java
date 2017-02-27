package cs.dal.krush.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by greg on 27/02/17.
 */

public class Tutor extends Table{

    public Tutor(SQLiteDatabase dbWrite, SQLiteDatabase dbRead){
        super(dbWrite,dbRead);
    }

    @Override
    public boolean insert(Object[] args){
        ContentValues contentValues = new ContentValues();
        contentValues.put("f_name", (String) args[0]);
        contentValues.put("l_name", (String) args[1]);
        contentValues.put("email", (String) args[2]);
        contentValues.put("password", (String) args[3]);
        contentValues.put("rate", (int) args[4]);
        dbWrite.insert("tutors", null, contentValues);
        return true;
    }

    @Override
    public Cursor getData(int id){
        Cursor res = dbRead.rawQuery("SELECT * FROM tutors WHERE id="+id+"",null);
        return res;
    }
}
