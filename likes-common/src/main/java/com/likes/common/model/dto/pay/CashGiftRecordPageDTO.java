package com.likes.common.model.dto.pay;

/**
 * ClassName:    CashGiftRecordPageDTO
 * Package:    com.likes.common.model.dto.pay
 * Description:
 * Datetime:    2020/5/8   14:34
 * Author:   木鱼
 */
public class CashGiftRecordPageDTO {

    private String type;
    private String endTime;
    private String startTime;
    private String account;
    private String remark;
    private String memberId;
    private Integer pageNo = 1;
    private Integer pageSize = 10;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
