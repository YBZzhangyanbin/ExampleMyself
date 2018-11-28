package com.example.antmoneyanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout rl = findViewById(R.id.rl);
        NewCreditSesameView view=new NewCreditSesameView(this);
        rl.addView(view);
    }
}
