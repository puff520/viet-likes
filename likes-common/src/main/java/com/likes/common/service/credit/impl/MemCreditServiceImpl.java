package com.likes.common.service.credit.impl;

import com.likes.common.constant.Constants;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.request.CreditRequest;
import com.likes.common.mybatis.entity.MemLogin;
import com.likes.common.mybatis.entity.MemberCredit;
import com.likes.common.mybatis.entity.MemberCreditDetail;
import com.likes.common.mybatis.mapper.MemLoginMapper;
import com.likes.common.mybatis.mapper.MemberCreditDetailMapper;
import com.likes.common.mybatis.mapper.MemberCreditMapper;
import com.likes.common.service.credit.MemCreditService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.util.redis.RedisBusinessUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MemCreditServiceImpl implements MemCreditService {

    @Resource
    private MemberCreditMapper memberCreditMapper;
    @Resource
    private MemberCreditDetailMapper memberCreditDetailMapper;
    @Resource
    private MemLoginService memLoginService;
    @Resource
    private MemLoginMapper memLoginMapper;


    @Override
    public boolean operation(CreditRequest request) {
//        MemberCreditDetail memberCreditDetail = new MemberCreditDetail();
//        memberCreditDetail.setIntegral(request.getIntegral());
//        memberCreditDetail.setMemberNo(request.getMemNo());
//        memberCreditDetail.setType(request.getOperationType());
//        if (request.getOperationType().equals(2)) {
//            memberCreditDetail.setIntegral(-request.getIntegral());
//        }
//        memberCreditDetail.setSource(request.getSource());
//        memberCreditDetail.setCreateTime(new Date());
//        memberCreditDetail.setUpdateUser("system");
//        int row = memberCreditDetailMapper.insertSelective(memberCreditDetail);
//        if (row > 0) {
//            int row2 = memberCreditMapper.updateMemberCreditByMemNo(memberCreditDetail.getIntegral(), request.getMemNo());
//            if (row2 <= 0) {
//                throw new BusinessException("设置会员积分出错");
//            }
//
//        }
//        queryAndForbidden(request.getMemNo());
        return true;

    }

    @Override
    public boolean initMemberCredit(String memberNo) {
        MemberCredit memberCredit = new MemberCredit();
        memberCredit.setCreateTime(new Date());
        memberCredit.setIntegral(60);
        memberCredit.setMemberNo(memberNo);
        memberCredit.setUpdateUser("system");
        return memberCreditMapper.insertSelective(memberCredit) > 0;
    }

    @Override
    public Integer selectCreditByMemNo(String memNo) {
        MemberCredit memberCredit = new MemberCredit();
        memberCredit.setMemberNo(memNo);
        MemberCredit memberCredit1 = memberCreditMapper.selectOne(memberCredit);
        if (memberCredit1 == null) {
            return 0;
        }
//        forbiddenMember(memberCredit1);
        return memberCredit1.getIntegral();
    }


    private void queryAndForbidden(String accno) {
        MemberCredit memberCredit = new MemberCredit();
        memberCredit.setMemberNo(accno);
        MemberCredit memberCredit1 = memberCreditMapper.selectOne(memberCredit);
        forbiddenMember(memberCredit1);
    }


    private void forbiddenMember(MemberCredit memberCredit) {
        MemLogin memLogin = memLoginService.selectByAccno(memberCredit.getMemberNo());
        if (memLogin != null && memberCredit.getIntegral() <= 0) {
            if (memLogin.getAccstatus().equals(Constants.ACCSTATUS_1)) {
                memLogin.setAccstatus(Constants.ACCSTATUS_9);
                memLoginMapper.updateByPrimaryKeySelective(memLogin);
                RedisBusinessUtil.clearUserLoginInfo(memberCredit.getMemberNo());
                RedisBusinessUtil.delUserAttention(memberCredit.getMemberNo());
            }
        }
    }


}
