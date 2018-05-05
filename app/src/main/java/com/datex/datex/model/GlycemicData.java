package com.datex.datex.model;

import android.content.ContentValues;

import com.datex.datex.model.DatexDBContract.*;

public class GlycemicData implements DatabaseObject {

    private int id;
    private int patientId;
    private String bmi;
    private String rbg;
    private String hba1C;
    private String medication;
    private String lastUpdateTime;
    private ValidationListener listener;

    /**
     * constructor used to create a new glycemic data object with an id and an associated patientId.
     * a constructor typically used inside the DatexDataSourceClass.
     *
     * @param id id of the glycemic data in the database.
     * @param patientId the id f the associated patient in the database.
     */
    public GlycemicData(int id, int patientId) {
        this.id = id;
        this.patientId = patientId;
    }

    public GlycemicData(int patientId) {
        this.patientId = patientId;
    }

    public String getBmi() {
        return bmi;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getRbg() {
        return rbg;
    }

    public void setRbg(String rbg) {
        this.rbg = rbg;
    }

    public String getHba1C() {
        return hba1C;
    }

    public void setHba1C(String hba1C) {
        this.hba1C = hba1C;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DBContract.getName(GlycemicDataTable.PATIENT_ID), patientId);
        cv.put(DBContract.getName(GlycemicDataTable.BMI), bmi);
        cv.put(DBContract.getName(GlycemicDataTable.RBG), rbg);
        cv.put(DBContract.getName(GlycemicDataTable.HBA1C), hba1C);
        cv.put(DBContract.getName(GlycemicDataTable.MEDICATION), medication);
        cv.put(DBContract.getName(GlycemicDataTable.LAST_UPDATE_TIME), lastUpdateTime);
        return cv;
    }

    @Override
    public String getTableName() {
        return GlycemicDataTable.TABLE_NAME;
    }

    @Override
    public String getPrimaryKey() {
        return DBContract.getName(GlycemicDataTable.ID);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setField(String field, int value) {

    }

    @Override
    public String getField(String field) {
        return null;
    }

    @Override
    public boolean validateNulls() {
        String[] notNulls = {bmi, rbg, hba1C};
        DB_INPUT[] pointers = {DB_INPUT.BMI, DB_INPUT.RBG, DB_INPUT.HBA1C};
        boolean valid = true;
        for (int x = 0; x < notNulls.length; x++) {
            if (notNulls[x] == null) {
                valid = false;
                if (listener != null) {
                    listener.onCatchNull(pointers[x]);
                }
            } else if (notNulls[x].equals("")) {
                valid = false;
                if (listener != null) {
                    listener.onCatchNull(pointers[x]);
                }
            }
        }
        if (patientId == 0) {
            valid = false;
            if (listener != null) {
                listener.onCatchNull(DB_INPUT.STATE_OF_ORIGIN);
            }
        }
        return valid;
    }

    @Override
    public void setValidationListener(ValidationListener listener) {
        this.listener = listener;
    }
}
