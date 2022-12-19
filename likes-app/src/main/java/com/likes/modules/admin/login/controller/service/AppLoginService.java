package com.likes.modules.admin.login.controller.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.dto.sys.SysAreaDO;
import com.likes.common.model.request.ActingUsersRequest;
import com.likes.common.model.request.UsersRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AppLoginService {


    /**
     * 发送验证码
     */
    Integer sendMailCode(UsersRequest req, HttpServletRequest request);

    /**
     * 用于短信发送选择地区
     */
    List<SysAreaDO> getFirstArea();


    /**
     * 注册
     */
    Map<String, Object> register(UsersRequest req);

    void getIpParse(LoginUser loginUser);

    /**
     * APP 登陆
     */
    LoginUser login(UsersRequest req, String source);

    /**
     * 忘记密码-修改密码
     */
    String updatePassword(UsersRequest req);


    String getSwipeVerificationSwift();

}
