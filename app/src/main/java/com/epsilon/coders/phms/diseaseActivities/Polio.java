package com.epsilon.coders.phms.diseaseActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.epsilon.coders.phms.R;


public class Polio extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polio);
        textView=(TextView)findViewById(R.id.polio);
        textView.setText(Html.fromHtml(getString(R.string.polioo)));
    }



}
