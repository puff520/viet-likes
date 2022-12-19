package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.MemberCreditDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MemberCreditDetailMapper extends Mapper<MemberCreditDetail> {
    // 获取积分流水
    List<MemberCreditDetail> getMemberCreditDeailList(String accno);

    // 获取用户当前积分
    Integer getMemberCredit(@Param("accno") String accno);
}
