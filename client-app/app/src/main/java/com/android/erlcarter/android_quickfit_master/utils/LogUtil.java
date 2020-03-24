package com.android.erlcarter.android_quickfit_master.utils;

import android.util.Log;

/**
 * @author erlcarter
 * @date 2020/02/06
 * @update todo
 */
public class LogUtil {
    /**
     * 管理Log的工具类：可通过设置mCurrentLevel，控制Log输出级别。
     * 项目上线时应将mCurrentLevel设置为LEVEL_NONE，禁止Log输出。
     */

    /**日志输出级别N 无*/
    public static int LEVEL_NONE = 0;
    /**日志输出级别E 系统已经出现了错误*/
    public static int LEVEL_ERROR = 1;
    /**日志输出级别W 警告系统出现了异常，即将出现错误*/
    public static int LEVEL_WARING = 2;
    /**日志输出级别I 例如一些运行时的状态信息，这些状态信息在出现问题的时候能提供帮助*/
    public static int LEVEL_INFO = 3;
    /**日志输出级别D 用于调试的信息，编译进产品，但可以在运行时关闭*/
    public static int LEVEL_DEBUG = 4;
    /**日志输出级别V 开发调试过程中一些详细信息，不应该编译进产品中，只在开发阶段使用*/
    public static int LEVEL_VERBOSE = 5;

    /**日志输出文件Tag*/
    private static String mTag = "LogUtil";
    /**当前日志输出级别 默认开发级别*/
    private static int mCurrentLevel = LEVEL_VERBOSE;

    public static LogUtil getInstance() {
        return new LogUtil();
    }

    /**
     * 以级别V的形式输出LOG
     */
    public static void v(String tag,String msg){
        if (mCurrentLevel >= LEVEL_VERBOSE){
            Log.v(tag,msg);
        }
    }

    /**
     * 以级别D的形式输出LOG
     */
    public static void d(String tag,String msg){
        if (mCurrentLevel >= LEVEL_DEBUG){
            Log.d(tag,msg);
        }
    }

    /**
     * 以级别I的形式输出LOG
     */
    public static void i(String tag,String msg){
        if (mCurrentLevel >= LEVEL_INFO){
            Log.i(tag,msg);
        }
    }
    /**
     * 以级别W的形式输出LOG
     */
    public static void w(String tag,String msg){
        if (mCurrentLevel >= LEVEL_WARING){
            Log.w(tag,msg);
        }
    }
    /**
     * 以级别W的形式输出Throwable
     */
    public static void w(Throwable tr){
        if (mCurrentLevel >= LEVEL_WARING){
            Log.w(mTag,"",tr);
        }
    }
    /**
     * 以级别W的形式输出LOG信息和Throwable
     */
    public static void w(String msg,Throwable tr){
        if (mCurrentLevel >= LEVEL_WARING && null != msg ){
            Log.w(mTag,msg,tr);
        }
    }
    /**
     * 以级别E的形式输出LOG
     */
    public static void e(String tag,String msg){
        if (mCurrentLevel >= LEVEL_ERROR){
            Log.e(tag,msg);
        }
    }
    /**
     * 以级别E的形式输出Throwable
     */
    public static void e(Throwable tr){
        if (mCurrentLevel >= LEVEL_ERROR){
            Log.e(mTag,"",tr);
        }
    }
    /**
     * 以级别E的形式输出LOG信息和Throwable
     */
    public static void e(String msg,Throwable tr){
        if (mCurrentLevel >= LEVEL_ERROR && null != msg ){
            Log.e(mTag,msg,tr);
        }
    }
}
