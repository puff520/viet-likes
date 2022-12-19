package com.likes.common.service.member;

import com.github.pagehelper.PageInfo;
import com.likes.common.model.LoginUser;
import com.likes.common.model.SubAndTaskDto;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.member.*;
import com.likes.common.model.dto.report.DayReportDTO;
import com.likes.common.model.dto.report.MemberReportDto;
import com.likes.common.model.dto.report.TeamReportDto;
import com.likes.common.model.request.*;
import com.likes.common.model.response.AppTeamResponse;
import com.likes.common.mybatis.entity.BasRobotset;
import com.likes.common.mybatis.entity.MemLevelRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author lucien
 * @create 2020/8/19 19:51
 */
public interface MemberOtherService {


    PageInfo<MemberReportDTO> getReport(MemberReportRequest req);

    PageResult vipRecordList(VipRecordRequest req);

    Map<String, Object> register(UsersRequest req);

    boolean deleteForManager(Long id, String uerid);

    PageInfo<MemGoldChangeDTON> getMemberGoldChange(MemGoldChangeRequest req);

    MemberCountResultDTO getMemberCount();

    PageInfo<DayReportDTO> getDayReport(DayReportRequest req);

    PageInfo<TeamReportDto> getTeamReport(DayReportRequest req);

    MemberReportDto getMemberReportDto(String accno);

    AppTeamResponse appTeamList(FundsRequest req, String accno);

    AppTeamResponse appTeamReport(AppTeamReportRequest req, LoginUser loginUser);


}
