package com.likes.common.model.vo.finance;

import java.math.BigDecimal;

public class MemFinanceVO {
    // 累计收益(播币)
    private BigDecimal allliveincome;
    // 已体现受益记录(播币)
    private BigDecimal allincarnate;
    // 当前所需打码量(元)
    private BigDecimal haixudamaliang;
    // 余额(元)
    private BigDecimal goldnum;
    //是否设置了资金提现密码  0 存在 9 不存在;
    private Integer ishavepay;
    //手续费;
    private BigDecimal shouxufei;
    private BigDecimal maxchargeamt;
    private BigDecimal minchargeamt;

    public BigDecimal getMaxchargeamt() {
        return maxchargeamt;
    }

    public void setMaxchargeamt(BigDecimal maxchargeamt) {
        this.maxchargeamt = maxchargeamt;
    }

    public BigDecimal getMinchargeamt() {
        return minchargeamt;
    }

    public void setMinchargeamt(BigDecimal minchargeamt) {
        this.minchargeamt = minchargeamt;
    }

    public BigDecimal getAllliveincome() {
        return allliveincome;
    }

    public void setAllliveincome(BigDecimal allliveincome) {
        this.allliveincome = allliveincome;
    }

    public BigDecimal getAllincarnate() {
        return allincarnate;
    }

    public void setAllincarnate(BigDecimal allincarnate) {
        this.allincarnate = allincarnate;
    }

    public BigDecimal getHaixudamaliang() {
        return haixudamaliang;
    }

    public void setHaixudamaliang(BigDecimal haixudamaliang) {
        this.haixudamaliang = haixudamaliang;
    }

    public BigDecimal getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(BigDecimal goldnum) {
        this.goldnum = goldnum;
    }

    public Integer getIshavepay() {
        return ishavepay;
    }

    public void setIshavepay(Integer ishavepay) {
        this.ishavepay = ishavepay;
    }

    public BigDecimal getShouxufei() {
        return shouxufei;
    }

    public void setShouxufei(BigDecimal shouxufei) {
        this.shouxufei = shouxufei;
    }
}
