package com.likes.modules.admin.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.PageHelper;
import com.likes.common.constant.Constants;
import com.likes.common.constant.ModuleConstant;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.LoginUserTypeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.member.MemBaseinfoDO;
import com.likes.common.model.dto.member.MemberBankDTO;
import com.likes.common.model.dto.member.UsersBdDO;
import com.likes.common.model.dto.member.UsersDO;
import com.likes.common.model.request.*;
import com.likes.common.model.vo.member.MemberCreditVO;
import com.likes.common.model.vo.member.UserDetailForUpdateVO;
import com.likes.common.model.vo.member.UserVO;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.*;
import com.likes.common.mybatis.mapperbean.MemberInfoBeanMapper;
import com.likes.common.mybatis.mapperext.member.MemBankaccountMapperExt;
import com.likes.common.mybatis.mapperext.member.MemLevelMapperExt;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.member.BdUserService;
import com.likes.common.service.member.MemBankaccountService;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemCertificationService;
import com.likes.common.service.member.MemLevelConfigService;
import com.likes.common.service.member.MemLevelService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.service.member.MemRelationshipService;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.service.sys.InfSysremindinfoService;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.CommonFunction;
import com.likes.common.util.DateUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.common.service.CommonService;
import com.likes.modules.admin.user.service.UserService;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private CommonService commonService;
    @Resource
    private MemLoginMapper memLoginMapper;
    @Resource
    private MemLoginService memLoginService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemBankaccountMapperExt memBankaccountMapperExt;
    @Resource
    private MemLevelService memLevelService;
    @Resource
    private MemRelationshipService memRelationshipService;
    @Resource
    private MemBankaccountService memBankaccountService;
    @Resource
    private MemCertificationService memCertificationService;
    @Resource
    private MemberInfoBeanMapper memberInfoBeanMapper;
    @Resource
    private BdUserService bdUserService;
    @Resource
    private MemBankaccountMapper memBankaccountMapper;
    @Autowired
    private MemGoldchangeService memGoldchangeService;
    @Resource
    private SysBusParamService sysBusParamService;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private MemberCreditChangeMapper memberCreditChangeMapper;
    @Resource
    private MemberCreditDetailMapper memberCreditDetailMapper;
    @Resource
    private MemberCreditMapper memberCreditMapper;
    @Resource
    private MemLevelConfigService memLevelConfigService;
    @Resource
    private MemLevelMapperExt memLevelMapperExt;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @DS("slave")
    public PageResult userList(UserRequest req, Integer pageNo, Integer pageSize) {
        if (null == req.getLogintype()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "类型为空");
        }
        PageBounds page = new PageBounds();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        //TODO 以前逻辑判断，目前调试感觉有问题，暂时注释掉，后面根据业务删除或还原
        // if (LoginUserTypeEnum.FAMILYHEAD.getCode() != req.getLogintype() ) {
        if (LoginUserTypeEnum.FAMILYHEAD.getCode().equals(req.getLogintype()) || LoginUserTypeEnum.BACKADMIN.getCode().equals(req.getLogintype())) {
            //家族长
            // 后台用户
            // APP用户
            Page<UsersBdDO> userList = memLoginService.userBdList(req, page.toRowBounds());
            if (CollectionUtil.isNotEmpty(userList)) {
                String key = RedisKeys.LIVE_SYSTEM_USER + JSONObject.toJSONString(req) + JSONObject.toJSONString(page.toRowBounds()) + "count";
                int count = (int) redisTemplate.opsForValue().get(key);
                return PageResult.getPageResult(count, page, userList);
            }
        } else {
            // APP用户
            Page<UsersDO> userList = null;
            if (req.getLogintype().equals(LoginUserTypeEnum.ORDINARY.getCode()) || req.getLogintype().equals(LoginUserTypeEnum.THIRD.getCode())) {
                //普通会员 第三方登录
                if (ObjectUtil.isNotNull(req.getMemlevel())) {
                    MemLevelConfig levelConfig = memLevelConfigService.getMemLevelConfigForConfigId(req.getMemlevel());
                    req.setMemlevel(levelConfig.getLevelSeq().longValue());
                }
                Page<UserVO> appUserList = memLoginService.userList(req, page.toRowBounds());
                setRefacclogin(appUserList);
                return PageResult.getPageResult((int) appUserList.getTotal(), page, appUserList);
            }
            return PageResult.getPageResult((int) userList.getTotal(), page, userList);
        }
        return null;
    }

    @Override
    public PageResult memBankList(MemberBankRequest req, PageBounds page) {
        if (StringUtils.isNotBlank(req.getBeginTime())) {
            req.setBeginTime(req.getBeginTime() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndTime())) {
            req.setEndTime(req.getEndTime() + " 23:59:59");
        }
        Page<MemberBankDTO> pageData = memBankaccountMapperExt.getMemberbankLst(req, page.toRowBounds());
        return PageResult.getPageResult(page, pageData);
    }

    @Override
    public PageResult getSystemUserList(UserRequest req, Integer pageNo, Integer pageSize) {
        PageBounds page = new PageBounds();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Page<UsersBdDO> userList = memLoginService.userBdList(req, page.toRowBounds());
        if (CollectionUtil.isNotEmpty(userList)) {
            String key = RedisKeys.LIVE_SYSTEM_USER + JSONObject.toJSONString(req) + JSONObject.toJSONString(page.toRowBounds()) + "count";
            int count = (int) redisTemplate.opsForValue().get(key);
            return PageResult.getPageResult(count, page, userList);
        }
        return null;
    }

    private void setMoneyAndTime(Page<UsersDO> userList) {
        if (!CollectionUtils.isEmpty(userList)) {
            Date date = new Date();
            for (UsersDO user : userList) {
                String accno = user.getAccno();
                List<Integer> typeList = new ArrayList<Integer>();
                typeList.add(2);
                String dateStr = DateUtils.formatDate(DateUtils.addDate("dd", -1, date), "yyyy-MM-dd");
                String startTime = dateStr + DateUtils.TIMEZERO_BLANK;
                String endTime = dateStr + DateUtils.TIMEOVER_BLANK_MINUTE;
                BigDecimal yesterdayGold = memGoldchangeService.tatolGoldchange(accno, null, typeList, startTime, endTime);
                if (null != yesterdayGold) {
                    user.setYesterdayGold(yesterdayGold.abs());
                } else {
                    user.setYesterdayGold(new BigDecimal("0"));
                }


                String token = RedisBusinessUtil.get(user.getAccno());
                if (null == token) {
                    user.setOnline("0");
                } else {
                    user.setOnline("1");
                }
            }
        }
    }

    /**
     * app会员管理，添加代理的账号,在线离线,默认等级
     */
    private void setRefacclogin(Page<UserVO> userList) {
        if (!CollectionUtils.isEmpty(userList)) {
            for (UserVO user : userList) {
                //代理用户id--走缓存
                MemRelationship memRelationship = memRelationshipService.findByAccno(user.getAccno());
                if (ObjectUtil.isNotNull(memRelationship)) {
                    MemLogin memLogin = memLoginService.selectByAccno(memRelationship.getHeadAccno());
                    if (ObjectUtil.isNotNull(memLogin)) {
                        user.setTopAgent(memLogin.getAcclogin());
                    }
                    MemLogin supRem = memLoginService.selectByAccno(memRelationship.getRefaccno());
                    if (ObjectUtil.isNotNull(supRem)) {
                        user.setRefUniqueId(supRem.getAcclogin());
                    }
                    MemLevel memLevel = memLevelMapperExt.selectByAccno(user.getAccno());
                    if (ObjectUtil.isNotNull(memLevel)) {
                        user.setMemlevel(memLevel.getMemlevel());
                    }
                }
                //是否在线
                String token = RedisBusinessUtil.get("Login_info:" + user.getAccno());
                if (null == token) {
                    user.setOnline("0");
                } else {
                    user.setOnline("1");
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer doAccstatusUser(UserRequest req) {
        if (StringUtils.isEmpty(req.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "accno为空");
        }
        MemLogin memLogin = memLoginService.selectByAccno(req.getAccno());
        if (memLogin != null) {
            SysInfolog sysInfolog = new SysInfolog();
            if (memLogin.getAccstatus().equals(Constants.ACCSTATUS_1)) {
                memLogin.setAccstatus(Constants.ACCSTATUS_9);
                sysInfolog.setOptcontent("禁用loginid=" + memLogin.getLoginid());
            } else {
                memLogin.setAccstatus(Constants.ACCSTATUS_1);
                sysInfolog.setOptcontent("启用loginid=" + memLogin.getLoginid());
            }
            memLoginMapper.updateByPrimaryKeySelective(memLogin);
            RedisBusinessUtil.clearUserLoginInfo(req.getAccno());
            RedisBusinessUtil.delUserAttention(req.getAccno());

            MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(memLogin.getAccno());
            if (memBaseinfo != null) {
                MemBaseinfo info = new MemBaseinfo();
                info.setUpdateUser(req.getOperataccno());
                info.setMemid(memBaseinfo.getMemid());
                memBaseinfoService.updateByPrimaryKeySelective(info);
            }

            // 日志
            sysInfolog.setAccno(req.getOperataccno());
            sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
            if (memLogin.getAccstatus().equals(Constants.ACCSTATUS_1)) {
                sysInfolog.setModelname("后台用户管理操作-开启用户");
            } else {
                sysInfolog.setModelname("后台用户管理操作-禁用用户");
            }

            sysInfolog.setOrginfo("doAccstatusUser");
            commonService.insertSelective(sysInfolog);

            return memLogin.getAccstatus();
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "用户不存在");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer doAccstatusSystemUser(UserRequest req) {
        if (StringUtils.isEmpty(req.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "accno为空");
        }
        BdUser bdUser = bdUserService.selectByAccno(req.getAccno());
        if (null != bdUser) {
            SysInfolog sysInfolog = new SysInfolog();
            if (bdUser.getAccstatus().equals(Constants.ACCSTATUS_1)) {
                bdUser.setAccstatus(Constants.ACCSTATUS_9);
                sysInfolog.setOptcontent("禁用loginid=" + bdUser.getBduserid());
            } else {
                bdUser.setAccstatus(Constants.ACCSTATUS_1);
                sysInfolog.setOptcontent("启用loginid=" + bdUser.getBduserid());
            }
            bdUser.setUpdateUser(req.getOperataccno());
            bdUserService.updateByPrimaryKeySelective(bdUser);
            RedisBusinessUtil.clearUserLoginInfo(req.getAccno());
            RedisBusinessUtil.delUserAttention(req.getAccno());

           /* MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(memLogin.getAccno());
            if (memBaseinfo != null) {
                MemBaseinfo info = new MemBaseinfo();
                info.setUpdateUser(req.getOperataccno());
                info.setMemid(memBaseinfo.getMemid());
                memBaseinfoService.updateByPrimaryKeySelective(info);
            }*/

            // 日志
            sysInfolog.setAccno(req.getOperataccno());
            sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
            if (bdUser.getAccstatus().equals(Constants.ACCSTATUS_1)) {
                sysInfolog.setModelname("后台用户管理操作-开启用户");
            } else {
                sysInfolog.setModelname("后台用户管理操作-禁用用户");
            }

            sysInfolog.setOrginfo("doAccstatusUser");
            commonService.insertSelective(sysInfolog);

            return bdUser.getAccstatus();
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "用户不存在");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String doDelUser(UserRequest req) {
        if (StringUtils.isEmpty(req.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "accno为空");
        }
        MemLogin memLogin = memLoginService.selectByAccno(req.getAccno());
        if (memLogin != null) {
            SysInfolog sysInfolog = new SysInfolog();
            memLogin.setAccstatus(Constants.ACCSTATUS_9);
            sysInfolog.setOptcontent("删除loginid=" + memLogin.getLoginid());
            memLoginMapper.updateByPrimaryKeySelective(memLogin);
            RedisBusinessUtil.clearUserLoginInfo(req.getAccno());

            MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(memLogin.getAccno());
            if (memBaseinfo != null) {
                memBaseinfo.setIsDelete(true);
                memBaseinfo.setUpdateUser(req.getOperataccno());
                memBaseinfoService.updateByPrimaryKeySelective(memBaseinfo);
            }
            BdUser bdUser = bdUserService.selectByAccno(memLogin.getAccno());
            if (bdUser != null) {
                bdUser.setIsDelete(true);
                bdUser.setUpdateUser(req.getOperataccno());
                bdUserService.updateByPrimaryKeySelective(bdUser);
            }

            // 日志
            sysInfolog.setAccno(req.getOperataccno());
            sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
            sysInfolog.setModelname("后台用户管理操作-删除账号");
            sysInfolog.setOrginfo("doDelUser");
            commonService.insertSelective(sysInfolog);
            return Constants.SUCCESS_MSG;
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "用户不存在");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String doDelSystemUser(UserRequest req) {
        if (StringUtils.isEmpty(req.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "accno为空");
        }
        BdUser bdUser = bdUserService.selectByAccno(req.getAccno());
        if (null != bdUser) {
            SysInfolog sysInfolog = new SysInfolog();
            sysInfolog.setOptcontent("删除loginid=" + bdUser.getBduserid());
            bdUser.setIsDelete(true);
            bdUser.setUpdateUser(req.getOperataccno());
            bdUser.setAccstatus(Constants.ACCSTATUS_9);
            bdUserService.updateByPrimaryKeySelective(bdUser);
            //删除登录缓存信息
            RedisBusinessUtil.clearUserLoginInfo(req.getAccno());

            // 日志
            sysInfolog.setAccno(req.getOperataccno());
            sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
            sysInfolog.setModelname("后台用户管理操作-删除账号");
            sysInfolog.setOrginfo("doDelUser");
            commonService.insertSelective(sysInfolog);
            return Constants.SUCCESS_MSG;
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "用户不存在");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getUserDetail(UserRequest req) {
        if (StringUtils.isEmpty(req.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "accno为空");
        }
        if (null == req.getLogintype()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "logintype为空");
        }
        if (req.getLogintype() != 3) {
            // APP用户详细
            if (req.getLogintype() == 1 || req.getLogintype() == 7) {
                //普通会员
                UsersDO usersDO = memLoginService.getAPPUserDetail(req.getAccno());
                return (T) usersDO;
            } else if (req.getLogintype() == 2) {
                //主播
                UsersDO usersDO = memLoginService.getAPPAnchorUserDetail(req.getAccno());
                return (T) usersDO;
            }

        } else {
            // 后台用户详细
            UsersBdDO usersBdDO = memLoginService.getBdUserDetail(req.getAccno());
            return (T) usersBdDO;
        }
        return null;
    }

    @Override
    public PageResult applyAnchorList(UserRequest req, PageBounds page) {
        Page<MemBaseinfoDO> list = memBaseinfoService.applyAnchorList(req, page.toRowBounds());
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(o -> {
                if (StringUtils.isNotEmpty(o.getIdcardfront())) {
                    o.setIdcardfronturl(o.getIdcardfront());
                }
                if (StringUtils.isNotEmpty(o.getIdcardback())) {
                    o.setIdcardbackurl(o.getIdcardback());
                }
            });
        }
        return PageResult.getPageResult(page, list);
    }

//    @Override
//    public String reviewAnchor(MemCertification req, LoginUser loginAdmin, Long familyid) {
//        if (null == req.getCertstatus()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "审核状态为空");
//        }
//        if (StringUtils.isEmpty(req.getCertdesc())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "审核说明为空");
//        }
//        if (StringUtils.isEmpty(req.getAccno())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1104.getCode(), "主播为空");
//        }
//        if (null == familyid) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "家族为空");
//        }
//
//        //获取最后一条待审核的数据
//        MemCertification memCertification = memCertificationService.getMemCertificationOne(req.getAccno());
//        if (memCertification == null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "没有申请信息");
//        }
//        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(req.getAccno());
//        if (memBaseinfo == null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "该用户被删除");
//        }
//
//        memCertification.setCertstatus(req.getCertstatus());
//        memCertification.setCertdesc(req.getCertdesc());
//        memCertification.setUpdateUser(loginAdmin.getAccno());
//        memCertificationService.updateByPrimaryKeySelective(memCertification);
//
//        if (req.getCertstatus() == 0) {
//            //修改memlogin  成为主播
//            UsersDO usersDO = memLoginService.getAPPUserDetail(req.getAccno());
//            MemLogin memLogin = new MemLogin();
//            memLogin.setLoginid(usersDO.getLoginid());
//            memLogin.setLogintype(LoginUserTypeEnum.ANCHOR.getCode());
//
//            int i = memLoginMapper.updateByPrimaryKeySelective(memLogin);
//            if (i > 0) {
//                memberInfoBeanMapper.updateMembaseInfoLoginType(usersDO.getAccno(), LoginUserTypeEnum.ANCHOR.getCode());
//                //重置前台 的 用户的 登录 logintype
//                if (RedisBusinessUtil.exists(memBaseinfo.getAccno())) {
//                    String apptocken = (String) RedisBusinessUtil.get(memBaseinfo.getAccno());
//                    if (RedisBusinessUtil.exists(apptocken)) {
//                        Object jsonstr = RedisBusinessUtil.get(apptocken);
//                        if (jsonstr != null) {
//                            LoginUser appUser = JSONObject.parseObject((String) jsonstr, LoginUser.class);
//                            appUser.setLogintype(LoginUserTypeEnum.ANCHOR.getCode());
//                            Long sessiontime = 2 * 60 * 60L;
//                            RedisBusinessUtil.set(apptocken, JSON.toJSONString(appUser), sessiontime);
//                        }
//                    } else {
//                        logger.info("redis accno-token is null");
//                    }
//                } else {
//                    logger.info("redis accno is null");
//                }
//            }
//            //把主播管理到家族
//            //查询是否存在家族中
//            MemFamilymem param = new MemFamilymem();
//            param.setAccno(req.getAccno());
//            param.setFamilyid(familyid);
//            MemFamilymem memFamilymem = memFamilymemService.getMemFamilymem(param);
//            if (memFamilymem != null) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_1109.getCode(), "该主播存在家族");
//            } else {
//                MemFamilymem m = new MemFamilymem();
//                m.setFamilyid(familyid);
//                m.setAccno(req.getAccno());
//                m.setNickname(memBaseinfo.getNickname());
//                m.setRoyaltypercent(new BigDecimal(0));
//                m.setCreateUser(loginAdmin.getAccno());
//                m.setUpdateUser(loginAdmin.getAccno());
//
//                memFamilymemService.insertSelective(m);
//
//                //更新家族主播数量
//                // 修改家族组员数量
//                memFamilyService.updateAddMemnums(familyid, 1);
//            }
//        }
//        //发送系统消息
//        InfSysremindinfo infSysremindinfo = new InfSysremindinfo();
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//        infSysremindinfo.setUuid(uuid);
//        infSysremindinfo.setBdpushid(null);
//        infSysremindinfo.setSender(loginAdmin.getAccno());
//        infSysremindinfo.setRmtype(Constants.RMTYPE_SYSTEM);
//        infSysremindinfo.setRecipienter(req.getAccno());
//        if (req.getCertstatus() == 0) {
//            //您的主播申请已通过，恭喜您成为主播，成为大家庭的一员
//            infSysremindinfo.setRemindtxt("您的主播申请已通过，恭喜您成为主播，成为大家庭的一员");
//        } else {
//            //很抱歉，您的主播申请暂未通过，请确认您提交的资料是否完整
//            infSysremindinfo.setRemindtxt("很抱歉，您的主播申请暂未通过，请确认您提交的资料是否完整");
//        }
//        infSysremindinfo.setRmtitle("审核主播");
//        infSysremindinfo.setSenddate(new Date());
//        infSysremindinfo.setRefparm(String.valueOf(memCertification.getCertid()));
//        infSysremindinfo.setRefaddlink(String.valueOf(req.getCertstatus()));
//        infSysremindinfo.setRmdateexp(DateUtils.afterDays(new Date(), 7));
//        infSysremindinfo.setIssee(Constants.ISSEE_9);
//        infSysremindinfo.setIstodo(Constants.ISTODO_0);
//        infSysremindinfo.setCreateUser(loginAdmin.getAccno());
//        infSysremindinfo.setUpdateUser(loginAdmin.getAccno());
//        infSysremindinfoService.insertSelective(infSysremindinfo);
//        RedisBusinessUtil.delete(RedisKeys.LIVE_INF_SYSREMINDINFO_NUM + req.getAccno());
//
//        return Constants.SUCCESS_MSG;
//    }

    @Override
    public String updatePassword(String acclogin, String pwd, Integer type, LoginUser loginAdmin) {
        if (loginAdmin == null || StringUtils.isEmpty(loginAdmin.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), " 未登录");
        }
        if (type == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "类型为空");
        }
        if (StringUtils.isEmpty(acclogin)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "手机号为空");
        }
        if (StringUtils.isEmpty(pwd)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "密码为空");
        }

        // 检查 密码 是否 过于简单
        List<String> jPwd = CommonFunction.jiandanPwd();
        if (jPwd.contains(pwd)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "您设置的密码过于简单");
        }

        MemLogin memLoginParam = new MemLogin();
        memLoginParam.setAcclogin(acclogin);
        MemLogin memLogin = memLoginService.getMemLoginByParam(memLoginParam);
        if (memLogin != null) {
            if (!memLogin.getAccstatus().equals(Constants.ACCOUNT_ONE)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "账号已禁用");
            }

            SysInfolog sysInfolog = new SysInfolog();
            //type=0修改登录密码，type=1修改支付密码
            if (type.intValue() == Constants.DEFAULT_INTEGER) {
                memLoginParam.setPasswordmd5(pwd);
                memLoginParam.setLoginid(memLogin.getLoginid());
                memLoginMapper.updateByPrimaryKeySelective(memLoginParam);
                sysInfolog.setModelname("后台用户管理操作-修改登录密码");
                RedisBusinessUtil.clearUserLoginInfo(memLogin.getAccno());
            } else {
                memLoginParam.setPaypassword(pwd);
                memLoginParam.setLoginid(memLogin.getLoginid());
                memLoginMapper.updateByPrimaryKeySelective(memLoginParam);
                sysInfolog.setModelname("后台用户管理操作-修改支付密码");
            }
            sysInfolog.setOptcontent("修改loginid=" + memLogin.getLoginid());
            // 日志
            sysInfolog.setAccno(loginAdmin.getAccno());
            sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
            sysInfolog.setOrginfo("updatePassword");
            commonService.insertSelective(sysInfolog);
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_111.getCode(), "账号不存在");
        }
        return Constants.SUCCESS_MSG;
    }

    @Override
    public String updateRemark(Long memid, String remark, LoginUser loginAdmin) {
        if (loginAdmin == null || StringUtils.isEmpty(loginAdmin.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), " 未登录");
        }
        if (null == memid) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "账号为空");
        }

        MemBaseinfo mem = memBaseinfoService.getMemById(memid);
        if (null == mem) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "账号不存在");
        }
        MemBaseinfo newMem = new MemBaseinfo();
        newMem.setMemid(memid);
        newMem.setRemark(remark);
        memBaseinfoService.updateByPrimaryKeySelective(newMem);
        return Constants.SUCCESS_MSG;
    }

    @Override
    @Transactional
    public boolean modifyCredit(UserCreditRequest request, LoginUser loginAdmin) {

        String emailAccno = "";
        String headAccno = "";
        String refAccno = "";

        if (StringUtils.isNotBlank(request.getEmail())) {
            MemLogin memLogin = memLoginService.findByEmailRegister(request.getEmail());
            if (ObjectUtil.isNull(memLogin)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "会员账号不存在!");
            }
            emailAccno = memLogin.getAccno();
        }

        if (StringUtils.isNotBlank(request.getHeadAgent())) {
            MemLogin memLogin = memLoginService.findByEmailRegister(request.getHeadAgent());
            if (ObjectUtil.isNull(memLogin)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "代理账号不存在");
            }
            headAccno = memLogin.getAccno();
        }

        if (StringUtils.isNotBlank(request.getTopAgent())) {
            MemLogin memLogin = memLoginService.findByEmailRegister(request.getTopAgent());
            if (ObjectUtil.isNull(memLogin)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "上级账号不存在");
            }
            refAccno = memLogin.getAccno();
        }

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT mb.accno from mem_baseinfo mb INNER JOIN mem_relationship mr " + "  on mb.accno = mr.accno ");
        if (StringUtils.isNotBlank(emailAccno)) {
            sb.append(" and  mr.accno = " + "'" + emailAccno + "'");
        }
        if (StringUtils.isNotBlank(headAccno)) {
            sb.append(" and  mr.head_accno = " + "'" + headAccno + "'");
        }
        if (StringUtils.isNotBlank(refAccno)) {
            sb.append(" and  mr.refaccno = " + "'" + refAccno + "'");
        }

        if (StringUtils.isNotBlank(request.getBeginTime())) {
            sb.append(" and  mb.registerdate >= " + "'" + request.getBeginTime() + "'");
        }
        if (StringUtils.isNotBlank(request.getEndTime())) {
            sb.append(" and  mb.registerdate <= " + "'" + request.getEndTime() + "'");
        }

        if (ObjectUtil.isNotNull(request.getBeginAmount())) {
            sb.append(" and  mb.goldnum >= " + request.getBeginAmount());
        }
        if (ObjectUtil.isNotNull(request.getEndAmount())) {
            sb.append(" and  mb.goldnum <= " + request.getEndAmount());
        }
        if (ObjectUtil.isNotNull(request.getMemLevel())) {
            MemLevelConfig levelConfig = memLevelConfigService.getMemLevelConfigForConfigId(request.getMemLevel());
            sb.append(" and  mb.`level` = " + levelConfig.getLevelSeq());
        }


        List<String> accList = memberCreditMapper.selectModiyfList(sb.toString());
        if (CollectionUtil.isNotEmpty(accList)) {
            MemberCredit credit = new MemberCredit();
            credit.setUpdateUser(loginAdmin.getAccno());
            credit.setUpdateTime(new Date());
            credit.setIntegral(request.getIntegral());
            memberCreditMapper.modifyBatch(credit, accList);
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "过滤条件未查出结果!");
        }

        MemberCreditDetail memberCreditDetail = new MemberCreditDetail();
        memberCreditDetail.setUpdateUser(loginAdmin.getAcclogin());
        memberCreditDetail.setCreateTime(new Date());
        memberCreditDetail.setUpdateTime(new Date());
        memberCreditDetail.setNum(accList.size());
        memberCreditDetail.setIntegral(request.getIntegral());
        memberCreditDetail.setValue(JSON.toJSONString(request));
        memberCreditDetailMapper.insert(memberCreditDetail);
        return true;
    }

    @Override
    @Transactional
    public boolean singleModifyCredit(CreditChangeRequest changeRequest, LoginUser loginAdmin) {
        int row = memberCreditMapper.modifyIntegral(changeRequest.getIntegral(), changeRequest.getAccno());
        if (row < 1) {
            throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "调整会员信用分失败");
        }
        MemberCreditChange creditChange = new MemberCreditChange();
        creditChange.setAccno(changeRequest.getAccno());
        creditChange.setIntegral(changeRequest.getIntegral());
        creditChange.setRefAcclogin("0");
        creditChange.setCreateTime(new Date());
        memberCreditChangeMapper.insertSelective(creditChange);
        return true;
    }


    @Override
    public PageResult creditList(UserCreditRequest userCreditRequest, LoginUser loginAdmin, Integer pageNo, Integer pageSize) {
        PageBounds page = new PageBounds();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        MemberCreditDetail param = new MemberCreditDetail();
        int totalCount = memberCreditDetailMapper.selectCount(param);
        PageResult<List<MemberCreditDetail>> pageResult = PageResult.getPageResult(pageNo, pageSize, totalCount);
        if (totalCount > 0) {
            PageHelper.startPage((pageNo - 1) * pageSize, pageSize);
            List<MemberCreditDetail> list = memberCreditDetailMapper.selectAll();
            pageResult.setData(list);
        }
        return pageResult;
    }

    @Override
    public PageResult memCreditList(CreditChangeRequest creditRequest, PageBounds pageBounds) {
        Page<MemberCreditVO> pageData = memberCreditMapper.memCreditList(creditRequest, pageBounds.toRowBounds());
        return PageResult.getPageResult(pageBounds, pageData);
    }


    @Override
    public String updateUser(UserUpdateRequest req, LoginUser loginAdmin) {
        //参数检查
        req.updateUserCheck(loginAdmin);
        MemBaseinfo info = memBaseinfoService.getMemById(req.getMemid());
        if (null == info) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "用户为空");
        }

        String pwd = req.getResetPwd();
        if (pwd != null && pwd != "") {
            List<String> jPwd = CommonFunction.jiandanPwd();
            if (jPwd.contains(pwd)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "您设置的密码过于简单");
            }
            MemLogin memLoginParam = new MemLogin();
            memLoginParam.setAcclogin(req.getAcclogin());
            MemLogin memLogin = memLoginService.getMemLoginByParam(memLoginParam);
            if (memLogin != null) {
                if (!memLogin.getAccstatus().equals(Constants.ACCOUNT_ONE)) {
                    throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "账号已禁用");
                }

                SysInfolog sysInfolog = new SysInfolog();
                memLoginParam.setPasswordmd5(pwd);
                memLoginParam.setLoginid(memLogin.getLoginid());
                memLoginMapper.updateByPrimaryKeySelective(memLoginParam);
                sysInfolog.setModelname("后台用户管理操作-修改登录密码");
                RedisBusinessUtil.clearUserLoginInfo(memLogin.getAccno());

                sysInfolog.setOptcontent("修改loginid=" + memLogin.getLoginid());
                // 日志
                sysInfolog.setAccno(loginAdmin.getAccno());
                sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
                sysInfolog.setOrginfo("updatePassword");
                commonService.insertSelective(sysInfolog);
            } else {
                throw new BusinessException(StatusCode.LIVE_ERROR_111.getCode(), "账号不存在");
            }
        }

        //修改用户表-昵称、个人签名
        updateUserInfo(req, loginAdmin, info);
        //修改银行卡信息
        updateUserBank(req, loginAdmin, info);
        //修改用户等级
        updateUserLevel(info.getAccno(), req.getMemlevel(), req.getLocked(), loginAdmin);
        return Constants.SUCCESS_MSG;
    }

    /**
     * 修改用户表-昵称、个人签名
     */
    private void updateUserInfo(UserUpdateRequest req, LoginUser loginAdmin, MemBaseinfo info) {
        String nickname = req.getNickname().trim();
        if (nickname.equals(info.getMemname())) {
            return;
        }
        updateNickname(nickname, info.getAccno());
        updateDescribes(req.getDescribes(), loginAdmin, info);
    }

    /**
     * 修改用户表-个人签名
     */
    private void updateDescribes(String describes, LoginUser loginAdmin, MemBaseinfo info) {
        if (StringUtils.isBlank(describes)) {
            if (StringUtils.isBlank(info.getDescribes())) {
                return;
            }
        } else if (describes.equals(info.getMemname())) {
            return;
        }
        MemBaseinfo newinfo = new MemBaseinfo();
        newinfo.setUpdateUser(loginAdmin.getAccno());
        newinfo.setUpdateTime(new Date());
        newinfo.setMemid(info.getMemid());
        newinfo.setDescribes(null == describes ? "" : describes);
        memBaseinfoService.updateByPrimaryKeySelective(newinfo);
    }

    /**
     * 保持和PersonServiceImpl的updateNickname一致
     */
    private void updateNickname(String nickname, String accno) {
        List<String> nickNameList = new ArrayList<>();
        nickNameList.add("BB直播");
        nickNameList.add("bb直播");
        nickNameList.add("BB");
        nickNameList.add("bb");
        nickNameList.add("逼逼");
        nickNameList.add("官方");
        if (nickNameList.contains(nickname)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "昵称包含非法字符");
        }
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(accno);
        //昵称相同不改昵称
        if (nickname.equals(memBaseinfo.getNickname())) {
            return;
        }
        // 查询昵称是否已经存在
        MemBaseinfo repeat = memBaseinfoService.getRepeateNickname(nickname.trim());
        if (repeat != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_110.getCode(), "该昵称已存在");
        }
        MemBaseinfo newinfo = new MemBaseinfo();
        newinfo.setUpdateUser(accno);
        newinfo.setMemid(memBaseinfo.getMemid());
        newinfo.setNickname(nickname);
        memBaseinfoService.updateByPrimaryKeySelective(newinfo);

        // 昵称修改成功后 刷新redis
        String acctoken = RedisBusinessUtil.get(accno);
        if (StringUtils.isNotBlank(acctoken)) {
            Object jsonstr = RedisBusinessUtil.get(acctoken);
            if (null != jsonstr) {
                LoginUser loginUserAPP = JSONObject.parseObject((String) jsonstr, LoginUser.class);
                loginUserAPP.setNickname(nickname);
                RedisBusinessUtil.refreshUser(loginUserAPP, sysParamService);
            }
        }
    }

    /**
     * 修改用户等级
     */
    @Override
    public void updateUserLevel(String accno, String memlevel, Boolean locked, LoginUser loginAdmin) {
        MemLevel level = memLevelService.selectByAccno(accno);
        if (null != level && null != memlevel) {
            //等级相同+锁相同,不修改
            if (memlevel.equals(level.getMemlevel()) && locked.equals(level.getLocked())) {
                return;
            }
            MemLevelConfig config = memLevelConfigService.getMemLevelConfigForLevel(memlevel);
            if (config == null) {
                return;
            }

            MemLevel update = new MemLevel();
            update.setLevelid(level.getLevelid());
            update.setMemlevel(config.getLevel());
            update.setLevelConfigId(config.getId());
            update.setUpdateUser(loginAdmin.getAccno());
            update.setUpdateTime(new Date());
            if (locked != null) {
                update.setLocked(locked);
            }
            memLevelService.updateLevelAndLocked(update, accno);


            LoginUser user = new LoginUser();
            user.setAccno(accno);
            user.setMemlevel(memlevel);
            try {
                RedisBusinessUtil.refreshTokenByAccno(user, sysParamService);
            } catch (Exception e) {
                logger.error("用户【{}】更新会话失败", user.getAcclogin(), e);
            }
        }
    }

    @Override
    public void updateUserLevelNoLock(String accno, String memlevel, boolean b, LoginUser loginAdmin) {
        MemLevel level = memLevelService.selectByAccno(accno);
        if (null != level && null != memlevel) {

            MemLevel update = new MemLevel();
            update.setLevelid(level.getLevelid());
            update.setMemlevel(memlevel);
            update.setUpdateUser(loginAdmin.getAccno());
            update.setUpdateTime(new Date());
            update.setLocked(false);
            memLevelService.updateLevelAndLocked(update, accno);

            MemLevelConfig config = memLevelConfigService.getMemLevelConfigForLevel(memlevel);
            LoginUser user = new LoginUser();
            user.setAccno(accno);
            user.setMemlevel(memlevel);

            try {
                RedisBusinessUtil.refreshTokenByAccno(user, sysParamService);
            } catch (Exception e) {
                logger.error("trial.updateUserLevelNoLock:fail：{}", user.getAcclogin(), e);
            }
        }
    }

    /**
     * 修改银行信息
     */
    private void updateUserBank(UserUpdateRequest req, LoginUser loginAdmin, MemBaseinfo info) {
        //修改银行卡信息
        MemBankaccount old = memBankaccountService.findByAccno(info.getAccno());
        MemBankaccount newBank = new MemBankaccount();
        newBank.setBankname(req.getBankname());
        newBank.setBankaddress(req.getBankaddress());
        newBank.setAccountno(req.getAccountno());
        newBank.setAccountname(req.getAccountname());
        newBank.setUpdateUser(loginAdmin.getAccno());
        if (null == old) {
            //如果都为空不保存
            if (StringUtils.isBlank(req.getBankname()) && StringUtils.isBlank(req.getBankaddress()) && StringUtils.isBlank(req.getAccountno()) && StringUtils.isBlank(req.getAccountname())) {
                return;
            }
            newBank.setAccno(info.getAccno());
            newBank.setCheckstatus(Constants.CHECKSTATUS_8);
            newBank.setAccounttype(Constants.DEFAULT_THREE);
            newBank.setCreateUser(loginAdmin.getAccno());
            memBankaccountMapper.insertSelective(newBank);
        } else {
            //如果相同，则不更新
            if (null != req.getBankname() && req.getBankname().equals(old.getBankname()) && null != req.getBankaddress() && req.getBankaddress().equals(old.getBankaddress()) && null != req.getAccountno() && req.getAccountno().equals(old.getAccountno()) && null != req.getAccountname() && req.getAccountname().equals(old.getAccountname())) {
                return;
            }
            newBank.setBankaccid(old.getBankaccid());
            memBankaccountMapper.updateByPrimaryKeySelective(newBank);
        }
    }

