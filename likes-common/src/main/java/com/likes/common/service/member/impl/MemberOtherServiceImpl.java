package com.likes.common.service.member.impl;

import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.constant.Constants;
import com.likes.common.constant.FileConstant;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.LoginUserTypeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.MemDailyReport;
import com.likes.common.model.SubAndTaskDto;
import com.likes.common.model.TimeReortDTO;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.AgentData;
import com.likes.common.model.dto.member.*;
import com.likes.common.model.dto.report.DayReportDTO;
import com.likes.common.model.dto.report.MemberReportDto;
import com.likes.common.model.dto.report.TeamReportDto;
import com.likes.common.model.request.*;
import com.likes.common.model.response.AppTeamResponse;
import com.likes.common.model.response.TeamBo;
import com.likes.common.model.response.TeamResponse;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.AgentMapper;
import com.likes.common.mybatis.mapper.MemDailyReportMapper;
import com.likes.common.mybatis.mapper.MemLevelRecordMapper;
import com.likes.common.mybatis.mapper.TaskMapper;
import com.likes.common.mybatis.mapperext.member.MemBankaccountMapperExt;
import com.likes.common.mybatis.mapperext.member.MemBaseinfoMapperExt;
import com.likes.common.mybatis.mapperext.member.MemGoldchangeMapperExt;
import com.likes.common.mybatis.mapperext.member.MemLevelMapperExt;
import com.likes.common.service.code.UniqueCodeService;
import com.likes.common.service.credit.MemCreditService;
import com.likes.common.service.member.*;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.*;
import com.likes.common.util.http.HttpUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.common.util.uploadFile.FileNameUtils;
import com.likes.common.util.uploadFile.FileUtils2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;


