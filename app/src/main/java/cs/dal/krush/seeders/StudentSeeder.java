package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Created by greg on 07/03/17.
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

    }
}
