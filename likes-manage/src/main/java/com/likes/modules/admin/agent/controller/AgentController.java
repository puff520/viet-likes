package com.likes.modules.admin.agent.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.SysAgentinfoReq;
import com.likes.common.model.request.SysRecordRequest;
import com.likes.common.mybatis.entity.SysAgentinfo;
import com.likes.common.service.member.MemRelationshipService;
import com.likes.common.service.sys.SysRecordService;
import com.likes.common.util.LogUtils;
import com.likes.modules.admin.agent.service.AgentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 阿布
 * <p>
 * 代理设置
 */

@Controller
@RequestMapping(value = "/agent")
public class AgentController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private AgentService agentService;
    @Resource
    private SysRecordService getReport;
    @Resource
    private MemRelationshipService memRelationshipService;

    @ResponseBody
    @RequestMapping(name = "代理设置", value = "/getList", method = RequestMethod.GET)
    public ResultInfo getList(SysAgentinfoReq req, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.agentService.getList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getList,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("代理设置出错");
            logger.error("{}.getList,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "代理设置新增", value = "/doAdd", method = RequestMethod.POST)
    public ResultInfo doAdd(SysAgentinfo req) {
        ResultInfo response = ResultInfo.ok();
        try {
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

            req.setAgentid(null);
            this.agentService.doEdt(req, this.getLoginAdmin());
            LoginUser loginUser = getLoginAdmin();
            LogUtils.logUserModifyOperates(getClass().getName() + ".doAdd", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doAdd,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("代理设置新增出错");
            logger.error("{}.doAdd,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "代理设置编辑", value = "/doEdt", method = RequestMethod.POST)
    public ResultInfo doEdt(SysAgentinfo req) {
        ResultInfo response = ResultInfo.ok();
        try {
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

            if (req.getAgentid() == null) {
                response.setResultInfo(StatusCode.LIVE_ERROR_21011.getCode(), "无效参数");
            }
            this.agentService.doEdt(req, this.getLoginAdmin());
            LoginUser loginUser = getLoginAdmin();
            LogUtils.logUserModifyOperates(getClass().getName() + ".doEdt", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doEdt,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("代理设置编辑出错");
            logger.error("{}.doEdt,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "代理设置删除", value = "doDel", method = RequestMethod.GET)
    public ResultInfo doDel(Long agentid) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (agentid == null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "无效参数");
            }
            this.agentService.doDel(agentid, this.getLoginAdmin());
            LoginUser loginUser = getLoginAdmin();
            LogUtils.logUserModifyOperates(getClass().getName() + ".doDel", "agentid=" + agentid, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doDel,失败:{},params:{}", getClass().getName(), e.getMessage(), "agentid=" + agentid, e);
        } catch (Exception e) {
            response = ResultInfo.error("代理设置删除出错");
            logger.error("{}.doDel,出错:{},params:{}", getClass().getName(), e.getMessage(), "agentid=" + agentid, e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "会员关系树", value = "agentTree", method = RequestMethod.GET)
    public ResultInfo agentTree(String accno,String email){
        if (StringUtils.isBlank(accno)) {
            accno = "puff";
        }
        return ResultInfo.ok( memRelationshipService.getMyPromotionListInfo(accno,email));
    }

    @ResponseBody
    @RequestMapping(name = "会员关系转移", value = "moveAgent", method = RequestMethod.POST)
    public ResultInfo moveAgent(String source,String target,String password){

        return ResultInfo.ok(memRelationshipService.moveAgent(source,target,password,getLoginAdmin()));
    }

    @ResponseBody
    @RequestMapping(name = "代理关系转移记录", value = "/moveList", method = RequestMethod.GET)
    public ResultInfo moveList(SysAgentinfoReq req, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.agentService.getList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getList,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("代理设置出错");
            logger.error("{}.getList,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }



    @ResponseBody
    @RequestMapping(name = "会员迁移记录", value = "/getLst", method = RequestMethod.GET)
    public ResultInfo report(SysRecordRequest req){
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            response = ResultInfo.ok(getReport.getLst(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.list分页查询会员迁移记录失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("分页会员迁移记录出错");
            logger.error("{}.list分页查询会员迁移记录出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/list耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

}

