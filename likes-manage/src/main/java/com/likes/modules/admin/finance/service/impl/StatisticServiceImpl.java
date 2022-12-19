package com.likes.modules.admin.finance.service.impl;

import com.likes.common.constant.Constants;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.StatisticRequest;
import com.likes.common.model.vo.member.UserReportDetailVO;
import com.likes.common.model.vo.member.UserReportVO;
import com.likes.common.model.vo.money.AccountSummaryVO;
import com.likes.common.model.vo.money.MemGoldchangeVO;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemBaseinfoExample;
import com.likes.common.mybatis.entity.MemGoldchange;
import com.likes.common.mybatis.entity.MemGoldchangeExample;
import com.likes.common.mybatis.mapperext.member.MemLoginMapperExt;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.util.DateUtils;
import com.likes.common.util.StringUtils;
import com.likes.common.util.cache.StatisticChangeTypeUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.finance.service.StatisticService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private MemBaseinfoService memBaseinfoService;
    @Autowired
    private MemGoldchangeService memGoldchangeService;
    @Autowired
    private MemLoginMapperExt memLoginMapperExt;

    /**
     * 后台统计-帐变列表-列表页
     */
    @Override
    public PageResult<List<MemGoldchangeVO>> pageGoldChange(StatisticRequest req, PageBounds page) {
        calStartEndDate(req);
        return getMemGoldchangeList(req, page);
    }

    /**
     * 计算开始时间，结束时间
     */
    private void calStartEndDate(StatisticRequest req) {
        Integer dateType = req.getDateType();
        String startTime = req.getStartTime();
        String endTime = req.getEndTime();
        if (null != dateType) {
            Date date = new Date();
            if (dateType.equals(Constants.DEFAULT_ONE)) {
                // 今天
                String dateStr = DateUtils.formatDate(date, "yyyy-MM-dd");
                startTime = dateStr + DateUtils.TIMEZERO_BLANK;
                endTime = dateStr + DateUtils.TIMEOVER_BLANK;
            } else if (dateType.equals(Constants.DEFAULT_TWO)) {
                // 昨天
                String dateStr = DateUtils.formatDate(DateUtils.addDate("dd", -1, date), "yyyy-MM-dd");
                startTime = dateStr + DateUtils.TIMEZERO_BLANK;
                endTime = dateStr + DateUtils.TIMEOVER_BLANK;
            } else if (dateType.equals(Constants.DEFAULT_THREE)) {
                // 本周
                String dateStr_begin = DateUtils.formatDate(DateUtils.getMondayOfThisWeek(), "yyyy-MM-dd");
                startTime = dateStr_begin + DateUtils.TIMEZERO_BLANK;
//                String dateStr_end = DateUtils.formatDate(DateUtils.getSundayOfThisWeek(), "yyyy-MM-dd");
                String dateStr_end = DateUtils.formatDate(date, "yyyy-MM-dd");
                if (dateStr_end.equals(dateStr_begin)) {
                    // 周一  设置startTime等于endTime，统计值（不含今天）为0
                    endTime = dateStr_end + DateUtils.TIMEZERO_BLANK;
                } else {
                    // 周二到周日  统计值（不含今天）
                    dateStr_end = DateUtils.formatDate(DateUtils.addDate("dd", -1, date), "yyyy-MM-dd");
                    endTime = dateStr_end + DateUtils.TIMEOVER_BLANK;
                }
            } else if (dateType.equals(Constants.DEFAULT_FOUR)) {
                // 上周
                String dateStr_begin = DateUtils.formatDate(DateUtils.addDate("dd", -7, DateUtils.getMondayOfThisWeek()), "yyyy-MM-dd");
                startTime = dateStr_begin + DateUtils.TIMEZERO_BLANK;
                String dateStr_end = DateUtils.formatDate(DateUtils.addDate("dd", -7, DateUtils.getSundayOfThisWeek()), "yyyy-MM-dd");
                endTime = dateStr_end + DateUtils.TIMEOVER_BLANK;
            } else if (dateType.equals(Constants.DEFAULT_FIVE)) {
                // 上月
                String dateStr_begin = DateUtils.formatDate(DateUtils.getFirstOfLastMonth(), "yyyy-MM-dd");
                startTime = dateStr_begin + DateUtils.TIMEZERO_BLANK;
                String dateStr_end = DateUtils.formatDate(DateUtils.getLastOfLastMonth(), "yyyy-MM-dd");
                endTime = dateStr_end + DateUtils.TIMEOVER_BLANK;
            } else if (dateType.equals(Constants.DEFAULT_SEVEN)) {
                // 本月
                String dateStr_begin = DateUtils.formatDate(DateUtils.addDate("dd", 1, DateUtils.getLastOfLastMonth()), "yyyy-MM-dd");
                startTime = dateStr_begin + DateUtils.TIMEZERO_BLANK;
                String dateStr_end = DateUtils.formatDate(date, "yyyy-MM-dd");
                if (dateStr_end.equals(dateStr_begin)) {
                    // 本月首日  设置startTime等于endTime，统计值（不含今天）为0
                    endTime = dateStr_end + DateUtils.TIMEZERO_BLANK;
                } else {
                    // 非本月首日  统计值（不含今天）
                    dateStr_end = DateUtils.formatDate(DateUtils.addDate("dd", -1, date), "yyyy-MM-dd");
                    endTime = dateStr_end + DateUtils.TIMEOVER_BLANK;
                }
            } else if (dateType.equals(Constants.DEFAULT_SIX)) {
                // 全部
                startTime = null;
                endTime = null;
            } else {
                // 日期类型不正确，直接显示今天的
                startTime = DateUtils.dayBeginStr();
                endTime = DateUtils.dayEndStr();
            }
        } else {
            if (null == startTime) {
                startTime = DateUtils.dayBeginStr();
            }
            if (null == endTime) {
                endTime = DateUtils.dayEndStr();
            }
        }
        req.setStartTime(startTime);
        req.setEndTime(endTime);
    }

    /**
     * 从数据库查询帐变
     */
    private PageResult<List<MemGoldchangeVO>> getMemGoldchangeList(StatisticRequest req, PageBounds page) {
        String startTime = req.getStartTime();
        String endTime = req.getEndTime();
        String type = req.getType();
        String nickname = req.getNickname();
        String uniqueId = req.getUniqueId();

        String accno = null;
        if (!(StringUtils.isBlank(nickname) && StringUtils.isBlank(uniqueId))) {
            accno = getAccno(nickname, uniqueId);
            if (null == accno) {
                return PageResult.getPageResult(page.getPageNo(), page.getPageSize(), 0);
            }
        }
        List<Integer> typeList = StringUtils.splitIntList(type);
        MemGoldchangeExample example = new MemGoldchangeExample();
        MemGoldchangeExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(accno)) {
            criteria.andAccnoEqualTo(accno);
        }

        if (typeList != null && typeList.size() == 1) {
            criteria.andChangetypeEqualTo(typeList.get(0));
        } else if (typeList != null && typeList.size() > 1) {
            criteria.andChangetypeIn(typeList);
        }

        if (org.apache.commons.lang3.StringUtils.isNotBlank(req.getStartTime())) {
            criteria.andCreateTimeGreaterThanOrEqualTo(DateUtils.parseDate(startTime));
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(req.getEndTime())) {
            criteria.andCreateTimeLessThan(DateUtils.parseDate(endTime));
        }

        Integer count = memGoldchangeService.countByExample(example);
        if (count > 0) {
            example.setOffset((page.getPageNo() - 1) * page.getPageSize());
            example.setLimit(page.getPageSize());
            example.setOrderByClause("create_time DESC");
            List<MemGoldchange> balanceChangeVOList = memGoldchangeService.selectByExample(example);
            List<MemGoldchangeVO> list = toMemGoldchangeVO(balanceChangeVOList);
            return PageResult.getPageResult(page.getPageNo(), page.getPageSize(), count, list);
        }
        return PageResult.getPageResult(page.getPageNo(), page.getPageSize(), 0);
    }

    /**
     * 根据昵称和手机号码，获取accno
     */
    private String getAccno(String nickname, String uniqueId) {
        String accno = null;
        if (StringUtils.isNotBlank(nickname) && StringUtils.isNotBlank(uniqueId)) {
            MemBaseinfoExample memExampler = new MemBaseinfoExample();
            MemBaseinfoExample.Criteria criteria = memExampler.createCriteria();
            criteria.andNicknameEqualTo(nickname);
            criteria.andUniqueIdEqualTo(uniqueId);
            MemBaseinfo member = memBaseinfoService.selectOneByExample(memExampler);
            if (null != member) {
                accno = member.getAccno();
            }
        } else if (StringUtils.isNotBlank(nickname)) {
            MemBaseinfoExample memExampler = new MemBaseinfoExample();
            MemBaseinfoExample.Criteria criteria = memExampler.createCriteria();
            criteria.andNicknameEqualTo(nickname);
            MemBaseinfo member = memBaseinfoService.selectOneByExample(memExampler);
            if (null != member) {
                accno = member.getAccno();
            }
        } else if (StringUtils.isNotBlank(uniqueId)) {
            MemBaseinfoExample memExampler = new MemBaseinfoExample();
            MemBaseinfoExample.Criteria criteria = memExampler.createCriteria();
            criteria.andUniqueIdEqualTo(uniqueId);
            MemBaseinfo member = memBaseinfoService.selectOneByExample(memExampler);
            if (null != member) {
                accno = member.getAccno();
            }
        }
        return accno;
    }

    /**
     * MemGoldchange 转 MemGoldchangeVO(补充昵称、手机号码、帐变类型)
     */
    private List<MemGoldchangeVO> toMemGoldchangeVO(List<MemGoldchange> goldchangeList) {
        List<MemGoldchangeVO> list = new ArrayList<MemGoldchangeVO>();
        if (goldchangeList != null && goldchangeList.size() > 0) {
            for (MemGoldchange memGoldchange : goldchangeList) {
                MemGoldchangeVO vo = new MemGoldchangeVO();
                BeanUtils.copyProperties(memGoldchange, vo);

                MemBaseinfo mem = memBaseinfoService.getUserByAccno(memGoldchange.getAccno());
                String nickName = mem == null ? "" : mem.getNickname();
                String uniqueId = mem == null ? "" : mem.getUniqueId();
                if (memGoldchange.getChangetype() != null) {
                    vo.setChangeTypeName(GoldchangeEnum.getNameByValue(memGoldchange.getChangetype()));
                    vo.setNickname(nickName);
                    vo.setUniqueId(uniqueId);
                    list.add(vo);
                }
            }
        }
        return list;
    }

    /**
     * 后台统计-帐变列表-总数
     */
    @Override
    public BigDecimal tatolGoldChange(StatisticRequest req) {
        calStartEndDate(req);
        String startTime = req.getStartTime();
        String endTime = req.getEndTime();
        String type = req.getType();
        String nickname = req.getNickname();
        String uniqueId = req.getUniqueId();

        String accno = null;
        if (!(StringUtils.isBlank(nickname) && StringUtils.isBlank(uniqueId))) {
            accno = getAccno(nickname, uniqueId);
            if (null == accno) {
                return BigDecimal.ZERO;
            }
        }
        List<Integer> typeList = StringUtils.splitIntList(type);
        BigDecimal total = memGoldchangeService.tatolGoldchange(null, accno, typeList, startTime, endTime);
        if (total == null) {
            total = new BigDecimal("0");
        }
        return total;
    }

    /**
     * 后台统计-帐变列表-帐变类型
     */
    @Override
    public Map listChangeType() {
        return StatisticChangeTypeUtils.getListChangeTypeMap();
    }

    /**
     * 后台统计-出入账目汇总
     */
    @Override
    public AccountSummaryVO getAccountSummary(StatisticRequest req) {
        AccountSummaryVO accountSummaryVo = null;
        Integer dateType = req.getDateType();
        Date date = new Date();

        // redis keys
        String redisKeyYesterday = "";
        String redisKeyThisWeekNoToday = "";
        String redisKeyLastWeek = "";
        String redisKeyLastMonth = "";
        String redisKeyThisMonthNoToday = "";

        // redis expire time 单位秒
        Long expireTimeYesterday = 0L;
        Long expireTimeThisWeekNoToday = 0L;
        Long expireTimeLastWeek = 0L;
        Long expireTimeLastMonth = 0L;
        Long expireTimeThisMonthNoToday = 0L;

        // 从缓存获取
        if (null != dateType) {
            if (dateType.equals(Constants.DEFAULT_ONE)) {
                // 今天
            } else if (dateType.equals(Constants.DEFAULT_TWO)) {
                // 昨天
                redisKeyYesterday = RedisKeys.ACCOUNT_SUMMARY_VO + "yesterday_" + DateUtils.formatDate(DateUtils.addDate("dd", -1, date), "yyyy-MM-dd");
                expireTimeYesterday = (DateUtils.todayEndTime().getTime() - date.getTime()) / 1000;
                accountSummaryVo = RedisBusinessUtil.get(redisKeyYesterday);
            } else if (dateType.equals(Constants.DEFAULT_THREE)) {
                // 本周
                redisKeyThisWeekNoToday = RedisKeys.ACCOUNT_SUMMARY_VO + "this_week_no_today_" + DateUtils.formatDate(DateUtils.getMondayOfThisWeek(), "yyyy-MM-dd");
                expireTimeThisWeekNoToday = (DateUtils.todayEndTime().getTime() - date.getTime()) / 1000;
                accountSummaryVo = RedisBusinessUtil.get(redisKeyThisWeekNoToday);
                if (null != accountSummaryVo) {
                    return addAccountSummaryToday(req, accountSummaryVo);
                }
            } else if (dateType.equals(Constants.DEFAULT_FOUR)) {
                // 上周
                redisKeyLastWeek = RedisKeys.ACCOUNT_SUMMARY_VO + "last_week_" + DateUtils.formatDate(DateUtils.addDate("dd", -7, DateUtils.getMondayOfThisWeek()), "yyyy-MM-dd");
                String thisWeekEndTime = DateUtils.formatDate(DateUtils.getSundayOfThisWeek(), "yyyy-MM-dd") + DateUtils.TIMEOVER_BLANK;
                expireTimeLastWeek = (DateUtils.parseDate(thisWeekEndTime).getTime() - date.getTime()) / 1000;
                accountSummaryVo = RedisBusinessUtil.get(redisKeyLastWeek);
            } else if (dateType.equals(Constants.DEFAULT_FIVE)) {
                // 上月
                redisKeyLastMonth = RedisKeys.ACCOUNT_SUMMARY_VO + "last_month_" + DateUtils.formatDate(DateUtils.getFirstOfLastMonth(), "yyyy-MM");
                String thisMonthEndTime = DateUtils.formatDate(DateUtils.getLastOfThisMonth(), "yyyy-MM-dd") + DateUtils.TIMEOVER_BLANK;
                expireTimeLastMonth = (DateUtils.parseDate(thisMonthEndTime).getTime() - date.getTime()) / 1000;
                accountSummaryVo = RedisBusinessUtil.get(redisKeyLastMonth);
            } else if (dateType.equals(Constants.DEFAULT_SEVEN)) {
                // 本月
                redisKeyThisMonthNoToday = RedisKeys.ACCOUNT_SUMMARY_VO + "this_month_no_today_" + DateUtils.formatDate(DateUtils.addDate("dd", 1, DateUtils.getLastOfLastMonth()), "yyyy-MM");
                expireTimeThisMonthNoToday = (DateUtils.todayEndTime().getTime() - date.getTime()) / 1000;
                accountSummaryVo = RedisBusinessUtil.get(redisKeyThisMonthNoToday);
                if (null != accountSummaryVo) {
                    return addAccountSummaryToday(req, accountSummaryVo);
                }
            } else if (dateType.equals(Constants.DEFAULT_SIX)) {
                // 全部
            }
            if (null != accountSummaryVo) {
                return accountSummaryVo;
            }
        }

        // 计算起止时间
        calStartEndDate(req);

        accountSummaryVo = getAccountSummaryByTime(req.getStartTime(), req.getEndTime());

        // 设置缓存
        if (null != dateType) {
            if (dateType.equals(Constants.DEFAULT_ONE)) {
                // 今天
            } else if (dateType.equals(Constants.DEFAULT_TWO)) {
                // 昨天
                if (null != accountSummaryVo) {
                    RedisBusinessUtil.set(redisKeyYesterday, accountSummaryVo, expireTimeYesterday, TimeUnit.SECONDS);
                }
            } else if (dateType.equals(Constants.DEFAULT_THREE)) {
                // 本周
                if (null == accountSummaryVo) {
                    return new AccountSummaryVO();
                }
                RedisBusinessUtil.set(redisKeyThisWeekNoToday, accountSummaryVo, expireTimeThisWeekNoToday, TimeUnit.SECONDS);
                AccountSummaryVO accountSummaryVoAddToday = addAccountSummaryToday(req, accountSummaryVo);
                if (null == accountSummaryVoAddToday) {
                    return new AccountSummaryVO();
                }
                return accountSummaryVoAddToday;
            } else if (dateType.equals(Constants.DEFAULT_FOUR)) {
                // 上周
                if (null != accountSummaryVo) {
                    RedisBusinessUtil.set(redisKeyLastWeek, accountSummaryVo, expireTimeLastWeek, TimeUnit.SECONDS);
                }
            } else if (dateType.equals(Constants.DEFAULT_FIVE)) {
                // 上月
                if (null != accountSummaryVo) {
                    RedisBusinessUtil.set(redisKeyLastMonth, accountSummaryVo, expireTimeLastMonth, TimeUnit.SECONDS);
                }
            } else if (dateType.equals(Constants.DEFAULT_SEVEN)) {
                // 本月
                if (null == accountSummaryVo) {
                    return new AccountSummaryVO();
                }
                RedisBusinessUtil.set(redisKeyThisMonthNoToday, accountSummaryVo, expireTimeThisMonthNoToday, TimeUnit.SECONDS);
                AccountSummaryVO accountSummaryVoAddToday = addAccountSummaryToday(req, accountSummaryVo);
                if (null == accountSummaryVoAddToday) {
                    return new AccountSummaryVO();
                }
                return accountSummaryVoAddToday;
            } else if (dateType.equals(Constants.DEFAULT_SIX)) {
                // 全部
            }
        }

        if (null != accountSummaryVo) {
            return accountSummaryVo;
        } else {
            return new AccountSummaryVO();
        }
    }

    /**
     * 后台统计-出入账目汇总  加上今天的
     */
    public AccountSummaryVO addAccountSummaryToday(StatisticRequest req, AccountSummaryVO accountSummaryVoNoToday) {
        AccountSummaryVO accountSummaryVo = getAccountSummaryByTime(DateUtils.dayBeginStr(), DateUtils.dayEndStr());

        accountSummaryVo.setIncomeRecharge(accountSummaryVo.getIncomeRecharge().add(accountSummaryVoNoToday.getIncomeRecharge()));
        accountSummaryVo.setIncomeRechargeCompany(accountSummaryVo.getIncomeRechargeCompany().add(accountSummaryVoNoToday.getIncomeRechargeCompany()));
        accountSummaryVo.setIncomeAgency(accountSummaryVo.getIncomeAgency().add(accountSummaryVoNoToday.getIncomeAgency()));
        accountSummaryVo.setIncomeActivity(accountSummaryVo.getIncomeActivity().add(accountSummaryVoNoToday.getIncomeActivity()));
        accountSummaryVo.setIncomeReturnWater(accountSummaryVo.getIncomeReturnWater().add(accountSummaryVoNoToday.getIncomeReturnWater()));
        accountSummaryVo.setIncomeAbnormal(accountSummaryVo.getIncomeAbnormal().add(accountSummaryVoNoToday.getIncomeAbnormal()));
        accountSummaryVo.setOutgoMember(accountSummaryVo.getOutgoMember().add(accountSummaryVoNoToday.getOutgoMember()));
        accountSummaryVo.setOutgoFamilyGift(accountSummaryVo.getOutgoFamilyGift().add(accountSummaryVoNoToday.getOutgoFamilyGift()));
        accountSummaryVo.setOutgoFamilyBet(accountSummaryVo.getOutgoFamilyBet().add(accountSummaryVoNoToday.getOutgoFamilyBet()));
        accountSummaryVo.setOutgoAbnormal(accountSummaryVo.getOutgoAbnormal().add(accountSummaryVoNoToday.getOutgoAbnormal()));
        return accountSummaryVo;
    }

    /**
     * 后台统计-出入账目汇总  根据起止时间
     */
    public AccountSummaryVO getAccountSummaryByTime(String startTime, String endTime) {

        // 后台统计-出入账目汇总
        List<Map<String, Object>> incomeMap = memGoldchangeService.statisticsAllType(startTime, endTime);
        // 后台统计-出入账目汇总  充值：1在线支付 2线下支付
        List<Map<String, Object>> incomeMapIncomeRecharge = memGoldchangeService.statisticsIncomeRecharge(startTime, endTime);
        Map<String, Object> dataMap = new HashMap<>();
        for (Map<String, Object> map : incomeMap) {
            String changeType = String.valueOf(map.get("changetype"));
            String money = String.valueOf(map.get("money"));
            dataMap.put(changeType, money);
        }
        for (Map<String, Object> map : incomeMapIncomeRecharge) {
            // 充值：1在线支付 2线下支付
            String orderType = String.valueOf(map.get("ordertype"));
            String money = String.valueOf(map.get("money"));
            if ("1".equals(orderType)) {
                orderType = "RECHARGE_ONLINE";
            }
            if ("2".equals(orderType)) {
                orderType = "RECHARGE_COMPANY";
            }
            if ("RECHARGE_ONLINE".equals(orderType) || "RECHARGE_COMPANY".equals(orderType)) {
                dataMap.put(orderType, money);
            }
        }

        AccountSummaryVO accountSummaryVO = new AccountSummaryVO();
        setIncome(accountSummaryVO, dataMap);
        setOutgo(accountSummaryVO, dataMap);
        return accountSummaryVO;
    }

    /**
     * 后台统计-出入账目汇总-会员账号（入款）
     */
    private void setIncome(AccountSummaryVO vo, Map<String, Object> dataMap) {

        //充值：充值 线上入款
        Object recharge = dataMap.get("RECHARGE_ONLINE");
        vo.setIncomeRecharge(new BigDecimal(null == recharge ? "0" : recharge.toString()));

        //充值：充值 公司入款
        Object rechargeCompany = dataMap.get("RECHARGE_COMPANY");
        vo.setIncomeRechargeCompany(new BigDecimal(null == rechargeCompany ? "0" : rechargeCompany.toString()));

        //充值：充值 代理充值
        Object rechargeAgency = dataMap.get(String.valueOf(GoldchangeEnum.REPAY_INCOME_ORDER.getValue()));
        vo.setIncomeAgency(new BigDecimal(null == rechargeAgency ? "0" : rechargeAgency.toString()));

        //活动：充值附值，邀请用户，发视频奖励，赠送，签到奖励，代理结算
        Object rechargeBonusO = dataMap.get(String.valueOf(GoldchangeEnum.RECHARGE_BONUS.getValue()));
        Object inviteUsersO = dataMap.get(String.valueOf(GoldchangeEnum.INVITE_USERS.getValue()));
        Object sendVideoRewardsO = dataMap.get(String.valueOf(GoldchangeEnum.SEND_VIDEO_REWARDS.getValue()));
        Object deliverO = dataMap.get(String.valueOf(GoldchangeEnum.DELIVER.getValue()));
        Object attendanceAwardO = dataMap.get(String.valueOf(GoldchangeEnum.ATTENDANCE_AWARD.getValue()));
        Object agencySettleO = dataMap.get(String.valueOf(GoldchangeEnum.AGENCY_SETTLE.getValue()));

        BigDecimal rechargeBonus = (new BigDecimal(null == rechargeBonusO ? "0" : rechargeBonusO.toString()));
        BigDecimal inviteUsers = (new BigDecimal(null == inviteUsersO ? "0" : inviteUsersO.toString()));
        BigDecimal sendVideoRewards = (new BigDecimal(null == sendVideoRewardsO ? "0" : sendVideoRewardsO.toString()));
        BigDecimal deliver = (new BigDecimal(null == deliverO ? "0" : deliverO.toString()));
        BigDecimal attendanceAward = (new BigDecimal(null == attendanceAwardO ? "0" : attendanceAwardO.toString()));
        BigDecimal agencySettle = (new BigDecimal(null == agencySettleO ? "0" : agencySettleO.toString()));
        BigDecimal activity = rechargeBonus.add(inviteUsers).add(sendVideoRewards).add(deliver).add(attendanceAward).add(agencySettle);
        vo.setIncomeActivity(activity);

        //返水：彩种返水、AG返水、开元返水、电竞返水、AE返水
        Object lotteryO = dataMap.get(String.valueOf(GoldchangeEnum.LOTTERY_RETURN_WATER.getValue()));
        Object agO = dataMap.get(String.valueOf(GoldchangeEnum.AG_RETURN_WATER.getValue()));
        Object kyO = dataMap.get(String.valueOf(GoldchangeEnum.KY_RETURN_WATER.getValue()));
        Object esO = dataMap.get(String.valueOf(GoldchangeEnum.ES_RETURN_WATER.getValue()));
        Object aeO = dataMap.get(String.valueOf(GoldchangeEnum.AE_RETURN_WATER.getValue()));
        BigDecimal lottery = (new BigDecimal(null == lotteryO ? "0" : lotteryO.toString()));
        BigDecimal ag = (new BigDecimal(null == agO ? "0" : agO.toString()));
        BigDecimal ky = (new BigDecimal(null == kyO ? "0" : kyO.toString()));
        BigDecimal es = (new BigDecimal(null == esO ? "0" : esO.toString()));
        BigDecimal ae = (new BigDecimal(null == aeO ? "0" : aeO.toString()));
        BigDecimal returnWater = lottery.add(ag).add(ky).add(es).add(ae);
        vo.setIncomeReturnWater(returnWater);

        //异常补偿入款：手动入款
        Object abnormalO = dataMap.get(String.valueOf(GoldchangeEnum.MANUALLY_INCOME_MONEY.getValue()));
        BigDecimal abnormal = (new BigDecimal(null == abnormalO ? "0" : abnormalO.toString()));
        vo.setIncomeAbnormal(abnormal);
    }

    /**
     * 后台统计-出入账目汇总-公司平台（出款）
     */
    private void setOutgo(AccountSummaryVO vo, Map<String, Object> dataMap) {
        //会员出款：已提现
        Object withdrawnO = dataMap.get(String.valueOf(GoldchangeEnum.WITHDRAWN.getValue()));
        BigDecimal withdrawn = (new BigDecimal(null == withdrawnO ? "0" : withdrawnO.toString()));
        vo.setOutgoMember(withdrawn);

        //家族礼物分成：家族礼物分成
        Object giftO = dataMap.get(String.valueOf(GoldchangeEnum.FAMILY_GIFT_DIVIDED.getValue()));
        BigDecimal gift = (new BigDecimal(null == giftO ? "0" : giftO.toString()));
        vo.setOutgoFamilyGift(gift);

        //家族投注分成：家族投注分成
        Object betO = dataMap.get(String.valueOf(GoldchangeEnum.FAMILY_BET_DIVIDED.getValue()));
        BigDecimal bet = (new BigDecimal(null == betO ? "0" : betO.toString()));
        vo.setOutgoFamilyBet(bet);

        //异常补偿出款：手动出款
        Object abnormalO = dataMap.get(String.valueOf(GoldchangeEnum.MANUALLY_OUTGO_MONEY.getValue()));
        BigDecimal abnormal = (new BigDecimal(null == abnormalO ? "0" : abnormalO.toString()));
        vo.setOutgoAbnormal(abnormal);
    }

    /**
     * 会员报表
     */
    @Override
    public Page<UserReportVO> userReport(String nickname, RowBounds rowBounds) {
        return memLoginMapperExt.userReportList(nickname, rowBounds);
    }

    @Override
    public Map<String, Object> userReportDetail(StatisticRequest req, PageBounds page) {
        String startTime = req.getStartTime();
        String endTime = req.getEndTime();
        String type = req.getType();
        String accno = req.getAccno();

        if (StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
            // 60天
            Date date = new Date();
            String dateStr = DateUtils.formatDate(DateUtils.addDate("dd", -60, date), "yyyy-MM-dd");
            startTime = dateStr + DateUtils.TIMEZERO_BLANK;
            endTime = DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss");
        }

        List<Integer> typeList = StringUtils.splitIntList(type);
        MemGoldchangeExample example = new MemGoldchangeExample();
        MemGoldchangeExample.Criteria criteria = example.createCriteria();
        criteria.andAccnoEqualTo(accno);
        if (typeList != null && typeList.size() > 0) {
            criteria.andChangetypeIn(typeList);
        } else {
            criteria.andChangetypeIn(StatisticChangeTypeUtils.getChangeTypeList());
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(startTime)) {
            criteria.andCreateTimeGreaterThanOrEqualTo(DateUtils.parseDate(startTime));
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(endTime)) {
            criteria.andCreateTimeLessThan(DateUtils.parseDate(endTime));
        }

        Integer count = memGoldchangeService.countByExample(example);
        if (count > 0) {
            example.setOffset((page.getPageNo() - 1) * page.getPageSize());
            example.setLimit(page.getPageSize());
            example.setOrderByClause("goldchangid DESC");
            List<MemGoldchange> balanceChangeVOList = memGoldchangeService.selectByExample(example);
            Map<String, Object> map = toUserReportDetail(balanceChangeVOList, page, count);
            return map;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("PageResult", PageResult.getPageResult(page.getPageNo(), page.getPageSize(), 0));
            map.put("amount", "0");
            return map;
        }
    }

    private Map<String, Object> toUserReportDetail(List<MemGoldchange> goldchangeList, PageBounds page, Integer count) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserReportDetailVO> list = new ArrayList<UserReportDetailVO>();
        BigDecimal amount = new BigDecimal(0.00);
        if (goldchangeList != null && goldchangeList.size() > 0) {
            for (MemGoldchange memGoldchange : goldchangeList) {
                UserReportDetailVO vo = new UserReportDetailVO();
                if (memGoldchange.getChangetype() != null) {
                    BigDecimal quantity = memGoldchange.getRecgoldnum().subtract(memGoldchange.getGoldnum());
                    vo.setGoldchangid(memGoldchange.getGoldchangid());
                    vo.setQuantity(quantity);
                    vo.setCreateTime(memGoldchange.getCreateTime());
                    processChangeName(vo, memGoldchange);
                    list.add(vo);
                    amount = amount.add(quantity);
                }
            }
        }
        map.put("PageResult", PageResult.getPageResult(page.getPageNo(), page.getPageSize(), count, list));
        map.put("amount", amount);
        return map;
    }

    /**
     * 处理名称
     */
    private void processChangeName(UserReportDetailVO vo, MemGoldchange mgc) {
        String name = StatisticChangeTypeUtils.getChangeTypeToNameMap().get(String.valueOf(mgc.getChangetype()));
        //送礼=前缀+备注信息;彩票=前缀+帐变类型+备注信息
        if (mgc.getChangetype().equals(GoldchangeEnum.REWARD.getValue())) {
            vo.setChangeName(name + mgc.getOpnote());
        } else if (mgc.getChangetype().equals(GoldchangeEnum.LOTTERY_PRIZE.getValue()) || mgc.getChangetype().equals(GoldchangeEnum.LOTTERY_BETTING.getValue())
                || mgc.getChangetype().equals(GoldchangeEnum.LOTTERY_BETTING_CANCLE.getValue()) || mgc.getChangetype().equals(GoldchangeEnum.LIVEROOM_BET.getValue())
                || mgc.getChangetype().equals(GoldchangeEnum.LIVEROOM_SETTLE.getValue())) {
            vo.setChangeName(name + GoldchangeEnum.getNameByValue(mgc.getChangetype()) + "-" + mgc.getOpnote());
        } else {
            vo.setChangeName(name);
        }
    }
}
