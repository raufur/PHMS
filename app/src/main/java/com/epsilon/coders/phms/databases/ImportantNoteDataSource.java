package com.epsilon.coders.phms.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.epsilon.coders.phms.model.ImportantNote;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tariqul on 6/29/2015.
 */
public class ImportantNoteDataSource {
    private SQLiteDatabase iCareDatabase;
    private SQLiteHelper sqlHelper;
    List<ImportantNote> noteList = new ArrayList<ImportantNote>();

    public ImportantNoteDataSource(Context context) {
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

    public boolean insert(ImportantNote note) {

        this.open();

        ContentValues cvInsert = new ContentValues();

        cvInsert.put(SQLiteHelper.KEY_NOTE_TITLE,
                note.getTitle());

        cvInsert.put(SQLiteHelper.KEY_TITLE_DATE,
                note.getDate());


        cvInsert.put(SQLiteHelper.KEY_TITLE_DESCRIPTION,
                note.getDescription());



        long check = iCareDatabase.insert(
                SQLiteHelper.TBL_NOTE, null, cvInsert);
        this.close();
        if (check < 0)
            return false;
        else
            return true;
    }

   /* // Updating database by id
    public boolean updateData(long noteId, ImportantNote note) {
        this.open();
        ContentValues cvUpdate = new ContentValues();

        cvUpdate.put(SQLiteHelper.KEY_NOTE_TITLE,
                note.getTitle());

        cvUpdate.put(SQLiteHelper.KEY_TITLE_DATE,
                note.getDate());
        cvUpdate.put(SQLiteHelper.KEY_TITLE_DESCRIPTION,
                note.getDescription());

        int check = iCareDatabase.update(SQLiteHelper.TBL_NOTE,
                cvUpdate, SQLiteHelper.COL_NOTE_TBL_ID + "="
                        + noteId, null);

        this.close();
        if (check == 0)
            return false;
        else
            return true;
    }
*/
    // Getting Note
    public List<ImportantNote> getNoteList() {
        List<ImportantNote> NoteList = new ArrayList<ImportantNote>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + SQLiteHelper.TBL_NOTE;


        iCareDatabase = sqlHelper.getWritableDatabase();
        Cursor cursor = iCareDatabase .rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ImportantNote note = new ImportantNote();
                note.setKey(Integer.parseInt((cursor.getString(0))));
                note.setTitle(cursor.getString(1));
                note.setDate(cursor.getString(2));

                note.setDescription(cursor.getString(3));


                // Adding contact to list
                NoteList.add(note);
            } while (cursor.moveToNext());
        }

        // return contact list
        return NoteList;
    }

}
