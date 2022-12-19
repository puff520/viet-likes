package com.likes.common.service.sys.impl;

import com.likes.common.model.dto.sys.SysInfologDO;
import com.likes.common.mybatis.entity.SysInfolog;
import com.likes.common.mybatis.mapper.SysInfologMapper;
import com.likes.common.mybatis.mapperext.sys.SysInfologMapperExt;
import com.likes.common.service.sys.SysInfologService;
import com.likes.common.util.BaseUtil;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: SysInfologServiceImpl
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/6/19 19:12
 */
@Service
public class SysInfologServiceImpl implements SysInfologService {

    private static final Logger logger = LoggerFactory.getLogger(SysInfologServiceImpl.class);
    @Resource
    public HttpServletRequest request;
    @Resource
    private SysInfologMapper sysInfologMapper;
    @Resource
    private SysInfologMapperExt sysInfologMapperExt;

    @Override
    @Async
    public void insert(SysInfolog sysInfolog) {
        try {
            if (null != sysInfolog) {
                sysInfologMapper.insertSelective(sysInfolog);
            }
        } catch (Exception e) {
            logger.error("insert sysInfoLog error. sysInfolog:{}, msg:{}", sysInfolog, e.getMessage(), e);
        }

    }

    @Override
    public void insert(String accno, String systemName, String modelName, String optContent, Double longitude, Double latitude, String origin) {
        insert(accno, systemName, modelName, optContent, BaseUtil.getUserIp(request), request.getLocalAddr(), longitude, latitude, origin);
    }

    @Override
    public void insert(String accno, String systemName, String modelName, String optContent, String optIp, String serverIp, Double longitude, Double latitude, String origin) {
        SysInfolog sysInfolog = new SysInfolog();
        sysInfolog.setAccno(accno);
        sysInfolog.setSystemname(systemName);
        sysInfolog.setModelname(modelName);
        sysInfolog.setOptcontent(optContent);
        sysInfolog.setOptip(optIp);
        sysInfolog.setServerip(serverIp);
        sysInfolog.setLongitude(String.valueOf(longitude));
        sysInfolog.setLatitude(String.valueOf(latitude));
        sysInfolog.setOrginfo(origin);
        this.insert(sysInfolog);
    }

    @Override
    public Page<SysInfologDO> getList(SysInfolog req, RowBounds rowBounds) {
        Page<SysInfologDO> page = null;
        if (null != req) {
            page = sysInfologMapperExt.getList(req, rowBounds);
        }
        return null == page ? new Page<>() : page;
    }
}
