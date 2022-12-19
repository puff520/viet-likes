package com.likes.common.service.sys;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.mybatis.entity.SysWhitelist;

public interface SysWhitelistService {

    PageResult getList(SysWhitelist sysWhite, PageBounds page);

    Long doSave(SysWhitelist sysWhitelist, LoginUser loginUser);

    Long doUpdate(SysWhitelist sysWhitelist, LoginUser loginUser);

    String doDelWhite(SysWhitelist sysWhitelist, LoginUser loginUser);


    SysWhitelist findByIp(String syscode, String ipaddress);

}