package com.likes.common.rest.money;


import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.FamilyIncarnateRequest;
import com.likes.common.model.request.UserReq;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.model.response.FamilyIncomeAndExpensesResponse;
import com.likes.common.model.response.FamilyMemGoldchangeResponse;
import com.likes.common.model.response.UserResp;
import com.likes.common.mybatis.entity.MemGoldchange;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * ClassName: MemGoldchangeRest
 * Description: 描述
 *
 * @author ping
 * @since JDK 1.8
 * date: 2020/6/19 21:26
 */
public interface MemGoldchangeRest {

    /**
     * 主播获得打赏帐变
     */
    ResultInfo<List<UserResp>> getTopTenStatistics(@RequestBody UserReq userReq);

    /**
     * 土豪打赏帐变
     */
    ResultInfo<List<UserResp>> getRoomBigMoneyTop(@RequestBody UserReq userReq);

    /**
     * 查询家族当月是否已经提现
     */
    ResultInfo<List<FamilyMemGoldchangeResponse>> isFamilyTiXian(@RequestBody FamilyIncarnateRequest param);

    /**
     * 家族的总收入 总支出
     */
    ResultInfo<List<FamilyIncomeAndExpensesResponse>> familyIncomeAndExpensesList(@RequestBody FamilyIncarnateRequest req);

    /**
     * 当月该家族长
     */
    MemGoldchange findFamilyIsIncarnate(@RequestBody MemGoldchange memGoldchangeParam);

    /**
     * 新增：计算从每个主播処获取的金币 和 金额 提现
     */
    int doFamilyIncarnateMemGoldchange(@RequestBody Map<String, Object> param);

    /**
     * 邀请新用户加金币帐变
     */
    int insertSelectiveMemGoldchange(@RequestBody MemGoldchange memGoldchange);

    /**
     * 累计收益(播币)
     */
    Double getAllQuantityByType(@RequestBody UsersRequest param);

    /**
     * 后台统计-帐变列表-总数
     */
    Object tatolGoldchange(@RequestParam("refaccno") String refaccno, @RequestBody List<Integer> typeList, @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime);

    /**
     * 插入金币变动数据
     */
    int insertSelective(@RequestBody MemGoldchange goldchange);

    /**
     * 统计帐变
     */
//    @PostMapping("/liveManage/memGoldchangeRest/countByExample")
//    Integer countByExample(@RequestBody MemGoldchangeExample example);

    /**
     * 获取帐变记录
     */
//    @PostMapping("/liveManage/memGoldchangeRest/selectByExample")
//    List<MemGoldchange> selectByExample(@RequestBody MemGoldchangeExample example);

    /**
     * 修改 金币变化记录表 将用户申请提现的记录 改为 状态已提现
     */
    int updateByPrimaryKeySelective(@RequestBody MemGoldchange memGoldchange);

    /**
     * 根据订单获取帐变记录
     */
    MemGoldchange selectOneByExample(@RequestParam("orderid") Long orderid, @RequestParam("accno") String accno);

    /**
     * 根据账号和起止时间获得帐变总数  列表
     */
    ResultInfo<List<Map<String, Object>>> getTotalGoldChangeByAccno(@RequestBody List<Map<String, Object>> accnoTimeList);

    /**
     * 根据账号获得帐变总数  列表
     */
    ResultInfo<List<Map<String, Object>>> getTotalGoldsByAccnos(@RequestBody List<String> accnoList);

    /**
     * 根据账号获得昨日帐变总数  列表
     */
    ResultInfo<List<Map<String, Object>>> getTotalYesterdayGoldsByAccnos(@RequestBody List<String> accnoList, @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime);
}
