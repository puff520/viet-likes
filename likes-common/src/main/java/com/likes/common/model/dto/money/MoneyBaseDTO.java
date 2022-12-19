package com.likes.common.model.dto.money;

import com.likes.common.enums.withdrawal.WithdrawHandleStatusEnum;
import com.likes.common.model.common.PageBaseInfo;
import com.likes.common.util.DateUtils;

import java.math.BigDecimal;

public class MoneyBaseDTO extends PageBaseInfo {
    protected String orderNo;
    protected Integer vipLevel;
    protected String startTime;
    protected String endTime;
    protected BigDecimal minAmount;
    protected BigDecimal maxAmount;

    protected String account;
    protected String searchType;
    protected String searchValue;
    protected String quickTime;

    public static final String WITHDRAW_BUS_RETRIEVE = "RETRIEVE";
    public static final String WITHDRAW_BUS_RETRIEVE_ME = "RETRIEVE_ME";
    public static final String WITHDRAW_BUS_COMPLETE = "COMPLETE";

    /**
     * 矫正查询时间
     */
    public void correctSearchTime() {
        if (null == this.quickTime || "".equals(this.quickTime)) {
            boolean today = false;
            if (null == this.startTime || "".equals(this.startTime)) {
                this.startTime = DateUtils.dayBeginStr();
                today = true;
            }
            if (null == this.endTime || "".equals(this.endTime)) {
                this.endTime = DateUtils.dayEndStr();
                today = true;
            }
            if (today) {
                //和页面端的快捷时间id对应
                this.quickTime = "today-time";
            }
        }
    }

    /**
     * 提现：根据场景设置 status 的值
     *
     * @param type 场景类型
     * @return
     */
    public String fillStatusByType(String type) {
        return fillStatusByType(type, null);
    }

    public String fillStatusByType(String type, Boolean showPickuped) {
        if (null == type || "".equals(type.trim())) {
            return null;
        }
        String status = null;
        switch (type) {
            case WITHDRAW_BUS_RETRIEVE:
                status = WithdrawHandleStatusEnum.retrieveStatus();
                break;
            case WITHDRAW_BUS_RETRIEVE_ME:
                status = WithdrawHandleStatusEnum.retrieveMeStatus();
                break;
            case WITHDRAW_BUS_COMPLETE:
                status = WithdrawHandleStatusEnum.completeStatus(showPickuped);
                break;
        }
        return status;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getQuickTime() {
        return quickTime;
    }

    public void setQuickTime(String quickTime) {
        this.quickTime = quickTime;
    }
}
