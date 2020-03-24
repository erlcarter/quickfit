package com.android.erlcarter.android_quickfit_master.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.*;

import java.util.*;

/**
 * @author erlcarter
 * @date 2020/02/06
 * @update todo
 */
public class SharedPreferencesUtil {

    private static SharedPreferencesUtil util;
    private static SharedPreferences sp;

    private SharedPreferencesUtil(Context context, String name){
        sp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }

    /**
     * 初始化SharedPreferencesUtil,只需要初始化一次,建议在Application中初始化
     * @param context
     * @param name SharedPreferences Name
     */
    public static void getInstance(Context context,String name){
        if (util == null){
            util = new SharedPreferencesUtil(context,name);
        }
    }

    /**
     * 保存数据到SharedPreferences
     * @param key   键
     * @param value 值
     * @return  结果
     */
    public static boolean putData(String key,Object value){
        boolean result;
        SharedPreferences.Editor editor = sp.edit();
        String type = value.getClass().getSimpleName();
        try{
            switch (type){
                case "Boolean":
                    editor.putBoolean(key, (Boolean) value);
                    break;
                case "Long":
                    editor.putLong(key, (Long) value);
                    break;
                case "Float":
                    editor.putFloat(key, (Float) value);
                    break;
                case "String":
                    editor.putString(key, (String) value);
                    break;
                case "Integer":
                    editor.putInt(key, (Integer) value);
                    break;
                default:
                    Gson gson = new Gson();
                    String json = gson.toJson(value);
                    editor.putString(key,json);
                    break;
            }
            result = true;
        }catch (Exception e){
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }

    /**
     * 获取SharedPreferences里的数据
     * @param key   键
     * @param defaultValue  值
     * @return  结果
     */
    public static Object getData(String key,Object defaultValue){
        Object result;
        String type = defaultValue.getClass().getSimpleName();
        try{
            switch (type){
                case "Boolean":
                    result = sp.getBoolean(key, (Boolean) defaultValue);
                    break;
                case "Long":
                    result = sp.getLong(key, (Long) defaultValue);
                    break;
                case "Float":
                    result = sp.getFloat(key, (Float) defaultValue);
                    break;
                case "String":
                    result = sp.getString(key, (String) defaultValue);
                    break;
                case "Integer":
                    result = sp.getInt(key, (Integer) defaultValue);
                    break;
                default:
                    Gson gson = new Gson();
                    String json = sp.getString(key,"");
                    if (!json.equals("") && json.length() >= 0 ){
                        result = gson.fromJson(json,defaultValue.getClass());
                    }else {
                        result = defaultValue;
                    }
                    break;
            }
        }catch (Exception e){
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     *用于保存集合
     * @param key
     * @param list
     * @param <T>
     * @return 保存结果
     */
    public static <T> boolean putListData(String key, List<T> list){
        boolean result;
        String type = list.get(0).getClass().getSimpleName();
        SharedPreferences.Editor editor = sp.edit();
        JsonArray jsonArray = new JsonArray();
        try{
            switch (type){
                case "Boolean":
                    for (int i=0;i<list.size();i++){
                        jsonArray.add((Boolean) list.get(i));
                    }
                    break;
                case "Long":
                    for (int i=0;i<list.size();i++){
                        jsonArray.add((Long)list.get(i));
                    }
                    break;
                case "Float":
                    for (int i=0;i<list.size();i++){
                        jsonArray.add((Float)list.get(i));
                    }
                    break;
                case "String":
                    for (int i=0;i<list.size();i++){
                        jsonArray.add((String)list.get(i));
                    }
                    break;
                case "Integer":
                    for (int i=0;i<list.size();i++){
                        jsonArray.add((Integer)list.get(i));
                    }
                    break;
                default:
                    Gson gson = new Gson();
                    for (int i=0;i<list.size();i++){
                        JsonElement obj = gson.toJsonTree(list.get(i));
                        jsonArray.add(obj);
                    }
                    break;
            }
            editor.putString(key,jsonArray.toString());
            result = true;
        }catch (Exception e){
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }

    /**
     * 获取保存的List
     * @param key
     * @param cls
     * @param <T>
     * @return  对应的List集合
     */
    public static <T> List<T> getListData(String key,Class<T> cls){
        List<T> list = new ArrayList<T>();
        String json = sp.getString(key,"");
        if(!json.equals("") && json.length()>0){
            Gson gson = new Gson();
            JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement jElement : jsonArray) {
                list.add(gson.fromJson(jElement,cls));
            }
        }
        return list;
    }

    /**
     * 用于保存集合
     * @param key
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static<K,V>boolean putHashMapData(String key, Map<K,V> map){
        Boolean result;
        SharedPreferences.Editor editor = sp.edit();
        try{
            Gson gson = new Gson();
            String json = gson.toJson(map);
            editor.putString(key,json);
            result = true;
        }catch (Exception e){
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }

    /**
     * 获取保存的集合
     * @param key
     * @param clsV
     * @param <K>
     * @param <V>
     * @return HashMap
     */
    public static<K,V> HashMap<String,V> getHashMapData(String key, Class<V> clsV){
        String json = sp.getString(key,"");
        HashMap<String,V> map = new HashMap<>();
        Gson gson = new Gson();
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Set<Map.Entry<String,JsonElement>> entrySet = obj.entrySet();
        for (Map.Entry<String,JsonElement> entry : entrySet) {
            String entryKey = entry.getKey();
            JsonObject value = (JsonObject) entry.getValue();
            map.put(entryKey,gson.fromJson(value,clsV));
        }
        LogUtil.e("SharedPreferencesUtil",obj.toString());
        return map;
    }

}
