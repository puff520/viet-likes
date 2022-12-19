package com.likes.common.model.dto.start;

import java.math.BigDecimal;
import java.util.Date;

public class CpActivityInfoDTO {
	
	private Integer id;
	  
    private String actOutBanner;

    private String actInBanner;
   
    private String actStartTime;
   
    private String actEndTime;

    private String actTitle;

    private String actGuide;
   
    private BigDecimal actMinAmount;
   
    private BigDecimal actMaxAmount;

    private BigDecimal actMinShamAmount;
   
    private BigDecimal actMaxShamAmount;
    
    private Integer actType;
    
    private Integer actStatus;
    
    private Integer actIntoPage;
   
    private Integer isDeleted;
   
    private Integer isPopup;
    
    private Date createTime;

    private Date updateTime;
    
    private BigDecimal actReceiveLimitBetAmount;
    
    private BigDecimal actReceiveLimitAmount;

    private Integer timeType;

    private String startTime;

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    private String stopTime;

    private String startDate;

    private String stopDate;

	public Integer getIsTodayChargeHundred() {
		return isTodayChargeHundred;
	}

	public void setIsTodayChargeHundred(Integer isTodayChargeHundred) {
		this.isTodayChargeHundred = isTodayChargeHundred;
	}

	public Integer isTodayChargeHundred;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActOutBanner() {
		return actOutBanner;
	}

	public void setActOutBanner(String actOutBanner) {
		this.actOutBanner = actOutBanner;
	}

	public String getActInBanner() {
		return actInBanner;
	}

	public void setActInBanner(String actInBanner) {
		this.actInBanner = actInBanner;
	}

	public String getActStartTime() {
		return actStartTime;
	}

	public void setActStartTime(String actStartTime) {
		this.actStartTime = actStartTime;
	}

	public String getActEndTime() {
		return actEndTime;
	}

	public void setActEndTime(String actEndTime) {
		this.actEndTime = actEndTime;
	}

	public String getActTitle() {
		return actTitle;
	}

	public void setActTitle(String actTitle) {
		this.actTitle = actTitle;
	}

	public String getActGuide() {
		return actGuide;
	}

	public void setActGuide(String actGuide) {
		this.actGuide = actGuide;
	}

	public BigDecimal getActMinAmount() {
		return actMinAmount;
	}

	public void setActMinAmount(BigDecimal actMinAmount) {
		this.actMinAmount = actMinAmount;
	}

	public BigDecimal getActMaxAmount() {
		return actMaxAmount;
	}

	public void setActMaxAmount(BigDecimal actMaxAmount) {
		this.actMaxAmount = actMaxAmount;
	}

	public BigDecimal getActMinShamAmount() {
		return actMinShamAmount;
	}

	public void setActMinShamAmount(BigDecimal actMinShamAmount) {
		this.actMinShamAmount = actMinShamAmount;
	}

	public BigDecimal getActMaxShamAmount() {
		return actMaxShamAmount;
	}

	public void setActMaxShamAmount(BigDecimal actMaxShamAmount) {
		this.actMaxShamAmount = actMaxShamAmount;
	}

	public Integer getActType() {
		return actType;
	}

	public void setActType(Integer actType) {
		this.actType = actType;
	}

	public Integer getActStatus() {
		return actStatus;
	}

	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}

	public Integer getActIntoPage() {
		return actIntoPage;
	}

	public void setActIntoPage(Integer actIntoPage) {
		this.actIntoPage = actIntoPage;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getIsPopup() {
		return isPopup;
	}

	public void setIsPopup(Integer isPopup) {
		this.isPopup = isPopup;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getActReceiveLimitBetAmount() {
		return actReceiveLimitBetAmount;
	}

	public void setActReceiveLimitBetAmount(BigDecimal actReceiveLimitBetAmount) {
		this.actReceiveLimitBetAmount = actReceiveLimitBetAmount;
	}

	public BigDecimal getActReceiveLimitAmount() {
		return actReceiveLimitAmount;
	}

	public void setActReceiveLimitAmount(BigDecimal actReceiveLimitAmount) {
		this.actReceiveLimitAmount = actReceiveLimitAmount;
	}
}
