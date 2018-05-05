package com.datex.datex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.datex.datex.model.DatexDataSource;
import com.datex.datex.model.DatexOpenHelper;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DatexDBTest {
    private Context appContext;

    @Before
    public void setUp() throws Exception {
        appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.datex.datex", appContext.getPackageName());
        DatexDataSource dataSource = new DatexDataSource(appContext);
        dataSource.open();
        //dataSource.reset();
        dataSource.close();
    }

    @Test
    public void testCreateDB() {
        DatexOpenHelper dbHelper = new DatexOpenHelper(appContext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        assertTrue(database.isOpen());
        database.close();
    }
}
