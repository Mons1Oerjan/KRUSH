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

    public boolean insertCoursesTutors(int tutor_id, int course_id){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tutor_id", tutor_id);
        contentValues.put("course_id", course_id);
        dbWrite.insert("course_tutors", null, contentValues);
        return true;
    }

    public Cursor getCoursesFromTutor(int tutor_id){
        Cursor res = dbRead.rawQuery(
                "SELECT c.course_code, c.id " +
                        "FROM course_tutors ct " +
                        "INNER JOIN courses c ON c.id = ct.course_id " +
                        "WHERE ct.tutor_id="+tutor_id+""
                ,null
        );
        return res;
    }

    public Cursor getTutorsFromCourse(int course_id){
        Cursor res = dbRead.rawQuery(
                "SELECT t.f_name, t.l_name, t.id " +
                        "FROM course_tutors ct " +
                        "INNER JOIN tutors t ON t.id = ct.tutor_id " +
                        "WHERE ct.course_id="+course_id+""
                ,null
        );
        return res;
    }
}
