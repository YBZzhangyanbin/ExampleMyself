package com.example.doubleserviceprotectdemoa;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(MainActivity.this, FirstService.class);
                startService(startIntent);

            }
        });

        findViewById(R.id.tv_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stopService = new Intent(MainActivity.this, FirstService.class);
                stopService(stopService);


            }
        });

        findViewById(R.id.tv_bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FirstService.class);

                bindService(intent, serviceConnection, BIND_AUTO_CREATE);


            }
        });

        findViewById(R.id.tv_ubind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                unbindService(serviceConnection);

                ComponentName componentName = new ComponentName("com.android.settings",
                        "com.android.settings.Settings$ManageApplicationsActivity");
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(componentName);
                startActivity(intent);


            }
        });
    }

    FirstService.MyBinder myBinder;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            FirstService.MyBinder myBind = (FirstService.MyBinder) service;
            myBind.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {


        }
    };
}
