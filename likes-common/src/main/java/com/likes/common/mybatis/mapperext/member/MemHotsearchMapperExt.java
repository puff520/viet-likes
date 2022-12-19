package com.likes.common.mybatis.mapperext.member;

import com.likes.common.mybatis.entity.MemHotsearch;

import java.util.List;


public interface MemHotsearchMapperExt {

	MemHotsearch find(MemHotsearch m);

	void updatenum(MemHotsearch memHotsearch);

	List<MemHotsearch> getHotSearch(MemHotsearch memHotsearch);
}