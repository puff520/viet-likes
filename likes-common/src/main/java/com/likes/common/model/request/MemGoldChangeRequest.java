package com.likes.common.model.request;

import java.math.BigDecimal;
import java.util.Date;

public class MemGoldChangeRequest extends BaseRequest {
    private String accno;

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    private Integer reachtype; //搜索类型 ，1名字，2订单，3流水，4说明
    private String keywords;    //关键字 对应reachtype
    //变动类型
    // 1充值 2打赏 3赠送 4簽到獎勵 5發帖獎勵 6 發視頻獎勵 7 邀請用戶 8充值附赠 9 主播分成
    // 10 提現申請 11提現取消 12已提現 13彩票派獎 14彩票下注 15彩票下注取消 16稽核手续费
    // 17稽核手续费取消
    //18 棋牌轉出 19棋牌轉入 20代理結算
    private Integer recordtype;
    private BigDecimal minMoney;// 最小金额
    private BigDecimal maxMoney;//最大金额
    private Date recorddate;    //更新日期
    private Date startTime;     //开始时间

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    private Date endTime;       //结束时间

    public Integer getReachtype() {
        return reachtype;
    }

    public void setReachtype(Integer reachtype) {
        this.reachtype = reachtype;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getRecordtype() {
        return recordtype;
    }

    public void setRecordtype(Integer recordtype) {
        this.recordtype = recordtype;
    }

    public BigDecimal getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    public BigDecimal getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }
}
