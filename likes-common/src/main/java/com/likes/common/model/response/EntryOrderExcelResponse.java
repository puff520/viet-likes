package com.likes.common.model.response;

import com.alibaba.excel.annotation.ExcelProperty;
import com.likes.common.constant.Constants;
import com.likes.common.util.DateUtils;
import com.likes.common.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.util.Date;

public class EntryOrderExcelResponse {
    @ExcelProperty(value = "订单号")
    private String orderno;
    @ExcelProperty(value = "会员暱称")
    private String nickname;
    @ExcelProperty(value = "会员ID")
    private String uniqueId;
    @ExcelProperty(value = "会员备注")
    private String remark;

    @ExcelProperty(value = "实际充入播币")
    private BigDecimal realamt;
    @ExcelProperty(value = "充值赠送播币")
    private Double givegold;
    @ExcelProperty(value = "存款人姓名")
    private String payuser;
    @ExcelProperty(value = "存款备注")
    private String paynote;
    private String ordernote;
    @ExcelProperty(value = "申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    @ExcelProperty(value = "订单状态")
    private String orderstatusText;
    private String orderstatus;
    private String modifyusername;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @ExcelProperty(value = "完成时间")
    private String completionTime;

    public String getCompletionTime() {
        if (!Constants.ORDER_ORD13.equals(orderstatus) && !Constants.ORDER_ORD08.equals(orderstatus)) {
            return "";
        }
        return DateFormatUtils.format(updateTime, "yyyy-MM-dd HH:mm:ss");
    }

    @ExcelProperty(value = "处理信息")
    private String processInfo;

    public String getProcessInfo() {
        if (StringUtils.isBlank(modifyusername)) {
            return "...";
        }

        return "操作者:" + modifyusername + " 操作时间:" + DateUtils.formatDate(updateTime, "yyyy-MM-dd HH:mm:ss");
    }

    public String getOrderstatusText() {
        switch (orderstatus) {
            case Constants.ORDER_ORD04:
                this.orderstatusText = "待付款";
                break;
            case Constants.ORDER_ORD08:
                this.orderstatusText = "已付款";
                break;
            case Constants.ORDER_ORD13:
                this.orderstatusText = "充值失败";
                break;
            case Constants.ORDER_ORD99:
                this.orderstatusText = "已过期";
                break;
            default:
                this.orderstatusText = "";
        }
        return this.orderstatusText;
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

}
