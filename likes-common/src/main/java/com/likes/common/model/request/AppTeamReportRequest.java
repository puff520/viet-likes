package com.likes.common.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.util.List;

@ApiModel
public class AppTeamReportRequest extends BaseRequest {
    @ApiModelProperty(value = "用户Uid")
    private List<String> accNoList;
    @ApiModelProperty(value = "开始时间")
    private String beginTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    private Integer level;



    public List<String> getAccNoList() {
        return accNoList;
    }

    public void setAccNoList(List<String> accNoList) {
        this.accNoList = accNoList;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
