package cs.dal.krush.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by greg on 27/02/17.
 */

public class Student extends Table{

    public Student(SQLiteDatabase dbWrite, SQLiteDatabase dbRead){
        super(dbWrite,dbRead);
    }

    /**
     * Insert row into students table
     * @param schoolId
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @return boolean
     */
    public boolean insert(int schoolId, String profilePic, String firstName, String lastName, String email, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put("school_id", schoolId);
        contentValues.put("profile_pic", profilePic);
        contentValues.put("f_name", firstName);
        contentValues.put("l_name", lastName);
        contentValues.put("email", email);
        contentValues.put("password", password);
        dbWrite.insert("students", null, contentValues);
        return true;
    }

    @Override
    public Cursor getData(int id){
        return dbRead.rawQuery("SELECT * FROM students WHERE id="+id+"",null);
    }

    @Override
    public Cursor getAll() {
        return dbRead.rawQuery("SELECT * FROM students",null);
    }

    /**
     * Get a student from the f_name field
     * @param firstName
     * @return Cursor
     */
    public Cursor getDataByFirstName(String firstName){
        return dbRead.rawQuery("SELECT * FROM students WHERE f_name="+firstName+"",null);
    }

    /**
     * Get a student from the l_name field
     * @param lastName
     * @return Cursor
     */
    public Cursor getDataByLastName(String lastName){
        return dbRead.rawQuery("SELECT * FROM students WHERE l_name="+lastName+"",null);
    }

    /**
     * Get a student from the email field
     * @param email
     * @return Cursor
     */
    public Cursor getDataEmail(String email){
        return dbRead.rawQuery("SELECT * FROM students WHERE email="+email+"",null);
    }

    /**
     * Delete a student by id
     * @param id
     * @return int
     */
    public int deleteStudent(int id){
        return dbWrite.delete("students","id = ?",new String[] { Integer.toString(id) });
    }
}
