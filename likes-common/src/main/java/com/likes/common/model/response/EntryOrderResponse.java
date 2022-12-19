package com.likes.common.model.response;

import com.likes.common.constant.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.util.Date;

public class EntryOrderResponse {
    private String orderid;
    private String orderno;
    private String nickname;
    private String uniqueId;
    private BigDecimal realamt;
    private BigDecimal givepercent;
    private Double rechargegold;
    private Double givegold;
    private String payuser;
    private String paynote;
    private String ordernote;
    private String remark;
    private String moneyAddress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT-5")
    private Date createdate;
    private String orderstatus;
    private String modifyusername;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT-5")
    private Date updateTime;
    private Long tpaysetid;
    private String provider;
    private String mobileno;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT-5")
    private Date paydate;


    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    private String completionTime;

    public String getCompletionTime() {
        if (!Constants.ORDER_ORD13.equals(orderstatus) && !Constants.ORDER_ORD08.equals(orderstatus)) {
            return "";
        }
        return DateFormatUtils.format(updateTime, "yyyy-MM-dd HH:mm:ss");
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Long getTpaysetid() {
        return tpaysetid;
    }

    public void setTpaysetid(Long tpaysetid) {
        this.tpaysetid = tpaysetid;
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

    public String getOrdernote() {
        return ordernote;
    }

    public void setOrdernote(String ordernote) {
        this.ordernote = ordernote;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPayuser() {
        return payuser;
    }

    public void setPayuser(String payuser) {
        this.payuser = payuser;
    }

    public String getPaynote() {
        return paynote;
    }

    public void setPaynote(String paynote) {
        this.paynote = paynote;
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

    public BigDecimal getRealamt() {
        return realamt;
    }

    public void setRealamt(BigDecimal realamt) {
        this.realamt = realamt;
    }

    public BigDecimal getGivepercent() {
        return givepercent;
    }

    public void setGivepercent(BigDecimal givepercent) {
        this.givepercent = givepercent;
    }

    public Double getRechargegold() {
        return rechargegold;
    }

    public void setRechargegold(Double rechargegold) {
        this.rechargegold = rechargegold;
    }

    public Double getGivegold() {
        return givegold;
    }

    public void setGivegold(Double givegold) {
        this.givegold = givegold;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getModifyusername() {
        return modifyusername;
    }

    public void setModifyusername(String modifyusername) {
        this.modifyusername = modifyusername;
    }

    public String getMoneyAddress() {
        return moneyAddress;
    }

    public void setMoneyAddress(String moneyAddress) {
        this.moneyAddress = moneyAddress;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }
}
