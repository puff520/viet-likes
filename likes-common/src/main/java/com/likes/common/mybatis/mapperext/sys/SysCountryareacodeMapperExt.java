package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.dto.sys.SysAreaDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysCountryareacodeMapperExt {

    List<SysAreaDO> getFirstArea();
}