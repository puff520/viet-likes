package com.likes.common.mybatis.mapperext.member;

import com.likes.common.model.request.FamilyIncarnateRequest;
import com.likes.common.model.response.FamilyIncarnateResponse;
import com.likes.common.model.dto.member.MemFamilyReq;
import com.likes.common.model.dto.member.MemFamilyResponse;
import com.likes.common.mybatis.entity.MemFamily;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface MemFamilyMapperExt {

	MemFamily getMemFamilyByAccno(String accno);

	int updateAddMemnums(@Param("familyid") Long familyid, @Param("size") int size);

	MemFamily getRepeat(MemFamilyReq req);

	int doDelFamily(MemFamily memFamily);

	Page<MemFamilyResponse> familyList(MemFamilyReq req, RowBounds rowBounds);

	int updateSubtractMemnums(@Param("familyid")Long familyid,@Param("size") int size);

	List<FamilyIncarnateResponse> findAllFamilyList();

	FamilyIncarnateResponse findOneByFamilyId(Long familyid);

	List<MemFamily> allFamilyList();

	MemFamily getByAnchorAccno(String accno);

	MemFamily getFamilyByAccno(String accno);
}