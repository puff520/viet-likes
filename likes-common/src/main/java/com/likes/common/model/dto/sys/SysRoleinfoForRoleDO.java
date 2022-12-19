package com.likes.common.model.dto.sys;

import com.alibaba.fastjson.JSONArray;
import com.likes.common.mybatis.entity.SysRoleinfo;

public class SysRoleinfoForRoleDO extends SysRoleinfo {
    private Long aid;
    private Long bid;
    private Long cid;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    private Long did;
    private JSONArray functionlist = new JSONArray();

    public JSONArray getFunctionlist() {
        return functionlist;
    }

    public void setFunctionlist(JSONArray functionlist) {
        this.functionlist = functionlist;
    }

}
