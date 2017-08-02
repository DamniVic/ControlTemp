package com.hs.damnicomniplusvic.controltemp.util.control;

import android.content.Context;
import android.content.Intent;

public class MessageView {
	Context mActivity;

	/**
	 * 打开发短信界面
	 * @param activity 上下文context
	 * ***/
	public MessageView(Context activity){
		mActivity=activity;
	}
	
	public void start(){
		Intent intent=new Intent();
		intent.setClassName("com.android.mms","com.android.mms.ui.ConversationList");
		mActivity.startActivity(intent);
	}
}
