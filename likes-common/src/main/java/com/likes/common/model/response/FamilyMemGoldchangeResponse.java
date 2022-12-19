package com.likes.common.model.response;

import com.likes.common.mybatis.entity.MemGoldchange;

import java.math.BigDecimal;

public class FamilyMemGoldchangeResponse extends MemGoldchange {

    private String refaccno;

    private String accno;

    private String nickname;

    private BigDecimal amount;

    private String bdusername;

    public String getBdusername() {
        return bdusername;
    }

    public void setBdusername(String bdusername) {
        this.bdusername = bdusername;
    }

    public String getRefaccno() {
        return refaccno;
    }

    public void setRefaccno(String refaccno) {
        this.refaccno = refaccno;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
