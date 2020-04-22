package com.android.erlcarter.android_quickfit_master.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    public static String DB_NAME = "quickfit.db";

    public static final String QF_MEMBER = "member";// 用户信息

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 创建用户信息表
         */
        db.execSQL("CREATE TABLE  IF NOT EXISTS " + QF_MEMBER + "( "
                + "memberId INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "memberUserName VARCHAR, "// 用户名
                + "memberPassWord VARCHAR, "// 密码
                + "memberSex VARCHAR, "// 性别
                + "memberBrithday VARCHAR, "// 出生日期
                + "memberWeightNow DOUBLE, "// 最新体重
                + "memberWeightTarget DOUBLE, "// 目标体重
                + "memberWeightOriginal DOUBLE, "// 初始体重
                + "memberReduceWeightPlanWeek VARCHAR, "// 计划周期
                + "memberDisease VARCHAR, "// 患有疾病
                + "memberConsunmptionHabits VARCHAR "// 消费习惯
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//数据库版本号增加
        db.execSQL("DROP TABLE IF EXISTS " + QF_MEMBER);
        onCreate(db);
    }
}
