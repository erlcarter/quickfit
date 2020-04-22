package com.android.erlcarter.android_quickfit_master.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.utils.LogUtil;

public class ShopItemDetailActivity extends AppCompatActivity {

    private int shopItemId = 0;//商品编号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_item_detail);
        //初始化数据
        initdata();
        //初始化UI
        initui();
    }

    private void initdata() {
        //接收传递过来的参数
        final Intent intent = getIntent();
        shopItemId = intent.getIntExtra("shopItemId", 0);
//        LogUtil.d("接收的商品ID为：","["+shopItemId+"]");
    }

    private void initui() {
    }
}
