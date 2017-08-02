package com.hs.damnicomniplusvic.controltemp.util.control;

import android.content.Context;
import android.content.Intent;

public class CallView {
	
	Context mActivity;

	/**
	 * 打开通话界面
	 * @param activity 上下文context
	 * **/
	public CallView(Context activity){
		mActivity=activity;
	}
	
	public void start(){
		Intent intent=new Intent();
		intent.setAction(Intent.ACTION_CALL_BUTTON);
		mActivity.startActivity(intent);
	}
}
