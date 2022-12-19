package com.likes.common.model.dto.sys;

import com.likes.common.mybatis.entity.SysFeedback;

import java.util.ArrayList;
import java.util.List;

public class SysFeedbackDO extends SysFeedback {
    private String nickname;
    private String mobileno;
    private List<String> feedimgslist = new ArrayList<String>();

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public List<String> getFeedimgslist() {
        return feedimgslist;
    }

    public void setFeedimgslist(List<String> feedimgslist) {
        this.feedimgslist = feedimgslist;
    }
}
