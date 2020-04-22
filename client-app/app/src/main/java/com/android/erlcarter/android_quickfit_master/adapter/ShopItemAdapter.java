package com.android.erlcarter.android_quickfit_master.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.data.ShopItem;

import java.util.List;

public class ShopItemAdapter extends BaseAdapter {

    private Context context;
    private List<ShopItem> shopItemInfo;

    public ShopItemAdapter(Context context, List<ShopItem> shopItemInfo) {
        this.context = context;
        this.shopItemInfo = shopItemInfo;
    }

    @Override
    public int getCount() {
        return shopItemInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return shopItemInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            view = View.inflate(context, R.layout.shop_item,null);
            holder = new ViewHolder();
            holder.iv_shop_item_img = view.findViewById(R.id.iv_shop_item_img);
            holder.tv_shop_item_title = view.findViewById(R.id.tv_shop_item_title);
            holder.tv_shop_item_signature = view.findViewById(R.id.tv_shop_item_signature);
            holder.tv_shop_item_real_price = view.findViewById(R.id.tv_shop_item_real_price);
            holder.tv_shop_item_original_price = view.findViewById(R.id.tv_shop_item_original_price);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        ShopItem item = shopItemInfo.get(position);
        holder.tv_shop_item_title.setText(item.getShopItemTitle());
        holder.tv_shop_item_signature.setText(item.getShopItemSignature());
        holder.tv_shop_item_real_price.setText(item.getShopItemRealPrice());
        holder.tv_shop_item_original_price.setText(item.getShopItemOriginalPrice());
        holder.iv_shop_item_img.setImageResource(item.getShopItemImg());
        //设置划线
        holder.tv_shop_item_original_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);

        return view;
    }

    public class ViewHolder{
        ImageView iv_shop_item_img;
        TextView tv_shop_item_title;
        TextView tv_shop_item_signature;
        TextView tv_shop_item_real_price;
        TextView tv_shop_item_original_price;
    }

}
