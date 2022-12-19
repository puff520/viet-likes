package com.likes.common.model.response;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.likes.common.constant.Constants;
import com.likes.common.util.DateUtils;
import com.likes.common.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;


public class EntryIncarnateOrderExcelResponse {


    @ExcelProperty(value = "订单号")
    private String orderno;
    @ExcelProperty(value = "会员ID")
    private String uniqueId;
    @ExcelProperty(value = "会员暱称")
    private String nickname;
    @ExcelProperty(value = "提现方式")
    private String accounttypename;
    @ExcelProperty(value = "提现账户")
    private String accountno;
    @ExcelProperty(value = "提现金额")
    private BigDecimal sumamt;
    @ExcelProperty(value = "实际出款金额")
    private BigDecimal realamt;
    @ExcelProperty(value = "会员备注")
    private String remark;
    @ExcelProperty(value = "申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "完成时间")
    private Date finishTime;
    @ExcelProperty(value = "订单状态")
    private String orderstatusText;
    private String orderstatus;
    private String updateUser;
    private Integer accounttype;
    @ExcelProperty(value = "处理信息")
    @ColumnWidth(50)
    private String processInfo;




    public String getProcessInfo() {
        if (StringUtils.isBlank(updateUser) || orderstatus.equals(Constants.ORDER_ORD05)) {
            return "...";
        }
        return "操作者:" + updateUser + " 操作时间:" + DateUtils.formatDate(updateTime, "yyyy-MM-dd HH:mm:ss");
    }




    public String getOrderstatusText() {
        switch (orderstatus) {
            case Constants.ORDER_ORD07:
                this.orderstatusText = "提现处理中";
                break;
            case Constants.ORDER_ORD12:
                this.orderstatusText = "已提现";
                break;
            case Constants.ORDER_ORD14:
                this.orderstatusText = "提现失败";
                break;
            case Constants.ORDER_ORD99:
                this.orderstatusText = "已过期";
            case Constants.ORDER_ORD05:
                this.orderstatusText = "提现申请";
                break;
            default:
                this.orderstatusText = "";
        }
        return this.orderstatusText;
    }

    public String getAccounttypename() {
        switch (accounttype) {
            case Constants.ACCOUNTTYPE_ALIPAY:
                this.accounttypename = Constants.ALIPAY;
                break;
            case Constants.ACCOUNTTYPE_WECHAT:
                this.accounttypename = Constants.WECHAT;
                break;
            case Constants.ACCOUNTTYPE_UNIONPAY:
                this.accounttypename = Constants.UNIONPAY;
                break;
            default:
                this.orderstatusText = "";
        }
        return accounttypename;
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

    public Integer getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Integer accounttype) {
        this.accounttype = accounttype;
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

    public BigDecimal getSumamt() {
        return sumamt;
    }

    public void setSumamt(BigDecimal sumamt) {
        this.sumamt = sumamt;
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

    public void setOrderstatusText(String orderstatusText) {
        this.orderstatusText = orderstatusText;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setProcessInfo(String processInfo) {
        this.processInfo = processInfo;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
