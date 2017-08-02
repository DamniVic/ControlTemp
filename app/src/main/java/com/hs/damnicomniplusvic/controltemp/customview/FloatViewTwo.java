package com.hs.damnicomniplusvic.controltemp.customview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.hs.damnicomniplusvic.controltemp.R;
import com.hs.damnicomniplusvic.controltemp.adapter.FloatWindowAdapter;
import com.hs.damnicomniplusvic.controltemp.util.MyWindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/7/10.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public class FloatViewTwo extends LinearLayout {
    private static final String TAG = "FloatViewTwo";
    /**
     * 悬浮框的宽高
     * **/
    public static int viewWidth;
    public static int viewHeight;
    /**
    *管理窗口的管理者
    * */
    private WindowManager windowManager;
    /**
     * 显示相关设置的按钮
     * **/
    private RecyclerView mRecyclerView;
    /**
     * recycleview的适配器，
     * **/
    private FloatWindowAdapter mFloatWindowAdapter;
    /**
     * recycleview 的数据容器
     * **/
    private List<String> mStringList=new ArrayList<String>();
    public FloatViewTwo(Context context) {
        super(context);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater.from(context).inflate(R.layout.float_window_layout_two, this);
        View view = findViewById(R.id.float_window_two);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;
        mRecyclerView= (RecyclerView) findViewById(R.id.float_window_recycle);
        mFloatWindowAdapter=new FloatWindowAdapter();
        mRecyclerView.setAdapter(mFloatWindowAdapter);
        init();
    }

    private void init(){
        for(int i=0;i<5;i++){
            mStringList.add(i+"");
        }
        mFloatWindowAdapter.setStringList(mStringList);
        mFloatWindowAdapter.setClickCallBack(new FloatWindowAdapter.ClickCallBack() {
            @Override
            public void click(String name) {
                Log.i(TAG, "click: "+name);
                switch (name){
                    case "0":
                        MyWindowManager.removefloatWindowTwo(getContext());
                        MyWindowManager.createfloatWindowOne(getContext());
                        break;
                    case "1":
                        break;
                    case "2":
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
