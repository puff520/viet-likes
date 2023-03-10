package com.likes.modules.admin.users.service.impl;

import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.DomainList;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.dto.AppinfoDO;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.SysCdn;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.mybatis.mapper.MemLoginMapper;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.code.UniqueCodeService;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemLevelService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.service.member.MemRelationshipService;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.service.sys.SysAppInfoService;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.service.sys.SysCndService;
import com.likes.common.service.sys.SysInfologService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.service.sys.SysShortmsgService;
import com.likes.common.util.BaseUtil;
import com.likes.common.util.http.HttpUtils;
import com.likes.modules.admin.common.service.CommonService;
import com.likes.modules.admin.users.service.UsersService;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl extends BaseServiceImpl implements UsersService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private CommonService commonService;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private SysShortmsgService sysShortmsgService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemLoginMapper memLoginMapper;
    @Resource
    private MemLoginService memLoginService;
    @Resource
    private MemRelationshipService memRelationshipService;
    @Resource
    private SysAppInfoService sysAppInfoService;
    @Resource
    private SysBusParamService sysBusParamService;
    @Resource
    private MemGoldchangeService memGoldchangeService;
    @Resource
    private MemLevelService memLevelService;
    @Resource
    private SysCndService sysCndService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private SysInfologService sysInfologService;
    @Resource
    private UniqueCodeService uniqueCodeService;

