package com.epsilon.coders.phms.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.adapters.DailyProfileViewAdapter;
import com.epsilon.coders.phms.databases.DietDataSource;
import com.epsilon.coders.phms.databases.SQLiteHelper;
import com.epsilon.coders.phms.model.DietChart;

import java.util.List;


public class DailyDietChartView extends AppCompatActivity {
	ListView mListView = null;
	DietDataSource mDietDataSource = null;
	SQLiteHelper mDBHelper = null;
	DietChart dailyDietChart = null;
	int id_To_Update = 0;
	String mDate = "";
	TextView textDate;
	String mId = "";
	TextView mId_tv = null;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_diet_chart_view);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        init();
    }

    private void init() {

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
        mDietDataSource = new DietDataSource(this);
        textDate = (TextView) findViewById(R.id.textDailyDietChartDate);

//        Bundle bundle = this.getA();
//        if (bundle != null) {
//            mDate = bundle.getString(FTFLConstants.ACTIVITYDATE);
//
//            Toast.makeText(this, mDate, Toast.LENGTH_LONG).show();
//        }

      /*  Intent mIntent = getActivity().getIntent();
        mDate = mIntent.getStringExtra(FTFLConstants.ACTIVITYDATE);*/

        textDate.setText(mDate);
        List<DietChart> allDailyDietChart = mDietDataSource
                .getDailyDietChart(mDate);

        DailyProfileViewAdapter arrayAdapter = new DailyProfileViewAdapter(
                this, allDailyDietChart);

        // adding it to the list view.
        mListView = (ListView) findViewById(R.id.lvDailyDietChart);
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

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            mDate = bundle.getString(FTFLConstants.ACTIVITYDATE);
//
//            Toast.makeText(this, mDate, Toast.LENGTH_LONG).show();
//        }
//    }


	public void editData(String eId) {
		Intent mEditIntent = new Intent(this,
				CreateDietChart.class);
		mEditIntent.putExtra("FTFLConstants.ACTIVITYID", eId);
		// startActivity(mEditIntent);
		startActivityForResult(mEditIntent, 2);
	}
//    private void displayView() {
//        // update the main content by replacing fragments
//        Fragment fragment = null;
//
//        fragment = new CreateDietChart();
//        if (fragment != null) {
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.frame_container, fragment).commit();
//
//
//        }
//    }
	public void deleteData(String eId) {
		mDietDataSource = new DietDataSource(this);
		Long lId = Long.parseLong(eId);
		mDietDataSource.deleteData(lId);

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
