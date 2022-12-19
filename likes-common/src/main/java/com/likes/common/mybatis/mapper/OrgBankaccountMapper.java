package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.OrgBankaccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrgBankaccountMapper {
    int deleteByPrimaryKey(Long bankaccid);

    int insert(OrgBankaccount record);

    int insertSelective(OrgBankaccount record);

    OrgBankaccount selectByPrimaryKey(Long bankaccid);

    int updateByPrimaryKeySelective(OrgBankaccount record);

    int updateByPrimaryKey(OrgBankaccount record);
}