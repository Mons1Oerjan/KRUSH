package cs.dal.krush.seeders;

import cs.dal.krush.models.DBHelper;

/**
 * Created by greg on 07/03/17.
 */

public class TutoringSessionSeeder {

    /**
     * Function that inserts dummy tutoring sessions into the DB
     * @param db
     */
    public static void insert(DBHelper db){
        db.tutoringSession.insert(1,1,1,"Meeting at Starbucks on Robie St");
        db.tutoringSession.insert(2,1,1,"Meeting at Dalhousie CS building");
        db.tutoringSession.insert(1,1,2,"Meeting at Sparkzone");
        db.tutoringSession.insert(3,1,1,"Meeting at Coburg Coffee");
        db.tutoringSession.insert(2,6,6,"Meeting at Acadia library");
        db.tutoringSession.insert(2,3,3,"Meeting at Truro Big Stop");
        db.tutoringSession.insert(4,2,1,"Meeting at Dal Math Learning Centre");
        db.tutoringSession.insert(1,2,1,"Meeting at Dal Killam Library");
        db.tutoringSession.insert(1,3,1,"Meeting at Public Library 4th floor");
        db.tutoringSession.insert(1,5,1,"Meeting at Dal CS Learning Centre");
        db.tutoringSession.insert(1,6,1,"Meeting at Dal Student Union Building");
    }
}
