package com.likes.common.model.vo.pay;

public class PayWayVo {

    /** 充值项id */
    private Integer wayId;

    /** 最小金额 */
    private Integer minMoney;

    /** 最大金额：0表示不限制 */
    private Integer maxMoney;

    /** 充值方式标识 */
    private String wayTag;

    /** 充值项名称 */
    private String wayName;

    /** 充值项图标路径 */
    private String wayPicture;

    /**
     * 收款方式（1：银行卡转银行卡；2：微信转银行卡；3：支付宝转银行卡）银行账户用
     */
    private Integer receiveType;

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public Integer getWayId() {
        return wayId;
    }

    public Integer getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(Integer minMoney) {
        this.minMoney = minMoney;
    }

    public void setWayId(Integer wayId) {
        this.wayId = wayId;
    }

    public Integer getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(Integer maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getWayTag() {
        return wayTag;
    }

    public void setWayTag(String wayTag) {
        this.wayTag = wayTag;
    }

    public String getWayPicture() {
        return wayPicture;
    }

    public void setWayPicture(String wayPicture) {
        this.wayPicture = wayPicture;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }
}
