package com.likes.common.model.vo.pay.level;

public class PayLevelItemVo {
    private Long id;
    private String levelName;
    private Integer minDepositCount;
    private Integer minRechargeAmount;
    private Integer maxRechargeAmount;
    private String payOnlines;
    private String payBankAccounts;
    private String apiAddress;
    private String quickMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getMinDepositCount() {
        return minDepositCount;
    }

    public void setMinDepositCount(Integer minDepositCount) {
        this.minDepositCount = minDepositCount;
    }

    public Integer getMinRechargeAmount() {
        return minRechargeAmount;
    }

    public void setMinRechargeAmount(Integer minRechargeAmount) {
        this.minRechargeAmount = minRechargeAmount;
    }

    public Integer getMaxRechargeAmount() {
        return maxRechargeAmount;
    }

    public void setMaxRechargeAmount(Integer maxRechargeAmount) {
        this.maxRechargeAmount = maxRechargeAmount;
    }

    public String getPayOnlines() {
        return payOnlines;
    }

    public void setPayOnlines(String payOnlines) {
        this.payOnlines = payOnlines;
    }

    public String getPayBankAccounts() {
        return payBankAccounts;
    }

    public void setPayBankAccounts(String payBankAccounts) {
        this.payBankAccounts = payBankAccounts;
    }

    public String getApiAddress() {
        return apiAddress;
    }

    public void setApiAddress(String apiAddress) {
        this.apiAddress = apiAddress;
    }

    public String getQuickMoney() {
        return quickMoney;
    }

    public void setQuickMoney(String quickMoney) {
        this.quickMoney = quickMoney;
    }
}
