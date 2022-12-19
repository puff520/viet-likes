package com.likes.common.model.dto.circle;

import java.math.BigDecimal;

public class OfflinePaySubmitDTO {
    private Integer accountId;  //账号id
    private BigDecimal amount;  //充值金额
    private Integer fuyan;  //附言
    private String transferSign;//转账标识

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getFuyan() {
        return fuyan;
    }

    public void setFuyan(Integer fuyan) {
        this.fuyan = fuyan;
    }

	public String getTransferSign() {
		return transferSign;
	}
	public void setTransferSign(String transferSign) {
		this.transferSign = transferSign;
	}

    @Override
    public String toString() {
        return "OfflinePaySubmitDTO{" +
                "accountId=" + accountId +
                ", amount=" + amount +
                ", fuyan=" + fuyan +
                ", transferSign='" + transferSign + '\'' +
                '}';
    }
}
