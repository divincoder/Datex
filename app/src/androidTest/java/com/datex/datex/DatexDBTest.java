package com.datex.datex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.datex.datex.model.DatexDataSource;
import com.datex.datex.model.DatexOpenHelper;
import com.datex.datex.model.Patient;

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
    public void setUp() {
        appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.datex.datex", appContext.getPackageName());
        DatexDataSource dataSource = new DatexDataSource(appContext);
        dataSource.open();
        dataSource.reset();
        dataSource.close();
    }

    @Test
    public void testCreateDB() {
        DatexOpenHelper dbHelper = new DatexOpenHelper(appContext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        assertTrue(database.isOpen());
        database.close();
    }

    @Test
    public void testPatientsTable() {
        DatexDataSource dataSource = new DatexDataSource(appContext);
        dataSource.open();
        Patient patient = new Patient("John", "Ken", "Snow");
        assertFalse(patient.validateNulls());
        patient.setDob("02/05/1880");
        assertFalse(patient.validateNulls());
        patient.setSex("M");
        patient.setAddress("Hola");
        patient.setStateOfOrigin(1);
        assertFalse(patient.validateNulls());
        patient.setPhone("08034993503");
        assertEquals(1, dataSource.createDatabaseObject(patient));
        assertEquals(1, dataSource.getPatientsCount());
        assertEquals(-1, dataSource.createDatabaseObject(patient));
        patient.setPhone("09034993502");
        assertEquals(2, dataSource.createDatabaseObject(patient));
        assertEquals(2, dataSource.getPatientsCount());
        patient = dataSource.getPatient(1);
        assertEquals("John", patient.getFirstName());
        assertEquals("Ken", patient.getMiddleName());
        assertEquals("Snow", patient.getLastName());
        assertEquals("02/05/1880", patient.getDob());
        assertEquals("M", patient.getSex());
        assertEquals(1, patient.getStateOfOrigin());
        assertEquals("08034993503", patient.getPhone());
        patient = dataSource.getPatient(2);
        assertEquals("John", patient.getFirstName());
        assertEquals("Ken", patient.getMiddleName());
        assertEquals("Snow", patient.getLastName());
        assertEquals("02/05/1880", patient.getDob());
        assertEquals("M", patient.getSex());
        assertEquals(1, patient.getStateOfOrigin());
        assertEquals("09034993502", patient.getPhone());
        dataSource.close();
    }

}
