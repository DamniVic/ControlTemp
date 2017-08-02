package com.hs.damnicomniplusvic.controltemp.adapter;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hs.damnicomniplusvic.controltemp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/7/10.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public class FloatWindowAdapter extends RecyclerView.Adapter {

    private List<String> mStringList;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(),R.layout.float_window_recycle_layout,null);
        return new BtnViewHolder(view);
    }

    private ClickCallBack mClickCallBack;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BtnViewHolder viewHolder= (BtnViewHolder) holder;
        viewHolder.btn.setText(mStringList.get(position));
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatButton btn= (AppCompatButton) v;
                if(null!=mClickCallBack) {
                    mClickCallBack.click(btn.getText().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null==mStringList?0:mStringList.size();
    }

    public void setClickCallBack(ClickCallBack clickCallBack){
        mClickCallBack=clickCallBack;
    }
    public void setStringList(List<String> list){
        mStringList=list;
        notifyDataSetChanged();
    }

    class BtnViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.recycle_btn)AppCompatButton btn;
        public BtnViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface ClickCallBack{
        void click(String name);
    }
}
