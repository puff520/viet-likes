package com.likes.common.model.request;

import com.likes.common.model.common.PageBaseInfo;

public class TrialAccountRequest extends PageBaseInfo {

    private String memlevel;
    private String accstatus;
    private String uniqueId;
    private String nickname;
    private String acclogin;

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }


    public String getAccstatus() {
        return accstatus;
    }

    public void setAccstatus(String accstatus) {
        this.accstatus = accstatus;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
