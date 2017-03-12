package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Seeder for the locations database table
 */

public class TutorSeeder {

    public static void insert(DBHelper db){
        db.tutor.insert(1,1,"Greg","Miller","gregpmillr@gmail.com","password",2);
        db.tutor.insert(3,2,"Michael","Casey","mc@dal.ca","password",2);
        db.tutor.insert(2,1,"Orjan","Monsen","om@dal.ca","password",5);
        db.tutor.insert(4,5,"Eric","Desjardins","ed@dal.ca","password",1);
        db.tutor.insert(5,3,"Connor","Walsh","cw@dal.ca","password",4);
        db.tutor.insert(2,2,"Jack","Reaper","jr@dal.ca","password",2);
        db.tutor.insert(5,4,"Peter","Richards","pr@gmail.com","password",2);
        db.tutor.insert(1,3,"Bonnie","Trimper","br@gmail.com","password",2);
        db.tutor.insert(1,3,"Eve","Zinck","ez@gmail.com","password",2);
    }
}
