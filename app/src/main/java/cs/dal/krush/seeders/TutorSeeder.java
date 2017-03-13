package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Seeder for the locations database table
 */

public class TutorSeeder {

    /**
     * Function that inserts dummy tutor accounts into the DB.
     * Insert format: locationId, schoolId, profilePic, firstName, lastName, email, password, rate.
     * @param db
     */
    public static void insert(DBHelper db){
        db.tutor.insert(1,1,"sample1","Greg","Miller","gregpmillr@gmail.com","password",2);
        db.tutor.insert(3,2,"sample2","Michael","Casey","mc@dal.ca","password",2);
        db.tutor.insert(2,1,"sample3","Orjan","Monsen","om@dal.ca","password",5);
        db.tutor.insert(4,5,"sample4","Eric","Desjardins","ed@dal.ca","password",1);
        db.tutor.insert(5,3,"sample5","Connor","Walsh","cw@dal.ca","password",4);
        db.tutor.insert(2,2,"sample6","Jack","Reaper","jr@dal.ca","password",2);
        db.tutor.insert(5,4,"sample7","Peter","Richards","pr@gmail.com","password",2);
        db.tutor.insert(1,3,"sample8","Bonnie","Trimper","br@gmail.com","password",2);
        db.tutor.insert(1,3,"sample9","Eve","Zinck","ez@gmail.com","password",2);
    }
}
