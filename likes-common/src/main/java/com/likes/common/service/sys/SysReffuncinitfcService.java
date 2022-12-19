package com.likes.common.service.sys;

import com.likes.common.mybatis.entity.SysReffuncinitfc;

import java.util.Date;
import java.util.List;

/**
 * @author lucien
 * @create 2020/6/19 17:00
 */
public interface SysReffuncinitfcService {
    /**
     * 删除关联接口功能表数据
     * @param liveManage
     * @param now
     * @return
     */
    int deleteByFunctionModifydate(String liveManage, Date now);
    /**
     * 新增关联接口功能表信息
     * @param sr
     * @return
     */
    int insertSelective(SysReffuncinitfc sr);

    /**
     * 获取关联接口功能表信息
     * @param liveManage
     * @return
     */

    List<SysReffuncinitfc> getSysReffuncinitfcList(String liveManage);

    /**
     * 更新关联接口功能表信息
     * @param sr
     * @return
     */
    int updateSysReffuncinitfc(SysReffuncinitfc sr);
}
