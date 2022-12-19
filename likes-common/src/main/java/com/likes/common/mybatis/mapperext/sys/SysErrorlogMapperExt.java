package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.mybatis.entity.SysErrorlog;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface SysErrorlogMapperExt {

    SysErrorlog selectByMd5(@Param("orginfo") String orginfo);

    Page<SysErrorlog> getList(SysErrorlog req, RowBounds rowBounds);
}