package com.likes.modules.admin.finance.service.impl;

import com.likes.common.constant.Constants;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.sys.SysThreepaysetDO;
import com.likes.common.mybatis.entity.SysPayprovider;
import com.likes.common.mybatis.entity.SysThreepayset;
import com.likes.common.mybatis.mapper.SysThreepaysetMapper;
import com.likes.common.mybatis.mapperext.sys.SysThreepaysetMapperExt;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.money.SysPayproviderService;
import com.likes.common.util.BeanUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.finance.service.SysThreepaysetService;
import com.github.pagehelper.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class SysThreepaysetServiceImpl extends BaseServiceImpl implements SysThreepaysetService {

    @Resource
    private SysThreepaysetMapper sysThreepaysetMapper;
    @Resource
    private SysThreepaysetMapperExt sysThreepaysetMapperExt;
    @Resource
    private SysPayproviderService sysPayproviderService;

    @Override
    public Long doSaveSysThreepayset(SysThreepayset sysThreepayset, LoginUser loginUser) {
        if (null == sysThreepayset.getProviderid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1160.getCode(), "商家ID为空");
        }
        if (null == sysThreepayset.getMinamt() || sysThreepayset.getMinamt().doubleValue() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1159.getCode(), "金额最小值不符合规范");
        }
        if (null == sysThreepayset.getMaxamt() || sysThreepayset.getMaxamt().doubleValue() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1158.getCode(), "金额最大值不符合规范");
        }
        if (StringUtils.isNotEmpty(sysThreepayset.getMinmemlevel())
                && Integer.parseInt(sysThreepayset.getMinmemlevel()) < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1157.getCode(), "等级最小值不符合规范");
        }
        if (StringUtils.isNotEmpty(sysThreepayset.getMaxmemlevel())
                && Integer.parseInt(sysThreepayset.getMaxmemlevel()) < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1156.getCode(), "等级最大值不符合规范");
        }
        if (null == sysThreepayset.getStopamt()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1155.getCode(), "停用金额为空");
        }
        if (StringUtils.isEmpty(sysThreepayset.getEasyrecharge())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1154.getCode(), "快捷充值套餐金额为空");
        }
        if (null == sysThreepayset.getIsinput()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1153.getCode(), "是否允许输入金额为空");
        }
        if (null == sysThreepayset.getStatus()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1152.getCode(), "状态为空");
        }
        if (StringUtils.isEmpty(sysThreepayset.getTpayname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1151.getCode(), "名称为空");
        }
        if (StringUtils.isEmpty(sysThreepayset.getPaytype())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1150.getCode(), "支付方式为空");
        }
        if (StringUtils.isEmpty(sysThreepayset.getPaycode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1149.getCode(), "支付标识为空");
        }
        if (StringUtils.isEmpty(sysThreepayset.getPayvalue())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1148.getCode(), "支付设置值为空");
        }

        sysThreepayset.setCreateUser(loginUser.getAccno());
        sysThreepayset.setUpdateUser(loginUser.getAccno());

        sysThreepaysetMapper.insertSelective(sysThreepayset);
        return sysThreepayset.getTpaysetid();
    }

    @Override
    public Long doUpdateSysThreepayset(SysThreepayset sysThreepayset, LoginUser loginUser) {
        if (null == sysThreepayset.getTpaysetid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1161.getCode(), "ID为空");
        }
        if (null == sysThreepayset.getProviderid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1160.getCode(), "商家ID为空");
        }
        if (null == sysThreepayset.getMinamt() || sysThreepayset.getMinamt().doubleValue() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1159.getCode(), "金额最小值不符合规范");
        }
        if (null == sysThreepayset.getMaxamt() || sysThreepayset.getMaxamt().doubleValue() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1158.getCode(), "金额最大值不符合规范");
        }
        if (StringUtils.isNotEmpty(sysThreepayset.getMinmemlevel())
                && Integer.parseInt(sysThreepayset.getMinmemlevel()) < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1157.getCode(), "等级最小值不符合规范");
        }
        if (StringUtils.isNotEmpty(sysThreepayset.getMaxmemlevel())
                && Integer.parseInt(sysThreepayset.getMaxmemlevel()) < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1156.getCode(), "等级最大值不符合规范");
        }
        if (null == sysThreepayset.getStopamt()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1155.getCode(), "停用金额为空");
        }
        if (StringUtils.isEmpty(sysThreepayset.getEasyrecharge())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1154.getCode(), "快捷充值套餐金额为空");
        }
        if (null == sysThreepayset.getIsinput()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1153.getCode(), "是否允许输入金额为空");
        }
        if (null == sysThreepayset.getStatus()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1152.getCode(), "状态为空");
        }
        if (StringUtils.isEmpty(sysThreepayset.getTpayname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1151.getCode(), "名称为空");
        }
        if (StringUtils.isEmpty(sysThreepayset.getPaytype())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1150.getCode(), "支付方式为空");
        }
        if (StringUtils.isEmpty(sysThreepayset.getPaycode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1149.getCode(), "支付标识为空");
        }
        if (StringUtils.isEmpty(sysThreepayset.getPayvalue())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1148.getCode(), "支付设置值为空");
        }

        if (StringUtils.isEmpty(sysThreepayset.getMinmemlevel())) {
            sysThreepayset.setMinmemlevel(null);
        }
        if (StringUtils.isEmpty(sysThreepayset.getMaxmemlevel())) {
            sysThreepayset.setMaxmemlevel(null);
        }

        sysThreepayset.setIsDelete(false);
        sysThreepayset.setCreateTime(new Date());
        sysThreepayset.setCreateUser(loginUser.getAccno());
        sysThreepayset.setUpdateUser(loginUser.getAccno());
        sysThreepayset.setUpdateTime(new Date());

        SysThreepayset s = sysThreepaysetMapper.selectByPrimaryKey(sysThreepayset.getTpaysetid());
        if (s != null) {
            // sysThreepayset.setStatus(s.getStatus());
            BeanUtils.copyProperties(sysThreepayset, s);
            if (StringUtils.isEmpty(sysThreepayset.getMinmemlevel())) {
                s.setMinmemlevel(null);
            }
            if (StringUtils.isEmpty(sysThreepayset.getMaxmemlevel())) {
                s.setMaxmemlevel(null);
            }
            sysThreepaysetMapper.updateByPrimaryKey(s);
        }

        return sysThreepayset.getTpaysetid();
    }

    @Override
    public String doDelSysThreepayset(SysThreepayset sysPayaccount, LoginUser loginUser) {
        if (null == sysPayaccount.getTpaysetid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10001.getCode(), "ID为空");
        }

        sysPayaccount.setIsDelete(true);
        sysPayaccount.setUpdateUser(loginUser.getAccno());
        sysThreepaysetMapper.updateByPrimaryKeySelective(sysPayaccount);
        return Constants.SUCCESS_MSG;
    }

    @Override
    public String doUpdateSysThreepaysetStatus(SysThreepayset sysThreepayset, LoginUser loginUser) {
        if (null == sysThreepayset.getTpaysetid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1109.getCode(), "ID为空");
        }
        if (null == sysThreepayset.getStatus()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1108.getCode(), "状态为空");
        }
        if (!sysThreepayset.getStatus().equals(Constants.STATUS_0) && !sysThreepayset.getStatus().equals(Constants.STATUS_9)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10007.getCode(), "状态值不对");
        }

        if (sysThreepayset.getStatus().equals(Constants.STATUS_0)) {
            SysThreepayset s = sysThreepaysetMapper.selectByPrimaryKey(sysThreepayset.getTpaysetid());
            if (s != null) {
                Long providerid = s.getProviderid();
                SysPayprovider sysPayprovider = sysPayproviderService.selectByPrimaryKey(providerid);
                if (sysPayprovider != null) {
                    if (sysPayprovider.getStatus().equals(Constants.STATUS_9)) {
                        throw new BusinessException(StatusCode.LIVE_ERROR_10009.getCode(), "支付商已经禁用");
                    }
                }
            }
        }

        sysThreepayset.setIsDelete(false);
        sysThreepayset.setUpdateUser(loginUser.getAccno());
        sysThreepaysetMapper.updateByPrimaryKeySelective(sysThreepayset);

        return Constants.SUCCESS_MSG;
    }

    @Override
    public PageResult sysThreepaysetlist(SysThreepayset sysThreepayset, PageBounds page) {
		/*if (null == sysThreepayset.getProviderid()) {
			throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "支付商ID为空");
		}*/
        Page<SysThreepaysetDO> list = sysThreepaysetMapperExt.getlist(sysThreepayset, page.toRowBounds());
        return PageResult.getPageResult(page, list);
    }

    @Override
    public PageResult getSysPayproviderList(SysPayprovider sysPayprovider, PageBounds page) {
        Page<SysPayprovider> list = sysPayproviderService.getList(sysPayprovider, page.toRowBounds());
        return PageResult.getPageResult(page, list);
    }

    @Override
    public Long doSaveSysPayprovider(SysPayprovider sysPayprovider, LoginUser loginUser) {
        if (StringUtils.isEmpty(sysPayprovider.getProvider())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "支付商名称为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getProvidercode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1190.getCode(), "支付商code为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getPaydns())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "支付域名为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getBackurl())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "回调地址为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getAllowips())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1118.getCode(), "回调IP为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getTorderurl())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "三方支付订单查询地址为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getPaygateway())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1103.getCode(), "支付网关为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getAccountno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1104.getCode(), "商户ID为空");
        }
        if (null == sysPayprovider.getStatus()) {
            sysPayprovider.setStatus(0);
        }

        // 检查名字是否相同
        SysPayprovider s = sysPayproviderService.getRepeate(sysPayprovider);
        if (s != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1105.getCode(), "名称重复");
        }

        sysPayprovider.setCreateUser(loginUser.getAccno());
        sysPayprovider.setUpdateUser(loginUser.getAccno());
        sysPayproviderService.insertSelective(sysPayprovider);
        return sysPayprovider.getProviderid();
    }

    @Override
    public Long doUpdateSysPayprovider(SysPayprovider sysPayprovider, LoginUser loginUser) {
        if (null == sysPayprovider.getProviderid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10001.getCode(), "ID为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getProvider())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "支付商名称为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getProvidercode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1190.getCode(), "支付商code为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getPaydns())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "支付域名为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getBackurl())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "回调地址为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getAllowips())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1118.getCode(), "回调IP为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getTorderurl())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "三方支付订单查询地址为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getPaygateway())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1103.getCode(), "支付网关为空");
        }
        if (StringUtils.isEmpty(sysPayprovider.getAccountno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1104.getCode(), "商户ID为空");
        }

        if (null == sysPayprovider.getStatus()) {
            sysPayprovider.setStatus(0);
        }

        // 检查名字是否相同
        SysPayprovider s = sysPayproviderService.getRepeate(sysPayprovider);
        if (s != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1105.getCode(), "名称重复");
        }

        sysPayprovider.setUpdateUser(loginUser.getAccno());
        sysPayproviderService.updateByPrimaryKeySelective(sysPayprovider);
        RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_SYS_PAYPROVIDER_BY_TPAYSETID);
        return sysPayprovider.getProviderid();
    }

    @Override
    public String doDelSysPayprovider(SysPayprovider sysPayprovider, LoginUser loginUser) {
        if (null == sysPayprovider.getProviderid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10001.getCode(), "ID为空");
        }
        sysPayprovider.setIsDelete(true);
        sysPayprovider.setUpdateUser(loginUser.getAccno());
        sysPayproviderService.updateByPrimaryKeySelective(sysPayprovider);
        return Constants.SUCCESS_MSG;
    }

    @Override
    public String doUpdateSysPayproviderStatus(SysPayprovider sysPayprovider, LoginUser loginUser) {
        if (null == sysPayprovider.getProviderid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10001.getCode(), "ID为空");
        }
        if (null == sysPayprovider.getStatus()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1108.getCode(), "状态为空");
        }
        if (!sysPayprovider.getStatus().equals(Constants.STATUS_0) && !sysPayprovider.getStatus().equals(Constants.STATUS_9)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10007.getCode(), "状态值不对");
        }
        SysPayprovider old = sysPayproviderService.selectByPrimaryKey(sysPayprovider.getProviderid());
        if (ObjectUtils.isEmpty(old)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10009.getCode(), "记录不存在");
        }
        old.setStatus(sysPayprovider.getStatus());
        // sysPayprovider.setStatus(sysPayprovider.getStatus());
        old.setUpdateUser(loginUser.getAccno());
        sysPayproviderService.updateByPrimaryKeySelective(sysPayprovider);


        //支付商 禁用  也要 支付方式禁用
        if (sysPayprovider.getStatus().equals(Constants.STATUS_9)) {
            SysThreepayset sysThreepayset = new SysThreepayset();
            sysThreepayset.setProviderid(sysPayprovider.getProviderid());
            List<SysThreepaysetDO> list = sysThreepaysetMapperExt.getAllList(sysThreepayset);
            if (CollectionUtils.isNotEmpty(list)) {
                for (SysThreepaysetDO sysThreepaysetDO : list) {
                    Double rechargeamt = sysThreepaysetDO.getRechargeamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue();
                    Double stopamt = sysThreepaysetDO.getStopamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue();
                    //只修改 充值金额 小于 停用金额的通道
                    if (rechargeamt < stopamt) {
                        SysThreepayset s = new SysThreepayset();
                        s.setTpaysetid(sysThreepaysetDO.getTpaysetid());
                        s.setStatus(sysPayprovider.getStatus());
                        s.setUpdateUser(loginUser.getAccno());
                        sysThreepaysetMapper.updateByPrimaryKeySelective(s);
                    }
                }
            }
        }


        return Constants.SUCCESS_MSG;
    }

    @Override
    public List<SysPayprovider> getAllsysPayproviderlist(SysPayprovider sysPayprovider) {
        List<SysPayprovider> list = sysPayproviderService.getAllsysPayproviderlist(sysPayprovider);
        return list;
    }

}
