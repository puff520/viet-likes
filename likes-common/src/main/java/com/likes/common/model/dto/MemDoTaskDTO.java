package com.likes.common.model.dto;

import java.util.Date;

public class MemDoTaskDTO {
    private Integer sumcount;
    private Integer  conduct;
    private Integer examine;
    private Integer finish;
    private Integer failed;
    private Integer malice;
    private Integer abandon;

    private Integer newMemDoTaskNum;
    private Integer doTaskAllNum;

    public Integer getNewMemDoTaskNum() {
        return newMemDoTaskNum;
    }

    public void setNewMemDoTaskNum(Integer newMemDoTaskNum) {
        this.newMemDoTaskNum = newMemDoTaskNum;
    }

    public Integer getDoTaskAllNum() {
        return doTaskAllNum;
    }

    public void setDoTaskAllNum(Integer doTaskAllNum) {
        this.doTaskAllNum = doTaskAllNum;
    }


    public Integer getSumcount() {
        return sumcount;
    }

    public void setSumcount(Integer sumcount) {
        this.sumcount = sumcount;
    }

    public Integer getConduct() {
        return conduct;
    }

    public void setConduct(Integer conduct) {
        this.conduct = conduct;
    }

    public Integer getExamine() {
        return examine;
    }

    public void setExamine(Integer examine) {
        this.examine = examine;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public Integer getFailed() {
        return failed;
    }

    public void setFailed(Integer failed) {
        this.failed = failed;
    }

    public Integer getMalice() {
        return malice;
    }

    public void setMalice(Integer malice) {
        this.malice = malice;
    }

    public Integer getAbandon() {
        return abandon;
    }

    public void setAbandon(Integer abandon) {
        this.abandon = abandon;
    }
}
