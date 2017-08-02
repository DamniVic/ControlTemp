package com.hs.damnicomniplusvic.controltemp.util.control;

import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/7/12.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public class ClockView {

    private Context mActivity;
    private Integer hour;
    private Integer minutes;
    private Integer days;
    private String message;
    private boolean vibrate=true;
    private String rangtone;
    private boolean skipui=true;

    /**
     * 设置闹钟
     * @param hour 小时，范围0~23
     * @param minutes 分钟，范围0~59
     * @param days 星期，范围1~7（星期天为1，星期一位2依次类推）
     * @param message 提醒时候的消息
     * @param vibrate 是否震动
     * @param rangtone 铃声，路径
     * @param skipui 是否有界面提示是否关闭闹钟。
     * @param activity 上下文context
     * **/
    public ClockView(Integer hour, Integer minutes, int days,String message,boolean vibrate, String rangtone,boolean skipui,Context activity){
        mActivity=activity;
        this.hour=hour;
        this.minutes=minutes;
        this.days=days;
        this.message=message;
        this.vibrate=vibrate;
        this.rangtone=rangtone;
        this.skipui=skipui;
    }



    public void start(){
        Intent alarmas = new Intent(AlarmClock.ACTION_SET_ALARM);
        if(null!=hour)
            alarmas.putExtra(AlarmClock.EXTRA_HOUR,hour);
        if(null!=minutes)
            alarmas.putExtra(AlarmClock.EXTRA_MINUTES,minutes);
        if(null!=days)
            alarmas.putExtra(AlarmClock.EXTRA_DAYS,days);
        if(null!=message)
            alarmas.putExtra(AlarmClock.EXTRA_MESSAGE,message);
        alarmas.putExtra(AlarmClock.EXTRA_VIBRATE,vibrate);
        if(null!=rangtone)
            alarmas.putExtra(AlarmClock.EXTRA_RINGTONE,rangtone);
        alarmas.putExtra(AlarmClock.EXTRA_SKIP_UI,skipui);
        mActivity.startActivity(alarmas);
    }
}
