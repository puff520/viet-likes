package com.likes.common.model.dto.godplan;

import com.likes.common.mybatis.entity.GodPlan;
import com.likes.common.mybatis.entity.GodPlanIssue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 大神计划接口参数
 *
 * @author peter
 * @create 2020-03-16 18:10
 **/
public class GodPlanDTO extends GodPlan {
    private Integer settingId;
    private Integer betCount;
    private BigDecimal singleMoney;
    private String serisIssue;
    private List<GodPlanIssue> godPlanIssues = new ArrayList<GodPlanIssue>();
    private Integer issueCount;
    private Integer newWinCount;
    private String betNumber;

    private String showBetNumber;

    public String getShowBetNumber() {
        return showBetNumber;
    }

    public void setShowBetNumber(String showBetNumber) {
        this.showBetNumber = showBetNumber;
    }

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public BigDecimal getSingleMoney() {
        return singleMoney;
    }

    public void setSingleMoney(BigDecimal singleMoney) {
        this.singleMoney = singleMoney;
    }

    public String getSerisIssue() {
        return serisIssue;
    }

    public void setSerisIssue(String serisIssue) {
        this.serisIssue = serisIssue;
    }

    private List<GodPlanIssueDTO> godPlanIssueDtos = new ArrayList<GodPlanIssueDTO>();

    public Integer getNewWinCount() {
        return newWinCount;
    }

    public void setNewWinCount(Integer newWinCount) {
        this.newWinCount = newWinCount;
    }

    public Integer getBetCount() {
        return betCount;
    }

    public void setBetCount(Integer betCount) {
        this.betCount = betCount;
    }

    public String getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(String betNumber) {
        this.betNumber = betNumber;
    }

    public List<GodPlanIssueDTO> getGodPlanIssueDtos() {
        return godPlanIssueDtos;
    }

    public void setGodPlanIssueDtos(List<GodPlanIssueDTO> godPlanIssueDtos) {
        this.godPlanIssueDtos = godPlanIssueDtos;
    }

    public Integer getIssueCount() {
        return issueCount;
    }

    public void setIssueCount(Integer issueCount) {
        this.issueCount = issueCount;
    }

    public List<GodPlanIssue> getGodPlanIssues() {
        return godPlanIssues;
    }

    public void setGodPlanIssues(List<GodPlanIssue> godPlanIssues) {
        this.godPlanIssues = godPlanIssues;
    }
}
