package com.likes.modules.admin.business.service.impl;

import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.SysParameterEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.dto.pay.SysThreePaysetDTO;
import com.likes.common.model.dto.sys.SysPayaccountDO;
import com.likes.common.model.request.TraRechargemealRequest;
import com.likes.common.model.response.TraRechargemealResponse;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemLevelConfig;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.mybatis.entity.SysPayaccount;
import com.likes.common.mybatis.entity.SysPayset;
import com.likes.common.mybatis.entity.SysPaysetExample;
import com.likes.common.mybatis.entity.SysThreepayset;
import com.likes.common.mybatis.entity.TraOrderinfom;
import com.likes.common.mybatis.entity.TraOrdertracking;
import com.likes.common.mybatis.entity.TraRechargeaudit;
import com.likes.common.mybatis.mapperext.sys.SysBusparameterMapperExt;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemLevelConfigService;
import com.likes.common.service.money.SysPayAccountService;
import com.likes.common.service.money.SysPaysetService;
import com.likes.common.service.money.SysThreePaysetService;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.service.money.TraOrdertrackingService;
import com.likes.common.service.money.TraRechargeauditService;
import com.likes.common.service.money.TraRechargemealService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.BeanUtils;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.DateUtils;
import com.likes.common.util.SnowflakeIdWorker;
import com.likes.common.util.SourceUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.business.service.RechargeService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

@Service
public class RechargeServiceImpl implements RechargeService {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private TraRechargemealService traRechargemealMapperService;
    @Resource
    private TraOrderinfomService traOrderinfomMapperService;
    @Resource
    private TraOrdertrackingService traOrdertrackingMapperService;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private SysBusparameterMapperExt sysBusparameterMapperExt;
    @Resource
    private SysPayAccountService sysPayAccountMapperService;
    @Resource
    private SysThreePaysetService sysThreePaysetService;
    @Resource
    private SysPaysetService sysPaysetService;
    @Resource
    private TraRechargeauditService traRechargeauditService;

    @Autowired
    private HttpServletRequest request;

    @Resource
    private MemBaseinfoService memBaseinfoService;

    @Resource
    private MemLevelConfigService memLevelConfigService;

    @Override
    public List<TraRechargemealResponse> rechargemealList() {
        //??????6???
        Integer num = 6;
        SysParameter sp = this.sysParamService.getByCode(SysParameterEnum.RECHARGEMEAL_NUM.name());
        if (sp != null
                && StringUtils.isNotEmpty(sp.getSysparamvalue())) {
            num = Integer.parseInt(sp.getSysparamvalue());
        }

        List<TraRechargemealResponse> list = traRechargemealMapperService.getList(num);
        return list;
    }


    public String getRandomWX() {
        //??????????????????
        SysParameter sysParameter = sysParamService.getByCode(SysParameterEnum.OFF_RECHARGE_KEFU.name());
        if (sysParameter != null) {
            return sysParameter.getSysparamvalue();
        }
        return null;
    }

    @Override
    public Map<String, Object> getBankList(LoginUser loginUser) {
        Map<String, Object> dataMap = getBankListCacheByLevel(loginUser.getLevelSeq());
        SysPayset payset = sysPaysetService.getUseOne(2);
        dataMap.put("giftrate", payset.getGiftrate().multiply(BigDecimal.valueOf(100)).intValue());

        if (payset == null) {
            throw new BusinessException("171","?????????????????????");
        }
        if (1 == payset.getRechargetype()) {
            BigDecimal payAmount = memBaseinfoService.getUserByAccno(loginUser.getAccno()).getPayAmount();
            if (payAmount.compareTo(BigDecimal.ZERO) == 1) {
                dataMap.put("giftrate", 0);
            }
        }
        return dataMap;
    }


    /**
     * NETBANK ????????????  WECHAT ????????????  ALIPAY ??????????????? ????????????  1????????? 2??????   3??????
     */
    private Map<String, Object> getBankListCacheByLevel(Integer levelSeq) {
        HashMap<String, Object> dataMap = RedisBusinessUtil.getBanksCacheByLevel(levelSeq);
        if (dataMap != null) {
            return dataMap;
        }
        dataMap = new HashMap<>();
        List<SysPayaccountDO> aliPays = sysPayAccountMapperService.getPayInfo(1, levelSeq);
        dataMap.put("ALIPAY", aliPays);

        List<SysPayaccountDO> wechatPays = sysPayAccountMapperService.getPayInfo(2,levelSeq);
        dataMap.put("WECHAT", wechatPays);

        List<SysPayaccountDO> netbankPays = sysPayAccountMapperService.getPayInfo(3,levelSeq);
        if (!CollectionUtil.isEmpty(netbankPays)) {
            netbankPays.forEach(sysPayaccountDO -> {
                SysBusparameter sysBusparameter = sysBusparameterMapperExt.selectByBusparamcode(sysPayaccountDO.getBankname());
                if (sysBusparameter != null) {
                    sysPayaccountDO.setBanknamealias(sysBusparameter.getBusparamname());
                }
            });
        }
        dataMap.put("NETBANK", netbankPays);
        RedisBusinessUtil.addBanksCacheByLevel(levelSeq, dataMap);
        return dataMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> doPayV1(LoginUser loginUserAPP, TraRechargemealRequest req) {
        doPayV1checkParams(loginUserAPP, req);
        //???????????????????????????
        SysPayaccount sysPayaccount = sysPayAccountMapperService.selectByPrimaryKey(req.getBankid());
        if (sysPayaccount == null || sysPayaccount.getIsDelete()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1109.getCode(), "?????????????????????");
        }
        if (sysPayaccount.getStatus() != 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1110.getCode(), "??????????????????,?????????????????????");
        }

