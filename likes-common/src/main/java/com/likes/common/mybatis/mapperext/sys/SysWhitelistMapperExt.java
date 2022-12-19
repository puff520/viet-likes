package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.dto.sys.SysWhitelistDO;
import com.likes.common.mybatis.entity.SysWhitelist;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface SysWhitelistMapperExt {

    Page<SysWhitelistDO> getList(SysWhitelist sysWhite, RowBounds rowBounds);

    SysWhitelist findByIp(@Param("syscode") String syscode, @Param("ipaddress") String ipaddress);
}