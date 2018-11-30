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
        final BeserView chartViewbeser = findViewById(R.id.beser);
        final TextView tv_click = findViewById(R.id.tv_click);
        final ChartView chartView = new ChartView(this);
        RL_containt.addView(chartView);

        chartView.statrtAnimation();


        chartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartView.statrtAnimation();
            }
        });


    }
}
