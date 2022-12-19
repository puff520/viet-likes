package com.likes.common.model.vo.money;

import com.likes.common.constant.PropertyConstant;
import com.likes.common.mybatis.entity.PaymentSummary;
import com.likes.common.util.DateUtils;
import com.likes.common.util.ViewUtil;

public class OfflinePaymentVO extends PaymentSummary {
    private static final long serialVersionUID = 1L;
    private String channelName;
    private String bankName;
    private String bankSkf;
    private String bankAccountLastFour; //银行卡后4位

    private String userName;
    private String realName;
    private String vipLevel;
    private String loginIp;
    private String remark;

    public String getStatusText() {
        Integer status = this.getStatus();
        if (null == status || status <= 0) {
            return "";
        }
        if (PropertyConstant.PaymentSummaryStauts.SUCCESS == status) {
            return "充值成功";
        }
        if (PropertyConstant.PaymentSummaryStauts.FAIL == status) {
            return "已拒绝";
        }
        if (PropertyConstant.PaymentSummaryStauts.WAIT == status) {
            return "待验证";
        }
        return "";
    }

    //做页面端金额颜色显示用
    public String getAmountColor() {
        return ViewUtil.getAmountColor(this.getAmount());
    }

    public String getCreateTimeString() {
        return DateUtils.formatDate(this.getCreateTime());
    }

    public String getCheckTimeString() {
        return DateUtils.formatDate(this.getCheckTime());
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankSkf() {
        return bankSkf;
    }

    public void setBankSkf(String bankSkf) {
        this.bankSkf = bankSkf;
    }

    public String getBankAccountLastFour() {
        return bankAccountLastFour;
    }

    public void setBankAccountLastFour(String bankAccountLastFour) {
        this.bankAccountLastFour = bankAccountLastFour;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
