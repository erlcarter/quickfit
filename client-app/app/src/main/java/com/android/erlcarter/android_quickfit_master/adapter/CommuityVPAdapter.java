package com.android.erlcarter.android_quickfit_master.adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class CommuityVPAdapter extends FragmentPagerAdapter {

    private List<Fragment> pageFragment;
    private final String[] titles;

    public CommuityVPAdapter(FragmentManager fm, List<Fragment> pageFragment, String[] titles) {
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

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
