package com.likes.common.service.sys;

import com.likes.common.model.dto.sys.SysRoleinfoForRoleDO;
import com.likes.common.mybatis.entity.SysRoleinfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

/**
 * @author lucien
 * @create 2020/6/19 17:02
 */
public interface SysRoleinfoService {
    /**
     * 更新系统角色
     * @param sysRoleinfo
     * @return
     */
    int updateByPrimaryKeySelective(SysRoleinfo sysRoleinfo);

    /**
     * 新增系统角色
     * @param sysRoleinfo
     * @return
     */
    int insertSelective(SysRoleinfo sysRoleinfo);

    /**
     * 获取系统角色
     * @param sysroleid
     * @return
     */
    SysRoleinfo selectByPrimaryKey(Long sysroleid);

    /**
     * 根据用户标识号获取角色信息
     * @param accno
     * @return
     */
    SysRoleinfo getRoleByAccno(String accno);

    /**
     * 检查重复
     * @param sysRoleinfo
     * @return
     */
    SysRoleinfo checkDuplicates(SysRoleinfo sysRoleinfo);

    /**
     * 获取系统用户权限列表
     * @param sysRoleinfo
     * @param toRowBounds
     * @return
     */
    Page<SysRoleinfoForRoleDO> roleList(SysRoleinfo sysRoleinfo, RowBounds toRowBounds);
}
