package com.likes.modules.admin.business.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.dto.pay.SysThreePaysetDTO;
import com.likes.common.model.request.TraRechargemealRequest;
import com.likes.common.model.response.TraRechargemealResponse;

import java.util.List;
import java.util.Map;

public interface RechargeService {

    List<TraRechargemealResponse> rechargemealList();

    /**
     * 随机返回一个
     */
    Map<String, Object> getBankList(LoginUser loginUser);

    Map<String, Object> doPayV1(LoginUser loginUserAPP, TraRechargemealRequest req);

    /**
     * 获取在线充值设置
     */
    List<SysThreePaysetDTO> paySetInfo(LoginUser loginUserAPP, String wayType);
}
