package cs.dal.krush.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by greg on 27/02/17.
 */

public class School extends Table{

    public School(SQLiteDatabase dbWrite, SQLiteDatabase dbRead){
        super(dbWrite,dbRead);
    }

    @Override
    public boolean insert(Object[] args){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", (String) args[0]);
        contentValues.put("location_id", (int) args[1]);
        contentValues.put("type", (String) args[2]);
        dbWrite.insert("schools", null, contentValues);
        return true;
    }

    @Override
    public Cursor getData(int id){
        res = dbRead.rawQuery("SELECT * FROM schools WHERE id="+id+"",null);
        return res;
    }

    public Cursor getDataByName(String name){
        res = dbRead.rawQuery("SELECT * FROM schools WHERE name="+name+"",null);
        return res;
    }

    public Cursor getDataByType(String type){
        res = dbRead.rawQuery("SELECT * FROM schools WHERE type="+type+"",null);
        return res;
    }

    public Cursor getSchoolsByLocation(int location_id){
        res = dbRead.rawQuery("SELECT * FROM schools WHERE location_id="+location_id+"",null);
        return res;
    }

}
