package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.mybatis.entity.SysParameter;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface SysParameterMapperExt {

    SysParameter selectByCode(String sysparamcode);

    Page<SysParameter> getList(SysParameter req, RowBounds rowBounds);

    SysParameter getRepeat(SysParameter req);

    List<SysParameter> queryByCodeNames(@Param("codeNames") List<String> codeNames);

    List<String> getSameCodeParamList(@Param("sysparamcode") String code, @Param("sort") String sort);

    @Select("select sysparamcode from sys_parameter")
    List<String> queryAllKey();

}