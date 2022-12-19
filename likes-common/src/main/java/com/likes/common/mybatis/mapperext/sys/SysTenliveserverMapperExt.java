package com.likes.common.mybatis.mapperext.sys;


import com.likes.common.model.response.TenLiveserverResp;
import com.likes.common.util.robin.Node;

import java.util.List;

public interface SysTenliveserverMapperExt {

    List<TenLiveserverResp> getList();

    List<Node> selectByPtliveid();

}