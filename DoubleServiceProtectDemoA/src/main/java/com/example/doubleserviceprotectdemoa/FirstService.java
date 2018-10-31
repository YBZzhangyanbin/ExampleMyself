package com.example.doubleserviceprotectdemoa;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class FirstService extends Service {
    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("zhangyb", "onCreate: ==service创建");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("zhangyb", "onStartCommand: ==flags" + flags + "    startID" + startId);
        
        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public void onDestroy() {
        Log.i("zhangyb", "onDestroy: ");
        super.onDestroy();
    }


    @Nullable
    @Override
    public MyBinder onBind(Intent intent) {
        Log.i("zhangyb", "onBind: ");
        return mBinder;
    }

    class MyBinder extends Binder {
        public void startDownload() {
            Log.i("zhangyb", "startDownload:正在下载...... ");
        }
    }
    
    
    


}
