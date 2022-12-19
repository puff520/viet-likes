package com.likes.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:    GoldchangeEnum Package:    com.likes.common.constant Description: Datetime:
 * 2020/4/16   18:29 Author:   木鱼
 */
public enum GoldchangeEnum {

    RECHARGE(1, "充值"),

    REWARD(2, "礼物赠送"),

    //20200805,sea说不要用此类型
    DELIVER(3, "赠送"),

    ATTENDANCE_AWARD(4, "签到奖励"),

    POSTING_AWARD(5, "发帖奖励"),

    //20200805,sea说不要用此类型
    SEND_VIDEO_REWARDS(6, "发视频奖励"),

    INVITE_USERS(7, "邀请用户"),

    RECHARGE_BONUS(8, "充值附赠"),

    //20200805,sea说不要用此类型
    ANCHOR_SPLIT(9, "主播分成"),

    WITHDRAWAL_APPLY(10, "提现申请"),

    //20200805,sea说不要用此类型
    WITHDRAWAL_CANCLE(11, "提现取消"),

    WITHDRAWN(12, "已提现"),

    LOTTERY_PRIZE(13, "彩票派奖"),

    LOTTERY_BETTING(14, "彩票下注"),

    LOTTERY_BETTING_CANCLE(15, "彩票下注取消"),

    //20200805,sea说不要用此类型
    AUDIT_FEE(16, "稽核手续费"),

    //20200805,sea说不要用此类型
    AUDIT_FEE_CANCLE(17, "稽核手续费取消"),

    AGENCY_SETTLE(20, "代理结算"),

    ///////////////以下是CPT变更类型/////////////////////
    /*                       * 41 -- 电竞  需要修改
     * 37-- AG
     * 39 -- 棋牌
     * 50 -- AE*/
    REVOKE_AWARD(21, "撤销开奖"),

    BET_BALANCE(22, "打和退还"),

    APPEND_BET_BACK(23, "停追退款"),

    BET_ORDER_BAK(24, "主动撤单"),

    BET_ORDER_BAK_SYSTEM(25, "系统撤单"),

    BET_ORDER_BACK(26, "VIP返点"),

    VIP_UPGRADE_AWARDS(27, "VIP升级奖励"),

    ACTIVITY_ACCOUNT(28, "活动礼金"),

    //20200805,sea说不要用此类型
    XGKTED(29, "修改可提额度"),

    REGISTER_AWARD(30, "注册赠送"),

    SHARE_AWARD(31, "分享赠送"),

    SHARE_BACK(32, "分享返水"),

    RED_ENVELOP(33, "红包活动"),

    ORDER_APPEND_BACK(34, "追号撤单"),

    LOTTERY_RETURN_WATER(35, "彩种返水"),

    GOD_PLAN_REWARD(36, "大神打赏支出"),

    AG_IN(37, "AG视讯转出"),

    AG_OUT(38, "转入AG视讯"),

    KY_IN(39, "开元棋牌转出"),

    KY_OUT(40, "转入开元棋牌"),

    ES_IN(41, "电竞转出"),

    ES_OUT(42, "转入电竞"),

    AG_BET_ORDER(43, "AG视讯投注"),

    KY_BET_ORDER(44, "开元棋牌投注"),

    ES_BET_ORDER(45, "电竞投注"),

    AG_RETURN_WATER(46, "AG返水"),

    KY_RETURN_WATER(47, "开元返水"),

    ES_RETURN_WATER(48, "电竞返水"),

    AE_RETURN_WATER(49, "AE返水"),

    AE_IN(50, "AE棋牌转出"),

    AE_OUT(51, "转入AE棋牌"),

    AE_BET_ORDER(52, "AE棋牌投注"),

    //20200805,sea说不要用此类型
    BET_ORDER_LOSS(53, "投注输钱"),

    WITHDRAW_FAILED(54, "提现失败"),

    MANUALLY_INCOME_MONEY(55, "手动入款"),

    MANUALLY_OUTGO_MONEY(56, "手动出款"),

    MANUALLY_ADD_DAMALIANG(57, "手动增加所需打码量"),

    MANUALLY_SUB_DAMALIANG(58, "手动减少所需打码量"),

    FAMILY_GIFT_DIVIDED(59, "家族礼物分成"),

    LIVEROOM_BET(60, "直播间投注"),

    LIVEROOM_SETTLE(61, "直播间结算"),

    FAMILY_BET_DIVIDED(62, "家族投注分成"),

    MG_IN(63, "MG电子转出"),

    MG_OUT(64, "转入MG电子"),

    DB_IN(65, "JDB捕鱼转出"),

    DB_OUT(66, "转入JDB捕鱼"),

    MG_BET_ORDER(67, "MG电子投注"),

    JDB_BET_ORDER(68, "JDB捕鱼投注"),

    REPAY_INCOME_ORDER(69, "代理充值"),
    JACKPOT(70, "增加彩金"),
    REGISTER_JACKPOT(71, "注册彩金"),

    BUY_VIP(100, "购买VIP"),

    BROKERAGE_LEVEL_1(101, "第一级返佣"),

    BROKERAGE_LEVEL_2(102, "第二级返佣"),

    BROKERAGE_LEVEL_3(103, "第三级级返佣"),

    TASK_AWARD(200, "任务奖励"),
    TASK_REVICE(201, "会员任务领取扣款"),
    TASK_PUB(202, "会员发布任务扣款"),
    TASK_PUMP(203, "会员发布任务抽水"),

    BUVIP_LEVEL_1(301, "第一级返佣"),

    BUVIP_LEVEL_2(302, "第二级返佣"),

    BUVIP_LEVEL_3(303, "第三级级返佣"),

    YUEBAO_INTO(401, "余额转入到余额宝"),
    YUEBAO_OUT(402, "余额宝转出到余额");

    private final Integer value;
    private final String name;

    GoldchangeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getNameByValue(int value) {
        GoldchangeEnum balanceChangeEnum = valueOf(value);
        return null == balanceChangeEnum ? "" : balanceChangeEnum.getName();
    }

    public static GoldchangeEnum valueOf(Integer value) {
        if (null == value) {
            return null;
        }
        GoldchangeEnum[] values = GoldchangeEnum.values();
        for (GoldchangeEnum balanceChangeEnum : values) {
            if (value.equals(balanceChangeEnum.getValue())) {
                return balanceChangeEnum;
            }
        }
        return null;
    }

    /**
     * 提取帐变类型(包好第三方游戏打码量，手动加减打码量)
     */
    public static final List<Integer> getChangeType() {
        List<Integer> list = new ArrayList<>();
        list.add(MANUALLY_ADD_DAMALIANG.value);
        list.add(MANUALLY_SUB_DAMALIANG.value);
        list.add(WITHDRAW_FAILED.value);
        list.add(WITHDRAWN.value);
        list.add(AG_BET_ORDER.value);
        list.add(KY_BET_ORDER.value);
        list.add(ES_BET_ORDER.value);
        list.add(AE_BET_ORDER.value);
        list.add(MG_BET_ORDER.value);
        list.add(JDB_BET_ORDER.value);
        return list;
    }

}
