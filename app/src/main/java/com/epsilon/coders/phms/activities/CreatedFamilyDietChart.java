package com.epsilon.coders.phms.activities;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.databases.FamilyDietDataSource;
import com.epsilon.coders.phms.model.FamilyDietChart;
import com.epsilon.coders.phms.utills.FTFLConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CreatedFamilyDietChart extends AppCompatActivity implements
		OnClickListener, OnDateSetListener, OnTimeSetListener {
	Button btnSave = null;

    Spinner etFamilyDietName = null;
	EditText etDate = null;
	String mDate = "";
	EditText etTime = null;
	String mTime = "";
	EditText etName = null;
	String mName = "";
	EditText etDescription = null;
	String mDescription = "";
	CheckBox cbAlarm = null;
	String mAlarm = "";
	String mCurrentDate = "";
	int mHour = 0;
	int mMinute = 0;
	int mYear = 0;
	int mDay = 0;
	int mMonth = 0;
	String mStrProfileID = "";
	String mStrActivityID = "";
	long mActivityId = 0;
	FamilyDietDataSource activityDS = null;
	FamilyDietChart mUpdateActivity = null;
	FamilyDietDataSource mActivityDataSource = null;
	String mActivityCurrentDate = "";
	String mActivityProfileId = "";
	Integer mDbHour = 0;
	Integer mDbMinute = 0;
	Calendar mCalendar = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_family_diet_chart);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",
				Locale.getDefault());
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);
		mAlarm = "0";

		etDate = (EditText) findViewById(R.id.editTextDP);
		etTime = (EditText) findViewById(R.id.etFamilyDietTime);
		//etName = (EditText) findViewById(R.id.etFamilyDietName);
        etFamilyDietName = (Spinner) findViewById(R.id.spinnerFamilyDietName);

        etDescription = (EditText) findViewById(R.id.editFamilyDietDetails);

		cbAlarm = (CheckBox) findViewById(R.id.FamilyDiet_alarm);
		btnSave = (Button) findViewById(R.id.btnSave);

		etDate.setOnClickListener(this);
		etTime.setOnClickListener(this);

		cbAlarm.setOnClickListener(this);
		btnSave.setOnClickListener(this);

		Intent mActivityIntent = getIntent();
		mStrActivityID = mActivityIntent
				.getStringExtra(FTFLConstants.ACTIVITYID);

		if (mStrActivityID != null) {
			mActivityId = Long.parseLong(mStrActivityID);

			mActivityDataSource = new FamilyDietDataSource(this);
			mUpdateActivity = mActivityDataSource
					.updateActivityData(mActivityId);

			String mDate = mUpdateActivity.getDate();
			String mTime = mUpdateActivity.getTime();
			String mName = mUpdateActivity.getFamilyDietName();
			String mDescription = mUpdateActivity.getFamilyDietDetails();
			String mAlarm = mUpdateActivity.getAlarm();
			long mActivityAlarm = Long.parseLong(mAlarm);

			// set the value of database to the text field.
			etDate.setText(mDate);
			etTime.setText(mTime);
			etName.setText(mName);
			etDescription.setText(mDescription);
			if (mActivityAlarm == 1) {
				cbAlarm.setChecked(!cbAlarm.isChecked());
			}

			/*
			 * change button name
			 */
			btnSave.setText(getString(R.string.Update));
		}
        addItemsOnSpinner3();
        addListenerOnSpinnerItemSelection3();

	}



    public void addItemsOnSpinner3() {


        List<String> list = new ArrayList<String>();
        list.add("Breakfast");
        list.add("Launch");
        list.add("Snacks");
		list.add("Dinner");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etFamilyDietName.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection3() {
        etFamilyDietName = (Spinner) findViewById(R.id.spinnerFamilyDietName);
        // etBloodGroup.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }










	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

		mDbHour = hourOfDay;
		mDbMinute = minute;
		int hour = 0;
		String time = "";
		if (hourOfDay > 12) {
			hour = hourOfDay - 12;
			time = getString(R.string.pm);
		}

		else if (hourOfDay == 12) {
			hour = hourOfDay;
			time = getString(R.string.pm);
		}

		else if (hourOfDay == 0) {
			hour = hourOfDay + 12;
			time = getString(R.string.am);
		} else {
			hour = hourOfDay;
			time = getString(R.string.am);
		}
		etTime.setText(new StringBuilder().append(hour).append(" : ")
				.append(minute).append(" ").append(time));
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		etDate.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(dayOfMonth).append("/").append(monthOfYear + 1)
				.append("/").append(year));
	}

	@Override
	public void onClick(View v) {
		Toast toast = null;
		switch (v.getId()) {

		case R.id.editTextDP:

			mYear = mCalendar.get(Calendar.YEAR);
			mMonth = mCalendar.get(Calendar.MONTH);
			mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog dialog = new DatePickerDialog(this, this, mYear,
					mMonth, mDay);
			dialog.show();
			break;

		case R.id.etFamilyDietTime:

			// Process to get Current Time
			mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
			mMinute = mCalendar.get(Calendar.MINUTE);

			// Launch Time Picker Dialog
			TimePickerDialog tpd = new TimePickerDialog(this, this, mHour,
					mMinute, false);
			tpd.show();
			break;

		case R.id.btnSave:

			mDate = etDate.getText().toString();
			mTime = etTime.getText().toString();
			//mName = etName.getText().toString();
            mName = String.valueOf(etFamilyDietName.getSelectedItem());

			mDescription = etDescription.getText().toString();

			if (cbAlarm.isChecked()) {
				mAlarm = "1";
				Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
				alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, mDescription);
				alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, mDbHour);
				alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, mDbMinute);
				alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
				alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(alarmIntent);
			}

			FamilyDietChart activityDataInsert = new FamilyDietChart();
			activityDataInsert.setDate(mDate);
			activityDataInsert.setTime(mTime);
			activityDataInsert.setFamilyDietName(mName);
			activityDataInsert.setFamilyDietDetails(mDescription);
			activityDataInsert.setAlarm(mAlarm);





			/*
			 * if update is needed then update otherwise submit
			 */
			if (mStrActivityID != null) {

				mActivityId = Long.parseLong(mStrActivityID);

				activityDS = new FamilyDietDataSource(this);

				if (activityDS.updateData(mActivityId, activityDataInsert) == true) {





					toast = Toast.makeText(this,
							getString(R.string.successfullyUpdated),
							Toast.LENGTH_LONG);
					toast.show();
					startActivity(new Intent(CreatedFamilyDietChart.this,
							FamilyDeitChartListTodayAndUpcoming.class));
					finish();
				} else {
					toast = Toast.makeText(this,
							getString(R.string.notUpdated), Toast.LENGTH_LONG);
					toast.show();
				}
			} else {
				activityDS = new FamilyDietDataSource(this);
				if (activityDS.insert(activityDataInsert) == true) {


					toast = Toast.makeText(this,
							getString(R.string.successfullySaved),
							Toast.LENGTH_LONG);
					toast.show();

					startActivity(new Intent(CreatedFamilyDietChart.this,
							FamilyDeitChartListTodayAndUpcoming.class));
					finish();
				} else {
					toast = Toast.makeText(this, getString(R.string.notSaved),
							Toast.LENGTH_LONG);
					toast.show();
				}
			}
			break;
		}
	}
}