        SysPaysetExample sysPaysetExample = new SysPaysetExample();
        SysPaysetExample.Criteria criteria = sysPaysetExample.createCriteria();
        criteria.andIsDeleteEqualTo(false);
        criteria.andStatusEqualTo(0);
        criteria.andSettypeEqualTo(2);
        SysPayset sysPayset = sysPaysetService.selectOneByExample(sysPaysetExample);
        if (sysPayset == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1111.getCode(), "?????????????????????");
        }
        // ????????? ??????????????? ?????? ?????????????????? ????????????????????? ??????????????????
        Date nowDate = new Date();
        // ????????????
        TraOrderinfom traOrderinfom = new TraOrderinfom();
        traOrderinfom.setBankid(req.getBankid());
        traOrderinfom.setPayuser(req.getPayuser());
        traOrderinfom.setPaynote(req.getPaynote());
        traOrderinfom.setOrdertype(Constants.ORDERTYPE2);
        traOrderinfom.setPaycode(null);
        traOrderinfom.setAccno(loginUserAPP.getAccno());
        traOrderinfom.setOrderdate(nowDate);
        traOrderinfom.setSource(resolveClientType(request));
        traOrderinfom.setBuyVip(req.getBuyVip());

        //NETBANK ????????????  WECHAT ????????????  ALIPAY ???????????????
        //????????????  1????????? 2??????   3??????
        if (sysPayaccount.getAccounttype() == 1) {
            traOrderinfom.setPaytype("ALIPAY");
        } else if (sysPayaccount.getAccounttype() == 2) {
            traOrderinfom.setPaytype("WECHAT");
        } else if (sysPayaccount.getAccounttype() == 3) {
            traOrderinfom.setPaytype("NETBANK");
        }

        //??????????????????  1 ??? ????????? 23:59:00???
        Date twoDayAfter = DateUtils.afterDays(nowDate, 1);
        String twoDayAfterString = DateUtils.formatDate(twoDayAfter, "yyyy-MM-dd HH:mm:ss");
        String expiredateString = twoDayAfterString.substring(0, 10).concat(" 23:59:00");
        traOrderinfom.setExpiredate(DateUtils.parseDate(expiredateString, "yyyy-MM-dd HH:mm:ss"));
        BigDecimal tradeOffAmount = getTradeOffAmount(req.getPrice());
        traOrderinfom.setOldamt(tradeOffAmount);
        traOrderinfom.setSumamt(tradeOffAmount);
        traOrderinfom.setRealamt(tradeOffAmount);
        traOrderinfom.setIsinvoice(9);
        traOrderinfom.setOrderstatus(Constants.ORDER_ORD04);
        traOrderinfom.setAccountstatus(Constants.ORDER_ACC04);
        //??????????????????
        String weichat = getRandomWX();
        traOrderinfom.setPaywechat(weichat);
        traOrderinfom.setOrdernote("??????[" + loginUserAPP.getNickname() + "]??????????????????");
        traOrderinfom.setCreateUser(loginUserAPP.getAccno());
        traOrderinfom.setUpdateUser(loginUserAPP.getAccno());
        traOrderinfom.setOrderno(SnowflakeIdWorker.generateShortId());

        // ????????????????????? ??????????????????????????????
        int i = traOrderinfomMapperService.insertOrder(traOrderinfom);
        if (i < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10004.getCode(), "????????????");
        }
        traOrderinfom = traOrderinfomMapperService.selectByPrimaryKey(traOrderinfom.getOrderid());
        // ??????????????????
        TraOrdertracking traOrdertracking = new TraOrdertracking();
        traOrdertracking.setOrderid(traOrderinfom.getOrderid());
        traOrdertracking.setTrackdate(new Date());
        traOrdertracking.setOrderstatus(Constants.ORDER_ORD04);
        traOrdertracking.setOperuse(loginUserAPP.getAccno());
        traOrdertracking.setTrackbody("??????[" + loginUserAPP.getNickname() + "]????????????" + req.getPrice() + "???");
        traOrdertrackingMapperService.insertSelective(traOrdertracking);


        TraRechargeaudit traRechargeaudit = new TraRechargeaudit();
        traRechargeaudit.setOrderid(traOrderinfom.getOrderid());
        traRechargeaudit.setPaysetid(sysPayset.getPaysetid());
        traRechargeaudit.setCreateUser(loginUserAPP.getAccno());
        traRechargeauditService.insertSelective(traRechargeaudit);
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        // ?????? ?????????
        dataMap.put("createdate", DateUtils.formatDate(traOrderinfom.getCreateTime(), "yyyy-MM-dd"));
        dataMap.put("orderno", traOrderinfom.getOrderno());
        dataMap.put("orderstatus", traOrderinfom.getOrderstatus());
        dataMap.put("realamt", tradeOffAmount);
        dataMap.put("weichat", weichat);
        RedisBusinessUtil.delRechargeUnLineOrder();
        return dataMap;

    }

    private void doPayV1checkParams(LoginUser loginUserAPP, TraRechargemealRequest req) {
        if (StringUtils.isEmpty(req.getPayuser())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "??????????????????");
        }
        if (req.getPayuser().length() > 10) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1112.getCode(), "???????????????????????????10???");
        }
        if (null == req.getBankid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "????????????");
        }

        SysPayaccount payacount = sysPayAccountMapperService.selectByPrimaryKey(req.getBankid());
        if (payacount.getMaxamt().compareTo(req.getPrice()) == -1) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1113.getCode(), "??????????????????????????????????????????");
        }
        if (payacount.getMinamt().compareTo(req.getPrice()) == 1) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1114.getCode(), "??????????????????????????????????????????");
        }
        if (!payacount.getSysStatus()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1115.getCode(), "???????????????????????????");
        }
        //??????????????????????????? ??????????????????
        TraOrderinfom weiTraOrderinfom = traOrderinfomMapperService.selectWeiZhifuOne(loginUserAPP.getAccno(), req.getPrice());
        if (weiTraOrderinfom != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1116.getCode(), "????????????????????????");
        }
        if (null != req.getBuyVip()) {
            MemLevelConfig leveConfig = memLevelConfigService.selectByPrimaryKey(req.getBuyVip());
            if (leveConfig == null) {
                throw new BusinessException(StatusCode.LiVE_ERROR_1117.getCode(), "????????????????????????");
            }
            if (leveConfig.getRechargeAmount().compareTo(req.getPrice()) != 0) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1118.getCode(), "???????????????????????????");
            }
        }
    }

    private String resolveClientType(HttpServletRequest request) {
        String clientType = request.getHeader(Constants.CLIENT_TYPE_STRING);
        if (org.apache.commons.lang3.StringUtils.isEmpty(clientType)) {
            clientType = SourceUtil.getClientSource(request);
        }
        return org.apache.commons.lang3.StringUtils.isEmpty(clientType) ? "" : clientType.toUpperCase();
    }

    @Override
    public List<SysThreePaysetDTO> paySetInfo(LoginUser loginUserAPP, String wayType) {
        List<SysThreePaysetDTO> sysThreePaysetDTOList = new ArrayList<>();
        SysPayset sysPayset = sysPaysetService.getUseOne(1);
        MemBaseinfo chongzhiBaseinfo = memBaseinfoService.getUserByAccno(loginUserAPP.getAccno());
        List<SysThreepayset> sysThreepaysetList = sysThreePaysetService.commonPay(loginUserAPP.getLevelSeq(), wayType);
        if (sysThreepaysetList.size() > 0) {
            SysThreePaysetDTO sysThreePaysetDTO;
            for (SysThreepayset sysThreepayset : sysThreepaysetList) {
                sysThreePaysetDTO = new SysThreePaysetDTO();
                if (Constants.PAY_SET_ONE == sysPayset.getRechargetype() && chongzhiBaseinfo.getPayAmount().compareTo(BigDecimal.ZERO) < 1) {
                    sysThreePaysetDTO.setDiscount(sysPayset.getGiftrate().multiply(new BigDecimal(100)));
                } else if (Constants.PAY_SET_TWO == sysPayset.getRechargetype()) {
                    sysThreePaysetDTO.setDiscount(sysPayset.getGiftrate().multiply(new BigDecimal(100)));
                }
                sysThreePaysetDTO.setMaxamt(sysThreepayset.getMaxamt().setScale(0, BigDecimal.ROUND_DOWN));
                sysThreePaysetDTO.setMinamt(sysThreepayset.getMinamt().setScale(0, BigDecimal.ROUND_DOWN));
                BeanUtils.copyProperties(sysThreepayset, sysThreePaysetDTO);

                sysThreePaysetDTOList.add(sysThreePaysetDTO);
                Collections.shuffle(sysThreePaysetDTOList);
            }
        }
        return sysThreePaysetDTOList;
    }
}
