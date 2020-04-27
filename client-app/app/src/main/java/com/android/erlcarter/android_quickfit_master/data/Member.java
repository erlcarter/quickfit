package com.android.erlcarter.android_quickfit_master.data;

import java.util.Date;

public class Member {

    private int memberId;

    private String mamberUserName;

    private String mamberPassWord;

    private String memberImagePath;

    private String mamberSex;

    private String mamberBrithday;

    private Double mamberWeightNow;

    private Double mamberWeightTarget;

    private Double mamberWeightOriginal;

    private String mamberReduceWeightPlanWeek;

    private String mamberDisease;

    private String mamberConsunmptionHabits;

    public Member() {}

    public Member(int memberId, String mamberUserName, String mamberPassWord) {
        this.memberId = memberId;
        this.mamberUserName = mamberUserName;
        this.mamberPassWord = mamberPassWord;
    }

    public Member(String mamberSex, String mamberBrithday, Double mamberWeightNow,
                  Double mamberWeightTarget, Double mamberWeightOriginal, String mamberReduceWeightPlanWeek,
                  String mamberDisease, String mamberConsunmptionHabits,String memberImagePath) {
        this.mamberSex = mamberSex;
        this.mamberBrithday = mamberBrithday;
        this.mamberWeightNow = mamberWeightNow;
        this.mamberWeightTarget = mamberWeightTarget;
        this.mamberWeightOriginal = mamberWeightOriginal;
        this.mamberReduceWeightPlanWeek = mamberReduceWeightPlanWeek;
        this.mamberDisease = mamberDisease;
        this.mamberConsunmptionHabits = mamberConsunmptionHabits;
        this.memberImagePath = memberImagePath;
    }

    public Member(int memberId, String mamberUserName, String mamberPassWord,
                  String mamberSex, String mamberBrithday, Double mamberWeightNow,
                  Double mamberWeightTarget, Double mamberWeightOriginal, String mamberReduceWeightPlanWeek,
                  String mamberDisease, String mamberConsunmptionHabits,String memberImagePath) {
        this.memberId = memberId;
        this.mamberUserName = mamberUserName;
        this.mamberPassWord = mamberPassWord;
        this.mamberSex = mamberSex;
        this.mamberBrithday = mamberBrithday;
        this.mamberWeightNow = mamberWeightNow;
        this.mamberWeightTarget = mamberWeightTarget;
        this.mamberWeightOriginal = mamberWeightOriginal;
        this.mamberReduceWeightPlanWeek = mamberReduceWeightPlanWeek;
        this.mamberDisease = mamberDisease;
        this.mamberConsunmptionHabits = mamberConsunmptionHabits;
        this.memberImagePath = memberImagePath;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMamberUserName() {
        return mamberUserName;
    }

    public void setMamberUserName(String mamberUserName) {
        this.mamberUserName = mamberUserName;
    }

    public String getMamberPassWord() {
        return mamberPassWord;
    }

    public void setMamberPassWord(String mamberPassWord) {
        this.mamberPassWord = mamberPassWord;
    }

    public String getMamberSex() {
        return mamberSex;
    }

    public void setMamberSex(String mamberSex) {
        this.mamberSex = mamberSex;
    }

    public String getMamberBrithday() {
        return mamberBrithday;
    }

    public void setMamberBrithday(String mamberBrithday) {
        this.mamberBrithday = mamberBrithday;
    }

    public Double getMamberWeightNow() {
        return mamberWeightNow;
    }

    public void setMamberWeightNow(Double mamberWeightNow) {
        this.mamberWeightNow = mamberWeightNow;
    }

    public Double getMamberWeightTarget() {
        return mamberWeightTarget;
    }

    public void setMamberWeightTarget(Double mamberWeightTarget) {
        this.mamberWeightTarget = mamberWeightTarget;
    }

    public Double getMamberWeightOriginal() {
        return mamberWeightOriginal;
    }

    public void setMamberWeightOriginal(Double mamberWeightOriginal) {
        this.mamberWeightOriginal = mamberWeightOriginal;
    }

    public String getMamberReduceWeightPlanWeek() {
        return mamberReduceWeightPlanWeek;
    }

    public void setMamberReduceWeightPlanWeek(String mamberReduceWeightPlanWeek) {
        this.mamberReduceWeightPlanWeek = mamberReduceWeightPlanWeek;
    }

    public String getMamberDisease() {
        return mamberDisease;
    }

    public void setMamberDisease(String mamberDisease) {
        this.mamberDisease = mamberDisease;
    }

    public String getMamberConsunmptionHabits() {
        return mamberConsunmptionHabits;
    }

    public void setMamberConsunmptionHabits(String mamberConsunmptionHabits) {
        this.mamberConsunmptionHabits = mamberConsunmptionHabits;
    }

    public String getMemberImagePath() {
        return memberImagePath;
    }

    public void setMemberImagePath(String memberImagePath) {
        this.memberImagePath = memberImagePath;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", mamberUserName='" + mamberUserName + '\'' +
                ", mamberPassWord='" + mamberPassWord + '\'' +
                ", memberImagePath='" + memberImagePath + '\'' +
                ", mamberSex='" + mamberSex + '\'' +
                ", mamberBrithday='" + mamberBrithday + '\'' +
                ", mamberWeightNow=" + mamberWeightNow +
                ", mamberWeightTarget=" + mamberWeightTarget +
                ", mamberWeightOriginal=" + mamberWeightOriginal +
                ", mamberReduceWeightPlanWeek='" + mamberReduceWeightPlanWeek + '\'' +
                ", mamberDisease='" + mamberDisease + '\'' +
                ", mamberConsunmptionHabits='" + mamberConsunmptionHabits + '\'' +
                '}';
    }
}
