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
import com.android.erlcarter.android_quickfit_master.data.CommuityRecyclerViewData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChoiceFragment extends Fragment {

    private View view;
    private RecyclerView rv_commuity_activity,rv_commuity_activity_item;//声明RecyclerView
    private CommuityRecyclerViewAdapter adapter;//声明适配器
    private List<CommuityRecyclerViewData> list;
    private CommuityRecyclerViewData test1,test2,test3;


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
        //initChoiceItemRecyclerView();
        return view;
    }

    private void initUi(){
        rv_commuity_activity = view.findViewById(R.id.rv_commuity_activity);
        rv_commuity_activity_item = view.findViewById(R.id.rv_commuity_activity_item);
        list = new ArrayList<>();
        test1 = new CommuityRecyclerViewData();
        test2 = new CommuityRecyclerViewData();
        test3 = new CommuityRecyclerViewData();
    }

    private void initData(){
        //设置banner数据
        test1.setTitle("【测试】test");
        test1.setContent("testtesttest");
        test1.setImg(R.drawable.load_test);
        test2.setTitle("【测试】test");
        test2.setContent("testtesttest");
        test2.setImg(R.drawable.load_test);
        test3.setTitle("【测试】test");
        test3.setContent("testtesttest");
        test3.setImg(R.drawable.load_test);
        //添加到list
        list.add(test1);
        list.add(test2);
        list.add(test3);
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
        adapter = new CommuityRecyclerViewAdapter(getContext(),list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rv_commuity_activity.setLayoutManager(manager);
        rv_commuity_activity.setAdapter(adapter);
    }

}
