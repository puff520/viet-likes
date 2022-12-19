package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.response.SysBusparameterResp;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface SysBusparameterMapperExt {

    List<SysBusparameter> selectByParedubpcode(@Param("pbusparamcode") String pbusparamcode,@Param("isDelete")  boolean isDelete);

    List<SysBusparameter> getList(SysBusparameter sysBusparameter);

    SysBusparameter getRepeat(SysBusparameter sysBusparameter);

    SysBusparameter selectByBusparamcode(String busparamcode);

    List<String> getChild(String busparamcode);

    List<SysBusparameter> getByCodes(List<String> list);

    List<SysBusparameterResp> selectByCode(@Param("busparamcode") String busparamcode);

    Page<SysBusparameter> getList2(SysBusparameter req, RowBounds rowBounds);

    List<SysBusparameter> getNormList(@Param("pbusparamcode") String pbusparamcode);

    SysBusparameter getRandomOne(@Param("pbusparamcode") String pbusparamcode);

    @Select("select busparamcode from sys_busparameter")
    List<String> queryAllBusKey();
}