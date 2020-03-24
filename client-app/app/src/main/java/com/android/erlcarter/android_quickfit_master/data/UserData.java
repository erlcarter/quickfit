package com.android.erlcarter.android_quickfit_master.data;

public class UserData {

    private String UserId;//唯一标识ID
    private String UserName;//用户名
    private String PassWord;//密码

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
