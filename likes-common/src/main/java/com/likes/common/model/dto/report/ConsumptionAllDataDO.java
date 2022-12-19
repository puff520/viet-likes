package com.likes.common.model.dto.report;


import java.math.BigDecimal;


public class ConsumptionAllDataDO {

    /**
     * 累计打码
     */
    private BigDecimal allconsumptionamt;

    /**
     * 累计打码人数
     */
    private Long allconsumptionpeoples;


    /**
     * 彩票投注金额
     */
    private BigDecimal lotamt;

    /**
     * 彩票投注人数
     */
    private Long lotpeoples;
    /**
     * 游戏投注金额
     */
    private BigDecimal gameamt;

    /**
     * 游戏中奖
     */
    private BigDecimal gameawardamt;

    /**
     * 游戏投注人数
     */
    private Long gamepeoples;

    /**
     * 大神金额
     */
    private BigDecimal godplanamt;

    /**
     * 大神人数
     */
    private Long godplanpeoples;


    /**
     * 礼物金额
     */
    private BigDecimal giftamt;

    /**
     * 礼物人数
     */
    private Long giftpeoples;

    public BigDecimal getGameawardamt() {
        return gameawardamt;
    }

    public void setGameawardamt(BigDecimal gameawardamt) {
        this.gameawardamt = gameawardamt;
    }

    public BigDecimal getAllconsumptionamt() {
        return allconsumptionamt;
    }

    public void setAllconsumptionamt(BigDecimal allconsumptionamt) {
        this.allconsumptionamt = allconsumptionamt;
    }

    public Long getAllconsumptionpeoples() {
        return allconsumptionpeoples;
    }

    public void setAllconsumptionpeoples(Long allconsumptionpeoples) {
        this.allconsumptionpeoples = allconsumptionpeoples;
    }

    public BigDecimal getLotamt() {
        return lotamt;
    }

    public void setLotamt(BigDecimal lotamt) {
        this.lotamt = lotamt;
    }

    public Long getLotpeoples() {
        return lotpeoples;
    }

    public void setLotpeoples(Long lotpeoples) {
        this.lotpeoples = lotpeoples;
    }

    public BigDecimal getGameamt() {
        return gameamt;
    }

    public void setGameamt(BigDecimal gameamt) {
        this.gameamt = gameamt;
    }

    public Long getGamepeoples() {
        return gamepeoples;
    }

    public void setGamepeoples(Long gamepeoples) {
        this.gamepeoples = gamepeoples;
    }

    public BigDecimal getGodplanamt() {
        return godplanamt;
    }

    public void setGodplanamt(BigDecimal godplanamt) {
        this.godplanamt = godplanamt;
    }

    public Long getGodplanpeoples() {
        return godplanpeoples;
    }

    public void setGodplanpeoples(Long godplanpeoples) {
        this.godplanpeoples = godplanpeoples;
    }

    public BigDecimal getGiftamt() {
        return giftamt;
    }

    public void setGiftamt(BigDecimal giftamt) {
        this.giftamt = giftamt;
    }

    public Long getGiftpeoples() {
        return giftpeoples;
    }

    public void setGiftpeoples(Long giftpeoples) {
        this.giftpeoples = giftpeoples;
    }
}