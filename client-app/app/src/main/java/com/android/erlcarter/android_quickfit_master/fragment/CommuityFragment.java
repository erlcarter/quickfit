package com.android.erlcarter.android_quickfit_master.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.adapter.CommuityVPAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommuityFragment extends Fragment {

    private TabLayout tab_title;
    private ViewPager vp_commuity;
    //数据源
    private List<Fragment> fragments;
    private String[] titles = {"精选","好友圈"};

    public CommuityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commuity, container, false);
        tab_title = view.findViewById(R.id.tl_commuity_tab_title);
        vp_commuity = view.findViewById(R.id.vp_commuity_pages);
        fragments = new ArrayList<Fragment>();
        fragments.add(new ChoiceFragment());
        fragments.add(new MomentFragment());
        CommuityVPAdapter cvpAdapter = new CommuityVPAdapter(getFragmentManager(),fragments,titles);
        vp_commuity.setAdapter(cvpAdapter);
        tab_title.setupWithViewPager(vp_commuity);
        //关联viewpager后会移除所有tab，需手动添加
        for (int i=0;i<titles.length;i++){
            tab_title.getTabAt(i).setText(titles[i]);
        }
        return view;
    }
}
