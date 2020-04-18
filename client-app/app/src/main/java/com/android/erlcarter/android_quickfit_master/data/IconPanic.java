package com.android.erlcarter.android_quickfit_master.data;

public class IconPanic {

    private int imageleft,imageblew;

    private String textfirst,textsecond;

    public IconPanic() {}

    public IconPanic(int imageleft, int imageblew, String textfirst, String textsecond) {
        this.imageleft = imageleft;
        this.imageblew = imageblew;
        this.textfirst = textfirst;
        this.textsecond = textsecond;
    }

    public int getImageleft() {
        return imageleft;
    }

    public void setImageleft(int imageleft) {
        this.imageleft = imageleft;
    }

    public int getImageblew() {
        return imageblew;
    }

    public void setImageblew(int imageblew) {
        this.imageblew = imageblew;
    }

    public String getTextfirst() {
        return textfirst;
    }

    public void setTextfirst(String textfirst) {
        this.textfirst = textfirst;
    }

    public String getTextsecond() {
        return textsecond;
    }

    public void setTextsecond(String textsecond) {
        this.textsecond = textsecond;
    }
}
