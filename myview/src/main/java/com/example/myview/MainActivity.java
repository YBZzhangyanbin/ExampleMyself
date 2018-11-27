package com.example.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout RL_containt = findViewById(R.id.RL_containt);
        AntAnimation antAnimation = new AntAnimation(this);
        RL_containt.addView(antAnimation);

    }
}
