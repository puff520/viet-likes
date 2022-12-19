package com.likes.common.model.dto.circle;

import com.likes.common.model.common.PageBaseInfo;

public class FollowOrderWebDTO extends PageBaseInfo {
    private String orderByType; //排序方式：ASC DESC
    private String orderByColumn;   //排序字段

    public String getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(String orderByType) {
        this.orderByType = orderByType;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }
}
