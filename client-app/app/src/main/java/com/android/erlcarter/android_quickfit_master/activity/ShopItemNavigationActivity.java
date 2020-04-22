package com.android.erlcarter.android_quickfit_master.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.adapter.CommuityVPAdapter;
import com.android.erlcarter.android_quickfit_master.adapter.ShopNavigationGridViewAdapter;
import com.android.erlcarter.android_quickfit_master.adapter.ShopNavigationRecyclerViewAdapter;
import com.android.erlcarter.android_quickfit_master.adapter.ShopNavigationVPAdapter;
import com.android.erlcarter.android_quickfit_master.data.Colors;
import com.android.erlcarter.android_quickfit_master.data.NavigationItemData;
import com.android.erlcarter.android_quickfit_master.fragment.ChoiceFragment;
import com.android.erlcarter.android_quickfit_master.fragment.DBBCFragment;
import com.android.erlcarter.android_quickfit_master.fragment.FBSSFragment;
import com.android.erlcarter.android_quickfit_master.fragment.MomentFragment;
import com.android.erlcarter.android_quickfit_master.fragment.XPGFragment;
import com.android.erlcarter.android_quickfit_master.view.MyRecycleview;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class ShopItemNavigationActivity extends AppCompatActivity {

    private TextView tv_shop_item_navigation_item;
//    private RecyclerView rv_shop_item_navigation;//声明RecyclerView
    private GridView gv_shop_item_navigation;
    private ShopNavigationGridViewAdapter mAdapter;
//    private ShopNavigationRecyclerViewAdapter adapter;//声明适配器
    private List<NavigationItemData> list;
    private List<NavigationItemData> navigationItemData;
    private ViewPager vp_shop_item;
    private List<Fragment> fragments;
    private Colors colors;
    //测试数据
    String testlist[] = {
            "新品购",
            "方便素食",
            "蛋白补充",
            "燃燃燃燃",
            "瘦身服务",
            "体脂秤",
            "美味零嘴",
            "低脂肉肉",
            "冲泡饮品",
            "其它"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_item_navigation);
        //初始化UI
        initui();
        //初始化数据
        initdata();
//        initNavigationRecyclerView();
        //初始化商品导航栏
        initNavigationGridView();
        //启用监听
        initOnListener();
        //初始化商品展示页
        initViewPager();
    }

    private void initViewPager() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new XPGFragment());//新品购
        fragments.add(new FBSSFragment());//方便速食
        fragments.add(new DBBCFragment());//蛋白补充
        ShopNavigationVPAdapter svpAdapter = new ShopNavigationVPAdapter(getSupportFragmentManager(),fragments,testlist);
        vp_shop_item.setAdapter(svpAdapter);
    }

    private void initui() {
//        rv_shop_item_navigation = findViewById(R.id.rv_shop_item_navigation);
        tv_shop_item_navigation_item = findViewById(R.id.tv_shop_item_navigation_item);
        gv_shop_item_navigation = findViewById(R.id.gv_shop_item_navigation);
        vp_shop_item = findViewById(R.id.vp_shop_item);
    }

    private void initdata() {
        colors = new Colors(this);
        list = new ArrayList<>();
        navigationItemData = new ArrayList<>();
        gv_shop_item_navigation.setColumnWidth(25);
//        gv_shop_item_navigation.setHorizontalSpacing(10);
//        gv_shop_item_navigation.setVerticalSpacing(10);
        gv_shop_item_navigation.setNumColumns(1);
    }

    private void initOnListener(){
        //默认选中第一行
      gv_shop_item_navigation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                for (int i=0;i<adapterView.getCount();i++){
                    View v = adapterView.getChildAt(i);
                    if (position == i) {//当前选中的item
                        view.setBackgroundColor(colors.getColorWhite());
                        vp_shop_item.setCurrentItem(position,false);//直接切换，禁止滑动
                    }else{
                        v.setBackgroundColor(colors.getColorGray8());
                    }
                }
            }
        });
    }

    private void initNavigationGridView(){
        //测试数据
        for (int i=0;i<testlist.length;i++){
            navigationItemData.add(new NavigationItemData(testlist[i]));
        }
        for (int j=0;j<navigationItemData.size();j++){
            list.add(navigationItemData.get(j));
        }
        mAdapter = new ShopNavigationGridViewAdapter(this,list);
        gv_shop_item_navigation.setAdapter(mAdapter);
    }

   /* private void initNavigationRecyclerView() {
        adapter = new ShopNavigationRecyclerViewAdapter(this,list);
        MyRecycleview gridLayoutManager = new MyRecycleview(this,1);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        gridLayoutManager.setScrollEnabled(false);
        rv_shop_item_navigation.setLayoutManager(gridLayoutManager);
        rv_shop_item_navigation.setAdapter(adapter);
    }*/




}
