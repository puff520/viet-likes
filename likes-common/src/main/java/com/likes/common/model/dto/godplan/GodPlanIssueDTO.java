package com.likes.common.model.dto.godplan;

import com.likes.common.mybatis.entity.GodPlanIssue;

import java.util.ArrayList;
import java.util.List;

/**
 * 彩种赛果资讯的DTO
 *
 * @author lzy
 * @create 2018-07-20 18:10
 **/
public class GodPlanIssueDTO extends GodPlanIssue {
    private List<String> playNameList = new ArrayList<>();

    private int valueCount;

    public List<String> getPlayNameList() {
        return playNameList;
    }

    public void setPlayNameList(List<String> playNameList) {
        this.playNameList = playNameList;
    }

    public int getValueCount() {
        return valueCount;
    }

    public void setValueCount(int valueCount) {
        this.valueCount = valueCount;
    }
}
