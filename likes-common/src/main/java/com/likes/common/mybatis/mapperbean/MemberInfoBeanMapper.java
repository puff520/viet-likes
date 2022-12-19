package com.likes.common.mybatis.mapperbean;


import com.likes.common.model.vo.member.MemberLoginLogVO;
import com.likes.common.model.vo.member.MemberInfoVO;
import com.likes.common.mybatis.entity.MemberLoginLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MemberInfoBeanMapper {

    /**
     * 查询会员的账号信息
     *
     * @param memberId 会员id
     * @return
     */
    @Select("SELECT a.create_time,a.nickname_status,a.`account`,a.qq,a.phone, a.`sex`, a.heads, a.`real_name` as realName, v.`name` as vip, " +
            "a.`balance`, a.`nickname`,a.`wait_amount` waitAmount,  a.`birthday`, a.pay_password as payPassword, a.promotion_code as promotionCode, a.bet_amount as betAmount," +
            "a.`withdrawal_amount` withdrawalAmount, a.`no_withdrawal_amount` noWithdrawalAmount, a.`withdrawal_remainder` withdrawalRemainder,"+
            "a.pay_level_id payLevelId, a.user_type userType, a.room_status roomStatus " +
            "from `app_member` a " +
            "LEFT JOIN vip_grade v ON a.vip_id = v.id where a.id = #{memberId} and a.deleted = 0")
    MemberInfoVO getAccountInfo(@Param("memberId") Integer memberId);

    /**
     * 查询会员上一次的登录历史
     *
     * @param account
     * @return
     */
    @Select("SELECT m.login_time as loginTime, m.login_ip as loginIp, m.address from member_login_log m where m.account = #{account} ORDER BY m.login_time DESC LIMIT 1,1")
    MemberLoginLog getLastLoginLog(@Param("account") String account);

    /**
     * 查询会员的常用登陆地址
     *
     * @param memberId 会员id
     */
    @Select("SELECT COUNT(a.address) num , a.address " +
            "from (SELECT m.address from member_login_log m where m.member_id = #{memberId} ORDER BY m.login_time desc limit 20) a GROUP BY a.address order by num Desc LIMIT 1")
    Map<String, Object> getOftenLoginAddress(@Param("memberId") Integer memberId);

    @Select("SELECT m.login_time as loginTime, m.client, m.address, m.state from member_login_log m where m.member_id = #{memberId} ORDER BY m.login_time DESC LIMIT #{pageNo},#{pageSize}")
    List<MemberLoginLogVO> pageLoginLog(@Param("memberId") Integer memberId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    @Select("SELECT balance FROM app_member where id = #{memberId}")
    BigDecimal getMebBalanceById(@Param("memberId") Integer memberId);

    @Update(" update mem_baseinfo set logintype = #{loginType} where accno = #{accno} ")
    void updateMembaseInfoLoginType(@Param("accno") String accno,@Param("loginType") Integer loginType);
}
