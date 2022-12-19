package com.likes.common.model.dto.report;

import java.math.BigDecimal;


public class RechargeAllDataDO {

    /**
     * 充值金额
     */
    private BigDecimal allrechargeamt;

    /**
     * 充值人数
     */
    private Long allrechargepeoples;
    /**
     * 充值笔数
     */
    private Long allrechargetimes;


    /**
     * 线上
     */
    private BigDecimal onlineamt;

    /**
     *
     */
    private Long onlinepeoples;


    /**
     * 线下
     */
    private BigDecimal offlineamt;

    /**
     *
     */
    private Long offlinepeoples;

    /**
     * 代理
     */
    private BigDecimal agentamt;

    /**
     *
     */
    private Long agentpeoples;

    public BigDecimal getAllrechargeamt() {
        return allrechargeamt;
    }

    public void setAllrechargeamt(BigDecimal allrechargeamt) {
        this.allrechargeamt = allrechargeamt;
    }

    public Long getAllrechargepeoples() {
        return allrechargepeoples;
    }

    public void setAllrechargepeoples(Long allrechargepeoples) {
        this.allrechargepeoples = allrechargepeoples;
    }

    public Long getAllrechargetimes() {
        return allrechargetimes;
    }

    public void setAllrechargetimes(Long allrechargetimes) {
        this.allrechargetimes = allrechargetimes;
    }

    public BigDecimal getOnlineamt() {
        return onlineamt;
    }

    public void setOnlineamt(BigDecimal onlineamt) {
        this.onlineamt = onlineamt;
    }

    public Long getOnlinepeoples() {
        return onlinepeoples;
    }

    public void setOnlinepeoples(Long onlinepeoples) {
        this.onlinepeoples = onlinepeoples;
    }

    public BigDecimal getOfflineamt() {
        return offlineamt;
    }

    public void setOfflineamt(BigDecimal offlineamt) {
        this.offlineamt = offlineamt;
    }

    public Long getOfflinepeoples() {
        return offlinepeoples;
    }

    public void setOfflinepeoples(Long offlinepeoples) {
        this.offlinepeoples = offlinepeoples;
    }

    public BigDecimal getAgentamt() {
        return agentamt;
    }

    public void setAgentamt(BigDecimal agentamt) {
        this.agentamt = agentamt;
    }

    public Long getAgentpeoples() {
        return agentpeoples;
    }

    public void setAgentpeoples(Long agentpeoples) {
        this.agentpeoples = agentpeoples;
    }

    public static RechargeAllDataDO getDefault() {
        RechargeAllDataDO rechargeAllDataDO = new RechargeAllDataDO();
        rechargeAllDataDO.setAllrechargeamt(new BigDecimal("0").setScale(3, BigDecimal.ROUND_DOWN));
        rechargeAllDataDO.setAllrechargepeoples(0L);
        rechargeAllDataDO.setAllrechargetimes(0L);
        rechargeAllDataDO.setOnlineamt(new BigDecimal("0").setScale(3, BigDecimal.ROUND_DOWN));
        rechargeAllDataDO.setOnlinepeoples(0L);
        rechargeAllDataDO.setOfflineamt(new BigDecimal("0").setScale(3, BigDecimal.ROUND_DOWN));
        rechargeAllDataDO.setOfflinepeoples(0L);
        rechargeAllDataDO.setAgentamt(new BigDecimal("0").setScale(3, BigDecimal.ROUND_DOWN));
        rechargeAllDataDO.setAgentpeoples(0L);


        return rechargeAllDataDO;

    }
}