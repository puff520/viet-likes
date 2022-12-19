package com.likes.common.model;

import java.util.Date;

/**
 * @author abu
 * @Description  
 * @revise
 * @time     2018年3月15日 下午3:48:20
 * @version 1.0
 * @copyright Copyright @2017, Co., Ltd. All right.
 */
public class ModelQyOneplusddinfo {
   
	/**青羊业务参数id**/
	private Long qyopddinfoid;

	/**业务参数代码**/
	private String busparamcode;

	/**业务参数父代码**/
	private String pbusparamcode;

	/**业务参数名称**/
	private String busparamname;

	/**参数说明**/
	private String remark;

	/**系统参数启用状态0启用9未启用**/
	private Integer status;

	/**排序权重**/
	private Integer sortby;

	private Integer isdelete;
	private Date    createdate;
	private String  createuser;
	private Date    modifydate;
	private String  modifyuser ;
     
	private Integer videocnt ;    //伪列视频数据
	
	private Integer videoplaycnt ;//伪列视频 播放次数

	public Long getQyopddinfoid() {
		return qyopddinfoid;
	}

	public String getBusparamcode() {
		return busparamcode;
	}

	public String getPbusparamcode() {
		return pbusparamcode;
	}

	public String getBusparamname() {
		return busparamname;
	}

	public String getRemark() {
		return remark;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getSortby() {
		return sortby;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public String getCreateuser() {
		return createuser;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public String getModifyuser() {
		return modifyuser;
	}

	public Integer getVideocnt() {
		return videocnt;
	}

	public Integer getVideoplaycnt() {
		return videoplaycnt;
	}

	public void setQyopddinfoid(Long qyopddinfoid) {
		this.qyopddinfoid = qyopddinfoid;
	}

	public void setBusparamcode(String busparamcode) {
		this.busparamcode = busparamcode;
	}

	public void setPbusparamcode(String pbusparamcode) {
		this.pbusparamcode = pbusparamcode;
	}

	public void setBusparamname(String busparamname) {
		this.busparamname = busparamname;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setSortby(Integer sortby) {
		this.sortby = sortby;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}

	public void setVideocnt(Integer videocnt) {
		this.videocnt = videocnt;
	}

	public void setVideoplaycnt(Integer videoplaycnt) {
		this.videoplaycnt = videoplaycnt;
	}
	
}
