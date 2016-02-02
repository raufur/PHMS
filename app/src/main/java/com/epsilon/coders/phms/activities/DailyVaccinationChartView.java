package com.epsilon.coders.phms.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.adapters.VaccinationViewAdapter;
import com.epsilon.coders.phms.databases.SQLiteHelper;
import com.epsilon.coders.phms.databases.VaccinationDataSource;
import com.epsilon.coders.phms.model.VaccinationChart;
import com.epsilon.coders.phms.utills.FTFLConstants;

import java.util.List;


public class DailyVaccinationChartView extends AppCompatActivity {
	ListView mListView = null;
	VaccinationDataSource mVaccinationDataSource = null;
	SQLiteHelper mDBHelper = null;
	VaccinationChart dailyVaccinationChart = null;
	int id_To_Update = 0;
	String mDate = "";
	TextView textDate;
	String mId = "";
	TextView mId_tv = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.daily_vaccination_chart_view);



		final String[] option = new String[] { getString(R.string.editData),
				getString(R.string.deleteData) };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, option);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle(getString(R.string.selectData));
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					editData(mId);
				}
				if (which == 1) {
					deleteData(mId);
				}
			}
		});
		final AlertDialog dialog = builder.create();

		mDBHelper = new SQLiteHelper(this);
		mVaccinationDataSource = new VaccinationDataSource(this);
		textDate = (TextView) findViewById(R.id.textDailyVaccinationChartDate);

		Intent mIntent = getIntent();
		mDate = mIntent.getStringExtra(FTFLConstants.ACTIVITYDATE);

		textDate.setText(mDate);
		List<VaccinationChart> allDailyVaccinationChart = mVaccinationDataSource
				.getDailyVaccinationChart(mDate);

		VaccinationViewAdapter arrayAdapter = new VaccinationViewAdapter(
				this, allDailyVaccinationChart);

		// adding it to the list view.
		mListView = (ListView) findViewById(R.id.lvDailyVaccinationChart);
		mListView.setAdapter(arrayAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				mId_tv = (TextView) view.findViewById(R.id.viewId);
				mId = mId_tv.getText().toString(); // get id
				dialog.show();
			}
		});
	}

	public void editData(String eId) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				CreateVaccinationChart.class);
		mEditIntent.putExtra("FTFLConstants.ACTIVITYID", eId);
		// startActivity(mEditIntent);
		startActivityForResult(mEditIntent, 2);
	}

	public void deleteData(String eId) {
		mVaccinationDataSource = new VaccinationDataSource(this);
		Long lId = Long.parseLong(eId);
		mVaccinationDataSource.deleteData(lId);
		finish();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.icare_daily_vaccination_chart_view, menu);
//		return true;
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.home) {
//            home();
//			return true;
//		}else if(id==R.id.Mainhome){
//            Intent intent= new Intent(this,MainActivity.class) ;
//            startActivity(intent);
//        }
//		return super.onOptionsItemSelected(item);
//	}
//    public  void home(){
//        Intent intent= new Intent(this,VaccinationListTodayAndUpcoming.class) ;
//        startActivity(intent);
//    }
}
