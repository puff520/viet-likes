package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.request.UsersRequest;
import com.likes.common.model.dto.sys.SysShortmsgDO;
import com.likes.common.mybatis.entity.SysShortmsg;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface SysShortmsgMapperExt {

	Integer getLimit(SysShortmsg sysShortmsgParam);

	Integer getCountDown(UsersRequest req);

	void insertSelectiveSysShortmsg(SysShortmsgDO sysShortmsg);

	Integer getMsgByParamCountDown(UsersRequest req);

	SysShortmsg getMsgByParam(UsersRequest req);

	Page<SysShortmsg> getList(SysShortmsg req, RowBounds rowBounds);

	Integer insertByParam(SysShortmsg req);

	Integer selectWaittime(SysShortmsg req);
}