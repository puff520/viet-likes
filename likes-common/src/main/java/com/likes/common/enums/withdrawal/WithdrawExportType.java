package com.likes.common.enums.withdrawal;

/**
 * 提现导出操作类型
 */
public enum WithdrawExportType {
    NON_COMPLETE(1, "未完成"),
    COMPLETE(2, "已完成");

    private Integer value;
    private String desc;

    WithdrawExportType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
