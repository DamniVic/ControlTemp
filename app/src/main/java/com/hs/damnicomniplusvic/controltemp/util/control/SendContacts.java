package com.hs.damnicomniplusvic.controltemp.util.control;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;

public class SendContacts {
	
	private String mName,mReceiver,mNumberSender,mNumberReceiver;
	private static String TAG="SendContacts";
    Context mActivity;

    /**
	 * 发送名片给某人，
	 * @param name 将要发送的名片（联系人的名字）
	 * @param receiver 发送的对象（接收的人的名字）
	 * @param activity 上下文context
	 * **/
	public SendContacts(String name, String receiver, Context activity){
		mName=name;
		mReceiver=receiver;
		mActivity=activity;
	}
	
	public void start(){
		mNumberSender=getNumberByName(mName,mActivity);
		mNumberReceiver=getNumberByName(mReceiver,mActivity);
		if((mNumberSender == null)||(mNumberReceiver==null))
        {
			if(mNumberSender == null){
				Log.i(TAG,"通讯录没有找到"+mName);
			}
			else Log.i(TAG,"通讯录没有找到"+mReceiver);
          
        }else{	    
       	 //发名片
       	 SmsManager smsManager = SmsManager.getDefault();
             smsManager.sendTextMessage(mNumberReceiver, null, mNumberSender, null, null);
             insertDB(mNumberReceiver,mNumberSender);
       	
       }
	}
	
	private String getNumberByName(String name, Context context)
	  {
		 Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, name);
		  ContentResolver resolver  = context.getContentResolver();
		  Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Contacts._ID}, null, null, null);
		  if((cursor!=null)&&(cursor.moveToFirst())){
			  int idCoulmn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
			  long id = cursor.getLong(idCoulmn);
		      cursor.close();
		      cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,  new String[]{"data1"}, "contact_id = ?",  new String[]{Long.toString(id)}, null);
		      if ((cursor != null) && (cursor.moveToFirst()))
		      {
		        int m = cursor.getColumnIndex("data1");
		        String num = cursor.getString(m);
		        cursor.close();
		       return num;
		      }	      
		  }
		  return null;
	  }
	
	 private void insertDB(String number, String content){//将发送的短信插入系统数据库中，使其在短信界面显示
	    	//////////////////////会抛出null的异常---已解决--- mActivity.getContentResolver()才可以
	    	try{
		    	ContentValues values = new ContentValues();
		    	values.put("date", System.currentTimeMillis());
		    	 //阅读状态              
		        values.put("read", 0);             
		         //1为收 2为发             
		       values.put("type", 2);           
		         //送达号码              
		      // values.put("status", -1);
		       values.put("address",number);             
		         //送达内容            
		       values.put("body", content);             
		         //插入短信库    
		      // getContentResolver
		       mActivity.getContentResolver().insert(Uri.parse("content://sms/sent"), values);
				Log.i(TAG,"短信发送成功");
	    	}catch (Exception e) {
	            Log.d("dd", "插入数据库问题："+e.getMessage());
	    	  }
	    }
}
