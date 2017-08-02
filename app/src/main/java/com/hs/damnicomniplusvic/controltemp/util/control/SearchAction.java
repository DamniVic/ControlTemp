package com.hs.damnicomniplusvic.controltemp.util.control;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;


public class SearchAction {
	 Context mActivity;
	 String mKeyword;
	 private static String TAG="SearchAction";
	 //String searchEngine;

	/**
	 * 网页百度搜索
	 * @param name 要搜索的内容
	 * @param activity 上下文context
	 * **/
	 public SearchAction(String name, Context activity)
	  {
		mKeyword = name;
	    mActivity=activity;
	  }
	 
	 public void Search(){		 
		startWebSearch();	
	 }
	
	 private void startWebSearch()
	  {
		  Log.i(TAG,"正在搜索："+mKeyword+"...");
		 Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("http://m.baidu.com/s?word="+mKeyword));
			intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");//设置为系统自带浏览器启动
	    mActivity.startActivity(intent);
	  }
}
