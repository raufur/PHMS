package com.epsilon.coders.phms.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.epsilon.coders.phms.model.FamilyProfileClass;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mobile App Develop on 29-6-15.
 */
public class FamilyProfileDataSource {

    private SQLiteDatabase iCareDatabase;
    private SQLiteHelper iCareDbHelper;
    List<FamilyProfileClass> profilesList = new ArrayList<FamilyProfileClass>();

    public FamilyProfileDataSource(Context context) {
        iCareDbHelper = new SQLiteHelper(context);
    }

    /*
     * open a method for writable database
     */
    public void open() throws SQLException {
        iCareDatabase = iCareDbHelper.getWritableDatabase();
    }

    /*
     * close database connection
     */
    public void close() {
        iCareDbHelper.close();
    }

	/*
	 * insert data into the database.
	 */

    public boolean insert(FamilyProfileClass familyProfile) {

        this.open();

        ContentValues cvInsert = new ContentValues();

        cvInsert.put(SQLiteHelper.COL_ICARE_FAMILY_PROFILE_NAME, familyProfile.getFname());
        cvInsert.put(SQLiteHelper.COL_ICARE_FAMILY_PROFILE_NUMBER, familyProfile.getFNumber());
        cvInsert.put(SQLiteHelper.COL_ICARE_FAMILY_PROFILE_RELATIONSHIP, familyProfile.getFrelationship());
        cvInsert.put(SQLiteHelper.COL_ICARE_FAMILY_PROFILE_AGE, familyProfile.getFage());
        cvInsert.put(SQLiteHelper.COL_ICARE_FAMILY_PROFILE_BLOOD_GROUP, familyProfile.getFbloodGroup());
        cvInsert.put(SQLiteHelper.COL_ICARE_FAMILY_PROFILE_WEIGHT, familyProfile.getFweight());
        cvInsert.put(SQLiteHelper.COL_ICARE_FAMILY_PROFILE_HEIGHT,familyProfile.getFheight());
        cvInsert.put(SQLiteHelper.COL_ICARE_FAMILY_PROFILE_DATE_OF_BIRTH, familyProfile.getFdateOfBirth());

        cvInsert.put(SQLiteHelper.COL_ICARE_FAMILY_PROFILE_SPECIAL_NOTES, familyProfile.getFspecialNotes());

        long check = iCareDatabase.insert(SQLiteHelper.TBL_I_CARE_FAMILY_PROFILE,  null, cvInsert);

        this.close();
        if (check < 0)
            return false;
        else
            return true;
    }

  /*  // Updating database by id
    public boolean updateData(long eProfileId, Profile eProfile) {
        this.open();
        ContentValues cvUpdate = new ContentValues();

        cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_NAME,
                eProfile.getName());
        cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_AGE, eProfile.getAge());
        cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP,
                eProfile.getBlooGroup());
        cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_WEIGHT,
                eProfile.getWeight());
        cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_HEIGHT,
                eProfile.getHeight());
        cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_DATE_OF_BIRTH,
                eProfile.getDateOfBirth());
        cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_SPECIAL_NOTES,
                eProfile.getSpecialNotes());

        int check = iCareDatabase.update(SQLiteHelper.I_CARE_PROFILE,
                cvUpdate, SQLiteHelper.COL_ICARE_PROFILE_ID + "="
                        + eProfileId, null);

        this.close();
        if (check == 0)
            return false;
        else
            return true;
    }*/

    // delete data form database.
    public boolean deleteFamilyData(long eFamilyProfileId) {
        this.open();
        try {
            iCareDatabase.delete(SQLiteHelper.TBL_I_CARE_FAMILY_PROFILE,
                    SQLiteHelper.COL_ICARE_FAMILY_PROFILE_ID + "=" + eFamilyProfileId,
                    null);
        } catch (Exception ex) {
            Log.e("ERROR", "data insertion problem");
            return false;
        }
        this.close();
        return true;
    }
    // Getting All family
    public List<FamilyProfileClass> getAllFamilyProfile() {
        List<FamilyProfileClass> familyProfileClasses = new ArrayList<FamilyProfileClass>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + SQLiteHelper.TBL_I_CARE_FAMILY_PROFILE;


        iCareDatabase = iCareDbHelper.getWritableDatabase();
        Cursor cursor = iCareDatabase .rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FamilyProfileClass familyProfileClass = new FamilyProfileClass();
                familyProfileClass.setFid(Long.parseLong((cursor.getString(0))));
                familyProfileClass.setFname(cursor.getString(1));
                familyProfileClass.setFNumber(cursor.getString(2));
                familyProfileClass.setFrelationship(cursor.getString(3));
                familyProfileClass.setFage(cursor.getString(4));
                familyProfileClass.setFbloodGroup(cursor.getString(5));
                familyProfileClass.setFheight(cursor.getString(6));
                familyProfileClass.setFweight(cursor.getString(7));
                familyProfileClass.setFdateOfBirth(cursor.getString(8));
                familyProfileClass.setFspecialNotes(cursor.getString(9));


                // Adding contact to list
                familyProfileClasses.add(familyProfileClass);
            } while (cursor.moveToNext());
        }

        // return contact list
        return familyProfileClasses;
    }

