package com.likes.modules.admin.finance.service.impl;

import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.sys.SysPayaccountDO;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.likes.common.mybatis.entity.SysPayaccount;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.money.SysPayAccountService;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.util.BeanUtils;
import com.likes.modules.admin.finance.service.ManageSysPayaccountService;
import com.github.pagehelper.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ManageSysPayaccountServiceImpl extends BaseServiceImpl implements ManageSysPayaccountService {


    @Resource
    private SysPayAccountService sysPayAccountMapperService;
    @Resource
    private SysBusParamService sysBusParamService;

    @Override
    public PageResult getList(SysPayaccount sysPayaccount, PageBounds page) {
        Page<SysPayaccountDO> list = sysPayAccountMapperService.getList(sysPayaccount, page.toRowBounds());
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(o -> {
                // o.setBankaddress(null);
                // o.setAccountname(null);
                // 账号类型 1支付宝 2微信 3银联
                int accounttype = o.getAccounttype();
                if (accounttype == 1) {
                    o.setAccounttypename("支付宝账户");
					/*String name = o.getAccountno().substring(0, 3) + "***"
							+ o.getAccountno().substring(6, o.getAccountno().length());
					o.setAccountno(name);*/
                } else if (accounttype == 2) {
                    o.setAccounttypename("微信账户");
					/*String name = o.getAccountno().substring(0, 3) + "***"
							+ o.getAccountno().substring(6, o.getAccountno().length());
					o.setAccountno(name);
					*/
                } else if (accounttype == 3) {
                    o.setAccounttypename("银联");
					/*String name = o.getAccountno().substring(o.getAccountno().length() - 4, o.getAccountno().length());
					o.setAccountno(name);*/
                    // 查询银行
                    SysBusparameter sysBusparameter = sysBusParamService.selectByBusparamcode(o.getBankname());
                    if (sysBusparameter != null) {
                        o.setBanknamealias(sysBusparameter.getBusparamname());
                    }
                }
            });
        }
        return PageResult.getPageResult(page, list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long doSavePayaccount(SysPayaccount memBankaccount, LoginUser loginUser) {
        savePayaccountVerifyParams(memBankaccount);

        // 检查同类型的账号是否存在
        SysPayaccount re = sysPayAccountMapperService.getRepeat(memBankaccount);
        if (re != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1110.getCode(), "该类型的账号已经存在");
        }
        memBankaccount.setIsinput(0);
        // memBankaccount.setStatus(Constants.STATUS_0);
        memBankaccount.setCreateUser(loginUser.getAccno());
        memBankaccount.setUpdateUser(loginUser.getAccno());
        sysPayAccountMapperService.insertSelective(memBankaccount);
        return memBankaccount.getBankid();
    }

    private void savePayaccountVerifyParams(SysPayaccount memBankaccount) {
        if (null == memBankaccount.getAccounttype()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "类型为空");
        }
        if (StringUtils.isEmpty(memBankaccount.getAccountname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "姓名为空");
        }
        if (memBankaccount.getAccountname().contains("*")) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1110.getCode(), "请输入正确的姓名");
        }
        if (StringUtils.isEmpty(memBankaccount.getAccountno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1103.getCode(), "账号为空");
        }
        // 账号类型 1支付宝 2微信 3银联
        checkAliWechatAccount(memBankaccount);
        checkeNetBank(memBankaccount);

        if (null == memBankaccount.getMinamt() || memBankaccount.getMinamt().doubleValue() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1159.getCode(), "金额最小值不符合规范");
        }
        if (null == memBankaccount.getMaxamt() || memBankaccount.getMaxamt().doubleValue() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1158.getCode(), "金额最大值不符合规范");
        }
        if (StringUtils.isNotEmpty(memBankaccount.getMinmemlevel())
                && Integer.parseInt(memBankaccount.getMinmemlevel()) < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1157.getCode(), "等级最小值不符合规范");
        }
        if (StringUtils.isNotEmpty(memBankaccount.getMaxmemlevel())
                && Integer.parseInt(memBankaccount.getMaxmemlevel()) < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1156.getCode(), "等级最大值不符合规范");
        }
        if (null == memBankaccount.getStopamt()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1155.getCode(), "停用金额为空");
        }
        if (StringUtils.isEmpty(memBankaccount.getEasyrecharge())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1154.getCode(), "快捷充值套餐金额为空");
        }

        if (null == memBankaccount.getStatus()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1152.getCode(), "状态为空");
        }
    }

    private void checkeNetBank(SysPayaccount memBankaccount) {
        if (memBankaccount.getAccounttype() == 3) {
            if (StringUtils.isEmpty(memBankaccount.getBankname())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1110.getCode(), "银行为空");
            }
            if (StringUtils.isEmpty(memBankaccount.getBankaddress())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1112.getCode(), "开户行为空");
            }
        } else if (memBankaccount.getAccounttype() == 2) {
            if (StringUtils.isEmpty(memBankaccount.getNickname())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1109.getCode(), "昵称为空");
            }
        } else if (memBankaccount.getAccounttype() == 1) {
            if (StringUtils.isEmpty(memBankaccount.getNickname())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1109.getCode(), "昵称为空");
            }
        }
    }

    private void checkAliWechatAccount(SysPayaccount memBankaccount) {
        if (memBankaccount.getAccountno().contains("*") || memBankaccount.getAccountno().length() < 10) {
            if (memBankaccount.getAccounttype() == 3) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1111.getCode(), "请输入正确的银行卡号");
            } else if (memBankaccount.getAccounttype() == 2) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1111.getCode(), "请输入微信账号");
            } else if (memBankaccount.getAccounttype() == 1) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1111.getCode(), "请输入支付宝账号");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String doDelPayaccount(SysPayaccount sysPayaccount, LoginUser loginUser) {
        if (null == sysPayaccount.getBankid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10001.getCode(), "ID为空");
        }

        sysPayaccount.setIsDelete(true);
        sysPayaccount.setUpdateUser(loginUser.getAccno());
        sysPayAccountMapperService.updateByPrimaryKeySelective(sysPayaccount);
        return Constants.SUCCESS_MSG;
    }

    @Override
    public Long doUpdatePayaccount(SysPayaccount memBankaccount, LoginUser loginUser) {
        updatePayaccountVerifyParams(memBankaccount);
        SysPayaccount s = sysPayAccountMapperService.selectByPrimaryKey(memBankaccount.getBankid());
        if (s == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1152.getCode(), "无此账号信息");
        }
        if (memBankaccount.getStopamt().compareTo(s.getTotalAmount()) == -1) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1152.getCode(), "停用金额 必须大于 已充值金额");
        }

        // 检查同类型的账号是否存在
        SysPayaccount re = sysPayAccountMapperService.getRepeat(memBankaccount);
        if (re != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1110.getCode(), "该类型的账号已经存在");
        }
        memBankaccount.setIsinput(0);
        // memBankaccount.setStatus(Constants.STATUS_0);
        memBankaccount.setIsDelete(false);
        memBankaccount.setCreateTime(new Date());
        memBankaccount.setCreateUser(loginUser.getAccno());
        memBankaccount.setUpdateTime(new Date());
        memBankaccount.setUpdateUser(loginUser.getAccno());


        BeanUtils.copyProperties(memBankaccount, s);

        if (StringUtils.isEmpty(memBankaccount.getMinmemlevel())) {
            s.setMinmemlevel(null);
        }
        if (StringUtils.isEmpty(memBankaccount.getMaxmemlevel())) {
            s.setMaxmemlevel(null);
        }
        s.setSysStatus(null);
        s.setTotalAmount(null);
        sysPayAccountMapperService.updateByPrimaryKeySelective(s);


        return memBankaccount.getBankid();
    }

    private void updatePayaccountVerifyParams(SysPayaccount memBankaccount) {
        if (null == memBankaccount.getBankid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10001.getCode(), "ID为空");
        }
        if (null == memBankaccount.getAccounttype()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "类型为空");
        }
        if (StringUtils.isEmpty(memBankaccount.getAccountname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "姓名为空");
        }
        if (memBankaccount.getAccountname().contains("*")) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1110.getCode(), "请输入正确的姓名");
        }
        if (StringUtils.isEmpty(memBankaccount.getAccountno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1103.getCode(), "账号为空");
        }
        // 账号类型 1支付宝 2微信 3银联
        checkAliWechatAccount(memBankaccount);
        checkeNetBank(memBankaccount);


        if (null == memBankaccount.getMinamt() || memBankaccount.getMinamt().doubleValue() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1159.getCode(), "金额最小值不符合规范");
        }
        if (null == memBankaccount.getMaxamt() || memBankaccount.getMaxamt().doubleValue() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1158.getCode(), "金额最大值不符合规范");
        }
        if (StringUtils.isNotEmpty(memBankaccount.getMinmemlevel())
                && Integer.parseInt(memBankaccount.getMinmemlevel()) < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1157.getCode(), "等级最小值不符合规范");
        }
        if (StringUtils.isNotEmpty(memBankaccount.getMaxmemlevel())
                && Integer.parseInt(memBankaccount.getMaxmemlevel()) < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1156.getCode(), "等级最大值不符合规范");
        }
        if (null == memBankaccount.getStopamt()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1155.getCode(), "停用金额为空");
        }
        if (StringUtils.isEmpty(memBankaccount.getEasyrecharge())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1154.getCode(), "快捷充值套餐金额为空");
        }
        if (null == memBankaccount.getStatus()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1152.getCode(), "状态为空");
        }
    }

    @Override
    public String doUpdateStatus(SysPayaccount sysPayaccount, LoginUser loginUser) {
        if (null == sysPayaccount.getBankid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1109.getCode(), "ID为空");
        }
        if (null == sysPayaccount.getStatus()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1108.getCode(), "状态为空");
        }

        sysPayaccount.setIsDelete(false);
        sysPayaccount.setUpdateUser(loginUser.getAccno());
        sysPayAccountMapperService.updateByPrimaryKeySelective(sysPayaccount);

        return Constants.SUCCESS_MSG;
    }

    @Override
    public String cleanTotalAmount(SysPayaccount sysCdn, LoginUser loginUser) {
        if (sysCdn.getBankid() == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1108.getCode(), "请选择公司入款账户");
        }

        sysPayAccountMapperService.clearTotalAmount(loginUser.getAccno(), sysCdn.getBankid());
        return Constants.SUCCESS_MSG;
    }

}
