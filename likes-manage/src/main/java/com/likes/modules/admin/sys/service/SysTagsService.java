package com.likes.modules.admin.sys.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.mybatis.entity.SysTags;

public interface SysTagsService {

    Long saveSysTags(SysTags sysTags, LoginUser loginAdmin);

    Long updateSysTags(SysTags sysTags, LoginUser loginAdmin);

    PageResult sysTagslList(SysTags req, PageBounds page);

    SysTags getSysTagsDetail(SysTags req);

    String delSysTags(SysTags req, LoginUser loginAdmin);

}
