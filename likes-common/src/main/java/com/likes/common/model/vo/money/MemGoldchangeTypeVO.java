package com.likes.common.model.vo.money;

public class MemGoldchangeTypeVO {

    /**
     * 类型编号
     */
    private String type;

    /**
     * 帐变类型的名称
     */
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType(Integer type) {
        this.type = String.valueOf(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
