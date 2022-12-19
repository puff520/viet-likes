package com.likes.modules.admin.sys.service.impl;

import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.sys.SysTagsDO;
import com.likes.common.mybatis.entity.SysTags;
import com.likes.common.mybatis.mapper.SysTagsMapper;
import com.likes.common.mybatis.mapperext.sys.SysTagsMapperExt;
import com.likes.modules.admin.sys.service.SysTagsService;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysTagsServiceImpl implements SysTagsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysTagsMapper sysTagsMapper;
    @Resource
    private SysTagsMapperExt sysTagsMapperExt;

    @Override
    public Long saveSysTags(SysTags sysTags, LoginUser loginAdmin) {
        if (null == sysTags.getSortby()) {
            sysTags.setSortby(0);
        }
        SysTags sTags = sysTagsMapperExt.findByName(sysTags);
        if (sTags != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "名称已存在");
        }
        sysTags.setCreateUser(loginAdmin.getAccno());
        sysTags.setUpdateUser(loginAdmin.getAccno());
        sysTagsMapper.insertSelective(sysTags);
        return sysTags.getTagid();
    }

    @Override
    public Long updateSysTags(SysTags sysTags, LoginUser loginAdmin) {
        if (null == sysTags.getSortby()) {
            sysTags.setSortby(0);
        }

        SysTags sTags = sysTagsMapperExt.findByName(sysTags);
        if (sTags != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "名称已存在");
        }
        sysTags.setIsDelete(false);
        sysTags.setCreateUser(loginAdmin.getAccno());
        sysTags.setUpdateUser(loginAdmin.getAccno());
        sysTagsMapper.updateByPrimaryKeySelective(sysTags);
        return sysTags.getTagid();
    }

    @Override
    public PageResult sysTagslList(SysTags req, PageBounds page) {
        if (req.getTagtype() == 1) {
            Page<SysTags> list = sysTagsMapperExt.getList(req, page.toRowBounds());
            //list.forEach(v -> {
            //});
            return PageResult.getPageResult(page, list);
        }

        if (req.getTagtype() == 2) {
            Page<SysTagsDO> list = sysTagsMapperExt.getListTuwen(req, page.toRowBounds());
            //list.forEach(v -> {
            //});
            return PageResult.getPageResult(page, list);
        }

        return null;

    }

    @Override
    public SysTags getSysTagsDetail(SysTags sysTags) {
        SysTags sTags = sysTagsMapper.selectByPrimaryKey(sysTags.getTagid());
        if (sTags != null && !sTags.getIsDelete()) {
            return sTags;
        }
        return null;
    }

    @Override
    public String delSysTags(SysTags req, LoginUser loginAdmin) {
        req.setIsDelete(true);
        req.setUpdateUser(loginAdmin.getAccno());
        sysTagsMapper.updateByPrimaryKeySelective(req);

        return Constants.SUCCESS_MSG;
    }

}
