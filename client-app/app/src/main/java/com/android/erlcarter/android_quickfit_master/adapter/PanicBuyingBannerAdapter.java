package com.android.erlcarter.android_quickfit_master.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.data.Icon;
import com.android.erlcarter.android_quickfit_master.data.IconPanic;

import java.util.List;

public class PanicBuyingBannerAdapter extends BaseAdapter {

    private Context context;
    private List<IconPanic> iconInfo;

    public PanicBuyingBannerAdapter(Context context, List<IconPanic> iconInfo) {
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
        ViewHolder viewHolder;
        if (view == null){
            view = View.inflate(context, R.layout.item_grid_panic_buying_icon,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_panic_buying_left = view.findViewById(R.id.iv_panic_buying_left);
            viewHolder.iv_panic_buying_blew = view.findViewById(R.id.iv_panic_buying_blew);
            viewHolder.tv_panic_buying_right = view.findViewById(R.id.tv_panic_buying_right);
            viewHolder.tv_panic_buying_sencond = view.findViewById(R.id.tv_panic_buying_sencond);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        IconPanic iconPanic = iconInfo.get(position);
        viewHolder.tv_panic_buying_right.setText(iconPanic.getTextfirst());
        viewHolder.tv_panic_buying_sencond.setText(iconPanic.getTextsecond());
        viewHolder.iv_panic_buying_left.setImageResource(iconPanic.getImageleft());
        viewHolder.iv_panic_buying_blew.setImageResource(iconPanic.getImageblew());

        return view;
    }

    public class ViewHolder{
        ImageView iv_panic_buying_left,iv_panic_buying_blew;
        TextView tv_panic_buying_right,tv_panic_buying_sencond;
    }

}
