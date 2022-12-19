package com.likes.common.model.dto.sys;

import com.likes.common.mybatis.entity.SysTags;

public class SysTagsDO extends SysTags {

    private Integer articlenum;

    private String bdusername;

    public Integer getArticlenum() {
        return articlenum;
    }

    public void setArticlenum(Integer articlenum) {
        this.articlenum = articlenum;
    }

    public String getBdusername() {
        return bdusername;
    }

    public void setBdusername(String bdusername) {
        this.bdusername = bdusername;
    }

}
