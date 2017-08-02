package com.hs.damnicomniplusvic.controltemp.util;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.hs.damnicomniplusvic.controltemp.R;
import com.hs.damnicomniplusvic.controltemp.customview.FloatViewOne;
import com.hs.damnicomniplusvic.controltemp.customview.FloatViewTwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/7/7.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public class MyWindowManager {

    private static final String TAG = "MyWindowManager";
    /**
     * 悬浮窗View的实例
     */
    private static FloatViewOne floatWindowOne;
    private static FloatViewTwo floatWindowTwo;

    /**
     * 悬浮窗View的参数
     */
    private static LayoutParams floatWindowOneParams;
    private static LayoutParams floatWindowTwoParams;


    /**
     * 用于控制在屏幕上添加或移除悬浮窗
     */
    private static WindowManager mWindowManager;

    /**
     * 用于获取手机可用内存
     */
    private static ActivityManager mActivityManager;

    /**
     * 创建一个悬浮窗。初始位置为屏幕的右部中间位置。
     *
     * @param context
     *            必须为应用程序的Context.
     */
    public static void createfloatWindowOne(Context context) {
        WindowManager windowManager = getWindowManager(context);
        Point size=new Point();
        windowManager.getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        if (floatWindowOne == null) {
            floatWindowOne = new FloatViewOne(context);
            if (floatWindowOneParams == null) {
                floatWindowOneParams = new LayoutParams();
                floatWindowOneParams.type = LayoutParams.TYPE_PHONE;
                floatWindowOneParams.format = PixelFormat.RGBA_8888;
                floatWindowOneParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | LayoutParams.FLAG_NOT_FOCUSABLE;
                floatWindowOneParams.gravity = Gravity.LEFT | Gravity.TOP;
                floatWindowOneParams.width = FloatViewOne.viewWidth;
                floatWindowOneParams.height = FloatViewOne.viewHeight;
                floatWindowOneParams.x = screenWidth;
                floatWindowOneParams.y = screenHeight / 2;
            }
            floatWindowOne.setParams(floatWindowOneParams);
            windowManager.addView(floatWindowOne, floatWindowOneParams);
        }
    }

    /**
     * 将小悬浮窗从屏幕上移除。
     *
     * @param context
     *            必须为应用程序的Context.
     */
    public static void removefloatWindowOne(Context context) {
        if (floatWindowOne != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(floatWindowOne);
            floatWindowOne = null;
        }
    }

    /**
     * 创建一个悬浮窗。初始位置为屏幕的右部中间位置。
     *
     * @param context
     *            必须为应用程序的Context.
     */
    public static void createfloatWindowTwo(Context context) {
        WindowManager windowManager = getWindowManager(context);
        Point size=new Point();
        windowManager.getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        if (floatWindowTwo == null) {
            floatWindowTwo = new FloatViewTwo(context);
            if (floatWindowTwoParams == null) {
                floatWindowTwoParams = new LayoutParams();
//                floatWindowTwoParams.x = screenWidth / 2 - floatWindowTwo.viewWidth / 2;
//                floatWindowTwoParams.y = screenHeight / 2 - floatWindowTwo.viewHeight / 2;
//                Log.i(TAG, "createfloatWindowTwo: x:"+screenWidth+" "+floatWindowTwoParams.x+" y:"+screenHeight+" "+floatWindowTwoParams.y);
                floatWindowTwoParams.type = LayoutParams.TYPE_PHONE;
                floatWindowTwoParams.format = PixelFormat.RGBA_8888;
                floatWindowTwoParams.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
                floatWindowTwoParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | LayoutParams.FLAG_NOT_FOCUSABLE;
                floatWindowTwoParams.width = floatWindowTwo.viewWidth;
                floatWindowTwoParams.height = floatWindowTwo.viewHeight;
            }
            windowManager.addView(floatWindowTwo, floatWindowTwoParams);
        }
    }

    /**
     * 将悬浮窗从屏幕上移除。
     *
     * @param context
     *            必须为应用程序的Context.
     */
    public static void removefloatWindowTwo(Context context) {
        if (floatWindowTwo != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(floatWindowTwo);
            floatWindowTwo = null;
        }
    }

    /**
     * 是否有悬浮窗(包括小悬浮窗和大悬浮窗)显示在屏幕上。
     *
     * @return 有悬浮窗显示在桌面上返回true，没有的话返回false。
     */
    public static boolean isWindowShowing() {
        return floatWindowOne != null || floatWindowTwo != null;
    }

    /**
     * 更新小悬浮窗的TextView上的数据，显示内存使用的百分比。
     *
     * @param context
     *            可传入应用程序上下文。
     */
    public static void updateUsedPercent(Context context) {
        if (floatWindowOne != null) {
            AppCompatTextView percentView = (AppCompatTextView) floatWindowOne.findViewById(R.id.float_window_text1);
            percentView.setText(getUsedPercentValue(context));
        }
    }


    /**
     * 如果WindowManager还未创建，则创建一个新的WindowManager返回。否则返回当前已创建的WindowManager。
     *
     * @param context
     *            必须为应用程序的Context.
     * @return WindowManager的实例，用于控制在屏幕上添加或移除悬浮窗。
     */
    private static WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

    /**
     * 如果ActivityManager还未创建，则创建一个新的ActivityManager返回。否则返回当前已创建的ActivityManager。
     *
     * @param context
     *            可传入应用程序上下文。
     * @return ActivityManager的实例，用于获取手机可用内存。
     */
    private static ActivityManager getActivityManager(Context context) {
        if (mActivityManager == null) {
            mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        }
        return mActivityManager;
    }

    /**
     * 计算已使用内存的百分比，并返回。
     *
     * @param context
     *            可传入应用程序上下文。
     * @return 已使用内存的百分比，以字符串形式返回。
     */
    public static String getUsedPercentValue(Context context) {
        String dir = "/proc/meminfo";
        try {
            FileReader fr = new FileReader(dir);
            BufferedReader br = new BufferedReader(fr, 2048);
            String memoryLine = br.readLine();
            String subMemoryLine = memoryLine.substring(memoryLine.indexOf("MemTotal:"));
            br.close();
            long totalMemorySize = Integer.parseInt(subMemoryLine.replaceAll("\\D+", ""));
            long availableSize = getAvailableMemory(context) / 1024;
            int percent = (int) ((totalMemorySize - availableSize) / (float) totalMemorySize * 100);
            return percent + "%";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "悬浮窗";
    }

    /**
     * 获取当前可用内存，返回数据以字节为单位。
     *
     * @param context
     *            可传入应用程序上下文。
     * @return 当前可用内存。
     */
    private static long getAvailableMemory(Context context) {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        getActivityManager(context).getMemoryInfo(mi);
        return mi.availMem;
    }

}
