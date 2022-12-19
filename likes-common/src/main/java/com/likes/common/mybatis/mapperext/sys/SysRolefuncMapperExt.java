package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.request.SysRolefuncRequest;
import com.likes.common.mybatis.entity.SysRolefunc;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRolefuncMapperExt {


	List<Long> getRoleSfunidList(Long sysroleid);

	void delRoleFunctionorg(SysRolefunc sysRolefunc);

	Integer insertList(List<SysRolefunc> list);

	Integer insertList2(SysRolefuncRequest sysRolefuncRequest);

	int updateSysRolefuncOne(SysRolefunc param);

	int insertSysRolefuncOne(SysRolefunc s);

	void insertSysRolefuncList(List<SysRolefunc> sysRolefuncList);
}