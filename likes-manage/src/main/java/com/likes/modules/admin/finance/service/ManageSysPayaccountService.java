package com.likes.modules.admin.finance.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.mybatis.entity.SysPayaccount;

public interface ManageSysPayaccountService {

    PageResult getList(SysPayaccount sysPayaccount, PageBounds page);

    Long doSavePayaccount(SysPayaccount sysPayaccount, LoginUser loginUser);

    String doDelPayaccount(SysPayaccount sysPayaccount, LoginUser loginUser);

    Long doUpdatePayaccount(SysPayaccount sysPayaccount, LoginUser loginUser);

    String doUpdateStatus(SysPayaccount sysCdn, LoginUser loginUser);

    Object cleanTotalAmount(SysPayaccount sysCdn, LoginUser loginUser);
}
