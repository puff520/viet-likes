package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.request.SysAgentinfoReq;
import com.likes.common.model.response.SysAgentinfoResp;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;


public interface SysAgentinfoMapperExt {
	Page<SysAgentinfoResp> getList(SysAgentinfoReq req, RowBounds page);
}