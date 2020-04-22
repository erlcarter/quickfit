package com.android.erlcarter.android_quickfit_master.fragment;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.adapter.CommuityRecyclerViewAdapter;
import com.android.erlcarter.android_quickfit_master.adapter.CommuityTrendsRecyclerViewAdapter;
import com.android.erlcarter.android_quickfit_master.data.CommuityRecyclerViewData;
import com.android.erlcarter.android_quickfit_master.data.CommuityTrendsRecyclerViewData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChoiceFragment extends Fragment {

    private View view;
    private RecyclerView rv_commuity_activity,rv_commuity_activity_item;//声明RecyclerView
    private CommuityRecyclerViewAdapter adapter;//声明适配器
    private CommuityTrendsRecyclerViewAdapter trendadapter;
    private List<CommuityRecyclerViewData> list;
    private List<CommuityTrendsRecyclerViewData> trendlist;
    private CommuityRecyclerViewData test1,test2,test3;
    private CommuityTrendsRecyclerViewData test_1,test_2,test_3;


    public ChoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_choice, container, false);
        initUi();
        initData();
        initActivityRecyclerView();
        initChoiceItemRecyclerView();
        return view;
    }

    private void initUi(){
        rv_commuity_activity = view.findViewById(R.id.rv_commuity_activity);
        rv_commuity_activity_item = view.findViewById(R.id.rv_commuity_activity_item);
        list = new ArrayList<>();
        trendlist = new ArrayList<>();
        test1 = new CommuityRecyclerViewData();
        test2 = new CommuityRecyclerViewData();
        test3 = new CommuityRecyclerViewData();
        test_1 = new CommuityTrendsRecyclerViewData();
        test_2 = new CommuityTrendsRecyclerViewData();
        test_3 = new CommuityTrendsRecyclerViewData();
    }

    private void initData(){
        //测试数据
        //设置banner数据
        test1.setTitle("话题 | 你有拿手的减脂餐嘛？");
        test1.setContent("在线征集【减脂餐做法】啦！");
        test1.setImg(R.drawable.load_test);
        test2.setTitle("话题 | 你有拿手的减脂餐嘛？");
        test2.setContent("在线征集【减脂餐做法】啦！");
        test2.setImg(R.drawable.load_test);
        test3.setTitle("话题 | 你有拿手的减脂餐嘛？");
        test3.setContent("在线征集【减脂餐做法】啦！");
        test3.setImg(R.drawable.load_test);
        //添加到list
        list.add(test1);
        list.add(test2);
        list.add(test3);
        //设置动态
        test_1.setUserImg(R.drawable.user_img);
        test_2.setUserImg(R.drawable.user_img);
        test_3.setUserImg(R.drawable.user_img);
        test_1.setUserName("tester1");
        test_2.setUserName("tester2");
        test_3.setUserName("tester3");
        test_1.setContent("这是一条测试动态【1】");
        test_2.setContent("这是一条测试动态【2】");
        test_3.setContent("这是一条测试动态【3】");
        test_1.setDianzanNum("9999");
        test_2.setDianzanNum("9999");
        test_3.setDianzanNum("9999");
        test_1.setPingjiaNum("9999");
        test_2.setPingjiaNum("9999");
        test_3.setPingjiaNum("9999");
        test_1.setShoucangNum("9999");
        test_2.setShoucangNum("9999");
        test_3.setShoucangNum("9999");
        trendlist.add(test_1);
        trendlist.add(test_2);
        trendlist.add(test_3);
    }

    /**
     * 初始化活动信息
     */
    private void initActivityRecyclerView(){
        adapter = new CommuityRecyclerViewAdapter(getContext(),list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rv_commuity_activity.setLayoutManager(manager);
        rv_commuity_activity.setAdapter(adapter);
    }

    /**
     * 初始化动态信息
     */
    private void initChoiceItemRecyclerView(){
        trendadapter = new CommuityTrendsRecyclerViewAdapter(getContext(),trendlist);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.setOrientation(RecyclerView.VERTICAL);
        rv_commuity_activity_item.setLayoutManager(manager);
        rv_commuity_activity_item.setAdapter(trendadapter);
    }

}
