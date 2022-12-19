package com.likes.common.mybatis.mapperext.member;

import com.likes.common.model.TimeReortDTO;
import com.likes.common.model.dto.member.MemGoldChangeDTON;
import com.likes.common.model.dto.member.MemGoldchangeDTO;
import com.likes.common.model.dto.report.*;
import com.likes.common.model.request.*;
import com.likes.common.model.response.FamilyIncomeAndExpensesResponse;
import com.likes.common.model.response.FamilyMemGoldchangeResponse;
import com.likes.common.model.response.FamilyMemIncarnateResponse;
import com.likes.common.model.response.UserResp;
import com.likes.common.mybatis.entity.MemGoldchange;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface MemGoldchangeMapperExt {

    Integer getcountbyrmtype(Map<String, Object> param);

    /**
     * accno  加金币
     *
     * @param memGoldchange
     * @return
     */
    int insertSelectiveMemGoldchange(MemGoldchange memGoldchange);

    int updateZhuboTixian(MemGoldchange paramMemGoldchange);

    List<FamilyMemGoldchangeResponse> isFamilyTiXian(FamilyIncarnateRequest param);

    List<FamilyIncomeAndExpensesResponse> familyIncomeAndExpensesList(FamilyIncarnateRequest req);

    MemGoldchange findFamilyIsIncarnate(MemGoldchange memGoldchangeParam);

    int doFamilyIncarnateMemGoldchange(Map<String, Object> param);

    List<FamilyMemIncarnateResponse> findFamilyTiXian(FamilyIncarnateRequest req);

    /**
     * 获取用户縂充值数量
     *
     * @param accno
     * @return
     */
    Double getZongchongzhi(String accno);

    /**
     * 获取这段时间内
     *
     * @param f
     * @return
     */
    Double getSumQuantity(FamilyStatisticsRequest f);

    int insertAuto(MemGoldchange record);

    List<UserResp> getTopTenStatistics(UserReq userReq);

    List<UserResp> getRoomBigMoneyTop(UserReq userReq);

    double getLiveincome(UserReq dashangReq);

    int insertSelectiveSubtractMemGoldchange(MemGoldchange memGoldchange);

    Double getAllQuantity(@Param("changetype") Integer changetype, @Param("accno") String accno);

    Double getNengtiGoldNum(String accno);

    List<MemGoldchange> getAllGoldchangeByType(UsersRequest xiaofeiparam);

    Page<MemGoldchangeDTO> myIncomeAndExpensesList(UsersRequest req, RowBounds toRowBounds);

    int insertSelectiveFamilyGoldchange(MemGoldchange memGoldchange);

    Double getAllQuantityByType(UsersRequest param);

    BigDecimal tatolGoldchange(@Param("refaccno") String refaccno, @Param("accno") String accno, @Param("typeList") List<Integer> typeList, @Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Map<String, Object>> statisticsAllType(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Map<String, Object>> statisticsIncomeRecharge(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Map<String, Object>> getTotalGoldsByAccnos(@Param("accnoList") List<String> accnoList);

    List<Map<String, Object>> getTotalYesterdayGoldsByAccnos(@Param("accnoList") List<String> accnoList, @Param("startTime") String startTime, @Param("endTime") String endTime);

    DepositStatisticsDO selectSumPeople(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("type") Integer type);

    List<ActivityUserDataDO> getActivityGoldchange(@Param("startTime") String startTime, @Param("endTime") String endTime);

    LinkedHashMap<String, Object> myPageCount(TimeReortDTO dto);

    List<RechargeUserDataDO> getRecharge(@Param("startTime") String startTime, @Param("endTime") String endTime);


    List<PaymentUserDataDO> getPayment(@Param("startTime") String startTime, @Param("endTime") String endTime);


    List<GodPlanRewardDataDO> getGodPlanReward(@Param("startTime") String startTime, @Param("endTime") String endTime);


    List<GiftUserDataDO> getGift(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<MemGoldChangeDTON> getMemberGoldChange(MemGoldChangeRequest req);


    @Select("SELECT DISTINCT(mg.accno) FROM mem_goldchange mg WHERE   create_time BETWEEN #{beginTime} AND #{endTime}" +
            " and mg.changetype in (1,12,55,101,102,103,100,301,302,303) order by mg.create_time")
    List<String> getYesterdayGoldAccno(String beginTime, String endTime);


    @Select("SELECT DISTINCT(mg.accno) FROM mem_goldchange mg WHERE   create_time BETWEEN #{beginTime} AND #{endTime}" +
            " and mg.changetype in (1,12,55,101,102,103,100,301,302,303) order by mg.create_time limit #{pageNo} ,#{pageSize}")
    List<String> getYesterdayGoldPage(String beginTime, String endTime,Integer pageNo,Integer pageSize);

    @Select("SELECT mg.changetype,SUM(mg.quantity) as quantity FROM mem_goldchange mg WHERE   mg.create_time BETWEEN #{beginTime} AND #{endTime}"
            + "  and mg.accno = #{accno} and mg.changetype in (1,12,55,101,102,103,100,301,302,303)"
            + " GROUP BY mg.changetype  order by mg.create_time ")
    List<Map<String, Object>> getYesterdayQuantityByChangetype(String accno, String beginTime, String endTime);


}
