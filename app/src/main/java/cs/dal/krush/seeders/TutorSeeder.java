package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Seeder for the locations database table
 */

public class TutorSeeder {

    /**
     * Function that inserts dummy tutor accounts into the DB
     * @param db
     */
    public static void insert(DBHelper db){
        db.tutor.insert(1,1,"@drawable/sample1","Greg","Miller","gregpmillr@gmail.com","password",2);
        db.tutor.insert(3,2,"@drawable/sample2","Michael","Casey","mc@dal.ca","password",2);
        db.tutor.insert(2,1,"@drawable/sample3","Orjan","Monsen","om@dal.ca","password",5);
        db.tutor.insert(4,5,"@drawable/sample4","Eric","Desjardins","ed@dal.ca","password",1);
        db.tutor.insert(5,3,"@drawable/sample5","Connor","Walsh","cw@dal.ca","password",4);
        db.tutor.insert(2,2,"@drawable/sample6","Jack","Reaper","jr@dal.ca","password",2);
        db.tutor.insert(5,4,"@drawable/sample7","Peter","Richards","pr@gmail.com","password",2);
        db.tutor.insert(1,3,"@drawable/sample8","Bonnie","Trimper","br@gmail.com","password",2);
        db.tutor.insert(1,3,"@drawable/sample9","Eve","Zinck","ez@gmail.com","password",2);
    }
}
