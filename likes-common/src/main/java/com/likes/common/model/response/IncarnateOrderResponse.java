package com.likes.common.model.response;

import com.likes.common.model.dto.order.OrderAuditDO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IncarnateOrderResponse {


    private String email;
    private String orderid;
    private String orderno;
    private String nickname;
    private Integer accounttype;
    private String accounttypename;
    private String accountno;
    private String accountname;
    private String bankname;
    private String banknamealias;
    private String bankaddress;
    private String orderstatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT-5")
    private Date createTime;
    private String accno;
    private String moneyAddress;
    private BigDecimal realamt;

    private Long paysetid;
    private BigDecimal sumamt;
    private String shouxuandxingzheng;
    private Integer isjihe;  //0 有 9 无
    private Long apycid;

    private Date paydate;

    private  boolean showThirdButton;

    /**
     * 打码量
     */
    private Double damaliang;

    /**
     * 手续费
     */
    private Double xingzhengfei;
    //稽核
    private Double allhaixudamaliang;
    private Double alljiehefei;
    private List<OrderAuditDO> auditList = new ArrayList<OrderAuditDO>();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT-5")
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT-5")
    private Date finishTime;

    private String updateUser;
    private String remark;

    private String memLevel;


    private String uniqueId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Double getAllhaixudamaliang() {
        return allhaixudamaliang;
    }

    public void setAllhaixudamaliang(Double allhaixudamaliang) {
        this.allhaixudamaliang = allhaixudamaliang;
    }

    public Double getAlljiehefei() {
        return alljiehefei;
    }

    public void setAlljiehefei(Double alljiehefei) {
        this.alljiehefei = alljiehefei;
    }

    public List<OrderAuditDO> getAuditList() {
        return auditList;
    }

    public void setAuditList(List<OrderAuditDO> auditList) {
        this.auditList = auditList;
    }

    public Long getApycid() {
        return apycid;
    }

    public void setApycid(Long apycid) {
        this.apycid = apycid;
    }

    public Long getPaysetid() {
        return paysetid;
    }

    public void setPaysetid(Long paysetid) {
        this.paysetid = paysetid;
    }

    public Integer getIsjihe() {
        return isjihe;
    }

    public void setIsjihe(Integer isjihe) {
        this.isjihe = isjihe;
    }

    public BigDecimal getSumamt() {
        return sumamt;
    }

    public void setSumamt(BigDecimal sumamt) {
        this.sumamt = sumamt;
    }

    public String getShouxuandxingzheng() {
        return shouxuandxingzheng;
    }

    public void setShouxuandxingzheng(String shouxuandxingzheng) {
        this.shouxuandxingzheng = shouxuandxingzheng;
    }

    public String getBanknamealias() {
        return banknamealias;
    }

    public void setBanknamealias(String banknamealias) {
        this.banknamealias = banknamealias;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Integer accounttype) {
        this.accounttype = accounttype;
    }

    public String getAccounttypename() {
        return accounttypename;
    }

    public void setAccounttypename(String accounttypename) {
        this.accounttypename = accounttypename;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public BigDecimal getRealamt() {
        return realamt;
    }

    public void setRealamt(BigDecimal realamt) {
        this.realamt = realamt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getDamaliang() {
        return damaliang;
    }

    public void setDamaliang(Double damaliang) {
        this.damaliang = damaliang;
    }

    public Double getXingzhengfei() {
        return xingzhengfei;
    }

    public void setXingzhengfei(Double xingzhengfei) {
        this.xingzhengfei = xingzhengfei;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getMoneyAddress() {
        return moneyAddress;
    }

    public void setMoneyAddress(String moneyAddress) {
        this.moneyAddress = moneyAddress;
    }


    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public boolean isShowThirdButton() {
        return showThirdButton;
    }

    public void setShowThirdButton(boolean showThirdButton) {
        this.showThirdButton = showThirdButton;
    }

    public String getMemLevel() {
        return memLevel;
    }

    public void setMemLevel(String memLevel) {
        this.memLevel = memLevel;
    }
}
