package com.hs.damnicomniplusvic.controltemp.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class PlayMusicOnline extends Service {
    public PlayMusicOnline() {
    }
    private static MediaPlayer mMediaPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(null==intent)
            return super.onStartCommand(intent,flags,startId);
        String path=intent.getStringExtra("uri");
        mMediaPlayer=MediaPlayer.create(this, Uri.parse(path));
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    public static Boolean Start(){
        if(null!=mMediaPlayer) {
            mMediaPlayer.start();
            return true;
        }
        return false;
    }

    public static Boolean Pause(){
        if(null==mMediaPlayer)
            return false;
        if(mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            return true;
        }
        return false;
    }


    @Override
    public void onDestroy() {
        mMediaPlayer.release();
        mMediaPlayer=null;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
