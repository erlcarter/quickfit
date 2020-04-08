package com.android.erlcarter.android_quickfit_master.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.data.Colors;
import com.android.erlcarter.android_quickfit_master.fragment.CalendarFragment;
import com.android.erlcarter.android_quickfit_master.fragment.RateFragment;
import com.hjm.bottomtabbar.BottomTabBar;

public class WeightRecordActivity extends AppCompatActivity {

    private Colors colors;
    private BottomTabBar btb_weight_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_record);
        initui();
    }

    private void initui() {
        colors = new Colors(this);
        btb_weight_record = (BottomTabBar) findViewById(R.id.btb_weight_record);
        btb_weight_record.init(this.getSupportFragmentManager())
                .setImgSize(25,25)
                .setFontSize(12)
                .setTabBarBackgroundColor(colors.getColorWhite())
                .setChangeColor(colors.getColorBlue(),colors.getColorGray5())
                .addTabItem("进度",R.drawable.rate_selected,R.drawable.rate, RateFragment.class)
                .addTabItem("日历",R.drawable.calendar_selected,R.drawable.calendar, CalendarFragment.class)
                .isShowDivider(false);
    }
}
