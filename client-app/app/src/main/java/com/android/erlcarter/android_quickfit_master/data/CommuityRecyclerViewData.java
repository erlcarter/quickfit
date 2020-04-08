package com.android.erlcarter.android_quickfit_master.data;

public class CommuityRecyclerViewData {

    private int img;

    private String title;

    private String content;

    public CommuityRecyclerViewData() {}

    public CommuityRecyclerViewData(int img, String title, String content) {
        this.img = img;
        this.title = title;
        this.content = content;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
