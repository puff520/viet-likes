package com.likes.common.model.dto.sys;

public class SysFunctionDO {
    private Long sfunid;
    private Long parsfunid;
    private String sfuntype;
    private String name;
    private String url;

    public Long getSfunid() {
        return sfunid;
    }

    public void setSfunid(Long sfunid) {
        this.sfunid = sfunid;
    }

    public Long getParsfunid() {
        return parsfunid;
    }

    public void setParsfunid(Long parsfunid) {
        this.parsfunid = parsfunid;
    }

    public String getSfuntype() {
        return sfuntype;
    }

    public void setSfuntype(String sfuntype) {
        this.sfuntype = sfuntype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
