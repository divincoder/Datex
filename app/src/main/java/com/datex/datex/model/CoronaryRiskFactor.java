package com.datex.datex.model;

import android.content.ContentValues;

import com.datex.datex.model.DatexDBContract.*;

public class CoronaryRiskFactor implements DatabaseObject {

    private int id;
    private int patientId;
    private int totalCholesterol;
    private String tryglycerides;
    private String hdlC;
    private String ldlC;
    private String bp;
    private String lastUpdateTime;
    private ValidationListener listener;

    public CoronaryRiskFactor(int id, int patientId) {
        this.id = id;
        this.patientId = patientId;
    }

    public CoronaryRiskFactor(int patientId) {
        this.patientId = patientId;
    }

    public int getTotalCholesterol() {
        return totalCholesterol;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setTotalCholesterol(int totalCholesterol) {
        this.totalCholesterol = totalCholesterol;
    }

    public String getTryglycerides() {
        return tryglycerides;
    }

    public void setTryglycerides(String tryglycerides) {
        this.tryglycerides = tryglycerides;
    }

    public String getHdlC() {
        return hdlC;
    }

    public void setHdlC(String hdlC) {
        this.hdlC = hdlC;
    }

    public String getLdlC() {
        return ldlC;
    }

    public void setLdlC(String ldlC) {
        this.ldlC = ldlC;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
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
        cv.put(DBContract.getName(CoronaryRiskFactorTable.PATIENT_ID), patientId);
        cv.put(DBContract.getName(CoronaryRiskFactorTable.TOTAL_CHOLESTEROL), totalCholesterol);
        cv.put(DBContract.getName(CoronaryRiskFactorTable.TRYGLYCERIDES), tryglycerides);
        cv.put(DBContract.getName(CoronaryRiskFactorTable.HDL_C), hdlC);
        cv.put(DBContract.getName(CoronaryRiskFactorTable.LDL_C), ldlC);
        cv.put(DBContract.getName(CoronaryRiskFactorTable.BP), bp);
        cv.put(DBContract.getName(CoronaryRiskFactorTable.LAST_UPDATE_TIME), lastUpdateTime);
        return cv;
    }

    @Override
    public String getTableName() {
        return CoronaryRiskFactorTable.TABLE_NAME;
    }

    @Override
    public String getPrimaryKey() {
        return DBContract.getName(CoronaryRiskFactorTable.ID);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setField(String field, int value) {

    }

    @Override
    public void setField(String field, String value) {
        if (field.equals(DBContract.getName(CoronaryRiskFactorTable.LAST_UPDATE_TIME))) {
            lastUpdateTime = value;
        }
    }

    @Override
    public String getField(String field) {
        return null;
    }

    @Override
    public boolean validateNulls() {
        String[] notNulls = {tryglycerides, hdlC, ldlC, bp};
        int[] intNotNulls = {patientId, totalCholesterol};
        DB_INPUT[] stringFlags = {DB_INPUT.TRYGLYCERIDES, DB_INPUT.HDLC, DB_INPUT.LDLC, DB_INPUT.BP};
        DB_INPUT[] intFlags = {DB_INPUT.PATIENT_ID, DB_INPUT.TOTAL_CHOLESTEROL};
        boolean valid = true;
        for (int x = 0; x < notNulls.length; x++) {
            if (notNulls[x] == null) {
                valid = false;
                if (listener != null) {
                    listener.onCatchNull(stringFlags[x]);
                }
            } else if (notNulls[x].equals("") || notNulls[x].equals("N")) {
                valid = false;
                if (listener != null) {
                    listener.onCatchNull(stringFlags[x]);
                }
            }
        }
        for (int x = 0; x < intNotNulls.length; x++) {
            if (intNotNulls[x] == 0) {
                valid = false;
                if (listener != null) {
                    listener.onCatchNull(intFlags[x]);
                }
            }
        }
        return valid;
    }

    @Override
    public void setValidationListener(ValidationListener listener) {
        this.listener = listener;
    }
}
