package com.likes.common.mybatis.mapperbean;


import com.likes.common.model.common.KeyValueModel;
import com.likes.common.mybatis.mapperbean.provider.money.CashDynaSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;


public interface CashBeanMapper {

    /**
     * 根据条件统计礼金记录各种类型的金额
     * @param type
     * @param startTime
     * @param endTime
     * @param memberId
     * @param account
     * @param remark
     * @return
     */
    @SelectProvider(type = CashDynaSqlProvider.class, method = "countMoneyByType")
    List<KeyValueModel> countMoneyByType(@Param("type") String type, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("memberId") Integer memberId, @Param("account") String account, @Param("remark") String remark);

    /**
     * 获取所有支持绑卡的银行
     * @return
     */
//    @Select("select s.id, s.name from support_bank s where s.deleted=1 and s.binding = 1")
//    List<SupportBankVO> findAllSupportBank();
}
