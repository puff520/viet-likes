package com.likes.common.mybatis.mapperext.member;

import com.github.pagehelper.Page;
import com.likes.common.model.dto.member.MemberBankDTO;
import com.likes.common.model.dto.member.MemberVipDTO;
import com.likes.common.model.request.MemberBankRequest;
import com.likes.common.model.request.MemberVIPRequest;
import com.likes.common.mybatis.entity.MemBankaccount;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface MemBankaccountMapperExt {
	MemBankaccount findByFamilyId(Long familyid);
	MemBankaccount findByAccno(String accno);

	int insertBank(MemBankaccount memBankaccount);

	MemBankaccount findBankByAccno(MemBankaccount param);

	int doDelAllBank(MemBankaccount delBankaccountparam);

	Page<MemberBankDTO> getMemberbankLst(MemberBankRequest req, RowBounds rowBounds);

	int deleteByPrimaryKey(MemBankaccount med);
}
