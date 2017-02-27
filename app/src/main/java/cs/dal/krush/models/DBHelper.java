package cs.dal.krush.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by greg on 26/02/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mike_casey.db";
    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    public Table student = new Student(this.getWritableDatabase(), this.getReadableDatabase());
    public Table tutor = new Tutor(this.getWritableDatabase(), this.getReadableDatabase());
    public Table school = new School(this.getWritableDatabase(), this.getReadableDatabase());
    public Table course = new Course(this.getWritableDatabase(), this.getReadableDatabase());
    public Table audioRecording = new AudioRecording(this.getWritableDatabase(), this.getReadableDatabase());
    public Table tutoringSession = new TutoringSession(this.getWritableDatabase(), this.getReadableDatabase());
    public CoursesTutors coursesTutors = new CoursesTutors(this.getWritableDatabase(), this.getReadableDatabase());

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
        db.execSQL("DROP TABLE IF EXISTS courses");
        db.execSQL("DROP TABLE IF EXISTS audio_recording");
        db.execSQL("DROP TABLE IF EXISTS course_tutors");

        onCreate(db);
    }




    @Override
    public void onDowngrade (SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
