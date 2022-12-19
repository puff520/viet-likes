package com.likes.modules.admin.finance.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.mybatis.entity.SysPayprovider;
import com.likes.common.mybatis.entity.SysThreepayset;

import java.util.List;

public interface SysThreepaysetService {

    PageResult getSysPayproviderList(SysPayprovider sysPayprovider, PageBounds page);

    Long doSaveSysPayprovider(SysPayprovider sysPayprovider, LoginUser loginUser);

    Long doUpdateSysPayprovider(SysPayprovider sysPayprovider, LoginUser loginUser);

    String doDelSysPayprovider(SysPayprovider sysPayprovider, LoginUser loginUser);

    String doUpdateSysPayproviderStatus(SysPayprovider sysPayprovider, LoginUser loginUser);

    List<SysPayprovider> getAllsysPayproviderlist(SysPayprovider sysPayprovider);

    Long doSaveSysThreepayset(SysThreepayset sysThreepayset, LoginUser loginUser);

    Long doUpdateSysThreepayset(SysThreepayset sysThreepayset, LoginUser loginUser);

    String doDelSysThreepayset(SysThreepayset sysPayaccount, LoginUser loginUser);

    String doUpdateSysThreepaysetStatus(SysThreepayset sysThreepayset, LoginUser loginUser);

    PageResult sysThreepaysetlist(SysThreepayset sysThreepayset, PageBounds page);

}
