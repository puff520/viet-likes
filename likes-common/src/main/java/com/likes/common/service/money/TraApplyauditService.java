package com.likes.common.service.money;

import com.likes.common.model.dto.TraApplyauditDO;
import com.likes.common.mybatis.entity.TraApplyaudit;
import com.likes.common.mybatis.entity.TraApplyauditExample;

import java.util.List;

public interface TraApplyauditService {

    int countByExample(TraApplyauditExample example);

    int deleteByExample(TraApplyauditExample example);

    int deleteByPrimaryKey(Long apauditid);

    int insert(TraApplyaudit record);

    int insertSelective(TraApplyaudit record);

    TraApplyaudit selectOneByExample(TraApplyauditExample example);

    List<TraApplyaudit> selectByExample(TraApplyauditExample example);

    TraApplyaudit selectByPrimaryKey(Long apauditid);

    int updateByExampleSelective(TraApplyaudit record, TraApplyauditExample example);

    int updateByExample(TraApplyaudit record, TraApplyauditExample example);

    int updateByPrimaryKeySelective(TraApplyaudit record);

    int updateByPrimaryKey(TraApplyaudit record);

    List<TraApplyauditDO> getByApycid(Long apycid);

    List<TraApplyaudit> getListById(Long apyid);

    void doDelJihe(Long apycid);
}
