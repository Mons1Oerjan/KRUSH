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

    public boolean insert(int studentId, int tutorId, int locationId, String title){
        ContentValues contentValues = new ContentValues();
        contentValues.put("student_id", studentId);
        contentValues.put("tutor_id", tutorId);
        contentValues.put("location_id", locationId);
        contentValues.put("title", title);
        dbWrite.insert("tutoring_sessions", null, contentValues);
        return true;
    }

    @Override
    public Cursor getData(int id){
        Cursor res = dbRead.rawQuery("SELECT * FROM tutoring_sessions WHERE id="+id+"",null);
        return res;
    }

    /**
     * Get a tutoring session by the title
     * @param title
     * @return Cursor
     */
    public Cursor getDataByTitle(String title){
        Cursor res = dbRead.rawQuery("SELECT * FROM tutoring_sessions WHERE title="+title+"",null);
        return res;
    }

    /**
     * Get a tutoring session by the location_id field
     * @param locationId
     * @return Cursor
     */
    public Cursor getDataByLocationId(int locationId){
        Cursor res = dbRead.rawQuery("SELECT * FROM tutoring_sessions WHERE location_id="+locationId+"",null);
        return res;
    }

    /**
     * Delete tutoring session by id
     * @param id
     * @return int
     */
    public int deleteTutoringSession(int id){
        return dbWrite.delete("tutoring_sessions","id = ?",new String[] { Integer.toString(id) });
    }

}
