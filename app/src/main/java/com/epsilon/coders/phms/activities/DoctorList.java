package com.epsilon.coders.phms.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.databases.DoctorProfileDataSource;
import com.epsilon.coders.phms.model.DoctorProfile;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Prodip on 6/21/2015.
 */
public class DoctorList extends AppCompatActivity {

    ListView lstView;
    DoctorProfileDataSource database;
    List<DoctorProfile> arrayListDoctor;
    ArrayList<String> allDoctor;
    DoctorProfile doctorProfile;
    ArrayAdapter<DoctorProfile> adapter;
    List<DoctorProfile> values;


    ImageButton AddNewDoctor;
    TextView displayDocListNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

    }

    private void init() {
        database = new DoctorProfileDataSource(this);

        displayDocListNotification=(TextView)findViewById(R.id.displayDocListNotification);


        allDoctor = new ArrayList<String>();

        // arrayListEmployee = new ArrayList<Employee>();
        arrayListDoctor = database.getAllDoctors();

        if (!arrayListDoctor.isEmpty()) {
            //Toast.makeText(getApplicationContext(), String.valueOf(arrayListDoctor), Toast.LENGTH_LONG).show();
            lstView = (ListView) findViewById(R.id.LV);
/*
        for (int i = 0; i > arrayListDoctor.size(); i++) {

            allDoctor.add(arrayListDoctor.get(i).toString());
        }

        lstView = (ListView) findViewById(R.id.LV);

       DoctorAdapter doctorAdapter = new DoctorAdapter(this, arrayListDoctor);
        lstView.setAdapter(doctorAdapter);
    */

            arrayListDoctor = database.getAllDoctors();

            // use the SimpleCursorAdapter to show the
            // elements in a ListView
            adapter = new ArrayAdapter<DoctorProfile>(this,
                    android.R.layout.simple_list_item_1, arrayListDoctor);
            lstView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }else {
            displayDocListNotification.setText("There are not Doctor Information Add doctor Information");
        }


        AddNewDoctor= (ImageButton)findViewById(R.id.AddNewDoctor);
        AddNewDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //displayView();
                Intent intent=new Intent(DoctorList.this,DoctorProfileCreate.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        if (v.getId()== R.id.LV){
            AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)menuInfo;
            doctorProfile=adapter.getItem(info.position);
            menu.setHeaderTitle(doctorProfile.getDname());
            menu.add(0, v.getId(), 0, "Call");
            menu.add(0, v.getId(), 0, "Send SMS");
            menu.add(0, v.getId(), 0, "View Details");
            menu.add(0, v.getId(), 0, "Delete");
           // menu.add(0, v.getId(), 0, "Create Diet Chart");



           /* String[] menuItems = getResources().getStringArray(R.array.menu);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }*/

        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        try {
            doctorProfile=adapter.getItem(info.position);

            if (item.getTitle()=="Send SMS"){
                Toast.makeText(this, "send sms", Toast.LENGTH_LONG).show();
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", doctorProfile.getChamber());
                startActivity(smsIntent);
            }else if (item.getTitle()=="Call"){

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + doctorProfile.getPhone()));
                startActivity(callIntent);
                Toast.makeText(this,"Call",Toast.LENGTH_LONG).show();
            }

            else if (item.getTitle()=="Delete") {


                database.deleteDoctorData(doctorProfile.getId());
                adapter = new ArrayAdapter<DoctorProfile>(this,
                        android.R.layout.simple_list_item_1, arrayListDoctor);
                lstView.setAdapter(adapter);
                registerForContextMenu(lstView);
                adapter.notifyDataSetChanged();

            }
            else if (item.getTitle()=="View Details") {


                String dName = adapter.getItem(info.position).getDname();
                String dQualification =adapter.getItem(info.position).getQualification();
                String dDesignation = adapter.getItem(info.position).getDesignation();
                String dExpertise =adapter.getItem(info.position).getExpertise();
                String dOrganization = adapter.getItem(info.position).getOrganization();
                String dChamber = adapter.getItem(info.position).getChamber();
                String dVisitingHours = adapter.getItem(info.position).getVisitinghours();
                String dLocation = adapter.getItem(info.position).getLocation();
                String dPhone = adapter.getItem(info.position).getPhone();
                String dEmail = adapter.getItem(info.position).getEmail();
                Intent i=new Intent(this,DisplayFamilyDetails.class);
                i.putExtra("dname", dName);
                i.putExtra("qualification", dQualification);
                i.putExtra("designation", dDesignation);
                i.putExtra("expertise", dExpertise);
                i.putExtra("organization", dOrganization);
                i.putExtra("chamber", dChamber);
                i.putExtra("visitinghours", dVisitingHours);
                i.putExtra("location", dLocation);
                i.putExtra("phone", dPhone);
                i.putExtra("email", dEmail);
                startActivity(i);


            }
//            else if (item.getTitle()=="Create Diet Chart") {
//                String FName = adapter.getItem(info.position).getDname();
//                Intent i=new Intent(this, DietListTodayAndUpcoming.class);
//                //   i.putExtra("FName", FName);
//                startActivity(i);
//            }
            else
            {
                return false;}
        }catch (Exception e){
            e.getMessage();
            return true;
        }


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
                startActivity(new Intent(DoctorList.this,
                        MainActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    }





