package com.hs.damnicomniplusvic.controltemp.util.netutil;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/7/1.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public interface SearchMusicServer {
    @FormUrlEncoded
    @Headers({"Host: music.163.com","Referer:http://music.163.com/","Content-Type:application/x-www-form-urlencoded","Accept-Encoding:gzip, deflate"})
    @POST("web?csrf_token=e219d4a9120b87b89324c3a7588b1791")
    Observable<ResponseBody> search(@Field("params")String params,@Field("encSecKey")String key);

    @GET("searchList.php?ie=utf-8")
    Observable<ResponseBody> bsearch(@Query("qword")String qword, @Query("page")Integer page);
}
