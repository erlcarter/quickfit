package com.android.erlcarter.android_quickfit_master.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.erlcarter.android_quickfit_master.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {

    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        return view;
    }
}
