package cs.dal.krush.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by greg on 05/03/17.
 */

public class Location extends Table{

    public Location(SQLiteDatabase dbWrite, SQLiteDatabase dbRead){
        super(dbWrite,dbRead);
    }

    @Override
    public boolean insert(Object[] args) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("location", (String) args[0]);
        dbWrite.insert("locations", null, contentValues);
        return true;
    }

    @Override
    public Cursor getData(int id) {
        res = dbRead.rawQuery("SELECT * FROM locations WHERE id="+id+"",null);
        return res;
    }

    public Cursor getDataByLocation(String location){
        res = dbRead.rawQuery("SELECT * FROM locations WHERE location="+location+"",null);
        return res;
    }

    public Cursor getLocationBySchool(int school_id){
        //get the school's location_id,
        res = dbRead.rawQuery("SELECT * FROM schools WHERE id="+school_id+"",null);
        res.moveToFirst();
        int location_id;
        location_id = res.getInt(res.getColumnIndex("location_id"));
        System.out.println("locationID:"+location_id);
        String s = res.getString(res.getColumnIndex("name"));
        System.out.println("name:"+s);

        //get the location from the location_id
        res = dbRead.rawQuery("SELECT * FROM locations WHERE id="+location_id+"",null);

        return res;
    }
}
