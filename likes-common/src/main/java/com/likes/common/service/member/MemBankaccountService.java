package com.likes.common.service.member;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageResult;
import com.likes.common.mybatis.entity.MemBankaccount;
import com.likes.common.service.BaseService;

import java.util.Map;

public interface MemBankaccountService extends BaseService {

    /**
     * 根据familyid获取银行卡信息
     */
    MemBankaccount findByFamilyId(Long familyid);

    /**
     * 根据accno获取银行卡信息
     */
    MemBankaccount findByAccno(String accno);

    /**
     * 插入银行卡信息
     */
    int insertBank(MemBankaccount memBankaccount);

    /**
     * 根据accno和类型获取银行卡信息
     */
    MemBankaccount findBankByAccno(MemBankaccount param);

    MemBankaccount findBankByAccno(String accno);

    MemBankaccount findBankByAddress(String address);

    Map<String,Object> findPayPassAndAccount(LoginUser loginUser);

    /**
     * 删除银行卡信息
     */
    int doDelAllBank(MemBankaccount delBankaccountparam);

    int reset(MemBankaccount bankaccount);
}
