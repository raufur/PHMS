package com.epsilon.coders.phms.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.epsilon.coders.phms.model.CareInfo;
import com.epsilon.coders.phms.model.DoctorProfile;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mobile App Develop on 24-6-15.
 */
public class CareInfoDataSource {

    // Database fields
    private SQLiteDatabase iCareDatabase;
    private SQLiteHelper sqlHelper;
    List<CareInfo> careInfos = new ArrayList<CareInfo>();

    public CareInfoDataSource(Context context) {
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
	 * insert data into careinfo database.
	 */

    public boolean insert(CareInfo careInfo) {

        this.open();
        ContentValues cvInsert = new ContentValues();

        cvInsert.put(SQLiteHelper.COL_CAREINFO_TBL_CARE_NAME,
                careInfo.getCareName());

        cvInsert.put(SQLiteHelper.COL_CAREINFO_TBL_CARE_ADDRESS, careInfo.getCareAddress());
        cvInsert.put(SQLiteHelper.COL_CAREINFO_TBL_CONTACT_NUMBER,
                careInfo.getContactNumber());

        cvInsert.put(SQLiteHelper.COL_CAREINFO_TBL_EMAIL,
                careInfo.getCareEmail());
        long check = iCareDatabase.insert(
                SQLiteHelper.I_CARE_CAREINFO_TBL, null, cvInsert);
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
                doctorProfile. getExpertise());
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

    public List<CareInfo> getAllCareInfo() {
        List<CareInfo> careInfoList = new ArrayList<CareInfo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + SQLiteHelper.I_CARE_CAREINFO_TBL;


        iCareDatabase = sqlHelper.getWritableDatabase();
        Cursor cursor = iCareDatabase .rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CareInfo careInfo = new CareInfo();
                careInfo.setCareName((cursor.getString(1)));
                careInfo.setCareAddress(cursor.getString(2));
                careInfo.setContactNumber(cursor.getString(3));
                careInfo.setCareEmail(cursor.getString(4));


                // Adding contact to list
                careInfoList.add(careInfo);
            } while (cursor.moveToNext());
        }

        // return contact list
        return careInfoList;
    }

}





