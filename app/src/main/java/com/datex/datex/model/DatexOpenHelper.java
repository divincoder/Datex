package com.datex.datex.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.datex.datex.model.DatexDBContract.*;

/**
 * Created by Francis Ilechukwu 09/04/2018.
 */

public class DatexOpenHelper extends SQLiteOpenHelper {

    private static final int database_version = 7;

    public DatexOpenHelper(Context context) {
        super(context, "datex.db", null, database_version);
    }

    /**
     * called the very first time a database read or write operation is carried out for the app.
     *
     * @param sqLiteDatabase an object handle to sqlite3 command line tool.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Patients Table.
        sqLiteDatabase.execSQL(DBContract.createTable(Patients.TABLE_NAME, Patients.CONFIG, ""));
        // Diagnosis Table.
        sqLiteDatabase.execSQL(DBContract.createTable(DiagnosisTable.TABLE_NAME, DiagnosisTable.CONFIG, ""));
        // Glycemic Data Table.
        sqLiteDatabase.execSQL(DBContract.createTable(GlycemicDataTable.TABLE_NAME, GlycemicDataTable.CONFIG, ""));
        // Coronary Risk Factor Table.
        sqLiteDatabase.execSQL(DBContract.createTable(CoronaryRiskFactorTable.TABLE_NAME, CoronaryRiskFactorTable.CONFIG, ""));

        /*
        Insertion Region.
         */
        ContentValues diagnosis = new ContentValues();
        diagnosis.put(DBContract.getName(DiagnosisTable.NAME), "Type 1");
        sqLiteDatabase.insert(DiagnosisTable.TABLE_NAME, "", diagnosis);
        diagnosis.put(DBContract.getName(DiagnosisTable.NAME), "Type 2");
        sqLiteDatabase.insert(DiagnosisTable.TABLE_NAME, "", diagnosis);
        diagnosis.put(DBContract.getName(DiagnosisTable.NAME), "Gestational Type");
        sqLiteDatabase.insert(DiagnosisTable.TABLE_NAME, "", diagnosis);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Patients.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DiagnosisTable.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GlycemicDataTable.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CoronaryRiskFactorTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
