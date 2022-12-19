package com.likes.common.mybatis.mapperbean;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AppMemberBeanMapper {

    /**
     * 根据id 查询用户最近一次进入的第三方游戏
     *
     * @param memberId
     */
    @Select("SELECT type from member_balance_change where member_id = #{memberId} and type in(28,23,21,42) ORDER BY create_time desc LIMIT 1")
    Integer getAppMemberLastInExternalGame(@Param("memberId") Integer memberId);


}
