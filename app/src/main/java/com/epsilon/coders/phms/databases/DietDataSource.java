package com.epsilon.coders.phms.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.epsilon.coders.phms.model.DietChart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DietDataSource {

	// Database fields
	private SQLiteDatabase iCareDacDatabase;
	private SQLiteHelper iCareDacDbHelper;
	List<DietChart> dailyDietChart = new ArrayList<DietChart>();
	List<DietChart> todayDietChart = new ArrayList<DietChart>();
	List<String> upcomingDates = new ArrayList<String>();
	List<String> allDates = new ArrayList<String>();
	List<String> weeklyDates = new ArrayList<String>();
	List<String> monthlyDates = new ArrayList<String>();
	String mCurrentDate = "";

	public DietDataSource(Context context) {
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

	public boolean insert(DietChart eActivityChart) {

		this.open();

		ContentValues cv = new ContentValues();

		cv.put(SQLiteHelper.COL_ICARE_DIET_DATE, eActivityChart.getDate());
		cv.put(SQLiteHelper.COL_ICARE_DIET_TIME, eActivityChart.getTime());
		cv.put(SQLiteHelper.COL_ICARE_DIET_FOOD_MENU,
				eActivityChart.getFoodMenu());
		cv.put(SQLiteHelper.COL_ICARE_DIET_EVENT_NAME,
				eActivityChart.getEventName());
		cv.put(SQLiteHelper.COL_ICARE_DIET_ALARM,
				eActivityChart.getAlarm());

		long check = iCareDacDatabase.insert(
				SQLiteHelper.ICARE_DIET_CHART, null, cv);
		this.close();
		if (check < 0)
			return false;
		else
			return true;
	}

	// Updating database by id
	public boolean updateData(long eActivityId,
			DietChart eActivityChart) {
		this.open();
		ContentValues cvUpdate = new ContentValues();

		cvUpdate.put(SQLiteHelper.COL_ICARE_DIET_DATE,
				eActivityChart.getDate());
		cvUpdate.put(SQLiteHelper.COL_ICARE_DIET_TIME,
				eActivityChart.getTime());
		cvUpdate.put(SQLiteHelper.COL_ICARE_DIET_FOOD_MENU,
				eActivityChart.getFoodMenu());
		cvUpdate.put(SQLiteHelper.COL_ICARE_DIET_EVENT_NAME,
				eActivityChart.getEventName());
		cvUpdate.put(SQLiteHelper.COL_ICARE_DIET_ALARM,
				eActivityChart.getAlarm());

		int check = iCareDacDatabase.update(SQLiteHelper.ICARE_DIET_CHART,
				cvUpdate, SQLiteHelper.COL_ICARE_DIET_ID + "="
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
		iCareDacDatabase.delete(SQLiteHelper.ICARE_DIET_CHART,
				SQLiteHelper.COL_ICARE_DIET_ID + "=" + eActivityId, null);
		this.close();
		return true;
	}

	/*
	 * using cursor for display data from database.
	 */
	public List<DietChart> getDailyDietChart(String eDate) {
		this.open();
		this.cDate();

		Cursor mCursor = iCareDacDatabase.query(
				SQLiteHelper.ICARE_DIET_CHART, new String[] {
						SQLiteHelper.COL_ICARE_DIET_ID,
						SQLiteHelper.COL_ICARE_DIET_DATE,
						SQLiteHelper.COL_ICARE_DIET_TIME,
						SQLiteHelper.COL_ICARE_DIET_FOOD_MENU,
						SQLiteHelper.COL_ICARE_DIET_EVENT_NAME,
						SQLiteHelper.COL_ICARE_DIET_ALARM, },
				SQLiteHelper.COL_ICARE_DIET_DATE + "= '" + eDate + "' ",
				null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {
				do {
					String mActivityId = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_ID));
					String mActivityDate = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_DATE));
					String mActivityTime = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_TIME));
					String mDietFoodMenu = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_FOOD_MENU));
					String mDietEventName = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_EVENT_NAME));
					String mSetAlarm = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_ALARM));

					dailyDietChart.add(new DietChart(mActivityId,
							mActivityDate, mActivityTime, mDietFoodMenu,
							mDietEventName, mSetAlarm));
				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return dailyDietChart;
	}

	/*
	 * using cursor for display data from database.
	 */
	public List<DietChart> getTodayDietChart() {
		this.open();
		this.cDate();

		Cursor mCursor = iCareDacDatabase.query(
				SQLiteHelper.ICARE_DIET_CHART, new String[] {
						SQLiteHelper.COL_ICARE_DIET_ID,
						SQLiteHelper.COL_ICARE_DIET_DATE,
						SQLiteHelper.COL_ICARE_DIET_TIME,
						SQLiteHelper.COL_ICARE_DIET_FOOD_MENU,
						SQLiteHelper.COL_ICARE_DIET_EVENT_NAME,
						SQLiteHelper.COL_ICARE_DIET_ALARM,

				}, SQLiteHelper.COL_ICARE_DIET_DATE + "= '" + mCurrentDate
						+ "' ", null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mActivityId = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_ID));
					String mActivityDate = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_DATE));
					String mActivityTime = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_TIME));
					String mDietFoodMenu = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_FOOD_MENU));
					String mDietEventName = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_EVENT_NAME));
					String mSetAlarm = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_ALARM));

					todayDietChart.add(new DietChart(mActivityId,
							mActivityDate, mActivityTime, mDietFoodMenu,
							mDietEventName, mSetAlarm));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return todayDietChart;
	}

	/*
	 * using cursor for display upcoming data from database.
	 */
	public List<String> getUpcomingDates() {
		this.open();
		this.cDate();

		Cursor mCursor = iCareDacDatabase.rawQuery("SELECT DISTINCT "
				+ SQLiteHelper.COL_ICARE_DIET_DATE + " FROM "
				+ SQLiteHelper.ICARE_DIET_CHART + "  WHERE "
				+ SQLiteHelper.COL_ICARE_DIET_DATE + " > '" + mCurrentDate
				+ "' ORDER BY " + SQLiteHelper.COL_ICARE_DIET_DATE
				+ " ASC", null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {
					String mActivityDate = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_DATE));
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
		Cursor mCursor = iCareDacDatabase.rawQuery("SELECT DISTINCT "
				+ SQLiteHelper.COL_ICARE_DIET_DATE + " FROM "
				+ SQLiteHelper.ICARE_DIET_CHART + "  WHERE "
				+ SQLiteHelper.COL_ICARE_DIET_DATE + " BETWEEN '"
				+ mCurrentDate + "' AND '" + mWeek + "' ORDER BY "
				+ SQLiteHelper.COL_ICARE_DIET_DATE + "", null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {
					String mActivityDate = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_DATE));
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

		Cursor mCursor = iCareDacDatabase.rawQuery("SELECT DISTINCT "
				+ SQLiteHelper.COL_ICARE_DIET_DATE + " FROM "
				+ SQLiteHelper.ICARE_DIET_CHART + "", null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {
					String mActivityDate = mCursor
							.getString(mCursor
									.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_DATE));
					allDates.add(mActivityDate);

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return allDates;
	}

	/*
	 * create a Diet chart of ICareProfile. Here the data of the database
	 * according to the given id is set to the Diet chart and return a Diet
	 * chart object.
	 */

	public DietChart updateActivityData(long eActivityId) {
		this.open();
		DietChart iCareUpdateActivity;

		Cursor mUpdateCursor = iCareDacDatabase.query(
				SQLiteHelper.ICARE_DIET_CHART, new String[] {
						SQLiteHelper.COL_ICARE_DIET_ID,
						SQLiteHelper.COL_ICARE_DIET_DATE,
						SQLiteHelper.COL_ICARE_DIET_TIME,
						SQLiteHelper.COL_ICARE_DIET_FOOD_MENU,
						SQLiteHelper.COL_ICARE_DIET_EVENT_NAME,
						SQLiteHelper.COL_ICARE_DIET_ALARM,

				}, SQLiteHelper.COL_ICARE_DIET_ID + "=" + eActivityId,
				null, null, null, null);

		mUpdateCursor.moveToFirst();

		String mActivityId = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_ID));
		String mActivityDate = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_DATE));
		String mActivityTime = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_TIME));
		String mDietFoodMenu = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_FOOD_MENU));
		String mDietEventName = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_EVENT_NAME));
		String mSetAlarm = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_ICARE_DIET_ALARM));

		mUpdateCursor.close();
		iCareUpdateActivity = new DietChart(mActivityId,
				mActivityDate, mActivityTime, mDietFoodMenu, mDietEventName,
				mSetAlarm);
		this.close();
		return iCareUpdateActivity;
	}

}
