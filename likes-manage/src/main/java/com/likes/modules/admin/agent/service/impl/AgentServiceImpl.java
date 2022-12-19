package com.likes.modules.admin.agent.service.impl;

import com.likes.common.model.SysRecord;
import com.likes.common.mybatis.mapper.SysRecordMapper;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.SysAgentinfoReq;
import com.likes.common.mybatis.entity.SysAgentinfo;
import com.likes.common.mybatis.mapper.SysAgentinfoMapper;
import com.likes.common.mybatis.mapperext.sys.SysAgentinfoMapperExt;
import com.likes.common.service.sys.SysRecordService;
import com.likes.modules.admin.agent.service.AgentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 阿布
 */

@Service
public class AgentServiceImpl extends BaseServiceImpl implements AgentService {

    @Resource
    private SysAgentinfoMapper sysAgentinfoMapper;
    @Resource
    private SysAgentinfoMapperExt sysAgentinfoMapperExt;

    @Override
    public PageResult getList(SysAgentinfoReq req, PageBounds page) {
        return PageResult.getPageResult(page, this.sysAgentinfoMapperExt.getList(req, page.toRowBounds()));
    }


    @Override
    public void doEdt(SysAgentinfo req, LoginUser user) throws BusinessException {
        if (StringUtils.isEmpty(req.getAgentname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "请填写推广级别名称");
        }
        if (req.getMinamt() == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21002.getCode(), "请填写业绩最低值");
        }
        if (req.getMaxamt() == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21003.getCode(), "请填写业绩最高值");
        }
        if (req.getCommission() == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21004.getCode(), "请填写返佣比值");
        }
        if (req.getCommission().floatValue() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21005.getCode(), "请填写正确返佣比值");
        }
        if (req.getSortby() == 0) {
            req.setSortby(0);
        }
        req.setUpdateUser(user.getAccno());
        if (req.getAgentid() == null) {
            req.setCreateUser(user.getAccno());
            this.sysAgentinfoMapper.insertSelective(req);
        } else {
            this.sysAgentinfoMapper.updateByPrimaryKeySelective(req);
        }
    }

    @Override
    public void doDel(Long agentid, LoginUser user) throws BusinessException {
        if (agentid == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "无效参数");
        }
        SysAgentinfo sa = this.sysAgentinfoMapper.selectByPrimaryKey(agentid);
        if (sa == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21002.getCode(), "该代理级别不存在或已被删除");
        }
        sa.setUpdateUser(user.getAccno());
        sa.setIsDelete(true);
        this.sysAgentinfoMapper.updateByPrimaryKey(sa);
    }

}
