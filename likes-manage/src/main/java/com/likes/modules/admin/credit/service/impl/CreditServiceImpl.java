package com.likes.modules.admin.credit.service.impl;

import com.likes.common.model.LoginUser;
import com.likes.common.model.request.CreditRequest;
import com.likes.common.mybatis.entity.MemberCredit;
import com.likes.common.mybatis.entity.MemberCreditDetail;
import com.likes.common.mybatis.mapper.MemberCreditDetailMapper;
import com.likes.common.mybatis.mapper.MemberCreditMapper;
import com.likes.modules.admin.credit.service.CreditService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CreditServiceImpl implements CreditService {

    @Resource
    private MemberCreditMapper memberCreditMapper;
    @Resource
    private MemberCreditDetailMapper memberCreditDetailMapper;


    @Override
    public boolean operation(CreditRequest request, LoginUser loginUser) {
//        MemberCreditDetail memberCreditDetail = new MemberCreditDetail();
//        memberCreditDetail.setIntegral(request.getIntegral());
//        memberCreditDetail.setMemberNo(request.getMemNo());
//        memberCreditDetail.setType(1);
//        memberCreditDetail.setSource("后台增加");
//        memberCreditDetail.setCreateTime(new Date());
//
//        int row = memberCreditDetailMapper.insertSelective(memberCreditDetail);

        return true;
    }

    @Override
    public Integer selectCreditByMemNo(String memNo) {
        MemberCredit memberCredit = new MemberCredit();
        memberCredit.setMemberNo(memNo);
        MemberCredit memberCredit1 = memberCreditMapper.selectOne(memberCredit);
        if (memberCredit1 == null) {
            return 0;
        }
        return memberCredit1.getIntegral();
    }

}
