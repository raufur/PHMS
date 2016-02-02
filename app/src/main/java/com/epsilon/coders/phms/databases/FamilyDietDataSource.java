package com.epsilon.coders.phms.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.epsilon.coders.phms.model.FamilyDietChart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class FamilyDietDataSource {

	// Database fields
	private SQLiteDatabase iCareDacDatabase;
	private SQLiteHelper iCareDacDbHelper;
	List<FamilyDietChart> dailyFamilyDietChart = new ArrayList<FamilyDietChart>();
	List<FamilyDietChart> todayFamilyDietChart = new ArrayList<FamilyDietChart>();
	List<String> upcomingDates = new ArrayList<String>();
	List<String> allDates = new ArrayList<String>();
	List<String> weeklyDates = new ArrayList<String>();
	//List<String> monthlyDates = new ArrayList<String>();
	String mCurrentDate = "";

	public FamilyDietDataSource(Context context) {
		iCareDacDbHelper = new SQLiteHelper(context);
	}

	/*
	 * open a method for writable database
	 */
	public void open() throws SQLException {
		iCareDacDatabase = iCareDacDbHelper.getWritableDatabase();
	}

	/*
	 * close database connection
	 */
	public void close() {
		iCareDacDbHelper.close();
	}

	/*
	 * providing the current date
	 */
	public void cDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy",
				Locale.getDefault());
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);
	}

	/*
	 * insert data into the database.
	 */

	public boolean insert(FamilyDietChart eActivityChart) {

		this.open();

		ContentValues cv = new ContentValues();

		cv.put(SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE, eActivityChart.getDate());
		cv.put(SQLiteHelper.COL_ICARE_FAMILY_DIET_TIME, eActivityChart.getTime());
		cv.put(SQLiteHelper.COL_ICARE_FAMILY_DIET_DETAILS,
				eActivityChart.getFamilyDietDetails());
		cv.put(SQLiteHelper.COL_ICARE_FAMILY_DIET_NAME,
				eActivityChart.getFamilyDietName());
		cv.put(SQLiteHelper.COL_ICARE_FAMILY_DIET_ALARM,
				eActivityChart.getAlarm());

		long check = iCareDacDatabase.insert(
				SQLiteHelper.TBL_FAMILY_DIET_CHART, null, cv);
		this.close();
		if (check < 0)
			return false;
		else
			return true;
	}

	// Updating database by id
	public boolean updateData(long eActivityId,
			FamilyDietChart eActivityChart) {
		this.open();
		ContentValues cvUpdate = new ContentValues();

		cvUpdate.put(SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE,
				eActivityChart.getDate());
		cvUpdate.put(SQLiteHelper.COL_ICARE_FAMILY_DIET_TIME,
				eActivityChart.getTime());
		cvUpdate.put(SQLiteHelper.COL_ICARE_FAMILY_DIET_DETAILS,
				eActivityChart.getFamilyDietDetails());
		cvUpdate.put(SQLiteHelper.COL_ICARE_FAMILY_DIET_NAME,
				eActivityChart.getFamilyDietName());
		cvUpdate.put(SQLiteHelper.COL_ICARE_FAMILY_DIET_ALARM,
				eActivityChart.getAlarm());

		int check = iCareDacDatabase.update(SQLiteHelper.TBL_FAMILY_DIET_CHART,
				cvUpdate, SQLiteHelper.COL_ICARE_FAMILY_DIET_ID + "="
						+ eActivityId, null);
		this.close();
		if (check == 0)
			return false;
		else
			return true;
	}

	// delete data form database.
	public boolean deleteData(long eActivityId) {
		this.open();
		iCareDacDatabase.delete(SQLiteHelper.TBL_FAMILY_DIET_CHART,
				SQLiteHelper.COL_ICARE_FAMILY_DIET_ID + "=" + eActivityId, null);
		this.close();
		return true;
	}

	/*
	 * using cursor for display data from database.
	 */
	public List<FamilyDietChart> getDailyFamilydietChart(String eDate) {
		this.open();
		this.cDate();

		Cursor mCursor = iCareDacDatabase.query(
				SQLiteHelper.TBL_FAMILY_DIET_CHART, new String[] {
						SQLiteHelper.COL_ICARE_FAMILY_DIET_ID,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_TIME,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_DETAILS,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_NAME,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_ALARM, },
				SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE + "= '" + eDate + "' ",
				null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {
				do {
					String mActivityId = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_ID));
					String mActivityDate = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE));
					String mActivityTime = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_TIME));
					String mFamilyDietDetails = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_DETAILS));
					String mFamilyDietName = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_NAME));
					String mSetAlarm = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_ALARM));

					dailyFamilyDietChart.add(new FamilyDietChart(mActivityId,
                            mActivityDate, mActivityTime, mFamilyDietDetails,
                            mFamilyDietName, mSetAlarm));
				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return dailyFamilyDietChart;
	}

	/*
	 * using cursor for display data from database.
	 */
	public List<FamilyDietChart> getTodayFamilyDietChart() {
		this.open();
		this.cDate();

		Cursor mCursor = iCareDacDatabase.query(
				SQLiteHelper.TBL_FAMILY_DIET_CHART, new String[] {
						SQLiteHelper.COL_ICARE_FAMILY_DIET_ID,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_TIME,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_DETAILS,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_NAME,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_ALARM,

				}, SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE + "= '" + mCurrentDate
						+ "' ", null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mActivityId = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_ID));
					String mActivityDate = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE));
					String mActivityTime = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_TIME));
					String mFamilyDietDetails = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_DETAILS));
					String mFamilyDietName = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_NAME));
					String mSetAlarm = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_ALARM));

					todayFamilyDietChart.add(new FamilyDietChart(mActivityId,
                            mActivityDate, mActivityTime, mFamilyDietDetails,
                            mFamilyDietName, mSetAlarm));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return todayFamilyDietChart;
	}

	/*
	 * using cursor for display upcoming data from database.
	 */
	public List<String> getUpcomingDates() {
		this.open();
		this.cDate();

		Cursor mCursor = iCareDacDatabase.rawQuery("SELECT "
				+ SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE + " FROM "
				+ SQLiteHelper.TBL_FAMILY_DIET_CHART + "  WHERE "
				+ SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE + " > '" + mCurrentDate
				+ "' ORDER BY " + SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE
				+ " ASC", null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {
					String mActivityDate = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE));
					upcomingDates.add(mActivityDate);

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return upcomingDates;
	}

	/*
	 * using cursor for display weekly data from database.
	 */
	public List<String> getPreviousDates(int eLimit) {
		this.open();
		this.cDate();

		Calendar weekly = Calendar.getInstance();

		weekly.add(Calendar.DATE, eLimit);

		StringBuilder week = new StringBuilder()
				.append(weekly.get(Calendar.DATE)).append("/")
				.append((weekly.get(Calendar.MONTH)) + 1).append("/")
				.append(weekly.get(Calendar.YEAR));
		String mWeek = week.toString();
		Cursor mCursor = iCareDacDatabase.rawQuery("SELECT  "
				+ SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE + " FROM "
				+ SQLiteHelper.TBL_FAMILY_DIET_CHART + "  WHERE "
				+ SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE + " BETWEEN '"
				+ mCurrentDate + "' AND '" + mWeek + "' ORDER BY "
				+ SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE + "", null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {
					String mActivityDate = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE));
					weeklyDates.add(mActivityDate);

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return weeklyDates;
	}

	/*
	 * using cursor for display all data from database.
	 */
	public List<String> getAllDates() {
		this.open();
		this.cDate();

		Cursor mCursor = iCareDacDatabase.rawQuery("SELECT  "
				+ SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE + " FROM "
				+ SQLiteHelper.TBL_FAMILY_DIET_CHART + "", null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {
					String mActivityDate = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE));
					allDates.add(mActivityDate);

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return allDates;
	}



	public FamilyDietChart updateActivityData(long eActivityId) {
		this.open();
		FamilyDietChart iCareUpdateActivity;

		Cursor mUpdateCursor = iCareDacDatabase.query(
				SQLiteHelper.TBL_FAMILY_DIET_CHART, new String[] {
						SQLiteHelper.COL_ICARE_FAMILY_DIET_ID,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_TIME,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_DETAILS,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_NAME,
						SQLiteHelper.COL_ICARE_FAMILY_DIET_ALARM,

				}, SQLiteHelper.COL_ICARE_FAMILY_DIET_ID + "=" + eActivityId,
				null, null, null, null);

		mUpdateCursor.moveToFirst();

		String mActivityId = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_ID));
		String mActivityDate = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_DATE));
		String mActivityTime = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_TIME));
		String mFamilyDietDetails = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_DETAILS));
		String mFamilyDietName = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_NAME));
		String mSetAlarm = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_ICARE_FAMILY_DIET_ALARM));

		mUpdateCursor.close();
		iCareUpdateActivity = new FamilyDietChart(mActivityId,
				mActivityDate, mActivityTime, mFamilyDietDetails, mFamilyDietName,
				mSetAlarm);
		this.close();
		return iCareUpdateActivity;
	}

}
