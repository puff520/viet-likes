package com.likes.common.model.dto.member;

import com.likes.common.model.dto.MemDoTaskDTO;

import java.util.List;

public class MemberCountResultDTO {
    private Integer vipSum; //vip总数
    private List<MemberCountDTO> memberCountDTO;// 今日会员等级
    private MemDoTaskDTO memDoTaskDTO; //站点内任务信息
    private List<MemberCountStatisticsDTO> memberCountStatisticsDTOS; //站内统计信息
    private MemberCountRegisterDTO memberCountRegisterDTO; //注册统计

    public List<MemberCountDTO> getMemberCountDTO() {
        return memberCountDTO;
    }

    public void setMemberCountDTO(List<MemberCountDTO> memberCountDTO) {
        this.memberCountDTO = memberCountDTO;
    }

    public MemDoTaskDTO getMemDoTaskDTO() {
        return memDoTaskDTO;
    }

    public void setMemDoTaskDTO(MemDoTaskDTO memDoTaskDTO) {
        this.memDoTaskDTO = memDoTaskDTO;
    }

    public Integer getVipSum() {
        return vipSum;
    }

    public void setVipSum(Integer vipSum) {
        this.vipSum = vipSum;
    }



    public List<MemberCountStatisticsDTO> getMemberCountStatisticsDTOS() {
        return memberCountStatisticsDTOS;
    }

    public void setMemberCountStatisticsDTOS(List<MemberCountStatisticsDTO> memberCountStatisticsDTOS) {
        this.memberCountStatisticsDTOS = memberCountStatisticsDTOS;
    }

    public MemberCountRegisterDTO getMemberCountRegisterDTO() {
        return memberCountRegisterDTO;
    }

    public void setMemberCountRegisterDTO(MemberCountRegisterDTO memberCountRegisterDTO) {
        this.memberCountRegisterDTO = memberCountRegisterDTO;
    }
}
