package com.likes.common.model.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class OrderAuditDO {

    private Long orderid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;
    // 充值金额
    private BigDecimal realamt;
    // 优惠
    private BigDecimal preferential;
    // 打码量
    private BigDecimal damaliang;
    // 常态打码量
    private BigDecimal changdamaliang;
    // 放放宽
    private BigDecimal auditfree;
    //状态 0 有 9无
    private Integer status;
    // 扣除金额
    private BigDecimal kouchu;

    public Long getOrderid() {
        return orderid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public BigDecimal getRealamt() {
        return realamt;
    }

    public void setRealamt(BigDecimal realamt) {
        this.realamt = realamt;
    }

    public BigDecimal getPreferential() {
        return preferential;
    }

    public void setPreferential(BigDecimal preferential) {
        this.preferential = preferential;
    }

    public BigDecimal getDamaliang() {
        return damaliang;
    }

    public void setDamaliang(BigDecimal damaliang) {
        this.damaliang = damaliang;
    }

    public BigDecimal getChangdamaliang() {
        return changdamaliang;
    }

    public void setChangdamaliang(BigDecimal changdamaliang) {
        this.changdamaliang = changdamaliang;
    }

    public BigDecimal getAuditfree() {
        return auditfree;
    }

    public void setAuditfree(BigDecimal auditfree) {
        this.auditfree = auditfree;
    }

    public BigDecimal getKouchu() {
        return kouchu;
    }

    public void setKouchu(BigDecimal kouchu) {
        this.kouchu = kouchu;
    }

	/*public BigDecimal getHaixudamaliang() {
		return haixudamaliang;
	}
	
	public void setHaixudamaliang(BigDecimal haixudamaliang) {
		this.haixudamaliang = haixudamaliang;
	}*/

}
