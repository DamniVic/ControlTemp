package com.hs.damnicomniplusvic.controltemp.util;

import com.hs.damnicomniplusvic.controltemp.util.netutil.DetailMusicServer;
import com.hs.damnicomniplusvic.controltemp.util.netutil.SearchMusicServer;
import com.hs.damnicomniplusvic.controltemp.util.netutil.TranslateServer;
import com.hs.damnicomniplusvic.controltemp.util.netutil.WeatherServer;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/6/30.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public class Network {
    private static TranslateServer STranslateServer;
    private static SearchMusicServer mSearchMusicServer;
    private static DetailMusicServer mDetailMusicServer;
    private static WeatherServer mWeatherServer;
    private static final String TRANSLATEURL = "http://openapi.baidu.com/public/2.0/bmt/";
    private static final String MusicUrl = "http://music.163.com/api/search/get/";
    private static final String BAIDUURL = "http://musicmini.baidu.com/app/search/";
    private static final String MUSICURL1="http://ting.baidu.com/data/music/";
    private static String weatherUrl="https://api.seniverse.com/";
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    public static TranslateServer getmTranslateServer() {
        if(STranslateServer==null)
        {
            Retrofit retrofit=new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(TRANSLATEURL)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            STranslateServer=retrofit.create(TranslateServer.class);
        }
        return STranslateServer;
    }
    public static SearchMusicServer getmSearchMusicServer(){
        if(mSearchMusicServer==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .baseUrl(BAIDUURL)
                    .client(okHttpClient)
                    .build();
            mSearchMusicServer = retrofit.create(SearchMusicServer.class);
        }
        return mSearchMusicServer;
    }
    public static DetailMusicServer getmDetailMusicServer(){
        if(null==mDetailMusicServer){
            Retrofit retrofit=new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(MUSICURL1)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            mDetailMusicServer=retrofit.create(DetailMusicServer.class);
        }
        return mDetailMusicServer;
    }
    public static WeatherServer getmWeatherServer(){
        if (mWeatherServer == null) {
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(weatherUrl)
                    .client(okHttpClient)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            mWeatherServer=retrofit.create(WeatherServer.class);
        }
        return mWeatherServer;
    }
}
