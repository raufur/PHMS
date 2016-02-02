package com.epsilon.coders.phms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.databases.NoteDataSource;
import com.epsilon.coders.phms.model.Note;

import java.util.ArrayList;
import java.util.List;

public class ImportantNoteActivity extends AppCompatActivity {

    ListView LV;


    NoteDataSource database;
    List<Note> noteInfo;
    ArrayList<String> allnote;
    TextView displaycareListNotification;
    ImageButton addNoteBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        init();
    }

    public void init() {



        addNoteBtn=(ImageButton)findViewById(R.id.btAddNote);


        database = new NoteDataSource(this);

        displaycareListNotification=(TextView)findViewById(R.id.notification);


        allnote = new ArrayList<String>();

        // arrayListEmployee = new ArrayList<Employee>();
        noteInfo = database.getNoteList();

        if (!noteInfo.isEmpty()) {
            //Toast.makeText(getApplicationContext(), String.valueOf(arrayListDoctor), Toast.LENGTH_LONG).show();
            LV = (ListView)findViewById(R.id.noteList);
            noteInfo=database.getNoteList();

            // use the SimpleCursorAdapter to show the
            // elements in a ListView
            ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this,
                    android.R.layout.simple_list_item_1, noteInfo);
            LV.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }else {
            displaycareListNotification.setText("There are no Note; please add note");
        }

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImportantNoteActivity.this, CreateNoteActivity.class);
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

