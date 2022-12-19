package com.likes.common.service.member.impl;

import com.likes.common.model.LoginUser;
import com.likes.common.mybatis.entity.MemBankaccount;
import com.likes.common.mybatis.entity.MemBankaccountExample;
import com.likes.common.mybatis.entity.MemLogin;
import com.likes.common.mybatis.mapper.MemBankaccountMapper;
import com.likes.common.mybatis.mapperext.member.MemBankaccountMapperExt;
import com.likes.common.service.member.MemBankaccountService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemBankaccountServiceImpl implements MemBankaccountService {

    @Resource
    private MemBankaccountMapperExt memBankaccountMapperExt;

    @Resource
    private MemBankaccountMapper memBankaccountMapper;

    @Resource
    private MemLoginService memLoginService;

    /**
     * 根据familyid获取银行卡信息
     */
    @Override
    public MemBankaccount findByFamilyId(Long familyid) {
        return memBankaccountMapperExt.findByFamilyId(familyid);
    }

    /**
     * 根据accno获取银行卡信息
     */
    @Override
    public MemBankaccount findByAccno(String accno) {
        return memBankaccountMapperExt.findByAccno(accno);
    }

    /**
     * 插入银行卡信息
     */
    @Override
    public int insertBank(MemBankaccount memBankaccount) {
        return memBankaccountMapperExt.insertBank(memBankaccount);
    }

    /**
     * 根据accno和类型获取银行卡信息
     */
    @Override
    public MemBankaccount findBankByAccno(MemBankaccount param) {
        return memBankaccountMapperExt.findBankByAccno(param);
    }

    @Override
    public MemBankaccount findBankByAccno(String accno) {
        MemBankaccountExample example = new MemBankaccountExample();
        example.createCriteria().andAccnoEqualTo(accno);
        return memBankaccountMapper.selectOneByExample(example);
    }

    @Override
    public MemBankaccount findBankByAddress(String address) {
        MemBankaccountExample example = new MemBankaccountExample();
        example.createCriteria().andBankaddressEqualTo(address);
        return memBankaccountMapper.selectOneByExample(example);
    }

    @Override
    public Map<String, Object> findPayPassAndAccount(LoginUser loginUser) {
        Map<String, Object> resultMap = new HashMap<>(2);
        MemBankaccountExample example = new MemBankaccountExample();
        example.createCriteria().andAccnoEqualTo(loginUser.getAccno());
        MemBankaccount memBankaccount = memBankaccountMapper.selectOneByExample(example);
        if (memBankaccount != null && memBankaccount.getCheckstatus().equals(8)) {
            resultMap.put("isBind", true);
            resultMap.put("memBankaccount", memBankaccount);
        } else {
            resultMap.put("isBind", false);
        }
        MemLogin memLogin = new MemLogin();
        memLogin.setAcclogin(loginUser.getAcclogin());
        MemLogin login = memLoginService.getMemLoginByParam(memLogin);
        if (login != null && !StringUtils.isBlank(login.getPaypassword())) {
            resultMap.put("isPayPass", true);
        } else {
            resultMap.put("isPayPass", false);
        }
        return resultMap;
    }

    /**
     * 删除银行卡信息
     */
    @Override
    public int doDelAllBank(MemBankaccount delBankaccountparam) {
        return memBankaccountMapperExt.doDelAllBank(delBankaccountparam);
    }

    @Override
    public int reset(MemBankaccount bankaccount) {
        return memBankaccountMapper.updateByPrimaryKeySelective(bankaccount);
    }
}
