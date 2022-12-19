package com.likes.common.model.dto.circle;

import com.likes.common.model.common.PageBaseInfo;

public class GodFansOrFocusListDTO extends PageBaseInfo {
    private Integer godId;  //大神id
    private Integer type;   //1关注列表2粉丝列表

    public Integer getGodId() {
        return godId;
    }

    public void setGodId(Integer godId) {
        this.godId = godId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
