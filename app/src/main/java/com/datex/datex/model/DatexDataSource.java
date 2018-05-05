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

    public Patient getPatient(int id) {
        Cursor cursor = database.query(Patients.TABLE_NAME, null, DBContract.getName(Patients.ID) + " = ?", new String[] {String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            Patient patient = new Patient(cursor.getInt(Patients.ID_INDEX), cursor.getString(Patients.FIRST_NAME_INDEX),
                    cursor.getString(Patients.MIDDLE_NAME_INDEX), cursor.getString(Patients.LAST_NAME_INDEX));
            patient.setAddress(cursor.getString(Patients.ADDRESS_INDEX));
            patient.setDob(cursor.getString(Patients.DOB_INDEX));
            patient.setSex(cursor.getString(Patients.SEX_INDEX));
            patient.setStateOfOrigin(cursor.getInt(Patients.STATE_OF_ORIGIN_INDEX));
            patient.setPhone(cursor.getString(Patients.PHONE_NO_INDEX));
            cursor.close();
            return patient;
        }
        cursor.close();
        return null;
    }

    /**
     *
     * gets the ID of the patient this glycemic data is associated with.
     *
     * @param patientId The id of the patient this glycemic data belongs to.
     * @return A GlycemicData object belonging to the given patient id.
     */
    public GlycemicData getGlycemicData(int patientId) {
        Cursor cursor = database.query(GlycemicDataTable.TABLE_NAME, null, DBContract.getName(GlycemicDataTable.PATIENT_ID) + " = ?", new String[] {String.valueOf(patientId)}, null, null, null);
        if (cursor.moveToFirst()) {
            GlycemicData glycemicData = new GlycemicData(cursor.getInt(GlycemicDataTable.ID_INDEX), cursor.getInt(GlycemicDataTable.PATIENT_ID_INDEX));
            glycemicData.setBmi(cursor.getString(GlycemicDataTable.BMI_INDEX));
            glycemicData.setRbg(cursor.getString(GlycemicDataTable.RBG_INDEX));
            glycemicData.setHba1C(cursor.getString(GlycemicDataTable.HBA1C_INDEX));
            glycemicData.setMedication(cursor.getString(GlycemicDataTable.MEDICATION_INDEX));
            glycemicData.setLastUpdateTime(cursor.getString(GlycemicDataTable.LAST_UPDATE_TIME_INDEX));
            glycemicData.setDiagnosisId(cursor.getInt(GlycemicDataTable.DIAGNOSIS_ID_INDEX));
            cursor.close();
            return glycemicData;
        }
        cursor.close();
        return null;
    }

}
