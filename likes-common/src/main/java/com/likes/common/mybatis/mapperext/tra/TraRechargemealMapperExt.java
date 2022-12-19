package com.likes.common.mybatis.mapperext.tra;

import com.likes.common.model.request.TraRechargemealReq;
import com.likes.common.model.response.TraRechargemealResponse;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface TraRechargemealMapperExt {


	Page<TraRechargemealResponse> traRechargemealList(TraRechargemealReq req, RowBounds rowBounds);

	int findAllTotal();
    List<TraRechargemealResponse> getList(Integer num);
}