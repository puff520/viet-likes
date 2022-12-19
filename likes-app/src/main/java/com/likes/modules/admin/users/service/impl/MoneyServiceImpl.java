package com.likes.modules.admin.users.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.Page;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.constant.Constants;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.model.TimeReortDTO;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.member.MemGoldchangeDTO;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.mybatis.entity.MemLevel;
import com.likes.common.mybatis.entity.MemLevelConfig;
import com.likes.common.mybatis.entity.MemLevelConfigExample;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.mybatis.mapper.TaskOrderMapper;
import com.likes.common.mybatis.mapperext.member.MemGoldchangeMapperExt;
import com.likes.common.service.member.MemLevelConfigService;
import com.likes.common.service.member.MemLevelService;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.DateUtils;
import com.likes.common.util.LevelUtils;
import com.likes.modules.admin.users.service.MoneyService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


@Service
public class MoneyServiceImpl implements MoneyService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MemLevelService memLevelService;
    @Resource
    private MemLevelConfigService memLevelConfigService;
    @Resource
    private MemGoldchangeService memGoldchangeService;
    @Resource
    private MemGoldchangeMapperExt memGoldchangeMapperExt;
    @Resource
    private TaskOrderMapper taskOrderMapper;

    @Resource
    private SysParamService sysParamService;

    @Override
    public Map<String, Object> myLevel(String accno) {
        MemLevel memLevel = memLevelService.selectByAccno(accno);
        Map<String, Object> dataMap = new HashMap<>();
        if (memLevel == null) {
            //小白
            double score = LevelUtils.getLevelScoreRewrite(2);
            dataMap.put("score", score);
            dataMap.put("memlevel", Constants.LEVEL_ONE);
            dataMap.put("nextlevel", Constants.LEVEL_TWO);
            dataMap.put("memscore", 0);
            dataMap.put("nextlevscore", 100);
            dataMap.put("currscore", 0);
        } else {
            String memlevel = memLevel.getMemlevel();
            Integer nextlevel = Integer.parseInt(memlevel) + 1;
            double score = LevelUtils.getLevelScoreRewrite(nextlevel);
            dataMap.put("score", score);
            dataMap.put("memlevel", memlevel);
            dataMap.put("nextlevel", String.valueOf(nextlevel));
            dataMap.put("memscore", memLevel.getMemscore());
            dataMap.put("nextlevscore", memLevel.getNextlevscore());
            dataMap.put("currscore", LevelUtils.getLevelScoreRewrite(Integer.parseInt(memlevel)));
        }
        MemLevelConfigExample example = new MemLevelConfigExample();
        MemLevelConfigExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(false);
        example.setOrderByClause("recharge_amount desc");
        MemLevelConfig memLevelConfig = memLevelConfigService.selectOneByExample(example);
        dataMap.put("memLevelConfig", memLevelConfig.getLevel());
        return dataMap;
    }

    @Override
    public Map<String, Object> moneyAddress(String accno) {
        SysParameter TRC20 = sysParamService.getByCode("TRC20");
        SysParameter ERC20 = sysParamService.getByCode("ERC20");
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("TRC20", TRC20.getSysparamvalue());
        objectMap.put("ERC20", ERC20.getSysparamvalue());
        return objectMap;
    }

    @Override
    @DS("slave")
    public HashMap<String, Object> myPageCount(String accno) {

        TimeReortDTO timeReortDTO = new TimeReortDTO();
        timeReortDTO.setAccno(accno);

        timeReortDTO.setJtBeginTime(DateUtils.dayBeginStr());
        timeReortDTO.setJtEnDTime(DateUtils.dayEndStr());

        timeReortDTO.setZtBeginTime(DateUtils.dayBeforeStratStr());
        timeReortDTO.setZtEnDTime(DateUtils.timeBeforeEndStr());

        timeReortDTO.setBzBeginTime(DateUtils.date2Str(DateUtils.getBeginDayOfWeek()));
        timeReortDTO.setBzEnDTime(DateUtils.date2Str(DateUtils.getEndDayOfWeek()));

        timeReortDTO.setByBeginTime(DateUtils.date2Str(DateUtils.getBeginOfThisMonth()));
        timeReortDTO.setByEnDTime(DateUtils.date2Str(DateUtils.getEndOfThisMonth()));

        HashMap<String, Object> homeMap = memGoldchangeMapperExt.myPageCount(timeReortDTO);
        if (homeMap == null) {
            homeMap = new HashMap<>();
        }
        int todayFinish = taskOrderMapper.countFinishOrderByMemNo(accno, timeReortDTO);
        int todaySurplus = taskOrderMapper.countSurplusOrderByMemNo(accno, timeReortDTO);
        homeMap.put("todayFinish", todayFinish);
        homeMap.put("todaySurplus", todaySurplus);
        return homeMap;
    }


    @Override
    @DS("slave")
    public PageResult myIncomeAndExpensesList(UsersRequest req, PageBounds page) {
        logger.info("myIncomeAndExpensesList,type:{}", req.getType());
        List<Integer> changetypeList = new ArrayList<Integer>();
        if (req.getType() == 1) {
            //收入
            changetypeList.add(GoldchangeEnum.RECHARGE.getValue());
            changetypeList.add(GoldchangeEnum.MANUALLY_INCOME_MONEY.getValue());

            changetypeList.add(GoldchangeEnum.WITHDRAWAL_CANCLE.getValue());
            changetypeList.add(GoldchangeEnum.WITHDRAW_FAILED.getValue());
            changetypeList.add(GoldchangeEnum.AGENCY_SETTLE.getValue());
            changetypeList.add(GoldchangeEnum.REPAY_INCOME_ORDER.getValue());
            changetypeList.add(GoldchangeEnum.BROKERAGE_LEVEL_1.getValue());
            changetypeList.add(GoldchangeEnum.BROKERAGE_LEVEL_2.getValue());
            changetypeList.add(GoldchangeEnum.BROKERAGE_LEVEL_3.getValue());
            changetypeList.add(GoldchangeEnum.BUVIP_LEVEL_1.getValue());
            changetypeList.add(GoldchangeEnum.BUVIP_LEVEL_2.getValue());
            changetypeList.add(GoldchangeEnum.BUVIP_LEVEL_3.getValue());
            changetypeList.add(GoldchangeEnum.TASK_AWARD.getValue());
            changetypeList.add(GoldchangeEnum.INVITE_USERS.getValue());
            changetypeList.add(GoldchangeEnum.JACKPOT.getValue());
            changetypeList.add(GoldchangeEnum.REGISTER_JACKPOT.getValue());
            changetypeList.add(GoldchangeEnum.YUEBAO_OUT.getValue());
        } else if (req.getType() == 2) {
            //支出
            changetypeList.add(GoldchangeEnum.WITHDRAWAL_APPLY.getValue());
            changetypeList.add(GoldchangeEnum.WITHDRAWN.getValue());
            changetypeList.add(GoldchangeEnum.BUY_VIP.getValue());
            changetypeList.add(GoldchangeEnum.YUEBAO_INTO.getValue());
        }

        if (CollectionUtils.isNotEmpty(changetypeList)) {
            req.setChangetypeList(changetypeList);
        }
        if (req.getType() == 0) {
            req.setChangetypeList(null);
        }
        Page<MemGoldchangeDTO> list = memGoldchangeService.myIncomeAndExpensesList(req, page.toRowBounds());
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(o -> {
                Integer changetype = o.getChangetype();
                o.setQuantity(o.getQuantity().setScale(3, BigDecimal.ROUND_DOWN));
                //1(1为收入，2为支出)
                if (GoldchangeEnum.WITHDRAWAL_APPLY.getValue().equals(changetype) || GoldchangeEnum.WITHDRAWN.getValue().equals(changetype) || GoldchangeEnum.BUY_VIP.getValue().equals(changetype)
                        || GoldchangeEnum.YUEBAO_INTO.getValue().equals(changetype)
                ) {
                    o.setType(2);
                } else {
                    o.setType(1);
                }
                if (GoldchangeEnum.RECHARGE.getValue().equals(changetype) || GoldchangeEnum.MANUALLY_INCOME_MONEY.getValue().equals(changetype)) {
                    o.setPrename("充值");
                } else if (GoldchangeEnum.BROKERAGE_LEVEL_1.getValue().equals(changetype) || GoldchangeEnum.BROKERAGE_LEVEL_2.getValue().equals(changetype) || GoldchangeEnum.BROKERAGE_LEVEL_3.getValue().equals(changetype)) {
                    o.setPrename("推广佣金");
                } else if (GoldchangeEnum.BUVIP_LEVEL_1.getValue().equals(changetype) || GoldchangeEnum.BUVIP_LEVEL_2.getValue().equals(changetype) || GoldchangeEnum.BUVIP_LEVEL_3.getValue().equals(changetype)) {
                    o.setPrename("购买vip佣金");
                } else if (GoldchangeEnum.BUY_VIP.getValue().equals(changetype)) {
                    o.setPrename("购买VIP");
                } else if (GoldchangeEnum.TASK_AWARD.getValue().equals(changetype)) {
                    o.setPrename("任务奖励");
                } else if (GoldchangeEnum.INVITE_USERS.getValue().equals(changetype)) {
                    o.setPrename("邀请用户");
                } else if (GoldchangeEnum.TASK_REVICE.getValue().equals(changetype)) {
                    o.setPrename("领取任务");
                } else if (GoldchangeEnum.WITHDRAWN.getValue().equals(changetype)) {
                    o.setPrename("提现");
                } else if (GoldchangeEnum.JACKPOT.getValue().equals(changetype)) {
                    o.setPrename("奖励");
                } else if (GoldchangeEnum.REGISTER_JACKPOT.getValue().equals(changetype)) {
                    o.setPrename("注册彩金");
                } else if (GoldchangeEnum.YUEBAO_OUT.getValue().equals(changetype)) {
                    o.setPrename("余额转出到余额宝");
                } else if (GoldchangeEnum.YUEBAO_INTO.getValue().equals(changetype)) {
                    o.setPrename("余额宝转出到余额");
                }

            });
        }
        int total = (int) list.getTotal();
        logger.info("myIncomeAndExpensesList,list{}", JSONObject.toJSONString(list));
        return PageResult.getPageResult(total, page, list);
    }


}
