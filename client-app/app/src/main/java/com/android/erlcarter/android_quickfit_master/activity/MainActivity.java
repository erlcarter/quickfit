package com.android.erlcarter.android_quickfit_master.activity;

import android.content.Context;
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

/**
 * @author erlcarter
 * @date 2020/02/06
 * @update 2020/03/21 23:34
 */
public class MainActivity extends BaseActivity implements View {

    private Context context;
    private LinearLayout ll_main_bottombar;
    private RelativeLayout main_topbar,bottombar_host_btn,
            bottombar_commuity_btn,bottombar_extend_btn,
            bottombar_shop_btn,bottombar_userinfo_btn;
    private FrameLayout fl_main_body;
    private TextView tv_main_bottombar_text_host,tv_main_bottombar_text_commuity,
            tv_main_bottombar_text_shop,tv_main_bottombar_text_userinfo;
    private ImageView iv_main_bottombar_image_host,iv_main_bottombar_image_commuity,
            iv_main_bottombar_image_extend,iv_main_bottombar_image_shop,
            iv_main_bottombar_image_userinfo;
    private String layoutTopBar[] = {"host_title_bar","commuity_title_bar","shop_title_bar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected Context getContext() { return context = this; }

    @Override
    protected void initUi() {
        ll_main_bottombar = findViewById(R.id.ll_main_bottombar);
        main_topbar = findViewById(R.id.main_topbar);
        bottombar_host_btn = findViewById(R.id.bottombar_host_btn);
        bottombar_commuity_btn = findViewById(R.id.bottombar_commuity_btn);
        bottombar_extend_btn = findViewById(R.id.bottombar_extend_btn);
        bottombar_shop_btn = findViewById(R.id.bottombar_shop_btn);
        bottombar_userinfo_btn = findViewById(R.id.bottombar_userinfo_btn);
        fl_main_body = findViewById(R.id.fl_main_body);
        tv_main_bottombar_text_host = findViewById(R.id.tv_main_bottombar_text_host);
        tv_main_bottombar_text_commuity = findViewById(R.id.tv_main_bottombar_text_commuity);
        tv_main_bottombar_text_shop = findViewById(R.id.tv_main_bottombar_text_shop);
        tv_main_bottombar_text_userinfo = findViewById(R.id.tv_main_bottombar_text_userinfo);
        iv_main_bottombar_image_host = findViewById(R.id.iv_main_bottombar_image_host);
        iv_main_bottombar_image_commuity = findViewById(R.id.iv_main_bottombar_image_commuity);
        iv_main_bottombar_image_extend = findViewById(R.id.iv_main_bottombar_image_extend);
        iv_main_bottombar_image_shop = findViewById(R.id.iv_main_bottombar_image_shop);
        iv_main_bottombar_image_userinfo = findViewById(R.id.iv_main_bottombar_image_userinfo);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void handleMessage(Message msg) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    /**
     * 底部按钮点击事件
     */
    private void onClick(View v){
        switch (v.getId()){
            //首页
            case R.id.bottombar_host_btn:
                break;
            //社区
            case R.id.bottombar_commuity_btn:
                break;
            //快速打开
            case R.id.bottombar_extend_btn:
                break;
            //商店
            case R.id.bottombar_shop_btn:
                break;
            //我的
            case R.id.bottombar_userinfo_btn:
                break;
        }
    }

    /**
     * 设置底部按钮的点击监听事件
     */
    private void setListener(){
        for (int i=0;i<ll_main_bottombar.getChildCount();i++){
            ll_main_bottombar.getChildAt(i).setOnClickListener();
        }
    }

}
