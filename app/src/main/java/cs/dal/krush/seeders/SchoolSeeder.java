package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Seeder for the schools database table
 */

public class SchoolSeeder {

    public static void insert(DBHelper db){
        db.school.insert("CPA",1,"Highschool");
        db.school.insert("Halifax West",1,"Highschool");
        db.school.insert("Bridgewater",4,"Highschool");
        db.school.insert("Dalhousie",1,"University");
        db.school.insert("SMU",1,"University");
        db.school.insert("NSCC Institute of Technology",1,"Community College");
        db.school.insert("NSCC Waterfront Campus",5,"Community College");
        db.school.insert("Acadia",6,"University");
    }
}
