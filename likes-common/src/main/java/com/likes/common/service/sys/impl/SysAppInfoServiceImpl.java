package com.likes.common.service.sys.impl;

import com.likes.common.model.dto.AppinfoDO;
import com.likes.common.model.dto.sys.SysAppInfoDO;
import com.likes.common.model.request.SysAppInfoReq;
import com.likes.common.mybatis.entity.SysAppInfo;
import com.likes.common.mybatis.mapper.SysAppInfoMapper;
import com.likes.common.mybatis.mapper.SysAppInfoMapperExt;
import com.likes.common.service.sys.SysAppInfoService;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAppInfoServiceImpl implements SysAppInfoService {
    @Autowired
    private SysAppInfoMapperExt sysAppInfoMapperExt;
    @Autowired
    private SysAppInfoMapper sysAppInfoMapper;


    @Override
    public List<AppinfoDO> getMaxXin() {
        List<AppinfoDO> list = RedisBusinessUtil.getLastUpdateApp();
        if (null == list) {
            list = sysAppInfoMapperExt.getMaxXin();
            RedisBusinessUtil.addLastUpdateApp(list);
        }
        return list;
    }

    @Override
    public Page<SysAppInfoDO> getList(SysAppInfo req, RowBounds rowBounds) {
        return sysAppInfoMapperExt.getList(req, rowBounds);
    }

    @Override
    public void noNew(Integer appType) {
        sysAppInfoMapperExt.noNew(appType);
    }

    @Override
    public SysAppInfo getSysAppInfoLast(SysAppInfoReq req) {
        return sysAppInfoMapperExt.getSysAppInfoLast(req);
    }

    @Override
    public int insertSelective(SysAppInfo record) {
        int n = sysAppInfoMapper.insertSelective(record);
        RedisBusinessUtil.delLastUpdateApp();
        return n;
    }

    @Override
    public SysAppInfo selectByPrimaryKey(Long appid) {
        return sysAppInfoMapper.selectByPrimaryKey(appid);
    }

    @Override
    public int updateByPrimaryKeySelective(SysAppInfo record) {
        int n = sysAppInfoMapper.updateByPrimaryKeySelective(record);
        RedisBusinessUtil.delLastUpdateApp();
        return n;
    }
}
