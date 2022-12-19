package com.likes.common.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.likes.common.enums.task.TaskOrderTypeEnum;

import java.util.Date;
import java.util.List;

public class SubmitTaskRequest {


    private String  submitSample;

    /**
     * id
     */
    private Long taskOrderId;


    public String getSubmitSample() {
        return submitSample;
    }

    public void setSubmitSample(String submitSample) {
        this.submitSample = submitSample;
    }

    public Long getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(Long taskOrderId) {
        this.taskOrderId = taskOrderId;
    }
}
