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

    public boolean insert(int locationId, int schoolId, String firstName, String lastName, String
                          email, String password, int rate){
        ContentValues contentValues = new ContentValues();
        contentValues.put("location_id", locationId);
        contentValues.put("school_id", schoolId);
        contentValues.put("f_name", firstName);
        contentValues.put("l_name", lastName);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("rate", rate);
        dbWrite.insert("tutors", null, contentValues);
        return true;
    }

    @Override
    public Cursor getData(int id){
        Cursor res = dbRead.rawQuery("SELECT * FROM tutors WHERE id="+id+"",null);
        return res;
    }

    /**
     * Get a tutor by the f_name field
     * @param firstName
     * @return Cursor
     */
    public Cursor getDataByFirstName(String firstName){
        res = dbRead.rawQuery("SELECT * FROM tutors WHERE f_name="+firstName+"",null);
        return res;
    }

    /**
     * Get a tutor by the l_name field
     * @param lastName
     * @return Cursor
     */
    public Cursor getDataByLastName(String lastName){
        res = dbRead.rawQuery("SELECT * FROM tutors WHERE l_name="+lastName+"",null);
        return res;
    }

    /**
     * Get a tutor by the email field
     * @param email
     * @return Cursor
     */
    public Cursor getDataEmail(String email){
        res = dbRead.rawQuery("SELECT * FROM tutors WHERE email="+email+"",null);
        return res;
    }

    /**
     * Get a tutor by the rating field
     * @param rating
     * @return Cursor
     */
    public Cursor getDataByRating(int rating){
        res = dbRead.rawQuery("SELECT * FROM tutors WHERE rating="+rating+"",null);
        return res;
    }

    /**
     * Delete a tutor by id
     * @param id
     * @return int
     */
    public int deleteTutor(int id){
        return dbWrite.delete("tutors","id = ?",new String[] { Integer.toString(id) });
    }
}
