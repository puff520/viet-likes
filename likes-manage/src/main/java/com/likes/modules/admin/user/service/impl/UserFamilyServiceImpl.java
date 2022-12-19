//package com.likes.modules.admin.user.service.impl;
//
//import com.likes.common.constant.Constants;
//import com.likes.common.enums.AccountTypeEnum;
//import com.likes.common.enums.LoginUserTypeEnum;
//import com.likes.common.enums.StatusCode;
//import com.likes.common.enums.pay.MemBankAccountTypeEnum;
//import com.likes.common.exception.BusinessException;
//import com.likes.common.model.LoginUser;
//import com.likes.common.model.common.PageBounds;
//import com.likes.common.model.common.PageResult;
//import com.likes.common.model.dto.member.MemFamilyReq;
//import com.likes.common.model.dto.member.MemFamilyResponse;
//import com.likes.common.model.dto.member.MemFamilymemReq;
//import com.likes.common.model.dto.member.MemFamilymemSingleReq;
//import com.likes.common.model.dto.member.MemLoginDO;
//import com.likes.common.model.dto.member.UserDO;
//import com.likes.common.mybatis.entity.MemBankaccount;
//import com.likes.common.mybatis.entity.MemBaseinfo;
//import com.likes.common.mybatis.entity.MemFamily;
//import com.likes.common.mybatis.entity.MemFamilymem;
//import com.likes.common.mybatis.entity.MemLogin;
//import com.likes.common.mybatis.entity.SysBusparameter;
//import com.likes.common.mybatis.mapper.MemBankaccountMapper;
//import com.likes.common.mybatis.mapper.MemLoginMapper;
//import com.likes.common.service.code.UniqueCodeService;
//import com.likes.common.service.member.MemBankaccountService;
//import com.likes.common.service.member.MemBaseinfoService;
//import com.likes.common.service.member.MemFamilyService;
//import com.likes.common.service.member.MemFamilymemService;
//import com.likes.common.service.member.MemLoginService;
//import com.likes.common.service.sys.SysBusParamService;
//import com.likes.common.util.BeanUtils;
//import com.likes.common.util.NameGeneratorUtil;
//import com.likes.common.util.RandomUtil;
//import com.likes.common.util.redis.RedisBusinessUtil;
//import com.likes.modules.admin.user.service.UserFamilyService;
//import com.github.pagehelper.Page;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class UserFamilyServiceImpl implements UserFamilyService {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Resource
//    private MemFamilyService memFamilyService;
//    @Resource
//    private MemBankaccountMapper memBankaccountMapper;
//    @Resource
//    private MemBankaccountService memBankaccountService;
//    @Resource
//    private MemFamilymemService memFamilymemService;
//    @Resource
//    private SysBusParamService sysBusParamService;
//    @Resource
//    private MemBaseinfoService memBaseinfoService;
//    @Resource
//    private MemLoginMapper memLoginMapper;
//    @Resource
//    private MemLoginService memLoginService;
//    @Resource
//    private UniqueCodeService uniqueCodeService;
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Long doSaveFamily(MemFamilyReq req, LoginUser loginUser) {
//        //logger.info("参数：" + JsonUtil.toJson(req));
//        if (null == req.getRoyaltypercent() || null == req.getBettingpercentage()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "分成为空");
//        }
//        if (req.getRoyaltypercent().doubleValue() < 0 || req.getRoyaltypercent().doubleValue() > 1 ||
//                req.getBettingpercentage().doubleValue() < 0 || req.getBettingpercentage().doubleValue() > 1) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "分成数字不对");
//        }
//        if (req.getAccounttype() == AccountTypeEnum.UNIONPAY.getCode()) {
//            if (StringUtils.isEmpty(req.getBankname())) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "银行名称为空");
//            }
//
//            if (StringUtils.isEmpty(req.getBankaddress())) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "开户行为空");
//            }
//        }
//        // 验证家族名称 重复
//        MemFamily mFamily = memFamilyService.getRepeat(req);
//        if (mFamily != null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "家族名称重复");
//        }
//
//        String accno = RandomUtil.uuid();
//        MemFamily memFamily = new MemFamily();
//        BeanUtils.copyProperties(req, memFamily);
//        memFamily.setCreateUser(loginUser.getAccno());
//        memFamily.setUpdateUser(loginUser.getAccno());
//        memFamily.setMemnums(0l);
//        memFamily.setAccno(accno);
//        int i = memFamilyService.insertSelective(memFamily);
//        if (i > 0) {
//            MemBankaccount memBankaccount = new MemBankaccount();
//            BeanUtils.copyProperties(req, memBankaccount);
//            memBankaccount.setAccidcardno(memFamily.getIdcardno());
//            memBankaccount.setFamilyid(memFamily.getFamilyid());
//            memBankaccount.setCheckstatus(Constants.CHECKSTATUS_8);
//            memBankaccount.setCreateUser(loginUser.getAccno());
//            memBankaccount.setUpdateUser(loginUser.getAccno());
//            memBankaccountMapper.insertSelective(memBankaccount);
//
//            //在创建mem_login 和  mem_baseinfo
//            MemLogin ml = memLoginService.findByAccloginRegister(req.getAcclogin());
//            if (ml != null) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "家族长专用账号已存在");
//            } else {
//                //新增
//                MemLoginDO memLogin = new MemLoginDO();
//                memLogin.setAccno(accno);
//                memLogin.setAcclogin(req.getAcclogin());
//                memLogin.setPasswordmd5(req.getPasswordmd5());
//                memLogin.setLogintype(LoginUserTypeEnum.FAMILYHEAD.getCode());
//                memLogin.setAccstatus(1);
//                memLogin.setLoginnum(0);
//                memLoginService.insertMemLogin(memLogin);
//
//                MemBaseinfo newUser = new MemBaseinfo();
//                newUser.setAccno(accno);
//                newUser.setMobileno(req.getTelephone());
//                String dateStr = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date());
//                newUser.setNickname(NameGeneratorUtil.generate() + dateStr.substring(dateStr.length() - 3, dateStr.length()));
//                newUser.setFansnum(0l);
//                newUser.setGoldnum(new BigDecimal(0));
//                newUser.setRegisterdate(new Date());
//                newUser.setCreateUser(newUser.getAccno());
//                newUser.setUpdateUser(newUser.getAccno());
//                newUser.setMemname(req.getFamilyman());
//                newUser.setLogintype(LoginUserTypeEnum.FAMILYHEAD.getCode());
//                newUser.setIdcardno(req.getIdcardno());
//                // 设置注册用户的邀请码
//                /*boolean recomcodeFlag = false;
//                String recomcode = null;
//                while (!recomcodeFlag) {
//                    recomcode = CommonFunction.inviteCode();
//                    MemBaseinfo recomcodeUser = memBaseinfoService.getUserByInvitecodeAll(recomcode);
//                    if (recomcodeUser == null) {
//                        recomcodeFlag = true;
//                    }
//                }*/
//                newUser.setUniqueId(uniqueCodeService.getAnchorUniqueId());
//                newUser.setRecomcode(uniqueCodeService.getAnchorInviteCode());
//                newUser.setMemorgin("regist");
//                // 用户基本信息
//                memBaseinfoService.insertSelective(newUser);
//            }
//        }
//        return memFamily.getFamilyid();
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Long doUpdateFamily(MemFamilyReq req, LoginUser loginUser) {
//        if (null == req.getRoyaltypercent() || null == req.getBettingpercentage()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "分成为空");
//        }
//        if (req.getRoyaltypercent().doubleValue() < 0 || req.getRoyaltypercent().doubleValue() > 1 ||
//                req.getBettingpercentage().doubleValue() < 0 || req.getBettingpercentage().doubleValue() > 1) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "分成数字不对");
//        }
//        if (null == req.getFamilyid()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "家族ID为空");
//        }
//        if (req.getAccounttype() == AccountTypeEnum.UNIONPAY.getCode()) {
//            if (StringUtils.isEmpty(req.getBankname())) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "银行名称为空");
//            }
//
//            if (StringUtils.isEmpty(req.getBankaddress())) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "开户行为空");
//            }
//        }
//
//        // 验证家族名称 重复
//        MemFamily mFamily = memFamilyService.getRepeat(req);
//        if (mFamily != null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "家族名称重复");
//        }
//
//        MemFamily memFamily = memFamilyService.selectByPrimaryKey(req.getFamilyid());
//        if (memFamily == null || memFamily.getIsDelete()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "家族不存在");
//        }
//
//        BeanUtils.copyProperties(req, memFamily);
//        memFamily.setUpdateUser(loginUser.getAccno());
//        //成员数量不能改
//        memFamily.setMemnums(null);
//        memFamilyService.updateByPrimaryKeySelective(memFamily);
//
//        MemBankaccount memBankaccount = memBankaccountService.findByFamilyId(req.getFamilyid());
//        if (memBankaccount == null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "家族银行账号不存在");
//        }
//        if (req.getAccountno().contains("*")) {
//            req.setAccountno(memBankaccount.getAccountno());
//        }
//        BeanUtils.copyProperties(req, memBankaccount);
//        memBankaccount.setAccidcardno(memFamily.getIdcardno());
//        memBankaccount.setFamilyid(memFamily.getFamilyid());
//        memBankaccount.setUpdateUser(loginUser.getAccno());
//        memBankaccount.setAccno(null);
//        if (req.getAccounttype() != 3) {
//            memBankaccount.setBankname(null);
//        }
//
//        memBankaccountMapper.updateByPrimaryKeySelective(memBankaccount);
//
//        //修改 mem_login 和 mem_baseinfo
//        MemLogin memLogin = memLoginService.findByAccno(memFamily.getAccno());
//        if (memLogin != null) {
//            //严重这个账号是否存在
//            MemLogin ml = memLoginService.findByAccloginAndLoginidRegister(req.getAcclogin(), memLogin.getLoginid());
//            if (ml != null) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_1105.getCode(), "家族账号已存在");
//            }
//            memLogin.setAcclogin(req.getAcclogin());
//            memLogin.setPasswordmd5(req.getPasswordmd5());
//            memLoginMapper.updateByPrimaryKeySelective(memLogin);
//
//            MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(memFamily.getAccno());
//            if (memBaseinfo != null) {
//                memBaseinfo.setMemname(req.getFamilyman());
//                memBaseinfo.setIdcardno(req.getIdcardno());
//                memBaseinfo.setMobileno(req.getTelephone());
//                memBaseinfo.setGoldnum(null);
//                memBaseinfo.setFansnum(null);
//                memBaseinfo.setUpdateUser(loginUser.getAccno());
//                memBaseinfoService.updateByPrimaryKeySelective(memBaseinfo);
//            }
//        }
//        return memFamily.getFamilyid();
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public String doDelFamily(MemFamilyReq req, LoginUser loginUser) {
//        if (null == req.getFamilyid()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "家族ID为空");
//        }
//        MemFamily memFamily = memFamilyService.selectByPrimaryKey(req.getFamilyid());
//        if (memFamily == null || memFamily.getIsDelete()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "家族不存在");
//        }
//        if (memFamily.getMemnums() > 0) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "主播人数大于0,不能删除");
//        }
//
//        memFamily.setIsDelete(true);
//        memFamily.setUpdateUser(loginUser.getAccno());
//        int i = memFamilyService.doDelFamily(memFamily);
//
//        if (i > 0) {
//            // 删除该家族长的银行卡号
//            MemBankaccount memBankaccount = memBankaccountService.findByFamilyId(req.getFamilyid());
//            if (memBankaccount == null) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "家族银行账号不存在");
//            }
//            memBankaccount.setIsDelete(true);
//            memBankaccount.setUpdateUser(loginUser.getAccno());
//            memBankaccountMapper.updateByPrimaryKeySelective(memBankaccount);
//
//            //修改 mem_login 和 mem_baseinfo
//            MemLogin memLogin = memLoginService.findByAccno(memFamily.getAccno());
//            if (memLogin != null) {
//                memLogin.setAccstatus(9);
//                memLoginService.updateLogin(memLogin);
//
//                MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(memFamily.getAccno());
//                if (memBaseinfo != null) {
//                    memBaseinfo.setIsDelete(true);
//                    memBaseinfo.setGoldnum(null);
//                    memBaseinfo.setFansnum(null);
//                    memBaseinfo.setUpdateUser(loginUser.getAccno());
//                    memBaseinfoService.updateByPrimaryKeySelective(memBaseinfo);
//                }
//                RedisBusinessUtil.clearUserLoginInfo(memLogin.getAccno());
//            }
//
//            //删除该家族关联的主播
//            memFamilymemService.doDelMemFamilymemAnchor(memFamily.getFamilyid(), loginUser.getAccno());
//
//            return Constants.SUCCESS_MSG;
//        } else {
//            return Constants.FAIL_MSG;
//        }
//    }
//
//    @Override
//    public PageResult familyList(MemFamilyReq req, PageBounds page) {
//        Page<MemFamilyResponse> list = memFamilyService.familyList(req, page.toRowBounds());
//        if (CollectionUtils.isNotEmpty(list)) {
//            list.forEach(o -> {
//                // 账号类型 1支付宝 2微信 3银联
//                int accounttype = o.getAccounttype();
//                o.setAccounttypename(MemBankAccountTypeEnum.valueOf(accounttype).getName());
//                if (accounttype == 3) {
//                    // 查询银行
//                    SysBusparameter sysBusparameter = sysBusParamService.selectByBusparamcode(o.getBankname());
//                    if (sysBusparameter != null) {
//                        o.setBanknamealias(sysBusparameter.getBusparamname());
//                    }
//                }
//
//            });
//        }
//        return PageResult.getPageResult(page, list);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public String doSaveManyFamilymem(MemFamilymemReq req, LoginUser loginUser) {
//        // accno 去重复 不相信前端
//        List<String> accnoStrings = req.getAccnolist();
//        Set<String> set = new HashSet<String>(accnoStrings);
//        BigDecimal royaltypercent = null;
//        if (null == req.getRoyaltypercent()) {
//            royaltypercent = new BigDecimal(0);
//        } else {
//            royaltypercent = req.getRoyaltypercent().setScale(2, BigDecimal.ROUND_DOWN);
//        }
//
//        //如果accno已经存在，则返回错误
//        if (isMenExist(set)) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "用户已经被添加，请刷新后添加");
//        }
//
//        // 准备
//        List<String> accnolist = new ArrayList<String>(set);
//        for (String accno : accnolist) {
//            MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(accno);
//            String nickname = "";
//            if (memBaseinfo != null) {
//                nickname = memBaseinfo.getNickname();
//            }
//            MemFamilymem memFamily = new MemFamilymem();
//            memFamily.setAccno(accno);
//            memFamily.setFamilyid(req.getFamilyid());
//            memFamily.setRoyaltypercent(royaltypercent);
//            memFamily.setCreateUser(loginUser.getAccno());
//            memFamily.setUpdateUser(loginUser.getAccno());
//            memFamily.setNickname(nickname);
//            memFamily.setIsDelete(false);
//            int i = memFamilymemService.insertMemFamilymem(memFamily);
//            if (i < 0) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), nickname + "存在家族");
//            }
//        }
//
//        // 修改家族组员数量
//        memFamilyService.updateAddMemnums(req.getFamilyid(), accnolist.size());
//        return Constants.SUCCESS_MSG;
//    }
//
//    private boolean isMenExist(Set<String> set) {
//        for (String accno : set) {
//            int num = memFamilymemService.countByExample(accno);
//            if (num > 0) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    //@Transactional(rollbackFor = Exception.class)
//    public String doUpdateOneFamilymemRcent(MemFamilymemSingleReq req, LoginUser loginUser) {
//        MemFamilymem mFamilymem = memFamilymemService.selectByPrimaryKey(req.getFamilymemid());
//        if (mFamilymem == null || mFamilymem.getIsDelete()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "成员不存在");
//        }
//        try {
//            BigDecimal royaltypercent = req.getRoyaltypercent().setScale(2, BigDecimal.ROUND_DOWN);
//            mFamilymem.setRoyaltypercent(royaltypercent);
//            mFamilymem.setUpdateUser(loginUser.getAccno());
//            memFamilymemService.updateByPrimaryKeySelective(mFamilymem);
//            return Constants.SUCCESS_MSG;
//        } catch (Exception e) {
//            logger.info("error: " + e.getMessage());
//        }
//        return Constants.FAIL_MSG;
//
//    }
//
//    @Override
//    public PageResult getNoFamilyAnchorList(MemFamilymem req, PageBounds page) {
//        Page<UserDO> list = memFamilymemService.getNoFamilyAnchorList(req, page.toRowBounds());
//        return PageResult.getPageResult(page, list);
//    }
//
//    @Override
//    public PageResult getFamilyAnchorList(MemFamilymem req, PageBounds page) {
//        if (null == req.getFamilyid()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "家族ID为空");
//        }
//        Page<MemFamilymem> list = memFamilymemService.getFamilyAnchorList(req, page.toRowBounds());
//        return PageResult.getPageResult(page, list);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public String doDelFamilyAnchor(MemFamilymem req, LoginUser loginUser) {
//        if (null == req.getFamilymemid()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "成员关系ID不能为空");
//        }
//		/*if (null == req.getFamilyid()) {
//			throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "家族ID不能为空");
//		}*/
//
//        MemFamilymem memFamilymem = memFamilymemService.selectByPrimaryKey(req.getFamilymemid());
//        if (memFamilymem == null || memFamilymem.getIsDelete()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "成员关系不存在或已删除");
//        }
//        // 删除关系
//        int i = memFamilymemService.doDelFamilyAnchor(req);
//        if (i > 0) {
//            // 修改家族组员数量Subtract
//            memFamilyService.updateSubtractMemnums(memFamilymem.getFamilyid(), 1);
//        } else {
//            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "已经取消关系");
//        }
//        return Constants.SUCCESS_MSG;
//    }
//
//    @Override
//    public List<MemFamily> allFamilyList(MemFamilymem req, LoginUser loginUser) {
//        return memFamilyService.allFamilyList();
//    }
//
//}
