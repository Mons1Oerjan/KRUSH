package cs.dal.krush;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);
        mydb.insertTutor("greg","miller","gregpmillr@gmail.com","password",2);
        mydb.insertStudent("gregstudent","millerstudent","gregpmillrstudent@gmail.com","password");
        mydb.insertTutoringSession("testsesh","3565 connaught avenue");
        mydb.insertCourse("Computer science 3", "CSCI3110");
        mydb.insertCourse("Server Side Scripting", "INFX2670");

        mydb.insertCoursesTutors(1,1);
        mydb.insertCoursesTutors(1,2);


        Cursor rs = mydb.getCoursesFromTutor(1);
        ArrayList<String> course_codes = new ArrayList<String>();

        //print out each item from query
        try{
            while(rs.moveToNext()){
                course_codes.add(rs.getString(rs.getColumnIndex("course_code")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        if(!rs.isClosed()){
            rs.close();
        }

        System.out.println(course_codes.toString());
    }
}
