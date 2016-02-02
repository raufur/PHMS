package com.epsilon.coders.phms.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.epsilon.coders.phms.databases.FamilyProfileDataSource;
import com.epsilon.coders.phms.model.FamilyProfileClass;

import java.util.ArrayList;
import java.util.List;

public class DisplayAllFamilyProfile extends AppCompatActivity {


    ListView lstView;
    FamilyProfileDataSource database;
    List<FamilyProfileClass> familyProfileClassList;
    ArrayList<String> allProfiles;

    ImageButton ivAddFamily;

    private Toolbar toolbar;


    FamilyProfileClass familyProfileClass;
    TextView displayFamilyListNotification;

    ArrayAdapter<FamilyProfileClass> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_all_family_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#22362D"));
//        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        init();
    }


    private void init() {

        ivAddFamily=(ImageButton)findViewById(R.id.ivAddFamily);
        ivAddFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayAllFamilyProfile.this, FamilyProfile.class);
                startActivity(intent);
            }
        });

        database = new FamilyProfileDataSource(this);

        displayFamilyListNotification = (TextView) findViewById(R.id.tvNotification);


        allProfiles = new ArrayList<String>();

        // arrayListEmployee = new ArrayList<Employee>();
        familyProfileClassList = database.getAllFamilyProfile();

        if (!familyProfileClassList.isEmpty()) {
            //Toast.makeText(getApplicationContext(), String.valueOf(arrayListDoctor), Toast.LENGTH_LONG).show();
            lstView = (ListView) findViewById(R.id.FamilyLV);

            familyProfileClassList = database.getAllFamilyProfile();

            // use the SimpleCursorAdapter to show the
            // elements in a ListView
            adapter = new ArrayAdapter<FamilyProfileClass>(this,
                    android.R.layout.simple_list_item_1, familyProfileClassList);
            lstView.setAdapter(adapter);
            registerForContextMenu(lstView);
            adapter.notifyDataSetChanged();
        } else {
            displayFamilyListNotification.setText("There are no Family Profile; Please add Family Profile");
        }
    }




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        if (v.getId()== R.id.FamilyLV){
            AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)menuInfo;
            familyProfileClass=adapter.getItem(info.position);
            menu.setHeaderTitle(familyProfileClass.getFname());
            menu.add(0, v.getId(), 0, "Call");
            menu.add(0, v.getId(), 0, "Send SMS");
            menu.add(0, v.getId(), 0, "View Details");
            menu.add(0, v.getId(), 0, "Create Diet Chart");
            menu.add(0, v.getId(), 0, "Delete");



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
            familyProfileClass=adapter.getItem(info.position);

            if (item.getTitle()=="Send SMS"){
                Toast.makeText(this, "send sms", Toast.LENGTH_LONG).show();
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", familyProfileClass.getFNumber());
                startActivity(smsIntent);
            }else if (item.getTitle()=="Call"){

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + familyProfileClass.getFNumber()));
               // startActivity(callIntent);
                Toast.makeText(this,"Call",Toast.LENGTH_LONG).show();
            }

            else if (item.getTitle()=="Delete") {


                database.deleteFamilyData(familyProfileClass.getFid());
                adapter = new ArrayAdapter<FamilyProfileClass>(this,
                        android.R.layout.simple_list_item_1, familyProfileClassList);
                lstView.setAdapter(adapter);
                registerForContextMenu(lstView);
                adapter.notifyDataSetChanged();

            }
            else if (item.getTitle()=="View Details") {


                String FName = adapter.getItem(info.position).getFname();
                String FNumber =adapter.getItem(info.position).getFNumber();
                String FRelationship = adapter.getItem(info.position).getFrelationship();
                String Fage =adapter.getItem(info.position).getFage();
                String FDateOfBirth = adapter.getItem(info.position).getFdateOfBirth();
                String FbloodGroup = adapter.getItem(info.position).getFbloodGroup();
                String FHeight = adapter.getItem(info.position).getFheight();
                String FWeight = adapter.getItem(info.position).getFweight();
                String FspecialNotes = adapter.getItem(info.position).getFspecialNotes();
                Intent i=new Intent(this,DisplayFamilyDetails.class);
                i.putExtra("FName", FName);
                i.putExtra("FNumber", FNumber);
                i.putExtra("FRelationship", FRelationship);
                i.putExtra("Fage", Fage);
                i.putExtra("FDateOfBirth", FDateOfBirth);
                i.putExtra("FbloodGroup", FbloodGroup);
                i.putExtra("FHeight", FHeight);
                i.putExtra("FWeight", FWeight);
                i.putExtra("FspecialNotes", FspecialNotes);
                startActivity(i);


            }
            else if (item.getTitle()=="Create Diet Chart") {
                String FName = adapter.getItem(info.position).getFname();
                Intent i=new Intent(this, DietListTodayAndUpcoming.class);
                //   i.putExtra("FName", FName);
                startActivity(i);
            }
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
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }





}
