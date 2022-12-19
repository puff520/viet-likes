package com.likes.common.service.yuebao;


import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.req.YuebaoOperateReq;
import com.likes.common.model.req.YuebaoRecordReq;
import com.likes.common.model.vo.AssetExpireVO;
import com.likes.common.model.vo.YuebaoBaseInfoVO;

import java.math.BigDecimal;

public interface YuebaoService {

     YuebaoBaseInfoVO queryBaseInfo(LoginUser loginUser);

     PageResult operateList(YuebaoRecordReq req, LoginUser loginUser);

     PageResult earnList(YuebaoRecordReq req, LoginUser loginUser);

     boolean intoYuebao(YuebaoOperateReq req, LoginUser loginUser);
     boolean deposit(YuebaoOperateReq req, LoginUser loginUser);

     boolean take(YuebaoOperateReq req,LoginUser loginUser);

     boolean outYuebao(YuebaoOperateReq req,LoginUser loginUser);

     BigDecimal usableAmount(YuebaoOperateReq req, LoginUser loginUser);

     AssetExpireVO expire(YuebaoOperateReq req, LoginUser loginUser);



}
