package com.hs.damnicomniplusvic.controltemp.util.control;

import android.content.Context;
import android.util.Log;

import com.hs.damnicomniplusvic.controltemp.util.ConstValue;
import com.hs.damnicomniplusvic.controltemp.util.Network;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Translation {
    private String mTarget, mContent, mResult;
    private Context mActivity;
    private static String TAG = "Translation";

    Observer<ResponseBody> transObserver = new Observer<ResponseBody>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                Log.i(TAG, "onNext: " + new String(responseBody.bytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 翻译文本
     *                 auto	自动检测
     *                 zh	中文
     *                 en	英语
     *                 yue	粤语
     *                 wyw	文言文
     *                 jp	日语
     *                 kor	韩语
     *                 fra	法语
     *                 spa	西班牙语
     *                 th	泰语
     *                 ara	阿拉伯语
     *                 ru	俄语
     *                 pt	葡萄牙语
     *                 de	德语
     *                 it	意大利语
     *                 el	希腊语
     *                 nl	荷兰语
     *                 pl	波兰语
     *                 bul	保加利亚语
     *                 est	爱沙尼亚语
     *                 dan	丹麦语
     *                 fin	芬兰语
     *                 cs	捷克语
     *                 rom	罗马尼亚语
     *                 slo	斯洛文尼亚语
     *                 swe	瑞典语
     *                 hu	匈牙利语
     *                 cht	繁体中文
     *                 vie	越南语
     * @param target   目标语言
     * @param content  翻译的内容
     * @param activity 上下文context
     **/
    public Translation(String target, String content, Context activity) {
        mTarget = target;
        mContent = content;
        mActivity = activity;
    }

    public void start() {//开始翻译
        Observable<ResponseBody> observable = Network.getmTranslateServer()
                .Qurey(ConstValue.API_KEY, mContent, "auto", mTarget)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(transObserver);
    }
}
