package com.epsilon.coders.phms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.databases.ProfileDataSource;
import com.epsilon.coders.phms.model.Profile;
import com.epsilon.coders.phms.utills.AgeCalculator;


/**
 * Created by nilima on 10/16/2015.
 */
public class MyProfileActivity extends AppCompatActivity {

    TextView name,age,dateofBirth,bloodGroup,height,weight,specialNotes;
    Button editBtn;

    Profile mUpdateProfile;

//    Button updateProfile ;
//    Button home_btn ;
//    EditText et_name = null;
//    EditText et_age = null;
//    EditText etBloodGroup = null;
//    EditText et_weight = null;
//    EditText et_height = null;
//    EditText et_dateOfBirth = null;
//    EditText et_specialNotes = null;
//    ImageView btnImage;
    Toast toast = null;
//
//    String mName = "";
//    String mAge = "";
//    String mBloodGroup = "";
//    String mWeight = "";
//    String mHeight = "";
//    String mDateOfBirth;
//    String mSpecialNotes = "";
//    byte[] image;
//
//    Profile mUpdateProfile = null;
    ProfileDataSource mDataSource = null;

    private int startYear=1980;
    private int startMonth=6;
    private int startDay=15;


    /// add new
    AgeCalculator ageCal2;


    static final int DATE_START_DIALOG_ID = 0;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ageCal2=new AgeCalculator();
//
//
//
//
        init();
//
////        home_btn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                displayView();
////
////
////            }
////        });
//
//        //return view;
//

    }

    private void init() {

        name=(TextView)findViewById(R.id.viewName);
        age=(TextView)findViewById(R.id.viewAge);
        dateofBirth=(TextView)findViewById(R.id.viewDOB);
        bloodGroup=(TextView)findViewById(R.id.viewBloodGroup);
        height=(TextView)findViewById(R.id.viewHeight);
        weight=(TextView)findViewById(R.id.viewHeight);
        specialNotes=(TextView)findViewById(R.id.viewSpComment);
        editBtn=(Button)findViewById(R.id.updateProfile);


        mUpdateProfile=new Profile();
        mDataSource = new ProfileDataSource(this);
        mUpdateProfile = mDataSource.singleProfileData();

        String mName = "";
        String mAge = "";
        String mEyeColor = "";
        String mWeight = "";
        String mHeight = "";
        String mDateOfBirth = "";
        String mSpecialNotes = "";
        byte[] image=null;

        if(mUpdateProfile !=null )
        {
            mName = mUpdateProfile.getName();
           // mAge = mUpdateProfile.getAge();
            mEyeColor = mUpdateProfile.getBloodGroup();
            mWeight = mUpdateProfile.getWeight();
            mHeight = mUpdateProfile.getHeight();
            mDateOfBirth = mUpdateProfile.getDateOfBirth();
            mSpecialNotes = mUpdateProfile.getSpecialNotes();
            image=mUpdateProfile.getImage();
        }

//
//        // Add the line
       mAge=ageCal2.calAge(mDateOfBirth);

        // set the value of database to the text field.
        name.setText(mName);
        age.setText(mAge);
        bloodGroup.setText(mEyeColor);
        weight.setText(mWeight);
        height.setText(mHeight);
        dateofBirth.setText(mDateOfBirth);
        specialNotes.setText(mSpecialNotes);
       // dateofBirth.setOnClickListener(this);



        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyProfileActivity.this,ProfilePreviewUpdate.class);
                startActivity(intent);
            }
        });



//        // set the textfield id to the variable.
//        et_name = (EditText) findViewById(R.id.viewName);
//        et_age = (EditText) findViewById(R.id.viewAge);
//        etBloodGroup = (EditText) findViewById(R.id.viewBloodGroup);
//        et_weight = (EditText) findViewById(R.id.viewWeight);
//        et_height = (EditText) findViewById(R.id.viewHeight);
//        et_dateOfBirth = (EditText) findViewById(R.id.viewDOB);
//        et_specialNotes = (EditText) findViewById(R.id.viewSpComment);
//        btnImage=(ImageView) findViewById(R.id.imageViewCreate);
//        updateProfile = (Button) findViewById(R.id.updateProfile);
//        updateProfile.setOnClickListener(mCorkyListener);
//        //home_btn = (Button) view.findViewById(R.id.buttonHome);
//
//
//		 * get the profile which include all data from database according
//		 * profileId of the clicked item.
//

