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
        db.student.insert(1,"sample1","Greg","Miller","gregpmillr@gmail.com","password");
        db.student.insert(3,"sample2","John","Wick","jw@gmail.com","password");
        db.student.insert(2,"sample3","John","Smith","js@gmail.com","password");
        db.student.insert(4,"sample4","Samuel","Jackson","sj@dal.ca","password");
        db.student.insert(5,"sample5","Jack","Daniels","jd@outlook.com","password");
        db.student.insert(6,"sample6","Tim","Thompson","tt@hotmail.ca","password");
        db.student.insert(7,"sample7","Mike","Jackson","mj@hotmail.ca","password");
        db.student.insert(8,"sample8","Lucy","Patterson","lp@hotmail.ca","password");
        db.student.insert(7,"sample9","Carline","Jenkins","mj@hotmail.ca","password");
        db.student.insert(3,"sample10","McKenzie","Rekker","mk@dal.ca","password");
        db.student.insert(4,"sample11","Darlene","Tims","dt@gmail.ca","password");
    }
}
