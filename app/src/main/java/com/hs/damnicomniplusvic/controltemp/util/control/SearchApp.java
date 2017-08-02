package com.hs.damnicomniplusvic.controltemp.util.control;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class SearchApp {
	private String mName;
	Context mActivity;
	private static String TAG="SearchApp";

	/**
	 * 从应用商店里面搜索应用
	 * @param name 要搜素应用的名字
	 * @param activity 上下文context
	 * **/
	public SearchApp( String name, Context activity){
		mName=name;
		mActivity=activity;
	}
	
	public void start(){
		Log.i(TAG,"正在搜索...");
		Intent intent = new Intent(Intent.ACTION_VIEW);
	    intent.setData(Uri.parse("market://search?q="+mName));
	    mActivity.startActivity(intent);
	}
}
