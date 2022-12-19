package com.likes.common.mybatis.mapperbean;


import com.likes.common.mybatis.entity.MemGoldchange;
import com.likes.common.mybatis.entity.MemberActivity;

import com.likes.common.mybatis.entity.MemberTopUp;
import com.likes.common.mybatis.entity.MemberWithdrawDeposit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MemberFundBeanMapper {

    /**
     * 查询用户的提现记录
     *
     * @param memberId 会员id
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Select("SELECT m.create_time as createTime, m.money, m.status, m.remark from member_withdraw_deposit m WHERE m.member_id = #{memberId} ORDER BY m.create_time DESC LIMIT #{pageNo},#{pageSize}")
    List<MemberWithdrawDeposit> pageWithdrawDeposit(@Param("memberId") Integer memberId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * 查询用户的充值记录
     *
     * @param memberId 会员id
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Select("SELECT m.create_time as createTime, m.money, m.status, m.remark from member_top_up m WHERE m.member_id = #{memberId} ORDER BY m.create_time DESC LIMIT #{pageNo},#{pageSize}")
    List<MemberTopUp> pageTopUp(@Param("memberId") Integer memberId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * 查询用户的活动记录
     *
     * @param memberId 会员id
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Select("SELECT m.create_time as createTime, m.money, m.status, m.title from member_activity m WHERE m.member_id = #{memberId} ORDER BY m.create_time DESC LIMIT #{pageNo},#{pageSize}")
    List<MemberActivity> pageActivity(@Param("memberId") Integer memberId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

//    /**
//     * 查询用户的帐变记录
//     * @param memberId 会员id
//     * @param pageNo
//     * @param pageSize
//     * @return
//     */
//    @Select("SELECT m.createdate, m.quantity, m.recgoldnum, m.changetype  from mem_goldchange m WHERE m.member_id = #{memberId} ORDER BY m.create_time DESC LIMIT #{pageNo},#{pageSize}")
//    List<MemGoldchange> pageBalanceChange(@Param("memberId") Integer memberId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
}
