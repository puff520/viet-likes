package com.likes.common.model;

import java.math.BigDecimal;

public class GreatPersonList {
    private String nickname;
    // 会员等级
    private String memlevel;
    // 头像
    private String headimg;
    // 总下注次数
    private int totalnums;
    // 中奖次数
    private int lucknums;
    // 胜率 #.##
    private BigDecimal luckrage;
    // 彩种名称
    private String lotname;
    // 玩法
    private String rulename;
    // 期数
    private String period;
    // 开奖结束时间 s
    private Long endsecond;
    // 下注订单id
    private Long orderid;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getTotalnums() {
        return totalnums;
    }

    public void setTotalnums(int totalnums) {
        this.totalnums = totalnums;
    }

    public int getLucknums() {
        return lucknums;
    }

    public void setLucknums(int lucknums) {
        this.lucknums = lucknums;
    }

    public BigDecimal getLuckrage() {
        return luckrage;
    }

    public void setLuckrage(BigDecimal luckrage) {
        this.luckrage = luckrage;
    }

    public String getLotname() {
        return lotname;
    }

    public void setLotname(String lotname) {
        this.lotname = lotname;
    }

    public String getRulename() {
        return rulename;
    }

    public void setRulename(String rulename) {
        this.rulename = rulename;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Long getEndsecond() {
        return endsecond;
    }

    public void setEndsecond(Long endsecond) {
        this.endsecond = endsecond;
    }
}
