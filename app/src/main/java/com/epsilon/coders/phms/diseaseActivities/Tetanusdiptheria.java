package com.epsilon.coders.phms.diseaseActivities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.widget.TextView;

import com.epsilon.coders.phms.R;


public class Tetanusdiptheria extends ActionBarActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tetanusdiptheria);
        textView=(TextView)findViewById(R.id.tetanusdiptheria);
        textView.setText(Html.fromHtml(getString(R.string.tetanusdiptheria)));

    }


}
