package com.likes.common.mybatis.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MemSubInfo {

    @Id
    private String accno;

    private Date createTime;

    private Date updateTime;

    private List<String> levelOneAgents;

    private List<String> levelTwoAgents;

    private List<String> levelThreeAgents;

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
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

    public List<String> getLevelOneAgents() {
        if(levelOneAgents == null){
            return  new LinkedList<>();
        }
        return levelOneAgents;
    }

    public void setLevelOneAgents(List<String> levelOneAgents) {
        if(levelOneAgents == null){
            this.levelOneAgents = new LinkedList<>();
        }
        this.levelOneAgents = levelOneAgents;
    }

    public List<String> getLevelTwoAgents() {
        if(levelTwoAgents == null){
            return  new LinkedList<>();
        }
        return levelTwoAgents;
    }

    public void setLevelTwoAgents(List<String> levelTwoAgents) {
        if(levelTwoAgents == null){
            this.levelTwoAgents = new LinkedList<>();
        }
        this.levelTwoAgents = levelTwoAgents;
    }

    public List<String> getLevelThreeAgents() {
        if(levelThreeAgents == null){
            return  new LinkedList<>();
        }
        return levelThreeAgents;
    }

    public void setLevelThreeAgents(List<String> levelThreeAgents) {
        if(levelThreeAgents == null){
            this.levelThreeAgents = new LinkedList<>();
        }
        this.levelThreeAgents = levelThreeAgents;
    }
}
