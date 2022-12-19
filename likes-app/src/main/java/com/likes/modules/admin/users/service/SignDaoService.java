package com.likes.modules.admin.users.service;

import com.likes.common.model.LoginUser;

import java.util.Map;

public interface SignDaoService {

    /**
     * 签到列表
     *
     * @param loginUserAPP
     * @return
     */
    Map<String, Object> signList(LoginUser loginUserAPP);

    /**
     * 签到
     *
     * @param loginUserAPP
     * @return
     */
    String dosign(LoginUser loginUserAPP);

}
