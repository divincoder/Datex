package com.datex.datex.model;

import android.content.ContentValues;

import com.datex.datex.model.DatexDBContract.*;

public class Diagnosis implements DatabaseObject {

    private int id;
    private String name;
    private ValidationListener listener;

    public Diagnosis(String name) {
        this.name = name;
    }

    public Diagnosis(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DBContract.getName(DiagnosisTable.NAME), name);
        return cv;
    }

    @Override
    public String getTableName() {
        return DiagnosisTable.TABLE_NAME;
    }

    @Override
    public String getPrimaryKey() {
        return DBContract.getName(DiagnosisTable.ID);
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

    }

    @Override
    public String getField(String field) {
        return null;
    }

    @Override
    public boolean validateNulls() {
        boolean valid = true;
        if (name == null) {
            valid = false;
            if (listener != null) {
                listener.onCatchNull(DB_INPUT.NAME);
            }
        } else if (name.equals("") || name.equals("N")) {
            valid = false;
            if (listener != null) {
                listener.onCatchNull(DB_INPUT.NAME);
            }
        }
        return valid;
    }

    @Override
    public void setValidationListener(ValidationListener listener) {
        this.listener = listener;
    }
}
