package com.likes.common.service.member.impl;

import com.likes.common.constant.Constants;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.YueaboOperateEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.YuebaoChangeBO;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemBaseinfoExample;
import com.likes.common.mybatis.entity.MemGoldchange;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemBaseinfoWriteService;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.service.yuebao.YuebaoChangeService;
import com.likes.common.util.BeanUtils;
import com.likes.common.util.JsonUtil;
import com.likes.common.util.SnowflakeIdWorker;
import com.likes.common.util.redis.RedisLock;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

/**
 * @author lzy
 * @create 2018-08-27 9:52
 **/
@Service
public class MemBaseinfoWriteServiceImpl implements MemBaseinfoWriteService {
    private static final Logger logger = LoggerFactory.getLogger(MemBaseinfoWriteServiceImpl.class);
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private MemBaseinfoService memBaseinfoService;
    @Autowired
    private MemGoldchangeService memGoldchangeService;
    @Autowired
    private YuebaoChangeService yuebaoChangeService;

    /**
     * 修改用户余额
     *
     * @param change 改变记录
     */
    @Override
    public boolean updateUserBalance(MemGoldchangeDO change) throws RuntimeException {
        if (ObjectUtils.isEmpty(change)) {
            logger.error("{}.updateUserBalance 参数为空", getClass().getName());
            throw new BusinessException(StatusCode.LIVE_ERROR_999.getCode(),"请求参数错误");
        }
        if ((change.getUserId() == null || change.getUserId() < 1) && StringUtils.isBlank(change.getAccno())) {
            logger.error("{}.updateUserBalance 参数不正确：{}", getClass().getName(), JsonUtil.toJson(change));
            throw new BusinessException(StatusCode.LIVE_ERROR_997.getCode(),"请求参数错误");
        }
        if (change.getUserId() == null || change.getUserId() < 1) {
            MemBaseinfo m = memBaseinfoService.getUserByAccno(change.getAccno());
            if (ObjectUtils.isEmpty(m)) {
                logger.error("{}.updateUserBalance 对应用户不存在：{}", getClass().getName(), JsonUtil.toJson(change));
                throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(),"对应用户不存在");
            }
            change.setUserId(m.getMemid().intValue());
        }

        logger.info("{} updateUserBalance change info:{}", change.getUserId(), change);
        String userId = Integer.toString(change.getUserId());
        long begin = System.currentTimeMillis();
        RReadWriteLock lock = redissonClient.getReadWriteLock(RedisLock.UPDATE_USER_BALANCE_ + userId);

