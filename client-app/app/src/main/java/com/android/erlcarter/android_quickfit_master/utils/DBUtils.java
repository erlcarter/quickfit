package com.android.erlcarter.android_quickfit_master.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.erlcarter.android_quickfit_master.data.Member;

public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;
    public DBUtils(Context context) {
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }
    public static DBUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtils(context);
        }
        return instance;
    }
    /**
     * 保存个人资料信息
     */
    public void saveMemberInfo(Member member) {
        ContentValues cv = new ContentValues();
        cv.put("memberId", member.getMemberId());
        cv.put("mamberUserName", member.getMamberUserName());
        cv.put("mamberPassWord", member.getMamberPassWord());
        cv.put("mamberSex", member.getMamberSex());
        cv.put("mamberBrithday", member.getMamberBrithday());
        cv.put("mamberWeightNow", member.getMamberWeightNow());
        cv.put("mamberWeightTarget", member.getMamberWeightTarget());
        cv.put("mamberWeightOriginal", member.getMamberWeightOriginal());
        cv.put("mamberReduceWeightPlanWeek", member.getMamberReduceWeightPlanWeek());
        cv.put("mamberDisease", member.getMamberDisease());
        cv.put("mamberConsunmptionHabits", member.getMamberConsunmptionHabits());
        db.insert(SQLiteHelper.QF_MEMBER, null, cv);
    }
    /**
     * 获取个人资料信息
     */
    public Member getMemberInfo(String memberUserName) {
        String sql = "SELECT * FROM " + SQLiteHelper.QF_MEMBER + " WHERE userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{memberUserName});
        Member member = null;
        while (cursor.moveToNext()) {
            member = new Member();
            member.setMemberId(cursor.getInt(cursor.getColumnIndex("memberId")));
            member.setMamberUserName(cursor.getString(cursor.getColumnIndex("mamberUserName")));
            member.setMamberPassWord(cursor.getString(cursor.getColumnIndex("mamberPassWord")));
            member.setMamberSex(cursor.getString(cursor.getColumnIndex("mamberSex")));
            member.setMamberBrithday(cursor.getString(cursor.getColumnIndex("mamberBrithday")));
            member.setMamberWeightNow(cursor.getDouble(cursor.getColumnIndex("mamberWeightNow")));
            member.setMamberWeightTarget(cursor.getDouble(cursor.getColumnIndex("mamberWeightTarget")));
            member.setMamberWeightOriginal(cursor.getDouble(cursor.getColumnIndex("mamberWeightOriginal")));
            member.setMamberReduceWeightPlanWeek(cursor.getString(cursor.getColumnIndex("mamberReduceWeightPlanWeek")));
            member.setMamberDisease(cursor.getString(cursor.getColumnIndex("mamberDisease")));
            member.setMamberConsunmptionHabits(cursor.getString(cursor.getColumnIndex("mamberConsunmptionHabits")));
        }
        cursor.close();
        return member;
    }

}
