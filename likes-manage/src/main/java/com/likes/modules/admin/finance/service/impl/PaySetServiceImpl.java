package com.likes.modules.admin.finance.service.impl;

import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.PaySet;
import com.likes.common.model.request.PaySetRequest;
import com.likes.common.mybatis.entity.SysPayset;
import com.likes.common.service.money.SysPaysetService;
import com.likes.common.util.DateUtils;
import com.likes.common.util.JsonUtil;
import com.likes.modules.admin.finance.service.PaySetService;
import com.github.pagehelper.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaySetServiceImpl implements PaySetService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysPaysetService sysPaysetService;


    @Override
    public PageResult getList(PaySetRequest paySetRequest, PageBounds page) {
        Page<SysPayset> list = sysPaysetService.getList(paySetRequest, page.toRowBounds());
        return PageResult.getPageResult(page, list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String doSave(PaySetRequest req, LoginUser loginUser) {
        if (StringUtils.isEmpty(req.getSetname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10001.getCode(), "设置名称为空");
        }
        if (null == req.getFreechargenums()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "单日出款免手续费次数为空");
        }
        if (null == req.getServicecharge()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "单笔出款手续费为空");
        }
        if (null == req.getMaxchargeamt()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1103.getCode(), "单笔出款上限金额为空");
        }
        if (null == req.getMinchargeamt()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1104.getCode(), "单笔出款下限金额为空");
        }

        if (StringUtils.isEmpty(req.getOnlinepayset())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1105.getCode(), "线上设定为空");
        }
        if (StringUtils.isEmpty(req.getCompanypayset())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1106.getCode(), "公司设定为空");
        }

        //检查相同的名称的稽核设定是否存在
        SysPayset s = sysPaysetService.getRepeate(req.getSetname().trim());
        if (null != s) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1107.getCode(), req.getSetname() + "已存在");
        }

        if (null == req.getStatus()) {
            req.setStatus(0);
        }
        if (null == req.getSortby()) {
            req.setSortby(0);
        }

        if (req.getStatus() == 0) {
            //禁用所有
            sysPaysetService.doAllJingyong2(req);
        }

        String onlinepaysetStr = req.getOnlinepayset();
        String companypaysetStr = req.getCompanypayset();

        //解析线上
        PaySet onlinepayset = JsonUtil.fromJson(onlinepaysetStr, PaySet.class);
        if (onlinepayset == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1107.getCode(), "线上设定为空");
        }
        if (null == onlinepayset.getRechargetype()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1108.getCode(), "线上设定款优惠频率为空");
        }
        if (null == onlinepayset.getGiftrate() || onlinepayset.getGiftrate() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1109.getCode(), "线上设定额外赠送率为空");
        }
        if (null == onlinepayset.getMaxgift() || onlinepayset.getMaxgift() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1110.getCode(), "线上设定优惠上线为空");
        }
        if (null == onlinepayset.getAuditfree() || onlinepayset.getAuditfree() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1111.getCode(), "线上设定常态性稽核放宽额度为空");
        }
        if (null == onlinepayset.getAuditper() || onlinepayset.getAuditper() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1112.getCode(), "线上设定常态性稽核（百分比）%为空");
        }
        if (null == onlinepayset.getAdministrative() || onlinepayset.getAdministrative() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1113.getCode(), "线上设定常态性稽核行政费率（百分值）%为空");
        }

        //解析公司
        PaySet companypayset = JsonUtil.fromJson(companypaysetStr, PaySet.class);
        if (companypayset == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1107.getCode(), "公司设定为空");
        }
        if (null == companypayset.getRechargetype()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1108.getCode(), "公司设定款优惠频率为空");
        }
        if (null == companypayset.getGiftrate() || companypayset.getGiftrate() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1109.getCode(), "公司设定额外赠送率为空");
        }
        if (null == companypayset.getMaxgift() || companypayset.getMaxgift() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1110.getCode(), "公司设定优惠上线为空");
        }
        if (null == companypayset.getAuditfree() || companypayset.getAuditfree() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1111.getCode(), "公司设定常态性稽核放宽额度为空");
        }
        if (null == companypayset.getAuditper() || companypayset.getAuditper() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1112.getCode(), "公司设定常态性稽核（百分比）%为空");
        }
        if (null == companypayset.getAdministrative() || companypayset.getAdministrative() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1113.getCode(), "公司设定常态性稽核行政费率（百分值）%为空");
        }

        //此时插入 2跳 相同名称的数据
        SysPayset onlineSysPayset = new SysPayset();
        onlineSysPayset.setSetname(req.getSetname());
        onlineSysPayset.setSettype(1);
        onlineSysPayset.setRechargetype(onlinepayset.getRechargetype());
        onlineSysPayset.setGiftrate(new BigDecimal(onlinepayset.getGiftrate()));
        onlineSysPayset.setMaxgift(new BigDecimal(onlinepayset.getMaxgift()));
        onlineSysPayset.setAuditper(new BigDecimal(onlinepayset.getAuditper()));
        onlineSysPayset.setAuditfree(new BigDecimal(onlinepayset.getAuditfree()));
        onlineSysPayset.setAdministrative(new BigDecimal(onlinepayset.getAdministrative()));
        onlineSysPayset.setFreechargenums(req.getFreechargenums());
        onlineSysPayset.setServicecharge(req.getServicecharge());
        onlineSysPayset.setMaxchargeamt(req.getMaxchargeamt());
        onlineSysPayset.setMinchargeamt(req.getMinchargeamt());
        onlineSysPayset.setStatus(req.getStatus());
        onlineSysPayset.setSortby(req.getSortby());
        onlineSysPayset.setCreateUser(loginUser.getAccno());
        onlineSysPayset.setUpdateUser(loginUser.getAccno());

        SysPayset companySysPayset = new SysPayset();
        companySysPayset.setSetname(req.getSetname());
        companySysPayset.setSettype(2);
        companySysPayset.setRechargetype(companypayset.getRechargetype());
        companySysPayset.setGiftrate(new BigDecimal(companypayset.getGiftrate()));
        companySysPayset.setMaxgift(new BigDecimal(companypayset.getMaxgift()));
        companySysPayset.setAuditper(new BigDecimal(companypayset.getAuditper()));
        companySysPayset.setAuditfree(new BigDecimal(companypayset.getAuditfree()));
        companySysPayset.setAdministrative(new BigDecimal(companypayset.getAdministrative()));
        companySysPayset.setFreechargenums(req.getFreechargenums());
        companySysPayset.setServicecharge(req.getServicecharge());
        companySysPayset.setMaxchargeamt(req.getMaxchargeamt());
        companySysPayset.setMinchargeamt(req.getMinchargeamt());
        companySysPayset.setStatus(req.getStatus());
        companySysPayset.setSortby(req.getSortby());
        companySysPayset.setCreateUser(loginUser.getAccno());
        companySysPayset.setUpdateUser(loginUser.getAccno());

        sysPaysetService.insertSelective(onlineSysPayset);
        sysPaysetService.insertSelective(companySysPayset);

        return Constants.SUCCESS_MSG;

    }

   /* @Override
    @Transactional(rollbackFor = Exception.class)
    public Long doUpdate(SysPayset sysPayset, LoginUser loginUser) {
        if (null == sysPayset.getPaysetid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "ID为空");
        }

        if (null == sysPayset.getAuditper()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10002.getCode(), "常态性稽覈为空");
        }
        if (sysPayset.getAuditper().doubleValue() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10003.getCode(), "常态性稽覈不能小于0");
        }
        if (null == sysPayset.getAuditfree()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10004.getCode(), "常态性稽覈放宽额度为空");
        }
        if (sysPayset.getAuditfree().doubleValue() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10005.getCode(), "常态性稽覈放宽额度不能小于0");
        }
        if (null == sysPayset.getAdministrative()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10006.getCode(), "常态性稽覈行政费率为空");
        }
        if (sysPayset.getAdministrative().doubleValue() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10007.getCode(), "常态性稽覈行政费率不能小于0");
        }
        if (null == sysPayset.getFreechargenums()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10008.getCode(), "免手续费单数为空");
        }
        if (sysPayset.getFreechargenums() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10009.getCode(), "免手续费单数不能小于0");
        }
        if (null == sysPayset.getServicecharge()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10010.getCode(), "单笔手续费为空");
        }
        if (sysPayset.getServicecharge().doubleValue() < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10011.getCode(), "单笔手续费不能小于0");
        }
        if (null == sysPayset.getStatus()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10012.getCode(), "状态为空");
        }
        if (null == sysPayset.getSortby()) {
            sysPayset.setSortby(0);
        }

        if (sysPayset.getStatus() == Constants.STATUS_0) {
            //只保存一个正在使用的
            //则其他全部修改为未启用 
            sysPaysetService.doAllJingyong(sysPayset);
        }

        sysPayset.setUpdateUser(loginUser.getAccno());
        sysPaysetService.updateByPrimaryKeySelective(sysPayset);
        return sysPayset.getPaysetid();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long doUpdateStatus(SysPayset sysPayset, LoginUser loginUser) {
        if (null == sysPayset.getPaysetid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "ID为空");
        }
        if (null == sysPayset.getStatus()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10012.getCode(), "状态为空");
        }
        if (sysPayset.getStatus() != Constants.STATUS_0 && sysPayset.getStatus() != Constants.STATUS_9) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10007.getCode(), "状态值不对");
        }
        if (sysPayset.getStatus() == Constants.STATUS_0) {
            //只保存一个正在使用的
            //则其他全部修改为未启用 
            sysPaysetService.doAllJingyong(sysPayset);
        }
        sysPayset.setUpdateUser(loginUser.getAccno());
        sysPaysetService.updateByPrimaryKeySelective(sysPayset);
        return sysPayset.getPaysetid();
    }

    @Override
    //@Transactional(rollbackFor = Exception.class)
    public String doDel(SysPayset sysPayset, LoginUser loginUser) {
        if (null == sysPayset.getPaysetid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "ID为空");
        }

        SysPayset sPayset = sysPaysetService.selectByPrimaryKey(sysPayset.getPaysetid());
        if (sPayset != null
                && !sPayset.getIsDelete()
                && sPayset.getStatus() == 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10012.getCode(), "启用状态不能删除");
        }

        sysPayset.setIsDelete(true);
        sysPayset.setStatus(Constants.STATUS_9);
        sysPayset.setUpdateUser(loginUser.getAccno());
        sysPaysetService.updateByPrimaryKeySelective(sysPayset);
        return Constants.SUCCESS_MSG;
    }*/

    @Override
    public PageResult getList2(PaySetRequest paySetRequest, PageBounds page) {
        List<SysPayset> allList = sysPaysetService.getAllList(paySetRequest);
        if (CollectionUtils.isNotEmpty(allList)) {
            //根据名称分组 还得保持排序 /(ㄒoㄒ)/~~
            //Map<NameAge, List<Person>> map = people.collect(Collectors.groupingBy(Person::getNameAge));
            LinkedHashMap<String, List<SysPayset>> linkedHashMap = new LinkedHashMap<String, List<SysPayset>>();
            for (SysPayset sysPayset : allList) {
                if (!linkedHashMap.containsKey(sysPayset.getSetname())) {
                    List<SysPayset> list = new ArrayList<SysPayset>();
                    list.add(sysPayset);
                    linkedHashMap.put(sysPayset.getSetname(), list);
                } else {
                    List<SysPayset> list = linkedHashMap.get(sysPayset.getSetname());
                    list.add(sysPayset);
                    linkedHashMap.put(sysPayset.getSetname(), list);
                }
            }

            //获取所有的 key
            List<List<SysPayset>> list = new ArrayList<List<SysPayset>>();
            for (Map.Entry<String, List<SysPayset>> entry : linkedHashMap.entrySet()) {
                //System.out.println("key:" + entry.getKey() + "   value:" + entry.getValue());
                list.add(entry.getValue());
            }

            List<PaySetRequest> result = new ArrayList<PaySetRequest>();
            for (List<SysPayset> ss : list) {
                PaySetRequest p = new PaySetRequest();
                SysPayset s1 = ss.get(0);
                //SysPayset s2 = ss.get(1);
                p.setSetname(s1.getSetname());
                p.setSortby(s1.getSortby());
                p.setStatus(s1.getStatus());
                p.setFreechargenums(s1.getFreechargenums());
                p.setServicecharge(s1.getServicecharge());
                p.setMaxchargeamt(s1.getMaxchargeamt());
                p.setMinchargeamt(s1.getMinchargeamt());
                p.setCreatedate(DateUtils.formatDate(s1.getCreateTime()));
                for (SysPayset sysPayset : ss) {
                    PaySet one = new PaySet();
                    one.setPaysetid(sysPayset.getPaysetid());
                    one.setRechargetype(sysPayset.getRechargetype());
                    one.setGiftrate(sysPayset.getGiftrate().doubleValue());
                    one.setMaxgift(sysPayset.getMaxgift().doubleValue());
                    one.setAuditper(sysPayset.getAuditper().doubleValue());
                    one.setAuditfree(sysPayset.getAuditfree().doubleValue());
                    one.setAdministrative(sysPayset.getAdministrative().doubleValue());
                    Integer settype = sysPayset.getSettype();
                    one.setSettype(settype);
                    if (settype == 1) {
                        p.setOnlinepayset(JsonUtil.toJson(one));
                    } else {
                        p.setCompanypayset(JsonUtil.toJson(one));
                    }
                }
                result.add(p);
            }

            if ((page.getPageNo() * page.getPageSize()) > result.size()) {
                result = result.subList((page.getPageNo() - 1) * page.getPageSize(), list.size());
            } else {
                result = result.subList((page.getPageNo() - 1) * page.getPageSize(), page.getPageNo() * page.getPageSize());
            }

            return PageResult.getPageResult(result.size(), page, result);
        }
        return PageResult.getPageResult(0, page, allList);
    }

    @Override
    public String doDel2(PaySetRequest req, LoginUser loginUser) {
        if (StringUtils.isEmpty(req.getSetname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "名称为空");
        }

        //获取一个 传递 参数 
        SysPayset sysPayset = sysPaysetService.getRepeate(req.getSetname());
        if (sysPayset == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "不存在");
        }

        if (sysPayset != null
                && sysPayset.getStatus() == 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10012.getCode(), "启用状态不能删除");
        }

        sysPayset.setIsDelete(true);
        sysPayset.setStatus(Constants.STATUS_9);
        sysPayset.setUpdateUser(loginUser.getAccno());
        sysPaysetService.doUpdate2(sysPayset);
        return Constants.SUCCESS_MSG;
    }

    @Override
    public String doUpdateStatus2(PaySetRequest req, LoginUser loginUser) {
        if (StringUtils.isEmpty(req.getSetname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "名称为空");
        }
        if (!req.getStatus().equals(Constants.STATUS_0) && !req.getStatus().equals(Constants.STATUS_9)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10007.getCode(), "状态值不对");
        }

        //获取一个 传递 参数 
        SysPayset sysPayset = sysPaysetService.getRepeate(req.getSetname());
        if (sysPayset == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "不存在");
        }

        if (req.getStatus().equals(Constants.STATUS_0)) {
            //只保存一个正在使用的
            //则其他全部修改为未启用 
            sysPaysetService.doAllJingyong2(req);
        }

        sysPayset.setIsDelete(false);
        sysPayset.setStatus(req.getStatus());
        sysPayset.setUpdateUser(loginUser.getAccno());
        sysPayset.setUpdateTime(new Date());
        sysPaysetService.doUpdate2(sysPayset);
        return Constants.SUCCESS_MSG;
    }

}
