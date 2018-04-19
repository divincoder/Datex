package com.datex.datex.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.datex.datex.model.DatexDBContract.*;

/**
 * Created by Francis Ilechukwu 09/04/2018.
 */

public class DatexOpenHelper extends SQLiteOpenHelper {

    private static final int database_version = 3;

    DatexOpenHelper(Context context) {
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
        sqLiteDatabase.execSQL(DBContract.createTable(GlycemicData.TABLE_NAME, GlycemicData.CONFIG, ""));
        sqLiteDatabase.execSQL(DBContract.createTable(CoronaryRiskFactor.TABLE_NAME, CoronaryRiskFactor.CONFIG, ""));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Patients.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GlycemicData.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CoronaryRiskFactor.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
