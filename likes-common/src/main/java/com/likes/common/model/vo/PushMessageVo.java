package com.likes.common.model.vo;

import com.likes.common.mybatis.entity.CpActivityInfo;
import com.likes.common.mybatis.entity.Lottery;
import com.likes.common.mybatis.entity.LotteryCategory;

import java.util.List;


public class PushMessageVo {
    private List<LotteryCategory> lotteryCategories;
    private List<Lottery> LotteryList ;
    private  List<CpActivityInfo> cpActivityInfocs;

    public List<LotteryCategory> getLotteryCategories() {
        return lotteryCategories;
    }

    public void setLotteryCategories(List<LotteryCategory> lotteryCategories) {
        this.lotteryCategories = lotteryCategories;
    }

    public List<Lottery> getLotteryList() {
        return LotteryList;
    }

    public void setLotteryList(List<Lottery> lotteryList) {
        LotteryList = lotteryList;
    }

    public List<CpActivityInfo> getCpActivityInfocs() {
        return cpActivityInfocs;
    }

    public void setCpActivityInfocs(List<CpActivityInfo> cpActivityInfocs) {
        this.cpActivityInfocs = cpActivityInfocs;
    }
}
