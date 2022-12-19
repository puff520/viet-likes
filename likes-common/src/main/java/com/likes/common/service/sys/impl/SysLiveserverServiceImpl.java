package com.likes.common.service.sys.impl;

import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.response.SysLiveserverResp;
import com.likes.common.mybatis.entity.SysLiveserver;
import com.likes.common.mybatis.mapper.SysLiveserverMapper;
import com.likes.common.mybatis.mapperext.sys.SysLiveserverMapperExt;
import com.likes.common.service.sys.SysLiveserverService;
import com.likes.common.util.StringUtils;
import com.likes.common.util.robin.Node;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysLiveserverServiceImpl implements SysLiveserverService {

    @Resource
    private SysLiveserverMapper sysLiveserverMapper;
    @Resource
    private SysLiveserverMapperExt sysLiveserverMapperExt;

    @Override
    public PageResult getList(SysLiveserver sysLiveserver, PageBounds page) {
        Page<SysLiveserver> list = sysLiveserverMapperExt.getList(sysLiveserver, page.toRowBounds());
        return PageResult.getPageResult((int) list.getTotal(), page, list);
    }

    @Override
    public Long doSave(SysLiveserver sysLiveserver, LoginUser loginUser) {
        if (null == sysLiveserver.getWeight()) {
            sysLiveserver.setWeight(0);
        }
        sysLiveserver.setCreateUser(loginUser.getAccno());
        sysLiveserver.setUpdateUser(loginUser.getAccno());
        sysLiveserverMapper.insertSelective(sysLiveserver);
        return sysLiveserver.getLiveid();
    }

    @Override
    public Long doUpdate(SysLiveserver sysLiveserver, LoginUser loginUser) {
        if (null == sysLiveserver.getWeight()) {
            sysLiveserver.setWeight(0);
        }
        sysLiveserver.setUpdateUser(loginUser.getAccno());
        sysLiveserverMapper.updateByPrimaryKeySelective(sysLiveserver);
        return sysLiveserver.getLiveid();
    }

    @Override
    public String doDelLiveserver(SysLiveserver sysLiveserver, LoginUser loginUser) {
        SysLiveserver liveserver = sysLiveserverMapper.selectByPrimaryKey(sysLiveserver.getLiveid());
        if (liveserver == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10005.getCode(), "直播服务器不存在");
        }
        sysLiveserver.setIsDelete(!liveserver.getIsDelete());

        sysLiveserver.setUpdateUser(loginUser.getAccno());
        sysLiveserverMapper.updateByPrimaryKeySelective(sysLiveserver);
        return String.valueOf(sysLiveserver.getIsDelete());
    }

    @Override
    public Long doDelete(SysLiveserver sysLiveserver, LoginUser loginUser) {
        SysLiveserver liveserver = sysLiveserverMapper.selectByPrimaryKey(sysLiveserver.getLiveid());
        if (liveserver == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10005.getCode(), "直播服务器不存在");
        }
        int i = sysLiveserverMapper.deleteByPrimaryKey(liveserver.getLiveid());
        return (long) i;
    }

    @Override
    public List<SysLiveserverResp> getListAll() {
        return sysLiveserverMapperExt.getListAll();
    }

    @Override
    public List<Node> selectByRegion(String region) {
        List<Node> list = null;
        if (StringUtils.isNotEmpty(region)) {
            list = sysLiveserverMapperExt.selectByRegion(region);
        }
        return null == list ? new ArrayList<>() : list;
    }
}
