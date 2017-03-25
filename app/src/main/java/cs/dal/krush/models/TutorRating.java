package cs.dal.krush.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Tutor rating modal
 */

public class TutorRating extends Table {

    public TutorRating(SQLiteDatabase dbWrite, SQLiteDatabase dbRead) { super(dbWrite,dbRead); }


    public boolean insert(float rating, int studentId, int tutorId){
        ContentValues contentValues = new ContentValues();
        contentValues.put("rating", rating);
        contentValues.put("student_id", studentId);
        contentValues.put("tutor_id", tutorId);
        dbWrite.insert("tutor_ratings", null, contentValues);
        return true;
    }

    public boolean updateTutorRating(float rating, int studentId, int tutorId){

        ContentValues contentValues = new ContentValues();
        contentValues.put("rating", rating);
        dbWrite.update("tutor_ratings", contentValues, "tutor_id=? AND student_id=?", new String[] { "" + tutorId, "" + studentId});
        return true;
    }
    @Override
    public Cursor getData(int id) {
        return dbRead.rawQuery("SELECT * FROM tutor_ratings WHERE id="+id+"",null);
    }

    @Override
    public Cursor getAll() { return dbRead.rawQuery("SELECT * FROM tutor_ratings",null); }

    /**
     * Gets all ratings records for given tutor
     * This is a query specifically meant for Cursor Adapters (renaming the id column to _id).
     *
     * @return Cursor
     */
    public Cursor getTutorRatingByTutorId(int tutorId){
        return dbRead.rawQuery(
                "SELECT id AS _id, tutor_id, student_id, rating " +
                "FROM tutor_ratings " +
                "WHERE tutor_id=" + tutorId
                ,null
        );
    }

    public Cursor getTutorRatingByTutorAndStudentId(int tutorId, int studentId){
        return dbRead.rawQuery(
                "SELECT id AS _id, tutor_id, student_id, rating " +
                "FROM tutor_ratings " +
                "WHERE tutor_id=" + tutorId +
                " AND student_id=" + studentId
                ,null
        );
    }




}
