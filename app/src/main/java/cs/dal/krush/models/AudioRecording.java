package cs.dal.krush.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by greg on 27/02/17.
 */

public class AudioRecording extends Table {

    public AudioRecording(SQLiteDatabase dbWrite, SQLiteDatabase dbRead){
        super(dbWrite,dbRead);
    }

    /**
     * Insert row into audio_recordings table
     * @param studentId
     * @param locationId
     * @return boolean
     */
    public boolean insert(int studentId, int locationId){
        ContentValues contentValues = new ContentValues();
        contentValues.put("student_id", studentId);
        contentValues.put("location_id", locationId);
        dbWrite.insert("audio_recording", null, contentValues);
        return true;
    }

    @Override
    public Cursor getData(int id){
        return dbRead.rawQuery("SELECT * FROM audio_recording WHERE id="+id+"",null);
    }

    @Override
    public Cursor getAll() {
        return dbRead.rawQuery("SELECT * FROM audio_recording",null);
    }

    /**
     * Get an audio recording by the location_id field
     * @param locationId
     * @return res
     */
    public Cursor getDataByLocationId(int locationId){
        return dbRead.rawQuery("SELECT * FROM audio_recording WHERE location_id="+locationId+"",null);
    }

    /**
     * Get an audio recording by the student_id field
     * @param studentId
     * @return res
     */
    public Cursor getDataByStudentId(int studentId){
        return dbRead.rawQuery("SELECT * FROM audio_recording WHERE student_id="+studentId+"",null);
    }

    /**
     * Delete an audio recording from an id
     * @param id
     * @return int
     */
    public int deleteAudioRecording(int id){
        return dbWrite.delete("audio_recording","id = ?",new String[] { Integer.toString(id) });
    }
}
