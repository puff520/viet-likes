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
//     * @author 阿布
//     * <p>
//     * 短信发送
//     */
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public synchronized Integer sendShortMsg(UsersRequest req, HttpServletRequest request) {
//        if (StringUtils.isEmpty(req.getTel()))
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "手机号为空");
//
//        if (null == req.getSendtype())
//            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "发送类型为空");
//
//        SysParameter issend = this.sysParamService.getByCode(SysParameterEnum.SMS_ONOFF.name());
//        if (issend == null || StringUtils.isEmpty(issend.getSysparamvalue()))
//            throw new BusinessException(StatusCode.LIVE_ERROR_201.getCode(), "参数异常，请稍后重试");
//        if (!"0".equals(issend.getSysparamvalue()))
//            throw new BusinessException(StatusCode.LIVE_ERROR_202.getCode(), "短信通道未开启，请联系系统管理员");
//
//        SysParameter validate = this.sysParamService.getByCode(SysParameterEnum.SMS_VALIDATE.name());
//        if (validate == null || StringUtils.isEmpty(validate.getSysparamvalue()))
//            throw new BusinessException(StatusCode.LIVE_ERROR_203.getCode(), "参数异常，请稍后重试");
//
//        SysParameter bs = this.sysParamService.getByCode(SysParameterEnum.SMS_SENDWAIT.name());
//        if (bs == null || StringUtils.isEmpty(bs.getSysparamvalue()))
//            throw new BusinessException(StatusCode.LIVE_ERROR_204.getCode(), "参数异常，请稍后重试");
//
//        MemLogin ml = memLoginService.findByMobile(req.getTel());
//        // 1短信登陆 ;2找回密码 ;3注册； 4.修改密码
//        if (req.getSendtype() == 3) {
//            if (ml != null)
//                throw new BusinessException(StatusCode.LIVE_ERROR_205.getCode(), "该手机号已存在");
//        } else {
//            if (ml == null)
//                throw new BusinessException(StatusCode.LIVE_ERROR_206.getCode(), "该手机号不存在");
//            if (ml.getAccstatus() != Constants.ACCOUNT_ONE)
//                throw new BusinessException(StatusCode.LIVE_ERROR_207.getCode(), "账号已禁用");
//        }
//        String smsCode = CommonFunction.getFourRandomSmsCode();
//        SysShortmsg sysShortmsg = new SysShortmsg();
//        sysShortmsg.setMobileno(req.getTel());
//        sysShortmsg.setMsgtype(req.getSendtype());
//        sysShortmsg.setMasdate(new Date());
//        sysShortmsg.setMsgcode(smsCode);
//        sysShortmsg.setIpaddress(BaseUtil.getIpAddress(request));
//        sysShortmsg.setMasbody("验证码：" + smsCode + "，验证码有效期为" + Integer.parseInt(validate.getSysparamvalue()) + "分钟，请勿向他人泄漏。");
//        sysShortmsg.setMasstatus("0");
//        if (this.sysShortmsgService.insertByParam(sysShortmsg) <= 0) {
//            int wait = this.sysShortmsgService.selectWaittime(sysShortmsg);
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "短信发送限制，" + wait + "秒后再试");
//        }
//        //  int status = smsService.sendSMS(sysShortmsg.getMobileno(), sysShortmsg.getMasbody(), "");
//        //发送短信验证码组件
//        //SmsSendResult smsSendResult = SmsSendResult.getResult(true); //SmsSendUtil.send(sysShortmsg.getMobileno(), smsCode, sysShortmsg.getMsgtype());
//        SmsSendResult smsSendResult = SmsSendUtil.send(req.getTel(), smsCode, sysShortmsg.getMsgtype());
//        if (!smsSendResult.isSuccess()) {
//            logger.error("短信发送失败，服务商返回状态 {}" + sysShortmsg.getMobileno());
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
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "手机号为空");
//        }
//        if (null == req.getSendtype()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "发送类型为空");
//        }
//
//        String ipaddress = BaseUtil.getIpAddress(request);
//        logger.info("ip: {} ", ipaddress);
//        // 验证这个ip设置 发送的短信是否超过限制
//        SysParameter shortmsg_limit_bs = this.sysParamService.getByCode(SysParameterEnum.SHORTMSG_LIMIT.name());
//        if (shortmsg_limit_bs == null || StringUtils.isEmpty(shortmsg_limit_bs.getSysparamvalue())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "系统参数(shortmsg_limit)异常");
//        }
//
//        int shortmsg_limit = Integer.parseInt(shortmsg_limit_bs.getSysparamvalue());
//        SysShortmsg sysShortmsgParam = new SysShortmsg();
//        sysShortmsgParam.setIpaddress(ipaddress);
//        Integer ipcount = sysShortmsgService.getLimit(sysShortmsgParam);
//        if (ipcount >= shortmsg_limit) {
//            logger.info("ip: {} ,短信条数 {}", ipaddress, ipcount);
//            throw new BusinessException(StatusCode.LIVE_ERROR_130.getCode(), "今天短信条数超过限制");
//        }
//
//        // 严重这个手机号是否超过限制
//        SysShortmsg sysShortmsgParam2 = new SysShortmsg();
//        sysShortmsgParam2.setMobileno(req.getTel());
//        Integer ipcount2 = sysShortmsgService.getLimit(sysShortmsgParam2);
//        if (ipcount2 >= shortmsg_limit) {
//            logger.info("mobile: {} ,短信条数 {}", req.getTel(), ipcount);
//            throw new BusinessException(StatusCode.LIVE_ERROR_130.getCode(), "今天短信条数超过限制");
//        }
//
//        // 验证这个手机号是否已经存在 如果是发送注册
//        if (req.getSendtype() == 3) {
//            // 手机号 不存在 才 3注册；
//            MemLogin ml = memLoginService.findByMobile(req.getTel());
//            if (ml != null) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "该手机号已存在");
//            }
//        } else {
//            // 手机号 存在才可以 1短信登陆 ;2找回密码 ;4.修改密码
//            MemLogin ml = memLoginService.findByMobile(req.getTel());
//            if (ml == null) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_120.getCode(), "该手机号不存在");
//            }
//            if (ml.getAccstatus() != Constants.ACCOUNT_ONE) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "账号已禁用");
//            }
//        }
//        // 验证这个手机号 在 倒计时 smsCountdownYanzheng 秒 内
//        // SELECT ((UNIX_TIMESTAMP(now()) - UNIX_TIMESTAMP(ctime)) - 60) dif_second from
//        // `user`;
//        SysParameter bs = this.sysParamService.getByCode(SysParameterEnum.SMS_SENDWAIT.name());
//        if (bs == null || StringUtils.isEmpty(bs.getSysparamvalue())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "系统参数(sms_sendwait)异常");
//        }
//        int sendwait = Integer.parseInt(bs.getSysparamvalue());
//        req.setCountDown(sendwait);
//        Integer countInteger = sysShortmsgService.getCountDown(req);
//        logger.info("countInteger: {}", countInteger);
//        if (countInteger == null || countInteger > 0) {
//            // 过了一分钟 才能发
//            // 可以发送短信
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
//            // sms_message.append("[乐跑]");
//            // 1短信登陆 ;2找回密码 ;3注册；4.修改密码
//            sms_message.append("验证码：");
//            sms_message.append(smsCode);
//
//            SysParameter bsYanzheng = this.sysParamService.getByCode(SysParameterEnum.SMS_VALIDATE.name());
//            if (bsYanzheng == null || StringUtils.isEmpty(bsYanzheng.getSysparamvalue()))
//                throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "系统参数(sms_validate)异常");
//            Integer smsCountdownYanzheng = Integer.parseInt(bsYanzheng.getSysparamvalue());
//            sms_message.append("，验证码有效期为" + smsCountdownYanzheng + "分钟，请勿向他人泄漏。[BB直播]");
//            sysShortmsg.setMasbody(sms_message.toString());
//            int inputLine = 1;
//            // 根据地区 调短信发送接口 默认 中国大陆
//            if (StringUtils.isEmpty(req.getAreacode()) || Constants.AREACODE_CHINA_MAINLAND_86.equals(req.getAreacode())
//                    || Constants.AREACODE_CHINA_MAINLAND_086.equals(req.getAreacode())) {
//
//                SysParameter bsSms = this.sysParamService.getByCode(SysParameterEnum.SMS_ONOFF.name());
//                if (bsSms == null || com.likes.common.util.StringUtils.isEmpty(bsSms.getSysparamvalue()) || Integer.parseInt(bsSms.getSysparamvalue()) != 1) {
//                    throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "系统参数(sms_onoff)异常");
//                }
//                //发送短信验证码组件
//                //SmsSendResult smsSendResult = SmsSendResult.getResult(true); //SmsSendUtil.send(sysShortmsg.getMobileno(), sysShortmsg.getMsgcode(), sysShortmsg.getMsgtype());
//                SmsSendResult smsSendResult = SmsSendUtil.send(req.getTel(), smsCode, sysShortmsg.getMsgtype());
//                if (smsSendResult.isSuccess()) {
//                    // 发送成功
//                    // logger.info("{}发送短信验证码{} ",req.getTel(),smsCode);
//                    logger.info("{}发送短信成功 ", req.getTel());
//                    sysShortmsg.setMasstatus(Constants.STATUS_SUCCESS);
//                    sysShortmsgService.insertSelectiveSysShortmsg(sysShortmsg);
//                } else {
//                    sysShortmsg.setMasstatus(Constants.STATUS_FAILE);
//                    sysShortmsgService.insertSelective(sysShortmsg);
//                    throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "短信发送失败");
//                }
//            } else {
//                throw new BusinessException(StatusCode.LIVE_ERROR_115.getCode(), "无效地区");
//            }
//
//            return sendwait;
//        } else {
//            // 明确提示
//            String s = String.valueOf(countInteger).replaceAll("-", "");
//            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), s + "秒后才能发送");
//        }
//
//    }
//
//    @Override
//    public Integer verificationSmsCode(UsersRequest req) {
//        if (StringUtils.isEmpty(req.getTel())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "手机号为空");
//        }
//        if (null == req.getSendtype()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "发送类型为空");
//        }
//        if (StringUtils.isEmpty(req.getSmscode())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "短信验证码为空");
//        }
//
//        SysParameter bsYanzheng = this.sysParamService.getByCode(SysParameterEnum.SMS_VALIDATE.name());
//        if (bsYanzheng == null || StringUtils.isEmpty(bsYanzheng.getSysparamvalue()))
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "系统参数(sms_validate)异常");
//        Integer smsCountdownYanzheng = Integer.parseInt(bsYanzheng.getSysparamvalue());
//
//        logger.info("{}分钟间隔", smsCountdownYanzheng);
//        // 查询当前发送类型的手机号验证码
//        req.setCountDown(smsCountdownYanzheng * 60);
//        // 验证是否在5分钟有效期内
//        logger.info("参数：{}", JSON.toJSONString(req));
//        Integer shortmsgCountDown = sysShortmsgService.getMsgByParamCountDown(req);
//        if (null == shortmsgCountDown) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "验证码错误");
//        }
//
//        if (shortmsgCountDown > 0) {
//            // 代表过了 5分钟
//            logger.info("已经超时： {}秒", shortmsgCountDown);
//            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "验证失败");
//        }
//
//        // 修改状态
//        SysShortmsg shortmsg = sysShortmsgService.getMsgByParam(req);
//        SysShortmsg updateSysShortmsg = new SysShortmsg();
//        updateSysShortmsg.setShortmsgid(shortmsg.getShortmsgid());
//        updateSysShortmsg.setMasstatus(Constants.STATUS_USED);
//        sysShortmsgService.updateByPrimaryKeySelective(updateSysShortmsg);
//
//        // 邀请码 严重这个人在不在
//        if (StringUtils.isNotEmpty(req.getInvitecode())) {
//            MemBaseinfo inviteUser = memBaseinfoService.getUserByInvitecode(req.getInvitecode());
//            if (inviteUser == null || inviteUser.getIsDelete()) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "邀请码对应的用户不存在");
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
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "手机号为空");
//        }
//        if (StringUtils.isEmpty(req.getPassword())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "密码为空");
//        }
//        if (StringUtils.isEmpty(req.getSmscode())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "短信验证码为空");
//        }
//        if (StringUtils.isEmpty(req.getInvitecode())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1104.getCode(), "邀请码为空");
//        }
//
//        // 检查 密码 是否 过于简单
//        List<String> jPwd = CommonFunction.jiandanPwd();
//        if (jPwd.contains(req.getPassword())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "您设置的密码过于简单");
//        }
//
//        MemLogin ml = memLoginService.findByMobileRegister(req.getTel());
//        if (ml != null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "该手机号已存在");
//        }
//
//        // 先验证 验证码 是否 是 已经使用的
//        req.setMasstatus(Constants.STATUS_USED);
//        req.setSendtype(3);
//        logger.info("参数：{}", JSON.toJSONString(req));
//        SysShortmsg shortmsg = sysShortmsgService.getMsgByParam(req);
//        if (shortmsg != null) {
//            // 插入用户
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
//            // 设置注册用户的邀请码
//            /*boolean recomcodeFlag = false;
//            String recomcode = null;
//            while (!recomcodeFlag) {
//                recomcode = CommonFunction.inviteCode();
//                MemBaseinfo recomcodeUser = memBaseinfoService.getUserByInvitecodeAll(recomcode);//TODO 有严重性能问题，需改进
//                if (recomcodeUser == null) {
//                    recomcodeFlag = true;
//                }
//            }*/
//            newUser.setRecomcode(uniqueCodeService.getUniqueCode(UniqueCodeEnum.USER_INVITE));//TODO 优化后的邀请码获取方式
//
//            MemBaseinfo inviteUser = null;
//            // Boolean inviteUserFlag = false;
//            if (StringUtils.isNotEmpty(req.getInvitecode())) {
//                // 根据邀请码 获取 对应的人
//                inviteUser = memBaseinfoService.getUserByInvitecode(req.getInvitecode());
//                if (inviteUser == null || inviteUser.getIsDelete()) {
//                    throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "邀请码对应的用户不存在");
//                }
//                newUser.setMemorgin("recommend");
//                // 插入推荐关系
//                MemRelationship memRelationship = new MemRelationship();
//                memRelationship.setRefaccno(inviteUser.getAccno());
//                memRelationship.setAccno(newUser.getAccno());
//                memRelationship.setCreateUser(newUser.getAccno());
//                memRelationship.setUpdateUser(newUser.getAccno());
//                // memRelationshipMapper.insertSelective(memRelationship);
//                int i = memRelationshipService.insertSelectiveMemRelationship(memRelationship);
//                if (i > 0) {
//                    // inviteUserFlag = true;
//                    // 关系成功后 ，主动关注 这个 邀请人
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
//                    throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "已经存在要求关系");
//                }
//
//            } else {
//                newUser.setMemorgin("regist");
//            }
//
//            // 用户基本信息
//            memBaseinfoService.insertSelective(newUser);
//
//            // 登陆用户
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
//                throw new BusinessException(StatusCode.LIVE_ERROR_1119.getCode(), "该手机号已存在");
//            }
//            // 设置返回
//            // 直接获取最新版的APP版本的下载链接
//            // List<AppinfoDO> appdownurl = sysAppInfoMapper.getMaxXin();
//            Map<String, Object> map = new HashMap<String, Object>();
//            // map.put("appdownurl", appdownurl);
//            memLevelService.initMemLevel(newUser.getAccno());
//            return map;
//        } else {
//            // 短信中 没有 该验证码的 验证信息
//            throw new BusinessException(StatusCode.LIVE_ERROR_107.getCode(), "无效验证码");
//        }
//    }
//
//    @Override
//    public String updatePassword(UsersRequest req) {
//        if (StringUtils.isEmpty(req.getTel())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "手机号为空");
//        }
//        if (StringUtils.isEmpty(req.getPassword())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "密码为空");
//        }
//        if (StringUtils.isEmpty(req.getSmscode())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "短信验证码为空");
//        }
//
//        // 检查 密码 是否 过于简单
//        List<String> jPwd = CommonFunction.jiandanPwd();
//        if (jPwd.contains(req.getPassword())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "您设置的密码过于简单");
//        }
//
//        // 先验证 验证码 是否 是 已经使用的
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
//                    throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "账号已禁用");
//                }
//                memLoginParam.setPasswordmd5(req.getPassword());
//                memLoginParam.setLoginid(memLogin.getLoginid());
//                memLoginMapper.updateByPrimaryKeySelective(memLoginParam);
//            } else {
//                throw new BusinessException(StatusCode.LIVE_ERROR_111.getCode(), "账号不存在");
//            }
//
//        } else {
//            // 短信中 没有 该验证码的 验证信息
//            throw new BusinessException(StatusCode.LIVE_ERROR_110.getCode(), "无效验证码");
//        }
//
//        return Constants.SUCCESS_MSG;
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public LoginUser login(UsersRequest req, HttpServletRequest request) {
//        if (StringUtils.isEmpty(req.getTel())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "手机号为空");
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
//                    throw new BusinessException(StatusCode.LIVE_ERROR_130.getCode(), "您的登录次数已达到最大限制，请12小时后再尝试。" + "您也可以使用短信登录或找回密码");
//                }
//                Long seconds = RedisBusinessUtil.getExpire(LOGINNUMMD5);
//                if (seconds > 0) {
//                    redisTemplate.opsForValue().set(LOGINNUMMD5, loginnum + 1, seconds, TimeUnit.SECONDS);
//                }
//            }
//
//            // 账号密码登陆
//            // 获取用户是否存在
//
//            // 先验证账号
//            MemLogin loginParam = new MemLogin();
//            loginParam.setAcclogin(req.getTel());
//            memLogin = memLoginService.getMemLoginByParam(loginParam);
//            if (memLogin == null) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_1130.getCode(), "账号不存在");
//            }
//            if (!Constants.ACCOUNT_ONE.equals(memLogin.getAccstatus())) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "账号已禁用");
//            }
//
//            MemLogin memLoginParam = new MemLogin();
//            memLoginParam.setAcclogin(req.getTel());
//            // memLoginParam.setAccstatus(Constants.ACCOUNT_ONE);
//            memLoginParam.setPasswordmd5(req.getPassword());
//            memLogin = memLoginService.getMemLoginByParam(memLoginParam);
//            if (memLogin == null) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_112.getCode(), "账号/密码错误");
//            }
//
//        } else if (StringUtils.isEmpty(req.getSmscode())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_131.getCode(), "非法访问");
//        }
//
//        // 短信登陆
//        SysParameter bsYanzheng = this.sysParamService.getByCode(SysParameterEnum.SMS_VALIDATE.name());
//        if (bsYanzheng == null || StringUtils.isEmpty(bsYanzheng.getSysparamvalue())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "系统参数(sms_validate)异常");
//        }
//
//        Integer smsCountdownYanzheng = Integer.parseInt(bsYanzheng.getSysparamvalue());
//        logger.info("{}分钟间隔", smsCountdownYanzheng);
//
//        // 查询当前发送类型的手机号验证码
//        req.setCountDown(smsCountdownYanzheng * 60);
//        // 验证是否在5分钟有效期内
//        logger.info("参数：{}", JSON.toJSONString(req));
//        // req.setMasstatus(Constants.STATUS_USED);
//        req.setSendtype(1);
//        Integer shortmsgCountDown = sysShortmsgService.getMsgByParamCountDown(req);
//        if (null == shortmsgCountDown) {
//            // 验证码错误 或者 该验证码 已经被使用了
//            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "验证码错误");
//        }
//
//        if (shortmsgCountDown > 0) {
//            // 代表过了 5分钟
//            logger.info("已经超时： {}秒", shortmsgCountDown);
//            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "验证失败");
//        }
//
//        // 先验证 验证码 是否 有效
//        // req.setMasstatus(Constants.STATUS_USED);
//        req.setSendtype(1);
//        SysShortmsg shortmsg = sysShortmsgService.getMsgByParam(req);
//        if (shortmsg == null || !shortmsg.getMasstatus().equals(Constants.STATUS_SUCCESS)) {
//            // 短信中 已经使用 该验证码的 验证信息
//            throw new BusinessException(StatusCode.LIVE_ERROR_107.getCode(), "无效验证码");
//        }
//
//        MemLogin memLoginParam = new MemLogin();
//        memLoginParam.setAcclogin(req.getTel());
//        // memLoginParam.setAccstatus(Constants.ACCOUNT_ONE);
//        memLogin = memLoginService.getMemLoginByParam(memLoginParam);
//
//        if (memLogin == null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_112.getCode(), "账号不存在");
//        }
//
//        if (!Constants.ACCOUNT_ONE.equals(memLogin.getAccstatus())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "账号已禁用");
//        }
//
//        // 修改状态
//        SysShortmsg updateSysShortmsg = new SysShortmsg();
//        updateSysShortmsg.setShortmsgid(shortmsg.getShortmsgid());
//        updateSysShortmsg.setMasstatus(Constants.STATUS_USED);
//        sysShortmsgService.updateByPrimaryKeySelective(updateSysShortmsg);
//
//        // 表示登录成功，清楚掉 LOGINNUM
//        if (redisTemplate.hasKey(LOGINNUMMD5)) {
//            redisTemplate.delete(LOGINNUMMD5);
//        }
//
//        // 存在
//        // 获取详细信息
//        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(memLogin.getAccno());
//        if (memBaseinfo == null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "该账号已被删除");
//        }
//
//        // 设置loginUserAPP
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
//        // 创建sessionId
//        String sessionid = RedisBusinessUtil.createSessionID(userSessionKey, loginUserAPP, sysParamService);
//        if (sessionid.isEmpty()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_112.getCode(), "请重新登录");
//        }
//        loginUserAPP.setAcctoken(sessionid);
//
//        // 其他
//        // 更新用户登陆次数
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
//        // 登陆日志
//        sysInfologService.insertAppLog(memBaseinfo.getAccno(), "APP用户登录", "[" + memBaseinfo.getMobileno() + "]登录", req.getLongitude(), req.getLatitude(), "login");
//
//        // 不返回 给前端 why no why?
//        // loginUserAPP.setAccno(null);
//        // loginUserAPP.setMemname(null);
//        // 缓存图片
//        List<String> cacheimages = new ArrayList<>();
//        // 频道图片 + 礼物 图片 + 头像
//        if (StringUtils.isNotEmpty(memBaseinfo.getHeadimg())) {
//            cacheimages.add(memBaseinfo.getHeadimg());
//        }
//        cacheimages.addAll(commonService.getCacheimages());
//        loginUserAPP.setCacheimages(cacheimages);
//
//        // 查询改用户是否填写的邀请码 ，如果填写邀请码，检查上级是否意见返了金币 ，若没有，加金币
//        // 二期打开
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
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "系统参数(recommend_user)异常");
//        double goldnum = Double.parseDouble(goldnumParameter.getSysparamvalue());
//
//        // 更新金币参数
//        UsersRequest usersRequest = new UsersRequest();
//        BigDecimal tradeOffAmount = getTradeOffAmount(new BigDecimal(goldnum));
//        usersRequest.setGoldnum(tradeOffAmount.doubleValue());
//        usersRequest.setAccno(userGoldDO.getRefaccno());
//        // 插入金币变动数据
//        MemGoldchange memGoldchange = new MemGoldchange();
//        memGoldchange.setRefid(userGoldDO.getRelaid());
//        memGoldchange.setAccno(userGoldDO.getRefaccno());
//        memGoldchange.setChangetype(GoldchangeEnum.INVITE_USERS.getValue());
//
//        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(userGoldDO.getRefaccno());
//        if (memBaseinfo == null || memBaseinfo.getIsDelete()) {
//            logger.info(userGoldDO.getRefaccno() + "已删除");
//            // throw new BusinessException(StatusCode.LIVE_ERROR_204.getCode(), "邀请人已删除");
//        } else {
//            memGoldchange.setGoldnum(getTradeOffAmount(memBaseinfo.getGoldnum()));
//            memGoldchange.setQuantity(tradeOffAmount);
//            BigDecimal recgoldnum = memGoldchange.getGoldnum().add(memGoldchange.getQuantity());
//            memGoldchange.setRecgoldnum(getTradeOffAmount(recgoldnum));
//            memGoldchange.setAmount(tradeOffAmount);
//            memGoldchange.setCreateUser(opearteAccno);
//            memGoldchange.setUpdateUser(opearteAccno);
//            memGoldchange.setOpnote("邀请新用户");
//
//            // 再insert处枷锁
//            int i = memGoldchangeService.insertSelectiveMemGoldchange(memGoldchange);
//            if (i > 0) {
//                memBaseinfoService.updateAddGold(usersRequest);
//                logger.info("邀请新用户加金币成功");
//            } else {
//                logger.info("已经加过金币");
//                // throw new BusinessException(2104, "已经加过金币");
//            }
//        }
//    }

    @Override
    public Map<String, Object> appdownurl() {
        // 设置返回
        // 直接获取最新版的APP版本的下载链接
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

            // 重组更新说明 多条信息 以#号隔开
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
//            // 是否存在
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
//                                    if ("XX".equals(city) || "内网IP".equals(city)) {
//                                        if ("XX".equals(province) || "内网IP".equals(province)) {
//                                            if ("XX".equals(country) || "内网IP".equals(country)) {
//                                                // logger.info("根据ip获取城市地址：无");
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
//                                logger.info("IP地址解析耗时: " + (System.currentTimeMillis() - start));
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
                // 更新用户所在区域
                if (StringUtils.isNotEmpty(region)) {
                    MemBaseinfo mb = new MemBaseinfo();
                    mb.setMemid(user.getMemid());
                    mb.setLogincountry(region);
                    this.memBaseinfoService.updateByPrimaryKeySelective(mb);
                }
                // 获取最近link地址
                SysCdn req = new SysCdn();
                // 域名类型： 域名类型： 0：乐跑app域名 1：乐跑管理后台域名 2：乐跑web域名 3：乐跑下载域名 4：幽兰web域名 5幽兰管理后台域名 6：awsS3视频资源 7：awsS3图片资源 8：h5彩票 9 h5注册 10.h5支付
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
                logger.info(user.getAccno() + "：获取用户区域地址失败");
                // 获取最近link地址
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
     * 获取请求参数相应域名
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
//            throw new BusinessException(StatusCode.LIVE_ERROR_21002.getCode(), "邀请码为空");
//        }
//        // 邀请码 严重这个人在不在
//        // if (StringUtils.isNotEmpty(req.getInvitecode())) {
//        MemBaseinfo inviteUser = memBaseinfoService.getUserByInvitecode(req.getInvitecode());
//        if (inviteUser == null || inviteUser.getIsDelete()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "邀请码对应的用户不存在");
//        }
//        // }
//        return Constants.SUCCESS_MSG;
//    }

    @Override
    public String getSysParam(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10001.getCode(), "代码为空");
        }
        SysParameter sysParameter = sysParamService.getByCode(code);
        if (sysParameter != null && !sysParameter.getIsDelete() && sysParameter.getStatus() == 0) {
            return sysParameter.getSysparamvalue();
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_10001.getCode(), "不存在/已禁用");
        }
    }

}
