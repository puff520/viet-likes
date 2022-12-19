package com.likes.common.model.dto;

import javax.persistence.Column;

public class InfSysnoticeDO {


    private String enNotice;

    private String spNotice;

    private String abNotice;

    private String fnNotice;
    private String title;

    private String messageType;

    private int sortBy;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnNotice() {
        return enNotice;
    }

    public void setEnNotice(String enNotice) {
        this.enNotice = enNotice;
    }

    public String getSpNotice() {
        return spNotice;
    }

    public void setSpNotice(String spNotice) {
        this.spNotice = spNotice;
    }

    public String getAbNotice() {
        return abNotice;
    }

    public void setAbNotice(String abNotice) {
        this.abNotice = abNotice;
    }

    public String getFnNotice() {
        return fnNotice;
    }

    public void setFnNotice(String fnNotice) {
        this.fnNotice = fnNotice;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }
}
