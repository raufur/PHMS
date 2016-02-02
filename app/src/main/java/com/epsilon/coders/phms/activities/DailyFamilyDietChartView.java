package com.epsilon.coders.phms.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.adapters.FamilyDietViewAdapter;
import com.epsilon.coders.phms.databases.FamilyDietDataSource;
import com.epsilon.coders.phms.databases.SQLiteHelper;
import com.epsilon.coders.phms.model.FamilyDietChart;
import com.epsilon.coders.phms.utills.FTFLConstants;

import java.util.List;


public class DailyFamilyDietChartView extends AppCompatActivity {
	ListView mListView = null;
	FamilyDietDataSource mFamilyDietDataSource = null;
	SQLiteHelper mDBHelper = null;
	FamilyDietChart dailyFamilyDietChart = null;
	int id_To_Update = 0;
	String mDate = "";
	TextView textDate;
	String mId = "";
	TextView mId_tv = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.daily_family_diet_chart_view);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#22362D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
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
		mFamilyDietDataSource = new FamilyDietDataSource(this);
		textDate = (TextView) findViewById(R.id.textDailyFamilyDietChartDate);

		Intent mIntent = getIntent();
		mDate = mIntent.getStringExtra(FTFLConstants.ACTIVITYDATE);

		textDate.setText(mDate);
		List<FamilyDietChart> allDailyFamilyDietChart = mFamilyDietDataSource
				.getDailyFamilydietChart(mDate);

		FamilyDietViewAdapter arrayAdapter = new FamilyDietViewAdapter(
				this, allDailyFamilyDietChart);

		// adding it to the list view.
		mListView = (ListView) findViewById(R.id.lvDailyFamilyDietChart);
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
				CreatedFamilyDietChart.class);
		mEditIntent.putExtra("FTFLConstants.ACTIVITYID", eId);
		// startActivity(mEditIntent);
		startActivityForResult(mEditIntent, 2);
	}

	public void deleteData(String eId) {
		mFamilyDietDataSource = new FamilyDietDataSource(this);
		Long lId = Long.parseLong(eId);
		mFamilyDietDataSource.deleteData(lId);
		finish();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.icare_daily_family_diet_chart_view, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.home) {
//            home();
//			return true;
//		}
//        else if(id==R.id.mainhomehome){
//            Intent intent= new Intent(this,MainActivity.class) ;
//            startActivity(intent);
//        }
//		return super.onOptionsItemSelected(item);
//	}
    public  void home(){
        Intent intent= new Intent(this,FamilyDeitChartListTodayAndUpcoming.class) ;
        startActivity(intent);
    }
}
