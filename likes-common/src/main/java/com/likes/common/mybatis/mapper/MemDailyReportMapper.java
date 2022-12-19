package com.likes.common.mybatis.mapper;

import com.likes.common.model.MemDailyReport;
import com.likes.common.model.dto.member.MemRelationshipDTO;
import com.likes.common.model.dto.report.DayReportDTO;
import com.likes.common.model.dto.report.MemberReportDto;
import com.likes.common.model.dto.report.TeamReportDto;
import com.likes.common.model.request.DayReportInfoRequest;
import com.likes.common.model.request.DayReportRequest;
import com.likes.common.model.response.AgentReportResponse;
import com.likes.common.mybatis.entity.MemRelationship;
import com.likes.common.mybatis.entity.MemSubInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MemDailyReportMapper extends Mapper<MemDailyReport> {
    int insertBatch(@Param("list") List<MemDailyReport> list);

    List<DayReportDTO> getDayReport(DayReportRequest req);

    TeamReportDto getTeamReport(DayReportRequest req);

    TeamReportDto getTeamReportInfo(DayReportInfoRequest req);

    List<MemRelationshipDTO> getMemRelationship(String refaccno);

    List<MemRelationshipDTO> getMemRelationshipAll();

    List<MemRelationshipDTO> getMemRleationshipByAcclogin(String acclogin);

    MemberReportDto getMemberReportDto(String accno);

    AgentReportResponse reportList(String accno);

    List<String> getDayGlodChangeAccno(String mobileno);

    @Select("SELECT mg.changetype,SUM(mg.quantity) as quantity FROM mem_goldchange mg WHERE create_time > DATE_FORMAT( CURDATE( ), '%Y-%m-%d %H:%i:%s' )   and mg.accno = #{accno} and mg.changetype in (1,7,12,101,102,103,100,200,203)" + " GROUP BY mg.changetype")
    List<Map<String, Object>> getQuantityByChangetype(@Param("accno") String accno);
}
