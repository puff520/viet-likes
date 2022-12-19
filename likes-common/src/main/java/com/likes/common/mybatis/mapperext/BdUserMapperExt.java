package com.likes.common.mybatis.mapperext;

import com.likes.common.mybatis.entity.BdUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BdUserMapperExt {


	BdUser selectByAccno(String accno);

	BdUser getRandWeiChat();

	List<String> getKeFuWeiChat(String sysrolename);

	BdUser getRepeatPhoneno(BdUser bdUserParam );


}