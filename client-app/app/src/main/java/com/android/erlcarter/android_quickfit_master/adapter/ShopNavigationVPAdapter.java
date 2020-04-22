package com.android.erlcarter.android_quickfit_master.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ShopNavigationVPAdapter extends FragmentPagerAdapter {

    private List<Fragment> pageFragment;
    private String[] title;


    public ShopNavigationVPAdapter(FragmentManager fm,List<Fragment> pageFragment,String[] title) {
        super(fm);
        this.pageFragment = pageFragment;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) { return pageFragment.get(position); }

    @Override
    public int getCount() { return pageFragment.size(); }

    @Override
    public CharSequence getPageTitle(int position) { return super.getPageTitle(position); }

}