//        mDataSource = new ProfileDataSource(this);
//        mUpdateProfile = mDataSource.singleProfileData();
//
//        String mName = mUpdateProfile.getName();
//        String mAge2 = mUpdateProfile.getAge();
//        String mEyeColor = mUpdateProfile.getBloodGroup();
//        String mWeight = mUpdateProfile.getWeight();
//        String mHeight = mUpdateProfile.getHeight();
//        mDateOfBirth = mUpdateProfile.getDateOfBirth();
//        String mSpecialNotes = mUpdateProfile.getSpecialNotes();
//        image=mUpdateProfile.getImage();
//
//        // Add the line
//       mAge=ageCal2.calAge(mDateOfBirth);
//
//        // set the value of database to the text field.
//        et_name.setText(mName);
//        et_age.setText(mAge);
//        etBloodGroup.setText(mEyeColor);
//        et_weight.setText(mWeight);
//        et_height.setText(mHeight);
//        et_dateOfBirth.setText(mDateOfBirth);
//        et_specialNotes.setText(mSpecialNotes);
//        et_dateOfBirth.setOnClickListener(this);

    }
//    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
//        public void onClick(View v) {
//            mName = et_name.getText().toString();
//            mAge = et_age.getText().toString();
//            mBloodGroup = etBloodGroup.getText().toString();
//            mWeight = et_weight.getText().toString();
//            mHeight = et_height.getText().toString();
//            mDateOfBirth = et_dateOfBirth.getText().toString();
//            mSpecialNotes = et_specialNotes.getText().toString();
//
//            // Assign values in the ICareProfile
//            Profile profileDataInsert = new Profile();
//            profileDataInsert.setName(mName);
//            profileDataInsert.setAge(mAge);
//            profileDataInsert.setBloodGroup(mBloodGroup);
//            profileDataInsert.setWeight(mWeight);
//            profileDataInsert.setHeight(mHeight);
//            profileDataInsert.setDateOfBirth(mDateOfBirth);
//            profileDataInsert.setSpecialNotes(mSpecialNotes);
//
//            mDataSource = new ProfileDataSource(getBaseContext());
//
//            if (mDataSource.updateData(1, profileDataInsert) == true) {
//                toast = Toast.makeText(MyProfileActivity.this,
//                        getString(R.string.successfullyUpdated), Toast.LENGTH_LONG);
//                toast.show();
//                //displayView();
//
//            } else {
//                toast = Toast.makeText(MyProfileActivity.this, getString(R.string.notUpdated),
//                        Toast.LENGTH_LONG);
//                toast.show();
//            }
//        }
//    };
//
////    private void displayView() {
////        // update the main content by replacing fragments
////        Fragment fragment = null;
////
////        fragment = new Fragment_Home();
////        if (fragment != null) {
////            FragmentManager fragmentManager = getFragmentManager();
////            fragmentManager.beginTransaction()
////                    .replace(R.id.frame_container, fragment).commit();
////
////
////        }
////    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.viewDOB:
//                this.showDialog(DATE_START_DIALOG_ID);
//                break;
//
//            default:
//                break;
//        }
//
//    }
//
//
//    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == 0) {
//                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//                File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
//                FileOutputStream fo;
//                try {
//                    destination.createNewFile();
//                    fo = new FileOutputStream(destination);
//                    fo.write(bytes.toByteArray());
//                    fo.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                btnImage.setImageBitmap(thumbnail);
//            } else if (requestCode == 1) {
//                Uri selectedImageUri = data.getData();
//                String[] projection = {MediaStore.MediaColumns.DATA};
//                Cursor cursor = managedQuery(selectedImageUri, projection, null, null, null);
//                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//                cursor.moveToFirst();
//                String selectedImagePath = cursor.getString(column_index);
//                Bitmap bm;
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inJustDecodeBounds = true;
//                BitmapFactory.decodeFile(selectedImagePath, options);
//                final int REQUIRED_SIZE = 200;
//                int scale = 1;
//                while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
//                    scale *= 2;
//                options.inSampleSize = scale;
//                options.inJustDecodeBounds = false;
//                bm = BitmapFactory.decodeFile(selectedImagePath, options);
//                btnImage.setImageBitmap(bm);
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                image = stream.toByteArray();
//
//            }
//        }
//    }
//
//
//    protected Dialog onCreateDialog(int id) {
//        switch (id) {
//            case DATE_START_DIALOG_ID:
//                return new DatePickerDialog(this,
//                        mDateSetListener,
//                        startYear, startMonth, startDay);
//        }
//        return null;
//    }
//
//    private DatePickerDialog.OnDateSetListener mDateSetListener
//            = new DatePickerDialog.OnDateSetListener() {
//        public void onDateSet(DatePicker view, int selectedYear,
//                              int selectedMonth, int selectedDay) {
//            startYear=selectedYear;
//            startMonth=selectedMonth;
//            startDay=selectedDay;
//
//            et_dateOfBirth.setText(""+selectedDay+":"+(startMonth+1)+":"+startYear);
//
//        }
//    };

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
