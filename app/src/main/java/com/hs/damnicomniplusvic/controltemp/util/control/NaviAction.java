package com.hs.damnicomniplusvic.controltemp.util.control;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/7/11.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public class NaviAction {
    private String des;//目的地
    private Context mContext;//上下文

    /**
     * 打开百度地图并导航
     * @param des 目的地
     * @param context 上下文context
     * **/
    public NaviAction(String des, Context context) {
        this.des = des;
        this.mContext = context;
    }

    public void start(){
        if(null!=des&&null!=mContext){
            try {
                Intent i1 = new Intent();// 驾车导航
                String url = String.format("baidumap://map/navi?query=%s", des);
                i1.setData(Uri.parse(url));
                mContext.startActivity(i1);
            }
            catch (ActivityNotFoundException e){
                Toast.makeText(mContext,"您手机没有安装百度地图",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
