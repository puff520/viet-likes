package com.likes.common.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class AgentEveryInitData implements Serializable {

    private String dat;

    private BigDecimal cz;
    private BigDecimal tx;

    private BigDecimal rgcz;

    private BigDecimal vip;

    private BigDecimal task1;

    private BigDecimal task2;

    private BigDecimal task3;

    private BigDecimal rw;

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public BigDecimal getTx() {
        return tx;
    }

    public void setTx(BigDecimal tx) {
        this.tx = tx;
    }

    public BigDecimal getRgcz() {
        return rgcz;
    }

    public void setRgcz(BigDecimal rgcz) {
        this.rgcz = rgcz;
    }

    public BigDecimal getVip() {
        return vip;
    }

    public void setVip(BigDecimal vip) {
        this.vip = vip;
    }

    public BigDecimal getTask1() {
        return task1;
    }

    public void setTask1(BigDecimal task1) {
        this.task1 = task1;
    }

    public BigDecimal getTask2() {
        return task2;
    }

    public void setTask2(BigDecimal task2) {
        this.task2 = task2;
    }

    public BigDecimal getTask3() {
        return task3;
    }

    public void setTask3(BigDecimal task3) {
        this.task3 = task3;
    }

    public BigDecimal getRw() {
        return rw;
    }

    public void setRw(BigDecimal rw) {
        this.rw = rw;
    }

    public BigDecimal getCz() {
        return cz;
    }

    public void setCz(BigDecimal cz) {
        this.cz = cz;
    }
}
