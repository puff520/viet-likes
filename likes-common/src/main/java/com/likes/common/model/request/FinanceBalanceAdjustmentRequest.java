package com.likes.common.model.request;

import com.likes.common.mybatis.entity.FinanceBalanceAdjustment;

import java.math.BigDecimal;
import java.util.ArrayList;

public class FinanceBalanceAdjustmentRequest extends FinanceBalanceAdjustment {

    private ArrayList<UniqueIdAndAmount> uniqueIdAndAmounts;

    public ArrayList<UniqueIdAndAmount> getUniqueIdAndAmounts() {
        return uniqueIdAndAmounts;
    }

    public void setUniqueIdAndAmounts(ArrayList<UniqueIdAndAmount> uniqueIdAndAmounts) {
        this.uniqueIdAndAmounts = uniqueIdAndAmounts;
    }

    public static class UniqueIdAndAmount{
        private String uniqueId;
        private BigDecimal amount;

        public UniqueIdAndAmount() {
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }
}
