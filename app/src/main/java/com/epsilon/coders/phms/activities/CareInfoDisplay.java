package com.epsilon.coders.phms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.databases.CareInfoDataSource;
import com.epsilon.coders.phms.model.CareInfo;

import java.util.ArrayList;
import java.util.List;


public class CareInfoDisplay extends AppCompatActivity {


    ListView LV;


    CareInfoDataSource database;
    List<CareInfo> careInfo;
    ArrayList<String> allCare;
    TextView displaycareListNotification;
    ImageButton addCareBtn;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_info_display);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        init();
    }



    private void init() {

        addCareBtn=(ImageButton)findViewById(R.id.AddNewCare);


        database = new CareInfoDataSource(this);

        displaycareListNotification=(TextView)findViewById(R.id.notification);


        allCare = new ArrayList<String>();

        // arrayListEmployee = new ArrayList<Employee>();
        careInfo = database.getAllCareInfo();

        if (!careInfo.isEmpty()) {
            //Toast.makeText(getApplicationContext(), String.valueOf(arrayListDoctor), Toast.LENGTH_LONG).show();
            LV = (ListView)findViewById(R.id.LV);




            // use the SimpleCursorAdapter to show the
            // elements in a ListView
            ArrayAdapter<CareInfo> adapter = new ArrayAdapter<CareInfo>(this,
                    android.R.layout.simple_list_item_1, careInfo);
            LV.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }else {
            displaycareListNotification.setText("There are no Care information; please add care Information");
        }

        addCareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CareInfoDisplay.this, AddCareInfo.class);
                startActivity(intent);
            }
        });

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
