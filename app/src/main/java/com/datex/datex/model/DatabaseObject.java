package com.datex.datex.model;

import android.content.ContentValues;

/**
 * Created by Francis Ilechukwu 11/04/2018.
 */

interface DatabaseObject {
    ContentValues getContentValues();

    String getTableName();

    String getPrimaryKey();

    int getId();

    void setField(String field, int value);

    void setField(String field, String value);

    String getField(String field);

    boolean validateNulls();

    void setValidationListener(ValidationListener listener);
}
