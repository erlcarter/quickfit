package com.android.erlcarter.android_quickfit_master.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.activity.ShopItemDetailActivity;
import com.android.erlcarter.android_quickfit_master.adapter.ShopItemAdapter;
import com.android.erlcarter.android_quickfit_master.data.ShopItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class XPGFragment extends Fragment {

    private View view;
    private GridView xpg_shop_item;
    private List<ShopItem> shopItems;
    private ShopItemAdapter shopItemAdapter;

    public XPGFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_x_p_g, container, false);
        initui();
        initdata();
        initListener();
        return view;
    }

    private void initListener() {
        xpg_shop_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                for (int i=0;i<adapterView.getCount();i++){
                    View v = adapterView.getChildAt(i);
                    if (position == i) {//当前选中的item
                        Intent intent = new Intent(getActivity(), ShopItemDetailActivity.class);//跳转到商品详情页
                        intent.putExtra("shopItemId", position);//参数：商品编号
                        getActivity().startActivity(intent);
                    }
                }
            }
        });
    }

    private void initdata() {
        shopItems = new ArrayList<>();//商品数据
        //测试数据
        shopItems.add(new ShopItem(R.drawable.load_shop_item_1,"21天10斤挑战 升级款","EasyAce™.全套(BMI 24-27.9)","￥1189","￥1669"));
        shopItems.add(new ShopItem(R.drawable.load_shop_item_2,"21天10斤挑战 升级款","EasyAce™.全套(BMI 24-27.9)","￥1189","￥1669"));
        shopItems.add(new ShopItem(R.drawable.load_shop_item_3,"暖心暖胃好滋味 21餐版","EasyFun™.饱腹午套餐(两种套餐可选)","￥269","￥438"));
        shopItems.add(new ShopItem(R.drawable.load_shop_item_3,"21天10斤挑战 升级款","EasyFun™.高蛋白鸡胸肉肠 12根/包","￥32","￥38"));

        shopItemAdapter = new ShopItemAdapter(getContext(),shopItems);
        xpg_shop_item.setAdapter(shopItemAdapter);
    }

    private void initui() {
        xpg_shop_item = view.findViewById(R.id.xpg_shop_item);
        //初始化商品展示列表
        xpg_shop_item.setColumnWidth(25);//列宽度
//        xpg_shop_item.setHorizontalSpacing(10);//水平间距
//        xpg_shop_item.setVerticalSpacing(10);//垂直间距
        xpg_shop_item.setNumColumns(2);//列数
    }
}
