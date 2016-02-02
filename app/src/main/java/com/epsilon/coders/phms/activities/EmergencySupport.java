package com.epsilon.coders.phms.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.utills.MyLocationService;


/**
 * Created by Prodip on 6/26/2015.
 */
public class EmergencySupport extends AppCompatActivity {
    MyLocationService myLocationService;
    Context c;
   private double latitude ;
  private double longitude ;


   TextView eNumberView;
    TextView eSMSView;
    Button setEmergencyNumber;
    Button setEmergencySMS;
    Button callEmergencyNumber;
    Button sendEmergencySMS;

    private Toolbar toolbar;

    final Context context = this;
    String eSms;
    String eNumber;
    public static final String MY_PREFS_NAME = "EmergencyPreferences";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_support);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        final MainActivity sta=new MainActivity();
        c=getApplicationContext();


        init();

        final SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
     final SharedPreferences pref = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        ////////////////// address location /////////////////////////////////
        myLocationService= new MyLocationService(c);


        Location nwLocation = myLocationService
                .getLocation(LocationManager.NETWORK_PROVIDER);

        if (nwLocation != null) {
            latitude = nwLocation.getLatitude();
            longitude = nwLocation.getLongitude();
        }

////////////////// address location /////////////////////////////////



        eNumber=pref.getString("key_e_Number", null);          // getting String
        eSms=pref.getString("key_e_SMS", null);          // getting String
        if(eNumber==null){
            eNumberView.setText("Yet not set emergency number");
        }
        else
        {
            eNumberView.setText(eNumber);
        }
//        if(eSms==null){
//            eNumberView.setText("Yet not set emergency SMS ");
//        }
//        else
//        {
//            eSMSView.setText(eSms);
//        }



// get emergency number from dialoag box
        setEmergencyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getset_emergency_number.xml view
                LayoutInflater layoutInflater = LayoutInflater.from(context);

                View promptView = layoutInflater.inflate(R.layout.fragment_set_emergency_number, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set fragment_set_emergency_number.xmlumber.xml to be the layout file of the alertdialog builder
                alertDialogBuilder.setView(promptView);

                final EditText inputENumber = (EditText) promptView.findViewById(R.id.etEmergencyNumber);

                // setup a dialog window
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                              String n=inputENumber.getText().toString();
                                //eNumberView.setText(input.getText());

                                editor.putString("key_e_Number", n);  // Saving string
                                editor.commit();
                                eNumber=pref.getString("key_e_Number", null);          // getting String
                                eNumberView.setText(eNumber);



                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,	int id) {
                                        dialog.cancel();
                                    }
                                });

                // create an alert dialog
                AlertDialog alertD = alertDialogBuilder.create();

                alertD.show();



            }
        });


// get emergency SMS from dialoag box
//        setEmergencySMS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // get fragment_set_emergency_sms.xmly_sms.xml view
//                LayoutInflater layoutInflater = LayoutInflater.from(context);
//
//                View promptView = layoutInflater.inflate(R.layout.fragment_set_emergency_sms, null);
//
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//
//                // set fragment_set_emergency_smsgency_sms.xml to be the layout file of the alertdialog builder
//                alertDialogBuilder.setView(promptView);
//
//                final EditText inputESMS = (EditText) promptView.findViewById(R.id.etEmergencySMS);
//
//                // setup a dialog window
//                alertDialogBuilder
//                        .setCancelable(false)
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // get user input and set it to result
//                                String sms=inputESMS.getText().toString();
//                               // eSMSView.setText(inputESMS.getText());
//
//
//                                editor.putString("key_e_SMS", sms);
//                                editor.commit();
//                                eSms=pref.getString("key_e_SMS", null);          // getting String
//                                eSMSView.setText(eSms);
//                            }
//                        })
//                        .setNegativeButton("Cancel",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,	int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//
//                // create an alert dialog
//                AlertDialog alertD = alertDialogBuilder.create();
//
//                alertD.show();
//
//
//            }
//        });
        callEmergencyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(Intent.ACTION_CALL);
                String phNum = "tel:" + eNumber;
                myIntent.setData(Uri.parse(phNum));
                startActivity(myIntent) ;

            }
        });

//// send emergency SMS from dialoag box
//        sendEmergencySMS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // get fragment_send_emergency_sms.xmly_sms.xml view
//                LayoutInflater layoutInflater = LayoutInflater.from(context);
//
//                View promptView = layoutInflater.inflate(R.layout.fragment_send_emergency_sms, null);
//
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//
//                // set fragment_set_emergency_smsgency_sms.xml to be the layout file of the alertdialog builder
//                alertDialogBuilder.setView(promptView);
//                String locationAddress=sta.getAddress(latitude,longitude);
//
//                final EditText inputSendSms = (EditText) promptView.findViewById(R.id.etEmergencySmsSend);
//                final EditText setENumber = (EditText) promptView.findViewById(R.id.etEmergencyNumberSet);
//                inputSendSms.setText(eSms+"\n"+locationAddress);
//                setENumber.setText(eNumber);
//
//                // setup a dialog window
//                alertDialogBuilder
//                        .setCancelable(false)
//                        .setPositiveButton("Send", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // get user input and set it to result
//                                String sms = inputSendSms.getText().toString();
//                                String number= setENumber.getText().toString();
//
//                                // eSMSView.setText(inputESMS.getText());
//                               // String phoneNumber = "+8801911469006";
//
//                                SmsManager smsManager = SmsManager.getDefault();
//                                smsManager.sendTextMessage(number, null, sms, null, null);
//
//
//                            }
//                        })
//                        .setNegativeButton("Cancel",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//
//                 //create an alert dialog
//                AlertDialog alertD = alertDialogBuilder.create();
//
//                alertD.show();
//
//
//            }
//        });

  //////////////////////////////////////////////////////
    }






    private void init() {
       eNumberView= (TextView) findViewById(R.id.textViewEmergencyNumber);
       // eSMSView= (TextView) findViewById(R.id.textViewEmergencySms);
        setEmergencyNumber= (Button) findViewById(R.id.buttonENmuber);
        //setEmergencySMS= (Button) findViewById(R.id.buttonESMS);
        callEmergencyNumber= (Button) findViewById(R.id.buttonECall);
       // sendEmergencySMS= (Button) findViewById(R.id.buttonSendESms);

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_emmergency, menu);
//        return true;
//    }

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

    public  void home(){
        Intent intent= new Intent(this,MainActivity.class) ;
        startActivity(intent);
    }

}
