package com.android.erlcarter.android_quickfit_master.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.data.CommuityRecyclerViewData;
import com.android.erlcarter.android_quickfit_master.data.Topic;

import java.util.List;

public class CommuityTopicRecyclerViewAdapter extends RecyclerView.Adapter<CommuityTopicRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private Topic topicData;
    private List<Topic> list;
    private View inflater;

    public CommuityTopicRecyclerViewAdapter(Context context, List<Topic> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context).inflate(R.layout.commuity_topic_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        topicData = list.get(position);
        holder.btn_topic.setText(topicData.getTopicTitle());
        holder.btn_topic.setBackgroundResource(topicData.getBackgroundDrawable());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //内部类，绑定控件
    class MyViewHolder extends RecyclerView.ViewHolder{
        Button btn_topic;
        public MyViewHolder(View itemView) {
            super(itemView);
            btn_topic = itemView.findViewById(R.id.btn_topic);
        }

    }

}
