package com.likes.common.model.request;

import com.likes.common.mybatis.entity.SysAppInfo;

public class SysAppInfoReq extends SysAppInfo {

    //是否马上发布 1 是  2 否
    public Integer isimmediately;

    public String publishedtimestr;

    public String getPublishedtimestr() {
        return publishedtimestr;
    }

    public void setPublishedtimestr(String publishedtimestr) {
        this.publishedtimestr = publishedtimestr;
    }

    public Integer getIsimmediately() {
        return isimmediately;
    }

    public void setIsimmediately(Integer isimmediately) {
        this.isimmediately = isimmediately;
    }

}
