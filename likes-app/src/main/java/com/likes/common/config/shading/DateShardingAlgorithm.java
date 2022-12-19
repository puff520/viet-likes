package com.likes.common.config.shading;

import cn.hutool.core.date.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Log4j2
@Component
public class DateShardingAlgorithm implements StandardShardingAlgorithm<Date> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
        for (String s : collection) {
            System.out.println("节点配置表名为: "+s);
        }
        // 查询数据库中的表 hss_history
        List<String> tableNames = ShardingAlgorithmTool.getAllTableNameBySchema();
        for (String s : tableNames) {
            System.out.println("数据库实时表名: "+s);
        }
//        ShardingAlgorithmTool.tableNameCacheReload();
        HashSet<String> tableNameCache = ShardingAlgorithmTool.cacheTableNames();
        for (String s : tableNameCache) {
            System.out.println("缓存中的表名: "+s);
        }
        // 返回数据库实时存在的表 如果返回collection会提示表不存在
        return tableNameCache;
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> preciseShardingValue) {
        Date creatTime = preciseShardingValue.getValue();
        //获取第几周
        Integer weekOfMonth = DateUtil.weekOfYear(creatTime);
        log.info(weekOfMonth);
        String logicTableName = preciseShardingValue.getLogicTableName();
        if (weekOfMonth < 25) {
            return logicTableName;
        }
        if (weekOfMonth.equals(25) || weekOfMonth.equals(26)) {
            return logicTableName + 2;
        } else if (weekOfMonth.equals(27) || weekOfMonth.equals(28)) {
            return logicTableName + 3;
        } else if (weekOfMonth.equals(29) || weekOfMonth.equals(30)) {
            return logicTableName + 4;
        } else if (weekOfMonth.equals(31) || weekOfMonth.equals(32)) {
            return logicTableName + 5;
        } else if (weekOfMonth.equals(33) || weekOfMonth.equals(34)) {
            return logicTableName + 6;
        } else {
            return logicTableName + 7;
        }
    }


    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return null;
    }
}
