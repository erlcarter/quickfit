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
        cv.put("memberUserName", member.getMamberUserName());
        cv.put("memberPassWord", member.getMamberPassWord());
        cv.put("memberImagePath",member.getMemberImagePath());
        cv.put("memberSex", member.getMamberSex());
        cv.put("memberBrithday", member.getMamberBrithday());
        cv.put("memberWeightNow", member.getMamberWeightNow());
        cv.put("memberWeightTarget", member.getMamberWeightTarget());
        cv.put("memberWeightOriginal", member.getMamberWeightOriginal());
        cv.put("memberReduceWeightPlanWeek", member.getMamberReduceWeightPlanWeek());
        cv.put("memberDisease", member.getMamberDisease());
        cv.put("memberConsunmptionHabits", member.getMamberConsunmptionHabits());
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
            member.setMamberUserName(cursor.getString(cursor.getColumnIndex("memberUserName")));
            member.setMamberPassWord(cursor.getString(cursor.getColumnIndex("memberPassWord")));
            member.setMemberImagePath(cursor.getString(cursor.getColumnIndex("memberImagePath")));
            member.setMamberSex(cursor.getString(cursor.getColumnIndex("memberSex")));
            member.setMamberBrithday(cursor.getString(cursor.getColumnIndex("memberBrithday")));
            member.setMamberWeightNow(cursor.getDouble(cursor.getColumnIndex("memberWeightNow")));
            member.setMamberWeightTarget(cursor.getDouble(cursor.getColumnIndex("memberWeightTarget")));
            member.setMamberWeightOriginal(cursor.getDouble(cursor.getColumnIndex("memberWeightOriginal")));
            member.setMamberReduceWeightPlanWeek(cursor.getString(cursor.getColumnIndex("memberReduceWeightPlanWeek")));
            member.setMamberDisease(cursor.getString(cursor.getColumnIndex("memberDisease")));
            member.setMamberConsunmptionHabits(cursor.getString(cursor.getColumnIndex("memberConsunmptionHabits")));
        }
        cursor.close();
        return member;
    }

}
