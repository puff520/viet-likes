package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.dto.MandatoryDO;
import com.likes.common.model.response.ThirdProviderNameResponse;
import com.likes.common.mybatis.entity.SysPayprovider;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface SysPayproviderMapperExt {


    Page<SysPayprovider> getList(SysPayprovider sysPayprovider, RowBounds rowBounds);

    SysPayprovider getRepeate(SysPayprovider sysPayprovider);

    MandatoryDO findByOrderno(String orderno);

    List<SysPayprovider> getAllsysPayproviderlist(SysPayprovider sysPayprovider);


    SysPayprovider findPayproviderByMerchantId(String appid);

	String selectByTpaySetId(Long tpaysetid);

//    SysPayprovider selectByTpaySetId(Long tpaysetid);

    List<ThirdProviderNameResponse> getIdAndName();

    Long getProviderId(String provider);
}