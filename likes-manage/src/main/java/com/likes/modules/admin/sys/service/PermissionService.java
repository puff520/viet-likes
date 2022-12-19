package com.likes.modules.admin.sys.service;


import com.alibaba.fastjson.JSONArray;
import com.likes.common.model.LoginUser;
import com.likes.common.model.SysRoleinfoDO;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.member.MemLoginDO;
import com.likes.common.model.dto.sys.SysFunctionorgDO;
import com.likes.common.model.dto.sys.SysFunctionorgForRoleDO;
import com.likes.common.model.request.RoleFunctionorgRequest;
import com.likes.common.model.vo.member.BdUserDetailVO;
import com.likes.common.mybatis.entity.SysBduserrole;
import com.likes.common.mybatis.entity.SysFunctionorg;
import com.likes.common.mybatis.entity.SysRoleinfo;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    Long saveRole(SysRoleinfo sysRoleinfo, LoginUser loginAdmin);

    Map<String, Object> adminRroleList(SysRoleinfo sysRoleinfo);

    Long adminRroleAdd(SysBduserrole sys, LoginUser loginAdmin);

    String adminRroleDel(SysBduserrole sys, LoginUser loginAdmin);

    List<SysFunctionorgDO> getSysFunctionorgTree();

    Long doSetSysFunctionorg(SysFunctionorg s, LoginUser loginAdmin);

    String doDelSysFunctionorg(SysFunctionorg s, LoginUser loginAdmin);

    PageResult roleList(SysRoleinfo sysRoleinfo, PageBounds page);

    String doRoleFunctionorg(RoleFunctionorgRequest req, LoginUser loginAdmin);

    JSONArray getSysSelectFunctionorgTree(Long sfunid);

    SysRoleinfoDO detailRoleFunctionorg(SysRoleinfo sysRoleinfo);

    Long doSaveBdUser(MemLoginDO memLogin, LoginUser loginAdmin);

    BdUserDetailVO getBdUserDetail(MemLoginDO memLogin, LoginUser loginAdmin);

    Long doUpdateBdUser(MemLoginDO memLogin, LoginUser loginAdmin);

    String doAccstatus(MemLoginDO memLogin, LoginUser loginAdmin);

    List<SysFunctionorgForRoleDO> getSysFunctionorgForRole(long sysroleid);

    void doSetRoleFunctionorgForInterface(Long sysroleid, List<SysFunctionorgForRoleDO> funlists);

    PageResult roleListForSystemUser(SysRoleinfo sysRoleinfo, PageBounds page);


    boolean deleteRole(SysRoleinfo sysRoleinfo, LoginUser loginAdmin);


    boolean checkUserAuthByToken(String acctoken,String url);
}
