package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.mybatis.entity.SysFuncinterface;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SysFuncinterfaceMapperExt {


    List<String> getInterfaceUrlsByRole(List<Long> param);

    SysFuncinterface selectOne(@Param("ofsystem") String ofsystem, @Param("itfcurl") String itfcurl);

    int deleteByModifydate(@Param("ofsystem") String ofsystem);

    List<SysFuncinterface> searchInterfaceBySfunname(String itfcname);
}