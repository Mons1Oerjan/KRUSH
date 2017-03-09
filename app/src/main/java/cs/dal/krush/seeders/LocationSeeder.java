package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Seeder for the locations database table
 */

public class LocationSeeder {

    public static void insert(DBHelper db){
        db.location.insert("Halifax");
        db.location.insert("Stewiacke");
        db.location.insert("Truro");
        db.location.insert("Bridgewater");
        db.location.insert("Dartmouth");
        db.location.insert("Wolfville");
        db.location.insert("New Minas");
    }
}
