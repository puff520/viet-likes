package com.likes.common.mybatis.mapperbean;


import com.likes.common.model.dto.game.AgFashBetOrderDTO;
import com.likes.common.model.dto.game.DbBetOrderDTO;
import com.likes.common.model.dto.game.EsBetOrderDTO;
import com.likes.common.model.dto.game.AeBetOrderDTO;
import com.likes.common.model.dto.game.AgBetOrderDTO;
import com.likes.common.model.dto.game.MgBetOrderDTO;
import com.likes.common.model.dto.order.KyBetOrderDTO;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ExternalBetBeanMapper {

    /**
     * ag
     */
    @Select("<script>SELECT COUNT(DISTINCT t1.player_name) as userNumber,count(*) as count,sum(t1.bet_amount) as totalBet,sum(t1.valid_bet_amount) as validBet,sum(t1.net_amount) as profitAndLoss FROM `ag_bet_order` t1,mem_baseinfo t2 where"
            + "  t1.user_id = t2.memid  "
            + "<if test='nickname != null'> and t2.nickname = #{nickname}</if>"
            + "<if test='mobileno != null'> and t2.mobileno = #{mobileno}</if>"
            + "<if test='uniqueId != null'> and t2.unique_id = #{uniqueId}</if>"
            + "<if test='gameCode != null'> and t1.game_code = #{gameCode}</if>"
            + "<if test='billNo != null'> and t1.bill_no = #{billNo}</if>"
            + "<if test='gameType != null'> and t1.game_type = #{gameType}</if>"
            + "<if test='round != null'> and t1.round = #{round}</if>"
            + "<if test='agAccount != null'> and t1.player_name = #{agAccount}</if>"
            + "<if test='startTime != null'> and t1.bet_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.bet_time &lt;= #{endTime}</if>"
            + "<if test='stratMomey != null'> and t1.net_amount &gt;=  #{stratMomey}</if>"
            + "<if test='endMomey != null'> and t1.net_amount &lt;= #{endMomey}</if>" + "</script>")
    Map<String, Object> agCount(@Param("nickname") String nickname, @Param("mobileno") String mobileno, @Param("startTime") String startTime,
                                @Param("endTime") String endTime, @Param("stratMomey") Integer stratMomey, @Param("endMomey") Integer endMomey,
                                @Param("gameCode") String gameCode, @Param("billNo") String billNo, @Param("gameType") String gameType,
                                @Param("agAccount") String agAccount, @Param("round") String round, @Param("uniqueId") String uniqueId);


    @Select("<script>SELECT distinct t1.id,t1.player_name as playerName,t1.bill_no as billNo,t1.agent_code as agentCode,t1.game_code as gameCode,t1.net_amount as netAmount,t1.round_name as roundName ,t1.pay_name payName,t1.game_name gameName,t1.table_code   tableCode,"
            + "t1.bet_amount as betAmount,t1.valid_bet_amount as validBetAmount,t2.unique_id as uniqueId,t1.flag flag,t1.game_type as gameType,t1.currency as currency,t1.bet_time as betTime,"
            + "t1.create_time as createTime,t1.login_ip as loginIp,t2.nickname as nickname,t2.mobileno as mobileno,t2.memid as memberId FROM `ag_bet_order` t1, mem_baseinfo t2 where"
            + " t1.user_id = t2.memid "
            + "<if test='nickname != null'> and t2.nickname = #{nickname}</if>"
            + "<if test='mobileno != null'> and t2.mobileno = #{mobileno}</if>"
            + "<if test='uniqueId != null'> and t2.unique_id = #{uniqueId}</if>"
            + "<if test='gameCode != null'> and t1.game_code = #{gameCode}</if>"
            + "<if test='billNo != null'> and t1.bill_no = #{billNo}</if>"
            + "<if test='gameType != null'> and t1.game_type = #{gameType}</if>"
            + "<if test='round != null'> and t1.round = #{round}</if>"
            + "<if test='agAccount != null'> and t1.player_name = #{agAccount}</if>"
            + "<if test='startTime != null'> and t1.bet_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.bet_time &lt;= #{endTime}</if>"
            + "<if test='stratMomey != null'> and t1.net_amount &gt;=  #{stratMomey}</if>"
            + "<if test='endMomey != null'> and t1.net_amount &lt;= #{endMomey}</if>"
            + " ORDER BY t1.bet_time DESC limit #{pageNo},#{pageSize}</script>")
    List<AgBetOrderDTO> agList(@Param("nickname") String nickname, @Param("mobileno") String mobileno, @Param("startTime") String startTime,
                               @Param("endTime") String endTime, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
                               @Param("stratMomey") Integer stratMomey, @Param("endMomey") Integer endMomey,
                               @Param("gameCode") String gameCode, @Param("billNo") String billNo, @Param("gameType") String gameType,
                               @Param("agAccount") String agAccount, @Param("round") String round, @Param("uniqueId") String uniqueId);

    /**
     * 开元
     *
     * @param nickname ky_bet_order
     */
    @Select("<script>SELECT   COUNT(DISTINCT t1.account) as userNumber, count(*) as count,sum(t1.all_bet) as totalBet,sum(t1.cell_score) as validBet,sum(t1.profit) as profitAndLoss,t2.nickname as nickname,t2.mobileno as mobileno FROM `ky_bet_order` t1,mem_baseinfo t2  where"
            + " t1.user_id = t2.memid  "
            + "<if test='nickname != null'> and t2.nickname = #{nickname}</if>"
            + "<if test='mobileno != null'> and t2.mobileno = #{mobileno}</if>"
            + "<if test='uniqueId != null'> and t2.unique_id = #{uniqueId}</if>"
            + "<if test='gameId != null'> and t1.game_id = #{gameId}</if>"
            + "<if test='kindId != null'> and t1.kind_id = #{kindId}</if>"
            + "<if test='serverId != null'> and t1.server_id = #{serverId}</if>"
            + "<if test='startTime != null'> and t1.game_start_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.game_start_time &lt;= #{endTime}</if>" + "</script>")
    Map<String, Object> kyCount(@Param("nickname") String nickname, @Param("mobileno") String mobileno, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("kindId") Integer kindId,
                                @Param("serverId") Integer serverId, @Param("gameId") String gameId, @Param("uniqueId") String uniqueId);

    @Select("<script>SELECT distinct t1.game_id as gameId,t1.server_id serverId,t1.kind_name as gameName,t1.server_name as serverName,"
            + "t1.table_id as tableId,t1.chair_id as chairId,t1.cell_score as cellScore,t1.all_bet as allBet,\r\n"
            + "t1.profit as profit,t1.revenue as revenue,t2.unique_id as uniqueId,t1.game_start_time as gameStartTime,t1.game_end_time as gameEndTime,\r\n"
            + "t1.channel_id as channelId,t1.create_time as createTime,t1.account as kyAccount,t2.nickname as nickname,t2.mobileno as mobileno,t2.memid as memberId FROM `ky_bet_order` t1,mem_baseinfo t2 where  t1.user_id = t2.memid "
            + "<if test='nickname != null'> and t2.nickname = #{nickname}</if>"
            + "<if test='mobileno != null'> and t2.mobileno = #{mobileno}</if>"
            + "<if test='uniqueId != null'> and t2.unique_id = #{uniqueId}</if>"
            + "<if test='kindId != null'> and t1.kind_id = #{kindId}</if>"
            + "<if test='serverId != null'> and t1.server_id = #{serverId}</if>"
            + "<if test='gameId != null'> and t1.game_id = #{gameId}</if>"
            + "<if test='startTime != null'> and t1.game_start_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.game_start_time &lt;= #{endTime}</if>"
            + " ORDER BY t1.game_start_time DESC  limit #{pageNo},#{pageSize}</script>")
    List<KyBetOrderDTO> kyList(@Param("nickname") String nickname, @Param("mobileno") String mobileno, @Param("startTime") String startTime,
                               @Param("endTime") String endTime, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
                               @Param("kindId") Integer kindId, @Param("serverId") Integer serverId, @Param("gameId") String gameId, @Param("uniqueId") String uniqueId);

    /**
     * 电竞
     */
    @Select("<script>SELECT  COUNT(DISTINCT t1.username) as userNumber,count(*) as count,sum(t1.total_amount) as totalBet,sum(t1.settlement_amount) as profitAndLoss FROM `es_bet_order` t1,cpt_open_member t2,mem_baseinfo t3 where"
            + " t1.username = t2.username and t2.user_id = t3.memid AND t2.type = 'T_ES'"
            + "<if test='nickname != null'> and t3.nickname = #{nickname}</if>"
            + "<if test='uniqueId != null'> and t3.unique_id = #{uniqueId}</if>"
            + "<if test='mobileno != null'> and t3.mobileno = #{mobileno}</if>"
            + "<if test='startTime != null'> and t1.settlement_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.settlement_time &lt;= #{endTime}</if>" + "</script>")
    Map<String, Object> esCount(@Param("nickname") String nickname, @Param("mobileno") String mobileno,
                                @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("uniqueId") String uniqueId);

    @Select("<script>SELECT distinct t1.id,t1.es_order_id as esOrderId,t1.total_amount as totalAmount,t1.odds_value as oddsValue,t1.can_win_amount as canWinAmount,t1.confirm_status as confirmStatus,t1.username as username,\r\n"
            + "t1.settlement_status as settlementStatus,t3.unique_id as uniqueId,t1.win_lose_state as winLoseState,t1.settlement_amount as settlementAmount,t1.settlement_time as settlementTime,t1.create_time as createTime,t3.nickname as nickname,t3.mobileno as mobileno,t3.memid as memberId FROM `es_bet_order` t1,cpt_open_member t2,mem_baseinfo t3 where"
            + " t1.username = t2.username and t2.user_id = t3.memid AND t2.type = 'T_ES'"
            + "<if test='uniqueId != null'> and t3.unique_id = #{uniqueId}</if>"
            + "<if test='nickname != null'> and t3.nickname = #{nickname}</if>"
            + "<if test='mobileno != null'> and t3.mobileno = #{mobileno}</if>"
            + "<if test='startTime != null'> and t1.settlement_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.settlement_time &lt;= #{endTime}</if>"
            + " ORDER BY t1.settlement_time DESC limit #{pageNo},#{pageSize}</script>")
    List<EsBetOrderDTO> esList(@Param("nickname") String nickname, @Param("mobileno") String mobileno, @Param("startTime") String startTime,
                               @Param("endTime") String endTime, @Param("uniqueId") String uniqueId,
                               @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);


    @Select("<script>SELECT COUNT(DISTINCT t1.uname) as userNumber, count(*) as count,sum(t1.allbet) as totalBet,sum(t1.bet) as validBet,sum(t1.profit) as profitAndLoss FROM `ae_bet_order` t1, mem_baseinfo t2 where"
            + " t1.user_id =  t2.memid "
            + "<if test='nickname != null'> and t2.nickname = #{nickname}</if>"
            + "<if test='mobileno != null'> and t2.mobileno = #{mobileno}</if>"
            + "<if test='uniqueId != null'> and t2.unique_id = #{uniqueId}</if>"
            + "<if test='battleId != null'> and t1.battle_id = #{battleId}</if>"
            + "<if test='orderNo != null'> and t1.order_no = #{orderNo}</if>"
            + "<if test='gameId != null'> and t1.game_id = #{gameId}</if>"
            + "<if test='roomId != null'> and t1.room_id = #{roomId}</if>"
            + "<if test='aeAccount != null'> and t1.uname = #{aeAccount}</if>"
            + "<if test='startTime != null'> and t1.etime &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.etime &lt;= #{endTime}</if>" + "</script>")
    Map<String, Object> aeCount(@Param("nickname") String nickname, @Param("mobileno") String mobileno, @Param("startTime") String startTime,
                                @Param("endTime") String endTime, @Param("gameId") String gameId, @Param("orderNo") String orderNo,
                                @Param("battleId") String battleId, @Param("roomId") String roomId, @Param("aeAccount") String aeAccount, @Param("uniqueId") String uniqueId);


    @Select("<script>SELECT distinct t1.order_no as orderNo,t2.nickname as  nickname,t2.unique_id as uniqueId,t2.mobileno as mobileno,t2.memid as memberId,t1.stime as stime,t1.uname as  aeAccount,t1.game_name as gameName,t1.room_name as roomName ,t1.battle_id as battleId,t1.bet as  bet,t1.allbet as allbet,t1.profit as profit ,t1.etime as etime    FROM `ae_bet_order` t1,mem_baseinfo t2 where"
            + " t1.user_id =  t2.memid "
            + "<if test='nickname != null'> and t2.nickname = #{nickname}</if>"
            + "<if test='mobileno != null'> and t2.mobileno = #{mobileno}</if>"
            + "<if test='uniqueId != null'> and t2.unique_id = #{uniqueId}</if>"
            + "<if test='battleId != null'> and t1.battle_id = #{battleId}</if>"
            + "<if test='orderNo != null'> and t1.order_no = #{orderNo}</if>"
            + "<if test='gameId != null'> and t1.game_id = #{gameId}</if>"
            + "<if test='roomId != null'> and t1.room_id = #{roomId}</if>"
            + "<if test='aeAccount != null'> and t1.uname = #{aeAccount}</if>"
            + "<if test='startTime != null'> and t1.etime &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.etime &lt;= #{endTime}</if>"
            + " ORDER BY t1.etime DESC limit #{pageNo},#{pageSize}</script>")
    List<AeBetOrderDTO> aeList(@Param("nickname") String nickname, @Param("mobileno") String mobileno, @Param("startTime") String startTime,
                               @Param("endTime") String endTime, @Param("gameId") String gameId, @Param("orderNo") String orderNo,
                               @Param("battleId") String battleId, @Param("roomId") String roomId, @Param("aeAccount") String aeAccount,
                               @Param("uniqueId") String uniqueId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);


    @Select("<script>SELECT COUNT(DISTINCT t1.player_name) as userNumber, count(*) as count,sum(t1.bet_amount) as totalBet,sum(t1.bet_amount) as validBet,SUM(t1.win_amount) - SUM(t1.bet_amount)  as profitAndLoss FROM `mg_bet_order` t1, mem_baseinfo t2 where"
            + " t1.user_id =  t2.memid   "
            + "<if test='nickname != null'> and t2.nickname = #{nickname}</if>"
            + "<if test='mobileno != null'> and t2.mobileno = #{mobileno}</if>"
            + "<if test='gameCode != null'> and t1.game_name = #{gameCode}</if>"
            + "<if test='betId != null'> and t1.bet_id = #{betId}</if>"
            + "<if test='uniqueId != null'> and t2.unique_id = #{uniqueId}</if>"
            + "<if test='playerName != null'> and t1.player_name = #{playerName}</if>"
            + "<if test='startTime != null'> and t1.created_at &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.created_at &lt;= #{endTime}</if>"
            + "</script>")
    Map<String, Object> mgCount(@Param("nickname") String nickname, @Param("betId") String betId, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("playerName") String playerName, @Param("gameCode") String gameCode, @Param("mobileno") String mobileno, @Param("uniqueId") String uniqueId);


    @Select("<script>SELECT t1.player_name as playerName,t1.bet_id as betId,sum(t1.bet_amount) as betAmount,sum(t1.win_amount) as winAmount,"
            + " t1.created_at as createdAt,t1.game_name as gameName,t2.unique_id as uniqueId,t2.nickname as nickName,t2.mobileno as mobileno   FROM `mg_bet_order` t1, mem_baseinfo t2 where"
            + " t1.user_id =  t2.memid  "
            + "<if test='nickname != null'> and t2.nickname = #{nickname}</if>"
            + "<if test='mobileno != null'> and t2.mobileno = #{mobileno}</if>"
            + "<if test='gameCode != null'> and t1.game_name = #{gameCode}</if>"
            + "<if test='betId != null'> and t1.bet_id = #{betId}</if>"
            + "<if test='uniqueId != null'> and t2.unique_id = #{uniqueId}</if>"
            + "<if test='playerName != null'> and t1.player_name = #{playerName}</if>"
            + "<if test='startTime != null'> and t1.created_at &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.created_at &lt;= #{endTime}</if>"
            + " GROUP BY t1.bet_id ORDER BY t1.created_at DESC"
            + "  limit #{pageNo},#{pageSize}</script>")
    List<MgBetOrderDTO> mgList(@Param("nickname") String nickname, @Param("betId") String betId, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("playerName") String playerName, @Param("gameCode") String gameCode, @Param("mobileno") String mobileno, @Param("uniqueId") String uniqueId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    @Select("<script>SELECT COUNT(DISTINCT t1.player_name) as userNumber, count(*) as count,sum(t1.cost) as totalBet,sum(t1.cost) as validBet,SUM(earn)   as profitAndLoss FROM `ag_fish_bet_order` t1, mem_baseinfo t2 where"
            + " t1.member_id =  t2.memid   "
            + "<if test='nickname != null'> and t2.nickname = #{nickname}</if>"
            + "<if test='mobileno != null'> and t2.mobileno = #{mobileno}</if>"
            + "<if test='roomId != null'> and t1.room_id = #{roomId}</if>"
            + "<if test='tradeNo != null'> and t1.trade_no = #{tradeNo}</if>"
            + "<if test='uniqueId != null'> and t2.unique_id = #{uniqueId}</if>"
            + "<if test='playerName != null'> and t1.player_name = #{playerName}</if>"
            + "<if test='startTime != null'> and t1.creation_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.creation_time &lt;= #{endTime}</if>"
            + "</script>")
    Map<String, Object> agFashCount(@Param("nickname") String nickname, @Param("roomId") String roomId, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("tradeNo") String tradeNo, @Param("mobileno") String mobileno, @Param("uniqueId") String uniqueId, @Param("playerName") String playerName);


    @Select("<script>SELECT t1.player_name as playerName,t1.trade_no as tradeNo,t2.unique_id  as uniqueId,t1.creation_time as creationTime,t1.room_id as roomId,t1.roombet  as roombet,t1.cost as cost, t1.earn as earn,t2.nickname as nickName,t2.mobileno as mobileno FROM `ag_fish_bet_order` t1, mem_baseinfo t2 where  "
            + " t1.member_id =  t2.memid   "
            + "<if test='nickname != null'> and t2.nickname = #{nickname}</if>"
            + "<if test='mobileno != null'> and t2.mobileno = #{mobileno}</if>"
            + "<if test='roomId != null'> and t1.room_id = #{roomId}</if>"
            + "<if test='tradeNo != null'> and t1.trade_no = #{tradeNo}</if>"
            + "<if test='playerName != null'> and t1.player_name = #{playerName}</if>"
            + "<if test='uniqueId != null'> and t2.unique_id = #{uniqueId}</if>"
            + "<if test='startTime != null'> and t1.creation_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.creation_time &lt;= #{endTime}</if>"
            + "  ORDER BY t1.creation_time DESC"
            + " limit #{pageNo},#{pageSize}</script>")
    List<AgFashBetOrderDTO> agFashList(@Param("nickname") String nickname, @Param("roomId") String roomId, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("tradeNo") String tradeNo, @Param("mobileno") String mobileno, @Param("uniqueId") String uniqueId, @Param("playerName") String playerName, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);


    @Select("<script>SELECT COUNT(DISTINCT t1.player_id) as userNumber, count(*) as count,sum(t1.bet) * -1 as totalBet,sum(t1.bet) * -1 as validBet,SUM(t1.total)   as profitAndLoss FROM `db_bet_order` t1, mem_baseinfo t2 where"
            + " t1.member_id =  t2.memid   "
            + "<if test='nickname != null'> and t2.nickname = #{nickname}</if>"
            + "<if test='mobileno != null'> and t2.mobileno = #{mobileno}</if>"
            + "<if test='uniqueId != null'> and t2.unique_id = #{uniqueId}</if>"
            + "<if test='seqNo != null'> and t1.seq_no = #{seqNo}</if>"
            + "<if test='gameName != null'> and t1.game_name = #{gameName}</if>"
            + "<if test='startTime != null'> and t1.last_modify_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.last_modify_time &lt;= #{endTime}</if>"
            + "</script>")
    Map<String, Object> jdbCount(@Param("nickname") String nickname, @Param("mobileno") String mobileno, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("seqNo") Long seqNo, @Param("gameName") String gameName, @Param("uniqueId") String uniqueId);

    @Select("<script>SELECT  t1.player_id as playerId,t1.seq_no as seqNo, t2.unique_id  as uniqueId,t2.nickname as nickName,t2.mobileno as mobileno,t1.seq_no  as seqNo,t1.game_name as gameName,t1.create_time   as createTime,t1.last_modify_time  as lastModifyTime,t1.denom as denom ,t1.win as win ,t1.before_balance as beforeBalance,t1.after_balance as  afterBalance,t1.total as total FROM `db_bet_order` t1, mem_baseinfo t2 where"
            + " t1.member_id =  t2.memid   "
            + "<if test='nickname != null'> and t2.nickname = #{nickname}</if>"
            + "<if test='mobileno != null'> and t2.mobileno = #{mobileno}</if>"
            + "<if test='uniqueId != null'> and t2.unique_id = #{uniqueId}</if>"
            + "<if test='seqNo != null'> and t1.seq_no = #{seqNo}</if>"
            + "<if test='gameName != null'> and t1.game_name = #{gameName}</if>"
            + "<if test='startTime != null'> and t1.last_modify_time &gt;= #{startTime}</if>"
            + "<if test='endTime != null'> and t1.last_modify_time &lt;= #{endTime}</if>"
            + "  ORDER BY t1.last_modify_time DESC"
            + " limit #{pageNo},#{pageSize}</script>")
    List<DbBetOrderDTO> jdbList(@Param("nickname") String nickname, @Param("mobileno") String mobileno, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("seqNo") Long seqNo, @Param("gameName") String gameName, @Param("uniqueId") String uniqueId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
}
