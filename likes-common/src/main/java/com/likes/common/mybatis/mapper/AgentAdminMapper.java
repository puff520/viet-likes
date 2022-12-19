package com.likes.common.mybatis.mapper;

import com.github.pagehelper.Page;
import com.likes.common.model.SubAndTaskDto;
import com.likes.common.model.dto.*;
import com.likes.common.model.dto.member.FundsResponse;
import com.likes.common.model.request.AgentAdminRequest;
import com.likes.common.model.request.AgentMemberOrderReq;
import com.likes.common.model.request.FundsRequest;
import com.likes.common.model.request.TeamRequest;
import com.likes.common.model.response.AgentAdminResponse;
import com.likes.common.model.response.AppTeamResponse;
import com.likes.common.model.response.TeamAdminResponse;
import com.likes.common.model.response.TeamResponse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface AgentAdminMapper {


    Integer addVipNum(String beginTime,String endTime);

    List<ChangeTypeData> changeTypeAmount(@Param("list") List<String> list , String beginTime, String endTime);

    BigDecimal rechargeAmount(@Param("list") List<String> list ,String beginTime,String endTime);

    BigDecimal withdrawAmount(@Param("list") List<String> list ,String beginTime,String endTime);

    BigDecimal subTaskBrokerage(@Param("list") List<String> list ,String beginTime,String endTime);

    BigDecimal buyVip(@Param("list") List<String> list  ,String beginTime,String endTime);

    Integer levelCount(@Param("accno")String accno, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    BigDecimal taskAmount(@Param("list") List<String> list ,String beginTime,String endTime);

    List<Map<String, String>> teamVipList(String accno);

    Page<TeamAdminResponse> teamAdminList(@Param("req") TeamRequest req, RowBounds rowBounds);

    Page<AgentAdminResponse> agentList(@Param("req") AgentAdminRequest req, RowBounds rowBounds);

    Page<AgentAdminResponse> agentSubList(@Param("req") AgentAdminRequest req, RowBounds rowBounds);

    List<String> teamNum(Long relaid, String beginTime, String endTime);

    Integer subNum(String accno, String beginTime, String endTime);

    Integer recMemNum(@Param("list") List<String> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    Integer cashMemNum(@Param("list") List<String> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    List<Map<String, Object>> subTimeOrderListByAccno(@Param("accno")String accno, @Param("beginTime") String beginTime,
                                                      @Param("endTime") String endTime, @Param("createTime") String createTime);

}
