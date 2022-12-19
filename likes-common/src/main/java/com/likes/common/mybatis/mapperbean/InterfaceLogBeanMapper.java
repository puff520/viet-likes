package com.likes.common.mybatis.mapperbean;


import com.likes.common.model.vo.OrderExportRecordVO;
import com.likes.common.model.vo.member.MemberChatRecordVO;
import com.likes.common.mybatis.mapperbean.provider.interfacelog.InterfaceLogDynaSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface InterfaceLogBeanMapper {

    @SelectProvider(type = InterfaceLogDynaSqlProvider.class, method = "listOrderExportRecord")
    List<OrderExportRecordVO> listOrderExportRecord(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("lotteryId") String lotteryId);

    @Select("SELECT m.id, m.member_id as memberId, m.account, a.nickname, m.message, m.create_time as createTime from member_chat_record m LEFT JOIN app_member a on m.member_id = a.id  where m.deleted = 0 ORDER BY m.id DESC LIMIT #{pageNo},#{pageSize}")
    List<MemberChatRecordVO> listMemberChatRecord(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
}
