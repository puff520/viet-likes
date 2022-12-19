package com.likes.common.service.sys.impl;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.mybatis.entity.SysErrorlog;
import com.likes.common.mybatis.mapper.SysErrorlogMapper;
import com.likes.common.mybatis.mapperext.sys.SysErrorlogMapperExt;
import com.likes.common.service.sys.SysErrorlogService;
import com.likes.common.util.BaseUtil;
import com.likes.common.util.StringUtils;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: SysErrorlogServiceImpl
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/6/19 20:17
 */
@Service
public class SysErrorlogServiceImpl implements SysErrorlogService {

    private static final Logger logger = LoggerFactory.getLogger(SysErrorlogServiceImpl.class);
    @Resource
    private HttpServletRequest request;
    @Resource
    private SysErrorlogMapper sysErrorlogMapper;
    @Resource
    private SysErrorlogMapperExt sysErrorlogMapperExt;

    @Override
    @Async
    public void insert(SysErrorlog sysErrorlog) {
        try {
            if (null != sysErrorlog) {
                sysErrorlogMapper.insertSelective(sysErrorlog);
            }
        } catch (Exception e) {
            logger.error("insert sysErrorLog error. info:{} error:{}", JSONObject.toJSONString(sysErrorlog), e.getMessage(), e);
        }
    }

    @Override
    public void insert(String systemname, String modulename, String content, String optuser, String level, String orginfo) {
        SysErrorlog sysErrorlog = new SysErrorlog();
        sysErrorlog.setSystemname(systemname);
        sysErrorlog.setModelname(modulename);
        sysErrorlog.setOptcontent(content);
        sysErrorlog.setOptuser(optuser);
        sysErrorlog.setLevel(level);
        sysErrorlog.setServerip(request.getLocalAddr());
        sysErrorlog.setOptip(BaseUtil.getUserIp(request));
        sysErrorlog.setOrginfo(orginfo);
        this.insert(sysErrorlog);
    }

    @Override
    public SysErrorlog selectByMd5(String orginfo) {
        if (StringUtils.isEmpty(orginfo)) {
            return null;
        }
        return sysErrorlogMapperExt.selectByMd5(orginfo);
    }

    @Override
    public Page<SysErrorlog> getList(SysErrorlog req, RowBounds rowBounds) {
        Page<SysErrorlog> page = null;
        if (null != req) {
            page = sysErrorlogMapperExt.getList(req, rowBounds);
        }
        return null == page ? new Page<>() : page;
    }
}
