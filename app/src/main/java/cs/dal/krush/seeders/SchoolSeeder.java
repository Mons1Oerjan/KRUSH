package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Seeder for the schools database table
 */
public class SchoolSeeder {

    /**
     * Function that inserts dummy schools into the DB
     * insert format: school_name, locationID, school_type
     * @param db
     */
    public static void insert(DBHelper db){
        db.school.insert("CPA",1,"Highschool");
        db.school.insert("Halifax West",1,"Highschool");
        db.school.insert("Bridgewater",4,"Highschool");
        db.school.insert("Dalhousie",1,"University");
        db.school.insert("SMU",1,"University");
        db.school.insert("NSCC Institute of Technology",1,"Community College");
        db.school.insert("NSCC Waterfront Campus",5,"Community College");
        db.school.insert("Acadia",6,"University");
        db.school.insert("NSCAD",1,"University");
    }
}
