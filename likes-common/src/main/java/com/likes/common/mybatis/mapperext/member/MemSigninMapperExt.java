package com.likes.common.mybatis.mapperext.member;

import com.likes.common.model.dto.order.SignDaoDO;
import com.likes.common.model.dto.member.MemSigninDO;
import com.likes.common.mybatis.entity.MemSignin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemSigninMapperExt {


	MemSignin getTodayData(SignDaoDO param);

	int insertMemSignin(MemSigninDO memSignin);
}