package com.epsilon.coders.phms.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.fragments.AboutUsFragment;
import com.epsilon.coders.phms.fragments.HelpFragment;
import com.epsilon.coders.phms.fragments.Home;
import com.epsilon.coders.phms.fragments.TabFragment;
import com.epsilon.coders.phms.utills.MyLocationService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    EditText searchEditText;

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    private SensorManager sensorManager;
    private long lastUpdate;
    int i=0;

    private String eNumber;
    private String eSMS;

    double latitude = 0.0;
    double longitude = 0.0;
    MyLocationService myLocationService;
    Context c;
//    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // toolbar = (Toolbar) findViewById(R.id.toolbar);

       // setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.firstaidpersonalmedicapplargeicon);



        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();



                if (menuItem.getItemId() == R.id.nav_item_about_us) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new AboutUsFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_item_help) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new HelpFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_item_home) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new Home()).commit();
                }


                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        //getSupportActionBar().setIcon(R.drawable.firstaidpersonalmedicapplargeicon);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();


        //sensor


        c=getApplicationContext();

        SharedPreferences pref = getSharedPreferences(EmergencySupport.MY_PREFS_NAME, MODE_PRIVATE);
        eNumber=pref.getString("key_e_Number", null);          // getting String
        eSMS=pref.getString("key_e_SMS", null);          // getting String

        // Toast.makeText(this, eSMS + "  \n " + eNumber, Toast.LENGTH_SHORT)                .show();


////////////////// address location /////////////////////////////////
        myLocationService= new MyLocationService(c);


        Location nwLocation = myLocationService
                .getLocation(LocationManager.NETWORK_PROVIDER);

        if (nwLocation != null) {
            latitude = nwLocation.getLatitude();
            longitude = nwLocation.getLongitude();
        }

////////////////// address location /////////////////////////////////




        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_search) {
            searchEditText.setVisibility(View.VISIBLE);
        }


        return super.onOptionsItemSelected(item);
    }

    //sensor

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }

    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = event.timestamp;
        if (accelationSquareRoot >= 2) //
        {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;

// get address method call
            String address= getAddress(latitude, longitude);
            i++;

//            // send emergency sms
//            if(i==1) {
//
//
//
//                String message = eSMS+"\n"+ address;
//                SmsManager smsManager = SmsManager.getDefault();
//                smsManager.sendTextMessage(eNumber, null, message, null, null);
//                Toast.makeText(this, "Emergeny SMS is sending " + eSMS, Toast.LENGTH_SHORT)
//                        .show();
//            }
            if(i==5 || i==10){

                Intent myIntent = new Intent(Intent.ACTION_CALL);
                String phNum = "tel:" + eNumber;
                myIntent.setData(Uri.parse(phNum));
                startActivity( myIntent ) ;

                Toast.makeText(this, " Emergeny calling  "+eNumber , Toast.LENGTH_SHORT)
                        .show();

            }

            Toast.makeText(this, "Sensor working " , Toast.LENGTH_SHORT)
                    .show();


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);

    }


    //////////// lat and lon to convert to address start  function
    public String getAddress(double lat, double lon) {
        Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
        String ret = "";
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);
            if(addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("Address:");
                for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(", ");
                }
                ret = strReturnedAddress.toString();
            }
            else{
                ret = "No Address returned!";
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ret = "Can't get Address!";
        }
        return ret;
    }


//////////// lat and lon to convert to address start  function


}
