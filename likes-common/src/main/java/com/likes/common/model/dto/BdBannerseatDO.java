package com.likes.common.model.dto;

import com.likes.common.mybatis.entity.BdBannerseat;

public class BdBannerseatDO extends BdBannerseat {
    private String bdusername;

    public String getBdusername() {
        return bdusername;
    }

    public void setBdusername(String bdusername) {
        this.bdusername = bdusername;
    }
}
