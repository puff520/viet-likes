package com.likes.common.model.dto.report;

import com.likes.common.constant.Constants;
import com.google.common.collect.Lists;


import java.util.List;

/**
 * @Author xiaoming
 * @ClassName 首页统计
 * @Description
 * @Date 11:04 上午 8/12/20
 **/

public class HomeDataDO {

    private Integer registered;
    /**
     * 总盈利
     */
    private ProfitAllDataDO profitAllDataDO;

    /**
     * 充值
     */
    private RechargeAllDataDO rechargeAllDataDO;

    /**
     * 提现
     */
    private PaymentAllDataDO paymentAllDataDO;

    /**
     * 活动
     */
    private ActivityAllDataDO activityAllDataDO;
    /**
     * 打码流水
     */
    private ConsumptionAllDataDO consumptionAllDataDO;

    /**
     * 彩票
     */
    private LotteryAllDataDO lotteryAllDataDO;

    public Integer getRegistered() {
        return registered;
    }

    public void setRegistered(Integer registered) {
        this.registered = registered;
    }

    public ProfitAllDataDO getProfitAllDataDO() {
        return profitAllDataDO;
    }

    public void setProfitAllDataDO(ProfitAllDataDO profitAllDataDO) {
        this.profitAllDataDO = profitAllDataDO;
    }

    public RechargeAllDataDO getRechargeAllDataDO() {
        return rechargeAllDataDO;
    }

    public void setRechargeAllDataDO(RechargeAllDataDO rechargeAllDataDO) {
        this.rechargeAllDataDO = rechargeAllDataDO;
    }

    public PaymentAllDataDO getPaymentAllDataDO() {
        return paymentAllDataDO;
    }

    public void setPaymentAllDataDO(PaymentAllDataDO paymentAllDataDO) {
        this.paymentAllDataDO = paymentAllDataDO;
    }

    public ActivityAllDataDO getActivityAllDataDO() {
        return activityAllDataDO;
    }

    public void setActivityAllDataDO(ActivityAllDataDO activityAllDataDO) {
        this.activityAllDataDO = activityAllDataDO;
    }

    public ConsumptionAllDataDO getConsumptionAllDataDO() {
        return consumptionAllDataDO;
    }

    public void setConsumptionAllDataDO(ConsumptionAllDataDO consumptionAllDataDO) {
        this.consumptionAllDataDO = consumptionAllDataDO;
    }

    public LotteryAllDataDO getLotteryAllDataDO() {
        return lotteryAllDataDO;
    }

    public void setLotteryAllDataDO(LotteryAllDataDO lotteryAllDataDO) {
        this.lotteryAllDataDO = lotteryAllDataDO;
    }

    public static List<QueryRule> getQueryRule(String startTime, String endTime) {
        QueryRule registered = new QueryRule(Constants.HOME_REGISTERED, startTime, endTime);
       // QueryRule profitAllDataDO = new QueryRule(Constants.HOME_PROFITALLDATA, startTime, endTime);
        QueryRule rechargeAllDataDO = new QueryRule(Constants.HOME_RECHARGEALLDATA, startTime, endTime);
        QueryRule paymentAllDataDO = new QueryRule(Constants.HOME_PAYMENTALLDATA, startTime, endTime);
        QueryRule activityAllDataDO = new QueryRule(Constants.HOME_ACTIVITYALLDATA, startTime, endTime);
        QueryRule consumptionAllDataDO = new QueryRule(Constants.HOME_CONSUMPTIONALLDATA, startTime, endTime);
        QueryRule lotteryAllDataDO = new QueryRule(Constants.HOME_LOTTERYALLDATA, startTime, endTime);

        List<QueryRule> ruleList = Lists.newArrayList(registered, rechargeAllDataDO
                , paymentAllDataDO, activityAllDataDO,consumptionAllDataDO,lotteryAllDataDO);
        return ruleList;
    }


    public static class QueryRule {

        private String key;
        private String startTime;
        private String endTime;

        public QueryRule(String key, String startTime, String endTime) {
            this.key = key;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

    }
}
