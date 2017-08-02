package com.hs.damnicomniplusvic.controltemp.util.netutil;

import retrofit2.http.FormUrlEncoded;
import rx.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/6/30.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public interface TranslateServer {
    @FormUrlEncoded()
    @POST("translate")
    Observable<ResponseBody> Qurey(@Field("client_id")String apikey,@Field("q")String text,@Field("from")String from,@Field("to")String to);
}
