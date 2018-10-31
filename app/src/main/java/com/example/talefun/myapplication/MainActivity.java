package com.example.talefun.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.Surface;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;

import java.lang.reflect.Method;
import java.util.Random;

/**
 *
 */
public class MainActivity extends Activity {

    private Integer[] arr;
    private Context content;
    private String TAG = "zhangyb";
    public static final String DISPLAY_NOTCH_STATUS = "display_notch_status";
    String manufacturer = Build.MANUFACTURER;
    private TextView tv_quick_sort;
    private boolean ret_huawei;
    private boolean ret_oppo;
    private boolean ret_vivo;
    private boolean ret_xiaomi;
    private Activity activity;
    private MyOrientationDetector myOrientationDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在使用LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES的时候，状态栏会显示为白色，这和主内容区域颜色冲突,
        //所以我们要开启沉浸式布局模式，即真正的全屏模式,以实现状态和主体内容背景一致

        Tools.setDisplayStyle(this, Tools.FUll_SCREEN);
        setContentView(R.layout.activity_main);
        content = this;
        activity = this;
        myOrientationDetector = new MyOrientationDetector(content);
    }


    @Override
    protected void onResume() {
        super.onResume();
        myOrientationDetector.enable();
//        controlView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                controlView();

            }
        }, 500);


    }


    /**
     * 在onResum中调用
     *
     * @param view 充满屏幕的view
     */
    private void getViewHigh(final View view) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        myOrientationDetector.disable();
    }

    /**
     * @param hight 处理适配逻辑
     */
    public void changeNotchHight(int hight) {


    }


    public void controlView() {

        Log.i(TAG, "controlView: " + Tools.getDispalyMarginMethod(this));
        Toast.makeText(content, "controlView: " + Tools.getDispalyMarginMethod(this), Toast.LENGTH_LONG).show();

    }


}
