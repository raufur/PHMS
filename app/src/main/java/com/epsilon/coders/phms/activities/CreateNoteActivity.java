package com.epsilon.coders.phms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.databases.ImportantNoteDataSource;
import com.epsilon.coders.phms.model.ImportantNote;

public class CreateNoteActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etTitle, etDate, etDescription;
    String Title, Date, Description;
    Button save;

    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//

        init();
    }

    public void init() {

        etTitle = (EditText)findViewById(R.id.etNoteTytle);
        etDate = (EditText)findViewById(R.id.etDate);
        etDescription = (EditText) findViewById(R.id.etDescription);


        save = (Button) findViewById(R.id.btnNoteSave);
        save.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {


        Title = etTitle.getText().toString();
        Date = etDate.getText().toString();
        Description = etDescription.getText().toString();


        ImportantNote importantNote= new ImportantNote();
        importantNote.setTitle(Title);
        importantNote.setDate(Date);
        importantNote.setDescription(Description);

        ImportantNoteDataSource importantNoteDataSource=new ImportantNoteDataSource(this);


        if (importantNoteDataSource.insert(importantNote) == true) {
            Intent intent=new Intent(CreateNoteActivity.this,ImportantNoteActivity.class);
            startActivity(intent);
            Toast toast = Toast.makeText(this, "Saved Successfully", Toast.LENGTH_LONG);
            toast.show();


        } else {
            Toast  toast = Toast.makeText(this, "not Saved", Toast.LENGTH_LONG);
            toast.show();
        }

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
