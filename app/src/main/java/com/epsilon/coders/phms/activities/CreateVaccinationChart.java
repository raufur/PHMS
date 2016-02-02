package com.epsilon.coders.phms.activities;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
import com.epsilon.coders.phms.databases.VaccinationDataSource;
import com.epsilon.coders.phms.model.VaccinationChart;
import com.epsilon.coders.phms.utills.FTFLConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CreateVaccinationChart extends AppCompatActivity implements
		OnClickListener, OnDateSetListener, OnTimeSetListener {

	private Toolbar toolbar;

	Button btnSave = null;
	Button btnCancel = null;
	Button btnPlus = null;
	Button btnMinus = null;
    Spinner etVaccinationName = null;
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
	VaccinationDataSource activityDS = null;
	VaccinationChart mUpdateActivity = null;
	VaccinationDataSource mActivityDataSource = null;
	String mActivityCurrentDate = "";
	String mActivityProfileId = "";
	Integer mDbHour = 0;
	Integer mDbMinute = 0;
	Calendar mCalendar = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_vaccination_chart);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#22362D"));
//        getSupportActionBar().setBackgroundDrawable(colorDrawable);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",
				Locale.getDefault());
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);
		mAlarm = "0";

		etDate = (EditText) findViewById(R.id.editTextDP);
		etTime = (EditText) findViewById(R.id.etVaccinationTime);
		//etName = (EditText) findViewById(R.id.etVaccinationName);
        etVaccinationName= (Spinner) findViewById(R.id.spinnerVaccinationName);

        etDescription = (EditText) findViewById(R.id.editVaccinationDetails);

		cbAlarm = (CheckBox) findViewById(R.id.vaccination_alarm);
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

			mActivityDataSource = new VaccinationDataSource(this);
			mUpdateActivity = mActivityDataSource
					.updateActivityData(mActivityId);

			String mDate = mUpdateActivity.getDate();
			String mTime = mUpdateActivity.getTime();
			String mName = mUpdateActivity.getVaccinationName();
			String mDescription = mUpdateActivity.getVaccinationDetails();
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
        list.add("Malaria vaccine");
        list.add("Hepatitis A");
        list.add("Typhoid vaccine");
        list.add("Polio vaccine");
        list.add("Hepatitis B");
        list.add("Rabies vaccine");
        list.add("Tetanus-diphtheria");
        list.add("Cholera vaccine");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etVaccinationName.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection3() {
        etVaccinationName = (Spinner) findViewById(R.id.spinnerVaccinationName);
        // etBloodGroup.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		getMenuInflater().inflate(R.menu.menu_create_vaccination_chart, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.Menu) {
//
//            Intent intent= new Intent(this, MainActivity.class);
//            startActivity(intent);
//
//
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

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

		case R.id.etVaccinationTime:

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
            mName = String.valueOf(etVaccinationName.getSelectedItem());

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

			VaccinationChart activityDataInsert = new VaccinationChart();
			activityDataInsert.setDate(mDate);
			activityDataInsert.setTime(mTime);
			activityDataInsert.setVaccinationName(mName);
			activityDataInsert.setVaccinationDetails(mDescription);
			activityDataInsert.setAlarm(mAlarm);



			/*
			 * if update is needed then update otherwise submit
			 */
			if (mStrActivityID != null) {

				mActivityId = Long.parseLong(mStrActivityID);

				activityDS = new VaccinationDataSource(this);

				if (activityDS.updateData(mActivityId, activityDataInsert) == true) {





					toast = Toast.makeText(this,
							getString(R.string.successfullyUpdated),
							Toast.LENGTH_LONG);
					toast.show();
					startActivity(new Intent(CreateVaccinationChart.this,
							VaccinationListTodayAndUpcoming.class));
					finish();
				} else {
					toast = Toast.makeText(this,
							getString(R.string.notUpdated), Toast.LENGTH_LONG);
					toast.show();
				}
			} else {
				activityDS = new VaccinationDataSource(this);
				if (activityDS.insert(activityDataInsert) == true) {


					toast = Toast.makeText(this,
							getString(R.string.successfullySaved),
							Toast.LENGTH_LONG);
					toast.show();

					startActivity(new Intent(CreateVaccinationChart.this,
							VaccinationListTodayAndUpcoming.class));
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
