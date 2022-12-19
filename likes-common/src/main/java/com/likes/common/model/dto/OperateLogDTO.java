package com.likes.common.model.dto;

import com.likes.common.model.common.PageBaseInfo;

/**
 * @author lzy
 * @create 2018-06-05 15:51
 **/
public class OperateLogDTO extends PageBaseInfo {

    //搜索条件--开始时间(yyyy-MM-dd HH:mm:ss)
    private String startDate;

    //结束时间
    private String endDate;

    //帐号
    private String account;

    private String keyword;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}

