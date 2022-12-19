package com.likes.common.service.sys;

import com.likes.common.mybatis.entity.SysFuncinterface;

import java.util.List;

/**
 * @author lucien
 * @create 2020/6/19 17:04
 */
public interface SysFuncinterfaceService {
    /**
     * 容器初始化时删除后台系统权限接口信息
     * @param liveManage
     * @return
     */
    int deleteByModifydate(String liveManage);

    /**
     * 根据所属系统，接口地址获取对应的接口信息
     * @param liveManage
     * @param url
     * @return
     */
    SysFuncinterface getOneSysFuncinterface(String liveManage, String url);

    /**
     * 容器初始化时插入单条接口详情信息(原生mapper)
     * @param api
     */
    int insertSelective(SysFuncinterface api);

    /**
     * 容器初始化时根据业务更新接口详情信息(原生mapper)
     * @param api
     */
    int updateByPrimaryKeySelective(SysFuncinterface api);

    /**
     * 根据角色获取角色模块对于的接口
     * @param param
     * @return
     */
    List<String> getInterfaceUrlsByRole(List<Long> param);

    /**
     * 根据模块名称获取对于的接口信息
     * @param sfunname
     * @return
     */
    List<SysFuncinterface> searchInterfaceBySfunname(String sfunname);
}
