package com.datex.datex.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Created by Francis Ilechukwu 11/04/2018.
 */

public class DatexDataSource {

    private SQLiteDatabase database; // SQLite Database Object.
    private DatexOpenHelper dbHelper;

    /**
     * DataSource Constructor
     *
     * @param context the context of the current running activity.
     */
    public DatexDataSource(Context context) {
        dbHelper = new DatexOpenHelper(context);
    }

    /**
     * the function that opens the database.
     *
     * @throws SQLiteException on failure to get writable database.
     */
    public void open() throws SQLiteException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * closes the database object.
     */
    public void close() {
        dbHelper.close();
    }

    public boolean createDatabaseObject(DatabaseObject object) {
        boolean success;
        try {
            success = database.insertOrThrow(object.getTableName(), null, object.getContentValues()) != -1;
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        return success;
    }

}
