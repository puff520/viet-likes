package com.likes.common.model.request;

/**
 * 代充用户登录请求
 *
 * @author 瑞夫
 * @version 1.0
 * @Description
 * @date 2020/5/5
 **/
public class ActingUsersRequest {
    private String accno;
    private String acclogin;
    private String password;
    private Double latitude;
    private Double longitude;
    private String clintipadd;

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getClintipadd() {
        return clintipadd;
    }

    public void setClintipadd(String clintipadd) {
        this.clintipadd = clintipadd;
    }
}
