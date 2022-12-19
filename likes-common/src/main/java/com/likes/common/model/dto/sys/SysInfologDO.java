package com.likes.common.model.dto.sys;

import com.likes.common.mybatis.entity.SysInfolog;

public class SysInfologDO extends SysInfolog {

    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
