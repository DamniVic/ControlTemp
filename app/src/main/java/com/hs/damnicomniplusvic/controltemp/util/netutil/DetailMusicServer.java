package com.hs.damnicomniplusvic.controltemp.util.netutil;

import com.hs.damnicomniplusvic.controltemp.bean.DetailMusicInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/7/3.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public interface DetailMusicServer {
    @GET("links")
    Observable<DetailMusicInfo> detail(@Query("songIds")String id);
}
