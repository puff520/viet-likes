package com.likes.common.model.dto.report;

import com.likes.common.model.dto.bas.FamilyRoomDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class GiftDailyRoomDO {
    /**
     * 日期
     */
    private String date;

    /**
     * roomid
     */
    private Long roomid;

    /**
     * 礼物
     */
    private BigDecimal giftamt;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public BigDecimal getGiftamt() {
        return giftamt;
    }

    public void setGiftamt(BigDecimal giftamt) {
        this.giftamt = giftamt;
    }


    public GiftDailyRoomDO getGiftamtByRoyaltypercent(FamilyRoomDTO familyRoomDTO) {
        GiftDailyRoomDO giftDailyRoomDO = new GiftDailyRoomDO();
        if (Objects.isNull(giftamt)) {
            giftDailyRoomDO.setGiftamt(BigDecimal.ZERO);
            return giftDailyRoomDO;
        }
        if (Objects.isNull(familyRoomDTO)) {
            giftDailyRoomDO.setGiftamt(giftamt);
            return giftDailyRoomDO;
        }
        BigDecimal royaltypercent = familyRoomDTO.getRoyaltypercent();
        if (Objects.isNull(royaltypercent)) {
            giftDailyRoomDO.setGiftamt(giftamt);
            return giftDailyRoomDO;
        }
        giftDailyRoomDO.setGiftamt(giftamt.multiply(BigDecimal.ONE.subtract(royaltypercent)));
        return giftDailyRoomDO;
    }
}