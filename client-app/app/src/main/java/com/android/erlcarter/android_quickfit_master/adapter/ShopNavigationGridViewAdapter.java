package com.android.erlcarter.android_quickfit_master.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.data.NavigationItemData;

import java.util.List;

public class ShopNavigationGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<NavigationItemData> navigationInfo;

    public ShopNavigationGridViewAdapter(Context context, List<NavigationItemData> navigationInfo) {
        this.context = context;
        this.navigationInfo = navigationInfo;
    }

    @Override
    public int getCount() {
        return navigationInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return navigationInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            view = View.inflate(context,R.layout.shop_item_navigation,null);
            holder = new ViewHolder();
            holder.tv_shop_item_navigation_item = view.findViewById(R.id.tv_shop_item_navigation_item);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        NavigationItemData navigationItemData = navigationInfo.get(position);
        holder.tv_shop_item_navigation_item.setText(navigationItemData.getTitles());

        return view;
    }

    public class ViewHolder{
        TextView tv_shop_item_navigation_item;
    }
}
