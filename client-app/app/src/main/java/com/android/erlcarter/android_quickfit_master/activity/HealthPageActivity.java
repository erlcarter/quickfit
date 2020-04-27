package com.android.erlcarter.android_quickfit_master.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.data.GetConfigReq;
import com.android.erlcarter.android_quickfit_master.utils.CommonPopWindow;
import com.android.erlcarter.android_quickfit_master.utils.CustomDatePicker;
import com.android.erlcarter.android_quickfit_master.utils.DateFormatUtils;
import com.android.erlcarter.android_quickfit_master.view.PickerScrollView;
import com.google.gson.Gson;

import java.util.List;

public class HealthPageActivity extends AppCompatActivity implements View.OnClickListener, CommonPopWindow.ViewClickListener {

    private RelativeLayout rl_brithday,rl_weight_manager,click;
    private CustomDatePicker mDatePicker;
    private TextView tv_brithday,tv_weight_sub,text;
    private List<GetConfigReq.DatasBean> datasBeanList;
    private int flag;
    private String categoryName,response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_page);
        initui();
        initlistener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatePicker.onDestroy();
    }

    private void initData() {
        //模拟请求后台返回数据
        GetConfigReq getConfigReq = new Gson().fromJson(response, GetConfigReq.class);
        //0请求表示成功
        if (getConfigReq.getRet() == 0) {
            //滚动选择数据集合
            datasBeanList = getConfigReq.getDatas();
        }
    }

    private void initlistener() {
        click.setOnClickListener(this);
        rl_brithday.setOnClickListener(this);
        rl_weight_manager.setOnClickListener(this);
    }

    private void initui() {
        rl_brithday = findViewById(R.id.rl_brithday);
        tv_brithday = findViewById(R.id.tv_brithday);
        rl_weight_manager = findViewById(R.id.rl_weight_manager);
        tv_weight_sub = findViewById(R.id.tv_weight_sub);
        click = findViewById(R.id.rl_health_sex);
        text = findViewById(R.id.tv_health_sex);
        initDatePicker();
    }

    /**
     * 将选择器放在底部弹出框
     * @param v
     */
    private void setAddressSelectorPopup(View v) {
        int screenHeigh = getResources().getDisplayMetrics().heightPixels;

        CommonPopWindow.newBuilder()
                .setView(R.layout.pop_picker_selector_bottom)
                .setAnimationStyle(R.style.AnimUp)
                .setBackgroundDrawable(new BitmapDrawable())
                .setSize(ViewGroup.LayoutParams.MATCH_PARENT, Math.round(screenHeigh * 0.3f))
                .setViewOnClickListener(this)
                .setBackgroundDarkEnable(true)
                .setBackgroundAlpha(0.7f)
                .setBackgroundDrawable(new ColorDrawable(999999))
                .build(this)
                .showAsBottom(v);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_health_sex:
//                Toast.makeText(HealthPageActivity.this,"你点击了性别",Toast.LENGTH_SHORT).show();
                response = "{\"ret\":0,\"" +
                        "msg\":\"succes,\"," +
                        "\"datas\":[" +
                        "{\"ID\":\"  0\",\"categoryName\":\"\\u7537\",\"state\":\"1\"}," +
                        "{\"ID\":\"1\",\"categoryName\":\"\\u5973\",\"state\":\"1\"}" +
                            "]" +
                        "}";
                initData();
                flag = 0;
                setAddressSelectorPopup(view);
                break;
            case R.id.rl_weight_manager:
                response = "{\"ret\":0,\"" +
                        "msg\":\"succes,\"," +
                        "\"datas\":[" +
                        "{\"ID\":\"  0\",\"categoryName\":\" \\u51Cf\\u91cd \",\"state\":\"1\"}," +
                        "{\"ID\":\"1\",\"categoryName\":\" \\u4fdd\\u6301 / \\u5851\\u5f62 \",\"state\":\"1\"}," +
                        "{\"ID\":\"2\",\"categoryName\":\" \\u589e\\u91cd / \\u589e\\u808c \",\"state\":\"1\"}"+
                            "]" +
                        "}";
                initData();
                flag = 1;
                setAddressSelectorPopup(view);
                break;
            case R.id.rl_brithday:
                // 日期格式为yyyy-MM-dd
//                Toast.makeText(HealthPageActivity.this,"你点击了出生日期",Toast.LENGTH_SHORT).show();
                mDatePicker.show(tv_brithday.getText().toString());
                break;
        }
    }

    @Override
    public void getChildView(final PopupWindow mPopupWindow, View view, int mLayoutResId) {
        switch (mLayoutResId) {
            case R.layout.pop_picker_selector_bottom:
                TextView imageBtn = view.findViewById(R.id.img_guanbi);
                PickerScrollView addressSelector = view.findViewById(R.id.address);

                // 设置数据，默认选择第一条
                addressSelector.setData(datasBeanList);
                addressSelector.setSelected(0);

                //滚动监听
                addressSelector.setOnSelectListener(new PickerScrollView.onSelectListener() {
                    @Override
                    public void onSelect(GetConfigReq.DatasBean pickers) {
                        categoryName = pickers.getCategoryName();
                    }
                });

                //完成按钮
                imageBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                        switch (flag){
                            case 0:
                                text.setText(categoryName);
                                break;
                            case 1:
                                tv_weight_sub.setText(categoryName);
                                break;
                        }
                    }
                });
                break;
        }
    }

    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("1980-01-01", false);
        long endTimestamp = System.currentTimeMillis();

        tv_brithday.setText(DateFormatUtils.long2Str(endTimestamp, false));

        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                tv_brithday.setText(DateFormatUtils.long2Str(timestamp, false));
            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(false);
        // 不显示时和分
        mDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim(false);
    }


}
