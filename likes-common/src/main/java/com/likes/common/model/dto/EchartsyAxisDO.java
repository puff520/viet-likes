package com.likes.common.model.dto;

import java.util.List;

public class EchartsyAxisDO {

    private String name;
    //private String type;
    //private Integer barGap;
    private List<Integer> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

}
