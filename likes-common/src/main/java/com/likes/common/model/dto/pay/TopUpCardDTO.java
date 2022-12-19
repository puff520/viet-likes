package com.likes.common.model.dto.pay;

/**
 * ClassName:    TopUpCardDTO
 * Package:    com.likes.common.model.dto.pay
 * Description:
 * Datetime:    2020/5/8   14:43
 * Author:   木鱼
 */
public class TopUpCardDTO {

    private Integer status;
    private String account;
    private String number;
    private String memberId;
    private String money;
    private Integer pageNo = 1;
    private Integer pageSize = 10;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
