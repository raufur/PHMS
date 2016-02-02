package com.epsilon.coders.phms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.databases.SQLiteHelper;
import com.epsilon.coders.phms.databases.VaccinationDataSource;
import com.epsilon.coders.phms.utills.FTFLConstants;

import java.util.List;


public class VaccinationChartHistory extends ActionBarActivity {
	ListView mListView = null;
	TextView tvDate  = null;
	VaccinationDataSource mVaccinationDataSource = null;
	SQLiteHelper mDBHelper  = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vaccination_chart_history);

//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#22362D"));
//        getSupportActionBar().setBackgroundDrawable(colorDrawable);
		
		mDBHelper = new SQLiteHelper(this);
		mVaccinationDataSource = new VaccinationDataSource(this);
		mListView = (ListView) findViewById(R.id.lvVaccinationChartHistory);
		
		List<String> allDates = null;
		if(FTFLConstants.RADIOBTN == FTFLConstants.ZERO)
		{
			allDates = mVaccinationDataSource.getAllDates();
		}
		
		else if(FTFLConstants.RADIOBTN == FTFLConstants.TWO)
		{	
			allDates = mVaccinationDataSource.getPreviousDates(-7);
		}
		
		else
		{
			allDates = mVaccinationDataSource.getPreviousDates(-30);
		}
		
		ArrayAdapter<String> mDatesAdapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, allDates);
		// adding it to the list view.
		mListView.setAdapter(mDatesAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {

				tvDate = (TextView) view.findViewById(android.R.id.text1);
				String dateValue = tvDate.getText().toString();
				Intent mPreviewIntent = new Intent(getApplicationContext(),
						DailyVaccinationChartView.class);
				mPreviewIntent.putExtra("cDate", dateValue);

				startActivity(mPreviewIntent);
			}
		});
	}
	
	public void viweAllData(View v){
		FTFLConstants.RADIOBTN = FTFLConstants.ZERO;
		 startActivity(new Intent(VaccinationChartHistory.this,
				 VaccinationChartHistory.class));
	}
	
	public void viewWeeklyData(View v){
		FTFLConstants.RADIOBTN = FTFLConstants.ONE;
		 
		 startActivity(new Intent(VaccinationChartHistory.this,
				 VaccinationChartHistory.class));
	}
	
	public void viewMonthlyData(View v){
		FTFLConstants.RADIOBTN = FTFLConstants.TWO;
		 
		 startActivity(new Intent(VaccinationChartHistory.this,
				 VaccinationChartHistory.class));
	}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_vaccination_chart_history, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.home) {
//
//            home();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public  void home(){
     Intent intent= new Intent(this,VaccinationListTodayAndUpcoming.class) ;
        startActivity(intent);
    }
}
