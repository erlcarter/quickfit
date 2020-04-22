package com.android.erlcarter.android_quickfit_master.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.activity.ShopItemNavigationActivity;
import com.android.erlcarter.android_quickfit_master.adapter.PanicBuyingBannerAdapter;
import com.android.erlcarter.android_quickfit_master.adapter.ShopBannerAdapter;
import com.android.erlcarter.android_quickfit_master.core.GlideImageLoader;
import com.android.erlcarter.android_quickfit_master.data.Icon;
import com.android.erlcarter.android_quickfit_master.data.IconPanic;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {
    /**
     * ui 组件
     */
    private View view;
    private Banner shop_banner;
    private CardView shop_navigation,shop_activity;
    private LinearLayout shop_recommend;
    private GridView gv_shop_banner,gv_panic_buying_banner;
    private GridView gv_shop_item_recommend;

    /**
     * bannerColumnWidth 列的宽度
     */
    private static final int bannerColumnWidth=25;

    /**
     * bannerItemGravity 组件对齐方式
     */
    private static final int bannerItemGravity=0;

    /**
     * bannerHorizontalSpacing 水平方向每个单元格的间距
     */
    private static final int bannerHorizontalSpacing=10;

    /**
     * bannerVerticalSpacing 竖直方向每个单元格的间距
     */
    private static final int bannerVerticalSpacing=10;

    /**
     * bannerNumColumns 设置列数
     */
    private static int bannerNumColumns=5;
    /**
     * bannerStretchMode 设置拉伸模式，可选值如下：
     * none：不拉伸
     * spacingWidth：拉伸元素间的间隔空隙
     * columnWidth：仅仅拉伸表格元素自身
     * spacingWidthUniform：既拉元素间距又拉伸他们之间的间隔空袭
     */
    private static int bannerStretchMode=0;

    /**
     * panicBuyingBannerColumnWidth 列的宽度
     */
    private static final int panicBuyingBannerColumnWidth=90;

    /**
     * panicBuyingBannerItemGravity 组件对齐方式
     */
    private static final int panicBuyingBannerItemGravity=0;

    /**
     * panicBuyingBannerHorizontalSpacing 水平方向每个单元格的间距
     */
    private static final int panicBuyingBannerHorizontalSpacing=10;

    /**
     * panicBuyingBannerVerticalSpacing 竖直方向每个单元格的间距
     */
    private static final int panicBuyingBannerVerticalSpacing=10;

    /**
     * panicBuyingBannerNumColumns 设置列数
     */
    private static int panicBuyingBannerNumColumns=2;

    /**
     * panicBuyingBannerStretchMode 设置拉伸模式，可选值如下：
     * none：不拉伸
     * spacingWidth：拉伸元素间的间隔空隙
     * columnWidth：仅仅拉伸表格元素自身
     * spacingWidthUniform：既拉元素间距又拉伸他们之间的间隔空袭
     */
    private static int panicBuyingBannerStretchMode=0;

    /**
     * url 轮播图加载url
     */
    private String url;

    /**
     * titles 轮播图标题
     */
    private List<String> titles;

    /**
     * imageUrl 网络图片集合
     */
    private List<Integer> imageUrl;

    /**
     * mData shopbanner的item
     */
    private List<Icon> mData;

    /**
     * mAdapter shopbanner item的适配器
     */
    private ShopBannerAdapter mAdapter;

    /**
     * mData2 pnicbuyingbanner
     */
    private List<IconPanic> mData2;

    /**
     * mAdapter2 shopbanner item的适配器
     */
    private PanicBuyingBannerAdapter mAdapter2;


    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop, container, false);
        //初始化UI
        initui();
        //初始化DATA
        initdata();
        //初始化监听
        setOnListener();
        //启用轮播图
        startBanner();
        return view;
    }

    //初始化UI
    private void initui(){
        //初始化ui
        shop_banner = view.findViewById(R.id.shop_banner);
        shop_navigation = view.findViewById(R.id.shop_navigation);
        shop_activity = view.findViewById(R.id.shop_activity);
        shop_recommend = view.findViewById(R.id.shop_recommend);
        gv_shop_banner = view.findViewById(R.id.gv_shop_banner);
        gv_panic_buying_banner = view.findViewById(R.id.gv_panic_buying_banner);
        gv_shop_item_recommend = view.findViewById(R.id.gv_shop_item_recommend);
        //初始化shopbanner
        gv_shop_banner.setColumnWidth(bannerColumnWidth);
        gv_shop_banner.setHorizontalSpacing(bannerHorizontalSpacing);
        gv_shop_banner.setVerticalSpacing(bannerVerticalSpacing);
        gv_shop_banner.setNumColumns(bannerNumColumns);
        //初始化panicbuyingbanner
        gv_panic_buying_banner.setColumnWidth(panicBuyingBannerColumnWidth);
        gv_panic_buying_banner.setHorizontalSpacing(panicBuyingBannerHorizontalSpacing);
        gv_panic_buying_banner.setVerticalSpacing(panicBuyingBannerVerticalSpacing);
        gv_panic_buying_banner.setNumColumns(panicBuyingBannerNumColumns);
    }

    //初始化数据
    private void initdata(){
        imageUrl = new ArrayList<>();
        titles = new ArrayList<>();
        mData = new ArrayList<>();
        mData2 = new ArrayList<>();
        //测试数据
        //banner
        for (int i=0;i<3;i++){
            imageUrl.add(R.drawable.load_shop_test);//图片
            titles.add("测试"+i);//标题
        }
        //shopbanner
        mData.add(new Icon(R.drawable.fit_new_item, "图标1"));
        mData.add(new Icon(R.drawable.fit_quick_food, "图标2"));
        mData.add(new Icon(R.drawable.fit_food, "图标3"));
        mData.add(new Icon(R.drawable.fit_add_service, "图标4"));
        mData.add(new Icon(R.drawable.fit_sub_service, "图标5"));
        mData.add(new Icon(R.drawable.fit_exercise_apparatus, "图标6"));
        mData.add(new Icon(R.drawable.fit_appurtenanceauxiliary, "图标7"));
        mData.add(new Icon(R.drawable.fit_meat, "图标8"));
        mData.add(new Icon(R.drawable.fit_drink, "图标9"));
        mData.add(new Icon(R.drawable.fit_more, "图标10"));

        mAdapter = new ShopBannerAdapter(getContext(),mData);
        gv_shop_banner.setAdapter(mAdapter);
        //pnicbuyingbanner
        mData2.add(new IconPanic(R.drawable.shop_hot,R.drawable.load_shop_item_2,"开学减脂季","每日秒限量券"));
        mData2.add(new IconPanic(R.drawable.shop_gift,R.drawable.load_shop_item_2,"奶茶热量榜","关注领及肉丸"));
        mData2.add(new IconPanic(R.drawable.shop_hot,R.drawable.load_shop_item_2,"开学减脂季","每日秒限量券"));
        mData2.add(new IconPanic(R.drawable.shop_gift,R.drawable.load_shop_item_2,"奶茶热量榜","关注领及肉丸"));

        mAdapter2 = new PanicBuyingBannerAdapter(getContext(),mData2);
        gv_panic_buying_banner.setAdapter(mAdapter2);
    }

    private void setOnListener(){
        gv_shop_banner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ShopItemNavigationActivity.class);
                startActivity(intent);
            }
        });

    }

    //启用轮播图
    private void startBanner() {
        //设置banner样式(显示圆形指示器)
        shop_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置（指示器居右）
        shop_banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载器
        shop_banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        shop_banner.setImages(imageUrl);
        //设置banner动画效果
        shop_banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        shop_banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        shop_banner.isAutoPlay(true);
        //设置轮播时间
        shop_banner.setDelayTime(5000);
        //banner设置方法全部调用完毕时最后调用
        shop_banner.start();
    }

    /*private void initBanner() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Advertisement advertisement = new Gson().fromJson(str, Advertisement.class);
                        int resultCode = advertisement.getResultCode();
                        if (resultCode == 100) {
                            adList = advertisement.getadvertisement1();
                            for (int i = 0; i < adList.size(); i++) {
                                String pic = adList.get(i).getPicture();
                                imageUrl.add(ImageBasePath + pic);
                            }
                            startBanner();
                        } else {
                            Toast.makeText(this, advertisement.getResultMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
*/

}
