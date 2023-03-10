package com.likes.modules.admin.login.controller.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.constant.Constants;
import com.likes.common.constant.ModuleConstant;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.*;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.dto.member.UserGoldDO;
import com.likes.common.model.dto.sys.SysAreaDO;
import com.likes.common.model.dto.sys.SysShortmsgDO;
import com.likes.common.model.request.ActingUsersRequest;
import com.likes.common.model.request.CreditRequest;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.model.response.level.MemberLevelResponse;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.MemBaseinfoMapper;
import com.likes.common.mybatis.mapper.MemLoginMapper;
import com.likes.common.mybatis.mapper.MemRepayuserMapper;
import com.likes.common.mybatis.mapper.MemSubInfoMapper;
import com.likes.common.mybatis.mapperext.member.MemLevelConfigMapperExt;
import com.likes.common.mybatis.mapperext.member.MemLevelMapperExt;
import com.likes.common.mybatis.mapperext.member.MemRelationshipMapperExt;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.code.UniqueCodeService;
import com.likes.common.service.credit.MemCreditService;
import com.likes.common.service.member.*;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.service.sys.SysInfologService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.service.sys.SysShortmsgService;
import com.likes.common.util.*;
import com.likes.common.util.encrypt.MD5;
import com.likes.common.util.http.HttpUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.login.controller.service.AppLoginService;
import com.likes.modules.admin.mail.service.MailService;
import com.likes.modules.admin.mail.service.impl.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

@Service
public class AppLoginServiceImpl extends BaseServiceImpl implements AppLoginService {

    private static final ExecutorService threadPool = CommonUtils.getMaxThreadPoolExecutor();
    private static final ExecutorService registerCachedThreadPool = CommonUtils.getMaxThreadPoolExecutor();
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private MemLoginMapper memLoginMapper;
    @Resource
    private MemLoginService memLoginService;
    @Resource
    private SysInfologService sysInfologService;
    @Resource
    private MemLevelConfigService memLevelConfigService;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private SysBusParamService sysBusParamService;
    @Resource
    private SysShortmsgService sysShortmsgService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemRelationshipService memRelationshipService;
    @Resource
    private MemLevelMapperExt memLevelMapperExt;
    @Resource
    private TraOrderinfomService traOrderinfomMapperService;
    @Resource
    private MemBaseinfoWriteService memBaseinfoWriteService;
    @Resource
    private MemLevelService memLevelService;
    @Resource
    private MemSubInfoMapper memSubInfoMapper;
    @Resource
    private MemCreditService memCreditService;
    @Resource
    private MemLevelConfigMapperExt memLevelConfigMapperExt;
    @Resource
    private MemRelationshipMapperExt memRelationshipMapperExt;

    @Resource
    private EmailService emailService;
    @Resource
    private MemBaseinfoMapper memBaseinfoMapper;


    @Override
    public Integer sendMailCode(UsersRequest req, HttpServletRequest request) {
        if (!StringUtils.isEmail(req.getEmail())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_115.getCode(), "?????????????????????");
        }

