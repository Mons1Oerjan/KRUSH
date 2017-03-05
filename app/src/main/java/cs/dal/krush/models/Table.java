package cs.dal.krush.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by greg on 27/02/17.
 */

public abstract class Table {

    protected SQLiteDatabase dbWrite;
    protected SQLiteDatabase dbRead;
    protected Cursor res;

    public Table(){}

    public Table(SQLiteDatabase dbWrite, SQLiteDatabase dbRead){
        this.dbWrite = dbWrite;
        this.dbRead = dbRead;
    }

    /**
     * Get field by id
     * @param id
     * @return Cursor
     */
    public abstract Cursor getData(int id);
}
