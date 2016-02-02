package com.epsilon.coders.phms.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.databases.CareInfoDataSource;
import com.epsilon.coders.phms.model.CareInfo;


/**
 * Created by Mobile App Develop on 24-6-15.
 */
public class AddCareInfo extends AppCompatActivity {

    private Toolbar toolbar;

    EditText etName;
    EditText etAddress;
    EditText etPhone;
    EditText etEmail;
    Button careSave;

    private String careName;
    private String careAddress;
    private String contactNumber;
    private String email;
    CareInfoDataSource careInfoDataSource = null;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_care_info);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        init();
    }

    public void saveCareInfo() {
        careName = etName.getText().toString();
        careAddress = etAddress.getText().toString();
        contactNumber = etPhone.getText().toString();

        email = etEmail.getText().toString();

        CareInfo careInfoInsert = new CareInfo();
        careInfoInsert.setCareName(careName);
        careInfoInsert.setCareAddress(careAddress);
        careInfoInsert.setContactNumber(contactNumber);

        careInfoInsert.setCareEmail(email);

        careInfoDataSource = new CareInfoDataSource(this);
        if (careInfoDataSource.insert(careInfoInsert) == true) {
            toast = Toast.makeText(this, "Saved Successfully", Toast.LENGTH_LONG);
            toast.show();


/*

            Intent intent = new Intent(getActivity(), Home.class);
            startActivity(intent);
*/

        } else {
            toast = Toast.makeText(this, "not Saved", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public  void  init( ){
        etName = (EditText) findViewById(R.id.editTextCareName);
        etAddress = (EditText) findViewById(R.id.editTextCareAddress);
        etPhone = (EditText) findViewById(R.id.editTextCarePhone);
        etEmail = (EditText) findViewById(R.id.editTextCareEmail);
        careSave = (Button) findViewById(R.id.buttonCareSave);

        careSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCareInfo();
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