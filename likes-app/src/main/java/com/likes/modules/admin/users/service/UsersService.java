package com.likes.modules.admin.users.service;

import com.likes.common.exception.BusinessException;
import com.likes.common.model.DomainList;
import com.likes.common.model.LoginUser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UsersService {

//    Integer sendShortMsg(UsersRequest req, HttpServletRequest request);
//
//    /**
//     * 发送验证码
//     *
//     * @param req
//     * @return
//     */
//    Integer sendSmsCode(UsersRequest req, HttpServletRequest request);
//
//    /**
//     * 用于短信发送选择地区
//     *
//     * @return
//     */
//    List<SysAreaDO> getFirstArea();
//
//    /**
//     * 验证短信码
//     *
//     * @param req
//     * @return
//     */
//    Integer verificationSmsCode(UsersRequest req);
//
//    /**
//     * 注册
//     *
//     * @param req
//     * @return
//     */
//    Map<String, Object> register(UsersRequest req);
//
//    /**
//     * 忘记密码-修改密码
//     *
//     * @param req
//     * @return
//     */
//    String updatePassword(UsersRequest req);
//
//    /**
//     * 登陆
//     *
//     * @param req
//     * @return
//     */
//    LoginUser login(UsersRequest req, HttpServletRequest request);

    /**
     * APP下载链接
     *
     * @return
     */
    Map<String, Object> appdownurl();

    /**
     * @return
     */
    Map<String, Object> updateapp();

//    /**
//     * 邀请插入金币
//     *
//     * @param userGoldDO
//     * @param opearteAccno
//     */
//    void doTuijianUser(UserGoldDO userGoldDO, String opearteAccno);

//    void getIpParse(LoginUser loginUser);

    /**
     * @return
     * @throws IOException 获取app访问最近域名
     */
    List<DomainList> getLink(LoginUser user) throws BusinessException;

//    String verificationInvitecode(UsersRequest req);

    String getSysParam(String code);

}
