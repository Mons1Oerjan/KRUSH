package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Created by greg on 07/03/17.
 */

public class CourseSeeder {

    public static void insert(DBHelper db){
        db.course.insert("Computer science 3", "CSCI3110");
        db.course.insert("Server Side Scripting","INFX2670");
        db.course.insert("Machine Learning", "CSCI4001");
        db.course.insert("Designing User Interfaces","CSCI3640");
        db.course.insert("Mobile Computing","CSCI4110");
        db.course.insert("Robotics","CSCI1400");
        db.course.insert("Social Computing","CSCI3600");
    }
}
