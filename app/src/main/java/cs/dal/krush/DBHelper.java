package cs.dal.krush;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by greg on 26/02/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mike_casey.db";
    public static final String TUTOR_COLUMN_FNAME = "f_name";
    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE tutors " +
                        "(id INTEGER PRIMARY KEY," +
                        "f_name VARCHAR(255)," +
                        "l_name VARCHAR(255)," +
                        "email VARCHAR(255)," +
                        "password VARCHAR(255)," +
                        "rating INTEGER," +
                        "locations VARCHAR(255)," +
                        "rate INTEGER," +
                        "revenue INTEGER)"
        );

        db.execSQL(
                "CREATE TABLE students " +
                        "(id INTEGER PRIMARY KEY," +
                        "f_name VARCHAR(255)," +
                        "l_name VARCHAR(255)," +
                        "email VARCHAR(255)," +
                        "password VARCHAR(255)," +
                        "credit_card_num VARCHAR(255)," +
                        "credit_card_exp_date DATE," +
                        "credit_card_cvv INTEGER)"
        );

        db.execSQL(
                "CREATE TABLE tutoring_sessions " +
                        "(id INTEGER PRIMARY KEY," +
                        "title VARCHAR(255)," +
                        "start_time DATETIME," +
                        "end_time DATETIME," +
                        "location VARCHAR(255)," +
                        "student_id INTEGER," +
                        "tutor_id INTEGER," +
                        "FOREIGN KEY(student_id) REFERENCES students(id)," +
                        "FOREIGN KEY(tutor_id) REFERENCES tutors(id))"
        );

        db.execSQL(
                "CREATE TABLE schools " +
                        "(id INTEGER PRIMARY KEY," +
                        "name VARCHAR(255)," +
                        "location VARCHAR(255)," +
                        "type VARCHAR(255))"
        );

        db.execSQL(
                "CREATE TABLE courses " +
                        "(id INTEGER PRIMARY KEY," +
                        "title VARCHAR(255)," +
                        "course_code VARCHAR(255))"
        );

        db.execSQL(
                "CREATE TABLE audio_recording " +
                        "(id INTEGER PRIMARY KEY," +
                        "student_id INTEGER," +
                        "location VARCHAR(255)," +
                        "FOREIGN KEY(student_id) REFERENCES students(id))"
        );

        db.execSQL(
                "CREATE TABLE course_tutors " +
                        "(id INTEGER PRIMARY KEY," +
                        "course_id INTEGER NOT NULL," +
                        "tutor_id INTEGER NOT NULL," +
                        "FOREIGN KEY(course_id) REFERENCES courses(id)," +
                        "FOREIGN KEY(tutor_id) REFERENCES tutors(id))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tutors");
        db.execSQL("DROP TABLE IF EXISTS students");
        db.execSQL("DROP TABLE IF EXISTS tutoring_sessions");
        db.execSQL("DROP TABLE IF EXISTS schools");

        onCreate(db);
    }

    public boolean insertTutor(String f_name, String l_name, String email, String password, int rate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("f_name", f_name);
        contentValues.put("l_name", l_name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("rate", rate);
        db.insert("tutors", null, contentValues);
        return true;
    }

    public Cursor getTutorData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tutors WHERE id="+id+"",null);
        return res;
    }

    public boolean insertStudent(String f_name, String l_name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("f_name", f_name);
        contentValues.put("l_name", l_name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        db.insert("students", null, contentValues);
        return true;
    }

    public Cursor getStudentData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM students WHERE id="+id+"",null);
        return res;
    }

    public boolean insertSchool(String name, String location, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("location", location);
        contentValues.put("type", type);
        db.insert("schools", null, contentValues);
        return true;
    }

    public Cursor getSchoolData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM schools WHERE id="+id+"",null);
        return res;
    }

    public boolean insertCourse(String title, String course_code){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("course_code", course_code);
        db.insert("courses", null, contentValues);
        return true;
    }

    public Cursor getCourseData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM courses WHERE id="+id+"",null);
        return res;
    }

    public boolean insertAudioRecording(String location, int student_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("location", location);
        contentValues.put("student_id", student_id);
        db.insert("audio_recording", null, contentValues);
        return true;
    }

    public Cursor getAudioRecordingData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM audio_recording WHERE id="+id+"",null);
        return res;
    }

    public boolean insertTutoringSession(String title, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("location", location);
        db.insert("tutoring_sessions", null, contentValues);
        return true;
    }

    public Cursor getTutoringSessionData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tutoring_sessions WHERE id="+id+"",null);
        return res;
    }

    public boolean insertCoursesTutors(int tutor_id, int course_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tutor_id", tutor_id);
        contentValues.put("course_id", course_id);
        db.insert("course_tutors", null, contentValues);
        return true;
    }

    public Cursor getCoursesFromTutor(int tutor_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT c.course_code, c.id " +
                "FROM course_tutors ct " +
                "INNER JOIN courses c ON c.id = ct.course_id " +
                "WHERE ct.tutor_id="+tutor_id+""
                ,null
        );
        return res;
    }

    public Cursor getTutorsFromCourse(int course_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT t.f_name, t.l_name, t.id " +
                        "FROM course_tutors ct " +
                        "INNER JOIN tutors t ON t.id = ct.tutor_id " +
                        "WHERE ct.course_id="+course_id+""
                ,null
        );
        return res;
    }



    @Override
    public void onDowngrade (SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
