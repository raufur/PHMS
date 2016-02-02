package com.epsilon.coders.phms.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.databases.FamilyProfileDataSource;
import com.epsilon.coders.phms.model.FamilyProfileClass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FamilyProfile extends AppCompatActivity implements View.OnClickListener {
    Button btnSave = null;
    EditText etName = null;
    EditText etNumber = null;
    EditText etRelation = null;
    EditText etAge = null;
    Spinner etBloodGroup = null;
    EditText etWeight = null;
    EditText etHeight = null;
    EditText etDateOfBirth = null;
    EditText etSpecialNotes = null;
    Toast toast = null;

    String mName = "";
    String relation="";
    String mAge = "";
    String mBloodGroup = "";
    String mWeight = "";
    String mHeight = "";
    String mDateOfBirth = "";
    String mSpecialNotes = "";
    String mNumber="";


    FamilyProfileDataSource profileDataSource = null;
    private int year;
    private int month;
    private int day;

    static final int DATE_PICKER_ID = 1111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_profile);

//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#22362D"));
//        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        // set the text field id to the variable.
        init();

        addItemsOnSpinner2();
        addListenerOnSpinnerItemSelection();


    }
    private void init() {
        etName = (EditText) findViewById(R.id.FcreateUserName);
        etNumber = (EditText) findViewById(R.id.FNumber);
        etRelation=(EditText)findViewById(R.id.FRelationWithUser);
        etAge = (EditText) findViewById(R.id.FcreateAge);
        etBloodGroup = (Spinner) findViewById(R.id.FcreateBloodGroup);
        etWeight = (EditText) findViewById(R.id.FcreateWeight);
        etHeight = (EditText) findViewById(R.id.FcreateHeight);

        etDateOfBirth = (EditText) findViewById(R.id.FcreateDateOfBirth);
        etDateOfBirth.setOnClickListener(this);

        etSpecialNotes = (EditText) findViewById(R.id.FcreateSpecialComment);
        btnSave = (Button) findViewById(R.id.FSave);
        btnSave.setOnClickListener(this);


    }

    public void addItemsOnSpinner2() {


        List<String> list = new ArrayList<String>();
        list.add("A+");
        list.add("B+");
        list.add("O+");
        list.add("AB+");
        list.add("A-");
        list.add("B-");
        list.add("O-");
        list.add("AB-");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etBloodGroup.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        etBloodGroup = (Spinner) findViewById(R.id.FcreateBloodGroup);
        // etBloodGroup.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.icare, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.FSave:
                save();

                break;
            case R.id.FcreateDateOfBirth:
                final Calendar c = Calendar.getInstance();
                year  = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day   = c.get(Calendar.DAY_OF_MONTH);
                showDialog(DATE_PICKER_ID);
                break;
        }



    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:


                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            // Show selected date
            etDateOfBirth.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };


    public void save(){
        mName = etName.getText().toString();
        mNumber=etNumber.getText().toString();
        relation = etRelation.getText().toString();
        mAge = etAge.getText().toString();
        mBloodGroup = String.valueOf(etBloodGroup.getSelectedItem());
        mWeight = etWeight.getText().toString();
        mHeight = etHeight.getText().toString();
        mDateOfBirth = etDateOfBirth.getText().toString();
        mSpecialNotes = etSpecialNotes.getText().toString();

        // Assign values in the ICareProfile
        FamilyProfileClass profileDataInsert = new FamilyProfileClass();
        profileDataInsert.setFname(mName);
        profileDataInsert.setFNumber(mNumber);
        profileDataInsert.setFrelationship(mName);
        profileDataInsert.setFage(mAge);
        profileDataInsert.setFbloodGroup(mBloodGroup);
        profileDataInsert.setFweight(mWeight);
        profileDataInsert.setFheight(mHeight);
        profileDataInsert.setFdateOfBirth(mDateOfBirth);
        profileDataInsert.setFspecialNotes(mSpecialNotes);

		/*
		 * if update is needed then update otherwise submit
		 */
        profileDataSource = new FamilyProfileDataSource(this);
        if (profileDataSource.insert(profileDataInsert) == true) {
            toast = Toast.makeText(this, getString(R.string.successfullySaved),
                    Toast.LENGTH_LONG);
            toast.show();
            startActivity(new Intent(FamilyProfile.this,
                    DisplayAllFamilyProfile.class));
        } else {
            toast = Toast.makeText(this, getString(R.string.notSaved), Toast.LENGTH_LONG);
            toast.show();
        }

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_family_profile, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.Home) {
//
//            Intent intent= new Intent(this, MainActivity.class);
//            startActivity(intent);
//            return true;
//        }

//        return super.onOptionsItemSelected(item);
//    }
}
