package com.likes.common.service.sys.impl;

import com.likes.common.model.dto.sys.SysShortmsgDO;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.mybatis.entity.SysShortmsg;
import com.likes.common.mybatis.mapper.SysShortmsgMapper;
import com.likes.common.mybatis.mapperext.sys.SysShortmsgMapperExt;
import com.likes.common.service.sys.SysShortmsgService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName: SysShortmsgServiceImpl
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/6/19 21:27
 */
@Service
public class SysShortmsgServiceImpl implements SysShortmsgService {

    @Resource
    private SysShortmsgMapper sysShortmsgMapper;
    @Resource
    private SysShortmsgMapperExt sysShortmsgMapperExt;

    @Override
    public Integer getLimit(SysShortmsg sysShortmsgParam) {
        return sysShortmsgMapperExt.getLimit(sysShortmsgParam);
    }

    @Override
    public Integer getCountDown(UsersRequest req) {
        return sysShortmsgMapperExt.getCountDown(req);
    }

    @Override
    public void insertSelective(SysShortmsgDO sysShortmsg) {
        sysShortmsgMapper.insertSelective(sysShortmsg);
    }

    @Override
    public void insertSelectiveSysShortmsg(SysShortmsgDO sysShortmsg) {
        sysShortmsgMapperExt.insertSelectiveSysShortmsg(sysShortmsg);
    }

    @Override
    public Integer getMsgByParamCountDown(UsersRequest req) {
        return sysShortmsgMapperExt.getMsgByParamCountDown(req);
    }

    @Override
    public SysShortmsg getMsgByParam(UsersRequest req) {
        return sysShortmsgMapperExt.getMsgByParam(req);
    }

    @Override
    public Page<SysShortmsg> getList(SysShortmsg req, RowBounds rowBounds) {
        return sysShortmsgMapperExt.getList(req, rowBounds);
    }

    @Override
    public Integer insertByParam(SysShortmsg req) {
        return sysShortmsgMapperExt.insertByParam(req);
    }

    @Override
    public Integer selectWaittime(SysShortmsg req) {
        return sysShortmsgMapperExt.selectWaittime(req);
    }

    @Override
    public void updateByPrimaryKeySelective(SysShortmsg msg) {
        if (null == msg) {
            return;
        }
        sysShortmsgMapper.updateByPrimaryKeySelective(msg);
    }
}