import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberOtherServiceImpl implements MemberOtherService {
    @Resource
    private MemBaseinfoMapperExt memBaseinfoMapperExt;
    @Resource
    private MemLevelRecordMapper memLevelRecordMapper;
    @Resource
    private MemLevelMapperExt memLevelMapperExt;
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private UniqueCodeService uniqueCodeService;
    @Resource
    private MemLoginService memLoginService;
    @Resource
    private SysBusParamService sysBusParamService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemCreditService memCreditService;
    @Resource
    private MemLevelService memLevelService;
    @Resource
    private MemRelationshipService memRelationshipService;

    @Resource
    private MemGoldchangeMapperExt memGoldchangeMapperExt;
    @Resource
    private TaskMapper taskMapper;

    @Resource
    private AgentMapper agentMapper;


    @Resource
    private MemDailyReportMapper memDailyReportMapper;


    @Override
    public boolean deleteForManager(Long id, String uerid) {
      return true;
    }



//    @Override
//    @ReadOnlyConnection
//    public PageInfo<MemberVipDTO> getMemberVipLst(MemberVIPRequest req) {
//        PageHelper.startPage(req.getPageNo(), req.getPageSize());
//        if (req.getStartTime() != null) {
//            req.setEndTime(DateUtils.addDays(req.getStartTime(), 1));
//        }
//        List<MemberVipDTO> tmp = memLevelMapperExt.getMemberVipLst(req);
//        PageInfo pageInfo = new PageInfo(tmp);
//        return pageInfo;
//    }

    @Override
    public PageInfo<MemberReportDTO> getReport(MemberReportRequest req) {
        PageHelper.startPage(req.getPageNo(), req.getPageSize());
        List<MemberReportDTO> tmp = memBaseinfoMapperExt.getReport(req);
        PageInfo pageInfo = new PageInfo(tmp);
        return pageInfo;
    }

    @Override
    @DS("slave")
    public PageResult vipRecordList(VipRecordRequest req) {
        PageHelper.startPage(req.getPageNo(), req.getPageSize());
        Example example  = new Example(MemLevelRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName",req.getEmail());
        if (StringUtils.isNotBlank(req.getBeginTime())) {
            criteria.andGreaterThanOrEqualTo("createTime",req.getBeginTime() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndTime())) {
            criteria.andLessThanOrEqualTo("createTime",req.getEndTime() + " 23:59:59");
        }
        example.setOrderByClause("create_time desc");
        List<MemLevelRecord> tmp = memLevelRecordMapper.selectByExample(example);
        tmp.forEach(item->{
            if(item.getChangeType().equals(2)){
                item.setChangeAmount(new BigDecimal(0));
            }
        });
        PageInfo pageInfo = new PageInfo(tmp);
        return PageResult.getPageResult(req.getPageNo(),req.getPageSize(),Integer.valueOf(pageInfo.getTotal()+""),pageInfo.getList());
    }

    @Override
    @DS("slave")
    public PageInfo<MemGoldChangeDTON> getMemberGoldChange(MemGoldChangeRequest req) {
        PageHelper.startPage(req.getPageNo(), req.getPageSize());
        List<MemGoldChangeDTON> tmp = memGoldchangeMapperExt.getMemberGoldChange(req);
        PageInfo pageInfo = new PageInfo(tmp);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> register(UsersRequest req) {

        if (StringUtils.isEmpty(req.getEmail())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "账号为空");
        }
        if (StringUtils.isEmpty(req.getPassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_126.getCode(), "密码为空");
        }
//        if (StringUtils.isEmpty(req.getSmscode())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "短信验证码为空");
//        }

//        // 检查 密码 是否 过于简单
//        List<String> jPwd = CommonFunction.jiandanPwd();
//        if (jPwd.contains(req.getPassword())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "您设置的密码过于简单");
//        }

        MemLogin ml = memLoginService.findByEmailRegister(req.getEmail());
        if (ml != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_119.getCode(), "该账号已存在");
        }

        logger.info("参数：{}", JSON.toJSONString(req));
        /*SysShortmsg sysShortmsg = (SysShortmsg) redisTemplate.opsForHash().get(RedisKeys.LIVE_SMS_CODE_DETAIL,
                req.getTel() + RedisKeys.LIVE_JOINT_COLON + MsgTypeEnum.REGISTER.getValue());
        if (sysShortmsg == null && !"LIVE_MSG_CODE".equals(req.getSmscode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_107.getCode(), "无效验证码");
        }
        if (!req.getSmscode().equalsIgnoreCase(sysShortmsg.getMsgcode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "验证码错误");
        }*/

        // 插入用户
        MemBaseinfo newUser = new MemBaseinfo();
        newUser.setAccno(RandomUtil.uuid());
        newUser.setUniqueId(uniqueCodeService.getMemUniqueId());
        newUser.setEmail(req.getEmail());
        String dateStr = DateUtils.currentTimeStr();
        // newUser.setNickname(NameGeneratorUtil.generate() + dateStr.substring(dateStr.length() - 3));
        newUser.setFansnum(0L);
        newUser.setGoldnum(BigDecimal.ZERO);
        newUser.setRegisterdate(new Date());
        newUser.setIsDelete(false);
        newUser.setCreateUser(newUser.getAccno());
        newUser.setUpdateUser(newUser.getAccno());
        // 设置注册用户的邀请码
        newUser.setRecomcode(uniqueCodeService.getMemInviteCode());
        newUser.setLogintype(LoginUserTypeEnum.ORDINARY.getCode());
        newUser.setClintipadd(req.getClintipadd());
        newUser.setMemorgin(Constants.REGIST);
        newUser.setRegisterDev(req.getClientPhoneModel());
        newUser.setHeadimg(sysBusParamService.getRandomHeadImg());
        // 没有推荐人
        /*if (ObjectUtils.isEmpty(req.getInvitecode())) {
            newUser.setMemorgin(Constants.REGIST);
        }*/
        // 用户基本信息
        memBaseinfoService.insertSelective(newUser);

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
            throw new BusinessException(StatusCode.LIVE_ERROR_169.getCode(), "会员信用分初始化失败！");
        }
        asynRegister(req, newUser);
        // registerCachedThreadPool.execute(() -> asynRegister(req, newUser));
        //asynRegister(req);
        return new HashMap<String, Object>();

    }

    private void asynRegister(UsersRequest req, MemBaseinfo newUser) {
        // 建立推荐人关系
        inviteUserProcess(req, newUser);
        //初始化用户等级
        memLevelService.initMemLevel(newUser.getAccno());

        String registerIp = req.getClintipadd();
        String registerAdd = null;

        Map<String, String> map = null;
        try {
            map = HttpUtils.ipParse(req.getClintipadd());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                        registerAdd = StringUtils.isEmpty(country) ? "" : country;
                    }
                } else {
                    registerAdd = StringUtils.isEmpty(province) ? "" : province;
                }
            } else {
                registerAdd = StringUtils.isEmpty(city) ? "" : city;
            }
        }

        if (StringUtils.isNotBlank(registerAdd)) {
            registerIp = registerIp + "(" + registerAdd + ")";
        }
        newUser.setRegisterIp(registerIp);
        memBaseinfoService.updateRegisterIp(newUser.getAccno(), newUser.getRegisterIp());
    }

    /**
     * 根据邀请码进来的用户，建立推荐人关系
     *
     * @param req
     * @param newUser
     */
    private MemBaseinfo inviteUserProcess(UsersRequest req, MemBaseinfo newUser) {
        MemBaseinfo inviteUser = null;
        String refaccno = "ROOT";
        if (StringUtils.isNotBlank(req.getInvitecode())) {
            // 根据邀请码 获取 对应的人
            inviteUser = memBaseinfoService.getUserByInvitecode(req.getInvitecode());
            if (null != inviteUser && null != inviteUser.getIsDelete() && !inviteUser.getIsDelete()) {
                refaccno = inviteUser.getAccno();
            }
        }

        memBaseinfoService.updateMemorigin(newUser.getAccno(), Constants.RECOMMEND);
        // 插入推荐关系
        MemRelationship memRelationship = new MemRelationship();
        memRelationship.setRefaccno(refaccno);
        memRelationship.setAccno(newUser.getAccno());
        memRelationship.setCreateUser(newUser.getAccno());
        memRelationship.setUpdateUser(newUser.getAccno());
        memRelationship.setIsDelete(false);
        int relateFlag = memRelationshipService.insertSelectiveMemRelationship(memRelationship);
        if (relateFlag < 1) {
            logger.error("{} register 邀请码{} 已经存在邀请关系", req.getEmail(), req.getInvitecode());
        }
        return inviteUser;
    }

    @Override
    @DS("slave")
    public MemberCountResultDTO getMemberCount() {
        MemberCountResultDTO result = new MemberCountResultDTO();
        Date today = new Date();
        Date yesterday = DateUtils.addDays(today, -1);
        String date = DateUtils.getDateString(today);

        //今日新增各类会员分布
        List<MemberCountDTO> todaycount = memLevelMapperExt.getMemberCountLevel(date);

        //之前各类会员分布
        List<MemberCountDTO> befoycount = memLevelMapperExt.getMemberCountLevelBefor(date);
        // 计算vip会员总数
        Integer vipSum = 0;
        List<MemberCountDTO> list = new LinkedList<>();
        for (Integer i = 0; i < todaycount.size(); i++) {
            MemberCountDTO tmptoday = null;
            tmptoday = todaycount.get(i);
            tmptoday.setAdd(1);
            list.add(tmptoday);
            tmptoday = befoycount.get(i);

            vipSum += tmptoday.getMemnum();
            tmptoday.setAdd(0);
            list.add(befoycount.get(i));
        }
        result.setMemberCountDTO(list);
        result.setVipSum(vipSum);


        TimeReortDTO timeReortDTO = new TimeReortDTO();
        timeReortDTO.setJtBeginTime(DateUtils.dayBeginStr());
        timeReortDTO.setJtEnDTime(DateUtils.dayEndStr());
        timeReortDTO.setZtBeginTime(DateUtils.dayBeforeStratStr());
        timeReortDTO.setZtEnDTime(DateUtils.timeBeforeEndStr());
        timeReortDTO.setByBeginTime(DateUtils.date2Str(DateUtils.getBeginOfThisMonth()));
        timeReortDTO.setByEnDTime(DateUtils.date2Str(DateUtils.getEndOfThisMonth()));
        Map<String,Long> zcMap  = memBaseinfoMapperExt.findAllUsersDate(timeReortDTO);

        // 会员所有余额
        BigDecimal balance = memBaseinfoMapperExt.countAllUserAmount();
        MemberCountRegisterDTO memberCountRegisterDTO = new MemberCountRegisterDTO();
        memberCountRegisterDTO.setToday(zcMap.get("jrzc"));
        memberCountRegisterDTO.setYesterday(zcMap.get("zrzc"));
        memberCountRegisterDTO.setMonth(zcMap.get("byzc"));
        memberCountRegisterDTO.setAllNum(zcMap.get("zzc"));
        memberCountRegisterDTO.setBalance(balance);
        memberCountRegisterDTO.setAssets(balance);
        memberCountRegisterDTO.setOnlineNum(RedisBusinessUtil.countCheckInByDate(5));
        result.setMemberCountRegisterDTO(memberCountRegisterDTO);

        result.setMemberCountStatisticsDTOS(new LinkedList<>());
        return result;
    }

    @Override
    @DS("slave")
    public PageInfo<DayReportDTO> getDayReport(DayReportRequest req) {
        if (req.getStartTime() == null || req.getEndTime() == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_170.getCode(), "日期参数错误");
        }

        req.setStartTime(DateUtils.addDate("dd", 1, req.getStartTime()));
        req.setEndTime(DateUtils.addDate("dd", 1, req.getEndTime()));

        req.setStartTime(DateUtils.getStartOfDay(req.getStartTime()));
        req.setEndTime(DateUtils.getEndOfDay(req.getEndTime()));


        PageHelper.startPage(req.getPageNo(), req.getPageSize());
        List<DayReportDTO> tmp = memDailyReportMapper.getDayReport(req);
        List<DayReportDTO> result = new ArrayList<DayReportDTO>();
        if (req.getPageNo() == 1 && req.getEndTime().compareTo(new Date()) > 0) {
            result.add(getToDayReport(req.getMobileno()));
        }
        for (DayReportDTO d : tmp) {
            d.setCreateTime(DateUtils.addDays(d.getCreateTime(), -1));
            result.add(d);
        }
        PageInfo pageInfo = new PageInfo(result);
        return pageInfo;
    }

    private DayReportDTO getToDayReport(String mobileno) {
        DayReportDTO result = new DayReportDTO();
//        result.setSpread(new BigDecimal(0));
//        result.setBrokerage(new BigDecimal(0));
//        result.setPump(new BigDecimal(0));
        result.setBuyVip(new BigDecimal(0));
        result.setTaskAward(new BigDecimal(0));
        result.setWithdrawal(new BigDecimal(0));
        result.setRecharge(new BigDecimal(0));

        result.setCreateTime(new Date());
        // 获取发生账变的用户 accno
        List<String> accnos = memDailyReportMapper.getDayGlodChangeAccno(mobileno);
        accnos.forEach(accno -> {
            // 获取账变记录
            List<Map<String, Object>> list = memDailyReportMapper.getQuantityByChangetype(accno);
            if (CollectionUtils.isEmpty(list)) {
                return;
            }
            MemDailyReport memDailyReport = new MemDailyReport();
            for (Map<String, Object> paramMap : list) {
                GoldchangeEnum goldchangeEnum = GoldchangeEnum.valueOf(Integer.parseInt(paramMap.get("changetype").toString()));
                if (null == goldchangeEnum) {
                    return;
                }
                switch (goldchangeEnum) {
                    case RECHARGE:
                        result.setRecharge(result.getRecharge().add(new BigDecimal(paramMap.get("quantity").toString())));
                        //memDailyReport.setRecharge(new BigDecimal(paramMap.get("quantity").toString()));
                        break;
                    case WITHDRAWN:
                        result.setWithdrawal(result.getWithdrawal().add(new BigDecimal(paramMap.get("quantity").toString())));
                        //memDailyReport.setWithdrawal(new BigDecimal(paramMap.get("quantity").toString()));
                        break;
                    case TASK_AWARD:
                        result.setTaskAward(result.getTaskAward().add(new BigDecimal(paramMap.get("quantity").toString())));
                        // memDailyReport.(new BigDecimal(paramMap.get("quantity").toString()));
                        break;
                    case BUY_VIP:
                        result.setBuyVip(result.getBuyVip().add(new BigDecimal(paramMap.get("quantity").toString())));
                        // memDailyReport.setBuyVip(new BigDecimal(paramMap.get("quantity").toString()));
                        break;
//                    case TASK_PUMP:
//                        result.setPump(result.getPump().add(new BigDecimal(paramMap.get("quantity").toString())));
//                        // memDailyReport.setPump(new BigDecimal(paramMap.get("quantity").toString()));
//                        break;
//                    case BROKERAGE_LEVEL_1:
//                        result.setBrokerage(result.getBrokerage().add(new BigDecimal(paramMap.get("quantity").toString())));
//                        // memDailyReport.setBrokerage(new BigDecimal(paramMap.get("quantity").toString()));
//                        break;
//                    case BROKERAGE_LEVEL_2:
//                        result.setBrokerage(result.getBrokerage().add(new BigDecimal(paramMap.get("quantity").toString())));
//                        break;
//                    case BROKERAGE_LEVEL_3:
//                        result.setBrokerage(result.getBrokerage().add(new BigDecimal(paramMap.get("quantity").toString())));
//                        break;
//                    case INVITE_USERS:
//                        result.setSpread(result.getSpread().add(new BigDecimal(paramMap.get("quantity").toString())));
//                        // memDailyReport.setSpread(new BigDecimal(paramMap.get("quantity").toString()));
//                        break;
                }
            }
            result.setAccno(accno);
//            memDailyReport.setAccno(accno);
            //上级代理
            String higher = null;
            MemRelationship relation = memRelationshipService.findByAccno(accno);
            if (null != relation) {
                MemBaseinfo refUser = memBaseinfoService.getUserByAccno(relation.getRefaccno());
                if (null != refUser) {
                    higher = refUser.getEmail();
                }
            }
//            result.setHigherNo(higher);
            result.setCreateTime(new Date());
            //result.add(memDailyReport);
        });
        return result;
    }


    @Override
    public AppTeamResponse appTeamList(FundsRequest req, String accno) {
        if (req.getStartTime() != null && req.getStartTime() != "") {
            req.setStartTime(req.getStartTime() + " 00:00:00");
        }
        if (req.getEndTime() != null && req.getEndTime() != "") {
            req.setEndTime(req.getEndTime() + " 23:59:59");
        }
        AppTeamResponse teamResponse = new AppTeamResponse();
        Map<String, Object> recMap = new HashMap<>();
        Map<String, Object> cashMap = new HashMap<>();

//        List<String> accNoList = null;
//        if (CollectionUtil.isNotEmpty(accNoList)) {
//            List<Map<String, Object>> orderList = agentMapper.subOrderList(accNoList);
//            List<TeamBo> recMemNum = agentMapper.subRecMemNum(accNoList);
//            if (CollectionUtil.isNotEmpty(orderList)) {
//                for (Map<String, Object> order : orderList) {
//                    if (order.get("orderstatus").toString().equals("ord08")) {
//                        recMap = order;
//
//                    }
//                    if (order.get("orderstatus").toString().equals("ord12")) {
//                        cashMap = order;
//
//                    }
//                }
//            }
//            teamResponse.setTeamNum(accNoList.size());
//            teamResponse.setRecNum(recMemNum.size());
//            if (CollectionUtil.isNotEmpty(recMap)) {
//                teamResponse.setRecCount(Integer.parseInt(recMap.get("operNum").toString()));
//                teamResponse.setRecAmount(new BigDecimal(recMap.get("realamt").toString()));
//            }
//            if (CollectionUtil.isNotEmpty(cashMap)) {
//                teamResponse.setCashAmount(new BigDecimal(cashMap.get("realamt").toString()));
//            }
//            if (CollectionUtil.isNotEmpty(accNoList)) {
//                List<SubAndTaskDto> level1Task = agentMapper.subTaskList(accNoList);
////                if (CollectionUtil.isNotEmpty(level1Task)) {
////                    teamResponse.setSubOneList(level1Task);
////                    List<String> accNo2List = level1Task.stream().map(SubAndTaskDto::getAccno).collect(Collectors.toList());
////                    List<String> ids2 = agentMapper.subNum(accNo2List);
////                    if (CollectionUtil.isNotEmpty(ids2)) {
////                        List<SubAndTaskDto> level2Task = agentMapper.subTaskList(ids2);
////                        if (CollectionUtil.isNotEmpty(level2Task)) {
////                            teamResponse.setSubTwoList(level2Task);
////                            List<String> accNo3List = level2Task.stream().map(SubAndTaskDto::getAccno).collect(Collectors.toList());
////                            List<String> ids3 = agentMapper.subNum(accNo3List);
////                            if (CollectionUtil.isNotEmpty(ids2)) {
////                                List<SubAndTaskDto> level3Task = agentMapper.subTaskList(ids3);
////                                teamResponse.setSubThreeList(level3Task);
////                            }
////
////                        }
////                    }
////
////                }
//            }
//
//        }
        return teamResponse;
    }

    @Override
    public AppTeamResponse appTeamReport(AppTeamReportRequest req, LoginUser loginUser) {
        if (req.getStartTime() != null && req.getStartTime() != "") {
            req.setStartTime(req.getStartTime() + " 00:00:00");
        }
        if (req.getEndTime() != null && req.getEndTime() != "") {
            req.setEndTime(req.getEndTime() + " 23:59:59");
        }
        AppTeamResponse teamResponse = new AppTeamResponse();
        Map<String, Object> recMap = new HashMap<>();
        Map<String, Object> cashMap = new HashMap<>();
        List<String> levelAccList = new ArrayList<>();
        List<String> levelOneAccList = agentMapper.subNum(Arrays.asList(loginUser.getAccno()),"2022-04-11 00:00:00", "2022-09-11 00:00:00");
        List<String> levelTwoAccList = new LinkedList<>();
        List<String> levelThreeAccList = new LinkedList<>();
        if (CollectionUtil.isNotEmpty(levelOneAccList)) {
            levelTwoAccList = agentMapper.subNum(levelOneAccList, req.getStartTime(), req.getEndTime());
            if (CollectionUtil.isNotEmpty(levelTwoAccList)) {
                 levelThreeAccList = agentMapper.subNum(levelTwoAccList, req.getStartTime(), req.getEndTime());
            }
        }
        if (req.getLevel().equals(1)) {
            teamResponse.setTeamNum(levelOneAccList.size());
            levelAccList.addAll(levelOneAccList);
        } else if (req.getLevel().equals(2)) {
            teamResponse.setTeamNum(levelTwoAccList.size());
            levelAccList.addAll(levelTwoAccList);
        } else {
            teamResponse.setTeamNum(levelThreeAccList.size());
            levelAccList.addAll(levelThreeAccList);
        }
        if (CollectionUtil.isNotEmpty(levelAccList)) {
            List<Map<String, Object>> orderList = agentMapper.subTimeOrderList(levelAccList,req.getStartTime(), req.getEndTime());
            Integer recMemNum = agentMapper.subRecMemNum(levelAccList,req.getStartTime(), req.getEndTime());
            Integer taskNum = agentMapper.taskNum(levelAccList, req.getStartTime(), req.getEndTime());
            if (CollectionUtil.isNotEmpty(orderList)) {
                for (Map<String, Object> order : orderList) {
                    if (order.get("orderstatus").toString().equals("ord08")) {
                        recMap = order;

                    }
                    if (order.get("orderstatus").toString().equals("ord12")) {
                        cashMap = order;

                    }
                }
            }
            teamResponse.setTaskNum(taskNum);
            teamResponse.setRecNum(recMemNum);
            if (CollectionUtil.isNotEmpty(recMap)) {
                teamResponse.setRecCount(Integer.parseInt(recMap.get("operNum").toString()));
                teamResponse.setRecAmount(new BigDecimal(recMap.get("realamt").toString()));
            }
            if (CollectionUtil.isNotEmpty(cashMap)) {
                teamResponse.setCashAmount(new BigDecimal(cashMap.get("realamt").toString()));
            }
        }
        return teamResponse;
    }


    @Override
    public PageInfo<TeamReportDto> getTeamReport(DayReportRequest req) {
        if (req.getStartTime() == null || req.getEndTime() == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_170.getCode(), "日期参数错误");
        }
        req.setStartTime(DateUtils.addDate("dd", 1, req.getStartTime()));
        req.setEndTime(DateUtils.addDate("dd", 1, req.getEndTime()));
        req.setStartTime(DateUtils.getStartOfDay(req.getStartTime()));
        req.setEndTime(DateUtils.getEndOfDay(req.getEndTime()));
        List<MemRelationshipDTO> all = memDailyReportMapper.getMemRelationshipAll();

        PageHelper.startPage(req.getPageNo(), req.getPageSize());
        List<MemRelationshipDTO> pagedata;
        if (req.getMobileno() != null && req.getMobileno() != "") {
            pagedata = memDailyReportMapper.getMemRleationshipByAcclogin(req.getMobileno());
        } else {
            pagedata = memDailyReportMapper.getMemRelationship(req.getAccno());
        }
        PageInfo pageInfo = new PageInfo(pagedata);

        List<TeamReportDto> result = new ArrayList<TeamReportDto>();
        for (MemRelationshipDTO m : pagedata) {
            String accnos = GetAllAccno(m.getAccno(), all);
            String[] accnosarr = accnos.split(",");
            DayReportInfoRequest reachreq = new DayReportInfoRequest();
            reachreq.setPageNo(req.getPageNo());
            reachreq.setPageSize(req.getPageSize());
            reachreq.setAccno(req.getAccno());
            reachreq.setStartTime(req.getStartTime());
            reachreq.setEndTime(req.getEndTime());
            reachreq.setMobileno(accnosarr);
            TeamReportDto tmp = memDailyReportMapper.getTeamReportInfo(reachreq);


//            if (tmp == null) tmp = new TeamReportDto();
//            tmp.setMemberName(m.getMemberName());
//            tmp.setAccno(m.getAccno());
//            tmp.setHigherNo(m.getRefaccno());
//            tmp.setTeamNum(accnosarr.length);
//
//
//            if (tmp.getActivity() == null) tmp.setActivity(new BigDecimal(0));
//            if (tmp.getBrokerage() == null) tmp.setBrokerage(new BigDecimal(0));
//            if (tmp.getBuyVip() == null) tmp.setBuyVip(new BigDecimal(0));
//            if (tmp.getPump() == null) tmp.setPump(new BigDecimal(0));
//            if (tmp.getRebate() == null) tmp.setRebate(new BigDecimal(0));
//            if (tmp.getRecharge() == null) tmp.setRecharge(new BigDecimal(0));
//            if (tmp.getRevokeTask() == null) tmp.setRevokeTask(new BigDecimal(0));
//            if (tmp.getSpread() == null) tmp.setSpread(new BigDecimal(0));
//            if (tmp.getTaskAward() == null) tmp.setTaskAward(new BigDecimal(0));
//            if (tmp.getWithdrawal() == null) tmp.setWithdrawal(new BigDecimal(0));
//
//            if (req.getPageNo() == 1 && req.getEndTime().compareTo(new Date()) > 0) {
//                DayReportDTO todaydata = getToDayReportBuyArr(accnosarr);
//                if (todaydata != null) {
//                    tmp.setActivity(tmp.getActivity().add(todaydata.getActivity() == null ? new BigDecimal(0) : todaydata.getActivity()));
//                    tmp.setBrokerage(tmp.getBrokerage().add(todaydata.getBrokerage() == null ? new BigDecimal(0) : todaydata.getBrokerage()));
//                    tmp.setBuyVip(tmp.getBuyVip().add(todaydata.getBuyVip() == null ? new BigDecimal(0) : todaydata.getBuyVip()));
//                    tmp.setPump(tmp.getPump().add(todaydata.getPump() == null ? new BigDecimal(0) : todaydata.getPump()));
//                    tmp.setRebate(tmp.getRebate().add(todaydata.getRebate() == null ? new BigDecimal(0) : todaydata.getRebate()));
//                    tmp.setRecharge(tmp.getRecharge().add(todaydata.getRecharge() == null ? new BigDecimal(0) : todaydata.getRecharge()));
//                    tmp.setRevokeTask(tmp.getRevokeTask().add(todaydata.getRevokeTask() == null ? new BigDecimal(0) : todaydata.getRevokeTask()));
//                    tmp.setSpread(tmp.getSpread().add(todaydata.getSpread() == null ? new BigDecimal(0) : todaydata.getSpread()));
//                    tmp.setTaskAward(tmp.getTaskAward().add(todaydata.getTaskAward() == null ? new BigDecimal(0) : todaydata.getTaskAward()));
//                    tmp.setWithdrawal(tmp.getWithdrawal().add(todaydata.getWithdrawal() == null ? new BigDecimal(0) : todaydata.getWithdrawal()));
//                }
//            }
            result.add(tmp);
        }
        pageInfo.setList(result);
