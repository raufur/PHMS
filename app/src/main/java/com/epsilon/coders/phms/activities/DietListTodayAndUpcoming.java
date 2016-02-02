package com.epsilon.coders.phms.activities;

import android.app.Fragment;
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
import com.epsilon.coders.phms.adapters.DailyProfileViewAdapter;
import com.epsilon.coders.phms.databases.DietDataSource;
import com.epsilon.coders.phms.databases.SQLiteHelper;
import com.epsilon.coders.phms.model.DietChart;
import com.epsilon.coders.phms.utills.FTFLConstants;

import java.util.List;


public class DietListTodayAndUpcoming extends AppCompatActivity {
	ListView mListViewOne = null;
	ListView mListViewTwo = null;
	DietDataSource mDietDataSource = null;
	SQLiteHelper mDBHelper = null;
	TextView tvDate = null;
    Fragment fragment;
    ImageButton myTodayDietChartView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_list_today_upcoming);


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        init();
    }


    private void init() {

        myTodayDietChartView=(ImageButton)findViewById(R.id.btnmyTodayDietChartView);

        myTodayDietChartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DietListTodayAndUpcoming.this,CreateDietChart.class);
                startActivity(intent);
            }
        });


        mDBHelper = new SQLiteHelper(this);
        mDietDataSource = new DietDataSource(this);

        List<DietChart> allDailyDietChart = mDietDataSource
                .getTodayDietChart();

        DailyProfileViewAdapter arrayAdapterOne = new DailyProfileViewAdapter(
                this, allDailyDietChart);

        // adding it to the list view.
        mListViewOne = (ListView) findViewById(R.id.lvTodayDietChart);
        mListViewOne.setAdapter(arrayAdapterOne);

        List<String> upcomingDates = mDietDataSource.getUpcomingDates();
        // DailyProfileViewThreeAdapter arrayAdapterTwo = new
        // DailyProfileViewThreeAdapter(this, allDailyDietChart);
        ArrayAdapter<String> mDatesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, upcomingDates);
        // adding it to the list view.
        mListViewTwo = (ListView) findViewById(R.id.lvUpComingDietChart);
        mListViewTwo.setAdapter(mDatesAdapter);

        mListViewTwo.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {

                tvDate = (TextView) view.findViewById(android.R.id.text1);
                String dateValue = tvDate.getText().toString(); // get date



                Intent mPreviewIntent = new Intent(DietListTodayAndUpcoming.this,
                        DailyDietChartView.class);
                mPreviewIntent.putExtra(FTFLConstants.ACTIVITYDATE, dateValue);

                startActivity(mPreviewIntent);


            }
        });

    }



//    public void passData(String data) {
//        DietListTodayAndUpcoming fragmentB = new DietListTodayAndUpcoming ();
//        Bundle args = new Bundle();
//        args.putString(FTFLConstants.ACTIVITYDATE, data);
//        fragmentB .setArguments(args);
//        getFragmentManager().beginTransaction()
//                .replace(R.id.frame_container, fragmentB )
//                .commit();
//    }
//    private void displayView() {
//        // update the main content by replacing fragments
//        Fragment fragment = null;
//
//        fragment = new DailyDietChartView();
//        if (fragment != null) {
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.frame_container, fragment).commit();
//
//
//        }


    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_diet_list_today_and_upcoming, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.history:
//                startActivity(new Intent(DietListTodayAndUpcoming.this,
//                        DietChartHistory.class));
//                return true;
//            case R.id.createDiet:
//                startActivity(new Intent(DietListTodayAndUpcoming.this,
//                        CreateDietChart.class));
//                return true;

            case R.id.home:
                startActivity(new Intent(DietListTodayAndUpcoming.this,
                        MainActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}