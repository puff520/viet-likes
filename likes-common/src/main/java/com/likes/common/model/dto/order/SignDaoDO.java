package com.likes.common.model.dto.order;

public class SignDaoDO {

    private String accno;
    // 顺序
    private Integer index;
    // 金币数
    private Double goldnum;
    // 是否已经签到
    private Integer issign;
    // 是否代签
    private Integer isdaisign;

    //查询用
    private String signDate;

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Double getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(Double goldnum) {
        this.goldnum = goldnum;
    }

    public Integer getIssign() {
        return issign;
    }

    public void setIssign(Integer issign) {
        this.issign = issign;
    }

    public Integer getIsdaisign() {
        return isdaisign;
    }

    public void setIsdaisign(Integer isdaisign) {
        this.isdaisign = isdaisign;
    }

}
