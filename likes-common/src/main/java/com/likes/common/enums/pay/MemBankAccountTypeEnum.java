package com.likes.common.enums.pay;

/**
 * 支付项操作类型枚举
 */
public enum MemBankAccountTypeEnum {

    AliPay(1, "支付宝", true),
    WechatPay(2, "微信", true),
    NETBANK(3, "银联", true);

    private Integer value;

    private String name;

    private Boolean enable;


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    MemBankAccountTypeEnum(Integer value, String name, Boolean enable) {
        this.value = value;
        this.name = name;
        this.enable = enable;
    }

    public static boolean verify(Integer type) {
        MemBankAccountTypeEnum e = valueOf(type);
        return e != null && e.enable;
    }

    public static MemBankAccountTypeEnum valueOf(Integer type) {
        if (null == type) {
            return null;
        }
        for (MemBankAccountTypeEnum memBankAccountTypeEnum : MemBankAccountTypeEnum.values()) {
            if (memBankAccountTypeEnum.value.equals(type)) {
                return memBankAccountTypeEnum;
            }
        }
        return null;
    }
}
