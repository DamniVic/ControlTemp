package com.hs.damnicomniplusvic.controltemp.util.control;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hs.damnicomniplusvic.controltemp.R;
import com.hs.damnicomniplusvic.controltemp.adapter.DailyAdapter;
import com.hs.damnicomniplusvic.controltemp.bean.WeatherDailyBean;
import com.hs.damnicomniplusvic.controltemp.bean.WeatherNowBean;
import com.hs.damnicomniplusvic.controltemp.bean.WeatherSuggBean;
import com.hs.damnicomniplusvic.controltemp.util.Network;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SearchWeather {

	private String mCity=null;
	private Context mActivity=null;
	private WeatherNowBean nowBean;
	private WeatherDailyBean dailyBean;
	private WeatherSuggBean suggBean;
	private static String TAG="SearchWeather";
	private DailyAdapter mDailyAdapter;
	private Dialog mDialog;

    @BindView(R.id.weather_address)
    TextView weatherAddress;
    @BindView(R.id.weather_text) TextView weatherText;
    @BindView(R.id.weather_temperature) TextView weatherTemperature;
    @BindView(R.id.weather_date) TextView weatherDate;
    @BindView(R.id.weather_washing_car) TextView weatherWashingCar;
    @BindView(R.id.weather_dressing) TextView weatherDressing;
    @BindView(R.id.weather_flu) TextView weatherFlu;
    @BindView(R.id.weather_sports) TextView weatherSports;
    @BindView(R.id.weather_travel) TextView weatherTravel;
    @BindView(R.id.weather_uv) TextView weatherUv;
    @BindView(R.id.weather_recycle)
    RecyclerView weatherRecycle;

    /**
	 * 根据城市名字搜索最近天气
	 * @param city 要搜索的城市名字
     * @param activity 上下文context
	 * **/
	public SearchWeather(String city, Context activity){
		mCity=city;
		mActivity=activity;
        View view=View.inflate(mActivity, R.layout.dialog_weather,null);
        ButterKnife.bind(this,view);
        mDialog=new Dialog(mActivity);
        mDialog.setContentView(view);
	}
	
	public void start(){
	    mDailyAdapter=new DailyAdapter();
        weatherRecycle.setLayoutManager(new LinearLayoutManager(mActivity));
        weatherRecycle.addItemDecoration(new DividerItemDecoration(this.mActivity, DividerItemDecoration.VERTICAL));
        weatherRecycle.setAdapter(mDailyAdapter);
        queryWeatherDialy(mCity);
        queryWeatherNow(mCity);
        queryWeatherSugg(mCity);
        mDialog.show();
	}


	private void queryWeatherNow(String address){
		Network.getmWeatherServer()
				.getWeatherNow(address)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<WeatherNowBean>() {
					@Override
					public void call(WeatherNowBean weatherNowBean) {
						nowBean = weatherNowBean;
						Log.i(TAG, "call: " + weatherNowBean.getResults().get(0).getNow().getText());
						initNowBean(weatherNowBean);
					}
				}, new Action1<Throwable>() {
					@Override
					public void call(Throwable throwable) {
						throwable.printStackTrace();
					}
				});
	}

	private void initNowBean(WeatherNowBean weatherNowBean){
		if(weatherNowBean!=null){
			nowBean = weatherNowBean;
			weatherAddress.setText(weatherNowBean.getResults().get(0).getLocation().getPath());
			weatherTemperature.setText(weatherNowBean.getResults().get(0).getNow().getTemperature() + "℃");
			weatherText.setText(weatherNowBean.getResults().get(0).getNow().getText());
			weatherDate.setText(weatherNowBean.getResults().get(0).getLast_update());
		}
	}

	private void queryWeatherDialy(String address){
		Network.getmWeatherServer()
				.getWeatherDaily(address)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<WeatherDailyBean>() {
					@Override
					public void call(WeatherDailyBean weatherDailyBean) {
						dailyBean = weatherDailyBean;
						Log.i(TAG, "call: " + weatherDailyBean.getResults().get(0).getDaily().get(0).getText_day());
						initDialyBean(weatherDailyBean);
					}
				}, new Action1<Throwable>() {
					@Override
					public void call(Throwable throwable) {
						throwable.printStackTrace();
					}
				});
	}

	private void initDialyBean(WeatherDailyBean weatherDailyBean){
		if(weatherDailyBean!=null){
			dailyBean=weatherDailyBean;
			mDailyAdapter.setDailyBeanList(dailyBean.getResults().get(0).getDaily());
		}
	}

	private void queryWeatherSugg(String address){
		Network.getmWeatherServer()
				.getWeatherSugg(address)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<WeatherSuggBean>() {
					@Override
					public void call(WeatherSuggBean weatherSuggBean) {
						suggBean = weatherSuggBean;
						Log.i(TAG, "call: " + weatherSuggBean.getResults().get(0).getSuggestion().getDressing().getBrief());
						initSuggBean(weatherSuggBean);
					}
				}, new Action1<Throwable>() {
					@Override
					public void call(Throwable throwable) {
						throwable.printStackTrace();
					}
				});
	}

	private void initSuggBean(WeatherSuggBean weatherSuggBean){
		if(weatherSuggBean!=null){
			suggBean=weatherSuggBean;
			weatherWashingCar.setText(weatherSuggBean.getResults().get(0).getSuggestion().getCar_washing().getBrief());
			weatherDressing.setText(weatherSuggBean.getResults().get(0).getSuggestion().getDressing().getBrief());
			weatherFlu.setText(weatherSuggBean.getResults().get(0).getSuggestion().getFlu().getBrief());
			weatherSports.setText(weatherSuggBean.getResults().get(0).getSuggestion().getSport().getBrief());
			weatherTravel.setText(weatherSuggBean.getResults().get(0).getSuggestion().getTravel().getBrief());
			weatherUv.setText(weatherSuggBean.getResults().get(0).getSuggestion().getUv().getBrief());
		}
	}
	
}