//        PageInfo  pageInfo = new PageInfo(result);
        return pageInfo;
    }

    private DayReportDTO getToDayReportBuyArr(String[] accnosarr) {
        DayReportDTO result = new DayReportDTO();
//        result.setSpread(new BigDecimal(0));
//        result.setBrokerage(new BigDecimal(0));
//        result.setPump(new BigDecimal(0));
        result.setBuyVip(new BigDecimal(0));
        result.setTaskAward(new BigDecimal(0));
        result.setWithdrawal(new BigDecimal(0));
        result.setRecharge(new BigDecimal(0));

        result.setCreateTime(new Date());
        // 获取发生账变的用户 accno
        List<String> accnos = new ArrayList<>();
        for (String accno : accnosarr) {
            accnos.add(accno);
        }
        accnos.forEach(accno -> {
            // 获取账变记录
            List<Map<String, Object>> list = memDailyReportMapper.getQuantityByChangetype(accno);
            if (CollectionUtils.isEmpty(list)) {
                return;
            }
            MemDailyReport memDailyReport = new MemDailyReport();
            for (Map<String, Object> paramMap : list) {
                GoldchangeEnum goldchangeEnum = GoldchangeEnum.valueOf(Integer.parseInt(paramMap.get("changetype").toString()));
                if (null == goldchangeEnum) {
                    return;
                }
                switch (goldchangeEnum) {
                    case RECHARGE:
                        result.setRecharge(result.getRecharge().add(new BigDecimal(paramMap.get("quantity").toString())));
                        //memDailyReport.setRecharge(new BigDecimal(paramMap.get("quantity").toString()));
                        break;
//                    case WITHDRAWN:
//                        result.setWithdrawal(result.getWithdrawal().add(new BigDecimal(paramMap.get("quantity").toString())));
//                        //memDailyReport.setWithdrawal(new BigDecimal(paramMap.get("quantity").toString()));
//                        break;
//                    case TASK_AWARD:
//                        result.setTaskAward(result.getTaskAward().add(new BigDecimal(paramMap.get("quantity").toString())));
//                        // memDailyReport.(new BigDecimal(paramMap.get("quantity").toString()));
//                        break;
//                    case BUY_VIP:
//                        result.setBuyVip(result.getBuyVip().add(new BigDecimal(paramMap.get("quantity").toString())));
//                        // memDailyReport.setBuyVip(new BigDecimal(paramMap.get("quantity").toString()));
//                        break;
//                    case TASK_PUMP:
//                        result.setPump(result.getPump().add(new BigDecimal(paramMap.get("quantity").toString())));
//                        // memDailyReport.setPump(new BigDecimal(paramMap.get("quantity").toString()));
//                        break;
//                    case BROKERAGE_LEVEL_1:
//                        result.setBrokerage(result.getBrokerage().add(new BigDecimal(paramMap.get("quantity").toString())));
//                        // memDailyReport.setBrokerage(new BigDecimal(paramMap.get("quantity").toString()));
//                        break;
//                    case BROKERAGE_LEVEL_2:
//                        result.setBrokerage(result.getBrokerage().add(new BigDecimal(paramMap.get("quantity").toString())));
//                        break;
//                    case BROKERAGE_LEVEL_3:
//                        result.setBrokerage(result.getBrokerage().add(new BigDecimal(paramMap.get("quantity").toString())));
//                        break;
//                    case INVITE_USERS:
//                        result.setSpread(result.getSpread().add(new BigDecimal(paramMap.get("quantity").toString())));
                        // memDailyReport.setSpread(new BigDecimal(paramMap.get("quantity").toString()));
//                        break;
                }
            }
            result.setAccno(accno);
//            memDailyReport.setAccno(accno);
            //上级代理
            String higher = null;
            MemRelationship relation = memRelationshipService.findByAccno(accno);
            if (null != relation) {
                MemBaseinfo refUser = memBaseinfoService.getUserByAccno(relation.getRefaccno());
                if (null != refUser) {
                    higher = refUser.getEmail();
                }
            }
//            result.setHigherNo(higher);
            result.setCreateTime(new Date());
            //result.add(memDailyReport);
        });
        return result;
    }

    private String GetAllAccno(String accno, List<MemRelationshipDTO> all) {
        String result = accno + ",";
        List<MemRelationship> tmp = all.stream().filter((MemRelationship b) -> b.getRefaccno().equals(accno)).collect(Collectors.toList());
        if (tmp != null && tmp.size() > 0) {
            for (MemRelationship m : tmp) {
                result += GetAllAccno(m.getAccno(), all);
            }
        }
        return result;
    }

    @Override
    public MemberReportDto getMemberReportDto(String accno) {
        return memDailyReportMapper.getMemberReportDto(accno);
    }


}
