package com.android.erlcarter.android_quickfit_master.data;

public class MembersData {

    private String login_title = "密码登录";   //登录页标题
    private String login_signature = "定制你的饮食方案，一站建立健康档案"; //登录页副标题

    public static MembersData getInstance(){
        return new MembersData();
    }

    public String getLogin_title() {
        return this.login_title;
    }

    public String getLogin_signature() {
        return this.login_signature;
    }
}
