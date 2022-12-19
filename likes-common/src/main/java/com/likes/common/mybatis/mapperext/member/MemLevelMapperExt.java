package com.likes.common.mybatis.mapperext.member;

import com.likes.common.model.dto.member.MemberCountDTO;
import com.likes.common.model.dto.member.MemberVipDTO;
import com.likes.common.model.request.GiftReq;
import com.likes.common.model.request.MemberVIPRequest;
import com.likes.common.mybatis.entity.MemLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MemLevelMapperExt {

	MemLevel selectByAccno(String accno);


	int updateMemscore(GiftReq memLevelParam);

	int updateLevel(MemLevel param);

	List<MemberVipDTO> getMemberVipLst(MemberVIPRequest req);

	List<MemberCountDTO> getMemberCountLevel(String date);

	List<MemberCountDTO> getMemberCountLevelBefor(String date);

	@Update("update mem_level  set is_delete = 1 where levelid = #{levelId} and levellog != '新用户注册初始化' ")
	int   updateMemLevelById(@Param("levelId") Long levelId);

	MemLevel getMiniLevelByAccno(String accno);

	MemLevel getByAccnoAndLevelId(@Param("accno") String accno, @Param("id")Long id);

	int checkUserMemberLevelExpire(@Param("levelSeq")Integer levelSeq,@Param("accno") String accno);

	@Select("SELECT accno from mem_level WHERE level_config_id > 0 and is_delete = 0 GROUP BY accno HAVING count(accno) > 1 ORDER BY accno")
	List<String>  chongFuList();
}
