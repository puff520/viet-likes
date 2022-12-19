package com.likes.common.mybatis.mapper;

import com.github.pagehelper.Page;
import com.likes.common.model.SubAndTaskDto;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.dto.AgentLineData;
import com.likes.common.model.dto.AgentMemberDTO;
import com.likes.common.model.dto.PromotionDo;
import com.likes.common.model.dto.member.FundsResponse;
import com.likes.common.model.request.*;
import com.likes.common.model.response.AppTeamResponse;
import com.likes.common.model.response.TeamBo;
import com.likes.common.model.response.TeamResponse;
import com.likes.common.mybatis.entity.Activity;
import com.likes.common.mybatis.entity.ActivityExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface AgentMapper {

    Page<AgentMemberDTO> memList(@Param("req") AgentMemberOrderReq req, RowBounds rowBounds);

    @Select("SELECT count(1) from mem_relationship mr WHERE mr.refaccno = #{accno} ")
    Integer countSubNum(@Param("accno") String accno);

    @Select("SELECT mr.accno from mem_relationship mr WHERE mr.refaccno = #{accno}   and mr.create_time BETWEEN #{beginTime} and #{endTime} ")
    List<String> subAccNoList(@Param("accno") String accno, @Param("beginTime")String beginTime,@Param("endTime") String endTime);

    Integer levelCount(@Param("accno")String accno, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    Integer taskNum(@Param("list") List<String> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    List<Map<String, Object>> subTimeOrderList(@Param("list") List<String> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    List<Map<String, Object>> subTimeOrderListByAccno(@Param("accno")String accno, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    Integer subRecMemNum(@Param("list") List<String> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    Page<FundsResponse> fundsList(@Param("req") FundsRequest req,RowBounds rowBounds);

    Page<TeamResponse> teamList(@Param("req") TeamRequest req, RowBounds rowBounds);

    Page<TeamResponse> adminTeamList(@Param("req") TeamRequest req, RowBounds rowBounds);

    Page<TeamResponse> team2List(@Param("req") TeamRequest req,  List<String> list, RowBounds rowBounds);

    List<AppTeamResponse> appTeamList(FundsRequest req);

    List<String> teamAddMem(@Param("accno")String accno, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

   BigDecimal memBalance(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    Integer teamTaskNum(@Param("accno")String accno, @Param("beginTime") String beginTime, @Param("endTime") String endTime);


    List<PromotionDo> agentTree(String accno);

    List<SubAndTaskDto> subTaskList(List<String> list);

    List<String> subNum(@Param("list") List<String> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    List<String> subNumNotTime(@Param("list") List<String> list);

    Integer subNum2(@Param("accno")String accno);

    List<String> todayAddSubNum(@Param("accno")String accno, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    Integer todayTaskNum(@Param("list") List<String> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    Integer todaySubmitTaskNum(@Param("accno")String accno, @Param("timeStr")String timeStr );
    Integer memTodayTaskNum(@Param("accno")String accno,@Param("timeStr")String timeStr);

//    List<String> level3SubAccList(String accno);

    List<String> level1SubAccList(String accno);
    List<String> level2SubAccList(String accno);

    List<String> level3SubAccList(String accno);
    List<String> levelAllSubAccList(String accno);
    Page<AgentLineData> agentLineList(String beginTime, String endTime, RowBounds rowBounds);

}
