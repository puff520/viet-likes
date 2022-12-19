package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.BrokerMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 消息记录表 Mapper 接口
 * </p>
 *
 * @author xxx
 * @since 2022-01-01
 */
public interface BrokerMessageMapper extends Mapper<BrokerMessage> {

    List<BrokerMessage> queryBrokeryMessageStatus4Timeout(@Param("status") Integer status);

    int updateForTryCount(@Param("messageId") String messageId, @Param("updateTime") Date updateTime);

    @Select("select * from broker_message")
    BrokerMessage selectById2();
}
