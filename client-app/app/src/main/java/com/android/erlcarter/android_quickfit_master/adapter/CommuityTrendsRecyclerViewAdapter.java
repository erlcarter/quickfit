package com.android.erlcarter.android_quickfit_master.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.data.CommuityTrendsRecyclerViewData;

import java.util.List;

public class CommuityTrendsRecyclerViewAdapter extends RecyclerView.Adapter<CommuityTrendsRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private CommuityTrendsRecyclerViewData ctrvData;
    private List<CommuityTrendsRecyclerViewData> trendlist;
    private View inflater;

    public CommuityTrendsRecyclerViewAdapter(Context context, List<CommuityTrendsRecyclerViewData> trendlist) {
        this.context = context;
        this.trendlist = trendlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context).inflate(R.layout.commuity_trends_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ctrvData = trendlist.get(position);
        holder.tv_commuity_trend_user_name.setText(ctrvData.getUserName());
        holder.tv_commuity_trend_content_text.setText(ctrvData.getContent());
        holder.tv_dianzan.setText(ctrvData.getDianzanNum());
        holder.tv_pinglun.setText(ctrvData.getPingjiaNum());
        holder.tv_pinglun.setText(ctrvData.getShoucangNum());
        holder.iv_commuity_trend_user_img.setImageResource(ctrvData.getUserImg());
    }

    @Override
    public int getItemCount() {
        return trendlist.size();
    }

    //内部类，绑定控件
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_commuity_trend_user_img;
        TextView tv_commuity_trend_user_name,tv_commuity_trend_content_text,tv_dianzan,tv_pinglun,tv_shoucang;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv_commuity_trend_user_img = itemView.findViewById(R.id.iv_commuity_trend_user_img);
            tv_commuity_trend_user_name = itemView.findViewById(R.id.tv_commuity_trend_user_name);
            tv_commuity_trend_content_text = itemView.findViewById(R.id.tv_commuity_trend_content_text);
            tv_dianzan = itemView.findViewById(R.id.tv_dianzan);
            tv_pinglun = itemView.findViewById(R.id.tv_pinglun);
            tv_shoucang = itemView.findViewById(R.id.tv_shoucang);
        }

    }

}
