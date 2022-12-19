package com.likes.common.model.dto.member;

import com.likes.common.mybatis.entity.MemSignin;

public class MemSigninDO extends MemSignin {
    private String signDate;

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

}
