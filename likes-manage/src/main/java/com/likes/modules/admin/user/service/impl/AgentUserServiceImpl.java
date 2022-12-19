package com.likes.modules.admin.user.service.impl;


import com.likes.common.constant.Constants;
import com.likes.common.enums.LoginUserTypeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.AgentUserDetailDO;
import com.likes.common.model.AgentUserQuery;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.AgentUserDO;
import com.likes.common.model.dto.member.MemLoginDO;
import com.likes.common.model.request.AgentUserRequest;
import com.likes.common.mybatis.entity.MemLogin;
import com.likes.common.mybatis.entity.MemRepayuser;
import com.likes.common.mybatis.mapper.MemLoginMapper;
import com.likes.common.mybatis.mapper.MemRepayuserMapper;
import com.likes.common.mybatis.mapperext.member.MemLoginMapperExt;
import com.likes.common.service.code.UniqueCodeService;
import com.likes.common.util.BeanUtils;
import com.likes.common.util.CommonFunction;
import com.likes.common.util.RandomUtil;
import com.likes.modules.admin.user.service.AgentUserService;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class AgentUserServiceImpl implements AgentUserService {

    @Resource
    private MemRepayuserMapper memRepayuserMapper;

    @Resource
    private MemLoginMapper memLoginMapper;
    @Resource
    private MemLoginMapperExt memLoginMapperExt;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createUser(AgentUserRequest request, LoginUser loginUser) {
        // 检验参数
        this.validateParams(request);

        // 删除字符串两边的空格
        this.trimWhitespace(request);

        MemLogin ml = memLoginMapperExt.findByAccloginRegister(request.getAcclogin());
        if (ml != null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "账号已存在");
        }

        // 检测会员暱称是否重复
        MemRepayuser repeatNickName = memRepayuserMapper.getRepeateNickname(request.getNickname());
        if (repeatNickName != null) {
            throw new BusinessException(StatusCode.ACCOUNT_NICKNAME_REPEAT.getCode(), "昵称已存在");
        }

        // 会员账号生成
        String accNo = RandomUtil.uuid();

        // 登陆用户
        MemLoginDO memLogin = new MemLoginDO();
        memLogin.setLoginnum(0);
        memLogin.setAcclogin(request.getAcclogin());
        memLogin.setAccno(accNo);
        //TODO 会员登录账号类型
        memLogin.setLogintype(LoginUserTypeEnum.AGENT.getCode());
        memLogin.setPasswordmd5(request.getPassword());
        if (Constants.STATUS_9.equals(request.getStatus())) {
            // 代充人禁止登录
            memLogin.setAccstatus(Constants.ACCSTATUS_9);
        } else {
            // 代充人正常登录
            memLogin.setAccstatus(Constants.ACCSTATUS_1);
        }
        int insertFlag = memLoginMapperExt.insertMemLogin(memLogin);
        if (insertFlag == 0) {
            throw new BusinessException(StatusCode.ACCOUNT_REPEAT.getCode(), "该账号已存在");
        }

        // 代充人信息
        MemRepayuser memRepayuser = new MemRepayuser();
        BeanUtils.copyProperties(request, memRepayuser);
        memRepayuser.setRepaymemid(null);
        memRepayuser.setAccno(accNo);
        memRepayuser.setNickname(request.getNickname());
        memRepayuser.setMemgold(new BigDecimal(0));
        memRepayuser.setRepaynums(0L);
        memRepayuser.setCreateUser(loginUser.getAccno());
        memRepayuser.setUpdateUser(loginUser.getAccno());
        memRepayuserMapper.insertSelective(memRepayuser);

        return memRepayuser.getRepaymemid();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(AgentUserRequest request, LoginUser loginUser) {

        if (request.getRepaymemid() == null || 0 == request.getRepaymemid()) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "代充人ID为空");
        }

        // 检验参数
        this.validateParams(request);

        // 删除字符串两边的空格
        this.trimWhitespace(request);

        MemRepayuser oldMemRepayuser = memRepayuserMapper.selectByPrimaryKey(request.getRepaymemid());
        if (oldMemRepayuser == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "代充人不存在");
        }

        MemLogin oldMemLogin = memLoginMapperExt.findByAccno(oldMemRepayuser.getAccno());
        boolean memLoginFlag = false;
        // 会员账号
        if (!oldMemLogin.getAcclogin().equalsIgnoreCase(request.getAcclogin())) {
            MemLogin duplicateMem = memLoginMapperExt.findByAccloginRegister(request.getAcclogin());
            if (duplicateMem != null) {
                throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "账号已存在");
            }
            memLoginFlag = true;
        }
        // 登陆密码
        if (!oldMemLogin.getPasswordmd5().equalsIgnoreCase(request.getPassword())) {
            memLoginFlag = true;
        }

        // 检测会员暱称是否重复
        MemRepayuser repeatNickName = memRepayuserMapper.getRepeateNickname(request.getNickname());
        if (repeatNickName != null && !oldMemRepayuser.getAccno().equals(repeatNickName.getAccno())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "昵称已存在");
        }

        // 更新登陆用户
        if (memLoginFlag) {
            MemLogin updateMemLogin = new MemLogin();
            updateMemLogin.setLoginid(oldMemLogin.getLoginid());
            updateMemLogin.setAcclogin(request.getAcclogin());
            updateMemLogin.setPasswordmd5(request.getPassword());
            memLoginMapper.updateByPrimaryKeySelective(updateMemLogin);
        }

        // 更新代充人信息
        MemRepayuser updateMemRepayuser = new MemRepayuser();
        BeanUtils.copyProperties(request, updateMemRepayuser);
        updateMemRepayuser.setRepaymemid(oldMemRepayuser.getRepaymemid());
        updateMemRepayuser.setUpdateUser(loginUser.getAccno());
        updateMemRepayuser.setNickname(request.getNickname());
        updateMemRepayuser.setMobileno(request.getMobileno());
        int result = memRepayuserMapper.updateByPrimaryKeySelective(updateMemRepayuser);

        // 代充人账号登录状态是否更新
        if (!request.getStatus().equals(oldMemRepayuser.getStatus())) {
            // 检查账号是否存在
            MemLogin memLogin = memLoginMapperExt.selectByAccno(oldMemRepayuser.getAccno());
            if (memLogin == null) {
                throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "登录账号不存在");
            }

            // 更新账号登录状态
            MemLogin updateMemLogin = new MemLogin();
            updateMemLogin.setLoginid(memLogin.getLoginid());
            if (Constants.STATUS_9.equals(request.getStatus())) {
                // 代充人禁止登录
                updateMemLogin.setAccstatus(Constants.ACCSTATUS_9);
            } else {
                // 代充人正常登录
                updateMemLogin.setAccstatus(Constants.ACCSTATUS_1);
            }
            result = memLoginMapper.updateByPrimaryKeySelective(updateMemLogin);
            if (result == 0) {
                throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "代充人更新失败");
            }
        }

        return result;
    }

    /**
     * 代充人启用禁用
     *
     * @param repayMemId 代充人ID
     * @param status     状态
     * @param loginUser  登录用户
     * @return
     */
    @Override
    @Transactional
    public int UpdateUserStatus(Long repayMemId, Integer status, LoginUser loginUser) {
        if (repayMemId == null || 0 == repayMemId) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "代充人ID为空");
        }
        if (status == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "状态为空");
        }
        if (!Constants.STATUS_0.equals(status) && !Constants.STATUS_9.equals(status)) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "状态无效");
        }

        MemRepayuser memRepayuser = memRepayuserMapper.selectByPrimaryKey(repayMemId);
        if (memRepayuser == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "代充人不存在");
        }
        if (status.equals(memRepayuser.getStatus())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "状态已更新");
        }

        // 更新代充人信息
        MemRepayuser updateMemRepayuser = new MemRepayuser();
        updateMemRepayuser.setRepaymemid(repayMemId);
        updateMemRepayuser.setStatus(status);
        updateMemRepayuser.setUpdateUser(loginUser.getAccno());
        int result = memRepayuserMapper.updateByPrimaryKeySelective(updateMemRepayuser);

        if (result == 0) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "代充人不存在");
        }

        // 检查账号是否存在
        MemLogin memLogin = memLoginMapperExt.selectByAccno(memRepayuser.getAccno());
        if (memLogin == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "登录账号不存在");
        }

        // 更新账号登录状态
        MemLogin updateMemLogin = new MemLogin();
        updateMemLogin.setLoginid(memLogin.getLoginid());
        if (Constants.STATUS_9.equals(status)) {
            // 代充人禁止登录
            updateMemLogin.setAccstatus(Constants.ACCSTATUS_9);
        } else {
            // 代充人正常登录
            updateMemLogin.setAccstatus(Constants.ACCSTATUS_1);
        }
        result = memLoginMapper.updateByPrimaryKeySelective(updateMemLogin);
        if (result == 0) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "代充人更新失败");
        }

        return result;
    }

    @Override
    public AgentUserDetailDO userDetail(Long repayMemId) {
        return memRepayuserMapper.selectUserDetailByRepayMemId(repayMemId);
    }

    @Override
    public PageResult userList(AgentUserQuery query, PageBounds page) {
        if (query != null) {
            query.setAcclogin(query.getAcclogin() == null ? null : query.getAcclogin().trim());
            query.setNickname(query.getNickname() == null ? null : query.getNickname().trim());
        }
        Page<AgentUserDO> list = memRepayuserMapper.selectUserList(query, page.toRowBounds());
        return PageResult.getPageResult((int) list.getTotal(), page, list);
    }

    private void validateParams(AgentUserRequest request) {

        if (StringUtils.isBlank(request.getAcclogin())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "账号为空");
        }
        if (StringUtils.isBlank(request.getPassword())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "密码为空");
        }
        if (StringUtils.isBlank(request.getNickname())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "暱称为空");
        }
        if (StringUtils.isBlank(request.getQq()) && StringUtils.isEmpty(request.getWebchat())
                && StringUtils.isEmpty(request.getAlipay())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "QQ/微信/支付宝必填写一项");
        }
        if (StringUtils.isBlank(request.getMobileno())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "手机号为空");
        }
        if (request.getOnlinedates() == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "营业时间起为空");
        }
        if (request.getOnlinedatee() == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "营业时间止为空");
        }
        if (request.getDiscountrag() == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "充值优惠为空");
        }
        if (request.getStatus() == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "状态为空");
        }
        // 检查密码是否过于简单
        List<String> jPwd = CommonFunction.jiandanPwd();
        if (jPwd.contains(request.getPassword())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "您设置的密码过于简单");
        }
        // 检查账号有效性
        if (!CommonFunction.numbersAndletters(request.getAcclogin())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "账号必须为数字或者字母的有效字符");
        }
        // 检查状态有效性
        if (!Constants.STATUS_0.equals(request.getStatus()) && !Constants.STATUS_9.equals(request.getStatus())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "状态无效");
        }
    }

    private void trimWhitespace(AgentUserRequest request) {
        request.setAcclogin(request.getAcclogin().trim());
        request.setPassword(request.getPassword().trim());
        request.setNickname(request.getNickname().trim());
        request.setQq(StringUtils.isEmpty(request.getQq()) ? "" : request.getQq().trim());
        request.setWebchat(StringUtils.isEmpty(request.getWebchat()) ? "" : request.getWebchat().trim());
        request.setAlipay(StringUtils.isEmpty(request.getAlipay()) ? "" : request.getAlipay().trim());
        request.setMobileno(request.getMobileno().trim());
    }
}
