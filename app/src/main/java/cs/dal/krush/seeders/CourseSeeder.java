package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Seeder for the courses database table
 */

public class CourseSeeder {

    public static void insert(DBHelper db){
        db.course.insert("Computer Science 3", "CSCI2110");
        db.course.insert("Server Side Scripting","INFX2670");
        db.course.insert("Machine Learning", "CSCI4001");
        db.course.insert("Designing User Interfaces","CSCI3160");
        db.course.insert("Mobile Computing","CSCI4176");
        db.course.insert("Robotics","CSCI1400");
        db.course.insert("Social Computing","CSCI1107");
        db.course.insert("Animted Computing","CSCI1106");
    }
}
