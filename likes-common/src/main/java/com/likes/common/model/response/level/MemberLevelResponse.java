package com.likes.common.model.response.level;


import java.util.Map;

public class MemberLevelResponse {

    /**
     * 等级
     */
    private Long levelId;

    /**
     * 等级
     */
    private String level;

    /**
     * 等级编号
     */
    private  Integer levelSeq;

    /**
     * 每天任务数
     */
    private  Integer taskNum;


    private String effectiveDate;





    /**
     * 会员剩余时间戳
     */
    private Map<String,Long> remainingTime;




    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getLevelSeq() {
        return levelSeq;
    }

    public void setLevelSeq(Integer levelSeq) {
        this.levelSeq = levelSeq;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }



    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Map<String, Long> getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(Map<String, Long> remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
