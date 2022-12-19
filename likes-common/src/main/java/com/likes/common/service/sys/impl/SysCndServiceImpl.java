package com.likes.common.service.sys.impl;

import com.likes.common.constant.Constants;
import com.likes.common.constant.PublicVariable;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.mybatis.entity.SysCdn;
import com.likes.common.mybatis.mapper.SysCdnMapper;
import com.likes.common.mybatis.mapperext.sys.SysCdnMapperExt;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.sys.SysCndService;
import com.likes.common.util.StringUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.github.pagehelper.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysCndServiceImpl extends BaseServiceImpl implements SysCndService {

    @Resource
    private SysCdnMapper sysCdnMapper;
    @Resource
    private SysCdnMapperExt sysCdnMapperExt;

    @Override
    public String refresh() {
        // 获取图片和视频cdn 域名
        List<SysCdn> list = sysCdnMapperExt.refresh();
        if (CollectionUtils.isNotEmpty(list)) {
            // 设置redis
            for (SysCdn sysCdn : list) {
                // 图片cdn
                if (PublicVariable.CND_PIC_KEY.equals(sysCdn.getSecretkey())) {
                    if (StringUtils.isNotEmpty(sysCdn.getDomain())) {
                        RedisBusinessUtil.set(PublicVariable.CND_PIC_KEY, sysCdn.getDomain());
                    }
                }
                // 视频cdn
                if (PublicVariable.CDN_VIDEO_KEY.equals(sysCdn.getSecretkey())) {
                    if (StringUtils.isNotEmpty(sysCdn.getDomain())) {
                        RedisBusinessUtil.set(PublicVariable.CDN_VIDEO_KEY, sysCdn.getDomain());
                    }
                }
            }

            // 便于查看
            String cdn_pic = RedisBusinessUtil.get(PublicVariable.CND_PIC_KEY);
            String cdn_video = RedisBusinessUtil.get(PublicVariable.CDN_VIDEO_KEY);
            return cdn_pic + "**************" + cdn_video;
        }
        return "listIsNull";
    }

    @Override
    public PageResult getList(SysCdn sysCdn, PageBounds page) {
        Page<SysCdn> list = sysCdnMapperExt.getList(sysCdn, page.toRowBounds());
        return PageResult.getPageResult((int) list.getTotal(), page, list);
    }

    @Override
    public Long doSave(SysCdn sysCdn, LoginUser loginUser) {
        sysCdn.setStatus(Constants.STATUS_0);
        sysCdn.setCreateUser(loginUser.getAccno());
        sysCdn.setUpdateUser(loginUser.getAccno());
        sysCdnMapper.insertSelective(sysCdn);
        return sysCdn.getCdnid();
    }

    @Override
    public Long doUpdate(SysCdn sysCdn, LoginUser loginUser) {
        sysCdn.setIsDelete(false);
        sysCdn.setUpdateUser(loginUser.getAccno());
        sysCdnMapper.updateByPrimaryKeySelective(sysCdn);
        return sysCdn.getCdnid();
    }

    @Override
    public String doDelCdn(SysCdn sysCdn, LoginUser loginUser) {
        sysCdn.setIsDelete(true);
        sysCdn.setUpdateUser(loginUser.getAccno());
        sysCdnMapper.updateByPrimaryKeySelective(sysCdn);
        return Constants.SUCCESS_MSG;
    }

    @Override
    public String doUpdateStatus(SysCdn sysCdn, LoginUser loginUser) {
        sysCdn.setIsDelete(false);
        sysCdn.setUpdateUser(loginUser.getAccno());
        sysCdnMapper.updateByPrimaryKeySelective(sysCdn);

        return Constants.SUCCESS_MSG;
    }

    @Override
    public Page<SysCdn> getList(SysCdn sysCdn, RowBounds rowBounds) {
        return sysCdnMapperExt.getList(sysCdn, rowBounds);
    }
}