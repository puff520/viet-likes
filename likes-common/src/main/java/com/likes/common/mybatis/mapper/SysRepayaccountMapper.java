package com.likes.common.mybatis.mapper;


import com.likes.common.model.AgentAccountDO;
import com.likes.common.model.request.RepayuserRechargeReq;
import com.likes.common.mybatis.entity.SysRepayaccount;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface SysRepayaccountMapper {
    int deleteByPrimaryKey(Long bankid);

    int insert(SysRepayaccount record);

    int insertSelective(SysRepayaccount record);

    SysRepayaccount selectByPrimaryKey(Long bankid);

    int updateByPrimaryKeySelective(SysRepayaccount record);

    int updateByPrimaryKey(SysRepayaccount record);

    List<SysRepayaccount> selectByBankName(RepayuserRechargeReq repayuserRechargeReq);

    List<SysRepayaccount> selectSelective(SysRepayaccount sysRepayaccount);

    Page<AgentAccountDO> selectAccountList(String nickname, RowBounds rowBounds);
}