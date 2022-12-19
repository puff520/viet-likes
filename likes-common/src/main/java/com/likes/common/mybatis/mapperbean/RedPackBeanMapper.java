package com.likes.common.mybatis.mapperbean;


import com.likes.common.model.vo.chat.ChatRedPackVo;
import com.likes.common.model.vo.chat.RedPackRankVo;
import com.likes.common.model.vo.chat.RedPackRecordVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

public interface RedPackBeanMapper {

	 @Select("<script>select count(*) "+
		 		"from chat_red_pack t1,app_member t2 where t2.id = t1.user_id\n" +
		 		"<if test='userId != null'> and t1.user_id = #{userId}</if>"
		 		+ "<if test='nickname != null'> and t1.nickname like #{nickname}</if>"
		 		+ "<if test='account != null'> and t2.account like #{account}</if>"
		 		+ "<if test='money != null'> and t1.money = #{money}</if></script>")
	int getChatRedPackVoCount(
             @Param("userId") Integer userId,
             @Param("nickname") String nickname,
             @Param("money") BigDecimal money,
             @Param("account") String account);


	@Select("<script>select t1.id as id,t1.user_id as userId,t1.nickname as nickname,t1.head as head,\n" +
	 		"t1.send_number as sendNumber,t1.money as money,t1.receive_money as receiveMoney,\n" +
	 		"t1.hold_number as holdNumber,t1.status as status,t1.create_time as createTime,\n" +
	 		"t2.account as appMemberAccount,t1.is_back as isBack \n" +
	 		"from chat_red_pack t1,app_member t2 where t2.id = t1.user_id\n" +
	 		"<if test='userId != null'> and t1.user_id = #{userId}</if>"
	 		+ "<if test='nickname != null'> and t1.nickname like #{nickname}</if>"
	 		+ "<if test='account != null'> and t2.account like #{account}</if>"
	 		+ "<if test='money != null'> and t1.money = #{money}</if>"
	 		+" ORDER BY t1.CREATE_TIME DESC LIMIT #{pageNo},#{pageSize}</script>")
	List<ChatRedPackVo> getChatRedPackVoList(
            @Param("pageNo") Integer pageNo,
            @Param("pageSize") Integer pageSize,
            @Param("userId") Integer userId,
            @Param("nickname") String nickname,
            @Param("money") BigDecimal money,
            @Param("account") String account);

	 @Select("<script>select count(*) "+
			"from chat_pack_record t1,app_member t2 where  t1.user_id = t2.id\n" +
			"<if test='userId != null'> and t1.user_id = #{userId}</if>"
			+ "<if test='nickname != null'> and t2.nickname like #{nickname}</if>"
			+ "<if test='realName != null'> and t2.real_name like #{realName}</if>"
			+ "<if test='account != null'> and t2.account like #{account}</if>"
			+ "<if test='startTime != null'> and t1.create_time &gt;= #{startTime}</if>"
			+ "<if test='endTime != null'> and t1.create_time &lt;= #{endTime}</if> </script>")
	int getChatRedPackRankVoCount(Integer userId, String nickname, String startTime, String endTime, String account, String realName);

	 @Select("<script>select   t2.real_name realName,t1.user_id as userId,SUM(t1.money) AS totalMomey,account,t2.pay_amount totalPayMomey,\n"
			    +" t2.nickname as nickName,SUM(1) as number, t2.balance  FROM "
			    + "	 chat_pack_record t1,app_member t2 where  t1.user_id = t2.id\n"
				+"<if test='userId != null'> and t1.user_id = #{userId}</if>"
		 		+ "<if test='nickname != null'> and t2.nickname like #{nickname}</if>"
		 		+ "<if test='realName != null'> and t2.real_name like #{realName}</if>"
		 		+ "<if test='account != null'> and t2.account like #{account}</if>"
				+ "<if test='startTime != null'> and t1.create_time &gt;= #{startTime}</if>"
		 		+ "<if test='endTime != null'> and t1.create_time &lt;= #{endTime}</if>"
			    +" GROUP BY  	t1.user_id  ORDER BY 	totalMomey DESC  LIMIT #{pageNo},#{pageSize}</script>")
	List<RedPackRankVo> getChatRedPackRankVoList(Integer pageNo, Integer pageSize, Integer userId, String nickname,
												 String startTime, String endTime, String account, String realName);

	 @Select("<script>select  	count(*)   FROM "
			    + "	chat_pack_record t1,chat_red_pack t2,app_member t3 where\n"
				+ " t1.pack_id = t2.id AND t2.user_id = t3.id   and t1.user_id =#{drowUserId}\n"
				+"<if test='userId != null'> and t2.user_id = #{userId}</if>"
		 		+ "<if test='nickname != null'> and t3.nickname like #{nickname}</if>"
		 		+ "<if test='realName != null'> and t3.real_name like #{realName}</if>"
		 		+ "<if test='account != null'> and t3.account like #{account}</if>"
				+ "<if test='startTime != null'> and t1.create_time &gt;= #{startTime}</if>"
		 		+ "<if test='endTime != null'> and t1.create_time &lt;= #{endTime}</if></script>")
	int getChatRedPackRecordVoCount(Integer userId, String nickname, String startTime, String endTime, String account,
                                    String realName, Integer drowUserId);

	 @Select("<script>select  	t2.create_time as createTime,t3.nickname,t3.account,t3.id as userId,t3.real_name as realName,t2.money as money,\n"
			    +" t2.send_number as number,t1.money as drowMomey,t1.create_time  drowTime   FROM "
			    + "	chat_pack_record t1,chat_red_pack t2,app_member t3 where\n"
				+ " t1.pack_id = t2.id AND t2.user_id = t3.id  and  t1.user_id =#{drowUserId}\n"
				+"<if test='userId != null'> and t2.user_id = #{userId}</if>"
		 		+ "<if test='nickname != null'> and t3.nickname like #{nickname}</if>"
		 		+ "<if test='realName != null'> and t3.real_name like #{realName}</if>"
		 		+ "<if test='account != null'> and t3.account like #{account}</if>"
				+ "<if test='startTime != null'> and t1.create_time &gt;= #{startTime}</if>"
		 		+ "<if test='endTime != null'> and t1.create_time &lt;= #{endTime}</if></script>")
	List<RedPackRecordVo> getChatRedPackRecordVoList(Integer pageNo, Integer pageSize, Integer userId, String nickname,
													 String startTime, String endTime, String account, String realName, Integer drowUserId);
}
