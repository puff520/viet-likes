package com.likes.common.mybatis.mapper;

import com.likes.common.model.AgentUserDetailDO;
import com.likes.common.model.AgentUserQuery;
import com.likes.common.model.dto.AgentUserDO;
import com.likes.common.model.dto.pay.RepayuserDO;
import com.likes.common.mybatis.entity.MemRepayuser;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface MemRepayuserMapper {
    int deleteByPrimaryKey(Long repaymemid);

    int insert(MemRepayuser record);

    int insertSelective(MemRepayuser record);

    MemRepayuser selectByPrimaryKey(Long repaymemid);

    int updateByPrimaryKeySelective(MemRepayuser record);

    int updateByPrimaryKey(MemRepayuser record);

    MemRepayuser selectByAccno(String accno);

    MemRepayuser getMemRepayuser(MemRepayuser memRepayuser);

    MemRepayuser getMemRepayuserByAccno(String accno);

    int updateMemgold(@Param("oldmemgold") BigDecimal oldmemgold, @Param("amount") BigDecimal amount, @Param("accno") String accno);

    List<RepayuserDO> getRGRechargemealAccount(@Param("num") Integer num);

    AgentUserDetailDO selectUserDetailByRepayMemId(Long repaymemid);

    Page<AgentUserDO> selectUserList(AgentUserQuery query, RowBounds rowBounds);

    MemRepayuser selectByAccNo(String accno);

    MemRepayuser getRepeateNickname(@Param("nickname") String nickname);
}