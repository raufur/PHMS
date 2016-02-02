package com.epsilon.coders.phms.diseaseActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.epsilon.coders.phms.R;


/**
 * Created by Md. Mehedi Hasan on 10/20/2015.
 */
public class HepatitisA extends AppCompatActivity {
        TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hepatitis_a);
        textView=(TextView)findViewById(R.id.hepatitis_a);
        textView.setText(Html.fromHtml(getString(R.string.hepatitis_a)));
    }
}
