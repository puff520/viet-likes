package com.likes.common.service.sys;

import com.likes.common.mybatis.entity.SysRolefunc;

import java.util.List;

/**
 * @author lucien
 * @create 2020/6/19 17:01
 */
public interface SysRolefuncService {

    /**
     * 获取角色拥有功能列表
     * @param sysroleid
     * @param accno
     * @return
     */
    List<SysRolefunc> getSysRolefuncList(Long sysroleid, String accno);

    /**
     * 获取角色 对于 的 节点
     * @param sysroleid
     * @return
     */
    List<Long> getRoleSfunidList(Long sysroleid);

    /**
     * 更新角色对应功能
     * @param sysRolefunc
     */
    void delRoleFunctionorg(SysRolefunc sysRolefunc);

    /**
     * 新增角色对应功能
     * @param s
     */
    int insertSysRolefuncOne(SysRolefunc s);

    /**
     * 批量新增角色对应功能
     * @param list
     * @return
     */
    Integer insertList(List<SysRolefunc> list);

    /**
     * 批量插入角色权限
     * @param sysRolefuncList
     */
    void insertSysRolefuncList(List<SysRolefunc> sysRolefuncList);
}
