package com.epsilon.coders.phms.fragments;

/**
 * Created by nilima on 10/6/2015.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.activities.CareInfoDisplay;
import com.epsilon.coders.phms.activities.DietListTodayAndUpcoming;
import com.epsilon.coders.phms.activities.DisplayAllFamilyProfile;
import com.epsilon.coders.phms.activities.DoctorList;
import com.epsilon.coders.phms.activities.EmergencySupport;
import com.epsilon.coders.phms.activities.ImportantNoteActivity;
import com.epsilon.coders.phms.activities.MyProfileActivity;
import com.epsilon.coders.phms.activities.VaccinationListTodayAndUpcoming;
import com.epsilon.coders.phms.adapters.CustomAdapter;
import com.epsilon.coders.phms.utills.BMICalculator;


public class Home extends Fragment {

    GridView mainGridView;
    String[] gridTitle={"Basic Info","Family Info","BMI Calculator","Diet","Vaccine","Doctor","Medical center","Note","Emergency"};
    Integer[] gridImage={R.drawable.profile, R.drawable.peopleicon , R.drawable.growthstatus, R.drawable.diet, R.drawable.vaccine, R.drawable.doctor, R.drawable.hospital, R.drawable.note, R.drawable.emmergency};



//    ImageButton addNewDoctor;
//    //ImageButton updateDietChart;
//    ImageButton dietInfo;
//    //ImageButton profileView;
//    //ImageButton doctorList;
//    ImageButton addProfile;
//    ImageButton careInfo;
//    ImageButton vaccination;
//    ImageButton emergencyContact;
    View rootView;

    Integer profileId;
    public Home(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_home, container, false);

        mainGridView =(GridView)rootView.findViewById(R.id.gridView1);
        Intent intent=getActivity().getIntent();
        profileId=intent.getIntExtra("profile_id",0);



            init();
        return rootView;

    }

    private void init() {



        mainGridView.setAdapter(new CustomAdapter(getActivity().getApplicationContext(), gridTitle, gridImage));
        mainGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        Intent basicInfoIntent = new Intent(getActivity().getApplicationContext(), MyProfileActivity.class);
                        basicInfoIntent.putExtra("profile_id",profileId);
                        startActivity(basicInfoIntent);
                        break;

                    case 1:
                        Intent familyIntent = new Intent(getActivity().getApplicationContext(), DisplayAllFamilyProfile.class);
                        familyIntent.putExtra("profile_id",profileId);
                        startActivity(familyIntent);
                        break;

                    case 2:
                        Intent bmiIntent = new Intent(getActivity().getApplicationContext(), BMICalculator.class);
                        bmiIntent.putExtra("profile_id",profileId);
                        startActivity(bmiIntent);
                        break;

                    case 3:

                        Intent dietIntent = new Intent(getActivity().getApplicationContext(), DietListTodayAndUpcoming.class);
                        dietIntent.putExtra("profile_id",profileId);
                        startActivity(dietIntent);
                        break;
                    case 4:
                        Intent vaccineIntent = new Intent(getActivity().getApplicationContext(), VaccinationListTodayAndUpcoming.class);
                        vaccineIntent.putExtra("profile_id",profileId);
                        startActivity(vaccineIntent);
                        break;



                    case 5:
                        Intent doctorIntent = new Intent(getActivity().getApplicationContext(), DoctorList.class);
                        doctorIntent.putExtra("profile_id",profileId);
                        startActivity(doctorIntent);
                        break;
                    case 6:
                        Intent medicalCenterIntent = new Intent(getActivity().getApplicationContext(), CareInfoDisplay.class);
                        medicalCenterIntent.putExtra("profile_id",profileId);
                        startActivity(medicalCenterIntent);
                        break;

                    case 7:
                        Intent noteIntent = new Intent(getActivity().getApplicationContext(), ImportantNoteActivity.class);
                        noteIntent.putExtra("profile_id",profileId);
                        startActivity(noteIntent);

                        break;

                    case 8:
                        Intent emergencyIntent=new Intent(getActivity().getApplicationContext(), EmergencySupport.class);
                        emergencyIntent.putExtra("profile_id",profileId);
                        startActivity(emergencyIntent);
                        break;


                }
            }
        });

    }


    }

//    public void ShowAlertDialogWithListview()
//    {
//        List<String> mAnimals = new ArrayList<String>();
//        mAnimals.add("Cat");
//        mAnimals.add("Dog");
//        mAnimals.add("Horse");
//        mAnimals.add("Elephant");
//        mAnimals.add("Rat");
//        mAnimals.add("Lion");
//        //Create sequence of items
//        final CharSequence[] Animals = mAnimals.toArray(new String[mAnimals.size()]);
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
//        dialogBuilder.setTitle("Animals");
//        dialogBuilder.setItems(Animals, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int item) {
//                String selectedText = Animals[item].toString();  //Selected item in listview
//            }
//        });
//        //Create alert dialog object via builder
//        AlertDialog alertDialogObject = dialogBuilder.create();
//        //Show the dialog
//        alertDialogObject.show();
//    }






