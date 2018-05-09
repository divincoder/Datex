package com.datex.datex.model;

import android.content.ContentValues;

import com.datex.datex.model.DatexDBContract.*;

import java.util.Calendar;

/**
 * Created by Francis Ilechukwu 11/04/2018.
 */

public class Patient implements DatabaseObject {

    private int id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String dob;
    private String sex;
    private String address;
    private String phone;
    private String dateCreated;
    private int stateOfOrigin;
    private ValidationListener listener;

    public Patient(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Patient(int id, String firstName, String middleName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getDob() {
        return dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStateOfOrigin() {
        return stateOfOrigin;
    }

    public void setStateOfOrigin(int stateOfOrigin) {
        this.stateOfOrigin = stateOfOrigin;
    }

    public int getAge() {
        Calendar today = Calendar.getInstance();
        int day = Integer.valueOf(dob.substring(0, dob.indexOf("/")));
        int month = Integer.valueOf(dob.substring(dob.indexOf("/") + 1, dob.indexOf("/", dob.indexOf("/") + 1)));
        int year = Integer.valueOf(dob.substring(dob.lastIndexOf("/") + 1, dob.length()));
        int age = today.get(Calendar.YEAR) -  year;
        if (month > 1 + today.get(Calendar.MONTH)) {
            --age;
        } else if (month == 1 + today.get(Calendar.MONTH)) {
            if (day > today.get(Calendar.DAY_OF_MONTH)) {
                --age;
            }
        }
        return age;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DBContract.getName(Patients.FIRST_NAME), firstName);
        cv.put(DBContract.getName(Patients.MIDDLE_NAME), middleName);
        cv.put(DBContract.getName(Patients.LAST_NAME), lastName);
        cv.put(DBContract.getName(Patients.DOB), dob);
        cv.put(DBContract.getName(Patients.SEX), sex);
        cv.put(DBContract.getName(Patients.ADDRESS), address);
        cv.put(DBContract.getName(Patients.STATE_OF_ORIGIN), stateOfOrigin);
        cv.put(DBContract.getName(Patients.PHONE_NO), phone);
        cv.put(DBContract.getName(Patients.DATE_CREATED), dateCreated);
        return cv;
    }

    @Override
    public String getTableName() {
        return Patients.TABLE_NAME;
    }

    @Override
    public String getPrimaryKey() {
        return DBContract.getName(Patients.ID);
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
        if (field.equals(DBContract.getName(Patients.DATE_CREATED))) {
            dateCreated = value;
        }
    }

    @Override
    public String getField(String field) {
        return null;
    }

    @Override
    public boolean validateNulls() {
        String[] notNulls = {firstName, lastName, dob, sex, phone};
        DB_INPUT[] pointers = {DB_INPUT.FIRST_NAME, DB_INPUT.LAST_NAME, DB_INPUT.DOB, DB_INPUT.SEX, DB_INPUT.PHONE};
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
        if (stateOfOrigin == 0) {
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
