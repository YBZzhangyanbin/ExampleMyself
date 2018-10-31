package com.example.talefun.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.Toast;


import java.lang.reflect.Method;

public class Tools {
    public static final int FUll_SCREEN = 0;
    public static final int NOT_FUll_SCREEN = 1;
    private static final String TAG = "zhangyb";

    /**
     * 获取跟布局的高度
     *
     * @param activity
     * @return
     */
    public static int getViewHight(Activity activity) {
        return activity.getWindow().getDecorView().getHeight();
    }


    private static boolean hasNotchInScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) { //安卓9.0判断是否刘海屏
            View decorView = activity.getWindow().getDecorView();
            if (decorView != null && android.os.Build.VERSION.SDK_INT >= 28) {
                WindowInsets windowInsets = decorView.getRootWindowInsets();
                if (windowInsets != null)
                    return windowInsets.getDisplayCutout() == null ? false : true;
            }
            return false;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {   //安卓8.0判断是否刘海屏
            boolean ret_all = false;
            boolean ret_oppo = false;
            boolean ret_vivo = false;
            try {
                ret_oppo = activity.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
            } catch (Exception e) {
            }
            try {
                int NOTCH_IN_SCREEN_VOIO = 0x00000020;//是否有凹槽
                ClassLoader cl = activity.getClassLoader();
                Class FtFeature = cl.loadClass("android.util.FtFeature");
                Method get = FtFeature.getMethod("isFeatureSupport", int.class);
                ret_vivo = (boolean) get.invoke(FtFeature, NOTCH_IN_SCREEN_VOIO);
            } catch (Exception e) {
                return true;
            }
            ret_all = ret_oppo | ret_vivo;
            return ret_all;
        } else
            return false;
    }


    /**
     * Android 8.0获取
     *
     * @param activity
     * @return
     */
    private static DisplayMarginSize getSaveSizeAndroidO(Activity activity) {
        DisplayMarginSize displayMarginSize = new DisplayMarginSize();
        int maxH = 0;
        int maxW = 0;
        int viewHight = activity.getWindow().getDecorView().getHeight();//获取跟布局的view
        int viewWidth = activity.getWindow().getDecorView().getWidth();//获取跟布局的view
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            activity.getWindowManager().getDefaultDisplay().getRealSize(point);
            maxH = point.y;
            maxW = point.x;

        } else {
//           maxH = Tools.getWindowSize(this).height;
        }
        boolean b = isScreenChange(activity);
        boolean a = hasNotchInScreen(activity);
        if (!isScreenChange(activity) && maxH == viewHight && hasNotchInScreen(activity)) {   //竖屏
            displayMarginSize.setTop(getStatusBarHeight(activity));
            displayMarginSize.setNotchStatus(0);
        } else if (isScreenChange(activity) && getRotaionMessage(activity) == 1 && maxW == viewWidth && hasNotchInScreen(activity)) {   //横屏 左旋 即逆时针
            displayMarginSize.setLeft(getStatusBarHeight(activity));
            displayMarginSize.setNotchStatus(0);
        } else if (isScreenChange(activity) && getRotaionMessage(activity) == 3 && maxW == viewWidth && hasNotchInScreen(activity)) {   //横屏 右旋 顺时针
            displayMarginSize.setRight(getStatusBarHeight(activity));
            displayMarginSize.setNotchStatus(0);
        } else if (!isScreenChange(activity) && maxH != viewHight && hasNotchInScreen(activity)) {  //竖屏
            displayMarginSize.setTop(maxH - viewHight + 2);
        } else if (isScreenChange(activity) && getRotaionMessage(activity) == 1 && maxH != viewHight && hasNotchInScreen(activity)) {  //横屏 左旋 即逆时针
            displayMarginSize.setLeft(maxW - viewWidth + 1);
        } else if (isScreenChange(activity) && getRotaionMessage(activity) == 3 && maxH != viewHight && hasNotchInScreen(activity)) {  //横屏 右旋 顺时针
            displayMarginSize.setRight(maxW - viewWidth + 3);
        }
        return displayMarginSize;

    }

    @TargetApi(28)
    private static DisplayMarginSize getSaveSizeAndroidP(Activity activity) {
        DisplayMarginSize displayMarginSize = new DisplayMarginSize();
        View decorView = activity.getWindow().getDecorView();
        if (decorView != null) {
            WindowInsets windowInsets = decorView.getRootWindowInsets();
            if (windowInsets != null) {
                DisplayCutout displayCutout = windowInsets.getDisplayCutout();
                if (displayCutout != null) {
                    displayMarginSize.setTop(displayCutout.getSafeInsetTop());
                    displayMarginSize.setBootom(displayCutout.getSafeInsetBottom());
                    displayMarginSize.setLeft(displayCutout.getSafeInsetLeft());
                    displayMarginSize.setRight(displayCutout.getSafeInsetRight());
                    displayMarginSize.setNotchStatus(0);
                }
            }
        }
        return displayMarginSize;
    }

    //获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public static DisplayMarginSize getDispalyMarginMethod(Activity activity) {
        DisplayMarginSize displayMarginSize = new DisplayMarginSize();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            displayMarginSize = getSaveSizeAndroidP(activity);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            displayMarginSize = getSaveSizeAndroidO(activity);
        }
        return displayMarginSize;

    }

    public static void setDisplayStyle(Activity activity, int fUll_screen) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        View view = activity.getWindow().getDecorView();
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            switch (fUll_screen) {
                case FUll_SCREEN:
                    lp.layoutInDisplayCutoutMode
                            = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                    break;
                case NOT_FUll_SCREEN:
                    lp.layoutInDisplayCutoutMode
                            = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
                    break;
            }
        }
    }

    /**
     * 获取当前是横屏还是竖屏
     *
     * @param activity
     * @return
     */
    public static boolean isScreenChange(Activity activity) {

        Configuration mConfiguration = activity.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            return true;//横屏
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            return false; //竖屏
        }
        return true;
    }

    /**
     * 获取旋转的角度
     */
    private static int getRotaionMessage(Activity activity) {
        int angle = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        switch (angle) {
            case Surface.ROTATION_0:
                Log.d(TAG, "Rotation_0");
                return 0;

            case Surface.ROTATION_90:
                Log.d(TAG, "ROTATION_90");
                return 1;

            case Surface.ROTATION_180:
                Log.d(TAG, "ROTATION_180");
                return 2;

            case Surface.ROTATION_270:
                return 3;
            default:
                return 0;
        }

    }


}
