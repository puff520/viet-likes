package com.likes.common.mybatis.mapperbean;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface GodPlanLotteryCategoryBeanMapper {
    /**
     * 同步GodPlanLotteryCategory
     * This method corresponds to the database table god_plan_lottery_category
     *
     * @mbggenerated
     */
    // 此种更新name
//    @Update("update god_plan_lottery_category gplc,lottery lt set gplc.lottery_name=lt.name where gplc.parent_id!=0 and gplc.lottery_id=lt.lottery_id")
    @Update("update god_plan_lottery_category gplc,lottery lt set gplc.lottery_id=lt.lottery_id where gplc.parent_id!=0 and gplc.lottery_name=lt.name")
    int synchGodPlanLotteryCategory();


    /**
     * 查询是否有同步
     * This method corresponds to the database table god_plan_lottery_category
     *
     * @mbggenerated
     */
    @Select("select count(1) from god_plan_lottery_category where parent_id!=0 and lottery_id==0")
    int everSynchGodPlanLotteryCategory();
}