package com.likes.common.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TraAgentclearingResp {
    private Long cleanid;

    private String accno;

    private String cleantype;

    private String refids;

    private BigDecimal chargeamt;

    private BigDecimal reverseamt;

    private String buttonnote;

    private Integer isdelete;

    private String createuser;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdate;

    private String modifyuser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifydate;

    private Long agentid;

    private String agentname;

    private BigDecimal minamt;

    private BigDecimal maxamt;

    private BigDecimal commission;

    public Long getAgentid() {
        return agentid;
    }

    public void setAgentid(Long agentid) {
        this.agentid = agentid;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public BigDecimal getMinamt() {
        return minamt;
    }

    public void setMinamt(BigDecimal minamt) {
        this.minamt = minamt;
    }

    public BigDecimal getMaxamt() {
        return maxamt;
    }

    public void setMaxamt(BigDecimal maxamt) {
        this.maxamt = maxamt;
    }

    public Long getCleanid() {
        return cleanid;
    }

    public void setCleanid(Long cleanid) {
        this.cleanid = cleanid;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getCleantype() {
        return cleantype;
    }

    public void setCleantype(String cleantype) {
        this.cleantype = cleantype;
    }

    public String getRefids() {
        return refids;
    }

    public void setRefids(String refids) {
        this.refids = refids;
    }

    public BigDecimal getChargeamt() {
        return chargeamt;
    }

    public void setChargeamt(BigDecimal chargeamt) {
        this.chargeamt = chargeamt;
    }

    public BigDecimal getReverseamt() {
        return reverseamt;
    }

    public void setReverseamt(BigDecimal reverseamt) {
        this.reverseamt = reverseamt;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public String getButtonnote() {
        return buttonnote;
    }

    public void setButtonnote(String buttonnote) {
        this.buttonnote = buttonnote;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}
