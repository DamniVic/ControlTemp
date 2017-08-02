package com.hs.damnicomniplusvic.controltemp.util.control;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.hs.damnicomniplusvic.controltemp.bean.DetailMusicInfo;
import com.hs.damnicomniplusvic.controltemp.service.PlayMusicOnline;
import com.hs.damnicomniplusvic.controltemp.util.Network;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/7/1.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public class SearchMusic {
    private static String TAG="SearchMusic";
    private String name;
    private Context mContext;
    /**
     * 在线搜索音乐并播放
     * @param musicname 要搜索的音乐的音乐名字
     * @param context 上下文context
     * **/
    public SearchMusic(String musicname, Context context){
        name=musicname;
        mContext=context;
    }
    Observer<DetailMusicInfo> mMusicInfoObserver=new Observer<DetailMusicInfo>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(DetailMusicInfo detailMusicInfo) {
            if(null==detailMusicInfo.getData())
                return;
            if(null==detailMusicInfo.getData().getSongList()||detailMusicInfo.getData().getSongList().size()==0)
                return;
            String link=detailMusicInfo.getData().getSongList().get(0).getSongLink();
            Log.i(TAG, "onNext: "+link);
            Intent intent = new Intent();
            intent.setClass(mContext, PlayMusicOnline.class);
            intent.putExtra("uri",link);
            mContext.startService(intent);
        }
    };
    public void start(){
        Subscription subscribe = Network.getmSearchMusicServer()
//                .search("afsr06ovYhTq9cSkKudYVob/0eNeTj9FQd3Y2ApuESEeW4gEaNA0L9k6aOjKvG2Cb2DvPVHFJRvoNZCDZM4/YXAJL381q3p3vOoe+Cef79b47kEfVzOXJw9kT+ORuF8R", "b7fa8b96d0654b188c2b3f4809649b63bbafb842e25510ce401fff0d3bbd275b2d32864730b9e9ca507c6807f0fa4942c6e00cc66c2a39e8a2e8828bdc15e3bba6450df0ea85aa154e167ade4525ce46cce48ced96b5a82bc866b7f76ad1c2826f1394b3e533de0a44c28496efea18cfed6a5442eb3f9a9888ae59d5df2909c4")
                .bsearch(name, 1)
                .map(new Func1<ResponseBody, String>() {
                    @Override
                    public String call(ResponseBody responseBody) {
                        String html = null;
                        try {
                            html = new String(responseBody.bytes());
                            Log.i(TAG, "call: "+html);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (html != null) {
                            Pattern p = Pattern.compile("<a href=\"javascript:;\" onclick=\"playSong\\(&#039;(.*?)&#039;,&#039;(.*?)&#039;,&#039;(.*?)&#039;,&#039;.*?&#039;,.*?,&#039;.*?&#039;,&#039;.*?&#039;\\)\" class=\"play\" title=\"播放\">play</a>");
                            Matcher result = p.matcher(html);
                            Log.i(TAG, "call: "+p.pattern());
                            Log.i(TAG, "call: " + result.find());
                            if (result.find()) {
                                Log.i(TAG, "call: " + result.groupCount());
                                for (int i = 1; i < result.groupCount() + 1; i++) {
                                    Log.i(TAG, "call: " + result.group(i));
                                }
                                return result.group(1);
                            }
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Network.getmDetailMusicServer()
                                .detail(s)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(mMusicInfoObserver);
                    }
                });
//        subscribe.unsubscribe();
    }
}
