package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.response.SysLiveserverResp;
import com.likes.common.mybatis.entity.SysLiveserver;
import com.likes.common.util.robin.Node;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysLiveserverMapperExt {

    Page<SysLiveserver> getList(SysLiveserver sysLiveserver, RowBounds rowBounds);

    List<SysLiveserverResp> getListAll();

    List<Node> selectByRegion(@Param("region") String region);

}