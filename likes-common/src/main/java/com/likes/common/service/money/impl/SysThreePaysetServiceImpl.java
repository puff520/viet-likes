package com.likes.common.service.money.impl;

import com.likes.common.model.dto.report.ProviderSetDO;
import com.likes.common.mybatis.entity.SysThreepayset;
import com.likes.common.mybatis.mapperext.sys.SysThreepaysetMapperExt;
import com.likes.common.service.money.SysThreePaysetService;

import org.springframework.stereotype.Service;


import java.util.List;

import javax.annotation.Resource;

@Service
public class SysThreePaysetServiceImpl implements SysThreePaysetService {
    @Resource
    private SysThreepaysetMapperExt sysThreepaysetMapperExt;

    @Override
    public List<SysThreepayset> commonPay(int userLevel, String wayType) {
        return sysThreepaysetMapperExt.commonPay(userLevel, wayType);
    }

    @Override
    public List<Long> getAllSysThreePaySet(String providerid){
        return sysThreepaysetMapperExt.getAllSysThreePaySet(providerid);
    }

    @Override
    public List<ProviderSetDO> getAllsetAndProvider() {
        return sysThreepaysetMapperExt.getAllsetAndProvider();
    }

    @Override
    public List<Long> getAllids(String provider) {
        return sysThreepaysetMapperExt.getAllids(provider);
    }
}
