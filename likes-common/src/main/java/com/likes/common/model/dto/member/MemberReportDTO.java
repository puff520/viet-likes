package com.likes.common.model.dto.member;

import java.math.BigDecimal;
import java.util.Date;

public class MemberReportDTO {
    private Long reportid;//会员报表ID				
    private Long memid;//会员ID					
    private String accno;//会员标识号				
    private String nickname;//呢称				
    private String memname;//真实姓名				
    private String mobileno;//手机号码				
    private Long levelid;//等级id					
    private String levelname;//等级名称
    private BigDecimal amount;//会员账户金额					
    private Long refmemid;//上级会员ID					
    private String refaccno;//上级会员标识号				
    private String refnickname;//上级呢称				
    private String refmemname;//上级真实姓名				
    private String refmobileno;//上级手机号码				
    private Integer gettasknum;//接受任务次数					
    private Integer finishtasknum;//完成任务次数					
    private Integer rechargenum;//充值次数					
    private BigDecimal rechargesum;//充值总额					
    private Integer agentnum;//代理提成次数					
    private BigDecimal agentsum;//代理提成总额					
    private Integer withdrawalnum;//提现次数					
    private BigDecimal withdrawalsum;//提现总额					
    private Date registertime;//注册时间					
    private Date recorddate;//记录添加时间					
    private Date lastupdatetime;//记录最后更新时间					

    public Long getReportid() {
        return reportid;
    }

    public void setReportid(Long reportid) {
        this.reportid = reportid;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
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

    public String getMemname() {
        return memname;
    }

    public void setMemname(String memname) {
        this.memname = memname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public Long getLevelid() {
        return levelid;
    }

    public void setLevelid(Long levelid) {
        this.levelid = levelid;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getRefmemid() {
        return refmemid;
    }

    public void setRefmemid(Long refmemid) {
        this.refmemid = refmemid;
    }

    public String getRefaccno() {
        return refaccno;
    }

    public void setRefaccno(String refaccno) {
        this.refaccno = refaccno;
    }

    public String getRefnickname() {
        return refnickname;
    }

    public void setRefnickname(String refnickname) {
        this.refnickname = refnickname;
    }

    public String getRefmemname() {
        return refmemname;
    }

    public void setRefmemname(String refmemname) {
        this.refmemname = refmemname;
    }

    public String getRefmobileno() {
        return refmobileno;
    }

    public void setRefmobileno(String refmobileno) {
        this.refmobileno = refmobileno;
    }

    public Integer getGettasknum() {
        return gettasknum;
    }

    public void setGettasknum(Integer gettasknum) {
        this.gettasknum = gettasknum;
    }

    public Integer getFinishtasknum() {
        return finishtasknum;
    }

    public void setFinishtasknum(Integer finishtasknum) {
        this.finishtasknum = finishtasknum;
    }

    public Integer getRechargenum() {
        return rechargenum;
    }

    public void setRechargenum(Integer rechargenum) {
        this.rechargenum = rechargenum;
    }

    public BigDecimal getRechargesum() {
        return rechargesum;
    }

    public void setRechargesum(BigDecimal rechargesum) {
        this.rechargesum = rechargesum;
    }

    public Integer getAgentnum() {
        return agentnum;
    }

    public void setAgentnum(Integer agentnum) {
        this.agentnum = agentnum;
    }

    public BigDecimal getAgentsum() {
        return agentsum;
    }

    public void setAgentsum(BigDecimal agentsum) {
        this.agentsum = agentsum;
    }

    public Integer getWithdrawalnum() {
        return withdrawalnum;
    }

    public void setWithdrawalnum(Integer withdrawalnum) {
        this.withdrawalnum = withdrawalnum;
    }

    public BigDecimal getWithdrawalsum() {
        return withdrawalsum;
    }

    public void setWithdrawalsum(BigDecimal withdrawalsum) {
        this.withdrawalsum = withdrawalsum;
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }
}
