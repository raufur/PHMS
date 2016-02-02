package com.epsilon.coders.phms.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.epsilon.coders.phms.R;


public class Splash extends Activity {

    MediaPlayer mpSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        mpSplash= MediaPlayer.create(getBaseContext(), R.raw.wavecrush);
        mpSplash.start();

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(4000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
//                    Intent i = new Intent(Splash.this,MainActivity.class);
//                    startActivity(i);
                    if (Appconfig.activity == false) {
                        Intent intent = new Intent();
                        intent.setClass(Splash.this,MyProfileCreateActivity.class);
                        startActivity(intent);
                        Splash.this.finish();
                    }
                    if (Appconfig.activity == true) {

                        Intent intent = new Intent();
                        intent.setClass(Splash.this, MainActivity.class);
                        startActivity(intent);

                    }

                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mpSplash.release();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mpSplash.pause();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}
