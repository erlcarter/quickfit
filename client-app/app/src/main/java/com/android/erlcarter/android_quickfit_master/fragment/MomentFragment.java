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
public class MomentFragment extends Fragment {

    public MomentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moment, container, false);
    }
}
