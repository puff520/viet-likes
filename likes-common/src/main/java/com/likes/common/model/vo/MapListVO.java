package com.likes.common.model.vo;

import java.util.List;

/**
 * 资讯返回类
 *
 * @author lzy
 * @create 2018-07-27 16:07
 **/
public class MapListVO {
    private String type;
    private List value;

    public MapListVO() {
    }

    public MapListVO(String type, List value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List getValue() {
        return value;
    }

    public void setValue(List value) {
        this.value = value;
    }
}

