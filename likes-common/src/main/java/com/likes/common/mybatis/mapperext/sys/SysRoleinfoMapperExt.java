package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.dto.sys.SysRoleinfoForRoleDO;
import com.likes.common.mybatis.entity.SysRoleinfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface SysRoleinfoMapperExt {



	SysRoleinfo repeat(SysRoleinfo sysRoleinfo);

	Page<SysRoleinfoForRoleDO> roleList(SysRoleinfo sysRoleinfo, RowBounds rowBounds);

	SysRoleinfo getRoleByAccno(String accno);
}