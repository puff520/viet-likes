package com.likes.modules.admin.business.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.IncarnateRequest;
import com.likes.common.model.vo.finance.MemFinanceVO;
import com.likes.common.mybatis.entity.MemBankaccount;

import java.util.Map;

public interface IncarnateService {

    PageResult anchorIncarnateRecord(LoginUser loginUserAPP, PageBounds page);

    Map<String, Object> anchorBank(LoginUser loginUserAPP);

    Long doSetAnchorBank(LoginUser loginUserAPP, MemBankaccount memBankaccount);


    MemFinanceVO anchorIncarnateDataV2(LoginUser loginUserAPP);

    String doIncarnateV2(LoginUser loginUserAPP, IncarnateRequest req);

    Long reSetAnchorBank(LoginUser loginUserAPP, MemBankaccount memBankaccount);

    String surep(LoginUser loginUserAPP, IncarnateRequest req);

    String cancelIncarnateV2(LoginUser loginUserAPP);


}
