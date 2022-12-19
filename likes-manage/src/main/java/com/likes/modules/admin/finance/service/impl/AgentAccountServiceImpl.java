package com.likes.modules.admin.finance.service.impl;


import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.AgentAccountDO;
import com.likes.common.model.AgentAccountDetailDO;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.AgentAccountRequest;
import com.likes.common.mybatis.entity.SysRepayaccount;
import com.likes.common.mybatis.mapper.SysRepayaccountMapper;
import com.likes.common.util.BeanUtils;
import com.likes.modules.admin.finance.service.AgentAccountService;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class AgentAccountServiceImpl implements AgentAccountService {


    @Resource
    private SysRepayaccountMapper sysRepayaccountMapper;

    @Override
    public Long createAccount(AgentAccountRequest request, LoginUser loginUser) {
        // 检验参数
        this.validateParams(request);

        // 删除字符串两边的空格
        this.trimWhitespace(request);

        SysRepayaccount repayAccount = new SysRepayaccount();
        BeanUtils.copyProperties(request, repayAccount);
        BigDecimal bd = new BigDecimal(0);
        repayAccount.setBankid(null);
        repayAccount.setMinamt(bd);
        repayAccount.setMaxamt(bd);
        repayAccount.setStopamt(bd);
        repayAccount.setCreateUser(loginUser.getAccno());
        repayAccount.setUpdateUser(loginUser.getAccno());
        sysRepayaccountMapper.insertSelective(repayAccount);

        return repayAccount.getBankid();
    }

    @Override
    public int deleteAccount(Long bankid, LoginUser loginUser) {
        if (bankid == null || 0 == bankid) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "银行账户ID为空");
        }

        SysRepayaccount repayAccount = new SysRepayaccount();
        repayAccount.setBankid(bankid);
        repayAccount.setDelete(true);
        repayAccount.setUpdateUser(loginUser.getAccno());
        int result = sysRepayaccountMapper.updateByPrimaryKeySelective(repayAccount);
        if (result == 0) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "银行账户不存在");
        }
        return result;
    }

    @Override
    public int updateAccount(AgentAccountRequest request, LoginUser loginUser) {

        if (request.getBankid() == null || 0 == request.getBankid()) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "银行账户ID为空");
        }

        // 检验参数
        this.validateParams(request);

        // 删除字符串两边的空格
        this.trimWhitespace(request);

        SysRepayaccount repayAccount = new SysRepayaccount();
        BeanUtils.copyProperties(request, repayAccount);
        repayAccount.setUpdateUser(loginUser.getAccno());
        int result = sysRepayaccountMapper.updateByPrimaryKeySelective(repayAccount);
        if (result == 0) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "银行账户不存在");
        }
        return result;
    }

    @Override
    public int UpdateAccountStatus(Long bankid, Integer status, LoginUser loginUser) {
        if (bankid == null || 0 == bankid) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "银行账户id为空");
        }
        if (status == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "状态为空");
        }
        if (!Constants.STATUS_0.equals(status) && !Constants.STATUS_9.equals(status)) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "状态无效");
        }

        SysRepayaccount repayAccount = new SysRepayaccount();
        repayAccount.setBankid(bankid);
        repayAccount.setStatus(status);
        repayAccount.setUpdateUser(loginUser.getAccno());
        int result = sysRepayaccountMapper.updateByPrimaryKeySelective(repayAccount);
        if (result == 0) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "银行账户不存在");
        }
        return result;
    }

    @Override
    public AgentAccountDetailDO accountDetail(Long bankid) {
        SysRepayaccount repayAccount = sysRepayaccountMapper.selectByPrimaryKey(bankid);
        AgentAccountDetailDO accountDetail = new AgentAccountDetailDO();
        BeanUtils.copyProperties(repayAccount, accountDetail);
        return accountDetail;
    }

    @Override
    public PageResult accountList(String nickname, PageBounds page) {
        if (nickname != null) {
            nickname = nickname.trim();
        }
        Page<AgentAccountDO> list = sysRepayaccountMapper.selectAccountList(nickname, page.toRowBounds());
        return PageResult.getPageResult(page, list);
    }

    private void validateParams(AgentAccountRequest request) {
        if (StringUtils.isBlank(request.getNickname())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "收款名称为空");
        }
        if (StringUtils.isBlank(request.getBankname())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "银行名称为空");
        }
        if (StringUtils.isBlank(request.getAccountno())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "银行账号为空");
        }
        if (request.getBankaddress() == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "开户银行网点为空");
        }
        if (request.getAccountname() == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "收款人为空");
        }
        if (request.getStatus() == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "状态为空");
        }
        // 检查状态有效性
        if (!Constants.STATUS_0.equals(request.getStatus()) && !Constants.STATUS_9.equals(request.getStatus())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "状态无效");
        }
    }

    private void trimWhitespace(AgentAccountRequest request) {
        request.setNickname(request.getNickname().trim());
        request.setBankname(request.getBankname().trim());
        request.setAccountno(request.getAccountno().trim());
        request.setBankaddress(request.getBankaddress().trim());
        request.setAccountname(request.getAccountname().trim());
    }
}
