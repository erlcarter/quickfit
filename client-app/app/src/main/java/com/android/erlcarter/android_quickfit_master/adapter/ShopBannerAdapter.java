package com.android.erlcarter.android_quickfit_master.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.baseconfig.CommonBaseAdapter;
import com.android.erlcarter.android_quickfit_master.data.Icon;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class ShopBannerAdapter extends BaseAdapter{

    private Context context;
    private List<Icon> iconInfo;

    public ShopBannerAdapter(Context context, List<Icon> iconInfo) {
        this.context = context;
        this.iconInfo = iconInfo;
    }

    @Override
    public int getCount() {
        return iconInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return iconInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            view = View.inflate(context,R.layout.item_grid_shop_icon,null);
            holder = new ViewHolder();
            holder.fit_img_icon = view.findViewById(R.id.fit_img_icon);
            holder.fit_txt_icon = view.findViewById(R.id.fit_txt_icon);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Icon icon = iconInfo.get(position);
        holder.fit_txt_icon.setText(icon.getText());
        holder.fit_img_icon.setImageResource(icon.getImage());

        return view;
    }

    public class ViewHolder{
        ImageView fit_img_icon;
        TextView fit_txt_icon;
    }

}
