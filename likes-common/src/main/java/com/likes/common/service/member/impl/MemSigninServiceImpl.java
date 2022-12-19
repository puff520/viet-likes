package com.likes.common.service.member.impl;

import com.likes.common.model.dto.member.MemSigninDO;
import com.likes.common.model.dto.order.SignDaoDO;
import com.likes.common.mybatis.entity.MemSignin;
import com.likes.common.mybatis.mapper.MemSigninMapper;
import com.likes.common.mybatis.mapperext.member.MemSigninMapperExt;
import com.likes.common.service.member.MemSigninService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemSigninServiceImpl implements MemSigninService {
    @Resource
    private MemSigninMapperExt memSigninMapperExt;
    @Resource
    private MemSigninMapper memSigninMapper;


    @Override
    public MemSignin getTodayData(SignDaoDO param) {
        return memSigninMapperExt.getTodayData(param);
    }

    @Override
    public int insertMemSignin(MemSigninDO memSignin) {
        return memSigninMapperExt.insertMemSignin(memSignin);
    }
}
