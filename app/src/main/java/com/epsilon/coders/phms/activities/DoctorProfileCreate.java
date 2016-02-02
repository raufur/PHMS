package com.epsilon.coders.phms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.databases.DoctorProfileDataSource;
import com.epsilon.coders.phms.model.DoctorProfile;


/**
 * Created by Mobile App Develop on 20-6-15.
 */
public class DoctorProfileCreate extends AppCompatActivity implements
        View.OnClickListener {

    EditText etName;
    EditText etQualification;
    EditText etDesignation;
    EditText etExpertise;
    EditText etOrganization;
    EditText etChamber;
    EditText etVisitingHours;
    EditText etLocation;
    EditText etPhone;
    EditText etEmail;
    Toast toast;
    Button btnSave;
    DoctorProfileDataSource doctorProfileDataSource = null;


    String dname=" ";
    String qualification=" ";
    String designation=" ";
    String expertise=" ";
    String organization=" ";
    String chamber=" ";
    String visitinghours=" ";
    String location=" ";
    String phone=" ";
    String email=" ";
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_profile_create);

        init();

    }

    public void init() {

        etName = (EditText) findViewById(R.id.editTextDoctorName);
        etQualification = (EditText) findViewById(R.id.editTextQualification);
        etDesignation = (EditText) findViewById(R.id.editTextDesignation);
        etExpertise = (EditText) findViewById(R.id.editTextExpertise);
        etOrganization = (EditText)findViewById(R.id.editTextOrganization);
        etChamber = (EditText) findViewById(R.id.editTextChamber);
        etVisitingHours = (EditText) findViewById(R.id.editTextVisitingHours);
        etLocation = (EditText) findViewById(R.id.editTextLocation);
        etPhone = (EditText) findViewById(R.id.editTextPhone);
        etEmail = (EditText)findViewById(R.id.editTextEmail);
        btnSave = (Button) findViewById(R.id.btndoctorsave);
        btnSave.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {


        dname = etName.getText().toString();
        qualification = etQualification.getText().toString();
        designation = etDesignation.getText().toString();
        expertise = etExpertise.getText().toString();
        organization = etOrganization.getText().toString();
        chamber = etChamber.getText().toString();
        visitinghours = etVisitingHours.getText().toString();
        location = etLocation.getText().toString();
        phone = etPhone.getText().toString();
        email = etEmail.getText().toString();

        DoctorProfile doctorProfileDataInsert = new DoctorProfile();
        doctorProfileDataInsert.setDname(dname);
        doctorProfileDataInsert.setQualification(qualification);
        doctorProfileDataInsert.setDesignation(designation);
        doctorProfileDataInsert.setExpertise(expertise);
        doctorProfileDataInsert.setOrganization(organization);
        doctorProfileDataInsert.setChamber(chamber);
        doctorProfileDataInsert.setVisitinghours(visitinghours);
        doctorProfileDataInsert.setLocation(location);
        doctorProfileDataInsert.setPhone(phone);
        doctorProfileDataInsert.setEmail(email);

        doctorProfileDataSource = new DoctorProfileDataSource(this);
        if (doctorProfileDataSource.insert(doctorProfileDataInsert) == true) {
            Intent intent=new Intent(DoctorProfileCreate.this,DoctorList.class);
            startActivity(intent);
            toast = Toast.makeText(DoctorProfileCreate.this, "Saved Successfully", Toast.LENGTH_LONG);
            toast.show();

            //displayView();

        } else {
            toast = Toast.makeText(this, "not Saved", Toast.LENGTH_LONG);
            toast.show();
        }

    }
}

