package com.likes.common.model;

import java.io.Serializable;
import java.util.Date;

public class Busbody implements Serializable {

    private static final long serialVersionUID = 1L;

    private String topic;

    private String command;

    private String message;

    private Date sendtime;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendtime() {
        if (sendtime == null) {
            return new Date();
        }
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

}
