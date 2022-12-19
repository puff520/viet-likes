package com.likes.common.service.sys.impl;

import cn.hutool.core.util.ObjectUtil;
import com.likes.common.constant.Constants;
import com.likes.common.enums.MessageTypeEnum;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.InfSysnoticeDO;
import com.likes.common.mybatis.entity.InfSysnotice;
import com.likes.common.mybatis.mapper.InfSysnoticeMapper;
import com.likes.common.mybatis.mapperext.InfSysnoticeMapperExt;
import com.likes.common.service.sys.InfSysnoticeService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class InfSysnoticeServiceImpl implements InfSysnoticeService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private InfSysnoticeMapper infSysnoticeMapper;

    @Resource
    private InfSysnoticeMapperExt infSysnoticeMapperExt;

    @Override
    public PageResult getList(InfSysnotice infSysnotice, PageBounds page) {
        Page<InfSysnotice> list = infSysnoticeMapperExt.getList(infSysnotice, page.toRowBounds());
        return PageResult.getPageResult((int) list.getTotal(), page, list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long doSave(InfSysnotice infSysnotice, LoginUser loginUser) {
        infSysnotice.setCreateUser(loginUser.getAccno());
        infSysnotice.setUpdateUser(loginUser.getAccno());
        infSysnoticeMapper.insertSelective(infSysnotice);
        RedisBusinessUtil.deleteNoticeList();
        RedisBusinessUtil.delInfSysnoticeList();
        return infSysnotice.getNoticeid();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long doUpdate(InfSysnotice infSysnotice, LoginUser loginUser) {
        infSysnotice.setIsDelete(false);
        infSysnotice.setUpdateUser(loginUser.getAccno());
        infSysnoticeMapper.updateByPrimaryKeySelective(infSysnotice);
        RedisBusinessUtil.deleteNoticeList();
        RedisBusinessUtil.delInfSysnoticeList();
        return infSysnotice.getNoticeid();
    }

    @Override
    public String doDel(InfSysnotice infSysnotice, LoginUser loginUser) {
        infSysnotice.setIsDelete(true);
        infSysnotice.setUpdateUser(loginUser.getAccno());
        infSysnoticeMapper.updateByPrimaryKeySelective(infSysnotice);
        RedisBusinessUtil.deleteNoticeList();
        RedisBusinessUtil.delInfSysnoticeList();
        return Constants.SUCCESS_MSG;
    }

    @Override
    public String doWorkStatus(InfSysnotice infSysnotice) {
        InfSysnotice infSysnotice1 = infSysnoticeMapper.selectByPrimaryKey(infSysnotice.getNoticeid());
        infSysnotice.setWorkStatus(!infSysnotice1.getWorkStatus());
        int i = infSysnoticeMapper.updateByPrimaryKeySelective(infSysnotice);
        RedisBusinessUtil.deleteNoticeList();
        RedisBusinessUtil.delInfSysnoticeList();
        return Constants.SUCCESS_MSG;
    }


    @Override
    public List<InfSysnoticeDO> getInfSysnoticeList(Integer type) {
        List<InfSysnoticeDO> infSysnoticeList = RedisBusinessUtil.getInfSysnoticeListByType(type);
        if (CollectionUtil.isEmpty(infSysnoticeList)) {
            infSysnoticeList = infSysnoticeMapperExt.getInfSysnoticeList(type);
            RedisBusinessUtil.addInfSysnoticeListByType(type, infSysnoticeList);
        }

        return infSysnoticeList;
    }

    @Override
    public Page<InfSysnotice> getList(InfSysnotice infSysnotice, RowBounds rowBounds) {

        Page<InfSysnotice> list = infSysnoticeMapperExt.getList(infSysnotice, rowBounds);
        return list;
    }

    @Override
    public void updateAppWorkStatus(Integer type) {
        infSysnoticeMapperExt.updateAppWorkStatus(type);
        RedisBusinessUtil.delInfSysnoticeList();
    }

    @Override
    public List<InfSysnoticeDO> selectNoticeList(Integer type) {

        List<InfSysnoticeDO> infSysnoticeDOS = RedisBusinessUtil.get(Constants.MESSAGE_NOTICE + type);
        if (infSysnoticeDOS == null || infSysnoticeDOS.size() < 1) {
            infSysnoticeDOS = infSysnoticeMapperExt.selectNoticeList(type);
            if (infSysnoticeDOS != null && infSysnoticeDOS.size() > 0) {
                RedisBusinessUtil.set(Constants.MESSAGE_NOTICE + type, infSysnoticeDOS);
            }
        }
        return infSysnoticeDOS;
    }

    @Override
    public List<InfSysnoticeDO> selectAPPNoticeList(Integer type) {
        List<InfSysnoticeDO> infSysnoticeDOS =RedisBusinessUtil.get(Constants.MESSAGE_NOTICE + type);
        if(CollectionUtil.isEmpty(infSysnoticeDOS)){
            infSysnoticeDOS = new LinkedList<>();
        }
        if (CollectionUtil.isEmpty(infSysnoticeDOS)) {
            InfSysnotice param = new InfSysnotice();
            param.setType(type);
            param.setIsDelete(false);
            List<InfSysnotice> infSysnotice =  infSysnoticeMapper.select(param);
            for (InfSysnotice sysnotice : infSysnotice) {
                InfSysnoticeDO infSysnoticeDO = new InfSysnoticeDO();
                BeanUtils.copyProperties(sysnotice,infSysnoticeDO);
                infSysnoticeDOS.add(infSysnoticeDO);
            }
            if (ObjectUtil.isNotNull(infSysnotice)) {
                RedisBusinessUtil.set(Constants.MESSAGE_NOTICE + type, infSysnoticeDOS);
            }
        }
        return infSysnoticeDOS;
    }
}
