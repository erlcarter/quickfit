package com.android.erlcarter.android_quickfit_master.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.activity.FoodRecordActivity;
import com.android.erlcarter.android_quickfit_master.activity.WeightRecordActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HostFragment extends Fragment {

    private RelativeLayout rl_food_reord;
    private CardView cv_host_userinfo_show;
    private View view;

    public HostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_host, container, false);
        initui();//初始化UI
        initData();//初始化数据
        initLinstener();//初始化监听器
        return view;
    }

    private void initui(){
        cv_host_userinfo_show = view.findViewById(R.id.cv_host_userinfo_show);
        rl_food_reord = view.findViewById(R.id.rl_food_reord);

    }

    private void initData(){

    }

    private void initLinstener(){
        cv_host_userinfo_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WeightRecordActivity.class);
                startActivity(intent);//跳转到体重记录
            }
        });
        rl_food_reord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FoodRecordActivity.class);
                startActivity(intent);//跳转到食物记录
            }
        });
    }

}
