package com.likes.common.service.sys.impl;

import com.likes.common.mybatis.entity.SysReffuncinitfc;
import com.likes.common.mybatis.mapper.SysReffuncinitfcMapper;
import com.likes.common.mybatis.mapperext.sys.SysReffuncinitfcMapperExt;
import com.likes.common.service.sys.SysReffuncinitfcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lucien
 * @create 2020/6/19 17:00
 */
@Service
public class SysReffuncinitfcServiceImpl implements SysReffuncinitfcService {

    @Autowired
    private SysReffuncinitfcMapper sysReffuncinitfcMapper;
    @Autowired
    private SysReffuncinitfcMapperExt sysReffuncinitfcMapperExt;

    @Override
    public int deleteByFunctionModifydate(String liveManage, Date now) {
       return sysReffuncinitfcMapperExt.deleteByFunctionModifydate(liveManage,now);
    }
    @Override
    public int insertSelective(SysReffuncinitfc sr) {
       return sysReffuncinitfcMapper.insertSelective(sr);
    }

    @Override
    public List<SysReffuncinitfc> getSysReffuncinitfcList(String liveManage) {
        return sysReffuncinitfcMapperExt.getRefList(liveManage);
    }

    @Override
    public int updateSysReffuncinitfc(SysReffuncinitfc sr) {
        return sysReffuncinitfcMapperExt.updateRef(sr);
    }
}
