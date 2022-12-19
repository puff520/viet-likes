package com.likes.common.service.yuebao.impl;

import cn.hutool.core.util.ObjectUtil;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.AssetOperateEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.YueaboOperateEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.AssetChangeBO;
import com.likes.common.model.YuebaoChangeBO;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.*;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.yuebao.YuebaoChangeService;
import com.likes.common.util.DateUtils;
import com.likes.common.util.SnowflakeIdWorker;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

/**
 * @author lzy
 * @create 2018-08-27 9:52
 **/
@Service
public class YuebaoChangeServiceImpl implements YuebaoChangeService {
    private static final Logger logger = LoggerFactory.getLogger(YuebaoChangeServiceImpl.class);
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private MemYuebaoRecordMapper memYuebaoRecordMapper;
    @Autowired
    private MemYuebaoMapper memYuebaoMapper;
    @Autowired
    private MemAssetRecordMapper memAssetRecordMapper;
    @Autowired
    private MemAssetMapper memAssetMapper;
    @Autowired
    private MemAssetCfgMapper memAssetCfgMapper;
    @Autowired
    private MemBaseinfoService memBaseinfoService;

    /**
     * 修改用户余额
     *
     * @param change 改变记录
     */
    @Override
    public boolean updateYuebaoAmount(YuebaoChangeBO change) throws RuntimeException {
        boolean intiYuebaoFlag = false;
        try {
            if (change.getOperateEnum().equals(YueaboOperateEnum.OUT)) {
                change.setChangeAmount(change.getChangeAmount().negate());
            }
            MemBaseinfo memBaseinfo = memBaseinfoService.selectByAccno(change.getAccno());
            Example example = new Example(MemYuebao.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("accno", change.getAccno());
            MemYuebao memYuebao = memYuebaoMapper.selectOneByExample(example);
            if (ObjectUtil.isNull(memYuebao)) {
                initMemYuebao(memBaseinfo, change);
                intiYuebaoFlag = true;
            }
            // 变动前余额
            BigDecimal currentBalance = memYuebao == null ? getTradeOffAmount(null) : memYuebao.getAmount();
            // 添加账变记录
            MemYuebaoRecord record = new MemYuebaoRecord();
            record.setAccno(change.getAccno());
            record.setAssetCfgId(change.getAssetCfgId());
            record.setChangeAmount(change.getChangeAmount());
            record.setBeforeAmount(getTradeOffAmount(currentBalance));
            BigDecimal changeMoney = change.getChangeAmount() == null ? BigDecimal.ZERO : change.getChangeAmount();
            BigDecimal newBalance = currentBalance.add(changeMoney);
            record.setAfterAmount(getTradeOffAmount(newBalance));
            record.setChangeType(change.getOperateEnum().getValue());
            record.setCreateTime(new Date());
            record.setSerialNo(SnowflakeIdWorker.generateId() + "");
            record.setCreateUser(change.getAccno());
            if (change.getIsSystemWay()) {
                record.setIsSystemWay(1);
            }
            record.setBuyAssetCfgId(change.getBuyAssetCfgId());
            memYuebaoRecordMapper.insertSelective(record);
            if (!intiYuebaoFlag) {
                BigDecimal amount = record.getChangeAmount();
                int i = memYuebaoMapper.updateMemYuebaoAmount(amount, change.getAccno());
                if (i != 1) {
                    throw new BusinessException(StatusCode.OPERATION_FAILED.getCode(), "操作失败");
                }
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException("用户修改余额出错", e.getCause());
        }
    }

    @Override
    public boolean updateAssetAmount(AssetChangeBO change) throws RuntimeException {
        boolean intiAssetFlag = false;
        try {
            if (change.getAssetOperateEnum().equals(AssetOperateEnum.TAKE)) {
                change.setChangeAmount(change.getChangeAmount().negate());
            }
            MemBaseinfo memBaseinfo = memBaseinfoService.selectByAccno(change.getAccno());
            if (ObjectUtil.isNull(memBaseinfo)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "对应用户不存在");
            }
            Example assetExample = new Example(MemAsset.class);
            Example.Criteria assetCriteria = assetExample.createCriteria();
            assetCriteria.andEqualTo("accno", change.getAccno());
            assetCriteria.andEqualTo("assetCfgId", change.getAssetCfgId());
            MemAsset memAsset = memAssetMapper.selectOneByExample(assetExample);
            if (ObjectUtil.isNull(memAsset)) {
                initMemAsset(memBaseinfo, change);
                intiAssetFlag = true;
            }
            // 变动前余额
            BigDecimal currentBalance = memAsset == null ? getTradeOffAmount(null) : memAsset.getAmount();
            // 添加账变记录
            MemYuebaoRecord record = new MemYuebaoRecord();
            record.setAccno(change.getAccno());
            record.setAssetCfgId(change.getAssetCfgId());
            record.setChangeAmount(change.getChangeAmount());
            record.setBeforeAmount(getTradeOffAmount(currentBalance));
            BigDecimal changeMoney = change.getChangeAmount() == null ? BigDecimal.ZERO : change.getChangeAmount();
            BigDecimal newBalance = currentBalance.add(changeMoney);
            record.setAfterAmount(getTradeOffAmount(newBalance));
            record.setChangeType(change.getAssetOperateEnum().getValue());
            record.setCreateTime(new Date());
            record.setSerialNo(SnowflakeIdWorker.generateId() + "");
            record.setCreateUser(change.getAccno());
            record.setBuyAssetCfgId(change.getBuyAssetCfgId());
            memYuebaoRecordMapper.insertSelective(record);


            MemAssetRecord assetRecord = new MemAssetRecord();
            assetRecord.setAccno(change.getAccno());
            assetRecord.setAssetCfgId(change.getAssetCfgId());
            assetRecord.setChangeAmount(record.getChangeAmount());
            if (change.getAssetOperateEnum().equals(AssetOperateEnum.DEPOSIT)) {
                assetRecord.setChangeType(YueaboOperateEnum.INTO.getValue());
            } else if (change.getAssetOperateEnum().equals(AssetOperateEnum.TAKE)) {
                assetRecord.setChangeType(YueaboOperateEnum.OUT.getValue());
            }
            assetRecord.setCreateTime(new Date());
            assetRecord.setSerialNo(SnowflakeIdWorker.generateId() + "");
            assetRecord.setCreateUser(change.getAccno());
            memAssetRecordMapper.insertSelective(assetRecord);

            if (!intiAssetFlag) {
                int f = memAssetMapper.updateMemAssetAmount(record.getChangeAmount(), record.getAssetCfgId(), change.getAccno(), memAsset.getTakeTime(), DateUtils.fiveZero());
                if (f != 1) {
                    throw new BusinessException(StatusCode.OPERATION_FAILED.getCode(), "操作失败");
                }
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException("用户修改余额出错", e.getCause());
        }
    }

    @Override
    public boolean updateEranAmount(AssetChangeBO change) throws RuntimeException {
        if (ObjectUtils.isEmpty(change) || StringUtils.isBlank(change.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_999.getCode(), "请求参数错误");
        }
        RReadWriteLock lock = redissonClient.getReadWriteLock(RedisKeys.UPDATE_YUEBAO_BALANCE_ + change.getAccno());
        try {
            boolean bool = lock.writeLock().tryLock(100, 10, TimeUnit.SECONDS);
            if (bool) {
                logger.info("用户修改余额拿到锁{}", change.getAccno());
                MemBaseinfo memBaseinfo = memBaseinfoService.selectByAccno(change.getAccno());
                if (ObjectUtil.isNull(memBaseinfo)) {
                    throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "对应用户不存在");
                }
                Example assetExample = new Example(MemAsset.class);
                Example.Criteria assetCriteria = assetExample.createCriteria();
                assetCriteria.andEqualTo("accno", change.getAccno());
                assetCriteria.andEqualTo("assetCfgId", change.getAssetCfgId());
                MemAsset memAsset = memAssetMapper.selectOneByExample(assetExample);

                // 变动前余额
                BigDecimal currentBalance = memAsset == null ? getTradeOffAmount(null) : memAsset.getAmount();

                // 添加账变记录
                MemYuebaoRecord record = new MemYuebaoRecord();
                record.setAccno(change.getAccno());
                record.setAssetCfgId(change.getAssetCfgId());

                Example cfgExample = new Example(MemAssetCfg.class);
                Example.Criteria cfgCriteria = cfgExample.createCriteria();
                cfgCriteria.andEqualTo("id", change.getAssetCfgId());
                MemAssetCfg memAssetCfg = memAssetCfgMapper.selectOneByExample(cfgExample);

                BigDecimal dailyRate = memAssetCfg.getDailyEarnRate();
                Date now = new Date();
                if (ObjectUtil.isNotNull(memAsset) && now.after(memAsset.getTakeTime())) {
                    dailyRate = new BigDecimal(0.005);
                }
                BigDecimal changeMoney = change.getChangeAmount() == null ? BigDecimal.ZERO : change.getChangeAmount();
                changeMoney = changeMoney.multiply(dailyRate);
                record.setChangeAmount(getTradeOffAmount(changeMoney));
                record.setBeforeAmount(getTradeOffAmount(currentBalance));
                BigDecimal newBalance = currentBalance.add(changeMoney);
                record.setAfterAmount(getTradeOffAmount(newBalance));

                record.setChangeType(change.getOperateEnum().getValue());
                record.setCreateTime(new Date());
                record.setSerialNo(SnowflakeIdWorker.generateId() + "");
                record.setCreateUser(change.getAccno());

                memYuebaoRecordMapper.insertSelective(record);


                MemAssetRecord assetRecord = new MemAssetRecord();
                assetRecord.setAccno(change.getAccno());
                assetRecord.setAssetCfgId(change.getAssetCfgId());
                assetRecord.setChangeAmount(record.getChangeAmount());
                assetRecord.setChangeType(YueaboOperateEnum.EARN.getValue());
                assetRecord.setCreateTime(new Date());
                assetRecord.setSerialNo(SnowflakeIdWorker.generateId() + "");
                assetRecord.setCreateUser(change.getAccno());
                memAssetRecordMapper.insertSelective(assetRecord);

                BigDecimal yesterdayEarn = memYuebaoRecordMapper.yesterdayEarn(change.getAccno(), change.getAssetCfgId(), DateUtils.dayBeforeStratStr(), DateUtils.timeBeforeEndStr());
                int k = memAssetMapper.updateMemAssetEarn(record.getChangeAmount(), getTradeOffAmount(yesterdayEarn), record.getAssetCfgId(), change.getAccno());
                if (k != 1) {
                    throw new BusinessException(StatusCode.OPERATION_FAILED.getCode(), "操作失败");
                }
                return true;
            } else {
                throw new BusinessException(StatusCode.OPERATION_FAILED.getCode(), "操作失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("用户修改余额出错", e.getCause());
        } finally {
            // 释放锁
            lock.writeLock().unlock();
            logger.info("{} updateUserBalance 用户修改余额释放锁", change.getAccno());
        }
    }

    @Override
    public boolean updateEranYueBao(AssetChangeBO change) throws RuntimeException {
        if (ObjectUtils.isEmpty(change) || StringUtils.isBlank(change.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_999.getCode(), "请求参数错误");
        }
        RReadWriteLock lock = redissonClient.getReadWriteLock(RedisKeys.UPDATE_YUEBAO_BALANCE_ + change.getAccno());
        try {
            boolean bool = lock.writeLock().tryLock(100, 10, TimeUnit.SECONDS);
            if (bool) {
                logger.info("用户修改余额拿到锁{}", change.getAccno());
                MemBaseinfo memBaseinfo = memBaseinfoService.selectByAccno(change.getAccno());
                if (ObjectUtil.isNull(memBaseinfo)) {
                    throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "对应用户不存在");
                }
                MemYuebao param = new MemYuebao();
                param.setAccno(change.getAccno());
                MemYuebao memYuebao = memYuebaoMapper.selectOne(param);

                // 变动前余额
                BigDecimal currentBalance = memYuebao == null ? getTradeOffAmount(null) : memYuebao.getAmount();

                // 添加账变记录
                MemYuebaoRecord record = new MemYuebaoRecord();
                record.setAccno(change.getAccno());
                record.setAssetCfgId(change.getAssetCfgId());
                BigDecimal dailyRate = new BigDecimal(0.005);
                BigDecimal changeMoney = change.getChangeAmount() == null ? BigDecimal.ZERO : change.getChangeAmount();
                changeMoney = changeMoney.multiply(dailyRate);
                record.setChangeAmount(getTradeOffAmount(changeMoney));
                record.setBeforeAmount(getTradeOffAmount(currentBalance));
                BigDecimal newBalance = currentBalance.add(changeMoney);
                record.setAfterAmount(getTradeOffAmount(newBalance));

                record.setChangeType(change.getOperateEnum().getValue());
                record.setCreateTime(new Date());
                record.setSerialNo(SnowflakeIdWorker.generateId() + "");
                record.setCreateUser(change.getAccno());

                memYuebaoRecordMapper.insertSelective(record);


                MemAssetRecord assetRecord = new MemAssetRecord();
                assetRecord.setAccno(change.getAccno());
                assetRecord.setAssetCfgId(change.getAssetCfgId());
                assetRecord.setChangeAmount(record.getChangeAmount());
                assetRecord.setChangeType(YueaboOperateEnum.EARN.getValue());
                assetRecord.setCreateTime(new Date());
                assetRecord.setSerialNo(SnowflakeIdWorker.generateId() + "");
                assetRecord.setCreateUser(change.getAccno());
                memAssetRecordMapper.insertSelective(assetRecord);

                BigDecimal yesterdayEarn = memYuebaoRecordMapper.yesterdayEarn(change.getAccno(), change.getAssetCfgId(), DateUtils.dayBeforeStratStr(), DateUtils.timeBeforeEndStr());
                int k = memAssetMapper.updateMemYuebaoEarn(record.getChangeAmount(), getTradeOffAmount(yesterdayEarn), change.getAccno());
                if (k != 1) {
                    throw new BusinessException(StatusCode.OPERATION_FAILED.getCode(), "操作失败");
                }
                return true;
            } else {
                throw new BusinessException(StatusCode.OPERATION_FAILED.getCode(), "操作失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("用户修改余额出错", e.getCause());
        } finally {
            // 释放锁
            lock.writeLock().unlock();
            logger.info("{} updateUserBalance 用户修改余额释放锁", change.getAccno());
        }
    }


    private void initMemYuebao(MemBaseinfo memBaseinfo, YuebaoChangeBO change) {
        MemYuebao memYuebao1 = new MemYuebao();
        memYuebao1.setAccno(memBaseinfo.getAccno());
        if (change.getOperateEnum().equals(YueaboOperateEnum.INTO)) {
            memYuebao1.setAmount(getTradeOffAmount(change.getChangeAmount()));
        } else {
            memYuebao1.setAmount(getTradeOffAmount(null));
        }
        memYuebao1.setDividendType(1);
        memYuebao1.setCreateTime(new Date());
        memYuebao1.setCreateUser(memBaseinfo.getAccno());
        memYuebao1.setUpdateTime(new Date());
        memYuebao1.setCreateUser(memBaseinfo.getAccno());
        memYuebao1.setUpdateUser(memBaseinfo.getAccno());
        memYuebao1.setRateType(1);
        memYuebao1.setTotalIncome(getTradeOffAmount(null));
        memYuebaoMapper.insertSelective(memYuebao1);

    }

    private void initMemAsset(MemBaseinfo memBaseinfo, AssetChangeBO change) {
        MemAsset memAsset1 = new MemAsset();
        memAsset1.setAmount(getTradeOffAmount(null));
        if (change.getAssetOperateEnum().equals(AssetOperateEnum.DEPOSIT)) {
            memAsset1.setAmount(getTradeOffAmount(change.getChangeAmount()));
        } else {
            memAsset1.setAmount(getTradeOffAmount(null));
        }
        memAsset1.setAccno(memBaseinfo.getAccno());
        memAsset1.setCreateTime(new Date());
        memAsset1.setUpdateTime(new Date());
        memAsset1.setCreateUser(memBaseinfo.getAccno());
        memAsset1.setUpdateUser(memBaseinfo.getAccno());
        memAsset1.setIntoTime(new Date());

        Date now = new Date();
        Date takeTime = new Date();
        if (change.getAssetCfgId().equals(1L)) {
            takeTime = DateUtils.addDays(now, 1);
        } else if (change.getAssetCfgId().equals(2L)) {
            takeTime = DateUtils.addDays(now, 7);
        } else if (change.getAssetCfgId().equals(3L)) {
            takeTime = DateUtils.addDays(now, 14);
        } else if (change.getAssetCfgId().equals(4L)) {
            takeTime = DateUtils.addDays(now, 30);
        }
        memAsset1.setTakeTime(takeTime);
        memAsset1.setTotalEarn(getTradeOffAmount(null));
        memAsset1.setUsableAmount(memAsset1.getAmount());
        memAsset1.setYesterdayEarn(getTradeOffAmount(null));
        memAsset1.setAssetCfgId(change.getAssetCfgId());

        memAssetMapper.insertSelective(memAsset1);
    }


}