//    /**
//     * @author ??????
//     * <p>
//     * ????????????
//     */
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public synchronized Integer sendShortMsg(UsersRequest req, HttpServletRequest request) {
//        if (StringUtils.isEmpty(req.getTel()))
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "???????????????");
//
//        if (null == req.getSendtype())
//            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "??????????????????");
//
//        SysParameter issend = this.sysParamService.getByCode(SysParameterEnum.SMS_ONOFF.name());
//        if (issend == null || StringUtils.isEmpty(issend.getSysparamvalue()))
//            throw new BusinessException(StatusCode.LIVE_ERROR_201.getCode(), "??????????????????????????????");
//        if (!"0".equals(issend.getSysparamvalue()))
//            throw new BusinessException(StatusCode.LIVE_ERROR_202.getCode(), "????????????????????????????????????????????????");
//
//        SysParameter validate = this.sysParamService.getByCode(SysParameterEnum.SMS_VALIDATE.name());
//        if (validate == null || StringUtils.isEmpty(validate.getSysparamvalue()))
//            throw new BusinessException(StatusCode.LIVE_ERROR_203.getCode(), "??????????????????????????????");
//
//        SysParameter bs = this.sysParamService.getByCode(SysParameterEnum.SMS_SENDWAIT.name());
//        if (bs == null || StringUtils.isEmpty(bs.getSysparamvalue()))
//            throw new BusinessException(StatusCode.LIVE_ERROR_204.getCode(), "??????????????????????????????");
//
//        MemLogin ml = memLoginService.findByMobile(req.getTel());
//        // 1???????????? ;2???????????? ;3????????? 4.????????????
//        if (req.getSendtype() == 3) {
//            if (ml != null)
//                throw new BusinessException(StatusCode.LIVE_ERROR_205.getCode(), "?????????????????????");
//        } else {
//            if (ml == null)
//                throw new BusinessException(StatusCode.LIVE_ERROR_206.getCode(), "?????????????????????");
//            if (ml.getAccstatus() != Constants.ACCOUNT_ONE)
//                throw new BusinessException(StatusCode.LIVE_ERROR_207.getCode(), "???????????????");
//        }
//        String smsCode = CommonFunction.getFourRandomSmsCode();
//        SysShortmsg sysShortmsg = new SysShortmsg();
//        sysShortmsg.setMobileno(req.getTel());
//        sysShortmsg.setMsgtype(req.getSendtype());
//        sysShortmsg.setMasdate(new Date());
//        sysShortmsg.setMsgcode(smsCode);
//        sysShortmsg.setIpaddress(BaseUtil.getIpAddress(request));
//        sysShortmsg.setMasbody("????????????" + smsCode + "????????????????????????" + Integer.parseInt(validate.getSysparamvalue()) + "?????????????????????????????????");
//        sysShortmsg.setMasstatus("0");
//        if (this.sysShortmsgService.insertByParam(sysShortmsg) <= 0) {
//            int wait = this.sysShortmsgService.selectWaittime(sysShortmsg);
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "?????????????????????" + wait + "????????????");
//        }
//        //  int status = smsService.sendSMS(sysShortmsg.getMobileno(), sysShortmsg.getMasbody(), "");
//        //???????????????????????????
//        //SmsSendResult smsSendResult = SmsSendResult.getResult(true); //SmsSendUtil.send(sysShortmsg.getMobileno(), smsCode, sysShortmsg.getMsgtype());
//        SmsSendResult smsSendResult = SmsSendUtil.send(req.getTel(), smsCode, sysShortmsg.getMsgtype());
//        if (!smsSendResult.isSuccess()) {
//            logger.error("?????????????????????????????????????????? {}" + sysShortmsg.getMobileno());
//        }
//        int wait = this.sysShortmsgService.selectWaittime(sysShortmsg);
//        if (wait > 0) {
//            return wait;
//        }
//        return 0;
//    }
//
//    @Override
//    public List<SysAreaDO> getFirstArea() {
//        List<SysBusparameter> list = sysBusParamService.selectByParedubpcode("mobileprefix");
//        if (!CollectionUtils.isEmpty(list)) {
//            List<SysAreaDO> sysAreaDOs = list.stream().map(v -> {
//                SysAreaDO d = new SysAreaDO();
//                d.setAreacode(v.getBusparamcode());
//                d.setAreaname(v.getBusparamname());
//                return d;
//            }).collect(Collectors.toList());
//            return sysAreaDOs;
//        }
//        return new ArrayList<>();
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public synchronized Integer sendSmsCode(UsersRequest req, HttpServletRequest request) {
//        if (StringUtils.isEmpty(req.getTel())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "???????????????");
//        }
//        if (null == req.getSendtype()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "??????????????????");
//        }
//
//        String ipaddress = BaseUtil.getIpAddress(request);
//        logger.info("ip: {} ", ipaddress);
//        // ????????????ip?????? ?????????????????????????????????
//        SysParameter shortmsg_limit_bs = this.sysParamService.getByCode(SysParameterEnum.SHORTMSG_LIMIT.name());
//        if (shortmsg_limit_bs == null || StringUtils.isEmpty(shortmsg_limit_bs.getSysparamvalue())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "????????????(shortmsg_limit)??????");
//        }
//
//        int shortmsg_limit = Integer.parseInt(shortmsg_limit_bs.getSysparamvalue());
//        SysShortmsg sysShortmsgParam = new SysShortmsg();
//        sysShortmsgParam.setIpaddress(ipaddress);
//        Integer ipcount = sysShortmsgService.getLimit(sysShortmsgParam);
//        if (ipcount >= shortmsg_limit) {
//            logger.info("ip: {} ,???????????? {}", ipaddress, ipcount);
//            throw new BusinessException(StatusCode.LIVE_ERROR_130.getCode(), "??????????????????????????????");
//        }
//
//        // ???????????????????????????????????????
//        SysShortmsg sysShortmsgParam2 = new SysShortmsg();
//        sysShortmsgParam2.setMobileno(req.getTel());
//        Integer ipcount2 = sysShortmsgService.getLimit(sysShortmsgParam2);
//        if (ipcount2 >= shortmsg_limit) {
//            logger.info("mobile: {} ,???????????? {}", req.getTel(), ipcount);
//            throw new BusinessException(StatusCode.LIVE_ERROR_130.getCode(), "??????????????????????????????");
//        }
//
//        // ??????????????????????????????????????? ?????????????????????
//        if (req.getSendtype() == 3) {
//            // ????????? ????????? ??? 3?????????
//            MemLogin ml = memLoginService.findByMobile(req.getTel());
//            if (ml != null) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "?????????????????????");
//            }
//        } else {
//            // ????????? ??????????????? 1???????????? ;2???????????? ;4.????????????
//            MemLogin ml = memLoginService.findByMobile(req.getTel());
//            if (ml == null) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_120.getCode(), "?????????????????????");
//            }
//            if (ml.getAccstatus() != Constants.ACCOUNT_ONE) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "???????????????");
//            }
//        }
//        // ????????????????????? ??? ????????? smsCountdownYanzheng ??? ???
//        // SELECT ((UNIX_TIMESTAMP(now()) - UNIX_TIMESTAMP(ctime)) - 60) dif_second from
//        // `user`;
//        SysParameter bs = this.sysParamService.getByCode(SysParameterEnum.SMS_SENDWAIT.name());
//        if (bs == null || StringUtils.isEmpty(bs.getSysparamvalue())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "????????????(sms_sendwait)??????");
//        }
//        int sendwait = Integer.parseInt(bs.getSysparamvalue());
//        req.setCountDown(sendwait);
//        Integer countInteger = sysShortmsgService.getCountDown(req);
//        logger.info("countInteger: {}", countInteger);
//        if (countInteger == null || countInteger > 0) {
//            // ??????????????? ?????????
//            // ??????????????????
//            String smsCode = CommonFunction.getFourRandomSmsCode();
//            SysShortmsgDO sysShortmsg = new SysShortmsgDO();
//            sysShortmsg.setCountDown(sendwait);
//            sysShortmsg.setMobileno(req.getTel());
//            sysShortmsg.setMsgtype(req.getSendtype());
//            // System.out.println(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
//            sysShortmsg.setMasdate(new Date());
//            sysShortmsg.setMsgcode(smsCode);
//            sysShortmsg.setIpaddress(ipaddress);
//            StringBuilder sms_message = new StringBuilder();
//            // sms_message.append("[??????]");
//            // 1???????????? ;2???????????? ;3?????????4.????????????
//            sms_message.append("????????????");
//            sms_message.append(smsCode);
//
//            SysParameter bsYanzheng = this.sysParamService.getByCode(SysParameterEnum.SMS_VALIDATE.name());
//            if (bsYanzheng == null || StringUtils.isEmpty(bsYanzheng.getSysparamvalue()))
//                throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "????????????(sms_validate)??????");
//            Integer smsCountdownYanzheng = Integer.parseInt(bsYanzheng.getSysparamvalue());
//            sms_message.append("????????????????????????" + smsCountdownYanzheng + "?????????????????????????????????[BB??????]");
//            sysShortmsg.setMasbody(sms_message.toString());
//            int inputLine = 1;
//            // ???????????? ????????????????????? ?????? ????????????
//            if (StringUtils.isEmpty(req.getAreacode()) || Constants.AREACODE_CHINA_MAINLAND_86.equals(req.getAreacode())
//                    || Constants.AREACODE_CHINA_MAINLAND_086.equals(req.getAreacode())) {
//
//                SysParameter bsSms = this.sysParamService.getByCode(SysParameterEnum.SMS_ONOFF.name());
//                if (bsSms == null || com.likes.common.util.StringUtils.isEmpty(bsSms.getSysparamvalue()) || Integer.parseInt(bsSms.getSysparamvalue()) != 1) {
//                    throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "????????????(sms_onoff)??????");
//                }
//                //???????????????????????????
//                //SmsSendResult smsSendResult = SmsSendResult.getResult(true); //SmsSendUtil.send(sysShortmsg.getMobileno(), sysShortmsg.getMsgcode(), sysShortmsg.getMsgtype());
//                SmsSendResult smsSendResult = SmsSendUtil.send(req.getTel(), smsCode, sysShortmsg.getMsgtype());
//                if (smsSendResult.isSuccess()) {
//                    // ????????????
//                    // logger.info("{}?????????????????????{} ",req.getTel(),smsCode);
//                    logger.info("{}?????????????????? ", req.getTel());
//                    sysShortmsg.setMasstatus(Constants.STATUS_SUCCESS);
//                    sysShortmsgService.insertSelectiveSysShortmsg(sysShortmsg);
//                } else {
//                    sysShortmsg.setMasstatus(Constants.STATUS_FAILE);
//                    sysShortmsgService.insertSelective(sysShortmsg);
//                    throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "??????????????????");
//                }
//            } else {
//                throw new BusinessException(StatusCode.LIVE_ERROR_115.getCode(), "????????????");
//            }
//
//            return sendwait;
//        } else {
//            // ????????????
//            String s = String.valueOf(countInteger).replaceAll("-", "");
//            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), s + "??????????????????");
//        }
//
//    }
//
//    @Override
//    public Integer verificationSmsCode(UsersRequest req) {
//        if (StringUtils.isEmpty(req.getTel())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "???????????????");
//        }
//        if (null == req.getSendtype()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "??????????????????");
//        }
//        if (StringUtils.isEmpty(req.getSmscode())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "?????????????????????");
//        }
//
//        SysParameter bsYanzheng = this.sysParamService.getByCode(SysParameterEnum.SMS_VALIDATE.name());
//        if (bsYanzheng == null || StringUtils.isEmpty(bsYanzheng.getSysparamvalue()))
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "????????????(sms_validate)??????");
//        Integer smsCountdownYanzheng = Integer.parseInt(bsYanzheng.getSysparamvalue());
//
//        logger.info("{}????????????", smsCountdownYanzheng);
//        // ?????????????????????????????????????????????
//        req.setCountDown(smsCountdownYanzheng * 60);
//        // ???????????????5??????????????????
//        logger.info("?????????{}", JSON.toJSONString(req));
//        Integer shortmsgCountDown = sysShortmsgService.getMsgByParamCountDown(req);
//        if (null == shortmsgCountDown) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "???????????????");
//        }
//
//        if (shortmsgCountDown > 0) {
//            // ???????????? 5??????
//            logger.info("??????????????? {}???", shortmsgCountDown);
//            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "????????????");
//        }
//
//        // ????????????
//        SysShortmsg shortmsg = sysShortmsgService.getMsgByParam(req);
//        SysShortmsg updateSysShortmsg = new SysShortmsg();
//        updateSysShortmsg.setShortmsgid(shortmsg.getShortmsgid());
//        updateSysShortmsg.setMasstatus(Constants.STATUS_USED);
//        sysShortmsgService.updateByPrimaryKeySelective(updateSysShortmsg);
//
//        // ????????? ????????????????????????
//        if (StringUtils.isNotEmpty(req.getInvitecode())) {
//            MemBaseinfo inviteUser = memBaseinfoService.getUserByInvitecode(req.getInvitecode());
//            if (inviteUser == null || inviteUser.getIsDelete()) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "?????????????????????????????????");
//            }
//        }
//
//        return 1;
//    }
//
//    // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Map<String, Object> register(UsersRequest req) {
//
//        if (StringUtils.isEmpty(req.getTel())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "???????????????");
//        }
//        if (StringUtils.isEmpty(req.getPassword())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "????????????");
//        }
//        if (StringUtils.isEmpty(req.getSmscode())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "?????????????????????");
//        }
//        if (StringUtils.isEmpty(req.getInvitecode())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1104.getCode(), "???????????????");
//        }
//
//        // ?????? ?????? ?????? ????????????
//        List<String> jPwd = CommonFunction.jiandanPwd();
//        if (jPwd.contains(req.getPassword())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "??????????????????????????????");
//        }
//
//        MemLogin ml = memLoginService.findByMobileRegister(req.getTel());
//        if (ml != null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "?????????????????????");
//        }
//
//        // ????????? ????????? ?????? ??? ???????????????
//        req.setMasstatus(Constants.STATUS_USED);
//        req.setSendtype(3);
//        logger.info("?????????{}", JSON.toJSONString(req));
//        SysShortmsg shortmsg = sysShortmsgService.getMsgByParam(req);
//        if (shortmsg != null) {
//            // ????????????
//            MemBaseinfo newUser = new MemBaseinfo();
//            newUser.setAccno(RandomUtil.uuid());
//            newUser.setMobileno(req.getTel());
//            String dateStr = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date());
//            newUser.setNickname(NameGeneratorUtil.generate() + dateStr.substring(dateStr.length() - 3, dateStr.length()));
//            newUser.setFansnum(0l);
//            newUser.setGoldnum(getTradeOffAmount(null));
//            newUser.setRegisterdate(new Date());
//            newUser.setCreateUser(newUser.getAccno());
//            newUser.setUpdateUser(newUser.getAccno());
//            newUser.setLogintype(LoginUserTypeEnum.ORDINARY.getCode());
//            // ??????????????????????????????
//            /*boolean recomcodeFlag = false;
//            String recomcode = null;
//            while (!recomcodeFlag) {
//                recomcode = CommonFunction.inviteCode();
//                MemBaseinfo recomcodeUser = memBaseinfoService.getUserByInvitecodeAll(recomcode);//TODO ?????????????????????????????????
//                if (recomcodeUser == null) {
//                    recomcodeFlag = true;
//                }
//            }*/
//            newUser.setRecomcode(uniqueCodeService.getUniqueCode(UniqueCodeEnum.USER_INVITE));//TODO ?????????????????????????????????
//
//            MemBaseinfo inviteUser = null;
//            // Boolean inviteUserFlag = false;
//            if (StringUtils.isNotEmpty(req.getInvitecode())) {
//                // ??????????????? ?????? ????????????
//                inviteUser = memBaseinfoService.getUserByInvitecode(req.getInvitecode());
//                if (inviteUser == null || inviteUser.getIsDelete()) {
//                    throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "?????????????????????????????????");
//                }
//                newUser.setMemorgin("recommend");
//                // ??????????????????
//                MemRelationship memRelationship = new MemRelationship();
//                memRelationship.setRefaccno(inviteUser.getAccno());
//                memRelationship.setAccno(newUser.getAccno());
//                memRelationship.setCreateUser(newUser.getAccno());
//                memRelationship.setUpdateUser(newUser.getAccno());
//                // memRelationshipMapper.insertSelective(memRelationship);
//                int i = memRelationshipService.insertSelectiveMemRelationship(memRelationship);
//                if (i > 0) {
//                    // inviteUserFlag = true;
//                    // ??????????????? ??????????????? ?????? ?????????
//                    MemFollow memFollow = new MemFollow();
//                    memFollow.setAccno(newUser.getAccno());
//                    memFollow.setMemid(inviteUser.getMemid());
//                    memFollow.setCreateUser(newUser.getAccno());
//                    memFollow.setUpdateUser(newUser.getAccno());
//                    memFollowService.insertSelective(memFollow);
//
//                    UsersRequest ur = new UsersRequest();
//                    ur.setMemid(inviteUser.getMemid());
//                    ur.setIsattention(1);
//                    memBaseinfoService.updateFansNum(ur);
//                } else {
//                    throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "????????????????????????");
//                }
//
//            } else {
//                newUser.setMemorgin("regist");
//            }
//
//            // ??????????????????
//            memBaseinfoService.insertSelective(newUser);
//
//            // ????????????
//            MemLogin memLogin = new MemLogin();
//            memLogin.setLoginnum(0);
//            memLogin.setAcclogin(newUser.getMobileno());
//            memLogin.setAccno(newUser.getAccno());
//            memLogin.setLogintype(LoginUserTypeEnum.ORDINARY.getCode());
//            memLogin.setPasswordmd5(req.getPassword());
//            memLogin.setAccstatus(Constants.ACCOUNT_ONE);
//
//            int i = memLoginService.insertSelectiveMemLogin(memLogin);
//            if (i == 0) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_1119.getCode(), "?????????????????????");
//            }
//            // ????????????
//            // ????????????????????????APP?????????????????????
//            // List<AppinfoDO> appdownurl = sysAppInfoMapper.getMaxXin();
//            Map<String, Object> map = new HashMap<String, Object>();
//            // map.put("appdownurl", appdownurl);
//            memLevelService.initMemLevel(newUser.getAccno());
//            return map;
//        } else {
//            // ????????? ?????? ??????????????? ????????????
//            throw new BusinessException(StatusCode.LIVE_ERROR_107.getCode(), "???????????????");
//        }
//    }
//
//    @Override
//    public String updatePassword(UsersRequest req) {
//        if (StringUtils.isEmpty(req.getTel())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "???????????????");
//        }
//        if (StringUtils.isEmpty(req.getPassword())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "????????????");
//        }
//        if (StringUtils.isEmpty(req.getSmscode())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "?????????????????????");
//        }
//
//        // ?????? ?????? ?????? ????????????
//        List<String> jPwd = CommonFunction.jiandanPwd();
//        if (jPwd.contains(req.getPassword())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "??????????????????????????????");
//        }
//
//        // ????????? ????????? ?????? ??? ???????????????
//        req.setMasstatus(Constants.STATUS_USED);
//        req.setSendtype(2);
//        SysShortmsg shortmsg = sysShortmsgService.getMsgByParam(req);
//        if (shortmsg != null) {
//            MemLogin memLoginParam = new MemLogin();
//            memLoginParam.setAcclogin(req.getTel());
//            // memLoginParam.setAccstatus(Constants.ACCOUNT_ONE);
//            MemLogin memLogin = memLoginService.getMemLoginByParam(memLoginParam);
//            if (memLogin != null) {
//                if (memLogin.getAccstatus() != Constants.ACCOUNT_ONE) {
//                    throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "???????????????");
//                }
//                memLoginParam.setPasswordmd5(req.getPassword());
//                memLoginParam.setLoginid(memLogin.getLoginid());
//                memLoginMapper.updateByPrimaryKeySelective(memLoginParam);
//            } else {
//                throw new BusinessException(StatusCode.LIVE_ERROR_111.getCode(), "???????????????");
//            }
//
//        } else {
//            // ????????? ?????? ??????????????? ????????????
//            throw new BusinessException(StatusCode.LIVE_ERROR_110.getCode(), "???????????????");
//        }
//
//        return Constants.SUCCESS_MSG;
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public LoginUser login(UsersRequest req, HttpServletRequest request) {
//        if (StringUtils.isEmpty(req.getTel())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "???????????????");
//        }
//
//        String LOGINNUMMD5 = MD5.md5(Constants.LOGINNUM + req.getTel());
//        MemLogin memLogin = null;
//        if (StringUtils.isNotEmpty(req.getPassword())) {
//            Integer loginnum = (Integer) redisTemplate.opsForValue().get(LOGINNUMMD5);
//            if (loginnum == null) {
//                redisTemplate.opsForValue().set(LOGINNUMMD5, 1, 12 * 60 * 60l);
//            } else {
//                if (loginnum >= 20) {
//                    throw new BusinessException(StatusCode.LIVE_ERROR_130.getCode(), "?????????????????????????????????????????????12?????????????????????" + "?????????????????????????????????????????????");
//                }
//                Long seconds = RedisBusinessUtil.getExpire(LOGINNUMMD5);
//                if (seconds > 0) {
//                    redisTemplate.opsForValue().set(LOGINNUMMD5, loginnum + 1, seconds, TimeUnit.SECONDS);
//                }
//            }
//
//            // ??????????????????
//            // ????????????????????????
//
//            // ???????????????
//            MemLogin loginParam = new MemLogin();
//            loginParam.setAcclogin(req.getTel());
//            memLogin = memLoginService.getMemLoginByParam(loginParam);
//            if (memLogin == null) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_1130.getCode(), "???????????????");
//            }
//            if (!Constants.ACCOUNT_ONE.equals(memLogin.getAccstatus())) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "???????????????");
//            }
//
//            MemLogin memLoginParam = new MemLogin();
//            memLoginParam.setAcclogin(req.getTel());
//            // memLoginParam.setAccstatus(Constants.ACCOUNT_ONE);
//            memLoginParam.setPasswordmd5(req.getPassword());
//            memLogin = memLoginService.getMemLoginByParam(memLoginParam);
//            if (memLogin == null) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_112.getCode(), "??????/????????????");
//            }
//
//        } else if (StringUtils.isEmpty(req.getSmscode())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_131.getCode(), "????????????");
//        }
//
//        // ????????????
//        SysParameter bsYanzheng = this.sysParamService.getByCode(SysParameterEnum.SMS_VALIDATE.name());
//        if (bsYanzheng == null || StringUtils.isEmpty(bsYanzheng.getSysparamvalue())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "????????????(sms_validate)??????");
//        }
//
//        Integer smsCountdownYanzheng = Integer.parseInt(bsYanzheng.getSysparamvalue());
//        logger.info("{}????????????", smsCountdownYanzheng);
//
//        // ?????????????????????????????????????????????
//        req.setCountDown(smsCountdownYanzheng * 60);
//        // ???????????????5??????????????????
//        logger.info("?????????{}", JSON.toJSONString(req));
//        // req.setMasstatus(Constants.STATUS_USED);
//        req.setSendtype(1);
//        Integer shortmsgCountDown = sysShortmsgService.getMsgByParamCountDown(req);
//        if (null == shortmsgCountDown) {
//            // ??????????????? ?????? ???????????? ??????????????????
//            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "???????????????");
//        }
//
//        if (shortmsgCountDown > 0) {
//            // ???????????? 5??????
//            logger.info("??????????????? {}???", shortmsgCountDown);
//            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "????????????");
//        }
//
//        // ????????? ????????? ?????? ??????
//        // req.setMasstatus(Constants.STATUS_USED);
//        req.setSendtype(1);
//        SysShortmsg shortmsg = sysShortmsgService.getMsgByParam(req);
//        if (shortmsg == null || !shortmsg.getMasstatus().equals(Constants.STATUS_SUCCESS)) {
//            // ????????? ???????????? ??????????????? ????????????
//            throw new BusinessException(StatusCode.LIVE_ERROR_107.getCode(), "???????????????");
//        }
//
//        MemLogin memLoginParam = new MemLogin();
//        memLoginParam.setAcclogin(req.getTel());
//        // memLoginParam.setAccstatus(Constants.ACCOUNT_ONE);
//        memLogin = memLoginService.getMemLoginByParam(memLoginParam);
//
//        if (memLogin == null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_112.getCode(), "???????????????");
//        }
//
//        if (!Constants.ACCOUNT_ONE.equals(memLogin.getAccstatus())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "???????????????");
//        }
//
//        // ????????????
//        SysShortmsg updateSysShortmsg = new SysShortmsg();
//        updateSysShortmsg.setShortmsgid(shortmsg.getShortmsgid());
//        updateSysShortmsg.setMasstatus(Constants.STATUS_USED);
//        sysShortmsgService.updateByPrimaryKeySelective(updateSysShortmsg);
//
//        // ?????????????????????????????? LOGINNUM
//        if (redisTemplate.hasKey(LOGINNUMMD5)) {
//            redisTemplate.delete(LOGINNUMMD5);
//        }
//
//        // ??????
//        // ??????????????????
//        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(memLogin.getAccno());
//        if (memBaseinfo == null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "?????????????????????");
//        }
//
//        // ??????loginUserAPP
//        LoginUser loginUserAPP = new LoginUser();
//        loginUserAPP.setLogintype(memLogin.getLogintype());
//        loginUserAPP.setAccno(memBaseinfo.getAccno());
//        loginUserAPP.setMobileno(memBaseinfo.getMobileno());
//        loginUserAPP.setNickname(memBaseinfo.getNickname());
//        loginUserAPP.setMemname(memBaseinfo.getMemname());
//        loginUserAPP.setMemid(memBaseinfo.getMemid());
//        loginUserAPP.setRecomcode(memBaseinfo.getRecomcode());
//        loginUserAPP.setAcclogin(memLogin.getAcclogin());
//        MemLevel memLevel = memLevelService.selectByAccno(memBaseinfo.getAccno());
//        if (memLevel == null) {
//            loginUserAPP.setMemlevel(Constants.LEVEL_ONE);
//        } else {
//            loginUserAPP.setMemlevel(memLevel.getMemlevel());
//        }
//        // ??????sessionId
//        String sessionid = RedisBusinessUtil.createSessionID(userSessionKey, loginUserAPP, sysParamService);
//        if (sessionid.isEmpty()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_112.getCode(), "???????????????");
//        }
//        loginUserAPP.setAcctoken(sessionid);
//
//        // ??????
//        // ????????????????????????
//        String clintipadd = BaseUtil.getIpAddress(request);
//        MemLogin memLoginCurr = new MemLogin();
//        memLoginCurr.setAccno(memBaseinfo.getAccno());
//        memLoginCurr.setAcclogin(req.getTel());
//        memLoginCurr.setLastlogindate(new Date());
//        memLoginCurr.setClintipadd(clintipadd);
//        memLoginService.updateLogin(memLoginCurr);
//
//        loginUserAPP.setClintipadd(clintipadd);
//
//        // ????????????
//        sysInfologService.insertAppLog(memBaseinfo.getAccno(), "APP????????????", "[" + memBaseinfo.getMobileno() + "]??????", req.getLongitude(), req.getLatitude(), "login");
//
//        // ????????? ????????? why no why?
//        // loginUserAPP.setAccno(null);
//        // loginUserAPP.setMemname(null);
//        // ????????????
//        List<String> cacheimages = new ArrayList<>();
//        // ???????????? + ?????? ?????? + ??????
//        if (StringUtils.isNotEmpty(memBaseinfo.getHeadimg())) {
//            cacheimages.add(memBaseinfo.getHeadimg());
//        }
//        cacheimages.addAll(commonService.getCacheimages());
//        loginUserAPP.setCacheimages(cacheimages);
//
//        // ??????????????????????????????????????? ??????????????????????????????????????????????????????????????? ????????????????????????
//        // ????????????
//        UserGoldDO userGoldDO = memBaseinfoService.getUserRecomcodeGold(memBaseinfo.getAccno());
//        if (userGoldDO != null && StringUtils.isNotEmpty(userGoldDO.getRefaccno()) && null == userGoldDO.getGoldchangid()) {
//            this.doTuijianUser(userGoldDO, memBaseinfo.getAccno());
//        }
//
//        return loginUserAPP;
//    }

//    @Override
//    public void doTuijianUser(UserGoldDO userGoldDO, String opearteAccno) {
//        SysParameter goldnumParameter = sysParamService.getByCode(SysParameterEnum.RECOMMEND_USER);
//        if (goldnumParameter == null || StringUtils.isEmpty(goldnumParameter.getSysparamvalue()))
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "????????????(recommend_user)??????");
//        double goldnum = Double.parseDouble(goldnumParameter.getSysparamvalue());
//
//        // ??????????????????
//        UsersRequest usersRequest = new UsersRequest();
//        BigDecimal tradeOffAmount = getTradeOffAmount(new BigDecimal(goldnum));
//        usersRequest.setGoldnum(tradeOffAmount.doubleValue());
//        usersRequest.setAccno(userGoldDO.getRefaccno());
//        // ????????????????????????
//        MemGoldchange memGoldchange = new MemGoldchange();
//        memGoldchange.setRefid(userGoldDO.getRelaid());
//        memGoldchange.setAccno(userGoldDO.getRefaccno());
//        memGoldchange.setChangetype(GoldchangeEnum.INVITE_USERS.getValue());
//
//        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(userGoldDO.getRefaccno());
//        if (memBaseinfo == null || memBaseinfo.getIsDelete()) {
//            logger.info(userGoldDO.getRefaccno() + "?????????");
//            // throw new BusinessException(StatusCode.LIVE_ERROR_204.getCode(), "??????????????????");
//        } else {
//            memGoldchange.setGoldnum(getTradeOffAmount(memBaseinfo.getGoldnum()));
//            memGoldchange.setQuantity(tradeOffAmount);
//            BigDecimal recgoldnum = memGoldchange.getGoldnum().add(memGoldchange.getQuantity());
//            memGoldchange.setRecgoldnum(getTradeOffAmount(recgoldnum));
//            memGoldchange.setAmount(tradeOffAmount);
//            memGoldchange.setCreateUser(opearteAccno);
//            memGoldchange.setUpdateUser(opearteAccno);
//            memGoldchange.setOpnote("???????????????");
//
//            // ???insert?????????
//            int i = memGoldchangeService.insertSelectiveMemGoldchange(memGoldchange);
//            if (i > 0) {
//                memBaseinfoService.updateAddGold(usersRequest);
//                logger.info("??????????????????????????????");
//            } else {
//                logger.info("??????????????????");
//                // throw new BusinessException(2104, "??????????????????");
//            }
//        }
//    }

    @Override
    public Map<String, Object> appdownurl() {
        // ????????????
        // ????????????????????????APP?????????????????????
        List<AppinfoDO> appdownurl = sysAppInfoService.getMaxXin();
        Map<String, Object> map = new HashMap<String, Object>();

        appdownurl.forEach(v -> {
            Integer app_type = v.getApp_type();
            if (app_type == 1) {
                map.put("apkdownurl", v.getAppdownurl());
            } else if (app_type == 2) {
                map.put("ipadownurl", v.getAppdownurl());
            }
        });

        return map;
    }

    @Override
    public Map<String, Object> updateapp() {
        List<AppinfoDO> appdownurl = sysAppInfoService.getMaxXin();
        Map<String, Object> map = new HashMap<String, Object>();
        appdownurl.forEach(v -> {
            Date now = new Date();

            Date publishedtime = v.getPublishedtime();
            if (now.getTime() > publishedtime.getTime()) {
                v.setIsPublishedtime(0);
            } else {
                v.setIsPublishedtime(9);
            }

            // ?????????????????? ???????????? ???#?????????
            String[] arr = v.getVersioninfo().split("#");
            v.setVersioninfolist(arr);
            Integer app_type = v.getApp_type();

            if (app_type == 1) {
                map.put("apk", v);
            } else if (app_type == 2) {
                map.put("ipa", v);
            }

        });

        return map;
    }

