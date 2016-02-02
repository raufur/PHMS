package com.epsilon.coders.phms.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.epsilon.coders.phms.model.DoctorProfile;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mobile App Develop on 20-6-15.
 */
public class DoctorProfileDataSource {
    private SQLiteDatabase iCareDatabase;
    private SQLiteHelper sqlHelper;
    List<DoctorProfile> doctorProfileList = new ArrayList<DoctorProfile>();

    public DoctorProfileDataSource(Context context) {
        sqlHelper = new SQLiteHelper(context);
    }

    /*
     * open a method for writable database
     */
    public void open() throws SQLException {
        iCareDatabase = sqlHelper.getWritableDatabase();
    }

    public void read() throws SQLException {
        iCareDatabase = sqlHelper.getReadableDatabase();
    }
    /*
     * close database connection
     */
    public void close() {
        sqlHelper.close();
    }

	/*
	 * insert data into the database.
	 */

    public boolean insert(DoctorProfile doctorProfile) {

        this.open();

        ContentValues cvInsert = new ContentValues();

        cvInsert.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_NAME,
                doctorProfile.getDname());

        cvInsert.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_QUALIFICATION, doctorProfile.getQualification());
        cvInsert.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_DESIGNATION,
                doctorProfile.getDesignation());
        cvInsert.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_EXPERTISE,
                doctorProfile.getExpertise());
        cvInsert.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_ORGANIZATION,
                doctorProfile.getOrganization());
        cvInsert.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_CHAMBER,
                doctorProfile.getChamber());

        cvInsert.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_VISITING_HOURS,
                doctorProfile.getVisitinghours());
        cvInsert.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_PHONE,
                doctorProfile.getPhone());

        cvInsert.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_EMAIL,
                doctorProfile.getEmail());


        long check = iCareDatabase.insert(
                SQLiteHelper.I_CARE_DOCTOR_PROFILE, null, cvInsert);
        this.close();
        if (check < 0)
            return false;
        else
            return true;
    }

    // Updating database by id
    public boolean updateData(long dProfileId, DoctorProfile doctorProfile) {
        this.open();
        ContentValues cvUpdate = new ContentValues();

        cvUpdate.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_NAME,
                doctorProfile.getDname());

        cvUpdate.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_QUALIFICATION, doctorProfile.getQualification());
        cvUpdate.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_DESIGNATION,
                doctorProfile.getDesignation());
        cvUpdate.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_EXPERTISE,
                doctorProfile.getExpertise());
        cvUpdate.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_ORGANIZATION,
                doctorProfile.getOrganization());
        cvUpdate.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_CHAMBER,
                doctorProfile.getChamber());

        cvUpdate.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_VISITING_HOURS,
                doctorProfile.getVisitinghours());
        cvUpdate.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_PHONE,
                doctorProfile.getPhone());

        cvUpdate.put(SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_EMAIL,
                doctorProfile.getEmail());

        int check = iCareDatabase.update(SQLiteHelper.I_CARE_DOCTOR_PROFILE,
                cvUpdate, SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_ID + "="
                        + dProfileId, null);

        this.close();
        if (check == 0)
            return false;
        else
            return true;
    }

    // Getting All Doctor
    public List<DoctorProfile> getAllDoctors() {
        List<DoctorProfile> DoctorList = new ArrayList<DoctorProfile>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + SQLiteHelper.I_CARE_DOCTOR_PROFILE;


        iCareDatabase = sqlHelper.getWritableDatabase();
        Cursor cursor = iCareDatabase .rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DoctorProfile doctorProfile = new DoctorProfile();
                doctorProfile.setId((cursor.getInt(0)));
                doctorProfile.setDname(cursor.getString(1));
                doctorProfile.setQualification(cursor.getString(2));
                doctorProfile.setDesignation(cursor.getString(3));
                doctorProfile.setExpertise(cursor.getString(4));
                doctorProfile.setOrganization(cursor.getString(5));
                doctorProfile.setChamber(cursor.getString(6));
                doctorProfile.setVisitinghours(cursor.getString(7));
                doctorProfile.setLocation(cursor.getString(8));
                doctorProfile.setPhone(cursor.getString(9));
                doctorProfile.setEmail(cursor.getString(10));

                // Adding contact to list
                DoctorList.add(doctorProfile);
            } while (cursor.moveToNext());
        }

        // return contact list
        return DoctorList;
    }

    // delete data form database.
    public boolean deleteDoctorData(long doctorId) {
        this.open();
        try {
            iCareDatabase.delete(SQLiteHelper.I_CARE_DOCTOR_PROFILE,
                    SQLiteHelper.COL_ICARE_DOCTOR_PROFILE_ID + "=" + doctorId,
                    null);
        } catch (Exception ex) {
            Log.e("ERROR", "data insertion problem");
            return false;
        }
        this.close();
        return true;
    }

}
