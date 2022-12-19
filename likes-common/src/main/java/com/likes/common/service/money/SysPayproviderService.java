package com.likes.common.service.money;

import com.likes.common.model.dto.MandatoryDO;
import com.likes.common.model.response.ThirdProviderNameResponse;
import com.likes.common.mybatis.entity.SysPayprovider;
import com.likes.common.mybatis.entity.SysPayproviderExample;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysPayproviderService {
    int countByExample(SysPayproviderExample example);

    int deleteByExample(SysPayproviderExample example);

    int deleteByPrimaryKey(Long providerid);

    int insert(SysPayprovider record);

    int insertSelective(SysPayprovider record);

    SysPayprovider selectOneByExample(SysPayproviderExample example);

    List<SysPayprovider> selectByExample(SysPayproviderExample example);

    SysPayprovider selectByPrimaryKey(Long providerid);

    int updateByExampleSelective(SysPayprovider record, SysPayproviderExample example);

    int updateByExample(SysPayprovider record, SysPayproviderExample example);

    int updateByPrimaryKeySelective(SysPayprovider record);

    int updateByPrimaryKey(SysPayprovider record);

    Page<SysPayprovider> getList(SysPayprovider sysPayprovider, RowBounds rowBounds);

    SysPayprovider getRepeate(SysPayprovider sysPayprovider);

    MandatoryDO findByOrderno(String orderno);

    List<SysPayprovider> getAllsysPayproviderlist(SysPayprovider sysPayprovider);

    String selectByTpaySetId(Long tpaysetid);
//    SysPayprovider selectByTpaySetId(Long tpaysetid);

    List<ThirdProviderNameResponse> getIdAndName();

    Long getProviderId(String provider);
}
