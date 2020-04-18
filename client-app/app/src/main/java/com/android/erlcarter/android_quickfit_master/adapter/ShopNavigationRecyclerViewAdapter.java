package com.android.erlcarter.android_quickfit_master.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.data.NavigationItemData;

import java.util.List;

public class ShopNavigationRecyclerViewAdapter extends RecyclerView.Adapter<ShopNavigationRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private NavigationItemData niData;
    private List<NavigationItemData> list;
    private View inflater;

    public ShopNavigationRecyclerViewAdapter(Context context, List<NavigationItemData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context).inflate(R.layout.shop_item_navigation,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        niData = list.get(position);
        holder.tv_shop_item_navigation_item.setText(niData.getTitles());
    }

    @Override
    public int getItemCount() { return list.size(); }

    //内部类，绑定控件
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_shop_item_navigation_item;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_shop_item_navigation_item = (TextView) itemView.findViewById(R.id.tv_shop_item_navigation_item);
        }

    }

}
