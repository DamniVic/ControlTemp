package com.hs.damnicomniplusvic.controltemp.util.control;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.CalendarContract;

import java.util.Calendar;
import java.util.TimeZone;

import static com.hs.damnicomniplusvic.controltemp.util.ConstValue.CALANDER_URL;
import static com.hs.damnicomniplusvic.controltemp.util.ConstValue.CALENDARS_ACCOUNT_NAME;
import static com.hs.damnicomniplusvic.controltemp.util.ConstValue.CALENDARS_ACCOUNT_TYPE;
import static com.hs.damnicomniplusvic.controltemp.util.ConstValue.CALENDARS_DISPLAY_NAME;
import static com.hs.damnicomniplusvic.controltemp.util.ConstValue.CALENDARS_NAME;

public class ScheduleView {

	private Context mActivity;
	private String title;
	private String description;

	/**
     * 打开日历并填写备忘录
     * @param title 备忘录标题
     * @param description 备忘录详细描述
     * @param activity 上下文context
     * **/
	public ScheduleView(String title,String description,Context activity){
		mActivity=activity;
		this.title=title;
		this.description=description;
	}

	@SuppressLint("NewApi")
	public void start(){
	    if(null!=title&&description!=null) {
            int calID = checkAndAddCalendarAccount(mActivity);
            if (calID < 0) {
                // 获取账户id失败直接返回，添加日历事件失败
                return;
            }
            Calendar mCalendar = Calendar.getInstance();
            long beginTime = SystemClock.currentThreadTimeMillis() + 60000;
            mCalendar.setTimeInMillis(beginTime);//设置开始时间
            long start = mCalendar.getTime().getTime();
            mCalendar.setTimeInMillis(start + 3600000);//设置终止时间
            long end = mCalendar.getTime().getTime();
            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setData(CalendarContract.Events.CONTENT_URI);
            intent.putExtra(CalendarContract.Events.CALENDAR_ID, calID);
            intent.putExtra(CalendarContract.Events.DTSTART, start);
            intent.putExtra(CalendarContract.Events.DTEND, end);
            intent.putExtra(CalendarContract.Events.HAS_ALARM, 1);
            intent.putExtra(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai");
            intent.putExtra(CalendarContract.Events.TITLE, title);
            intent.putExtra(CalendarContract.Events.DESCRIPTION, description);
            mActivity.startActivity(intent);
        }
	}

	//检查是否存在账户，如果有则返回账户ID，没有返回-1
	private static int checkCalendarAccount(Context context) {
        Cursor userCursor = context.getContentResolver().query(Uri.parse(CALANDER_URL), null, null, null, null);
        try {
            if (userCursor == null)//查询返回空值
                return -1;
            int count = userCursor.getCount();
            if (count > 0) {//存在现有账户，取第一个账户的id返回
                userCursor.moveToFirst();
                return userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID));
            } else {
                return -1;
            }
        } finally {
            if (userCursor != null) {
                userCursor.close();
            }
        }
	}

	//添加一个账户
	private static long addCalendarAccount(Context context) {
		TimeZone timeZone = TimeZone.getDefault();
		ContentValues value = new ContentValues();
		value.put(CalendarContract.Calendars.NAME, CALENDARS_NAME);

		value.put(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME);
		value.put(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE);
		value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CALENDARS_DISPLAY_NAME);
		value.put(CalendarContract.Calendars.VISIBLE, 1);
		value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE);
		value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
		value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
		value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
		value.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDARS_ACCOUNT_NAME);
		value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);

		Uri calendarUri = Uri.parse(CALANDER_URL);
		calendarUri = calendarUri.buildUpon()
				.appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
				.appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
				.appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE)
				.build();

		Uri result = context.getContentResolver().insert(calendarUri, value);
		long id = result == null ? -1 : ContentUris.parseId(result);
		return id;
	}

	//检查是否已经添加了日历账户，如果没有添加先添加一个日历账户再查询
	private static int checkAndAddCalendarAccount(Context context){
		int oldId = checkCalendarAccount(context);
		if( oldId >= 0 ){
			return oldId;
		}else{
			long addId = addCalendarAccount(context);
			if (addId >= 0) {
				return checkCalendarAccount(context);
			} else {
				return -1;
			}
		}
	}
}
