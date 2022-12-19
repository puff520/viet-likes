package com.likes.modules.admin.finance.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.PaySetRequest;
import com.likes.common.mybatis.entity.SysPayset;

public interface PaySetService {

    PageResult getList(PaySetRequest paySetRequest, PageBounds page);

    String doSave(PaySetRequest sysPayset, LoginUser loginUser);

 /*   Long doUpdate(SysPayset sysPayset, LoginUser loginUser);

    Long doUpdateStatus(SysPayset sysPayset, LoginUser loginUser);

    String doDel(SysPayset sysPayset, LoginUser loginUser);*/

    PageResult getList2(PaySetRequest paySetRequest, PageBounds page);

    String doDel2(PaySetRequest sysPayset, LoginUser loginUser);

    String doUpdateStatus2(PaySetRequest sysPayset, LoginUser loginUser);

}
