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
        db.tutor.insert(1, 1, null, "Greg", "Miller", "gregpmillr@gmail.com", "password", 2, (float)4);
        db.tutor.insert(3, 2, null, "Michael", "Casey", "mc@dal.ca", "password", 2, (float)4.5);
        db.tutor.insert(2, 1, null, "Orjan", "Monsen", "om@dal.ca", "password", 5, (float)4.1);
        db.tutor.insert(4, 5, null, "Eric", "Desjardins", "ed@dal.ca", "password", 1, (float)3.5);
        db.tutor.insert(5, 3, null, "Connor", "Walsh", "cw@dal.ca", "password", 4, (float)4);
        db.tutor.insert(2, 2, null, "Jack", "Reaper", "jr@dal.ca", "password", 2, (float)4.25);
        db.tutor.insert(5, 4, null, "Peter", "Richards", "pr@gmail.com", "password", 2, (float)3);
        db.tutor.insert(1, 3, null, "Bonnie", "Trimper", "br@gmail.com", "password", 2, (float)3.75);
        db.tutor.insert(1, 3, null, "Eve", "Zinck", "ez@gmail.com", "password", 2, (float)3.5);
    }
}
