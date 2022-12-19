package com.likes.common.model.dto.member;

public class MemGoldChangeTypeDO {
    private String label;
    private String value;

    public MemGoldChangeTypeDO(){}

    public MemGoldChangeTypeDO(String _value,String _label){
        this.label = _label;
        this.value = _value;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
