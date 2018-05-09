package com.datex.datex.model;

/**
 * Created by Francis Ilechukwu 11/04/2018.
 */

public enum DB_INPUT {

    FIRST_NAME("first_name"),
    LAST_NAME("last_name"),
    SEX("sex"),
    DOB("DOB"),
    NAME("name"),
    PHONE("phone"),
    TRYGLYCERIDES("tryglycerides"),
    HDLC("hdl_c"),
    LDLC("ldl_c"),
    TOTAL_CHOLESTEROL("total_cholesterol"),
    BP("bp"),
    PATIENT_ID("patient_id"),
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