//    @Override
//    public PageResult anchorList(UserRequest req, Integer pageNo, Integer pageSize) {
//        PageBounds page = new PageBounds();
//        page.setPageNo(pageNo);
//        page.setPageSize(pageSize);
//        UserRequestRowBoundsReq userRequestRowBoundsReq = new UserRequestRowBoundsReq();
//        userRequestRowBoundsReq.setRowBounds(page.toRowBounds());
//        userRequestRowBoundsReq.setUserRequest(req);
//        Page<BasAnchoronlineVO> basAnchoronlineList = basAnchoronLineRest.getOnlineList(userRequestRowBoundsReq);
//
//        if (null != basAnchoronlineList && !CollectionUtils.isEmpty(basAnchoronlineList.getResult())) {
//            for (BasAnchoronlineVO basAnchoron : basAnchoronlineList.getResult()) {
//                List<Integer> typeList = new ArrayList<Integer>();
//                typeList.add(2);
//                String startTime = DateUtils.formatDate(basAnchoron.getOnlinedate());
//                String endTime = DateUtils.formatDate(basAnchoron.getOfflinedate());
//                BigDecimal totalGold = memGoldchangeService.tatolGoldchange(basAnchoron.getAccno(), null, typeList, startTime, endTime);
//                if (null != totalGold) {
//                    basAnchoron.setTotalGold(totalGold.abs());
//                } else {
//                    basAnchoron.setTotalGold(new BigDecimal("0"));
//                }
//            }
//        }
//
//        return PageResult.getPageResult((int) basAnchoronlineList.getTotal(), page, basAnchoronlineList);
//    }

    @Override
    public UserDetailForUpdateVO userDetailForUpdate(BankRequest req, LoginUser loginAdmin) {
        if (null == req.getMemid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "用户id为空");
        }
        MemBaseinfo info = memBaseinfoService.getMemById(req.getMemid());
        if (null == info) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "用户为空");
        }
        UserDetailForUpdateVO vo = new UserDetailForUpdateVO();
        BeanUtils.copyProperties(info, vo);
        vo.setAcclogin(info.getEmail());
        if (!(req.getManage() != null && req.getManage())) {
            vo.setEmail("***********");
        }
        //等级
        MemLevel memLevel = memLevelService.selectByAccno(info.getAccno());
        if (null != memLevel) {
            vo.setMemlevel(memLevel.getMemlevel());
            vo.setLocked(memLevel.getLocked());
        }
        //银行
        MemBankaccount bank = memBankaccountService.findByAccno(info.getAccno());
        if (null != bank) {
            vo.setAccountname(bank.getAccountname());
            vo.setAccountno(bank.getAccountno());
            vo.setBankaddress(bank.getBankaddress());
            vo.setBankname(bank.getBankname());
        }
        //上级代理
        MemRelationship relation = memRelationshipService.findByAccno(info.getAccno());
        if (null != relation) {
            MemBaseinfo refUser = memBaseinfoService.getUserByAccno(relation.getRefaccno());
            if (null != refUser) {
                vo.setRefUniqueId(refUser.getUniqueId());
            }
        }
        //上级代理
        MemLogin memLogin = memLoginService.findByAccno(info.getAccno());
        vo.setAccstatus(memLogin.getAccstatus());
        vo.setLastlogindate(memLogin.getLastlogindate());
        return vo;
    }

    @Override
    public String delUserHeadimg(BankRequest req, LoginUser loginAdmin) {
        if (null == req.getMemid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "用户id为空");
        }
        MemBaseinfo info = memBaseinfoService.getMemById(req.getMemid());
        if (null == info) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "用户为空");
        }
        String headimg = sysBusParamService.getRandomHeadImg();
        if (null != headimg) {
            //删除用户头像
            MemBaseinfo update = new MemBaseinfo();
            update.setMemid(req.getMemid());
            update.setHeadimg(headimg);
            memBaseinfoService.updateByPrimaryKeySelective(update);
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "设置默认头像出错");
        }
        return Constants.SUCCESS_MSG;
    }

    @Override
    public String updateProxyUrl(Long memid, String proxyUrl, LoginUser loginAdmin) {
        if (loginAdmin == null || StringUtils.isEmpty(loginAdmin.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), " 未登录");
        }
        if (null == memid) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "账号为空");
        }

        MemBaseinfo mem = memBaseinfoService.getMemById(memid);
        if (null == mem) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "账号不存在");
        }
        MemBaseinfo newMem = new MemBaseinfo();
        newMem.setMemid(memid);
//        newMem.setProxyUrl(proxyUrl);
        memBaseinfoService.updateByPrimaryKeySelective(newMem);
        return Constants.SUCCESS_MSG;
    }
}
