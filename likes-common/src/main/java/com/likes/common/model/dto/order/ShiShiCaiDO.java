package com.likes.common.model.dto.order;

import com.likes.common.model.dto.bas.BasRuleoddsDO;

import java.util.List;

public class ShiShiCaiDO {

    private Long lotruleid;

    private String rulename;

    private String lotname;

    private List<BasRuleoddsDO> oddslist;

    public Long getLotruleid() {
        return lotruleid;
    }

    public void setLotruleid(Long lotruleid) {
        this.lotruleid = lotruleid;
    }

    public String getRulename() {
        return rulename;
    }

    public void setRulename(String rulename) {
        this.rulename = rulename;
    }

    public String getLotname() {
        return lotname;
    }

    public void setLotname(String lotname) {
        this.lotname = lotname;
    }

    public List<BasRuleoddsDO> getOddslist() {
        return oddslist;
    }

    public void setOddslist(List<BasRuleoddsDO> oddslist) {
        this.oddslist = oddslist;
    }

}
