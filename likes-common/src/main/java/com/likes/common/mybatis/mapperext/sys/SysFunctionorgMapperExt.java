package com.likes.common.mybatis.mapperext.sys;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.model.dto.sys.SysFunctionorgDO;
import com.likes.common.model.dto.sys.SysFunctionorgForRoleDO;
import com.likes.common.model.dto.sys.SysRoleinfoForRoleDO;
import com.likes.common.mybatis.entity.SysFunctionorg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysFunctionorgMapperExt {


	List<SysFunctionorgDO> getSysFunctionorgTree();

	SysFunctionorg repeat(SysFunctionorg s);

	List<SysFunctionorgDO> getSysFunctionorgTreeByRole(SysFunctionorgDO do1);

	List<SysFunctionorgDO> getAll(Long parsfunid);

	List<SysFunctionorg> getFunctionorgBySysroleid(long sysroleid);

	List<SysFunctionorgDO> getSysFunctionorgTreeByRoleAll(SysFunctionorgDO do1);

	List<Long> getParSfunidListNode(Long sfunid);

	List<SysFunctionorgForRoleDO> getSysFunctionorgList(List<Long> allSfunid);

	List<SysFunctionorgDO> selectByPSfunid(Long sfunid);

	List<SysFunctionorgDO> selectTreefunid(Long sfunid,Long sysroleid);

	List<SysRoleinfoForRoleDO> getSfunidList(Long sfunid);
}
