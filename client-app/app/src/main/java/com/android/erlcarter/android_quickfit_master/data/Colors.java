package com.android.erlcarter.android_quickfit_master.data;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.android.erlcarter.android_quickfit_master.R;

public class Colors {
    private Context context;
    private int colorGray4;
    private int colorGray5;
    private int colorGray6;
    private int colorGray8;
    private int colorBlue;
    private int colorWhite;
    private int colorConfigBlack;
    private int colorBlack5;
    private int colorRed;

    public Colors(Context context) {
        this.context = context;
    }

    public int getColorRed() {
        return colorRed = ContextCompat.getColor(context,R.color.colorRed);
    }

    public int getColorGray4() {
        return colorGray4 = ContextCompat.getColor(context,R.color.colorGray4);
    }

    public int getColorGray5() {
        return colorGray5 = ContextCompat.getColor(context, R.color.colorGray5);
    }

    public int getColorGray6() {
        return colorGray6 = ContextCompat.getColor(context,R.color.colorGray6);
    }

    public int getColorGray8() {
        return colorGray8 = ContextCompat.getColor(context,R.color.colorGray8);
    }

    public int getColorBlue() {
        return colorBlue = ContextCompat.getColor(context,R.color.colorBlue);
    }

    public int getColorWhite() {
        return colorWhite = ContextCompat.getColor(context,R.color.colorWhite);
    }

    public int getColorConfigBlack() {
        return colorConfigBlack = ContextCompat.getColor(context,R.color.colorConfigBlack);
    }

    public int getColorBlack5() {
        return colorBlack5 = ContextCompat.getColor(context,R.color.colorBlack5);
    }
}
