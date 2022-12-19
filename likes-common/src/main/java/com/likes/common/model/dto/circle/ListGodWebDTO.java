package com.likes.common.model.dto.circle;

import com.likes.common.model.common.PageBaseInfo;

public class ListGodWebDTO extends PageBaseInfo {
    private Integer type;   //类型
    private String isMyFocus;  //是否我的关注

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIsMyFocus() {
        return isMyFocus;
    }

    public void setIsMyFocus(String isMyFocus) {
        this.isMyFocus = isMyFocus;
    }
}
