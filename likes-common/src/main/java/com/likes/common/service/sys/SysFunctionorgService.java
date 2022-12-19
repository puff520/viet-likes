package com.likes.common.service.sys;

import com.likes.common.model.dto.sys.SysFunctionorgDO;
import com.likes.common.model.dto.sys.SysFunctionorgForRoleDO;
import com.likes.common.model.dto.sys.SysRoleinfoForRoleDO;
import com.likes.common.mybatis.entity.SysFunctionorg;

import java.util.List;

/**
 * @author lucien
 * @create 2020/6/19 16:58
 */
public interface SysFunctionorgService {
    /**
     * 插入接口权限表(原生mapper)
     * @param s
     * @return
     */
    int insertSelective(SysFunctionorg s);

    /**
     * 更新权限功能信息
     * @param s
     * @return
     */
    int updateByPrimaryKeySelective(SysFunctionorg s);

    /**
     * 获取权限功能信息
     * @param sfunid
     * @return
     */
    SysFunctionorg selectByPrimaryKey(Long sfunid);

    /**
     * 根据父级id 获取所有子接口权限功能
     * @param parsfunid
     * @return
     */
    List<SysFunctionorgDO> getSysFunctionorgList(Long parsfunid);

    /**
     * 获取权限列表数功能
     * @return
     */
    List<SysFunctionorgDO> getSysFunctionorgTree();

    /**
     * 根据名称类型获取单个接口功能权限
     * @param s
     * @return
     */
    SysFunctionorg getOneSysFunctionorg(SysFunctionorg s);

    /**
     * 禁用的功能不暂示
     * @param do1
     * @return
     */
    List<SysFunctionorgDO> getSysFunctionorgTreeByRoleAll(SysFunctionorgDO do1);
    /**
     * 获取权限列表
     * @param allSfunid
     * @return
     */
    List<SysFunctionorgForRoleDO> getSysFunctionorgAllList(List<Long> allSfunid);

    /**
     * 向上 查询 每个 节点 的 父节点
     * @param sfunid
     * @return
     */
    List<Long> getParSfunidListNode(Long sfunid);

    /**
     * 向下 查询 每个 节点 的 父节点
     * @param sfunid
     * @return
     */
    List<SysRoleinfoForRoleDO> getSfunidList(Long sfunid);
}
