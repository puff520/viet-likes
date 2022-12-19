package com.likes.common.util.cache;

import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.model.vo.money.MemGoldchangeTypeVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticChangeTypeUtils {

    /**
     * 帐变列表显示的帐变名称
     */
    private static Map<String, List> listChangeTypeMap = null;

    /**
     * 用户报表的类型转名称
     */
    private static Map<String, String> changeTypeToNameMap = null;

    /**
     * 用户报表的详情页，默认查询的帐变
     */
    private static List<Integer> changeTypeList = null;

    /**
     * 后台管理-获取统计-帐变列表-查询帐变选型列表
     */
    public static Map<String, List> getListChangeTypeMap() {
        if (null == listChangeTypeMap) {
            listChangeTypeMap = listChangeType();
        }
        return listChangeTypeMap;
    }

    /**
     * 后台统计-帐变列表-帐变类型
     */
    private static Map listChangeType() {
        List<MemGoldchangeTypeVO> basicChangeType = basicChangeType();
        List<MemGoldchangeTypeVO> errorChangeType = errorChangeType();
        List<MemGoldchangeTypeVO> specialChangeType = specialChangeType();
        List<MemGoldchangeTypeVO> liveChangeType = liveChangeType();

        Map<String, List> map = new HashMap<String, List>();
        map.put("basic", basicChangeType);
        map.put("error", errorChangeType);
        map.put("special", specialChangeType);
        map.put("live", liveChangeType);
        return map;
    }

    /**
     * 后台统计-帐变列表-帐变类型-basic
     */
    private static List<MemGoldchangeTypeVO> basicChangeType() {
        List<MemGoldchangeTypeVO> basicTypes = new ArrayList<>();
        MemGoldchangeTypeVO typeVO1 = new MemGoldchangeTypeVO();
        typeVO1.setType(GoldchangeEnum.RECHARGE.getValue());
        typeVO1.setName(GoldchangeEnum.RECHARGE.getName());
        MemGoldchangeTypeVO typeVO1_1 = new MemGoldchangeTypeVO();//代理充值
        typeVO1_1.setType(GoldchangeEnum.REPAY_INCOME_ORDER.getValue());
        typeVO1_1.setName(GoldchangeEnum.REPAY_INCOME_ORDER.getName());
        MemGoldchangeTypeVO typeVO2 = new MemGoldchangeTypeVO();
        typeVO2.setType(GoldchangeEnum.WITHDRAWAL_APPLY.getValue());
        typeVO2.setName(GoldchangeEnum.WITHDRAWAL_APPLY.getName());
//        MemGoldchangeTypeVO typeVO3 = new MemGoldchangeTypeVO();
//        typeVO3.setType(GoldchangeEnum.WITHDRAWAL_CANCLE.getValue());
//        typeVO3.setName(GoldchangeEnum.WITHDRAWAL_CANCLE.getName());
        MemGoldchangeTypeVO typeVO4 = new MemGoldchangeTypeVO();
        typeVO4.setType(GoldchangeEnum.WITHDRAWN.getValue());
        typeVO4.setName(GoldchangeEnum.WITHDRAWN.getName());
        MemGoldchangeTypeVO typeVO5 = new MemGoldchangeTypeVO();


        MemGoldchangeTypeVO typeVO6 = new MemGoldchangeTypeVO();

        typeVO6.setName("彩票结算");
        MemGoldchangeTypeVO typeVO7 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO8 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO9 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO10 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO11 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO12 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO13 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO14 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO15 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO16 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO17 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO18 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO19 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO20 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO21 = new MemGoldchangeTypeVO();


        basicTypes.add(typeVO1);
        basicTypes.add(typeVO1_1);
        basicTypes.add(typeVO2);
//        basicTypes.add(typeVO3);
        basicTypes.add(typeVO4);
        basicTypes.add(typeVO5);
        basicTypes.add(typeVO6);
        basicTypes.add(typeVO7);
        basicTypes.add(typeVO8);
        basicTypes.add(typeVO9);
        basicTypes.add(typeVO10);
        basicTypes.add(typeVO11);
        basicTypes.add(typeVO12);
        basicTypes.add(typeVO13);
        basicTypes.add(typeVO14);
        basicTypes.add(typeVO15);
        basicTypes.add(typeVO16);
        basicTypes.add(typeVO17);
        basicTypes.add(typeVO18);
        basicTypes.add(typeVO19);
        basicTypes.add(typeVO20);
        basicTypes.add(typeVO21);

        return basicTypes;
    }

    /**
     * 后台统计-帐变列表-帐变类型-error
     */
    private static List<MemGoldchangeTypeVO> errorChangeType() {
        List<MemGoldchangeTypeVO> basicTypes = new ArrayList<>();
        MemGoldchangeTypeVO typeVO1 = new MemGoldchangeTypeVO();
        typeVO1.setType(GoldchangeEnum.WITHDRAW_FAILED.getValue());
        typeVO1.setName(GoldchangeEnum.WITHDRAW_FAILED.getName());
//        MemGoldchangeTypeVO typeVO2 = new MemGoldchangeTypeVO();
//        typeVO2.setType(GoldchangeEnum.BET_ORDER_BAK.getValue());
//        typeVO2.setName(GoldchangeEnum.BET_ORDER_BAK.getName());
//        MemGoldchangeTypeVO typeVO3 = new MemGoldchangeTypeVO();
//        typeVO3.setType(GoldchangeEnum.XGKTED.getValue());
//        typeVO3.setName(GoldchangeEnum.XGKTED.getName());
        MemGoldchangeTypeVO typeVO4 = new MemGoldchangeTypeVO();
//        typeVO4.setType(GoldchangeEnum.MANUALLY_INCOME_MONEY.getValue());
//        typeVO4.setName(GoldchangeEnum.MANUALLY_INCOME_MONEY.getName());
//        MemGoldchangeTypeVO typeVO5 = new MemGoldchangeTypeVO();
//        typeVO5.setType(GoldchangeEnum.MANUALLY_OUTGO_MONEY.getValue());
//        typeVO5.setName(GoldchangeEnum.MANUALLY_OUTGO_MONEY.getName());
//        MemGoldchangeTypeVO typeVO6 = new MemGoldchangeTypeVO();
//        typeVO6.setType(GoldchangeEnum.MANUALLY_ADD_DAMALIANG.getValue());
//        typeVO6.setName(GoldchangeEnum.MANUALLY_ADD_DAMALIANG.getName());
//        MemGoldchangeTypeVO typeVO7 = new MemGoldchangeTypeVO();
//        typeVO7.setType(GoldchangeEnum.MANUALLY_SUB_DAMALIANG.getValue());
//        typeVO7.setName(GoldchangeEnum.MANUALLY_SUB_DAMALIANG.getName());
        basicTypes.add(typeVO1);
//        basicTypes.add(typeVO2);
//        basicTypes.add(typeVO3);
        basicTypes.add(typeVO4);
        return basicTypes;
    }

    /**
     * 后台统计-帐变列表-帐变类型-special
     */
    private static List<MemGoldchangeTypeVO> specialChangeType() {
        List<MemGoldchangeTypeVO> basicTypes = new ArrayList<>();
        MemGoldchangeTypeVO typeVO1 = new MemGoldchangeTypeVO();

//        MemGoldchangeTypeVO typeVO6 = new MemGoldchangeTypeVO();
//        typeVO6.setType(GoldchangeEnum.AUDIT_FEE.getValue());
//        typeVO6.setName(GoldchangeEnum.AUDIT_FEE.getName());
//        MemGoldchangeTypeVO typeVO7 = new MemGoldchangeTypeVO();
//        typeVO7.setType(GoldchangeEnum.AUDIT_FEE_CANCLE.getValue());
//        typeVO7.setName(GoldchangeEnum.AUDIT_FEE_CANCLE.getName());
        MemGoldchangeTypeVO typeVO8 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO9 = new MemGoldchangeTypeVO();
        typeVO9.setType(GoldchangeEnum.AGENCY_SETTLE.getValue());
        typeVO9.setName(GoldchangeEnum.AGENCY_SETTLE.getName());
        MemGoldchangeTypeVO typeVO10 = new MemGoldchangeTypeVO();
        //暂时不需要"赠送"
//        MemGoldchangeTypeVO typeVO11 = new MemGoldchangeTypeVO();
//        typeVO11.setType(GoldchangeEnum.DELIVER.getValue());
//        typeVO11.setName(GoldchangeEnum.DELIVER.getName());
//        MemGoldchangeTypeVO typeVO12 = new MemGoldchangeTypeVO();
//        typeVO12.setType(GoldchangeEnum.SEND_VIDEO_REWARDS.getValue());
//        typeVO12.setName(GoldchangeEnum.SEND_VIDEO_REWARDS.getName());
        MemGoldchangeTypeVO typeVO13 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO14 = new MemGoldchangeTypeVO();
        typeVO14.setType(GoldchangeEnum.RECHARGE_BONUS.getValue());
        typeVO14.setName(GoldchangeEnum.RECHARGE_BONUS.getName());
        basicTypes.add(typeVO1);
//        basicTypes.add(typeVO6);
//        basicTypes.add(typeVO7);
        basicTypes.add(typeVO8);
        basicTypes.add(typeVO9);
        basicTypes.add(typeVO10);
//        basicTypes.add(typeVO11);
//        basicTypes.add(typeVO12);
        basicTypes.add(typeVO13);
        basicTypes.add(typeVO14);
        return basicTypes;
    }

    /**
     * 后台统计-帐变列表-帐变类型-live
     */
    private static List<MemGoldchangeTypeVO> liveChangeType() {
        List<MemGoldchangeTypeVO> basicTypes = new ArrayList<>();
//        MemGoldchangeTypeVO typeVO1 = new MemGoldchangeTypeVO();
//        typeVO1.setType(GoldchangeEnum.FAMILY_GIFT_DIVIDED.getValue());
//        typeVO1.setName(GoldchangeEnum.FAMILY_GIFT_DIVIDED.getName());
//        MemGoldchangeTypeVO typeVO2 = new MemGoldchangeTypeVO();
//        typeVO2.setType(GoldchangeEnum.FAMILY_BET_DIVIDED.getValue());
//        typeVO2.setName(GoldchangeEnum.FAMILY_BET_DIVIDED.getName());
        MemGoldchangeTypeVO typeVO3 = new MemGoldchangeTypeVO();

        MemGoldchangeTypeVO typeVO4 = new MemGoldchangeTypeVO();
        MemGoldchangeTypeVO typeVO5 = new MemGoldchangeTypeVO();
//        basicTypes.add(typeVO1);
//        basicTypes.add(typeVO2);
        basicTypes.add(typeVO3);
        basicTypes.add(typeVO4);
        basicTypes.add(typeVO5);
        return basicTypes;
    }


    /**
     * 后台管理-获取统计-帐变列表-查询帐变选型列表
     */
    public static Map<String, String> getChangeTypeToNameMap() {
        if (null == changeTypeToNameMap) {
            changeTypeToNameMap = new HashMap<String, String>();
            changeTypeToNameMap.put("1", "【充值】入款成功");//充值
            changeTypeToNameMap.put("69", "【充值】代充入款成功");
            changeTypeToNameMap.put("10", "【提现】申请");//提现
            changeTypeToNameMap.put("12", "【提现】提现成功");
            changeTypeToNameMap.put("2", "【礼物】");//礼物
            changeTypeToNameMap.put("13", "【彩票】");//彩票
            changeTypeToNameMap.put("14", "【彩票】");
            changeTypeToNameMap.put("15", "【彩票】");
            changeTypeToNameMap.put("60", "【彩票】");
            changeTypeToNameMap.put("61", "【彩票】");
            changeTypeToNameMap.put("36", "【活动回收】大神打赏");//活动回收
            changeTypeToNameMap.put("4", "【活动优惠】签到奖励");//活动优惠
            changeTypeToNameMap.put("7", "【活动优惠】邀请用户奖励");
            changeTypeToNameMap.put("8", "【活动优惠】充值附赠");
            changeTypeToNameMap.put("35", "【活动优惠】彩种返水");
            changeTypeToNameMap.put("46", "【活动优惠】AG反水");
            changeTypeToNameMap.put("47", "【活动优惠】开元返水");
            changeTypeToNameMap.put("48", "【活动优惠】电竞返水");
            changeTypeToNameMap.put("49", "【活动优惠】AE返水");
            changeTypeToNameMap.put("20", "【活动优惠】代理佣金结算");
            changeTypeToNameMap.put("37", "【第三方游戏】AG视讯转出");//第三方游戏
            changeTypeToNameMap.put("39", "【第三方游戏】开元棋牌转出");
            changeTypeToNameMap.put("41", "【第三方游戏】电竞转出");
            changeTypeToNameMap.put("50", "【第三方游戏】AE棋牌转出");
            changeTypeToNameMap.put("63", "【第三方游戏】MG电子转出");
            changeTypeToNameMap.put("65", "【第三方游戏】JDB捕鱼转出");
            changeTypeToNameMap.put("38", "【第三方游戏】转入AG视讯");
            changeTypeToNameMap.put("40", "【第三方游戏】转入开元棋牌");
            changeTypeToNameMap.put("42", "【第三方游戏】转入电竞");
            changeTypeToNameMap.put("51", "【第三方游戏】转入AE棋牌");
            changeTypeToNameMap.put("64", "【第三方游戏】转入MG电子");
            changeTypeToNameMap.put("66", "【第三方游戏】转入JDB捕鱼");
            changeTypeToNameMap.put("55", "【人工】人工加款");//人工
            changeTypeToNameMap.put("56", "【人工】人工扣款");
        }
        return changeTypeToNameMap;
    }

    public static List<Integer> getChangeTypeList() {
        if (null == changeTypeList) {
            changeTypeList = new ArrayList<>();
            Map<String, String> map = getChangeTypeToNameMap();
            for (String key : map.keySet()) {
                changeTypeList.add(Integer.parseInt(key));
            }
            changeTypeList = new ArrayList(map.keySet());
        }
        return changeTypeList;
    }
}
