package com.android.erlcarter.android_quickfit_master.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class CommuityVPAdapter extends FragmentPagerAdapter {

    private List<Fragment> pageFragment;
    private final String[] titles;

    public CommuityVPAdapter(FragmentManager fm,List<Fragment>pageFragment,String[] titles) {
        super(fm);
        this.pageFragment = pageFragment;
        this.titles =  titles;
    }

    @Override
    public Fragment getItem(int position) {
        return pageFragment.get(position);
    }

    @Override
    public int getCount() {
        return pageFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
