package com.epsilon.coders.phms.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.epsilon.coders.phms.R;


public class DisplayFamilyDetails extends AppCompatActivity {

    EditText etName,etFNumber,etFRelationship,etFage,etFDateOfBirth,etFbloodGroup,etFHeight,etFWeight,etFspecialNotes;

    String FName,FNumber,FRelationship, Fage, FDateOfBirth,FbloodGroup,FHeight,FWeight,FspecialNotes;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_family_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#22362D"));
//        getSupportActionBar().setBackgroundDrawable(colorDrawable);

         FName = getIntent().getStringExtra("FName");
         FNumber = getIntent().getStringExtra("FNumber");
         FRelationship = getIntent().getStringExtra("FRelationship");
         Fage = getIntent().getStringExtra("Fage");
         FDateOfBirth = getIntent().getStringExtra("FDateOfBirth");
         FbloodGroup = getIntent().getStringExtra("FbloodGroup");
         FHeight = getIntent().getStringExtra("FHeight");
         FWeight = getIntent().getStringExtra("FWeight");
         FspecialNotes = getIntent().getStringExtra("FspecialNotes");

        init();


    }

    private void init() {
        etName=(EditText)findViewById(R.id.etFName);
        etFNumber=(EditText)findViewById(R.id.etFNumber);
        etFage=(EditText)findViewById(R.id.etFAge);
        etFRelationship=(EditText)findViewById(R.id.etFRelationship);
        etFDateOfBirth=(EditText)findViewById(R.id.etFDateOfBirth);
        etFbloodGroup=(EditText)findViewById(R.id.etFBloodGroup);
        etFHeight=(EditText)findViewById(R.id.etFHeight);
        etFWeight=(EditText)findViewById(R.id.etFWeight);
        etFspecialNotes=(EditText)findViewById(R.id.etFspecialNotes);

        etName.setText(FName);
        etFNumber.setText(FNumber);
        etFRelationship.setText(FRelationship);
        etFage.setText(Fage);
        etFDateOfBirth.setText(FDateOfBirth);
        etFbloodGroup.setText(FbloodGroup);
        etFHeight.setText(FHeight);
        etFWeight.setText(FWeight);
        etFspecialNotes.setText(FspecialNotes);
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
