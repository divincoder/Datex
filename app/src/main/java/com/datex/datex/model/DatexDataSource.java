package com.datex.datex.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.datex.datex.model.DatexDBContract.*;

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
        return database.insert(object.getTableName(), null, object.getContentValues()) != -1;
    }

    public Patient getPatient(String id) {
        Cursor cursor = database.query(Patients.TABLE_NAME, null, DBContract.getName(Patients.ID) + " = ?", new String[] {id}, null, null, null);
        if (cursor.moveToFirst()) {
            Patient patient = new Patient(cursor.getInt(Patients.ID_INDEX), cursor.getString(Patients.FIRST_NAME_INDEX),
                    cursor.getString(Patients.LAST_NAME_INDEX), cursor.getString(Patients.LAST_NAME_INDEX));
            patient.setAddress(cursor.getString(Patients.ADDRESS_INDEX));
            patient.setDob(cursor.getString(Patients.DOB_INDEX));
            patient.setSex(cursor.getString(Patients.SEX_INDEX));
            patient.setStateOfOrigin(cursor.getInt(Patients.STATE_OF_ORIGIN_INDEX));
            cursor.close();
            return patient;
        }
        cursor.close();
        return null;
    }

}
