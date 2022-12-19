package com.likes.common.mybatis.mapper;


import com.likes.common.model.MemberArtRepayOrderDO;
import com.likes.common.model.MemberArtRepayOrderQuery;
import com.likes.common.model.TraArtRepayOrderDO;
import com.likes.common.model.request.TraArtRepayOrderQuery;
import com.likes.common.mybatis.entity.TraArtrepayorder;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface TraArtrepayorderMapper {
    int deleteByPrimaryKey(Long artorderid);

    int insert(TraArtrepayorder record);

    int insertSelective(TraArtrepayorder record);

    TraArtrepayorder selectByPrimaryKey(Long artorderid);

    int updateByPrimaryKeySelective(TraArtrepayorder record);

    int updateByPrimaryKey(TraArtrepayorder record);

    Page<TraArtRepayOrderDO> selectAgentArtRepayOrderList(TraArtRepayOrderQuery query, RowBounds rowBounds);

    Page<MemberArtRepayOrderDO> selectMemberRepayOrderList(MemberArtRepayOrderQuery query, RowBounds rowBounds);

	TraArtrepayorder selectByOrderid(@Param("orderid") Long orderid, @Param("logintype") Integer logintype, @Param("ordertype") Integer ordertype);
}