package com.likes.common.mybatis.mapperbean.provider.money;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lzy
 * @create 2018-07-17 17:12
 **/
@Component
public class CashDynaSqlProvider {

    /**
     * 根据条件统计礼金记录各种类型的金额sql
     * @param map
     * @return
     */
    public String countMoneyByType(final  Map<String, Object> map) {
        String type = (String) map.get("type");
        String startTime = (String) map.get("startTime");
        String endTime = (String) map.get("endTime");
        Integer memberId = (Integer) map.get("memberId");
        String account = (String) map.get("account");
        String remark = (String) map.get("remark");

        return new org.apache.ibatis.jdbc.SQL() {
            {
                SELECT(" c.type AS name, SUM(c.money) AS nums ");
                FROM(" gift-money-record c ");

                if (StringUtils.isNotBlank(type)) {
                    WHERE("c.type = #{type}");
                }
                if (StringUtils.isNotBlank(startTime)) {
                    WHERE("CAST(c.create_time AS datetime) >= CAST(#{startTime} AS datetime)");
                }
                if (StringUtils.isNotBlank(endTime)) {
                    WHERE("CAST(c.create_time AS datetime) <= CAST(#{endTime} AS datetime)");
                }
                if (memberId != null) {
                    WHERE("c.member_id = #{memberId}");
                }
                if (StringUtils.isNotBlank(account)) {
                    WHERE("c.account like #{account}");
                }
                if (StringUtils.isNotBlank(remark)) {
                    WHERE("c.remark like #{remark}");
                }
            }
        }.toString();
    }
}
