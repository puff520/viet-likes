package com.likes.common.model.common;

/**
 * 代充人订单类型枚举
 *
 * @author 瑞夫
 * @version 1.0
 * @date 2020/5/7
 */
public enum RepayOrderTypeEnum {

    MANUAL_STORE(16, "人工存入"),
    MANUAL_ADVANCE(17, "人工提出"),
    RECHARGE(14, "账户充值"),
    REPAY(15, "账户代充");

    private Integer ordertype;

    private String ordertypedec;

    RepayOrderTypeEnum(Integer ordertype, String ordertypedec) {
        this.ordertype = ordertype;
        this.ordertypedec = ordertypedec;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public String getOrdertypedec() {
        return ordertypedec;
    }

    public void setOrdertypedec(String ordertypedec) {
        this.ordertypedec = ordertypedec;
    }

    /**
     * 根据code获取去value
     *
     * @param code
     * @return
     */
    public static String getValueByCode(Integer code) {
        for (RepayOrderTypeEnum orderTypeEnum : RepayOrderTypeEnum.values()) {
            if (orderTypeEnum.getOrdertype().equals(code)) {
                return orderTypeEnum.getOrdertypedec();
            }
        }
        return null;
    }
}
