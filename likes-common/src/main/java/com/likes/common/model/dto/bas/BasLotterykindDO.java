package com.likes.common.model.dto.bas;

import com.likes.common.mybatis.entity.BasLotterykind;

public class BasLotterykindDO extends BasLotterykind {

    private String kindimgurl;

    public String getKindimgurl() {
        return kindimgurl;
    }

    public void setKindimgurl(String kindimgurl) {
        this.kindimgurl = kindimgurl;
    }

}
