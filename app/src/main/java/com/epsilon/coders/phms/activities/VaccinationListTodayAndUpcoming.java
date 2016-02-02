package com.epsilon.coders.phms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.adapters.VaccinationViewAdapter;
import com.epsilon.coders.phms.databases.SQLiteHelper;
import com.epsilon.coders.phms.databases.VaccinationDataSource;
import com.epsilon.coders.phms.model.VaccinationChart;
import com.epsilon.coders.phms.utills.FTFLConstants;

import java.util.List;


public class VaccinationListTodayAndUpcoming extends AppCompatActivity {

	ListView mListViewOne = null;
	ListView mListViewTwo = null;
	VaccinationDataSource mVaccinationDataSource = null;
	SQLiteHelper mDBHelper = null;
	TextView tvDate = null;

	ImageButton myTodayVaccinationChartView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vaccination_list_today_upcoming);

		android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#22362D"));
//        getSupportActionBar().setBackgroundDrawable(colorDrawable);

//
//        ActionBar bar = getActionBar();
//        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000ff")));

		myTodayVaccinationChartView=(ImageButton)findViewById(R.id.btnmyTodayVaccinationChartView);

		myTodayVaccinationChartView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(VaccinationListTodayAndUpcoming.this,CreateVaccinationChart.class);
				startActivity(intent);
			}
		});

		mDBHelper = new SQLiteHelper(this);
		mVaccinationDataSource = new VaccinationDataSource(this);

		List<VaccinationChart> allDailyVaccinationChart = mVaccinationDataSource
				.getTodayVaccinationChart();

		VaccinationViewAdapter arrayAdapterOne = new VaccinationViewAdapter(
				this, allDailyVaccinationChart);

		// adding it to the list view.
		mListViewOne = (ListView) findViewById(R.id.lvTodayVaccinationChart);
		mListViewOne.setAdapter(arrayAdapterOne);

		List<String> upcomingDates = mVaccinationDataSource.getUpcomingDates();

		ArrayAdapter<String> mDatesAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, upcomingDates);
		// adding it to the list view.
		mListViewTwo = (ListView) findViewById(R.id.lvUpComingVaccinationChart);
		mListViewTwo.setAdapter(mDatesAdapter);

		mListViewTwo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {

				tvDate = (TextView) view.findViewById(android.R.id.text1);
				String dateValue = tvDate.getText().toString(); // get date

				Intent mPreviewIntent = new Intent(getApplicationContext(),
						DailyVaccinationChartView.class);
				mPreviewIntent.putExtra(FTFLConstants.ACTIVITYDATE, dateValue);

				startActivity(mPreviewIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_vaccination_list_today_and_upcoming, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.history:
			startActivity(new Intent(VaccinationListTodayAndUpcoming.this,
					VaccinationChartHistory.class));
			return true;
		case R.id.home:
			startActivity(new Intent(VaccinationListTodayAndUpcoming.this,
					MainActivity.class));
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
