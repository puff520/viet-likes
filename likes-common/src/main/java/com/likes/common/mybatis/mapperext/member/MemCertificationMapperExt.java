package com.likes.common.mybatis.mapperext.member;

import com.likes.common.mybatis.entity.MemCertification;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface MemCertificationMapperExt {

	MemCertification findByAccno(String accno);

	int insertSelectiveMemCertification(MemCertification mCertification);
	MemCertification getMemCertificationOne(String accno);
}