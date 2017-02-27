package cs.dal.krush.seeders;

import android.content.Context;
import android.database.Cursor;

import cs.dal.krush.models.DBHelper;

/**
 * Created by greg on 27/02/17.
 */

public class Initial {
    DBHelper mydb;
    Object[] args;
    Context context;

    public Initial(Context context){
        this.context = context;
    }

    public void insertData() {

        args = new Object[10];
        mydb = new DBHelper(context);

        args[0] = "greg";
        args[1] = "miller";
        args[2] = "gregpmillr@gmail.com";
        args[3] = "password";
        args[4] = 2;
        mydb.tutor.insert(args);
        args[0] = "testsesh";
        args[1] = "3565 connaught avenue";
        mydb.tutoringSession.insert(args);
        args[0] = "Computer Science 3";
        args[1] = "CSCI3110";
        mydb.course.insert(args);
        args[0] = "Server Side Scripting";
        args[1] = "INFX2670";
        mydb.course.insert(args);
        args[0] = "gregstudent";
        args[1] = "millerstudent";
        args[2] = "gregpmillrstudent@gmail.com";
        args[3] = "password";
        mydb.student.insert(args);
        args[0] = "Halifax";
        args[1] = 1;
        mydb.audioRecording.insert(args);
        args[0] = "CPA";
        args[1] = "HFX";
        args[2] = "Highschool";
        mydb.school.insert(args);
        mydb.coursesTutors.insertCoursesTutors(1, 1);
        mydb.coursesTutors.insertCoursesTutors(1, 2);


        Cursor rs;
        rs = mydb.audioRecording.getData(1);
        rs.moveToFirst();
        String s;
        s = rs.getString(rs.getColumnIndex("location"));
        System.out.println(s);
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
    }
}
