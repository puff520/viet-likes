package com.likes.common.model.request;

import com.likes.common.mybatis.entity.FinanceDamaAdjustment;

import java.math.BigDecimal;
import java.util.ArrayList;

public class FinanceDamaAdjustmentRequest extends FinanceDamaAdjustment {

    private ArrayList<UniqueIdAndDamaliang> uniqueIdAndDamaliangs;

    public ArrayList<UniqueIdAndDamaliang> getUniqueIdAndDamaliangs() {
        return uniqueIdAndDamaliangs;
    }

    public void setUniqueIdAndDamaliangs(ArrayList<UniqueIdAndDamaliang> uniqueIdAndDamaliangs) {
        this.uniqueIdAndDamaliangs = uniqueIdAndDamaliangs;
    }

    public static class UniqueIdAndDamaliang {
        private String uniqueId;
        private BigDecimal damaliang;

        public UniqueIdAndDamaliang() {
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public BigDecimal getDamaliang() {
            return damaliang;
        }

        public void setDamaliang(BigDecimal damaliang) {
            this.damaliang = damaliang;
        }
    }
}
