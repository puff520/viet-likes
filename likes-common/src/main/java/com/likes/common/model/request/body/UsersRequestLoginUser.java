package com.likes.common.model.request.body;

import com.likes.common.model.LoginUser;
import com.likes.common.model.request.UsersRequest;


public class UsersRequestLoginUser {
    private UsersRequest usersRequest;
    private LoginUser loginUser;
    private String wechat;

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public UsersRequest getUsersRequest() {
        return usersRequest;
    }

    public void setUsersRequest(UsersRequest usersRequest) {
        this.usersRequest = usersRequest;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }
}
