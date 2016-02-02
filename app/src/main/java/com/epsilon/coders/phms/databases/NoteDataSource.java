package com.epsilon.coders.phms.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.epsilon.coders.phms.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mobile App Develop on 27-6-15.
 */
public class NoteDataSource {
    // Database fields
    private SQLiteDatabase iCareDatabase;
    private SQLiteHelper sqlHelper;
    List<Note> noteList = new ArrayList<Note>();

    public NoteDataSource(Context context) {
        sqlHelper = new SQLiteHelper(context);
    }

    /*
     * open a method for writable database
     */
    public void open() throws SQLException {
        iCareDatabase = sqlHelper.getWritableDatabase();
    }

    public void read() throws SQLException {
        iCareDatabase = sqlHelper.getReadableDatabase();
    }
    /*
     * close database connection
     */
    public void close() {
        sqlHelper.close();
    }

	/*
	 * insert data into the database.
	 */

    public boolean insert(String title, String description, String date, String time, Integer id) {

        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.COL_NOTE_TBL_NAME, title);
        contentValues.put(SQLiteHelper.COL_NOTE_TBL_DETAIL, description);
        contentValues.put(SQLiteHelper.COL_NOTE_TBL_DATE, date);
        contentValues.put(SQLiteHelper.COL_NOTE_TBL_TIME, time);
        contentValues.put(SQLiteHelper.COL_NOTE_TBL_USER_ID,id);
        iCareDatabase.insert(SQLiteHelper.I_CARE_NOTE_TBL, null, contentValues);
        return true;
    }

    // Updating database by id
    public boolean updateData(long noteId, Note note) {
        this.open();
        ContentValues cvUpdate = new ContentValues();

        cvUpdate.put(SQLiteHelper.COL_NOTE_TBL_NAME,
                note.getNotename());

        cvUpdate.put(SQLiteHelper.COL_NOTE_TBL_DETAIL,
                note.getNotedetail());

        int check = iCareDatabase.update(SQLiteHelper.I_CARE_NOTE_TBL,
                cvUpdate, SQLiteHelper.COL_NOTE_TBL_ID + "="
                        + noteId, null);

        this.close();
        if (check == 0)
            return false;
        else
            return true;
    }

    // Getting Note
    public List<Note> getNoteList() {
        List<Note> NoteList = new ArrayList<Note>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + SQLiteHelper.I_CARE_NOTE_TBL;


        iCareDatabase = sqlHelper.getWritableDatabase();
        Cursor cursor = iCareDatabase .rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++){
                Note note = new Note();
                note.setId((cursor.getInt(0)));
                note.setNotename(cursor.getString(1));
                note.setNotedetail(cursor.getString(2));
                note.setNotedate(cursor.getString(3));
               // note.setNotetime(cursor.getString(4));


                NoteList.add(note);
                cursor.moveToNext();
            }
        }

        // return contact list
        return NoteList;
    }



}


