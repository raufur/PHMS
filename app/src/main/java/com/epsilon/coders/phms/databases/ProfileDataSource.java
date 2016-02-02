package com.epsilon.coders.phms.databases;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.epsilon.coders.phms.model.Profile;

import java.util.ArrayList;
import java.util.List;


public class ProfileDataSource {

	// Database fields
	private SQLiteDatabase iCareDatabase;
	private SQLiteHelper iCareDbHelper;
	List<Profile> profilesList = new ArrayList<Profile>();

	public ProfileDataSource(Context context) {
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

	public long addProfile(Profile profile) {

		iCareDatabase = iCareDbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(iCareDbHelper.COL_ICARE_PROFILE_NAME, profile.getName());
		values.put(iCareDbHelper.COL_ICARE_PROFILE_AGE, profile.getAge());
		values.put(iCareDbHelper.COL_ICARE_PROFILE_BLOOD_GROUP, profile.getBloodGroup());
		values.put(iCareDbHelper.COL_ICARE_PROFILE_DATE_OF_BIRTH, profile.getDateOfBirth());
		values.put(iCareDbHelper.COL_ICARE_PROFILE_WEIGHT, profile.getWeight());
		values.put(iCareDbHelper.COL_ICARE_PROFILE_HEIGHT, profile.getHeight());
		values.put(iCareDbHelper.COL_ICARE_PROFILE_SPECIAL_NOTES, profile.getSpecialNotes());

		return iCareDatabase.insert(iCareDbHelper.I_CARE_PROFILE, null, values);

	}


	public boolean insert(Profile eProfile) {

		this.open();

		ContentValues cvInsert = new ContentValues();

		cvInsert.put(SQLiteHelper.COL_ICARE_PROFILE_NAME,
				eProfile.getName());

		cvInsert.put(SQLiteHelper.COL_ICARE_PROFILE_AGE, eProfile.getAge());
		cvInsert.put(SQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP,
				eProfile.getBloodGroup());
		cvInsert.put(SQLiteHelper.COL_ICARE_PROFILE_WEIGHT,
				eProfile.getWeight());
		cvInsert.put(SQLiteHelper.COL_ICARE_PROFILE_HEIGHT,
				eProfile.getHeight());
		cvInsert.put(SQLiteHelper.COL_ICARE_PROFILE_DATE_OF_BIRTH,
				eProfile.getDateOfBirth());

		cvInsert.put(SQLiteHelper.COL_ICARE_PROFILE_SPECIAL_NOTES,
				eProfile.getSpecialNotes());

		cvInsert.put(SQLiteHelper.COL_ICARE_PROFILE_IMAGE,
				eProfile.getImage());

		long check = iCareDatabase.insert(SQLiteHelper.I_CARE_PROFILE,
				null, cvInsert);

		this.close();
		if (check < 0)
			return false;
		else
			return true;
	}



	public boolean updateData(long eProfileId, Profile eProfile) {
		this.open();
		ContentValues cvUpdate = new ContentValues();

		cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_NAME,
				eProfile.getName());
		cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_AGE, eProfile.getAge());
		cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP,
				eProfile.getBloodGroup());
		cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_WEIGHT,
				eProfile.getWeight());
		cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_HEIGHT,
				eProfile.getHeight());
		cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_DATE_OF_BIRTH,
				eProfile.getDateOfBirth());
		cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_SPECIAL_NOTES,
				eProfile.getSpecialNotes());
		cvUpdate.put(SQLiteHelper.COL_ICARE_PROFILE_IMAGE,
				eProfile.getSpecialNotes());


		int check = iCareDatabase.update(SQLiteHelper.I_CARE_PROFILE,
				cvUpdate, SQLiteHelper.COL_ICARE_PROFILE_ID + "="
						+ eProfileId, null);

		this.close();
		if (check == 0)
			return false;
		else
			return true;
	}

	// delete data form database.

	public void deleteProfile(Integer id){

		iCareDatabase=iCareDbHelper.getWritableDatabase();
		iCareDatabase.delete(iCareDbHelper.I_CARE_PROFILE,"id = ?", new String[]{ Integer.toString(id)});
	}

	public boolean deleteData(long eProfileId) {
		this.open();
		try {
			iCareDatabase.delete(SQLiteHelper.I_CARE_PROFILE,
					SQLiteHelper.COL_ICARE_PROFILE_ID + "=" + eProfileId,
					null);
		} catch (Exception ex) {
			Log.e("ERROR", "data insertion problem");
			return false;
		}
		this.close();
		return true;
	}

	/*
	 * using cursor for display data from database.
	 */
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

					int mId = mCursor
							.getInt(mCursor
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
					byte[] image = mCursor
							.getBlob(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_IMAGE));
					// long mmId = Long.parseLong(mId);
					profilesList.add(new Profile(mName, mAge,
							mBloodGroup, mWeight, mHeight, mDateOfBirth,
							mSpecialNotes,image,mId));
				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return profilesList;
	}

	/*
	 * create a profile of ICareProfile. Here the data of the database according
	 * to the given id is set to the profile and return a profile class object.
	 */
	public Profile singleProfileData() {
		this.open();
		Profile iCareUpdateProfile;
		int mId;
		String mName;
		int profileID = 1;
		String mAge;
		String mBloodGroup;
		String mWeight;
		String mHeight;
		String mDateOfBirth;
		String mSpecialNotes;
		byte[] image;

		Cursor mUpdateCursor = iCareDatabase.query(
				SQLiteHelper.I_CARE_PROFILE, new String[] {
						SQLiteHelper.COL_ICARE_PROFILE_ID,
						SQLiteHelper.COL_ICARE_PROFILE_NAME,
						SQLiteHelper.COL_ICARE_PROFILE_AGE,
						SQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP,
						SQLiteHelper.COL_ICARE_PROFILE_WEIGHT,
						SQLiteHelper.COL_ICARE_PROFILE_HEIGHT,
						SQLiteHelper.COL_ICARE_PROFILE_DATE_OF_BIRTH,
						SQLiteHelper.COL_ICARE_PROFILE_IMAGE,
						SQLiteHelper.COL_ICARE_PROFILE_SPECIAL_NOTES },
				SQLiteHelper.COL_ICARE_PROFILE_ID + "=" + profileID, null,
				null, null, null);
		if (mUpdateCursor!=null && mUpdateCursor.getCount()>0)
		{
			mUpdateCursor.moveToFirst();

			mId = mUpdateCursor.getInt(mUpdateCursor
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
			image = mUpdateCursor
					.getBlob(mUpdateCursor
							.getColumnIndex(SQLiteHelper.COL_ICARE_PROFILE_IMAGE));
			mUpdateCursor.close();
			iCareUpdateProfile = new Profile(mName, mAge,
					mBloodGroup, mWeight, mHeight, mDateOfBirth,
					mSpecialNotes,image,mId);
			this.close();
			return iCareUpdateProfile;
		}
		else
		{
			return null;
		}
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
	}








}
