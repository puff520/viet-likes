package com.likes.common.model;

import java.util.Date;

public class AgentUserQuery {

    /**
     * 账号
     */
    private String acclogin;

    /**
     * 代充暱称
     */
    private String nickname;

    /**
     * 起始时间
     */
    private Date onlinedates;

    /**
     * 结束时间
     */
    private Date onlinedatee;

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

    public Date getOnlinedates() {
        return onlinedates;
    }

    public void setOnlinedates(Date onlinedates) {
        this.onlinedates = onlinedates;
    }

    public Date getOnlinedatee() {
        return onlinedatee;
    }

    public void setOnlinedatee(Date onlinedatee) {
        this.onlinedatee = onlinedatee;
    }
}
