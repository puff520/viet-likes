package com.likes.common.service.money;

import com.likes.common.mybatis.entity.TraRechargeaudit;
import com.likes.common.mybatis.entity.TraRechargeauditExample;

import java.util.List;

public interface TraRechargeauditService {
    int countByExample(TraRechargeauditExample example);

    int deleteByExample(TraRechargeauditExample example);

    int deleteByPrimaryKey(Long recauditid);

    int insert(TraRechargeaudit record);

    int insertSelective(TraRechargeaudit record);

    TraRechargeaudit selectOneByExample(TraRechargeauditExample example);

    List<TraRechargeaudit> selectByExample(TraRechargeauditExample example);

    TraRechargeaudit selectByPrimaryKey(Long recauditid);

    int updateByExampleSelective(TraRechargeaudit record, TraRechargeauditExample example);

    int updateByExample(TraRechargeaudit record, TraRechargeauditExample example);

    int updateByPrimaryKeySelective(TraRechargeaudit record);

    int updateByPrimaryKey(TraRechargeaudit record);
}
