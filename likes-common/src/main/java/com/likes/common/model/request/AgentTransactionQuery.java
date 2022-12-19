package com.likes.common.model.request;

public class AgentTransactionQuery {

    /**
     * 代充账号
     */
    private String acclogin;

    /**
     * 代充暱称
     */
    private String nickname;

    /**
     * 状态
     */
    private String orderstatus;

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }
}
