package cs.dal.krush.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by greg on 27/02/17.
 */

public class Course extends Table{

    public Course(SQLiteDatabase dbWrite, SQLiteDatabase dbRead){
        super(dbWrite,dbRead);
    }

    @Override
    public boolean insert(Object[] args){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", (String) args[0]);
        contentValues.put("course_code", (String) args[1]);
        dbWrite.insert("courses", null, contentValues);
        return true;
    }

    @Override
    public Cursor getData(int id){
        res = dbRead.rawQuery("SELECT * FROM courses WHERE id="+id+"",null);
        return res;
    }

    public Cursor getDataByTitle(String title){
        res = dbRead.rawQuery("SELECT * FROM courses WHERE title="+title+"",null);
        return res;
    }

    public Cursor getDataByCourseCode(String courseCode){
        res = dbRead.rawQuery("SELECT * FROM courses WHERE course_code="+courseCode+"",null);
        return res;
    }
}
