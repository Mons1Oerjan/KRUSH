package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Seeder for the students database table
 */

public class StudentSeeder {

    /**
     * Function that inserts dummy student accounts into the DB
     * @param db
     */
    public static void insert(DBHelper db){
        db.student.insert(1,"@drawable/sample1","Greg","Miller","gregpmillr@gmail.com","password");
        db.student.insert(3,"@drawable/sample2","John","Wick","jw@gmail.com","password");
        db.student.insert(2,"@drawable/sample3","John","Smith","js@gmail.com","password");
        db.student.insert(4,"@drawable/sample4","Samuel","Jackson","sj@dal.ca","password");
        db.student.insert(5,"@drawable/sample5","Jack","Daniels","jd@outlook.com","password");
        db.student.insert(6,"@drawable/sample6","Tim","Thompson","tt@hotmail.ca","password");
        db.student.insert(7,"@drawable/sample7","Mike","Jackson","mj@hotmail.ca","password");
        db.student.insert(8,"@drawable/sample8","Lucy","Patterson","lp@hotmail.ca","password");
        db.student.insert(7,"@drawable/sample9","Carline","Jenkins","mj@hotmail.ca","password");
        db.student.insert(3,"@drawable/sample10","McKenzie","Rekker","mk@dal.ca","password");
        db.student.insert(4,"@drawable/sample11","Darlene","Tims","dt@gmail.ca","password");
    }
}
