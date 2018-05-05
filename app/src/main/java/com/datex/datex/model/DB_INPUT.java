package com.datex.datex.model;

/**
 * Created by Francis Ilechukwu 11/04/2018.
 */

public enum DB_INPUT {

    FIRST_NAME("first_name"),
    LAST_NAME("last_name"),
    SEX("sex"),
    DOB("DOB"),
    PHONE("phone"),
    STATE_OF_ORIGIN("state"),
    BMI("bmi"),
    RBG("rbg"),
    HBA1C("hba1c");

    String value;

    DB_INPUT(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
