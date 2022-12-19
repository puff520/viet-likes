package com.likes.common.config.shading;


import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Log4j2
public class ShardingAlgorithmTool {

    private static final HashSet<String> tableNameCache = new HashSet<>();

    /**
     * 缓存重载方法
     */
    public static void tableNameCacheReload() {
        // 读取数据库中所有表名
        List<String> tableNameList = getAllTableNameBySchema();
        // 删除旧的缓存(如果存在)
        ShardingAlgorithmTool.tableNameCache.clear();
        // 写入新的缓存
        ShardingAlgorithmTool.tableNameCache.addAll(tableNameList);
    }


    public static List<String> getAllTableNameBySchema() {
        List<String> res = new ArrayList<>();
        Environment env = SpringUtil.getApplicationContext().getEnvironment();
        try (Connection connection = DriverManager.getConnection(env.getProperty("spring.datasource.dynamic.datasource.likes.url"),
                env.getProperty("spring.datasource.dynamic.datasource.likes.username"),
                env.getProperty("spring.datasource.dynamic.datasource.likes.password"));
             Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery("show TABLES like 'mem_goldchange%'")) {
                while (rs.next()) {
                    res.add(rs.getString(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static HashSet<String> cacheTableNames() {
        return tableNameCache;
    }
}
