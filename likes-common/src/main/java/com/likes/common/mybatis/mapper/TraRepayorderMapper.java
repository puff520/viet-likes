package com.likes.common.mybatis.mapper;


import com.likes.common.model.TraRepayOrderDO;
import com.likes.common.model.request.TraRepayOrderQuery;
import com.likes.common.mybatis.entity.TraRepayorder;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface TraRepayorderMapper {
    int deleteByPrimaryKey(Long reorderid);

    int insert(TraRepayorder record);

    int insertSelective(TraRepayorder record);

    TraRepayorder selectByPrimaryKey(Long reorderid);

    int updateByPrimaryKeySelective(TraRepayorder record);

    int updateByPrimaryKey(TraRepayorder record);

    Page<TraRepayOrderDO> selectOrderList(TraRepayOrderQuery query, RowBounds rowBounds);
}