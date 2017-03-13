package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Seeder for the students database table
 */

public class StudentSeeder {

    public static void insert(DBHelper db){
        db.student.insert(1,"Greg","Miller","gregpmillr@gmail.com","password");
        db.student.insert(3,"John","Wick","jw@gmail.com","password");
        db.student.insert(2,"John","Smith","js@gmail.com","password");
        db.student.insert(4,"Samuel","Jackson","sj@dal.ca","password");
        db.student.insert(5,"Jack","Daniels","jd@outlook.com","password");
        db.student.insert(6,"Tim","Thompson","tt@hotmail.ca","password");
        db.student.insert(7,"Mike","Jackson","mj@hotmail.ca","password");
        db.student.insert(8,"Lucy","Patterson","lp@hotmail.ca","password");
        db.student.insert(7,"Carline","Jenkins","mj@hotmail.ca","password");
        db.student.insert(3,"McKenzie","Rekker","mk@dal.ca","password");
        db.student.insert(4,"Darlene","Tims","dt@gmail.ca","password");

    }
}
