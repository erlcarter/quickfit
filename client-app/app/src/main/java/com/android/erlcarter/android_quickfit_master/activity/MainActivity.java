package com.android.erlcarter.android_quickfit_master.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.baseconfig.BaseActivity;
import com.android.erlcarter.android_quickfit_master.data.Colors;
import com.android.erlcarter.android_quickfit_master.fragment.CommuityFragment;
import com.android.erlcarter.android_quickfit_master.fragment.HostFragment;
import com.android.erlcarter.android_quickfit_master.fragment.ShopFragment;
import com.android.erlcarter.android_quickfit_master.fragment.UserInfoFragment;
import com.hjm.bottomtabbar.BottomTabBar;

/**
 * @author erlcarter
 * @date 2020/02/06
 * @update 2020/03/21 23:34
 */
public class MainActivity extends AppCompatActivity {

    private Colors colors;
    private BottomTabBar btb_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化UI
        initUi();
    }

    protected void initUi() {
        colors = new Colors(this);
        btb_main = findViewById(R.id.btb_main);
        btb_main.init(this.getSupportFragmentManager())
                .setImgSize(25,25)
                .setFontSize(12)
                .setTabBarBackgroundColor(colors.getColorWhite())
                .setChangeColor(colors.getColorBlue(),colors.getColorGray5())
                .addTabItem("首页",R.drawable.quickfit_main_host_selected,R.drawable.quickfit_main_host, HostFragment.class)
                .addTabItem("发现",R.drawable.quickfit_main_commuity_selected,R.drawable.quickfit_main_commuity, CommuityFragment.class)
                .addTabItem("商店",R.drawable.quickfit_main_shop_selected,R.drawable.quickfit_main_shop, ShopFragment.class)
                .addTabItem("我的",R.drawable.quickfit_main_userinfo_selected,R.drawable.quickfit_main_userinfo, UserInfoFragment.class)
                .isShowDivider(false);
    }

    protected void initData() {


    }

    protected void initListener() {


    }

    protected void handleMessage(Message msg) {

    }

}
