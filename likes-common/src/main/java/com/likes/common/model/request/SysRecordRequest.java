package com.likes.common.model.request;

public class SysRecordRequest extends BaseRequest {
    private Integer rtype;
    private String keyword;

    public Integer getRtype() {
        return rtype;
    }

    public void setRtype(Integer rtype) {
        this.rtype = rtype;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
