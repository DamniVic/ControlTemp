package com.hs.damnicomniplusvic.controltemp.util.netutil;


import com.hs.damnicomniplusvic.controltemp.bean.WeatherDailyBean;
import com.hs.damnicomniplusvic.controltemp.bean.WeatherNowBean;
import com.hs.damnicomniplusvic.controltemp.bean.WeatherSuggBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/4/13.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public interface WeatherServer {
    @GET("v3/weather/now.json?key=ytqnyktcesqt8psl&language=zh-Hans&unit=c")
    Observable<WeatherNowBean> getWeatherNow(@Query("location") String address);

    @GET("v3/weather/daily.json?key=ytqnyktcesqt8psl&language=zh-Hans&unit=c&start=0&days=3")
    Observable<WeatherDailyBean> getWeatherDaily(@Query("location") String address);

    @GET("v3/life/suggestion.json?key=ytqnyktcesqt8psl&language=zh-Hans")
    Observable<WeatherSuggBean> getWeatherSugg(@Query("location") String address);
}
