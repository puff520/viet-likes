package com.likes.common.model;

import java.math.BigDecimal;

public class SubAndTaskDto {

    private  String email;

    private  String accno;
    private  String refaccno;

    private  Integer taskNum;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getRefaccno() {
        return refaccno;
    }

    public void setRefaccno(String refaccno) {
        this.refaccno = refaccno;
    }
}
