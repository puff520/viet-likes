package com.likes.common.model.vo.chat;


import com.likes.common.mybatis.entity.ChatRedPack;

public class ChatRedPackVo extends ChatRedPack {

    private static final long serialVersionUID = 1L;
    private String appMemberAccount;

    public String getAppMemberAccount() {
        return appMemberAccount;
    }

    public void setAppMemberAccount(String appMemberAccount) {
        this.appMemberAccount = appMemberAccount;
    }
}
