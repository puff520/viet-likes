package com.likes.common.model.dto.order;


import com.likes.common.mybatis.entity.KillConfig;
import com.likes.common.mybatis.entity.OrderBetRecord;

public class OrderBetKillDto extends OrderBetRecord {
    private String odds;

    private KillConfig killConfig;

    private long waittime;

    public long getWaittime() {
        return waittime;
    }

    public void setWaittime(long waittime) {
        this.waittime = waittime;
    }

    public KillConfig getKillConfig() {
        return killConfig;
    }

    public void setKillConfig(KillConfig killConfig) {
        this.killConfig = killConfig;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }
}

