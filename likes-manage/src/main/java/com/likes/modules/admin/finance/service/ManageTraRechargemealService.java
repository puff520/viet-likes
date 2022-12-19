package com.likes.modules.admin.finance.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.TraRechargemealReq;

public interface ManageTraRechargemealService {

    Long doSaveTraRechargemeal(TraRechargemealReq req, LoginUser loginUser);

    Long doUpdateTraRechargemeal(TraRechargemealReq req, LoginUser loginUser);

    String doDelTraRechargemeal(TraRechargemealReq req, LoginUser loginUser);

    PageResult traRechargemealList(TraRechargemealReq req, PageBounds page);

}
