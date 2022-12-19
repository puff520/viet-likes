package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.Activity;
import com.likes.common.mybatis.entity.ActivityExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AgentReportMapper {

    Integer todayRegister(String superiorId, String beginTime, String endTime);


    Map<String, String> amoutTj(String superiorId, String beginTime, String endTime);


    Map<String, Object> taskTj(String superiorId, String beginTime, String endTime);

}
