package cs.dal.krush.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by greg on 27/02/17.
 */

public abstract class Table {

    protected SQLiteDatabase dbWrite;
    protected SQLiteDatabase dbRead;

    public Table(){}

    public Table(SQLiteDatabase dbWrite, SQLiteDatabase dbRead){
        this.dbWrite = dbWrite;
        this.dbRead = dbRead;
    }

    public abstract boolean insert(Object[] args);
    public abstract Cursor getData(int id);
}
