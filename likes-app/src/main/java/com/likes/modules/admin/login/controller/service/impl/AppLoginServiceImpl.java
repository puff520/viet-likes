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
            throw new BusinessException(StatusCode.LIVE_ERROR_115.getCode(), "邮箱格式不正确");
        }

        /**
         * 验证这个手机号是否已经存在 如果是发送注册
         * 手机号 存在才可以 1短信登陆 ;2找回密码 ;4.修改密码
         */
        // 验证这个手机号是否已经存在 如果是发送注册
        if (req.getSendtype() == 3) {
            // 手机号 不存在 才 3注册；
            MemLogin ml = memLoginService.findByEmail(req.getEmail());
            if (ml != null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "该手机号已存在");
            }
        } else {
            // 手机号 存在才可以 1短信登陆 ;2找回密码 ;4.修改密码
            MemLogin ml = memLoginService.findByMobile(req.getEmail());
            if (ml == null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_120.getCode(), "该手机号不存在");
            }
            if (!ml.getAccstatus().equals(Constants.ACCOUNT_ONE)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_116.getCode(), "账号已禁用");
            }

        }
        // 验证这个手机号 在 倒计时 smsCountdownYanzheng 秒 内
        // SELECT ((UNIX_TIMESTAMP(now()) - UNIX_TIMESTAMP(ctime)) - 60) dif_second from
        // `user`;
        SysParameter bs = this.sysParamService.getByCode(SysParameterEnum.SMS_SENDWAIT.name());
        if (bs == null || StringUtils.isEmpty(bs.getSysparamvalue())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1000.getCode(), "系统参数(sms_sendwait)异常");
        }

        int sendwait = Integer.parseInt(bs.getSysparamvalue());
        req.setCountDown(sendwait);
        Integer countInteger = sysShortmsgService.getCountDown(req);
        logger.info("countInteger: {}", countInteger);
        if (countInteger == null || countInteger > 0) {
            // 过了一分钟 才能发
            // 可以发送短信
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
            sms_message.append("验证码：");
            sms_message.append(smsCode);

            asyncSendMail(req, sysShortmsg, sms_message, smsCode);

            return sendwait;
        } else {
            // 明确提示
            String s = String.valueOf(countInteger).replaceAll("-", "");
            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), s + "秒后才能发送");
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
            throw new BusinessException(StatusCode.LIVE_ERROR_126.getCode(), "密码为空");
        }
        if (StringUtils.isBlank(req.getInvitecode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_141.getCode(), "邀请码为空");
        }
        // 检查 密码 是否 过于简单
        List<String> jPwd = CommonFunction.jiandanPwd();
        if (jPwd.contains(req.getPassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_127.getCode(), "您设置的密码过于简单");
        }
        MemLogin ml = memLoginService.findByEmailRegister(req.getEmail());
        if (ml != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "该邮箱已存在");
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
//            throw new BusinessException(StatusCode.LIVE_ERROR_1031.getCode(), "同ip只能注册3个账号");
//        }
//        Example example2 = new Example(MemBaseinfo.class);
//        Example.Criteria criteria2 = example2.createCriteria();
//        criteria2.andEqualTo("registerDev", req.getDeviceCode());
//        Integer countDevice = memBaseinfoMapper.selectCountByExample(example2);
//        if (countDevice >= deviceLimit) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1032.getCode(), "同设备只能注册1个账号");
//        }

        // 插入用户
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
        // 用户基本信息
        memBaseinfoService.insertSelective(newUser);
        // 设置注册用户的邀请码
        newUser.setRecomcode(InvitationCodeGnerateUtil.generateInvitationCodeTwo(newUser));
        memBaseinfoService.updateByPrimaryKeySelective(newUser);

        // 登陆用户
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
            throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "该手机号已存在");
        }
        boolean flag = memCreditService.initMemberCredit(newUser.getAccno());
        if (!flag) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1119.getCode(), "会员信用分初始化失败！");
        }
        asynRegister(req, newUser);
        registerGive(newUser);
        return new HashMap<String, Object>();

    }

    private void asynRegister(UsersRequest req, MemBaseinfo newUser) {
        // 建立推荐人关系
        MemBaseinfo inviteUser = inviteUserProcess(req, newUser);
        //初始化用户等级
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
     * 根据邀请码进来的用户，建立推荐人关系
     *
     * @param req
     * @param newUser
     */
    private MemBaseinfo inviteUserProcess(UsersRequest req, MemBaseinfo newUser) {
        // 根据邀请码 获取 对应的人
        MemBaseinfo inviteUser = memBaseinfoService.getUserByInvitecode(req.getInvitecode().toLowerCase());
        if (ObjectUtil.isNull(inviteUser) || ObjectUtil.isNull(inviteUser.getIsDelete()) || inviteUser.getIsDelete()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_151.getCode(), "无效邀请码");
        }
        memBaseinfoService.updateMemorigin(newUser.getAccno(), Constants.RECOMMEND);
        memBaseinfoService.updateSuperiorId(newUser.getAccno(), inviteUser.getUniqueId());
        MemRelationship superMem = memRelationshipService.findByAccno(inviteUser.getAccno());
        // 插入推荐关系
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
            logger.error("{} register 邀请码{} 已经存在邀请关系", req.getEmail(), req.getInvitecode());
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
        change.setOpnote("用户:【" + memBaseinfo.getAccno() + "】 注册赠送【" + amount + "】");
        memBaseinfoWriteService.updateUserBalance(change);
    }


    @Override
    public void getIpParse(LoginUser loginUser) {
        if (null != loginUser && StringUtils.isNotEmpty(loginUser.getAccno())) {
            String ip = loginUser.getClintipadd();
            String accno = loginUser.getAccno();
            // 是否存在
            MemBaseinfo m = memBaseinfoService.getUserByAccno(accno);
            if (m == null || (m != null && StringUtils.isNotEmpty(m.getTag()))) {
                return;
            }
            registerCachedThreadPool.execute(() -> {
                try {
                    long start = System.currentTimeMillis();
                    Map<String, String> map = HttpUtils.ipParse(ip);
                    if (map != null) {
                        logger.info("根据ip获取城市地址ipmap：" + map);
                        String country = map.get("country");
                        String province = map.get("province");
                        String city = map.get("city");
                        if ("XX".equals(city) || "内网IP".equals(city)) {
                            if ("XX".equals(province) || "内网IP".equals(province)) {
                                if ("XX".equals(country) || "内网IP".equals(country)) {
                                    // logger.info("根据ip获取城市地址：无");
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
                    logger.info("IP地址解析耗时: " + (System.currentTimeMillis() - start));
                } catch (Exception e) {
                    logger.info(e.getMessage());
                }
            });
        }

    }

    @Override
    public LoginUser login(UsersRequest req, String source) {
        if (StringUtils.isEmpty(req.getEmail())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "邮箱为空");
        }
        if (StringUtils.isBlank(req.getPassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_172.getCode(), "密码为空");
        }

        String LOGINNUMMD5 = checkLoginTimes(req);
        MemLogin memLogin = null;
        if (StringUtils.isNotBlank(req.getPassword())) {
            memLogin = loginByPassword(req);
        }
        // 表示登录成功，清楚掉 LOGINNUM
        RedisBusinessUtil.delete(LOGINNUMMD5);

        // 存在
        // 获取详细信息
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(memLogin.getAccno());
        if (memBaseinfo == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "该账号已被删除");
        }

        //上级代理
        String higher = "";
        MemRelationship relation = memRelationshipService.findByAccno(memLogin.getAccno());
        if (null != relation) {
            MemBaseinfo refUser = memBaseinfoService.getUserByAccno(relation.getRefaccno());
            if (null != refUser) {
                higher = refUser.getEmail();
            }
        }
        // 设置loginUserAPP
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
        // 创建sessionId
        String sessionid = RedisBusinessUtil.createSessionID(userSessionKey, loginUserAPP, sysParamService);
        if (sessionid.isEmpty()) {
            throw new BusinessException("401", "请重新登录");
        }
        logger.info("app登录人账号{},登录人token{}", loginUserAPP.getAccno(), sessionid);
        loginUserAPP.setAcctoken(sessionid);
        //记录在线人数
        if (StringUtils.isNotEmpty(source)) {
            RedisBusinessUtil.saveMemberOnline(source, memBaseinfo.getMemid().toString());
        }
        threadPool.execute(() -> doOtherThings(req, source, memBaseinfo, loginUserAPP));

        return loginUserAPP;
    }

    private void doOtherThings(UsersRequest req, String source, MemBaseinfo memBaseinfo, LoginUser loginUserAPP) {
        // 其他
        // 更新用户登陆次数
        MemLogin memLoginParam = new MemLogin();
        memLoginParam.setAccno(memBaseinfo.getAccno());
        memLoginParam.setAcclogin(req.getEmail());
        memLoginParam.setLastlogindate(new Date());
        memLoginParam.setClintipadd(req.getClintipadd());
        memLoginService.updateLogin(memLoginParam);

        // 登陆日志
        SysInfolog sysInfolog = new SysInfolog();
        sysInfolog.setAccno(memBaseinfo.getAccno());
        sysInfolog.setSystemname(ModuleConstant.LIVE_APP);
        sysInfolog.setModelname("APP用户登录");
        sysInfolog.setOptcontent("【" + memBaseinfo.getUniqueId() + "】登录");
        sysInfolog.setOptip(req.getClintipadd());
        sysInfolog.setServerip(req.getServerIp());
        sysInfolog.setLongitude(String.valueOf(req.getLongitude()));
        sysInfolog.setLatitude(String.valueOf(req.getLatitude()));
        sysInfolog.setOrginfo("login");
        sysInfologService.insert(sysInfolog);

        // 记录登录设备
        memBaseinfoService.updateLastLoginDev(memBaseinfo.getAccno(), req.getClientPhoneModel());
        // 查询改用户是否填写的邀请码 ，如果填写邀请码，检查上级是否意见返了金币 ，若没有，加金币
        UserGoldDO userGoldDO = memBaseinfoService.getUserRecomcodeGold(memBaseinfo.getAccno());
        try {
            if (loginUserAPP != null) {
                // 根据IP 获取登录地址城市
                // 有异常也不需要处理，直接过
                loginUserAPP.setClintipadd(req.getClintipadd());
                getIpParse(loginUserAPP);
            }
        } catch (Exception e) {
            logger.error("{}.login 获取登录地址失败:{},params:{}", this.getClass().getName(), e.getMessage(), req, e);
        }
    }


    private MemLogin loginByPassword(UsersRequest req) {
        if (StringUtils.isBlank(req.getPassword())) {
            return null;
        }
        // 账号密码登陆
        // 获取用户是否存在
        // 先验证账号
        MemLogin loginParam = new MemLogin();
        loginParam.setAcclogin(req.getEmail());
        MemLogin memLogin = memLoginService.getMemLoginByParam(loginParam);
        if (memLogin == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1130.getCode(), "账号不存在");
        }
        if (!Constants.ACCOUNT_ONE.equals(memLogin.getAccstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_116.getCode(), "账号已禁用");
        }
        if (!req.getPassword().equals(memLogin.getPasswordmd5())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1131.getCode(), "账号/密码错误");
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
//                throw new BusinessException(StatusCode.LIVE_ERROR_1032.getCode(), "您的登录失败次数已达到最大限制，请12小时后再尝试。您也可以使用短信登录或找回密码");
            }
        } else {
            RedisBusinessUtil.set(LOGINNUMMD5, 1, 12 * 60 * 60l);
        }
        return LOGINNUMMD5;
    }


    @Override
    public String updatePassword(UsersRequest req) {
        if (StringUtils.isEmpty(req.getOldPassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "原始密码为空");
        }
        if (StringUtils.isEmpty(req.getPassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "密码为空");
        }

        MemLogin memLoginParam = new MemLogin();
        memLoginParam.setAcclogin(req.getAcclogin());
        MemLogin memLogin = memLoginService.getMemLoginByParam(memLoginParam);
        if (memLogin != null) {
            if (!memLogin.getAccstatus().equals(Constants.ACCOUNT_ONE)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "账号已禁用");
            }
            if (!memLogin.getPasswordmd5().equals(req.getOldPassword())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1130.getCode(), "原密码不正确");
            }
            memLoginParam.setPasswordmd5(req.getPassword());
            memLoginParam.setLoginid(memLogin.getLoginid());
            memLoginMapper.updateByPrimaryKeySelective(memLoginParam);
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_1130.getCode(), "账号不存在");
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
            throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "查询网易拼图开关出错");
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
                throw new BusinessException(StatusCode.LIVE_ERROR_130.getCode(), "您的登录失败次数已达到最大限制，请12小时后再尝试。您也可以使用短信登录或找回密码");
            }
        } else {
            RedisBusinessUtil.set(LOGINNUMMD5, 1, 12 * 60 * 60l);
        }
        return LOGINNUMMD5;
    }

}
