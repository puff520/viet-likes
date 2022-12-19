package com.likes.common.model.response;

import java.util.Objects;

public class DepositsReports {
    private String DepositsType;//入款类型
    private String NumPeople;//总人数
    private String NumSuccess;//成工次数
    private String NumFail;//失败次数
    private String SuccessRatio;//成功率
    private String SumDeposits;//总入款金额

    public String getDepositsType() {
        return DepositsType;
    }

    public void setDepositsType(String depositsType) {
        DepositsType = depositsType;
    }

    public String getNumPeople() {
        return NumPeople;
    }

    public void setNumPeople(String numPeople) {
        NumPeople = numPeople;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositsReports that = (DepositsReports) o;
        return DepositsType.equals(that.DepositsType) &&
                NumPeople.equals(that.NumPeople) &&
                NumSuccess.equals(that.NumSuccess) &&
                NumFail.equals(that.NumFail) &&
                SuccessRatio.equals(that.SuccessRatio) &&
                SumDeposits.equals(that.SumDeposits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DepositsType, NumPeople, NumSuccess, NumFail, SuccessRatio, SumDeposits);
    }

    public String getNumSuccess() {
        return NumSuccess;
    }

    public void setNumSuccess(String numSuccess) {
        NumSuccess = numSuccess;
    }

    public String getNumFail() {
        return NumFail;
    }

    public void setNumFail(String numFail) {
        NumFail = numFail;
    }

    public String getSuccessRatio() {
        return SuccessRatio;
    }

    public void setSuccessRatio(String successRatio) {
        SuccessRatio = successRatio;
    }

    public String getSumDeposits() {
        return SumDeposits;
    }

    public void setSumDeposits(String sumDeposits) {
        SumDeposits = sumDeposits;
    }
}
