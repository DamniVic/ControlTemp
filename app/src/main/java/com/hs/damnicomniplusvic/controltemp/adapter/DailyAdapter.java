package com.hs.damnicomniplusvic.controltemp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hs.damnicomniplusvic.controltemp.R;
import com.hs.damnicomniplusvic.controltemp.bean.WeatherDailyBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/4/17.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public class DailyAdapter extends RecyclerView.Adapter{
    List<WeatherDailyBean.ResultsBean.DailyBean> mDailyBeanList;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_weather_item_layout,parent,false);
        return new DailyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DailyViewHolder dailyViewHolder= (DailyViewHolder) holder;
        WeatherDailyBean.ResultsBean.DailyBean bean=mDailyBeanList.get(position);
        dailyViewHolder.date.setText(bean.getDate());
        dailyViewHolder.day.setText(bean.getText_day());
        dailyViewHolder.night.setText(bean.getText_night());
        dailyViewHolder.temperature.setText(bean.getLow()+"℃~"+bean.getHigh()+"℃");
        dailyViewHolder.direction.setText(bean.getWind_direction());
        dailyViewHolder.speed.setText(bean.getWind_speed());
    }

    public void setDailyBeanList(List<WeatherDailyBean.ResultsBean.DailyBean> list){
        mDailyBeanList=list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDailyBeanList==null?0:mDailyBeanList.size();
    }

    class DailyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.daily_item_date)TextView date;
        @BindView(R.id.daily_item_day)TextView day;
        @BindView(R.id.daily_item_night)TextView night;
        @BindView(R.id.daily_item_temp)TextView temperature;
        @BindView(R.id.daily_item_wind_dir)TextView direction;
        @BindView(R.id.daily_item_wind_spe)TextView speed;
        public DailyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
