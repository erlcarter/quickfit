package com.android.erlcarter.android_quickfit_master.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtils {

    static OkHttpClient client = null;

    public static void sendMessage(String url, RequestBody requestBody, Callback callback){
        client = new OkHttpClient();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }

}
