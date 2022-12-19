package com.likes.common.model.dto.report;

import org.springframework.stereotype.Component;

@Component
public class DepositStatisticsDO {
    private String money;//money
    private String renci;//人次
    private String status;//支付状态
    private String AllRenci;//总人数

    public String getAllRenci() {
        return AllRenci;
    }

    public void setAllRenci(String allRenci) {
        AllRenci = allRenci;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRenci() {
        return renci;
    }

    public void setRenci(String renci) {
        this.renci = renci;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DepositStatisticsDO{" +
                "money='" + money + '\'' +
                ", renci='" + renci + '\'' +
                ", status='" + status + '\'' +
                ", AllRenci='" + AllRenci + '\'' +
                '}';
    }
}
