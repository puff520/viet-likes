package com.likes.common.service.money;

import com.likes.common.model.request.TraRechargemealReq;
import com.likes.common.model.response.TraRechargemealResponse;
import com.likes.common.mybatis.entity.TraRechargemeal;
import com.likes.common.mybatis.entity.TraRechargemealExample;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TraRechargemealService {

    int countByExample(TraRechargemealExample example);

    int deleteByExample(TraRechargemealExample example);

    int deleteByPrimaryKey(Long mealid);

    int insert(TraRechargemeal record);

    int insertSelective(TraRechargemeal record);

    TraRechargemeal selectOneByExample(TraRechargemealExample example);

    List<TraRechargemeal> selectByExample(TraRechargemealExample example);

    TraRechargemeal selectByPrimaryKey(Long mealid);

    int updateByExampleSelective(TraRechargemeal record, TraRechargemealExample example);

    int updateByExample(TraRechargemeal record, TraRechargemealExample example);

    int updateByPrimaryKeySelective(TraRechargemeal record);

    int updateByPrimaryKey(TraRechargemeal record);

    Page<TraRechargemealResponse> traRechargemealList(TraRechargemealReq req, RowBounds rowBounds);

    int findAllTotal();

    List<TraRechargemealResponse> getList(Integer num);
}
