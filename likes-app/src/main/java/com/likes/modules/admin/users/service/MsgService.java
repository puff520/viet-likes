package com.likes.modules.admin.users.service;

import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.InfSysnoticeDO;
import com.likes.common.model.request.InfSysnoticeRequest;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.model.request.body.UsersRequestLoginUser;

import java.util.List;
import java.util.Map;

public interface MsgService {

    /**
     * 我的消息数量
     */
    Map<String, Object> myMsgNum(String accno);

    /**
     * 我的资源审核消息
     */
    PageResult mySourceMsgList(PageBounds page, UsersRequest usersRequest);

    /**
     * 系统公共消息
     */
    PageResult systemMsgList(PageBounds page, UsersRequest usersRequest);

    /**
     * 直播间系统公告
     */
    List<InfSysnoticeDO> infSysnoticeList(InfSysnoticeRequest req);

    List<InfSysnoticeDO> infSysnoticeMsgList(InfSysnoticeRequest req);

}
