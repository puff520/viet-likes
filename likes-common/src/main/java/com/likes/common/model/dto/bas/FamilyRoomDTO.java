package com.likes.common.model.dto.bas;


import java.math.BigDecimal;

public class FamilyRoomDTO {
    private Long roomid;

    private Long familyid;

    private BigDecimal royaltypercent;

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public Long getFamilyid() {
        return familyid;
    }

    public void setFamilyid(Long familyid) {
        this.familyid = familyid;
    }

    public BigDecimal getRoyaltypercent() {
        return royaltypercent;
    }

    public void setRoyaltypercent(BigDecimal royaltypercent) {
        this.royaltypercent = royaltypercent;
    }
}