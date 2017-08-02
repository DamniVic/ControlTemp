package com.hs.damnicomniplusvic.controltemp.util;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.provider.MediaStore;

import com.hs.damnicomniplusvic.controltemp.bean.SongBean;

import java.util.ArrayList;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/7/4.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public class AudioUtil {

    /**
     * 获取sd卡所有的音乐文件 
     *
     * @return
     * @throws Exception
     */
    public static ArrayList<SongBean> getAllSongBeans(Context context) {
        MediaScannerConnection.scanFile(context, new String[] { Environment
                .getExternalStorageDirectory().getAbsolutePath() }, null, null);

        ArrayList<SongBean> SongBeans = null;

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.YEAR,
                        MediaStore.Audio.Media.MIME_TYPE,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.DATA },
                MediaStore.Audio.Media.MIME_TYPE + "=? or "
                        + MediaStore.Audio.Media.MIME_TYPE + "=?",
                new String[] { "audio/mpeg", "audio/x-ms-wma" }, null);

        SongBeans = new ArrayList<SongBean>();

        if (cursor.moveToFirst()) {

            SongBean SongBean = null;

            do {
                SongBean = new SongBean();
                // 文件名  
                SongBean.setFileName(cursor.getString(1));
                // 歌曲名  
                SongBean.setTitle(cursor.getString(2));
                // 时长  
                SongBean.setDuration(cursor.getInt(3));
                // 歌手名  
                SongBean.setSinger(cursor.getString(4));
                // 专辑名  
                SongBean.setAlbum(cursor.getString(5));
                // 年代  
                if (cursor.getString(6) != null) {
                    SongBean.setYear(cursor.getString(6));
                } else {
                    SongBean.setYear("未知");
                }
                // 歌曲格式  
                if ("audio/mpeg".equals(cursor.getString(7).trim())) {
                    SongBean.setType("mp3");
                } else if ("audio/x-ms-wma".equals(cursor.getString(7).trim())) {
                    SongBean.setType("wma");
                }
                // 文件大小  
                if (cursor.getString(8) != null) {
                    float size = cursor.getInt(8) / 1024f / 1024f;
                    if(size<3f)
                        continue;
                    SongBean.setSize((size + "").substring(0, 4) + "M");
                } else {
                    SongBean.setSize("未知");
                }
                // 文件路径  
                if (cursor.getString(9) != null) {
                    SongBean.setFileUrl(cursor.getString(9));
                }
                SongBeans.add(SongBean);
            } while (cursor.moveToNext());

            cursor.close();

        }
        return SongBeans;
    }

}
