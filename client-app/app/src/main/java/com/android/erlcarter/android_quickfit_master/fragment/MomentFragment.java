package com.android.erlcarter.android_quickfit_master.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.adapter.CommuityTopicRecyclerViewAdapter;
import com.android.erlcarter.android_quickfit_master.adapter.CommuityTrendsRecyclerViewAdapter;
import com.android.erlcarter.android_quickfit_master.data.CommuityTrendsRecyclerViewData;
import com.android.erlcarter.android_quickfit_master.data.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MomentFragment extends Fragment {

    private RecyclerView rv_commuity_trends_item,rv_commuity_topic;
    private CommuityTrendsRecyclerViewAdapter trendadapter;
    private CommuityTopicRecyclerViewAdapter topicadapter;
    private List<CommuityTrendsRecyclerViewData> trendlist;
    private List<Topic> topiclist;
    private CommuityTrendsRecyclerViewData test_1,test_2,test_3;
    private Topic test1,test2,test3,test4,test5,test6,test7;
    private View view;

    public MomentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_moment, container, false);
        initui();
        initdata();
        initChoiceItemRecyclerView();
        initTopicItemRecyclerView();
        return view;
    }

    private void initdata() {
        trendlist = new ArrayList<>();
        topiclist = new ArrayList<>();
        test_1 = new CommuityTrendsRecyclerViewData();
        test_2 = new CommuityTrendsRecyclerViewData();
        test_3 = new CommuityTrendsRecyclerViewData();
        test1 = new Topic();
        test2 = new Topic();
        test3 = new Topic();
        test4 = new Topic();
        test5 = new Topic();
        test6 = new Topic();
        test7 = new Topic();
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
        //设置话题
        test1.setBackgroundDrawable(R.drawable.load_topic_test);
        test2.setBackgroundDrawable(R.drawable.load_topic_test);
        test3.setBackgroundDrawable(R.drawable.load_topic_test);
        test4.setBackgroundDrawable(R.drawable.load_topic_test);
        test5.setBackgroundDrawable(R.drawable.load_topic_test);
        test6.setBackgroundDrawable(R.drawable.load_topic_test);
        test7.setBackgroundDrawable(R.drawable.load_topic_test);
        test1.setTopicTitle("我要上精选");
        test2.setTopicTitle("好物安利");
        test3.setTopicTitle("对比照");
        test4.setTopicTitle("小基数减脂交流");
        test5.setTopicTitle("大基数减脂抱团");
        test6.setTopicTitle("21天减肥打卡");
        test7.setTopicTitle("宝妈联盟");
        topiclist.add(test1);
        topiclist.add(test2);
        topiclist.add(test3);
        topiclist.add(test4);
        topiclist.add(test5);
        topiclist.add(test6);
        topiclist.add(test7);
    }

    private void initui() {
        rv_commuity_trends_item = view.findViewById(R.id.rv_commuity_trends_item);
        rv_commuity_topic = view.findViewById(R.id.rv_commuity_topic);
    }

    /**
     * 初始化动态信息
     */
    private void initChoiceItemRecyclerView(){
        trendadapter = new CommuityTrendsRecyclerViewAdapter(getContext(),trendlist);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.setOrientation(RecyclerView.VERTICAL);
        rv_commuity_trends_item.setLayoutManager(manager);
        rv_commuity_trends_item.setAdapter(trendadapter);
    }

    /**
     * 初始化话题信息
     */
    private void initTopicItemRecyclerView(){
        topicadapter = new CommuityTopicRecyclerViewAdapter(getContext(),topiclist);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rv_commuity_topic.setLayoutManager(manager);
        rv_commuity_topic.setAdapter(topicadapter);
    }
}