//    @Override
//    public void getIpParse(LoginUser loginUser) {
//        if (null != loginUser && StringUtils.isNotEmpty(loginUser.getAccno())) {
//            String ip = loginUser.getClintipadd();
//            String accno = loginUser.getAccno();
//            // ????????????
//            MemBaseinfo m = memBaseinfoService.getUserByAccno(accno);
//            if (m == null || (m != null && StringUtils.isNotEmpty(m.getTag()))) {
//                return;
//            } else {
//                try {
//                    new Thread(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            try {
//                                long start = System.currentTimeMillis();
//                                Map<String, String> map = HttpUtils.ipParse(ip);
//                                if (map != null) {
//                                    String country = map.get("country");
//                                    String province = map.get("province");
//                                    String city = map.get("city");
//                                    if ("XX".equals(city) || "??????IP".equals(city)) {
//                                        if ("XX".equals(province) || "??????IP".equals(province)) {
//                                            if ("XX".equals(country) || "??????IP".equals(country)) {
//                                                // logger.info("??????ip????????????????????????");
//                                            } else {
//                                                MemBaseinfo memBaseinfo = new MemBaseinfo();
//                                                memBaseinfo.setAccno(accno);
//                                                memBaseinfo.setTag(country);
//                                                memBaseinfoService.updateAddress(m.getMemid(), memBaseinfo);
//                                            }
//                                        } else {
//                                            MemBaseinfo memBaseinfo = new MemBaseinfo();
//                                            memBaseinfo.setAccno(accno);
//                                            memBaseinfo.setTag(province);
//                                            memBaseinfoService.updateAddress(m.getMemid(), memBaseinfo);
//                                        }
//                                    } else {
//                                        MemBaseinfo memBaseinfo = new MemBaseinfo();
//                                        memBaseinfo.setAccno(accno);
//                                        memBaseinfo.setTag(city);
//                                        memBaseinfoService.updateAddress(m.getMemid(), memBaseinfo);
//                                    }
//                                }
//                                logger.info("IP??????????????????: " + (System.currentTimeMillis() - start));
//                            } catch (Exception e) {
//                                logger.info(e.getMessage());
//                            }
//                        }
//                    }).start();
//                } catch (Exception e) {
//                    logger.info(e.getMessage());
//                }
//            }
//        }
//    }

    @Override
    public List<DomainList> getLink(LoginUser user) throws BusinessException {
        try {
            List<DomainList> rlist = new ArrayList<>();
            Map<String, String> map = HttpUtils.ipParse(BaseUtil.getIpAddress(request));
            if (!map.isEmpty() && !"XX".equals(map.get("country"))) {
                String region = map.get("country");
                // ????????????????????????
                if (StringUtils.isNotEmpty(region)) {
                    MemBaseinfo mb = new MemBaseinfo();
                    mb.setMemid(user.getMemid());
                    mb.setLogincountry(region);
                    this.memBaseinfoService.updateByPrimaryKeySelective(mb);
                }
                // ????????????link??????
                SysCdn req = new SysCdn();
                // ??????????????? ??????????????? 0?????????app?????? 1??????????????????????????? 2?????????web?????? 3????????????????????? 4?????????web?????? 5???????????????????????? 6???awsS3???????????? 7???awsS3???????????? 8???h5?????? 9 h5?????? 10.h5??????
                req.setDomainkind(0);
                req.setRegion(region);
                rlist.add(getDomainList(req, new PageBounds(1, 3)));
                req = new SysCdn();
                req.setDomainkind(8);
                req.setRegion(region);
                rlist.add(getDomainList(req, new PageBounds(1, 3)));
                req = new SysCdn();
                req.setDomainkind(10);
                req.setRegion(region);
                rlist.add(getDomainList(req, new PageBounds(1, 3)));
            } else {
                logger.info(user.getAccno() + "?????????????????????????????????");
                // ????????????link??????
                SysCdn req = new SysCdn();
                req.setDomainkind(0);
                req.setRegion("");
                rlist.add(getDomainList(req, new PageBounds(1, 3)));
                req = new SysCdn();
                req.setDomainkind(8);
                req.setRegion("");
                rlist.add(getDomainList(req, new PageBounds(1, 3)));
                req = new SysCdn();
                req.setDomainkind(10);
                req.setRegion("");
                rlist.add(getDomainList(req, new PageBounds(1, 3)));
            }
            return rlist;
        } catch (Exception e) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21002.getCode(), e.getMessage());
        }
    }

    /**
     * ??????????????????????????????
     *
     * @param req
     * @return
     */
    public DomainList getDomainList(SysCdn req, PageBounds page) {
        List<String> urls = new ArrayList<>();
        Page<SysCdn> list = this.sysCndService.getList(req, page.toRowBounds());
        if (list == null || list.size() == 0) {
            req.setRegion("");
            list = this.sysCndService.getList(req, page.toRowBounds());
        }
        for (SysCdn sc : list) {
            urls.add(sc.getDomain());
        }
        return new DomainList(req.getDomainkind(), urls);
    }

//    @Override
//    public String verificationInvitecode(UsersRequest req) {
//        if (StringUtils.isEmpty(req.getInvitecode())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_21002.getCode(), "???????????????");
//        }
//        // ????????? ????????????????????????
//        // if (StringUtils.isNotEmpty(req.getInvitecode())) {
//        MemBaseinfo inviteUser = memBaseinfoService.getUserByInvitecode(req.getInvitecode());
//        if (inviteUser == null || inviteUser.getIsDelete()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "?????????????????????????????????");
//        }
//        // }
//        return Constants.SUCCESS_MSG;
//    }

    @Override
    public String getSysParam(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10001.getCode(), "????????????");
        }
        SysParameter sysParameter = sysParamService.getByCode(code);
        if (sysParameter != null && !sysParameter.getIsDelete() && sysParameter.getStatus() == 0) {
            return sysParameter.getSysparamvalue();
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_10001.getCode(), "?????????/?????????");
        }
    }

}
