package com.likes.common.model.dto.sys;

import com.likes.common.mybatis.entity.SysAppInfo;

public class SysAppInfoDO extends SysAppInfo {

    private String createusername;
    private String modifyusername;
    private Integer isupdate;

    /*private String publishedtimestr;


    public String getPublishedtimestr() {
        return publishedtimestr;
    }
    public void setPublishedtimestr(String publishedtimestr) {
        this.publishedtimestr = publishedtimestr;
    }*/
    public Integer getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(Integer isupdate) {
        this.isupdate = isupdate;
    }

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    public String getModifyusername() {
        return modifyusername;
    }

    public void setModifyusername(String modifyusername) {
        this.modifyusername = modifyusername;
    }

}
