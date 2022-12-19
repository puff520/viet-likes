package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.mybatis.entity.SysReffuncinitfc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SysReffuncinitfcMapperExt {


	int updateRef(SysReffuncinitfc record);

	List<SysReffuncinitfc> getRefList(@Param("ofsystem") String ofsystem);

	int deleteByFunctionModifydate(@Param("ofsystem") String ofsystem, @Param("updateTime") Date updateTime);
}