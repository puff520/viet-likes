package com.likes.common.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author 阿布
 */
public class ForbidResp {
    private Long forbidid;
    private String accno;
    private String nickname;
    private Integer sex;
    private String headimg;
    private String headimgurl;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private Date expirydates;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private Date expirydatee;
    private String reason;

    public Long getForbidid() {
        return forbidid;
    }

    public void setForbidid(Long forbidid) {
        this.forbidid = forbidid;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Date getExpirydates() {
        return expirydates;
    }

    public void setExpirydates(Date expirydates) {
        this.expirydates = expirydates;
    }

    public Date getExpirydatee() {
        return expirydatee;
    }

    public void setExpirydatee(Date expirydatee) {
        this.expirydatee = expirydatee;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getHeadimgurl() {
        if (null == headimgurl || "".equals(headimgurl)) {
            return headimg;
        }
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

}
