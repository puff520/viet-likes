package com.likes.modules.admin.users.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.member.MemBaseinfoDO;
import com.likes.common.model.dto.order.OrderRequest;
import com.likes.common.model.request.PayPasswordReq;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.model.vo.member.AttentionUserVO;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.SysFeedback;

import java.util.Map;

public interface PersonService {

    /**
     * 获取个人信息
     *
     * @param loginUserAPP
     * @return
     */
    MemBaseinfoDO getUserInfo(LoginUser loginUserAPP);

    /**
     * 我的推广
     *
     * @param accno
     * @return
     */
    PageResult getMyPromotion(String accno, PageBounds page);

    /**
     * 分享推广码
     *
     * @param accno
     * @return
     */
    Map<String, Object> sharePromotion(String accno);

    /**
     * 修改昵称
     *
     * @param req
     * @param loginUserAPP
     * @return
     */
    String updateNickname(UsersRequest req, LoginUser loginUserAPP);

    /**
     * 修改属性
     *
     * @param req
     * @param loginUserAPP
     * @return
     */
    String updateUserOther(UsersRequest req, LoginUser loginUserAPP);



    /**
     * 新增反馈
     *
     * @param sysFeedback
     * @param accno
     * @return
     */
    long doFeedback(SysFeedback sysFeedback, String accno);


    /**
     * 登出
     *
     * @param loginUserAPP
     * @return
     */
    String loginout(LoginUser loginUserAPP);

    /**
     * 填写邀请码
     *
     * @param usersRequest
     * @param loginUserAPP
     * @return
     */
    String fillinRecomcode(UsersRequest usersRequest, LoginUser loginUserAPP);




    /**
     * 进入成为主播页面
     *
     * @param loginUserAPP
     * @return
     */
    Map<String, Object> getAnchorMM(LoginUser loginUserAPP);

    /**
     * 成为主播
     *
     * @param loginUserAPP
     * @param memBaseinfo
     * @return
     */
    String applyAnchorMM(LoginUser loginUserAPP, MemBaseinfo memBaseinfo);


    PageResult myOrderList(PageBounds page, OrderRequest req);

    Map<String, Object> myOrderDetail(OrderRequest req);

    String cancelOrder(LoginUser loginUserAPP, OrderRequest req);

    String setPayPassword(LoginUser loginUserAPP, PayPasswordReq req);

    String updatePayPassword(LoginUser loginUserAPP, PayPasswordReq req);


    /**
     * 刷新账户余额
     *
     * @param loginUser
     * @return
     */
    Map<String, Object> doReflushGoldnumBalance(LoginUser loginUser);


    String getRecomcode(LoginUser loginUser);
}
