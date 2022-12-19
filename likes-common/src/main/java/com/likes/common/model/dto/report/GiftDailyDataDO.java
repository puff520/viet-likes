package com.likes.common.model.dto.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;


public class GiftDailyDataDO {
    /**
     * 日期
     */
    private String date;

    /**
     * 直播总收入
     */
    private BigDecimal giftsumamt;
    /**
     * 直播礼物平台分成
     */
    private BigDecimal giftamt;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getGiftsumamt() {
        return giftsumamt;
    }

    public void setGiftsumamt(BigDecimal giftsumamt) {
        this.giftsumamt = giftsumamt;
    }

    public BigDecimal getGiftamt() {
        return giftamt;
    }

    public void setGiftamt(BigDecimal giftamt) {
        this.giftamt = giftamt;
    }

    public GameDailyDataDO getDefaultByRoyaltypercent() {
        GameDailyDataDO gameDailyDataDO = new GameDailyDataDO();
        gameDailyDataDO.setGameamt(giftsumamt);
        gameDailyDataDO.setGameawardamt(giftamt);
        return gameDailyDataDO;

    }
}