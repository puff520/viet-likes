package com.likes.common.mybatis.mapperbean;


import com.likes.common.model.dto.order.AeBetOrderDTO;
import com.likes.common.model.dto.order.AgBetOrderDTO;
import com.likes.common.model.dto.order.EsBetOrderDTO;
import com.likes.common.model.dto.order.KyBetOrderDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ExternalBetReadBeanMapper {

    /**
     * ag
     *
     * @param account
     * @param userId
     * @param startTime
     * @param endTime
     * @param agAccount
     * @param gameType
     * @param billNo
     * @param gameCode
     * @param endMomey
     * @param stratMomey
     * @param round
     * @return
     */
    @Select("<script>SELECT count(*) as count,sum(t1.bet_amount) as totalBet,sum(t1.valid_bet_amount) as validBet,sum(t1.net_amount) as profitAndLoss FROM `ag_bet_order` t1,cpt_open_member t2,app_member t3 where"
            + " t1.player_name = t2.username and t2.member_id = t3.id and t2.type= 'T_AG' "
            + "<if test='userId != null'> and t3.id = #{userId}</if>"
            + "<if test='account != null'> and t3.account = #{account}</if>"
            + "<if test='stratMomey != null'> and t1.net_amount &gt;=  #{stratMomey}</if>"
            + "<if test='endMomey != null'> and t1.net_amount &lt;= #{endMomey}</if>"
            + "<if test='gameCode != null'> and t1.game_code = #{gameCode}</if>"
            + "<if test='billNo != null'> and t1.bill_no = #{billNo}</if>"
            + "<if test='gameType != null'> and t1.game_type = #{gameType}</if>"
            + "<if test='round != null'> and t1.round = #{round}</if>"
            + "<if test='agAccount != null'> and t1.player_name = #{agAccount}</if>"
            + "<if test='startTime != null'> and t1.bet_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.bet_time &lt;= #{endTime}</if>" + "</script>")
    Map<String, Object> agCount(@Param("account") String account, @Param("userId") Integer userId,
                                @Param("startTime") String startTime, @Param("endTime") String endTime,
                                @Param("stratMomey") Integer stratMomey, @Param("endMomey") Integer endMomey,
                                @Param("gameCode") String gameCode, @Param("billNo") String billNo, @Param("gameType") String gameType,
                                @Param("agAccount") String agAccount, @Param("round") String round);

    @Select("<script>SELECT distinct t1.id,t1.player_name as playerName,t1.bill_no as billNo,t1.agent_code as agentCode,t1.game_code as gameCode,t1.net_amount as netAmount,t4.round_name as roundName ,t5.pay_name payName,t6.game_name gameName,t1.table_code   tableCode,\r\n"
            + "t1.bet_amount as betAmount,t1.valid_bet_amount as validBetAmount,t1.flag flag,t1.game_type as gameType,t1.currency as currency,t1.bet_time as betTime,\r\n"
            + "t1.create_time as createTime,t1.login_ip as loginIp,t3.account as account,t3.id as memberId FROM `ag_bet_order` t1,cpt_open_member t2,app_member t3,ag_round t4,ag_pay_type  t5,ag_game t6 where"
            + " t1.player_name = t2.username and t2.member_id = t3.id and t1.round=t4.round and t1.game_type=t6.game_type and  t1.play_type = t5.pay_type and t2.type= 'T_AG'"
            + "<if test='userId != null'> and t3.id = #{userId}</if>"
            + "<if test='stratMomey != null'> and t1.net_amount &gt;=  #{stratMomey}</if>"
            + "<if test='endMomey != null'> and t1.net_amount &lt;= #{endMomey}</if>"
            + "<if test='gameCode != null'> and t1.game_code = #{gameCode}</if>"
            + "<if test='billNo != null'> and t1.bill_no = #{billNo}</if>"
            + "<if test='round != null'> and t1.round = #{round}</if>"
            + "<if test='gameType != null'> and t1.game_type = #{gameType}</if>"
            + "<if test='agAccount != null'> and t1.player_name = #{agAccount}</if>"
            + "<if test='account != null'> and t3.account = #{account}</if>"
            + "<if test='startTime != null'> and t1.bet_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.bet_time &lt;= #{endTime}</if>"
            + " ORDER BY t1.bet_time DESC limit #{pageNo},#{pageSize}</script>")
    List<AgBetOrderDTO> agList(@Param("account") String account, @Param("userId") Integer userId,
                               @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("pageNo") Integer pageNo,
                               @Param("pageSize") Integer pageSize, @Param("stratMomey") Integer stratMomey,
                               @Param("endMomey") Integer endMomey, @Param("gameCode") String gameCode, @Param("billNo") String billNo,
                               @Param("gameType") String gameType, @Param("agAccount") String agAccount, @Param("round") String round);

    /**
     * 开元
     *
     * @param account
     * @param userId
     * @param startTime
     * @param endTime
     * @param serverId
     * @param kindId
     * @param gameId
     * @return
     */
    @Select("<script>SELECT count(*) as count,sum(t1.all_bet) as totalBet,sum(t1.cell_score) as validBet,sum(t1.profit) as profitAndLoss FROM `ky_bet_order` t1,cpt_open_member t2,app_member t3 ,ky_kind t4,ky_server t5 where"
            + " SUBSTRING(t1.account,LOCATE('_',t1.account)+1) = t2.username and t2.member_id = t3.id and t1.kind_id=t4.kind_id and t1.server_id=t5.server_id"
            + "<if test='userId != null'> and t3.id = #{userId}</if>"
            + "<if test='account != null'> and t3.account = #{account}</if>"
            + "<if test='gameId != null'> and t3.game_id = #{gameId}</if>"
            + "<if test='startTime != null'> and t1.game_start_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.game_start_time &lt;= #{endTime}</if>"
            + "<if test='kindId != null'> and t4.kind_id = #{kindId}</if>"
            + "<if test='serverId != null'> and t5.server_id = #{serverId}</if>" + "</script>")
    Map<String, Object> kyCount(@Param("account") String account, @Param("userId") Integer userId,
                                @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("kindId") Integer kindId,
                                @Param("serverId") Integer serverId, @Param("gameId") String gameId);

    @Select("<script>SELECT distinct t1.game_id as gameId,t1.server_id serverId,t4.kind_name as gameName,t5.server_name as serverName,"
            + "t1.table_id as tableId,t1.chair_id as chairId,t1.cell_score as cellScore,t1.all_bet as allBet,\r\n"
            + "t1.profit as profit,t1.revenue as revenue,t1.game_start_time as gameStartTime,t1.game_end_time as gameEndTime,\r\n"
            + "t1.channel_id as channelId,t1.create_time as createTime,t1.account as kyAccount,t3.account as account,t3.id as memberId FROM `ky_bet_order` t1,cpt_open_member t2,app_member t3,ky_kind t4,ky_server t5 where"
            + " SUBSTRING(t1.account,LOCATE('_',t1.account)+1) = t2.username and t2.member_id = t3.id and t1.kind_id=t4.kind_id and t1.server_id=t5.server_id"
            + "<if test='userId != null'> and t3.id = #{userId}</if>"
            + "<if test='account != null'> and t3.account = #{account}</if>"
            + "<if test='kindId != null'> and t4.kind_id = #{kindId}</if>"
            + "<if test='serverId != null'> and t5.server_id = #{serverId}</if>"
            + "<if test='gameId != null'> and t3.game_id = #{gameId}</if>"
            + "<if test='startTime != null'> and t1.game_start_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.game_start_time &lt;= #{endTime}</if>"
            + " ORDER BY t1.game_start_time DESC  limit #{pageNo},#{pageSize}</script>")
    List<KyBetOrderDTO> kyList(@Param("account") String account, @Param("userId") Integer userId,
                               @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("pageNo") Integer pageNo,
                               @Param("pageSize") Integer pageSize, @Param("kindId") Integer kindId, @Param("serverId") Integer serverId,
                               @Param("gameId") String gameId);

    /**
     * 电竞
     *
     * @param account
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("<script>SELECT count(*) as count,sum(t1.total_amount) as totalBet,sum(t1.settlement_amount) as profitAndLoss FROM `es_bet_order` t1,cpt_open_member t2,app_member t3 where"
            + " t1.username = t2.username and t2.member_id = t3.id"
            + "<if test='userId != null'> and t3.id = #{userId}</if>"
            + "<if test='account != null'> and t3.account = #{account}</if>"
            + "<if test='startTime != null'> and t1.settlement_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.settlement_time &lt;= #{endTime}</if>" + "</script>")
    Map<String, Object> esCount(@Param("account") String account, @Param("userId") Integer userId,
                                @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("<script>SELECT distinct t1.id,t1.es_order_id as esOrderId,t1.total_amount as totalAmount,t1.odds_value as oddsValue,t1.can_win_amount as canWinAmount,t1.confirm_status as confirmStatus,t1.username as username,\r\n"
            + "t1.settlement_status as settlementStatus,t1.win_lose_state as winLoseState,t1.settlement_amount as settlementAmount,t1.settlement_time as settlementTime,t1.create_time as createTime,t3.account as account,t3.id as memberId FROM `es_bet_order` t1,cpt_open_member t2,app_member t3 where"
            + " t1.username = t2.username and t2.member_id = t3.id"
            + "<if test='userId != null'> and t3.id = #{userId}</if>"
            + "<if test='account != null'> and t3.account = #{account}</if>"
            + "<if test='startTime != null'> and t1.settlement_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.settlement_time &lt;= #{endTime}</if>"
            + " ORDER BY t1.settlement_time DESC limit #{pageNo},#{pageSize}</script>")
    List<EsBetOrderDTO> esList(@Param("account") String account, @Param("userId") Integer userId,
                               @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("pageNo") Integer pageNo,
                               @Param("pageSize") Integer pageSize);


    @Select("<script>SELECT count(*) as count,sum(t1.allbet) as totalBet,sum(t1.bet) as validBet,sum(t1.profit) as profitAndLoss FROM `ae_bet_order` t1,cpt_open_member t2,app_member t3 where"
            + " t1.uname = t2.username and t2.member_id = t3.id"
            + "<if test='userId != null'> and t3.id = #{userId}</if>"
            + "<if test='account != null'> and t3.account = #{account}</if>"
            + "<if test='battleId != null'> and t1.battle_id = #{battleId}</if>"
            + "<if test='orderNo != null'> and t1.order_no = #{orderNo}</if>"
            + "<if test='gameId != null'> and t1.game_id = #{gameId}</if>"
            + "<if test='roomId != null'> and t1.room_id = #{roomId}</if>"
            + "<if test='aeAccount != null'> and t1.player_name = #{aeAccount}</if>"
            + "<if test='startTime != null'> and t1.etime &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.etime &lt;= #{endTime}</if>" + "</script>")
    Map<String, Object> aeCount(@Param("account") String account, @Param("userId") Integer userId,
                                @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("gameId") String gameId,
                                @Param("orderNo") String orderNo, @Param("battleId") String battleId, @Param("roomId") String roomId, String aeAccount);


    @Select("<script>SELECT distinct t1.order_no as orderNo,t3.account as  account,t3.id as memberId,t1.stime as stime,t1.uname as  aeAccount,t4.game_name as gameName,t5.room_name as roomName ,t1.battle_id as battleId,t1.bet as  bet,t1.allbet as allbet,t1.profit as profit ,t1.etime as etime    FROM `ae_bet_order`    t1,cpt_open_member     t2,app_member t3,ae_game t4,ae_room t5 where"
            + " t1.uname = t2.username and t2.member_id = t3.id  and  t1.game_id = t4.game_id and  t1.room_id = t5.room_id"
            + "<if test='userId != null'> and t3.id = #{userId}</if>"
            + "<if test='account != null'> and t3.account = #{account}</if>"
            + "<if test='battleId != null'> and t1.battle_id = #{battleId}</if>"
            + "<if test='orderNo != null'> and t1.order_no = #{orderNo}</if>"
            + "<if test='gameId != null'> and t1.game_id = #{gameId}</if>"
            + "<if test='roomId != null'> and t1.room_id = #{roomId}</if>"
            + "<if test='aeAccount != null'> and t1.player_name = #{aeAccount}</if>"
            + "<if test='startTime != null'> and t1.etime &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.etime &lt;= #{endTime}</if>"
            + " ORDER BY t1.etime DESC limit #{pageNo},#{pageSize}</script>")
    List<AeBetOrderDTO> aeList(@Param("account") String account, @Param("userId") Integer userId,
                               @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("gameId") String gameId,
                               @Param("orderNo") String orderNo, @Param("battleId") String battleId, @Param("roomId") String roomId, String aeAccount, @Param("pageNo") Integer pageNo,
                               @Param("pageSize") Integer pageSize);
}
