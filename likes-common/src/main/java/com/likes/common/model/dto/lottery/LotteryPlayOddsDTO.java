package com.likes.common.model.dto.lottery;


import com.likes.common.mybatis.entity.LotteryPlayOdds;

public class LotteryPlayOddsDTO extends LotteryPlayOdds {

    private String odds;

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }
}
