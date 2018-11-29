package com.example.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout RL_containt = findViewById(R.id.RL_containt);
        final EditText et_text = findViewById(R.id.et_text);
        final TextView tv_click = findViewById(R.id.tv_click);
//        final AntAnimation antAnimation = new AntAnimation(this);
        BeserView beserView = new BeserView(this);
        RL_containt.addView(beserView);
        beserView.startAnimation();


//        RL_containt.addView(antAnimation);
        tv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                antAnimation.startAnimation(Integer.parseInt(et_text.getText().toString()));
            }
        });


    }
}
