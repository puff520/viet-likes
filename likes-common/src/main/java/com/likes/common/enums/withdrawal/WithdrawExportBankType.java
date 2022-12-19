package com.likes.common.enums.withdrawal;

/**
 * 提现导出场景银行类别
 */
public enum WithdrawExportBankType {
    MIGSHENG(1, "民生银行"),
	TIGER(2, "虎鲸");

    private Integer value;
    private String desc;

    WithdrawExportBankType(Integer value, String desc) {
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
