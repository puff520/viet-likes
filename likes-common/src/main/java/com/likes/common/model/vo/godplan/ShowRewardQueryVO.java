package com.likes.common.model.vo.godplan;

import com.likes.common.model.common.PageBaseInfo;

import java.util.List;

/**
 * 大神计划打赏列表查询参数
 */
public class ShowRewardQueryVO extends PageBaseInfo {
    //大神名称
    private String godName;
    //账号
    private String account;
    private Integer id;
    //开始时间
    private String createTimeBegin;
    //结束时间
    private String createTimeEnd;
    //用户名称
    private String userName;
    //彩种ID
    private Integer categoryId;

    public List<Integer> getLotteryIdList() {
        return lotteryIdList;
    }

    public void setLotteryIdList(List<Integer> lotteryIdList) {
        this.lotteryIdList = lotteryIdList;
    }

    private List<Integer> lotteryIdList;

    public ShowRewardQueryVO() {
    }

    public ShowRewardQueryVO(String godName, String account, Integer id, String createTimeBegin, String createTimeEnd, String userName, Integer categoryId) {
        this.godName = godName;
        this.account = account;
        this.id = id;
        this.createTimeBegin = createTimeBegin;
        this.createTimeEnd = createTimeEnd;
        this.userName = userName;
        this.categoryId = categoryId;
    }

    public String getGodName() {
        return godName;
    }

    public ShowRewardQueryVO setGodName(String godName) {
        this.godName = godName;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public ShowRewardQueryVO setAccount(String account) {
        this.account = account;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ShowRewardQueryVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCreateTimeBegin() {
        return createTimeBegin;
    }

    public ShowRewardQueryVO setCreateTimeBegin(String createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
        return this;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public ShowRewardQueryVO setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ShowRewardQueryVO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public ShowRewardQueryVO setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }
}
