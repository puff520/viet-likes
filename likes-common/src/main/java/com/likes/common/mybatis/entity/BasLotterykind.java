package com.likes.common.mybatis.entity;

import java.util.Date;

public class BasLotterykind {
    private Long lotkindid;

    private Long parlotkindid;

    private String lotname;

    private String lotcode;

    private String kindimg;

    private Integer status;
    
    private Integer stoptime;

    private Integer sortby;

    private Integer isdelete;

    private String createuser;

    private Date createdate;

    private String modifyuser;

    private Date modifydate;

    
    
    public Integer getStoptime() {
		return stoptime;
	}

	public void setStoptime(Integer stoptime) {
		this.stoptime = stoptime;
	}

	public Long getLotkindid() {
        return lotkindid;
    }

    public void setLotkindid(Long lotkindid) {
        this.lotkindid = lotkindid;
    }

    public Long getParlotkindid() {
        return parlotkindid;
    }

    public void setParlotkindid(Long parlotkindid) {
        this.parlotkindid = parlotkindid;
    }

    public String getLotname() {
        return lotname;
    }

    public void setLotname(String lotname) {
        this.lotname = lotname;
    }

    public String getLotcode() {
        return lotcode;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
    }

    public String getKindimg() {
        return kindimg;
    }

    public void setKindimg(String kindimg) {
        this.kindimg = kindimg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortby() {
        return sortby;
    }

    public void setSortby(Integer sortby) {
        this.sortby = sortby;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}