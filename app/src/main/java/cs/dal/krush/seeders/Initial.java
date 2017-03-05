package cs.dal.krush.seeders;

import android.content.Context;
import android.database.Cursor;

import cs.dal.krush.models.DBHelper;

/**
 * Created by greg on 27/02/17.
 */

public class Initial {
    /**
     * Declare variables
     */
    DBHelper mydb;
    Object[] args;
    Context context;

    /**
     * Empty constructor
     * @param context
     */
    public Initial(Context context){
        this.context = context;
    }

    /**
     * Seed data into database
     */
    public void insertData() {
        //instantiate DBHelper
        mydb = new DBHelper(context);

        /**
         * Insert data
         */
        mydb.location.insert("Halifax");
        mydb.location.insert("Stewiacke");
        mydb.school.insert("CPA",2,"Highschool");
        mydb.tutor.insert(1,1,"Greg","Miller","gregpmillr@gmail.com","password",2);
        mydb.student.insert(1,"Greg","Miller","gregpmillr@gmail.com","password");
        mydb.tutoringSession.insert(1,1,2,"test tutoring session");
        mydb.course.insert("Computer science 3", "CSCI3110");
        mydb.course.insert("Server Side Scripting","INFX2670");
        mydb.audioRecording.insert(1,2);
        mydb.coursesTutors.insertCoursesTutors(1, 1);
        mydb.coursesTutors.insertCoursesTutors(1, 2);

        /**
         * Get and display data
         */
        Cursor rs;
        rs = mydb.audioRecording.getData(1);
        rs.moveToFirst();
        String s;
        s = rs.getString(rs.getColumnIndex("location_id"));
        System.out.println(s);
        if (!rs.isClosed()) {
            rs.close();
        }

        rs = mydb.location.getLocationBySchool(1);
        rs.moveToFirst();
        s = rs.getString(rs.getColumnIndex("location"));
        System.out.println("LOCATION:" + s);
        if (!rs.isClosed()) {
            rs.close();
        }

        rs = mydb.course.getData(1);
        rs.moveToFirst();
        s = rs.getString(rs.getColumnIndex("course_code"));
        System.out.println(s);
        if (!rs.isClosed()) {
            rs.close();
        }

        rs = mydb.school.getData(1);
        rs.moveToFirst();
        s = rs.getString(rs.getColumnIndex("name"));
        System.out.println(s);
        if (!rs.isClosed()) {
            rs.close();
        }

        rs = mydb.tutor.getData(1);
        rs.moveToFirst();
        s = rs.getString(rs.getColumnIndex("f_name"));
        System.out.println(s);
        if (!rs.isClosed()) {
            rs.close();
        }
    } //end method
} // end class
