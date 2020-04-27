package com.android.erlcarter.android_quickfit_master.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by xyz on 2019/4/11.
 * 获取用户的登录信息
 * 解析json码
 * 设置图片的状态
 */

public class AnalysisUtils {
    public static String readLoginUserName(Context context){
        SharedPreferences sp = context.getSharedPreferences("loginInfo",MODE_PRIVATE);
        return  sp.getString("LoginUserName","");
    }

    public static String readUserImage(Context context){
        SharedPreferences sp = context.getSharedPreferences("loginInfo",MODE_PRIVATE);
        return  sp.getString("usesImage","");
    }

    //解析json文件返回习题详情信息的集合
/*    public static List<ExercisesBean> getDetailsInfosFromJson(InputStream is)
            throws IOException {
        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        String json = new String(buffer, "utf-8");
        //使用gson库解析JSON数据
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ExercisesBean>>() { }.getType();
        List<ExercisesBean> detailsInfos = gson.fromJson(json, listType);
        return detailsInfos;
    }

    *//**
     * 设置A、B、C、D选项是否可点击
     *//*
    public static void setABCDEnable(boolean value, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d){
        iv_a.setEnabled(value);
        iv_b.setEnabled(value);
        iv_c.setEnabled(value);
        iv_d.setEnabled(value);
    }

    //解析json文件返回习题详情信息的集合
    public static List<VideoBean> getVideoInfosFromJson(InputStream is, int id)
            throws IOException {
        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        String json = new String(buffer, "utf-8");
        //使用gson库解析JSON数据
        Gson gson = new Gson();
        Type listType = new TypeToken<List<VideoBean>>() { }.getType();
        List<VideoBean> videoInfos = gson.fromJson(json, listType);
        //查找符合单元id的数据源
        List<VideoBean> videoBeanList = new ArrayList<VideoBean>();
        for (int i=0;i<videoInfos.size();i++){
            VideoBean bean = videoInfos.get(i);
            if (bean.chapterId==id){
                videoBeanList.add(bean);
            }
        }
        return videoBeanList;
    }*/

}
