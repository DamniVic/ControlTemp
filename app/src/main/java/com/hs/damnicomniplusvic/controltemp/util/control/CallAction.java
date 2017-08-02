package com.hs.damnicomniplusvic.controltemp.util.control;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


public class CallAction {
	private String mPerson = null;
	private String number = null;
	Context mActivity;
	String TAG = "CallAction";

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 100:
					mPerson = (String) msg.obj;
					start();
					break;
			}
			super.handleMessage(msg);
		}
	};

	/**
	 * 两个至少有一个，如果两个都有，则默认person有效
	 * @param person 联系人名称
	 * @param code 对方电话号码
	 * @param activity 上下文context
	* **/
	public CallAction(String person, String code, Context activity) {
		mPerson = person;
		number = code;
		mActivity = activity;
	}

	public void start()//打电话
	{
		if ((number == null) || (number.equals(""))) {
			if ((mPerson == null) || (mPerson.equals(""))) {

				//mActivity.speak("至少告诉我名字或者号码吧？", false);
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ""));
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mActivity.startActivity(intent);
			} else {
				mPerson = mPerson.trim();
				number = getNumberByName(mPerson, mActivity);
				if (number == null) {
					Log.i(TAG, "没有在通讯录中找到" + mPerson + "的号码。");
				} else {
					//打电话
					Log.i(TAG, "即将拨给" + mPerson + "...");
					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
					if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
						// TODO: Consider calling
						//    ActivityCompat#requestPermissions
						// here to request the missing permissions, and then overriding
						//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
						//                                          int[] grantResults)
						// to handle the case where the user grants the permission. See the documentation
						// for ActivityCompat#requestPermissions for more details.
						return;
					}
					mActivity.startActivity(intent);
				}
			    }
		}
		else{
			Log.i(TAG,"即将拨给"+number+"...");
       	 	Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
	        mActivity.startActivity(intent);
		}
	  }
	
	
	 private String getNumberByName(String name, Context context)//通过名字查找号码
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
}
