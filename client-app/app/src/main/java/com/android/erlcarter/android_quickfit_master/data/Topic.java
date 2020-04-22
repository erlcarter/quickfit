package com.android.erlcarter.android_quickfit_master.data;

import android.graphics.drawable.Drawable;

public class Topic {
    private int backgroundDrawable;
    private String topicTitle;

    public Topic() {}

    public Topic(int backgroundDrawable, String topicTitle) {
        this.backgroundDrawable = backgroundDrawable;
        this.topicTitle = topicTitle;
    }

    public int getBackgroundDrawable() {
        return backgroundDrawable;
    }

    public void setBackgroundDrawable(int backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }
}
