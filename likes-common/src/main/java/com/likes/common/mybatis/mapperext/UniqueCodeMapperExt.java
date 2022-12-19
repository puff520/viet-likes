package com.likes.common.mybatis.mapperext;

import com.likes.common.mybatis.entity.UserUniqueCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UniqueCodeMapperExt {

    /**
     * 加载未使用的随机码
     *
     * @param tableName
     * @param size
     * @return
     */
    List<UserUniqueCode> loadUniqueCode(@Param("tableName") String tableName, @Param("size") int size);

    /**
     * 加载已有的所有随机码
     *
     * @param tableName
     * @return
     */
    Set<String> loadAllUniqueCode(@Param("tableName") String tableName);

    /**
     * 批量新增
     *
     * @param tableName
     * @param codes
     */
    int batchInsertCodes(@Param("tableName") String tableName, @Param("codes") Set<String> codes);

    /**
     * 更新已使用的随机码状态
     *
     * @param tableName
     * @param maxRecordId
     */
    int updateCodeStatus(@Param("tableName") String tableName, @Param("maxRecordId") int maxRecordId, @Param("status") int status);


}