        try {
            // 写锁（等待时间10s，超时时间10S[自动解锁]，单位：秒）【设定超时时间，超时后自动释放锁，防止死锁】
            boolean bool = lock.writeLock().tryLock(100, 20, TimeUnit.SECONDS);
            // 判断是否获取到锁
            if (bool) {
                begin = System.currentTimeMillis();
                logger.info("用户修改余额拿到锁{}", userId);
                MemBaseinfo memBaseinfo = memBaseinfoService.selectByPrimaryKey(Long.valueOf(change.getUserId()));
                change.setAccno(memBaseinfo.getAccno());
                if (memBaseinfo == null) {
                    logger.info("{} updateUserBalance member is null,memBaseinfo: {}", userId, memBaseinfo);
                    throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(),"对应用户不存在");
                }
                // 获取变动金额（余额）
                BigDecimal currentBalance = memBaseinfo.getGoldnum();
                BigDecimal changeMoney = change.getQuantity() == null ? BigDecimal.ZERO : change.getQuantity();
                BigDecimal newBalance = currentBalance.add(changeMoney);
                // 获取变动记录值（只用户记录账变记录）
                BigDecimal showChange = change.getShowChange();
                showChange = showChange == null ? changeMoney : showChange;


                // 余额变动
                BigDecimal amount = changeMoney;
                //默认金额
                BigDecimal defaultAmount = getTradeOffAmount(null);
                // 待开奖金额 变动
                BigDecimal waitAmount = defaultAmount;
                // 投注变动
                BigDecimal bamount = defaultAmount;
                // 不可提变动
                BigDecimal namount = defaultAmount;
                // 提现变动
                BigDecimal wamount = defaultAmount;
                // 充值变动
                BigDecimal pamount = defaultAmount;

                // 修改累计充值金额
                if (change.getPayAmount() != null && change.getPayAmount().compareTo(BigDecimal.ZERO) != 0) {
                    pamount = getTradeOffAmount(change.getPayAmount());
                }
                // 修改累计提现金额
                BigDecimal withdrawalAmount = change.getWithdrawalAmount();
                if (withdrawalAmount != null && withdrawalAmount.compareTo(BigDecimal.ZERO) != 0) {
                    wamount = getTradeOffAmount(withdrawalAmount);
                }
                if (showChange.compareTo(BigDecimal.ZERO) != 0 || GoldchangeEnum.getChangeType().contains(change.getChangetype())) {
                    // 添加账变记录
                    MemGoldchange c = new MemGoldchange();
                    BeanUtils.copyProperties(change, c);
                    c.setQuantity(getTradeOffAmount(amount.multiply(new BigDecimal(Constants.CHONGZHIBILIE))));
                    c.setGoldnum(getTradeOffAmount(currentBalance));
                    c.setRecgoldnum(getTradeOffAmount(newBalance));
                    if (memBaseinfo.getNoWithdrawalAmount().compareTo(BigDecimal.ZERO) < 0) {
                        c.setPreCgdml(getTradeOffAmount(null));
                    } else {
                        c.setPreCgdml(getTradeOffAmount(memBaseinfo.getNoWithdrawalAmount()));
                    }
                    BigDecimal aftdml = memBaseinfo.getNoWithdrawalAmount().add(namount).compareTo(BigDecimal.ZERO) == -1 ? BigDecimal.ZERO : memBaseinfo.getNoWithdrawalAmount().add(namount);
                    c.setAfterCgdml(getTradeOffAmount(aftdml));
                    c.setSuperiorId(memBaseinfo.getSuperiorId());
                    c.setCreateTime(new Date());
                    c.setSnowSn(SnowflakeIdWorker.generateId());
                    begin = System.currentTimeMillis();
                    memGoldchangeService.insertSelective(c);
                    logger.info("{} updateUserBalance memGoldchangeMapper.insertSelective 充值帐变 耗时 time, {}", change.getUserId(), System.currentTimeMillis() - begin);
                }
                BigDecimal consumeAcmount = getTradeOffAmount(caclConsumeAmount(namount, change.getChangetype()));
                calcRechargeInfo(memBaseinfo.getAccno(), amount, change.getChangetype());
                List<Integer> typeList = new LinkedList<>();
//                typeList.add(101);
//                typeList.add(102);
//                typeList.add(103);
                typeList.add(200);
//                typeList.add(301);
//                typeList.add(302);
//                typeList.add(303);
                if(memBaseinfo.getLevel()> 0 && typeList.contains(change.getChangetype())){
                    logger.info("不增加余额 进入余额宝");
                    YuebaoChangeBO dto = new YuebaoChangeBO();
                    dto.setAccno(memBaseinfo.getAccno());
                    dto.setAssetCfgId(1L);
                    dto.setBuyAssetCfgId(1L);
                    dto.setChangeAmount(amount);
                    dto.setOperateEnum(YueaboOperateEnum.INTO);
                    dto.setIsSystemWay(true);
                    yuebaoChangeService.updateYuebaoAmount(dto);
                }else {
                    int i = memBaseinfoService.updateMemberAmount(amount, pamount, bamount, namount, consumeAcmount, wamount, waitAmount, memBaseinfo.getAccno(), memBaseinfo.getMemid());
                    if (i != 1) {
                        logger.error("{} updateUserBalance updateMemberAmount 更新余额失败. return:{}", change.getUserId(), i);
                        throw new BusinessException(StatusCode.OPERATION_FAILED.getCode(), "操作失败");
                    }
                }
                logger.info("{} updateUserBalance updateMemberAmount 耗时 time, {}", change.getUserId(), System.currentTimeMillis() - begin);
                return true;
            } else {
                logger.error("{} updateUserBalance 用户修改余额没拿到锁, 记录修改余额变动对象 {}", userId, change);
                throw new BusinessException(StatusCode.OPERATION_FAILED.getCode(),"操作失败");
            }
        } catch (Exception e) {
            logger.error("{} updateUserBalance occur error. change info:{}", change.getUserId(), change, e);
            throw new RuntimeException("用户修改余额出错", e.getCause());
        } finally {
            // 释放锁
            lock.writeLock().unlock();
            logger.info("{} updateUserBalance 用户修改余额释放锁", userId);
        }
    }

    /**
     * 根据不可提变动金额和帐变类型，计算 打码量(即消费金额)
     *
     * @param namount    不可提金额
     * @param changeType 帐变类型
     * @return 打码量
     */
    private BigDecimal caclConsumeAmount(BigDecimal namount, Integer changeType) {
        if (namount == null || namount.compareTo(BigDecimal.ZERO) != -1) {
            return BigDecimal.ZERO;
        }
//        if (changeType.equals(GoldchangeEnum.MANUALLY_SUB_DAMALIANG.getValue())) {
//            return BigDecimal.ZERO;
//        }
        return namount.multiply(BigDecimal.valueOf(-1));
    }

    /**
     * 处理充值、代理充值信息 首充，充值次数，最大充值金额
     *
     * @param accno
     * @param tradeOffAmount
     */
    private void calcRechargeInfo(String accno, BigDecimal tradeOffAmount, Integer changeType) {
        if (changeType == null || (!changeType.equals(GoldchangeEnum.RECHARGE.getValue()) && !changeType.equals(GoldchangeEnum.REPAY_INCOME_ORDER.getValue()))) {
            return;
        }
        MemBaseinfoExample membaseinfoExample = new MemBaseinfoExample();
        membaseinfoExample.createCriteria().andAccnoEqualTo(accno);
        MemBaseinfo membaseinfo = memBaseinfoService.selectOneByExample(membaseinfoExample);
        if (membaseinfo.getPayFirst() == null || membaseinfo.getPayFirst().compareTo(BigDecimal.ZERO) == 0) {
            membaseinfo.setPayFirst(tradeOffAmount);
        }
        if (membaseinfo.getPayMax() == null || membaseinfo.getPayMax().compareTo(tradeOffAmount) == -1) {
            membaseinfo.setPayMax(tradeOffAmount);
        }
        membaseinfo.setPayNum(membaseinfo.getPayNum() == null ? 0 : membaseinfo.getPayNum() + 1);
        memBaseinfoService.updateByPrimaryKeySelective(membaseinfo);
    }
}
