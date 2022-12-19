package com.likes.common.mybatis.mapperbean;

import com.likes.common.model.PlayModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BetOrderBeanMapper {

    /**
     * 查询玩法的名称
     *
     * @param playIds 玩法id的集合
     * @return
     */
    @Select("<script>SELECT a.play_tag_id as id, ifnull(CONCAT(b.name,'/',a.name),a.`name`) as name FROM lottery_play a  left join  lottery_play b on  b.id = a.parent_id where a.play_tag_id in <foreach item='playId' index='index' collection='playIds' open='(' separator=',' close=')'> #{playId} </foreach> </script>")
    List<PlayModel> getPlayName(@Param("playIds") List<Integer> playIds);

    /**
     * 计算有效投注
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @Select("SELECT IFNULL(SUM(o.bet_amount), 0.00) FROM `order_bet_record` o WHERE o.`tb_status` in ('WIN','NO_WIN','HE') and o.`type` = 0 AND o.`create_time` >= #{startTime} AND o.`create_time` <= #{endTime}")
    BigDecimal countTotalValidBet(@Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("SELECT IFNULL(count(o.id), 0) FROM order_bet_record o WHERE o.is_delete = 0  AND o.create_time >= #{startTime} AND o.create_time <= #{endTime}")
    Integer countTotalBet(@Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("SELECT IFNULL(count(o.id), 0) FROM order_bet_record o WHERE o.is_delete = 0 and o.type = 0  ${sb} ")
    Integer countTotalBets(@Param("sb") String sb);


    @Select("SELECT IFNULL(SUM(o.bet_amount), 0.00) FROM `order_bet_record` o WHERE o.`tb_status` in ('WIN','NO_WIN','HE') and o.`type` = 0 ${sb}")
    BigDecimal countTotalValids(@Param("sb") String sb);

    @Select("SELECT IFNULL(SUM(o.amount), 0.00) FROM mem_goldchange o WHERE o.is_delete = 0 and o.changetype = 1  ${sb}")
    BigDecimal countTotalRecharge(@Param("sb") String sb);

    @Select("SELECT IFNULL(SUM(o.amount), 0.00) FROM mem_goldchange o WHERE o.is_delete = 0 and o.changetype = 12  ${sb}")
    BigDecimal countWithdrawals(@Param("sb") String sb);

    @Select("SELECT IFNULL(SUM(o.amount), 0.00) FROM mem_goldchange o where o.accno in (select DISTINCT accno from mem_goldchange WHERE is_delete = 0 and changetype = 12 )  and o.changetype = 12 and o.is_delete = 0  ${sb}")
    BigDecimal countTotalWithdrawals(@Param("sb") String sb);


    /**
     * 查询某彩种，某玩法，某期号下 的投注数据： setting_id,bet_number,bet_count,bet_amount
     *
     * @param lotteryId 彩种
     * @param playId    玩法
     * @param issue     期号
     * @return
     */
    @Select("select setting_id,bet_number,bet_count,bet_amount from order_bet_record where order_id in (select id from order_record where issue = #{issue}) and lottery_id = #{lotteryId} and play_id=#{playId};")
    List<Map<String, Object>> selectOrderBetRecordMessage(@Param("lotteryId") Integer lotteryId, @Param("playId") Integer playId, @Param("issue") String issue);

    /**
     * 计算中奖总额
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @Select("SELECT IFNULL(SUM(o.`win_amount`), 0.00) FROM `order_bet_record` o WHERE o.`tb_status` = 'WIN' AND o.`type` = 0 AND o.`create_time` >= #{startTime} AND o.`create_time` <= #{endTime}")
    BigDecimal countTotalWin(@Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("SELECT IFNULL(SUM(o.`win_amount`), 0.00) FROM `order_bet_record` o WHERE o.`tb_status` = 'WIN' AND o.`create_time` >= #{startTime} AND o.`create_time` <= #{endTime}")
    BigDecimal countWin(@Param("sb") String sb);

    @Select("SELECT IFNULL(SUM(o.`win_amount`), 0.00) FROM `order_bet_record` o WHERE o.`tb_status` = 'WIN' and o.`type` = 0 ${sb}")
    BigDecimal countTotalWins(@Param("sb") String sb);

    /**
     * 计算返点
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    // @Select("SELECT IFNULL(SUM(o.`back_amount`), 0.00) FROM `order_bet_record` o WHERE o.`tb_status` in ('WIN','NO_WIN','HE')  AND o.`create_time` >= #{startTime} AND o.`create_time` <= #{endTime}")
    @Select("SELECT ifnull(sum(o.quantity),0.00) from mem_goldchange o where o.changetype = 35 and o.is_delete = 0 and o.create_time >= #{startTime} and o.create_time <= #{endTime}")
    BigDecimal countTotalBackWater(@Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("SELECT IFNULL(SUM(o.`back_amount`), 0.00) FROM `order_bet_record` o WHERE o.`tb_status` in ('WIN','NO_WIN','HE')  AND ${sb}")
    BigDecimal countTotalWater(@Param("sb") String sb);

    @Select("SELECT ifnull(sum(o.quantity),0.00) from mem_goldchange o where o.changetype = 35 and o.is_delete = 0  ${sb}")
    BigDecimal countTotalBackWaters(@Param("sb") String sb);

    @Select("select IFNULL(COUNT(o.memid), 0) from mem_baseinfo o where o.freeze_status = 0 and o.is_delete = 0 and o.logintype =1 ${time}")
    Integer countRegistered(@Param("time") String time);

    @Select("SELECT IFNULL(COUNT(DISTINCT o.accno), 0) FROM mem_goldchange o WHERE o.is_delete = 0  and o.changetype = 1 ${time}")
    Integer countTotalRechargeNum(@Param("time") String time);

    @Select("SELECT IFNULL(o.goldchangid,0) as id FROM mem_goldchange o where o.changetype =1 and o.is_delete = 0 ${time} GROUP BY o.accno ")
    List<Integer> countFirstDeposit();

    @Select("SELECT IFNULL(sum(o.amount),0.00) FROM mem_goldchange o  where o.changetype =1 and o.is_delete = 0 ${time} ")
    BigDecimal countFirstDepositAmount(@Param("time") String time);

}
