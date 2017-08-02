package com.hs.damnicomniplusvic.controltemp.util.control;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class OpenWebsite {
	
	private String mUrl=null,mName=null;
	Context mActivity;
	private static String TAG="OpenWebsite";

	/**
	 * 打开网站或者根据名字搜索
	 * @param url 要打开的网址
	 * @param name 要搜索的内容
	 * @param activity 上下文context
	 * **/
	public OpenWebsite(String url, String name, Context activity){
		mUrl=url;
		mName=name;
		mActivity=activity;
	}
	
	public void start(){
		if(mUrl==null){
			Log.i(TAG,"未找到"+mName+"，正在百度...");
			 Intent intent = new Intent();
					intent.setAction(Intent.ACTION_VIEW);
					intent.setData(Uri.parse("http://m.baidu.com/s?word="+mName));
					intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");//设置为系统自带浏览器启动
			    mActivity.startActivity(intent);
		}
		else{
			Log.i(TAG,"正在打开"+mUrl+"...");
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(mUrl));
			intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");//设置为系统自带浏览器启动
			mActivity.startActivity(intent);
		}
		
	}

}
