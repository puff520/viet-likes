//package com.likes.common.mybatis.mapperext;
//
//
//
//import com.likes.common.model.vo.circle.*;
//import com.likes.common.mybatis.entity.CircleGodPushOrder;
//import org.apache.ibatis.annotations.Param;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Map;
//
//public interface GodInfoMapperExt {
//    /**
//     * 一定时间内的投注金额和中奖金额（除撤单）
//     */
//    Map<String, BigDecimal> calcBetMoneyAndWinMoneyByTime(@Param("userId") Integer userId,
//                                                          @Param("startTime") String startTime, @Param("endTime") String endTime);
//
//    /**
//     * 大神列表排序 ifnull(show_profit_rate, calc_profit_rate) ifnull(show_max_lz,
//     * calc_max_lz) ifnull(show_win_rate, calc_win_rate)
//     */
//    List<GodListVO> sortGodList(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
//                                @Param("sortBy") String sortBy);
//
//    /**
//     * 大神列表排序Web端 ifnull(show_profit_rate, calc_profit_rate) ifnull(show_max_lz,
//     * calc_max_lz) ifnull(show_win_rate, calc_win_rate)
//     */
//    List<GodListWebVO> sortGodListWeb(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
//                                      @Param("sortBy") String sortBy, @Param("isMyFocus") String isMyFocus, @Param("meId") Integer meId);
//
//    Integer sortGodListWebCount(@Param("sortBy") String sortBy, @Param("isMyFocus") String isMyFocus,
//                                @Param("meId") Integer meId);
//
//    /**
//     * 关注的列表
//     *
//     * @param meId
//     * @return
//     */
//    List<Integer> gzList(@Param("meId") Integer meId, @Param("pageNo") Integer pageNo,
//                         @Param("pageSize") Integer pageSize);
//
//    /**
//     * 关注的列表数量
//     *
//     * @param meId
//     * @return
//     */
//    Integer gzListCount(@Param("meId") Integer meId);
//
//    /**
//     * 根据用户用户ids查询用户信息
//     */
//    List<GodListVO> getGodListByFocusIds(@Param("idList") List<Integer> idList);
//
//    Integer getGdUserCount(@Param("godOrderId") Integer godOrderId);
//
//    /**
//     * 跟单盈了的总额
//     */
//    BigDecimal getTotalGdWinAmount(@Param("godOrderId") Integer godOrderId);
//
//    /**
//     * 一周内盈利单数
//     */
//    Integer weekWinCount(@Param("godId") Integer godId, @Param("startTime") String startTime,
//                         @Param("endTime") String endTime);
//
//    List<Map<String, Object>> getFansListByGodId(@Param("godId") Integer godId, @Param("pageNo") Integer pageNo,
//                                                 @Param("pageSize") Integer pageSize);
//
//    Integer getFansListByGodIdCount(@Param("godId") Integer godId);
//
//    /**
//     * 首页的推单排行榜
//     */
//    List<PushOrderListVO> pushOrderListIndex();
//
//    /**
//     * 跟单列表Web
//     */
//    List<FollowOrderVO> getFollowOrdersWeb(@Param("userId") Integer userId,
//                                           @Param("orderByColumn") String orderByColumn, @Param("orderByType") String orderByType,
//                                           @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
//
//    Integer getFollowOrdersWebCount(@Param("userId") Integer userId, @Param("orderByColumn") String orderByColumn,
//                                    @Param("orderByType") String orderByType);
//
//    List<GodListVO> sortGodLotteryList(@Param("lotteryId") Integer lotteryId, @Param("pageNo") Integer pageNo,
//                                       @Param("pageSize") Integer pageSize, @Param("sortBy") String sortBy);
//
//    /**
//     * 获取历史
//     *
//     * @param userId
//     * @param lotteryId
//     * @return
//     */
//    int countByPushHistory(@Param("userId") Integer userId, @Param("lotteryId") Integer lotteryId);
//
//    /**
//     * 获取列表
//     *
//     * @param userId
//     * @param pageSize
//     * @param pageNo
//     * @return
//     */
//    List<PushHistoryListVo> pushHistoryList(@Param("userId") Integer userId, @Param("lotteryId") Integer lotteryId,
//                                            @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
//
//    /**
//     * 热门列表
//     *
//     * @param lotteryId
//     * @param pageNo
//     * @param pageSize
//     * @return
//     */
//    List<CircleGodPushOrder> queryPushList(@Param("lotteryId") Integer lotteryId, @Param("orderByMoney") Integer orderByMoney, @Param("pageNo") Integer pageNo,
//                                           @Param("pageSize") Integer pageSize);
//
//    /**
//     * 我的列表
//     *
//     * @param id
//     * @param finishStatus
//     * @param pageNo
//     * @param pageSize
//     * @return
//     */
//    List<CircleGodPushOrder> queryByUserPushOrderList(@Param("id") Integer id,
//                                                      @Param("finishStatus") Integer finishStatus, @Param("pageNo") Integer pageNo,
//                                                      @Param("pageSize") Integer pageSize);
//
//    /**
//     * 查询是否超金额
//     *
//     * @return
//     */
//    Integer queryOrderByMoney();
//
//
//    /**
//     * 查询跟单总金额
//     *
//     * @return
//     */
//    double getGdUserAmount(@Param("godOrderId") Integer godOrderId);
//}
