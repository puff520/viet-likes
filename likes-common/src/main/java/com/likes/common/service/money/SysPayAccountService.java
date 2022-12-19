package com.likes.common.service.money;

import com.likes.common.model.dto.sys.SysPayaccountDO;
import com.likes.common.mybatis.entity.SysPayaccount;
import com.likes.common.mybatis.entity.SysPayaccountExample;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.List;

public interface SysPayAccountService {

    int countByExample(SysPayaccountExample example);

    int deleteByExample(SysPayaccountExample example);

    int deleteByPrimaryKey(Long bankid);

    int insert(SysPayaccount record);

    int insertSelective(SysPayaccount record);

    SysPayaccount selectOneByExample(SysPayaccountExample example);

    List<SysPayaccount> selectByExample(SysPayaccountExample example);

    SysPayaccount selectByPrimaryKey(Long bankid);

    int updateByExampleSelective(SysPayaccount record, SysPayaccountExample example);

    int updateByExample(SysPayaccount record, SysPayaccountExample example);

    int updateByPrimaryKeySelective(SysPayaccount record);

    int updateByPrimaryKey(SysPayaccount record);

    List<SysPayaccountDO> getPayInfo(Integer accounttype, Integer memlevel);

    Page<SysPayaccountDO> getList(SysPayaccount sysPayaccount, RowBounds rowBounds);

    SysPayaccount getRepeat(SysPayaccount memBankaccount);

    void updateTotalAmount(String accno, Long bankid, BigDecimal amount);

    void clearTotalAmount(String accno, Long bankid);
}
