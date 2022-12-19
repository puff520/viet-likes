package com.likes.modules.admin.agent.service;

import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.AgentData;
import com.likes.common.model.dto.AgentEveryData;
import com.likes.common.model.dto.AgentLineData;
import com.likes.common.model.dto.order.TraOrderinfomDTO;
import com.likes.common.model.request.*;
import com.likes.common.mybatis.entity.TraAgentclearing;
import com.likes.common.service.BaseService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 阿布 代理报表
 */
public interface AgentReportService extends BaseService {

    /**
     * 获取代理人列表
     *
     * @param req
     * @param page
     * @return
     */
    PageResult getAgentList(SysAgentinfoReq req, PageBounds page) throws BusinessException;

    /**
     * 获取代理人报表明细列表
     *
     * @param req
     * @param page
     * @return
     */
    PageResult getDetail(SysAgentinfoReq req, PageBounds page) throws BusinessException;

    /**
     * 获取下级用户报表列表
     *
     * @param req
     * @param page
     * @return
     */
    PageResult getNextList(SysAgentinfoReq req, PageBounds page) throws BusinessException;

    /**
     * 获取下级用户报表详细
     *
     * @param req
     * @param page
     * @return
     */
    PageResult getNextDetail(SysAgentinfoReq req, PageBounds page) throws BusinessException;

    PageResult teamTradingList(AgentDetailReq req, PageBounds page) throws BusinessException;

//    /**
//     * 代理日结算
//     *
//     * @throws BusinessException
//     */
//    void doDayExport() throws BusinessException;

    /**
     * 代理日结算-优化
     *
     * @throws BusinessException
     */
    String doDayExportNew(String date, String type) throws BusinessException;

    void insertTraAgentclearing(List<TraOrderinfomDTO> orderInfoList, BigDecimal withdraw, Integer newUsers, String refaccno, Date createDate, String type, String yesterdayStartDate) throws BusinessException;

    void insertDayExportNew(TraAgentclearing ta, String type) throws BusinessException;

    PageResult getUserWithdraw(SysAgentinfoReq req, PageBounds page) throws BusinessException;


    AgentData dataList(AgentOrderReq req, PageBounds page);

    PageResult agentLineList(AgentLineReq req, PageBounds page);

    PageResult everyDayList(AgentOrderReq req, PageBounds page);

    PageResult teamReport(TeamRequest req, PageBounds page);

    PageResult agentList(AgentAdminRequest req, PageBounds page);
}