/*
    *//*
     * using cursor for display data from database.
     *//**//*
    public List<Profile> iCareProfilesList() {
        this.open();

        Cursor mCursor = iCareDatabase.query(SQLiteHelper.I_CARE_PROFILE,
                new String[] { SQLiteHelper.COL_ICARE_PROFILE_ID,
                        SQLiteHelper.COL_ICARE_PROFILE_NAME,
                        SQLiteHelper.COL_ICARE_PROFILE_AGE,
                        SQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP,
                        SQLiteHelper.COL_ICARE_PROFILE_WEIGHT,
                        SQLiteHelper.COL_ICARE_PROFILE_HEIGHT,
                        SQLiteHelper.COL_ICARE_PROFILE_DATE_OF_BIRTH,
                        SQLiteHelper.COL_ICARE_PROFILE_SPECIAL_NOTES },
                null, null, null, null, null);

        if (mCursor != null) {
            if (mCursor.moveToFirst()) {

                do {

                    String mId = mCursor
                            .getString(mCursor
                                    .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_ID));
                    String mName = mCursor
                            .getString(mCursor
                                    .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_NAME));
                    String mAge = mCursor
                            .getString(mCursor
                                    .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_AGE));
                    String mBloodGroup = mCursor
                            .getString(mCursor
                                    .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP));
                    String mWeight = mCursor
                            .getString(mCursor
                                    .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_WEIGHT));
                    String mHeight = mCursor
                            .getString(mCursor
                                    .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_HEIGHT));
                    String mDateOfBirth = mCursor
                            .getString(mCursor
                                    .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_DATE_OF_BIRTH));
                    String mSpecialNotes = mCursor
                            .getString(mCursor
                                    .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_SPECIAL_NOTES));
                    // long mmId = Long.parseLong(mId);
                    profilesList.add(new Profile(mId, mName, mAge,
                            mBloodGroup, mWeight, mHeight, mDateOfBirth,
                            mSpecialNotes));
                } while (mCursor.moveToNext());
            }
            mCursor.close();
        }
        this.close();
        return profilesList;
    }

    *//**//*
     * create a profile of ICareProfile. Here the data of the database according
     * to the given id is set to the profile and return a profile class object.
     *//**//*
    public Profile singleProfileData() {
        this.open();
        Profile iCareUpdateProfile;
        String mId;
        String mName;
        int profileID = 1;
        String mAge;
        String mBloodGroup;
        String mWeight;
        String mHeight;
        String mDateOfBirth;
        String mSpecialNotes;

        Cursor mUpdateCursor = iCareDatabase.query(
                SQLiteHelper.I_CARE_PROFILE, new String[] {
                        SQLiteHelper.COL_ICARE_PROFILE_ID,
                        SQLiteHelper.COL_ICARE_PROFILE_NAME,
                        SQLiteHelper.COL_ICARE_PROFILE_AGE,
                        SQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP,
                        SQLiteHelper.COL_ICARE_PROFILE_WEIGHT,
                        SQLiteHelper.COL_ICARE_PROFILE_HEIGHT,
                        SQLiteHelper.COL_ICARE_PROFILE_DATE_OF_BIRTH,
                        SQLiteHelper.COL_ICARE_PROFILE_SPECIAL_NOTES },
                SQLiteHelper.COL_ICARE_PROFILE_ID + "=" + profileID, null,
                null, null, null);

        mUpdateCursor.moveToFirst();

        mId = mUpdateCursor.getString(mUpdateCursor
                .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_ID));
        mName = mUpdateCursor.getString(mUpdateCursor
                .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_NAME));
        mAge = mUpdateCursor.getString(mUpdateCursor
                .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_AGE));
        mBloodGroup = mUpdateCursor
                .getString(mUpdateCursor
                        .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP));
        mWeight = mUpdateCursor.getString(mUpdateCursor
                .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_WEIGHT));
        mHeight = mUpdateCursor.getString(mUpdateCursor
                .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_HEIGHT));
        mDateOfBirth = mUpdateCursor
                .getString(mUpdateCursor
                        .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_DATE_OF_BIRTH));
        mSpecialNotes = mUpdateCursor
                .getString(mUpdateCursor
                        .getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_SPECIAL_NOTES));
        mUpdateCursor.close();
        iCareUpdateProfile = new Profile(mId, mName, mAge, mBloodGroup,
                mWeight, mHeight, mDateOfBirth, mSpecialNotes);
        this.close();
        return iCareUpdateProfile;
    }

    public boolean isEmpty() {
        this.open();
        Cursor mCursor = iCareDatabase.query(SQLiteHelper.I_CARE_PROFILE,
                new String[] { SQLiteHelper.COL_ICARE_PROFILE_ID,
                        SQLiteHelper.COL_ICARE_PROFILE_NAME,
                        SQLiteHelper.COL_ICARE_PROFILE_AGE,
                        SQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP,
                        SQLiteHelper.COL_ICARE_PROFILE_WEIGHT,
                        SQLiteHelper.COL_ICARE_PROFILE_HEIGHT,
                        SQLiteHelper.COL_ICARE_PROFILE_DATE_OF_BIRTH,
                        SQLiteHelper.COL_ICARE_PROFILE_SPECIAL_NOTES },
                null, null, null, null, null);
        if (mCursor.getCount() == 0) {
            this.close();
            return true;
        } else {
            this.close();
            return false;
        }
    }*/
}
