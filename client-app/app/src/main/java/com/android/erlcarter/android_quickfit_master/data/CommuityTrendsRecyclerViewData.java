package com.android.erlcarter.android_quickfit_master.data;

public class CommuityTrendsRecyclerViewData {
    private int userImg;
    private String userName;
    private String content;
    private String dianzanNum;
    private String pingjiaNum;
    private String shoucangNum;

    public CommuityTrendsRecyclerViewData() {}

    public CommuityTrendsRecyclerViewData(int userImg, String userName, String content) {
        this.userImg = userImg;
        this.userName = userName;
        this.content = content;
    }

    public CommuityTrendsRecyclerViewData(int userImg, String userName, String content, String dianzanNum, String pingjiaNum, String shoucangNum) {
        this.userImg = userImg;
        this.userName = userName;
        this.content = content;
        this.dianzanNum = dianzanNum;
        this.pingjiaNum = pingjiaNum;
        this.shoucangNum = shoucangNum;
    }

    public int getUserImg() {
        return userImg;
    }

    public void setUserImg(int userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDianzanNum() {
        return dianzanNum;
    }

    public void setDianzanNum(String dianzanNum) {
        this.dianzanNum = dianzanNum;
    }

    public String getPingjiaNum() {
        return pingjiaNum;
    }

    public void setPingjiaNum(String pingjiaNum) {
        this.pingjiaNum = pingjiaNum;
    }

    public String getShoucangNum() {
        return shoucangNum;
    }

    public void setShoucangNum(String shoucangNum) {
        this.shoucangNum = shoucangNum;
    }
}
