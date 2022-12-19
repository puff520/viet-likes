package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.mybatis.entity.SysCdn;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface SysCdnMapperExt {

    List<SysCdn> refresh();

    Page<SysCdn> getList(SysCdn sysCdn, RowBounds rowBounds);

    Page<SysCdn> getListForApp(SysCdn record, RowBounds rowBounds);
}