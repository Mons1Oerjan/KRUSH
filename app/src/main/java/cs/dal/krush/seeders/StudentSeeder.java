package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Seeder for the students database table
 */
public class StudentSeeder {

    /**
     * Function that inserts dummy student accounts into the DB.
     * Insert format: schoolId, profilePic, firstName, lastName, email, password.
     * @param db
     */
    public static void insert(DBHelper db){
        db.student.insert(1,"egg1","Greg","Miller","gregpmillr@gmail.com","password");
        db.student.insert(3,"egg1","John","Wick","jw@gmail.com","password");
        db.student.insert(2,"egg1","John","Smith","js@gmail.com","password");
        db.student.insert(4,"egg1","Samuel","Jackson","sj@dal.ca","password");
        db.student.insert(5,"egg1","Jack","Daniels","jd@outlook.com","password");
        db.student.insert(6,"egg1","Tim","Thompson","tt@hotmail.ca","password");
        db.student.insert(7,"egg1","Mike","Jackson","mj@hotmail.ca","password");
        db.student.insert(8,"egg1","Lucy","Patterson","lp@hotmail.ca","password");
        db.student.insert(7,"egg1","Carline","Jenkins","mj@hotmail.ca","password");
        db.student.insert(3,"egg1","McKenzie","Rekker","mk@dal.ca","password");
        db.student.insert(4,"egg1","Darlene","Tims","dt@gmail.ca","password");
    }
}
