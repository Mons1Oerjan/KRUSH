package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Seeder for the courses_tutors database table
 */

public class CoursesTutorsSeeder {

    public static void insert(DBHelper db){
        db.coursesTutors.insertCoursesTutors(1, 1);
        db.coursesTutors.insertCoursesTutors(1, 2);
        db.coursesTutors.insertCoursesTutors(4,1);
        db.coursesTutors.insertCoursesTutors(3,1);
        db.coursesTutors.insertCoursesTutors(3,2);
        db.coursesTutors.insertCoursesTutors(4,4);
        db.coursesTutors.insertCoursesTutors(2,1);
        db.coursesTutors.insertCoursesTutors(2,5);
        db.coursesTutors.insertCoursesTutors(5,1);
        db.coursesTutors.insertCoursesTutors(5,3);
    }
}
