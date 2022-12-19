package com.likes.common.model.dto.sys;

import com.likes.common.mybatis.entity.SysWhitelist;

public class SysWhitelistDO extends SysWhitelist {

    private String bdusername;

    public String getBdusername() {
        return bdusername;
    }

    public void setBdusername(String bdusername) {
        this.bdusername = bdusername;
    }

}
