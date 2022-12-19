package com.likes.common.mybatis.mapper;

import com.github.pagehelper.Page;
import com.likes.common.model.dto.AgentEveryData;
import com.likes.common.mybatis.entity.EveryDayReport;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface EveryDayReportMapper extends Mapper<EveryDayReport> {

    List<AgentEveryData> everyDayList(String beginTime, String endTime);


    Page<AgentEveryData> everyDayReport(String beginTime, String endTime, RowBounds rowBounds);

    List<String> agentAccnoList(Integer pageNo,Integer pageSize);

    Integer countAgentAccNo();




}
