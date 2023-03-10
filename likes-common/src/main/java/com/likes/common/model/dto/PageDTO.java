package com.likes.common.model.dto;


import com.likes.common.model.common.PageBaseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzy
 * @create 2018-08-09 17:24
 **/
public class PageDTO extends PageBaseInfo {

    private List<Integer> types = new ArrayList<>(); // 类型集合,必须先初始化
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String startDate; // 开始时间

    private String endDate; // 结束时间

    public List<Integer> getTypes() {
        return types;
    }

    public void setTypes(List<Integer> types) {
        this.types = types;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
