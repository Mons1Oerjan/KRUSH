package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Created by greg on 07/03/17.
 */

public class TutoringSessionSeeder {

    public static void insert(DBHelper db){
        db.tutoringSession.insert(1,1,2,"test tutoring session");
        db.tutoringSession.insert(2,1,1,"Meeting at Dalhousie CS building");
        db.tutoringSession.insert(1,1,2,"Meeting at Sparkzone");
        db.tutoringSession.insert(3,1,1,"Meeting at Coburg Cafe");
        db.tutoringSession.insert(2,6,1,"Meeting at Acadia library");
        db.tutoringSession.insert(2,3,1,"Meeting at Truro Big Stop");
        db.tutoringSession.insert(4,2,1,"Meeting at Stewiacke mini-putt place");
    }
}
