package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.dto.pay.SysThreePaysetDTO;
import com.likes.common.model.dto.report.ProviderSetDO;
import com.likes.common.model.dto.sys.SysThreepaysetDO;
import com.likes.common.mybatis.entity.SysThreepayset;
import com.github.pagehelper.Page;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface SysThreepaysetMapperExt {

    SysThreepayset getRepeateByAccounttype(SysThreepayset sysThreepayset);

    Page<SysThreepaysetDO> getlist(SysThreepayset sysThreepayset, RowBounds rowBounds);

    List<SysThreepaysetDO> getAllList(SysThreepayset sysThreepayset);

    List<SysThreepayset> commonPay(@Param("memLevel") Integer memLevel, @Param("payType") String payType);

    SysThreePaysetDTO findbyOrderno(String orderno);

    List<Long> getAllSysThreePaySet(@Param("providerid") String providerid);

    List<ProviderSetDO> getAllsetAndProvider();

    List<Long> getAllids(@Param("provider") String provider);
}
