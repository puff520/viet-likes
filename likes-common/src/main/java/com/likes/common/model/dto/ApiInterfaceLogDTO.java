package com.likes.common.model.dto;

import com.likes.common.model.common.PageBaseInfo;

/**
 * @author lzy
 * @create 2018-06-06 14:12
 **/
public class ApiInterfaceLogDTO extends PageBaseInfo {

    //搜索条件--开始时间(yyyy-MM-dd HH:mm:ss)
    private String startDate;

    //结束时间
    private String endDate;

    private String memberId;

    private String account;

    private String name;

    private String date;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
