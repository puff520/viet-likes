package com.likes.modules.admin.pay.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.likes.common.config.UdunProperties;
import com.likes.common.constant.Constants;
import com.likes.common.constant.ModuleConstant;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.request.IncarnateOrderReq;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.DzCoinMapper;
import com.likes.common.mybatis.mapper.MemWalletAddressMapper;
import com.likes.common.mybatis.mapper.UdunOrderMapper;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemBaseinfoWriteService;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.service.money.TraApplycashService;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.service.money.TraOrdertrackingService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.SnowflakeIdWorker;
import com.likes.common.util.redis.RedisBaseUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.common.util.redis.RedisLock;
import com.likes.modules.admin.pay.service.MemWalletService;
import com.uduncloud.sdk.client.UdunClient;
import com.uduncloud.sdk.domain.Address;
import com.uduncloud.sdk.domain.ResultMsg;
import com.uduncloud.sdk.domain.Trade;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;


@Service
public class MemWalletServiceImpl implements MemWalletService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TraOrderinfomService traOrderinfomMapperService;
    @Resource
    private MemBaseinfoWriteService memBaseinfoWriteService;
    @Resource
    private MemGoldchangeService memGoldchangeService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private UdunOrderMapper udunRechargeMapper;
    @Resource
    private TraApplycashService traApplycashMapperService;
    @Resource
    private TraOrdertrackingService traOrdertrackingMapperService;
    @Resource
    private TraOrderinfomService traOrderinfomService;
    @Resource
    private MemWalletAddressMapper memWalletAddressMapper;
    @Resource
    private DzCoinMapper dzCoinMapper;
    @Resource
    UdunClient udunClient;
    @Resource
    UdunProperties udunProperties;
    @Resource
    private RedissonClient redissonClient;


    @Override
    public DzCoin getCoinType(String coinName) {
        DzCoin dzCoin = RedisBaseUtil.get("dz:dzCoin:" + coinName);
        if (ObjectUtil.isNull(dzCoin)) {
            DzCoin param = new DzCoin();
            param.setCoinName(coinName);
            dzCoin = dzCoinMapper.selectOne(param);
            RedisBaseUtil.set("dz:dzCoin:" + coinName, dzCoin);
        }
        return  dzCoin;
    }

    @Override
    public String getMemMoneyAddress(String coinName, LoginUser loginUser) {
        MemWalletAddress walletAddress = new MemWalletAddress();
        walletAddress.setCoinName(coinName);
        walletAddress.setAccno(loginUser.getAccno());
        MemWalletAddress result = memWalletAddressMapper.selectOne(walletAddress);
        if (ObjectUtil.isNull(result)) {
            String mainCoinType = getCoinType(coinName).getMainCoinType();
            Address address = udunClient.createAddress(mainCoinType, loginUser.getAcclogin(), "", udunProperties.getCallUrl());
            if (ObjectUtil.isNotNull(address)) {
                walletAddress = new MemWalletAddress();
                walletAddress.setMoneyAddress(address.getAddress());
                walletAddress.setAccno(loginUser.getAccno());
                walletAddress.setCoinName(coinName);
                walletAddress.setCreateUser(loginUser.getAccno());
                walletAddress.setCreateTime(new Date());
                walletAddress.setRemark(udunProperties.getCallUrl());
                memWalletAddressMapper.insertSelective(walletAddress);
                return walletAddress.getMoneyAddress();
            }
        }
        return result.getMoneyAddress();
    }

    @Override
    public List<DzCoin> coinList(LoginUser loginUser) {
        List<DzCoin> coinList = RedisBaseUtil.get("dz:dzCoin");
        if (CollectionUtil.isEmpty(coinList)) {
            coinList = dzCoinMapper.selectAll();
            RedisBaseUtil.set("dz:dzCoin" , coinList);
        }
        Iterator iterator = coinList.iterator();
        while(iterator.hasNext()){
            DzCoin dz = (DzCoin) iterator.next();
            if(dz.getCoinName().equals("ERC20")){
                iterator.remove();
                continue;
            }
        }
        return  coinList;
    }

    @Override
    public boolean submitRecharge(String coinName, BigDecimal amount, String moneyAddress, LoginUser loginUser) {
        DzCoin dzCoin = getCoinType(coinName);
        checkAddress(dzCoin.getMainCoinType(), moneyAddress);
        String businessId = "ud_" + SnowflakeIdWorker.generateId();
        ResultMsg resultMsg = udunClient.withdraw(moneyAddress, amount,
                dzCoin.getMainCoinType(), dzCoin.getCoinType(),
                businessId, null, udunProperties.getCallUrl());
        if (resultMsg.getCode().equals(200)) {
            return true;
        }
        logger.error("U dun?????????????????? ????????????  ===== >> {}", JSONUtil.toJsonStr(resultMsg));
        logger.error("U dun?????????????????? == coinName={},moneyAddress={},accLogin={}",
                coinName, moneyAddress, loginUser.getAcclogin());
        throw new BusinessException(StatusCode.LIVE_ERROR_11008.getCode(), "???????????????!");
    }

    @Override
    public boolean submitWithdraw(String coinName,String businessId, BigDecimal amount, String moneyAddress, LoginUser loginUser) {
        DzCoin dzCoin = getCoinType(coinName);
        UdunOrder udunOrder = new UdunOrder();
        udunOrder.setAmount(getTradeOffAmount(amount));
        udunOrder.setAccno(loginUser.getAccno());
        udunOrder.setMainCoinType(dzCoin.getMainCoinType());
        udunOrder.setCoinType(dzCoin.getCoinType());
        udunOrder.setTradeType(2);
        udunOrder.setAddress(moneyAddress);
        udunOrder.setBusinessId(businessId);
        udunOrder.setTradeStatus(0);
        udunOrder.setCreateUser(loginUser.getAccno());
        udunOrder.setCreateTime(new Date());
        udunRechargeMapper.insertSelective(udunOrder);

        ResultMsg resultMsg = udunClient.withdraw(moneyAddress, udunOrder.getAmount(),
                dzCoin.getMainCoinType(), dzCoin.getCoinType(),
                businessId, null, udunProperties.getCallUrl());
        if (resultMsg.getCode().equals(200)) {
            return true;
        }
        logger.error("U dun?????????????????? ????????????  ===== >> {}", JSONUtil.toJsonStr(resultMsg));
        logger.error("U dun?????????????????? == coinName={},moneyAddress={},accLogin={}",
                coinName, moneyAddress, loginUser.getAcclogin());
        return false;
    }


    @Transactional
    @Override
    public String udunCallBack(Trade trade) {
        MemWalletAddress walletAddress = new MemWalletAddress();
        walletAddress.setMoneyAddress(trade.getAddress());
        MemWalletAddress result = memWalletAddressMapper.selectOne(walletAddress);
        if (ObjectUtil.isNull(result)) {
            return "error";
        }
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(result.getAccno());
        BigDecimal amount = trade.getAmount().divide(new BigDecimal(1000000));
        UdunOrder udunRecharge = new UdunOrder();
        udunRecharge.setAmount(amount);
        udunRecharge.setAccno(memBaseinfo.getAccno());
        udunRecharge.setCoinType(trade.getCoinType());
        udunRecharge.setTradeType(1);
        udunRecharge.setTradeStatus(trade.getStatus());
        udunRecharge.setCreateTime(new Date());
        int row = udunRechargeMapper.insertSelective(udunRecharge);
        if (row > 0 && trade.getStatus() == 3) {
            boolean flag = createRechargeOrder(udunRecharge, memBaseinfo);
            if (flag) {
                return "ok";
            }
        }
        return "error";
    }


    public void checkAddress(String mainCoinType, String moneyAddress) {
        boolean b = udunClient.checkAddress(mainCoinType, moneyAddress);
        if (!b) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11008.getCode(), "??????????????????????????????!");
        }
    }

    private boolean createRechargeOrder(UdunOrder udunRecharge, MemBaseinfo memBaseinfo) {

        TraOrderinfom traOrderinfom = new TraOrderinfom();
        //???????????????
        traOrderinfom.setOrdertype(Constants.ORDERTYPE1);
        traOrderinfom.setOrderno(SnowflakeIdWorker.generateShortId());
        traOrderinfom.setAccno(memBaseinfo.getAccno());
        traOrderinfom.setOrderdate(new Date());


        //??????????????????????????????????????????
        traOrderinfom.setSumamt(udunRecharge.getAmount());
        traOrderinfom.setRealamt(udunRecharge.getAmount());

        traOrderinfom.setIsinvoice(9);
        traOrderinfom.setOrderstatus(Constants.ORDER_ORD08);
        traOrderinfom.setAccountstatus(Constants.ORDER_ACC04);
        traOrderinfom.setOrdernote("??????[" + memBaseinfo.getEmail() + "]udun ??????: ");
        traOrderinfom.setPaydate(new Date());
        int i = traOrderinfomService.insertOrder(traOrderinfom);
        if (i < 0) {
            logger.error("U dun ???????????????????????? == accLogin={}", memBaseinfo.getAccno());
            return false;
        }
        updateChangeBalance(traOrderinfom, memBaseinfo);
        return true;
    }


    private void updateChangeBalance(TraOrderinfom traOrderinfom, MemBaseinfo memBaseinfo) {
        MemGoldchangeDO memGoldchangeDO = new MemGoldchangeDO();
        memGoldchangeDO.setRefid(traOrderinfom.getOrderid());
        memGoldchangeDO.setUserId(memBaseinfo.getMemid().intValue());
        memGoldchangeDO.setShowChange(getTradeOffAmount(traOrderinfom.getSumamt()));
        memGoldchangeDO.setCreateUser(memBaseinfo.getAccno());
        memGoldchangeDO.setUpdateUser(memBaseinfo.getAccno());
        memGoldchangeDO.setQuantity(traOrderinfom.getSumamt());
        memGoldchangeDO.setAmount(traOrderinfom.getSumamt());
        memGoldchangeDO.setPayAmount(traOrderinfom.getSumamt());
        memGoldchangeDO.setChangetype(GoldchangeEnum.RECHARGE.getValue());
        memBaseinfoWriteService.updateUserBalance(memGoldchangeDO);
    }


    @Override
    @Transactional
    public void modifyWithdrawStatus(Trade trade) {
        UdunOrder udunRecharge = new UdunOrder();
        udunRecharge.setBusinessId(trade.getBusinessId());
        udunRecharge.setTradeStatus(0);
        UdunOrder result = udunRechargeMapper.selectOne(udunRecharge);
        if (ObjectUtil.isNull(result)) {
            logger.error("U dun ?????????????????????????????????== >{}", trade.getBusinessId());
            return;
        } else {
            UdunOrder update = new UdunOrder();
            update.setUnduOrderId(result.getUnduOrderId());
            update.setUpdateUser("udun");
            update.setTradeStatus(trade.getStatus());
            update.setUpdateTime(new Date());

            udunRechargeMapper.updateByPrimaryKeySelective(update);

        }
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(trade.getBusinessId());
        traOrderinfom.setOrderstatus(Constants.ORDER_ORD07);
        traOrderinfom.setUpdateUser("udun");
        traOrderinfomMapperService.doUpdateIncarnateHandleOrder(traOrderinfom);


        // ?????????????????????????????????
        TraApplycash traApplycash = traApplycashMapperService.findByOrderid(traOrderinfom.getOrderid());
        if (traApplycash == null) {
            throw new BusinessException("?????????????????????");
        }
        if (Constants.APYCSTATUS1 != traApplycash.getApycstatus()) {
            throw new BusinessException("????????????????????????");
        }

        if(trade.getStatus() == 1){
            // ??????
            traApplycash.setApycstatus(Constants.APYCSTATUS2);
            traApplycash.setPaymemname("udun");
            traApplycash.setUpdateUser("udun");
            // ????????????
            traApplycashMapperService.doUpdateIncarnateHandleOrder(traApplycash);
        } else if (trade.getStatus() == 2) {
            failedOrder(traOrderinfom, traApplycash,"??????");
        }
    }

    @Override
    @Transactional
    public void withdrawArrived(Trade trade) {
        // ??????
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(trade.getBusinessId());
        if (traOrderinfom == null) {
            logger.error("???????????????");
            return;
        }
//        BigDecimal amount = trade.getAmount().divide(new BigDecimal(1000000));
//        if(!(traOrderinfom.getSumamt().intValue() == amount.intValue())){
//            throw new BusinessException("???????????????");
//        }

//        if (!Constants.ORDER_ORD07.equals(traOrderinfom.getOrderstatus())) {
//            logger.error("?????????????????????????????????");
//            return;
//        }
        if (Constants.ORDER_ORD12.equals(traOrderinfom.getOrderstatus())) {
            throw new BusinessException("?????????????????????");
        }
        // ??????
        MemBaseinfo zhubo = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
        if (zhubo == null) {
            logger.error("???????????????");
            throw new BusinessException("???????????????");
        }
        // ????????????
        TraApplycash traApplycash = traApplycashMapperService.findByOrderid(traOrderinfom.getOrderid());
        if (traApplycash == null) {
            logger.error("?????????????????????");
            throw new BusinessException("?????????????????????");
        }
//        if (Constants.APYCSTATUS2 != traApplycash.getApycstatus()) {
//            logger.error("???????????????????????????");
//            return;
//        }

        UdunOrder param = new UdunOrder();
        param.setBusinessId(trade.getBusinessId());
        UdunOrder udunOrder =   udunRechargeMapper.selectOne(param);
        if(ObjectUtil.isNull(udunOrder)){
            logger.error("udun ??????????????????");
            throw new BusinessException("udun ??????????????????");
        }
        udunOrder.setTradeStatus(trade.getStatus());
        udunRechargeMapper.updateByPrimaryKey(udunOrder);

        if (trade.getStatus() == 4) {
            failedOrder(traOrderinfom, traApplycash,"??????");
            RedisBusinessUtil.delIncarnateOrderListCahce();
            logger.info("udun ??????????????????????????? ??????id ==== >> {}",trade.getBusinessId());
            return;
        }

        // ??????????????????
        // ??????
        traOrderinfom.setOrderstatus(Constants.ORDER_ORD12);
        traOrderinfom.setPaydate(new Date());
        traOrderinfom.setUpdateUser("udun");
        traOrderinfom.setOrdernote("udun ???????????????");
        int i = traOrderinfomMapperService.udunUpdateIncarnateConfirmOrder(traOrderinfom);
        if (i > 0) {
            // ??????????????????
            TraOrdertracking traOrdertracking = new TraOrdertracking();
            traOrdertracking.setOrderid(traOrderinfom.getOrderid());
            traOrdertracking.setTrackdate(new Date());
            traOrdertracking.setOrderstatus(Constants.ORDER_ORD12);
            traOrdertracking.setOperuse("udun");
            traOrdertracking.setTrackbody("udun????????????");
            // ????????????
            int tc = traOrdertrackingMapperService.insertTraOrdertracking(traOrdertracking);
            if (!(tc > 0)) {
                throw new BusinessException("?????????????????????");
            }
            // ??????????????????
            traApplycash.setPaydate(new Date());
            traApplycash.setApycstatus(Constants.APYCSTATUS4);
            traApplycash.setUpdateUser("udun");

            // ????????????
            int k = traApplycashMapperService.udunUpdateIncarnateConfirmApplycash(traApplycash);
            if (!(k > 0)) {
                throw new BusinessException("?????????????????????????????????");
            }

            // ?????? ????????????????????? ?????????????????????????????? ?????? ???????????????
            MemGoldchange paramMemGoldchange = new MemGoldchange();
            paramMemGoldchange.setAccno(traOrderinfom.getAccno());
            paramMemGoldchange.setChangetype(GoldchangeEnum.WITHDRAWAL_APPLY.getValue());
            paramMemGoldchange.setRefid(traOrderinfom.getOrderid());
            paramMemGoldchange.setUpdateUser("udun");
            paramMemGoldchange.setOpnote("????????????");
            paramMemGoldchange.setSource(traOrderinfom.getSource());

            int mg = memGoldchangeService.updateZhuboTixian(paramMemGoldchange);
            if (!(mg > 0)) {
                logger.error("????????????????????????");
                throw new BusinessException("????????????????????????");
            }

            MemBaseinfoExample membaseinfoExample = new MemBaseinfoExample();
            membaseinfoExample.createCriteria().andAccnoEqualTo(traOrderinfom.getAccno());
            MemBaseinfo membaseinfo = memBaseinfoService.selectOneByExample(membaseinfoExample);
            // ??????????????????
            membaseinfo.setWithdrawalAmount(getTradeOffAmount(traOrderinfom.getSumamt()));
            // ????????????????????????
            if (membaseinfo.getWithdrawalFirst() == null || membaseinfo.getWithdrawalFirst().compareTo(BigDecimal.ZERO) == 0) {
                membaseinfo.setWithdrawalFirst(getTradeOffAmount(traOrderinfom.getSumamt()));
            }
            // ????????????????????????
            if (membaseinfo.getWithdrawalMax() == null || membaseinfo.getWithdrawalMax().compareTo(getTradeOffAmount(traOrderinfom.getSumamt())) == -1) {
                membaseinfo.setWithdrawalMax(getTradeOffAmount(traOrderinfom.getSumamt()));
            }
            // ?????????????????????
            memBaseinfoService.updateWithdrawalAmount(membaseinfo);
            RedisBusinessUtil.delIncarnateOrderListCahce();
        } else {
            throw new BusinessException("????????????(???????????????)");
        }

    }

    private void failedOrder(TraOrderinfom traOrderinfom, TraApplycash traApplycash,String opNote) {

        BigDecimal sumamt = traOrderinfom.getSumamt();

        // ??????????????????
        traOrderinfom.setOrderstatus(Constants.ORDER_ORD14);
        traOrderinfom.setUpdateUser("udun");
        traOrderinfom.setUpdateTime(new Date());
        traOrderinfom.setOrdernote("udun ????????????"+opNote);
        traOrderinfomMapperService.updateByPrimaryKeySelective(traOrderinfom);
        Long mid = memBaseinfoService.selectByAccno(traOrderinfom.getAccno()).getMemid();
        RReadWriteLock lock = redissonClient.getReadWriteLock(RedisLock.UPDATE_USER_BALANCE_ + mid);
        try {
            boolean bool = lock.writeLock().tryLock(100, 20, TimeUnit.SECONDS);
            if (!bool) {
                logger.error("{}.failedOrder ????????????:{}", getClass().getName(), RedisLock.UPDATE_USER_BALANCE_ + mid);
                throw new BusinessException("?????????????????????????????????");
            }
            // ??????????????????
            MemBaseinfo membaseinfo = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
            TraOrdertracking traOrdertracking = new TraOrdertracking();
            traOrdertracking.setOrderid(traOrderinfom.getOrderid());
            traOrdertracking.setTrackdate(new Date());
            traOrdertracking.setOrderstatus(Constants.ORDER_ORD14);
            traOrdertracking.setOperuse("udun");
            traOrdertracking.setTrackbody("udun ?????????????????????[" + traOrderinfom.getOrderno() + "]"+opNote);
            traOrdertrackingMapperService.insertSelective(traOrdertracking);


            // ?????? ?????????????????????
            traApplycash.setApycstatus(Constants.APYCSTATUS3);
            traApplycash.setPaymemname("udun");
            traApplycash.setUpdateUser("udun");
            traApplycashMapperService.updateByPrimaryKeySelective(traApplycash);
            //??????
            // ?????? ????????????????????? ?????????????????????????????? ?????? ???????????????
            MemGoldchange memGoldchange = memGoldchangeService.selectOneByExample(traOrderinfom.getOrderid(), traOrderinfom.getAccno());
            BigDecimal tradeOffAmount = getTradeOffAmount(membaseinfo.getNoWithdrawalAmount());
            BigDecimal tradeOffAmount1 = getTradeOffAmount(membaseinfo.getGoldnum());
            memGoldchange.setAfterCgdml(tradeOffAmount);
            memGoldchange.setPreCgdml(tradeOffAmount);
            memGoldchange.setSource(traOrderinfom.getSource());
            memGoldchange.setGoldnum(tradeOffAmount1);
            memGoldchange.setQuantity(getTradeOffAmount(sumamt.multiply(new BigDecimal(Constants.CHONGZHIBILIE))));
            memGoldchange.setAmount(getTradeOffAmount(sumamt));
            memGoldchange.setRecgoldnum(tradeOffAmount1.add(sumamt));
            memGoldchange.setChangetype(GoldchangeEnum.WITHDRAW_FAILED.getValue());
            memGoldchange.setUpdateUser("udun");
            memGoldchange.setUpdateTime(new Date());
            memGoldchange.setOpnote("udun ??????"+opNote);
            memGoldchangeService.updateByPrimaryKeySelective(memGoldchange);
            memBaseinfoService.updateMemBalance(sumamt, BigDecimal.ZERO, BigDecimal.ZERO, membaseinfo.getAccno());

        } catch (Exception e) {
            logger.error("{}.udun_failedOrder ??????", getClass().getName(), e);
            throw new BusinessException("??????????????????");
        } finally {
            lock.writeLock().unlock();
        }
    }


}
