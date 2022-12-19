package com.likes.common.mybatis.mapperext.tra;

import com.likes.common.model.dto.member.CountWithdrawalDO;
import com.likes.common.model.dto.member.MemAgentDetailResp;
import com.likes.common.model.dto.member.MemAgentResp;
import com.likes.common.model.request.AgentDetailReq;
import com.likes.common.model.request.SysAgentinfoReq;
import com.likes.common.model.response.TraAgentclearingResp;
import com.likes.common.model.vo.member.ManageAgentDetailVO;
import com.likes.common.model.vo.member.ManageAgentVO;
import com.likes.common.mybatis.entity.TraAgentclearing;
import com.likes.common.mybatis.entity.TraOrderinfom;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.Date;
import java.util.List;

@Mapper
public interface TraAgentclearingMapperExt {


    Page<ManageAgentVO> getAgentList(SysAgentinfoReq req, RowBounds page);

    Page<ManageAgentDetailVO> getDetail(SysAgentinfoReq req, RowBounds page);

    Page<MemAgentResp> getNextList(SysAgentinfoReq req, RowBounds page);

    Page<MemAgentResp> getNextDetail(SysAgentinfoReq req, RowBounds page);

    Page<MemAgentDetailResp> agentDetailList(@Param("req") AgentDetailReq req,@Param("list")  List<String> list, RowBounds page);

    /**
     * 插入代理充值统计初始数据
     *
     * @return
     */
    int doInsertDayAgent();

    /**
     * 处理代理结算关联
     *
     * @return
     */
    int doUpdateDayAgent();

    /**
     * 结算
     *
     * @return
     */
    int doUpdateDayClearing();

    /**
     * 获取打款列表
     *
     * @return
     */
    List<TraAgentclearing> getPayList(@Param("createTime") Date createTime);

    int insertPayAgent(TraOrderinfom req);

    MemAgentResp getAgentReport(SysAgentinfoReq req);

    List<CountWithdrawalDO> countWithdrawal(@Param("refaccno") String refaccno);
}
