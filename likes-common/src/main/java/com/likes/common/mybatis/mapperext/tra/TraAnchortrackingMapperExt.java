package com.likes.common.mybatis.mapperext.tra;

import com.likes.common.mybatis.entity.TraAnchortracking;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TraAnchortrackingMapperExt {


	void insertMany(List<TraAnchortracking> allTraAnchortrackingList);

	List<Long> getOrderidByOnlineid(Long anconlineid);
}