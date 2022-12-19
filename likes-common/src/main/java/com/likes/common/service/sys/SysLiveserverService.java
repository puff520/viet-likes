package com.likes.common.service.sys;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.response.SysLiveserverResp;
import com.likes.common.mybatis.entity.SysLiveserver;
import com.likes.common.util.robin.Node;

import java.util.List;

public interface SysLiveserverService {

    PageResult getList(SysLiveserver sysLiveserver, PageBounds page);

    Long doSave(SysLiveserver sysLiveserver, LoginUser loginUser);

    Long doUpdate(SysLiveserver sysLiveserver, LoginUser loginUser);

    String doDelLiveserver(SysLiveserver sysLiveserver, LoginUser loginUser);

    Long doDelete(SysLiveserver sysLiveserver, LoginUser loginUser);

    List<SysLiveserverResp> getListAll();

    List<Node> selectByRegion(String region);
}
