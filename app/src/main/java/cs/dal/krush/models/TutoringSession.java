package cs.dal.krush.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Tutoring Sessions model class.
 */
public class TutoringSession extends Table{

    public TutoringSession(SQLiteDatabase dbWrite, SQLiteDatabase dbRead){
        super(dbWrite,dbRead);
    }

    /**
     * Insert row into tutoring_sessions
     * @param studentId
     * @param tutorId
     * @param locationId
     * @param title
     * @return boolean
     */
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
        return dbRead.rawQuery("SELECT * FROM tutoring_sessions WHERE id="+id+"",null);
    }

    @Override
    public Cursor getAll() {
        return dbRead.rawQuery("SELECT * FROM tutoring_sessions",null);
    }

    /**
     * This is a query specifically meant for Cursor Adapters (renaming the id column to _id).
     * Gets all tutoring sessions.
     * @return Cursor
     */
    public Cursor getAllForCursorAdapter(){
        return dbRead.rawQuery("SELECT id as _id, student_id, tutor_id, location_id, title FROM tutoring_sessions", null);
    }

    /**
     * Get a tutoring session by the title
     * @param title
     * @return Cursor
     */
    public Cursor getDataByTitle(String title){
        return dbRead.rawQuery("SELECT * FROM tutoring_sessions WHERE title="+title+"",null);
    }

    /**
     * Get a tutoring session by the location_id field
     * @param locationId
     * @return Cursor
     */
    public Cursor getDataByLocationId(int locationId){
        return dbRead.rawQuery("SELECT * FROM tutoring_sessions WHERE location_id="+locationId+"",null);
    }

    /**
     * Get a tutoring session by the student_id field
     * @param studentId
     * @return Cursor
     */
    public Cursor getDataByStudentId(int studentId){
        return dbRead.rawQuery("SELECT * FROM tutoring_sessions WHERE student_id="+studentId+"",null);
    }

    /**
     * Gets all tutors and tutoring sessions by the given student.
     * This is a query specifically meant for Cursor Adapters (renaming the id column to _id).
     *
     * Source:
     * [7] Android column '_id' does not exist? (n.d.). Retrieved March 12, 2017,
     * from http://stackoverflow.com/questions/3359414/android-column-id-does-not-exist
     *
     * @param studentId
     * @return Cursor
     */
    public Cursor getDataByStudentIdForCursorAdapter(int studentId){
        return dbRead.rawQuery(
                "SELECT t.id AS _id, t.location_id, t.school_id, t.profile_pic, t.f_name, t.l_name, " +
                "t.email, t.password, t.rating, t.rate, t.revenue, ts.student_id, ts.title, ts.id " +
                "FROM tutors t " +
                "INNER JOIN tutoring_sessions ts ON _id = ts.tutor_id " +
                "WHERE ts.student_id=" + studentId + ""
                ,null
        );
    }

    /**
     * Get a tutoring session by the tutor_id field
     * @param tutorId
     * @return Cursor
     */
    public Cursor getDataByTutorId(int tutorId){
        return dbRead.rawQuery("SELECT * FROM tutoring_sessions WHERE tutor_id="+tutorId+"",null);
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
