package com.likes.common.model.dto.member;

import java.math.BigDecimal;

public class MemFamilyDO {

    private Long familyid;
    private String familyname;
    private String accno;
    private String familyaccno;
    private BigDecimal royaltypercent;

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public Long getFamilyid() {
        return familyid;
    }

    public void setFamilyid(Long familyid) {
        this.familyid = familyid;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getFamilyaccno() {
        return familyaccno;
    }

    public void setFamilyaccno(String familyaccno) {
        this.familyaccno = familyaccno;
    }

    public BigDecimal getRoyaltypercent() {
        return royaltypercent;
    }

    public void setRoyaltypercent(BigDecimal royaltypercent) {
        this.royaltypercent = royaltypercent;
    }


}
