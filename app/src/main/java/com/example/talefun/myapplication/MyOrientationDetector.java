package com.example.talefun.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.OrientationEventListener;

public class MyOrientationDetector extends OrientationEventListener {
    public MyOrientationDetector(Context context) {
        super(context);
    }

    @Override
    public void onOrientationChanged(int orientation) {
        Log.i("zhangyb ","onOrientationChanged:"+orientation);

    }
}
