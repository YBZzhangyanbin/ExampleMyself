package com.example.text;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Queue;

public class QuneneActivity extends Activity {

    private EditText tv_request;
    private TextView tv_result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qunenen);
        tv_request = findViewById(R.id.tv_request);
        tv_result = findViewById(R.id.tv_result);

        tv_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(tv_request.getText().toString());


            }
        });

    }

    public  void buileQueue(){
//        Queue queue=new Queue_Arra
    }
}
