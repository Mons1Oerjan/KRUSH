package cs.dal.krush.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by greg on 27/02/17.
 */

public class CoursesTutors {
    SQLiteDatabase dbWrite;
    SQLiteDatabase dbRead;

    public CoursesTutors(SQLiteDatabase dbWrite, SQLiteDatabase dbRead){
        this.dbWrite = dbWrite;
        this.dbRead = dbRead;
    }

    /**
     * insert into this table using the tutor_id and course_id fields
     * @param tutor_id
     * @param course_id
     * @return boolean
     */
    public boolean insertCoursesTutors(int tutor_id, int course_id){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tutor_id", tutor_id);
        contentValues.put("course_id", course_id);
        dbWrite.insert("course_tutors", null, contentValues);
        return true;
    }

    /**
     * Get courses from a specific tutor
     * @param tutor_id
     * @return Cursor
     */
    public Cursor getCoursesFromTutor(int tutor_id){
        return dbRead.rawQuery(
                "SELECT c.course_code, c.id " +
                        "FROM course_tutors ct " +
                        "INNER JOIN courses c ON c.id = ct.course_id " +
                        "WHERE ct.tutor_id="+tutor_id+""
                ,null
        );
    }

    /**
     * Get tutors from a specific course
     * @param course_id
     * @return Cursor
     */
    public Cursor getTutorsFromCourse(int course_id){
        return dbRead.rawQuery(
                "SELECT t.f_name, t.l_name, t.id " +
                        "FROM course_tutors ct " +
                        "INNER JOIN tutors t ON t.id = ct.tutor_id " +
                        "WHERE ct.course_id="+course_id+""
                ,null
        );
    }
}
