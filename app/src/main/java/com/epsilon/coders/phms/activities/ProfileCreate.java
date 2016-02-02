package com.epsilon.coders.phms.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.databases.ProfileDataSource;
import com.epsilon.coders.phms.model.Profile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ProfileCreate extends Activity implements
        OnClickListener {
    Button btnSave = null;
    ImageView btnImage=null;
    EditText etName = null;
    EditText etAge = null;
    Spinner etBloodGroup = null;
    EditText etWeight = null;
    EditText etHeight = null;
    EditText etDateOfBirth = null;
    EditText etSpecialNotes = null;
    Toast toast = null;

    String mName = "";
    String mAge = "";
    String mBloodGroup = "";
    String mWeight = "";
    String mHeight = "";
    String mDateOfBirth = "";
    String mSpecialNotes = "";
    byte[] image;


    ProfileDataSource icareDS;
    private int year;
    private int month;
    private int day;

    static final int DATE_PICKER_ID = 1111;

    Integer profileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_create);

//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#22362D"));
//        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        // set the text field id to the variable.
        init();

        addItemsOnSpinner2();
        addListenerOnSpinnerItemSelection();

    }

    private void init() {


        etName = (EditText) findViewById(R.id.createUserName);
        etAge = (EditText) findViewById(R.id.createAge);
        etBloodGroup = (Spinner) findViewById(R.id.createBloodGroup);
        etWeight = (EditText) findViewById(R.id.createWeight);
        etHeight = (EditText) findViewById(R.id.createHeight);

        etDateOfBirth = (EditText) findViewById(R.id.createDateOfBirth);
        etDateOfBirth.setOnClickListener(this);

        etSpecialNotes = (EditText) findViewById(R.id.createSpecialComment);
        btnImage=(ImageView) findViewById(R.id.imageViewCreate);
        btnImage.setOnClickListener(this);



        btnSave = (Button) findViewById(R.id.Save);
        btnSave.setOnClickListener(this);


    }

    public void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileCreate.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select File"), 1);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                btnImage.setImageBitmap(thumbnail);
            } else if (requestCode == 1) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                Cursor cursor = managedQuery(selectedImageUri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                btnImage.setImageBitmap(bm);
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG,100,stream);
                image=stream.toByteArray();

            }
        }
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
        etBloodGroup = (Spinner) findViewById(R.id.createBloodGroup);
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
            case R.id.Save:
                save();

                break;
            case R.id.createDateOfBirth:
                final Calendar c = Calendar.getInstance();
                year  = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day   = c.get(Calendar.DAY_OF_MONTH);
                showDialog(DATE_PICKER_ID);
                break;
            case R.id.imageViewCreate:
               selectImage();

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
//
//        Intent intent=getIntent();
//        profileId=intent.getIntExtra("profile_id",0);


       // icareDS=new ProfileDataSource(getApplicationContext());

//        HashMap<String, String> map = new HashMap<String, String>();
//        map= icareDS.getAboutOneProfile(profileId);
//        Log.v("map value", map.toString());


        mName = etName.getText().toString();
        mAge = etAge.getText().toString();
        mBloodGroup = String.valueOf(etBloodGroup.getSelectedItem());
        mWeight = etWeight.getText().toString();
        mHeight = etHeight.getText().toString();
        mDateOfBirth = etDateOfBirth.getText().toString();
        mSpecialNotes = etSpecialNotes.getText().toString();


        // Assign values in the ICareProfile
        Profile profileDataInsert = new Profile(mName,mAge,mBloodGroup,mWeight,mHeight,mDateOfBirth,mSpecialNotes,image);
        profileDataInsert.setName(mName);
        profileDataInsert.setAge(mAge);
        profileDataInsert.setBloodGroup(mBloodGroup);
        profileDataInsert.setWeight(mWeight);
        profileDataInsert.setHeight(mHeight);
        profileDataInsert.setDateOfBirth(mDateOfBirth);
        profileDataInsert.setSpecialNotes(mSpecialNotes);
        profileDataInsert.setImage(image);

		/*
		 * if update is needed then update otherwise submit
		 */
        icareDS = new ProfileDataSource(getBaseContext());
        if (icareDS.insert(profileDataInsert)) {
            toast = Toast.makeText(this, getString(R.string.successfullySaved),
                    Toast.LENGTH_LONG);
            toast.show();
            startActivity(new Intent(ProfileCreate.this,
                    MainActivity.class));
        } else {
            toast = Toast.makeText(this, getString(R.string.notSaved), Toast.LENGTH_LONG);
            toast.show();
        }

    }
}
