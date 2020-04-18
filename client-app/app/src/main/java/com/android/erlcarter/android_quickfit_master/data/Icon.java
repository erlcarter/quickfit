package com.android.erlcarter.android_quickfit_master.data;

public class Icon {
    private int image;
    private String text;

    public Icon() {}

    public Icon(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public void setImage(int iId) {
        this.image = image;
    }

    public void setText(String text) {
        this.text = text;
    }
}