        /**
         * ??????????????????????????????????????? ?????????????????????
         * ????????? ??????????????? 1???????????? ;2???????????? ;4.????????????
         */
        // ??????????????????????????????????????? ?????????????????????
        if (req.getSendtype() == 3) {
            // ????????? ????????? ??? 3?????????
            MemLogin ml = memLoginService.findByEmail(req.getEmail());
            if (ml != null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "?????????????????????");
            }
        } else {
            // ????????? ??????????????? 1???????????? ;2???????????? ;4.????????????
            MemLogin ml = memLoginService.findByMobile(req.getEmail());
            if (ml == null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_120.getCode(), "?????????????????????");
            }
            if (!ml.getAccstatus().equals(Constants.ACCOUNT_ONE)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_116.getCode(), "???????????????");
            }

        }
        // ????????????????????? ??? ????????? smsCountdownYanzheng ??? ???
        // SELECT ((UNIX_TIMESTAMP(now()) - UNIX_TIMESTAMP(ctime)) - 60) dif_second from
        // `user`;
        SysParameter bs = this.sysParamService.getByCode(SysParameterEnum.SMS_SENDWAIT.name());
        if (bs == null || StringUtils.isEmpty(bs.getSysparamvalue())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1000.getCode(), "????????????(sms_sendwait)??????");
        }

        int sendwait = Integer.parseInt(bs.getSysparamvalue());
        req.setCountDown(sendwait);
        Integer countInteger = sysShortmsgService.getCountDown(req);
        logger.info("countInteger: {}", countInteger);
        if (countInteger == null || countInteger > 0) {
            // ??????????????? ?????????
            // ??????????????????
            String smsCode = CommonFunction.getFourRandomSmsCode();
            SysShortmsgDO sysShortmsg = new SysShortmsgDO();
            sysShortmsg.setCountDown(sendwait);
            sysShortmsg.setEmail(req.getEmail());
            sysShortmsg.setMsgtype(req.getSendtype());
            // System.out.println(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            sysShortmsg.setMasdate(new Date());
            sysShortmsg.setMsgcode(smsCode);
            sysShortmsg.setIpaddress("11");
            StringBuilder sms_message = new StringBuilder();
            sms_message.append("????????????");
            sms_message.append(smsCode);

            asyncSendMail(req, sysShortmsg, sms_message, smsCode);

            return sendwait;
        } else {
            // ????????????
            String s = String.valueOf(countInteger).replaceAll("-", "");
            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), s + "??????????????????");
        }
    }


    private void asyncSendMail(UsersRequest req, SysShortmsgDO sysShortmsg, StringBuilder sms_message, String smsCode) {
        emailService.sendRegisterEmail(req.getEmail(), smsCode);

    }


    @Override
    public List<SysAreaDO> getFirstArea() {
        List<SysBusparameter> list = sysBusParamService.selectByParedubpcode("mobileprefix");
        if (!CollectionUtils.isEmpty(list)) {
            List<SysAreaDO> sysAreaDOs = list.stream().map(v -> {
                SysAreaDO d = new SysAreaDO();
                d.setAreacode(v.getBusparamcode());
                d.setAreaname(v.getBusparamname());
                return d;
            }).collect(Collectors.toList());
            return sysAreaDOs;
        }
        return new ArrayList<SysAreaDO>();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> register(UsersRequest req) {
        if (StringUtils.isEmpty(req.getPassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_126.getCode(), "????????????");
        }
        if (StringUtils.isBlank(req.getInvitecode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_141.getCode(), "???????????????");
        }
        // ?????? ?????? ?????? ????????????
        List<String> jPwd = CommonFunction.jiandanPwd();
        if (jPwd.contains(req.getPassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_127.getCode(), "??????????????????????????????");
        }
        MemLogin ml = memLoginService.findByEmailRegister(req.getEmail());
        if (ml != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "??????????????????");
        }

        SysParameter sysIpLimit = sysParamService.getByCode("SAME_IP_REGISTER_NUM");
        Integer ipLimit = Integer.parseInt(sysIpLimit.getSysparamvalue());
        SysParameter sysDeviceLimit = sysParamService.getByCode("SAME_DEVICE_REGISTER_NUM");
        Integer deviceLimit = Integer.parseInt(sysDeviceLimit.getSysparamvalue());

        String registerIp = req.getClintipadd();
        Example example = new Example(MemBaseinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("registerIp", registerIp);
        Integer countIp = memBaseinfoMapper.selectCountByExample(example);
//        if (countIp >= ipLimit) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1031.getCode(), "???ip????????????3?????????");
//        }
//        Example example2 = new Example(MemBaseinfo.class);
//        Example.Criteria criteria2 = example2.createCriteria();
//        criteria2.andEqualTo("registerDev", req.getDeviceCode());
//        Integer countDevice = memBaseinfoMapper.selectCountByExample(example2);
//        if (countDevice >= deviceLimit) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1032.getCode(), "?????????????????????1?????????");
//        }

        // ????????????
        MemBaseinfo newUser = new MemBaseinfo();
        newUser.setNationality(req.getAreacode());
        newUser.setAccno(RandomUtil.uuid());
        newUser.setUniqueId(SnowFlakeUtil.getId().toString());
        newUser.setEmail(req.getEmail());
        String dateStr = DateUtils.currentTimeStr();
        newUser.setNickname(NameGeneratorUtil.generate() + dateStr.substring(dateStr.length() - 3));
        newUser.setFansnum(0L);
        newUser.setGoldnum(BigDecimal.ZERO);
        newUser.setRegisterdate(new Date());
        newUser.setIsDelete(false);
        newUser.setCreateUser(newUser.getAccno());
        newUser.setUpdateUser(newUser.getAccno());
        newUser.setRegisterDev(req.getDeviceCode());

        logger.info("puff  getDeviceCode" + req.getDeviceCode());
        newUser.setLogintype(LoginUserTypeEnum.ORDINARY.getCode());
        newUser.setClintipadd(req.getClintipadd());
        newUser.setRegisterIp(req.getClintipadd());
        newUser.setMemorgin(Constants.REGIST);
        newUser.setHeadimg(sysBusParamService.getRandomHeadImg());
        // ??????????????????
        memBaseinfoService.insertSelective(newUser);
        // ??????????????????????????????
        newUser.setRecomcode(InvitationCodeGnerateUtil.generateInvitationCodeTwo(newUser));
        memBaseinfoService.updateByPrimaryKeySelective(newUser);

        // ????????????
        MemLogin memLogin = new MemLogin();
        memLogin.setLoginnum(0);
        memLogin.setAcclogin(newUser.getEmail());
        memLogin.setAccno(newUser.getAccno());
        memLogin.setLogintype(LoginUserTypeEnum.ORDINARY.getCode());
        memLogin.setPasswordmd5(req.getPassword());
        memLogin.setAccstatus(Constants.ACCOUNT_ONE);
        memLogin.setClintipadd(req.getClintipadd());
        int i = memLoginService.insertSelectiveMemLogin(memLogin);
        if (i == 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "?????????????????????");
        }
        boolean flag = memCreditService.initMemberCredit(newUser.getAccno());
        if (!flag) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1119.getCode(), "?????????????????????????????????");
        }
        asynRegister(req, newUser);
        registerGive(newUser);
        return new HashMap<String, Object>();

    }

    private void asynRegister(UsersRequest req, MemBaseinfo newUser) {
        // ?????????????????????
        MemBaseinfo inviteUser = inviteUserProcess(req, newUser);
        //?????????????????????
        memLevelService.initMemLevel(newUser.getAccno());

        MemSubInfo memSubInfo = memSubInfoMapper.selectMemSubInfoByAccNo(inviteUser.getAccno());
        if (ObjectUtil.isNull(memSubInfo)) {
            memSubInfo = new MemSubInfo();
            memSubInfo.setAccno(inviteUser.getAccno());
            memSubInfo.setLevelOneAgents(Arrays.asList(newUser.getAccno()));
            Set<MemSubInfo> list = new HashSet<>();
            list.add(memSubInfo);
            memSubInfoMapper.insertBatch(list);
        } else {
            MemRelationship super1Mem = memRelationshipService.findByAccno(inviteUser.getAccno());
            if (ObjectUtil.isNotNull(super1Mem)) {
                MemSubInfo top1 = queryMemSubInfo(super1Mem.getAccno());
                if (ObjectUtil.isNotNull(top1)) {
                    List<String> oneList = top1.getLevelOneAgents();
                    oneList.add(newUser.getAccno());
                    memSubInfo.setLevelOneAgents(oneList);
                    memSubInfoMapper.updateTop1(top1);
                }
                MemRelationship super2Mem = memRelationshipService.findByAccno(super1Mem.getRefaccno());
                if (ObjectUtil.isNotNull(super2Mem)) {
                    MemSubInfo top2 = queryMemSubInfo(super2Mem.getAccno());
                    if (ObjectUtil.isNotNull(top2)) {
                        List<String> twoList = top2.getLevelTwoAgents();
                        twoList.add(newUser.getAccno());
                        memSubInfo.setLevelTwoAgents(twoList);
                        memSubInfoMapper.updateTop2(top2);
                    } else {
                        top2 = new MemSubInfo();
                        top2.setAccno(super2Mem.getAccno());
                        top2.setLevelTwoAgents(Arrays.asList(newUser.getAccno()));
                        Set<MemSubInfo> list = new HashSet<>();
                        list.add(top2);
                        memSubInfoMapper.insertBatch(list);
                    }
                    MemRelationship super3Mem = memRelationshipService.findByAccno(super2Mem.getRefaccno());
                    if (ObjectUtil.isNotNull(super3Mem)) {
                        MemSubInfo top3 = queryMemSubInfo(super3Mem.getAccno());
                        if (ObjectUtil.isNotNull(top3)) {
                            List<String> threeList = top3.getLevelThreeAgents();
                            threeList.add(newUser.getAccno());
                            memSubInfo.setLevelThreeAgents(threeList);
                            memSubInfoMapper.updateTop3(top3);
                        } else {
                            top3 = new MemSubInfo();
                            top3.setAccno(super3Mem.getAccno());
                            top3.setLevelThreeAgents(Arrays.asList(newUser.getAccno()));
                            Set<MemSubInfo> list = new HashSet<>();
                            list.add(top3);
                            memSubInfoMapper.insertBatch(list);
                        }
                    }
                }
            }
        }

    }

    private MemSubInfo queryMemSubInfo(String accno) {
        return memSubInfoMapper.selectMemSubInfoByAccNo(accno);
    }

    /**
     * ??????????????????????????????????????????????????????
     *
     * @param req
     * @param newUser
     */
    private MemBaseinfo inviteUserProcess(UsersRequest req, MemBaseinfo newUser) {
        // ??????????????? ?????? ????????????
        MemBaseinfo inviteUser = memBaseinfoService.getUserByInvitecode(req.getInvitecode().toLowerCase());
        if (ObjectUtil.isNull(inviteUser) || ObjectUtil.isNull(inviteUser.getIsDelete()) || inviteUser.getIsDelete()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_151.getCode(), "???????????????");
        }
        memBaseinfoService.updateMemorigin(newUser.getAccno(), Constants.RECOMMEND);
        memBaseinfoService.updateSuperiorId(newUser.getAccno(), inviteUser.getUniqueId());
        MemRelationship superMem = memRelationshipService.findByAccno(inviteUser.getAccno());
        // ??????????????????
        MemRelationship memRelationship = new MemRelationship();
        memRelationship.setRefaccno(inviteUser.getAccno());
        memRelationship.setSuprecomcode(inviteUser.getRecomcode());
        memRelationship.setAccno(newUser.getAccno());
        memRelationship.setCreateUser(newUser.getAccno());
        memRelationship.setUpdateUser(newUser.getAccno());
        if (inviteUser.getRecomcode().equals("86yl74")) {
            memRelationship.setHeadAccno(newUser.getAccno());
        }
        if (StringUtils.isNotBlank(superMem.getHeadAccno())) {
            memRelationship.setHeadAccno(superMem.getHeadAccno());
        }
        if (ObjectUtil.isNotNull(superMem)) {
            memRelationshipMapperExt.addSubNum(superMem.getAccno());
            memRelationship.setParentId(superMem.getRelaid());
        }
        memRelationship.setIsDelete(false);
        int relateFlag = memRelationshipService.insertSelectiveMemRelationship(memRelationship);
        if (relateFlag < 1) {
            logger.error("{} register ?????????{} ????????????????????????", req.getEmail(), req.getInvitecode());
        }
        return inviteUser;
    }

    private void registerGive(MemBaseinfo memBaseinfo) {
        String timeStr = "2022-06-24 00:00:00";
        Date date = DateUtils.str2date(timeStr);
        Date now = new Date();
        if (now.before(date)) {
            return;
        }
        BigDecimal amount = new BigDecimal(0);
        SysParameter giveAmount = this.sysParamService.getByCode("REGISTER_GIVE_AMOUNT");
        if (giveAmount == null || StringUtils.isEmpty(giveAmount.getSysparamvalue())) {
            return;
        }
        if (giveAmount.getIsDelete().equals(true)) {
            return;
        }
        else {
            amount = amount.add(new BigDecimal(giveAmount.getSysparamvalue()));
        }
        if (amount.equals(BigDecimal.ZERO)) {
            return;
        }
        MemGoldchangeDO change = new MemGoldchangeDO();
        change.setAccno(memBaseinfo.getAccno());
        change.setShowChange(amount);
        change.setQuantity(amount);
        change.setChangetype(GoldchangeEnum.REGISTER_JACKPOT.getValue());
        change.setOpnote("??????:???" + memBaseinfo.getAccno() + "??? ???????????????" + amount + "???");
        memBaseinfoWriteService.updateUserBalance(change);
    }


    @Override
    public void getIpParse(LoginUser loginUser) {
        if (null != loginUser && StringUtils.isNotEmpty(loginUser.getAccno())) {
            String ip = loginUser.getClintipadd();
            String accno = loginUser.getAccno();
            // ????????????
            MemBaseinfo m = memBaseinfoService.getUserByAccno(accno);
            if (m == null || (m != null && StringUtils.isNotEmpty(m.getTag()))) {
                return;
            }
            registerCachedThreadPool.execute(() -> {
                try {
                    long start = System.currentTimeMillis();
                    Map<String, String> map = HttpUtils.ipParse(ip);
                    if (map != null) {
                        logger.info("??????ip??????????????????ipmap???" + map);
                        String country = map.get("country");
                        String province = map.get("province");
                        String city = map.get("city");
                        if ("XX".equals(city) || "??????IP".equals(city)) {
                            if ("XX".equals(province) || "??????IP".equals(province)) {
                                if ("XX".equals(country) || "??????IP".equals(country)) {
                                    // logger.info("??????ip????????????????????????");
                                } else {
                                    MemBaseinfo memBaseinfo = new MemBaseinfo();
                                    memBaseinfo.setAccno(accno);
                                    memBaseinfo.setTag(country);
                                    memBaseinfo.setLogincountry(country);
                                    memBaseinfoService.updateAddress(m.getMemid(), memBaseinfo);
                                }
                            } else {
                                MemBaseinfo memBaseinfo = new MemBaseinfo();
                                memBaseinfo.setAccno(accno);
                                memBaseinfo.setTag(province);
                                memBaseinfo.setLogincountry(country);
                                memBaseinfoService.updateAddress(m.getMemid(), memBaseinfo);
                            }
                        } else {
                            MemBaseinfo memBaseinfo = new MemBaseinfo();
                            memBaseinfo.setAccno(accno);
                            memBaseinfo.setTag(city);
                            memBaseinfo.setLogincountry(country);
                            memBaseinfoService.updateAddress(m.getMemid(), memBaseinfo);
                        }
                    }
                    logger.info("IP??????????????????: " + (System.currentTimeMillis() - start));
                } catch (Exception e) {
                    logger.info(e.getMessage());
                }
            });
        }

    }

    @Override
    public LoginUser login(UsersRequest req, String source) {
        if (StringUtils.isEmpty(req.getEmail())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "????????????");
        }
        if (StringUtils.isBlank(req.getPassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_172.getCode(), "????????????");
        }

        String LOGINNUMMD5 = checkLoginTimes(req);
        MemLogin memLogin = null;
        if (StringUtils.isNotBlank(req.getPassword())) {
            memLogin = loginByPassword(req);
        }
        // ?????????????????????????????? LOGINNUM
        RedisBusinessUtil.delete(LOGINNUMMD5);

        // ??????
        // ??????????????????
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(memLogin.getAccno());
        if (memBaseinfo == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "?????????????????????");
        }

        //????????????
        String higher = "";
        MemRelationship relation = memRelationshipService.findByAccno(memLogin.getAccno());
        if (null != relation) {
            MemBaseinfo refUser = memBaseinfoService.getUserByAccno(relation.getRefaccno());
            if (null != refUser) {
                higher = refUser.getEmail();
            }
        }
        // ??????loginUserAPP
        LoginUser loginUserAPP = new LoginUser();
        loginUserAPP.setLogintype(memLogin.getLogintype());
        loginUserAPP.setAccno(memBaseinfo.getAccno());
        loginUserAPP.setMobileno(memBaseinfo.getEmail());
        loginUserAPP.setNickname(memBaseinfo.getNickname());
        loginUserAPP.setMemname(memBaseinfo.getMemname());
        loginUserAPP.setMemid(memBaseinfo.getMemid());
        loginUserAPP.setRecomcode(memBaseinfo.getRecomcode());
        loginUserAPP.setAcclogin(memLogin.getAcclogin());
        loginUserAPP.setSourceType(source);
        loginUserAPP.setHeadimgurl(memBaseinfo.getHeadimg());
        loginUserAPP.setHigher(higher);
        MemLevel memLevel = memLevelMapperExt.selectByAccno(memBaseinfo.getAccno());
        if (memLevel == null) {
            loginUserAPP.setMemlevel(Constants.LEVEL_ONE);
            loginUserAPP.setLevelSeq(0);
        } else {
            MemberLevelResponse memLevelConfig = memLevelConfigService.getMemLevelConfig(memBaseinfo.getAccno());
            loginUserAPP.setLevelSeq(memLevelConfig.getLevelSeq());
            loginUserAPP.setMemlevel(memLevel.getMemlevel());
        }
        // ??????sessionId
        String sessionid = RedisBusinessUtil.createSessionID(userSessionKey, loginUserAPP, sysParamService);
        if (sessionid.isEmpty()) {
            throw new BusinessException("401", "???????????????");
        }
        logger.info("app???????????????{},?????????token{}", loginUserAPP.getAccno(), sessionid);
        loginUserAPP.setAcctoken(sessionid);
        //??????????????????
        if (StringUtils.isNotEmpty(source)) {
            RedisBusinessUtil.saveMemberOnline(source, memBaseinfo.getMemid().toString());
        }
        threadPool.execute(() -> doOtherThings(req, source, memBaseinfo, loginUserAPP));

        return loginUserAPP;
    }

    private void doOtherThings(UsersRequest req, String source, MemBaseinfo memBaseinfo, LoginUser loginUserAPP) {
        // ??????
        // ????????????????????????
        MemLogin memLoginParam = new MemLogin();
        memLoginParam.setAccno(memBaseinfo.getAccno());
        memLoginParam.setAcclogin(req.getEmail());
        memLoginParam.setLastlogindate(new Date());
        memLoginParam.setClintipadd(req.getClintipadd());
        memLoginService.updateLogin(memLoginParam);

        // ????????????
        SysInfolog sysInfolog = new SysInfolog();
        sysInfolog.setAccno(memBaseinfo.getAccno());
        sysInfolog.setSystemname(ModuleConstant.LIVE_APP);
        sysInfolog.setModelname("APP????????????");
        sysInfolog.setOptcontent("???" + memBaseinfo.getUniqueId() + "?????????");
        sysInfolog.setOptip(req.getClintipadd());
        sysInfolog.setServerip(req.getServerIp());
        sysInfolog.setLongitude(String.valueOf(req.getLongitude()));
        sysInfolog.setLatitude(String.valueOf(req.getLatitude()));
        sysInfolog.setOrginfo("login");
        sysInfologService.insert(sysInfolog);

        // ??????????????????
        memBaseinfoService.updateLastLoginDev(memBaseinfo.getAccno(), req.getClientPhoneModel());
        // ??????????????????????????????????????? ??????????????????????????????????????????????????????????????? ????????????????????????
        UserGoldDO userGoldDO = memBaseinfoService.getUserRecomcodeGold(memBaseinfo.getAccno());
        try {
            if (loginUserAPP != null) {
                // ??????IP ????????????????????????
                // ???????????????????????????????????????
                loginUserAPP.setClintipadd(req.getClintipadd());
                getIpParse(loginUserAPP);
            }
        } catch (Exception e) {
            logger.error("{}.login ????????????????????????:{},params:{}", this.getClass().getName(), e.getMessage(), req, e);
        }
    }


    private MemLogin loginByPassword(UsersRequest req) {
        if (StringUtils.isBlank(req.getPassword())) {
            return null;
        }
        // ??????????????????
        // ????????????????????????
        // ???????????????
        MemLogin loginParam = new MemLogin();
        loginParam.setAcclogin(req.getEmail());
        MemLogin memLogin = memLoginService.getMemLoginByParam(loginParam);
        if (memLogin == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1130.getCode(), "???????????????");
        }
        if (!Constants.ACCOUNT_ONE.equals(memLogin.getAccstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_116.getCode(), "???????????????");
        }
        if (!req.getPassword().equals(memLogin.getPasswordmd5())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1131.getCode(), "??????/????????????");
        }
        return memLogin;
    }

    private String checkLoginTimes(UsersRequest req) {
        String LOGINNUMMD5 = MD5.md5(Constants.LOGINNUM + req.getEmail());
        Integer loginnum = RedisBusinessUtil.get(LOGINNUMMD5);
        if (loginnum != null) {
            if (loginnum < 20) {
                Long seconds = RedisBusinessUtil.getExpire(LOGINNUMMD5);
                if (seconds > 0) {
                    RedisBusinessUtil.set(LOGINNUMMD5, loginnum + 1);
                    RedisBusinessUtil.setExpire(LOGINNUMMD5, seconds);
                }
            } else {
//                throw new BusinessException(StatusCode.LIVE_ERROR_1032.getCode(), "???????????????????????????????????????????????????12??????????????????????????????????????????????????????????????????");
            }
        } else {
            RedisBusinessUtil.set(LOGINNUMMD5, 1, 12 * 60 * 60l);
        }
        return LOGINNUMMD5;
    }


    @Override
    public String updatePassword(UsersRequest req) {
        if (StringUtils.isEmpty(req.getOldPassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "??????????????????");
        }
        if (StringUtils.isEmpty(req.getPassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "????????????");
        }

        MemLogin memLoginParam = new MemLogin();
        memLoginParam.setAcclogin(req.getAcclogin());
        MemLogin memLogin = memLoginService.getMemLoginByParam(memLoginParam);
        if (memLogin != null) {
            if (!memLogin.getAccstatus().equals(Constants.ACCOUNT_ONE)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "???????????????");
            }
            if (!memLogin.getPasswordmd5().equals(req.getOldPassword())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1130.getCode(), "??????????????????");
            }
            memLoginParam.setPasswordmd5(req.getPassword());
            memLoginParam.setLoginid(memLogin.getLoginid());
            memLoginMapper.updateByPrimaryKeySelective(memLoginParam);
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_1130.getCode(), "???????????????");
        }

        return Constants.SUCCESS_MSG;
    }


    @Override
    public String getSwipeVerificationSwift() {
        String status;
        try {
            SysParameter sysParameter = sysParamService.getByCode(SysParameterEnum.SWIPE_VERIFICATION_SWIFT);
            logger.info("sysParameter:{}", JSONObject.toJSON(sysParameter));
            status = sysParameter.getStatus().toString();
            RedisBusinessUtil.setSystemInfoValue(sysParameter);
        } catch (Exception e) {
            throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "??????????????????????????????");
        }
        return status;
    }


    private String checkRepLoginTimes(ActingUsersRequest req) {
        String LOGINNUMMD5 = MD5.md5(Constants.LOGINNUM + req.getAcclogin());
        Integer loginnum = RedisBusinessUtil.get(LOGINNUMMD5);
        if (loginnum != null) {
            if (loginnum < 20) {
                Long seconds = RedisBusinessUtil.getExpire(LOGINNUMMD5);
                if (seconds > 0) {
                    RedisBusinessUtil.set(LOGINNUMMD5, loginnum + 1);
                    RedisBusinessUtil.setExpire(LOGINNUMMD5, seconds);
                }
            } else {
                throw new BusinessException(StatusCode.LIVE_ERROR_130.getCode(), "???????????????????????????????????????????????????12??????????????????????????????????????????????????????????????????");
            }
        } else {
            RedisBusinessUtil.set(LOGINNUMMD5, 1, 12 * 60 * 60l);
        }
        return LOGINNUMMD5;
    }

}
