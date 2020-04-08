package com.android.erlcarter.android_quickfit_master.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.data.CommuityRecyclerViewData;

import java.util.List;

public class CommuityRecyclerViewAdapter extends RecyclerView.Adapter<CommuityRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private CommuityRecyclerViewData crvData;
    private List<CommuityRecyclerViewData> list;
    private View inflater;

    public CommuityRecyclerViewAdapter(Context context, List<CommuityRecyclerViewData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context).inflate(R.layout.commuity_activity_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        crvData = list.get(position);
        holder.item_title.setText(crvData.getTitle());
        holder.item_content.setText(crvData.getContent());
        holder.item_img.setImageResource(crvData.getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //内部类，绑定控件
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView item_img;
        TextView item_title;
        TextView item_content;
        public MyViewHolder(View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.iv_item_activity_img);
            item_title = (TextView) itemView.findViewById(R.id.tv_item_activity_title);
            item_content = (TextView) itemView.findViewById(R.id.tv_item_activity_content);
        }

    }

}
