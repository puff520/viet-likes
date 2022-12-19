package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.dto.sys.SysInfologDO;
import com.likes.common.mybatis.entity.SysInfolog;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface SysInfologMapperExt {
    Page<SysInfologDO> getList(SysInfolog req, RowBounds rowBounds);
}