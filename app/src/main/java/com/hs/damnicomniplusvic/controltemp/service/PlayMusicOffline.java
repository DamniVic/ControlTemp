package com.hs.damnicomniplusvic.controltemp.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.hs.damnicomniplusvic.controltemp.bean.SongBean;

import java.io.IOException;
import java.util.List;

public class PlayMusicOffline extends Service {
    public PlayMusicOffline() {
    }
    private static MediaPlayer mMediaPlayer;
    private static String TAG="PlayMusicOffline";
    private static int songIndex=0;
    private static List<SongBean> songBeanList=null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(null==intent)
            return super.onStartCommand(intent,flags,startId);
        songBeanList=intent.getParcelableArrayListExtra("song");
        mMediaPlayer=new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(new CompletionListener());
        nextsong();
        return super.onStartCommand(intent, flags, startId);
    }

    private final class CompletionListener implements MediaPlayer.OnCompletionListener{

        @Override
        public void onCompletion(MediaPlayer mp) {
            songIndex=++songIndex%songBeanList.size();
            nextsong();
        }
    }
    private void nextsong() {
        if (songIndex < songBeanList.size()) {
            songplay();
        }
        else {
            songBeanList.clear();
            songIndex = 0;
        }
    }
    private void songplay() {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(songBeanList.get(songIndex).getFileUrl());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            Log.i(TAG, "songplay: play song:"+songBeanList.get(songIndex).getTitle());
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void next(){
        songIndex++;
        if(null==mMediaPlayer)
            return;
        if(mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mMediaPlayer.stop();
        }
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(songBeanList.get(songIndex).getFileUrl());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            Log.i(TAG, "next: play song:"+songBeanList.get(songIndex).getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void previous(){
        songIndex--;
        if(null==mMediaPlayer)
            return;
        if(mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mMediaPlayer.stop();
        }
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(songBeanList.get(songIndex).getFileUrl());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            Log.i(TAG, "next: play song:"+songBeanList.get(songIndex).getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onDestroy() {
        mMediaPlayer.release();
        mMediaPlayer=null;
        super.onDestroy();
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
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
