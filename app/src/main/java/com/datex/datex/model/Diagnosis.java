package com.datex.datex.model;

import android.content.ContentValues;

import com.datex.datex.model.DatexDBContract.*;

public class Diagnosis {

    private int id;
    private String name;
    private ValidationListener listener;

    Diagnosis(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
