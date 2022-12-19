package com.likes.common.service.sys;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.mybatis.entity.SysCdn;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

public interface SysCndService {

    String refresh();

    PageResult getList(SysCdn sysCdn, PageBounds page);

    Long doSave(SysCdn sysCdn, LoginUser loginUser);

    Long doUpdate(SysCdn sysCdn, LoginUser loginUser);

    String doDelCdn(SysCdn sysCdn, LoginUser loginUser);

    String doUpdateStatus(SysCdn sysCdn, LoginUser loginUser);

    Page<SysCdn> getList(SysCdn sysCdn, RowBounds rowBounds);

}