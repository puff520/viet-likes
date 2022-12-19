package com.likes.common.mybatis.mapperbean.provider.interfacelog;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lzy
 * @create 2018-06-19 18:02
 **/
@Component
public class InterfaceLogDynaSqlProvider {

    public String listOrderExportRecord(final Map<String, Object> map) {
        Integer lotteryId = (Integer) map.get("lotteryId");
        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");

        return new org.apache.ibatis.jdbc.SQL() {
            {
                SELECT(" o.id, l.name as lotteryName, o.issue, o.`name`, o.url, o.create_time as createTime ");
                FROM("order_export_record o ");
                LEFT_OUTER_JOIN("lottery l on o.lottery_id = l.id ");
                if (lotteryId != null) {
                    WHERE("o.lottery_id = #{lotteryId}");
                }
                ORDER_BY("o.create_time DESC limit #{pageNo},#{pageSize}");
            }
        }.toString();

    }
}
