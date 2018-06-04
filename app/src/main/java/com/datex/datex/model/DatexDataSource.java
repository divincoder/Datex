package com.datex.datex.model;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.Nullable;

import com.datex.datex.model.DatexDBContract.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
     *`
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

    /**
     *
     * creates a database object. how and where the data is created is handled by the object passed.
     *
     * @param object an object that implements the DataBaseObject interface.
     * @return id of newly created record in the database.
     */
    public long createDatabaseObject(DatabaseObject object) {
        if (!object.getTableName().equals(DiagnosisTable.TABLE_NAME)) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = new Date();
            if (object.getTableName().equals(Patients.TABLE_NAME)) {
                object.setField(DBContract.getName(Patients.DATE_CREATED), dateFormat.format(date));
            } else if (object.getTableName().equals(GlycemicDataTable.TABLE_NAME)) {
                object.setField(DBContract.getName(GlycemicDataTable.LAST_UPDATE_TIME), dateFormat.format(date));
            } else if (object.getTableName().equals(CoronaryRiskFactorTable.TABLE_NAME)) {
                object.setField(DBContract.getName(CoronaryRiskFactorTable.LAST_UPDATE_TIME), dateFormat.format(date));
            }
        }
        return database.insert(object.getTableName(), null, object.getContentValues());
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
            patient.setDateCreated(cursor.getString(Patients.DATE_CREATED_INDEX));
            cursor.close();
            return patient;
        }
        cursor.close();
        return null;
    }

    /**
     *
     * gets the number of patient records in the database.
     *
     * @return the number of patient records in the database.
     */
    public long getPatientsCount() {
        return DatabaseUtils.queryNumEntries(database, Patients.TABLE_NAME);
    }

    /**
     *
     * gets the ID of the patient this glycemic data is associated with.
     *
     * @param patientId The id of the patient this glycemic data belongs to.
     * @return A GlycemicData object belonging to the given patient id.
     */
    @Nullable
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

    @Nullable
    public CoronaryRiskFactor getCoronaryRiskFactor(int patientId) {
        Cursor cursor = database.query(CoronaryRiskFactorTable.TABLE_NAME, null, DBContract.getName(CoronaryRiskFactorTable.PATIENT_ID) + " = ?", new String[] {String.valueOf(patientId)}, null, null, null);
        if (cursor.moveToFirst()) {
            CoronaryRiskFactor coronaryRiskFactor = new CoronaryRiskFactor(cursor.getInt(CoronaryRiskFactorTable.ID_INDEX), cursor.getInt(CoronaryRiskFactorTable.PATIENT_ID_INDEX));
            coronaryRiskFactor.setBp(cursor.getString(CoronaryRiskFactorTable.BP_INDEX));
            coronaryRiskFactor.setHdlC(cursor.getString(CoronaryRiskFactorTable.HDL_C_INDEX));
            coronaryRiskFactor.setLastUpdateTime(cursor.getString(CoronaryRiskFactorTable.LAST_UPDATE_TIME_INDEX));
            coronaryRiskFactor.setLdlC(cursor.getString(CoronaryRiskFactorTable.LDL_C_INDEX));
            coronaryRiskFactor.setTotalCholesterol(cursor.getInt(CoronaryRiskFactorTable.TOTAL_CHOLESTEROL_INDEX));
            coronaryRiskFactor.setTryglycerides(cursor.getString(CoronaryRiskFactorTable.TRYGLYCERIDES_INDEX));
            cursor.close();
            return coronaryRiskFactor;
        }
        cursor.close();
        return null;
    }

    public String getDiagnosisName(int id) {
        Cursor cursor = database.query(DiagnosisTable.TABLE_NAME, null, DBContract.getName(DiagnosisTable.ID) + " = ?", new String[] {String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            String diagnosis = cursor.getString(DiagnosisTable.NAME_INDEX);
            cursor.close();
            return diagnosis;
        }
        cursor.close();
        return "";
    }

    public ArrayList<Diagnosis> getAllDiagnosis() {
        Cursor cursor = database.query(DiagnosisTable.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Diagnosis> allDiagnosis = new ArrayList<>();
        if (cursor.moveToFirst()) {
            allDiagnosis.add(new Diagnosis(cursor.getInt(DiagnosisTable.ID_INDEX), cursor.getString(DiagnosisTable.NAME_INDEX)));
        }
        cursor.close();
        return allDiagnosis;
    }

    /**
     *
     * PLEASE DO NOT IN ANY WAY EVER CALL THIS FUNCTION!!!!!!!!!!
     *
     * THIS IS FOR UNIT TESTING.
     *
     * AND DO NOT EVER RUN THE UNIT TESTS ON OUR PRODUCTION DEVICE!!!!!!!!!!!
     *
     * I CAN'T STRESS THIS ENOUGH
     *
     * I KNOW U UNDERSTAND :-)
     *
     * This function erases all data in all tables.
     *
     */
    public void reset() {
        database.execSQL("DELETE FROM " + Patients.TABLE_NAME + ";");
        database.execSQL("DELETE FROM " + GlycemicDataTable.TABLE_NAME + ";");
    }

}
