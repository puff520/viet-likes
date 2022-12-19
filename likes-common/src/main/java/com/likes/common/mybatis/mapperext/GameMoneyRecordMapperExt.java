package com.likes.common.mybatis.mapperext;

import com.likes.common.mybatis.entity.GameMoneyRecord;
import org.apache.ibatis.annotations.Param;

public interface GameMoneyRecordMapperExt {


    void insertOrUpdate(GameMoneyRecord gameMoneyRecord);

}
