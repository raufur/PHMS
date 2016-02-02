package com.epsilon.coders.phms.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.databases.ProfileDataSource;
import com.epsilon.coders.phms.model.Profile;
import com.epsilon.coders.phms.utills.AgeCalculator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ProfilePreviewUpdate extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;


    Button updateProfile ;
    ImageView btnImage=null;
    //Button home_btn ;
    EditText et_name = null;
    EditText et_age = null;
    EditText etBloodGroup = null;
    EditText et_weight = null;
    EditText et_height = null;
    EditText et_dateOfBirth = null;
    EditText et_specialNotes = null;
    //ImageView imageView=null;
    Toast toast = null;

    static int id;
    String mName = "";
    String mAge = "";
    String mBloodGroup = "";
    String mWeight = "";
    String mHeight = "";
    String mDateOfBirth;
    String mSpecialNotes = "";

    byte[] image=null;

    Profile mUpdateProfile = null;

    ProfileDataSource mDataSource = null;

    private int startYear=1980;
    private int startMonth=6;
    private int startDay=15;


    /// add new
    AgeCalculator ageCal2;


    static final int DATE_START_DIALOG_ID = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_preview_update);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

/// add new
        ageCal2=new AgeCalculator();




        init();


//        home_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                displayView();
//
//
//            }
//        });

       // return view;

    }

    private void init() {



        // set the textfield id to the variable.
        et_name = (EditText) findViewById(R.id.viewName);
        et_age = (EditText) findViewById(R.id.viewAge);
        etBloodGroup = (EditText) findViewById(R.id.viewBloodGroup);
        et_weight = (EditText) findViewById(R.id.viewWeight);
        et_height = (EditText) findViewById(R.id.viewHeight);
        et_dateOfBirth = (EditText) findViewById(R.id.viewDOB);
        et_specialNotes = (EditText) findViewById(R.id.viewSpComment);
        btnImage=(ImageView) findViewById(R.id.imageViewCreate);
        updateProfile = (Button) findViewById(R.id.updateProfile);
        updateProfile.setOnClickListener(mCorkyListener);
        //home_btn = (Button) view.findViewById(R.id.buttonHome);

//
//		 * get the profile which include all data from database according
//		 * profileId of the clicked item.


        mDataSource = new ProfileDataSource(this);
        mUpdateProfile = mDataSource.singleProfileData();

        if(mUpdateProfile !=null )
        {
            mName = mUpdateProfile.getName();
            // mAge = mUpdateProfile.getAge();
            mBloodGroup = mUpdateProfile.getBloodGroup();
            mWeight = mUpdateProfile.getWeight();
            mHeight = mUpdateProfile.getHeight();
            mDateOfBirth = mUpdateProfile.getDateOfBirth();
            mSpecialNotes = mUpdateProfile.getSpecialNotes();
            image=mUpdateProfile.getImage();
        }

        // Add the line
        mAge=ageCal2.calAge(mDateOfBirth);

        // set the value of database to the text field.
        et_name.setText(mName);
        et_age.setText(mAge);
        etBloodGroup.setText(mBloodGroup);
        et_weight.setText(mWeight);
        et_height.setText(mHeight);
        et_dateOfBirth.setText(mDateOfBirth);
        et_specialNotes.setText(mSpecialNotes);
        et_dateOfBirth.setOnClickListener(this);

    }
    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            mName = et_name.getText().toString();
           mAge = et_age.getText().toString();
            mBloodGroup=etBloodGroup.getText().toString();
            mWeight = et_weight.getText().toString();
            mHeight = et_height.getText().toString();
            mDateOfBirth = et_dateOfBirth.getText().toString();
            mSpecialNotes = et_specialNotes.getText().toString();

            // Assign values in the ICareProfile
            Profile profileDataInsert = new Profile();
            profileDataInsert.setName(mName);
            profileDataInsert.setAge(mAge);
            profileDataInsert.setBloodGroup(mBloodGroup);
            profileDataInsert.setWeight(mWeight);
            profileDataInsert.setHeight(mHeight);
            profileDataInsert.setDateOfBirth(mDateOfBirth);
            profileDataInsert.setSpecialNotes(mSpecialNotes);

            mDataSource = new ProfileDataSource(getBaseContext());

            if (mDataSource.updateData(1, profileDataInsert) == true) {
                Intent intent=new Intent(ProfilePreviewUpdate.this,MainActivity.class);
                startActivity(intent);
                toast = Toast.makeText(ProfilePreviewUpdate.this,
                        getString(R.string.successfullyUpdated), Toast.LENGTH_LONG);
                toast.show();
                //displayView();

            } else {
                toast = Toast.makeText(ProfilePreviewUpdate.this, getString(R.string.notUpdated),
                        Toast.LENGTH_LONG);
                toast.show();
            }
        }
    };
//
////    private void displayView() {
////        // update the main content by replacing fragments
//        Fragment fragment = null;
//
//        fragment = new Fragment_Home();
//        if (fragment != null) {
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.frame_container, fragment).commit();
//
//
//        }
//    }



    @Override
    public void onClick(View v) {
       switch (v.getId()) {
            case R.id.viewDOB:
                this.showDialog(DATE_START_DIALOG_ID);
                break;

            default:
                break;
        }

    }


    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                image = stream.toByteArray();

            }
        }
    }


    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_START_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        startYear, startMonth, startDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener
            = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                             int selectedMonth, int selectedDay) {
            startYear=selectedYear;
            startMonth=selectedMonth;
           startDay=selectedDay;

            et_dateOfBirth.setText(""+selectedDay+":"+(startMonth+1)+":"+startYear);

       }
    };

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
