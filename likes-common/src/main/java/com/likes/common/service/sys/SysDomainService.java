package com.likes.common.service.sys;

import com.github.pagehelper.Page;
import com.likes.common.model.LoginUser;
import com.likes.common.model.SysDomain;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.SysDomainRequest;
import com.likes.common.mybatis.entity.SysCdn;
import com.likes.common.util.PageBaseInfo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysDomainService {


    boolean add(SysDomainRequest request, LoginUser loginUser);

    boolean update(SysDomainRequest request, LoginUser loginUser);

    boolean delete(String ids, LoginUser loginUser);

    PageBaseInfo<SysDomain> domainList(SysDomainRequest request);


}