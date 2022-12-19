package com.likes.common.util.threeway;


import com.likes.common.constant.Constants;
import com.likes.common.mybatis.entity.ThreeMainWayDs;
import com.likes.common.mybatis.entity.ThreeSmallWayDs;
import com.likes.common.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ThreeSmallWayUtils
 * @Description: 下三路算法：小路
 * @author: HANS
 * @date: 2019年6月11日 下午4:11:13
 */
public class ThreeSmallWayUtils {
    private static final Logger logger = LoggerFactory.getLogger(ThreeSmallWayUtils.class);

    /**
     * @Title: mainWay
     * @Description: 小路
     * @author HANS
     * @date 2019年8月28日下午6:43:32
     */
    public static Map<String, Integer> smallWay(List<ThreeMainWayDs> initialMainList, List<ThreeMainWayDs> mainWayList, List<ThreeSmallWayDs> smallWayList) {
        Map<String, Integer> smallWayValue = new HashMap<String, Integer>();
        try {
            if (CollectionUtils.isEmpty(initialMainList)) {
                return smallWayValue;
            }
            if (CollectionUtils.isEmpty(mainWayList)) {
                return smallWayValue;
            }
            // 计算条件  B[A3] = 4或者3，则开始计算, 否则 B【A4】=1或者2，则开始计算，否则等待下期
            boolean conditionOne = waybThridCondition(initialMainList);
            boolean conditionTwo = waybFourthCondition(initialMainList);

            if (conditionOne && conditionTwo) {
                return smallWayValue;
            }
            // 获取上期小路数据
            Integer smallh = Constants.DEFAULT_INTEGER;
            Integer smalli = Constants.DEFAULT_INTEGER;
            Integer ruleTwoFlag = null;
            Integer ruleThreeFlag = null;

            if (!CollectionUtils.isEmpty(smallWayList)) {
                ThreeSmallWayDs threeSmallWayDs = smallWayList.get(Constants.DEFAULT_INTEGER);

                if (null != threeSmallWayDs) {
                    smallh = threeSmallWayDs.getSmallh() == null ? Constants.DEFAULT_INTEGER : threeSmallWayDs.getSmallh();
                    smalli = threeSmallWayDs.getSmalli() == null ? Constants.DEFAULT_INTEGER : threeSmallWayDs.getSmalli();
                    ruleTwoFlag = threeSmallWayDs.getRuleTwoFlag();
                    ruleThreeFlag = threeSmallWayDs.getRuleThreeFlag();
                }
            }
            /**
             *  按照规则一条件： Bn = 1 或 2  计算
             */
            // 获取当期大路数据
            Integer pastNum = mainWayList.get(Constants.DEFAULT_INTEGER).getMinb();
            // 若 大路B路 =1或 2
            if (Constants.DEFAULT_ONE.equals(pastNum) || Constants.DEFAULT_TWO.equals(pastNum)) {
                // 规则一
                smallWayValue = ruleOneSmallWay(mainWayList, smallh, smalli);
            }
            if (CollectionUtil.isNotEmpty(smallWayValue)) {
                return smallWayValue;
            }
            /**
             * 照规则二条件： 上期是规则三计算， 且X>=1
             */
            if (mainWayList.size() < Constants.DEFAULT_THREE) {
                return smallWayValue;
            }
            // 获取上二期大路数据
            Integer twoPastNum = mainWayList.get(Constants.DEFAULT_TWO).getMinb();

            if ((null != ruleTwoFlag && ruleTwoFlag > Constants.DEFAULT_ONE) || (null != ruleThreeFlag && ruleThreeFlag >= Constants.DEFAULT_ONE)) {
                // 规则二
                smallWayValue = ruleTwoSmallWay(pastNum, twoPastNum, smallh, smalli);
            }
            if (CollectionUtil.isNotEmpty(smallWayValue)) {
                return smallWayValue;
            }
            /**
             * 照规则三条件： 判断 Bn 为单
             */
            smallWayValue = ruleThreeSmallWay(pastNum, twoPastNum, smallh, smalli);
        } catch (Exception e) {
            logger.error("计算小路时异常：", e);
        }
        return smallWayValue;
    }

    /**
     * @Description: 规则一
     */
    private static Map<String, Integer> ruleOneSmallWay(List<ThreeMainWayDs> mainWayList, Integer smallh, Integer smalli) {
        Map<String, Integer> ruleOneValue = new HashMap<String, Integer>();
        try {
            // 计算当期X值
            Integer xValue = getSmallTwoGroupHeight(mainWayList);

            if (Constants.DEFAULT_INTEGER.equals(xValue) && Constants.DEFAULT_ONE.equals(smalli & Constants.DEFAULT_ONE)) {
                if (Constants.DEFAULT_INTEGER.equals(smallh)) {
                    ruleOneValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
                }

                if (smallh > Constants.DEFAULT_INTEGER) {
                    ruleOneValue.put(Constants.THREE_WAY_PART_A, smallh);
                }
                ruleOneValue.put(Constants.THREE_WAY_PART_B, smalli + Constants.DEFAULT_TWO);
            }

            if (Constants.DEFAULT_INTEGER.equals(xValue) && Constants.DEFAULT_INTEGER.equals(smalli & Constants.DEFAULT_ONE)) {
                ruleOneValue.put(Constants.THREE_WAY_PART_A, smallh + Constants.DEFAULT_ONE);
                ruleOneValue.put(Constants.THREE_WAY_PART_B, +Constants.DEFAULT_ONE);
            }

            if (!Constants.DEFAULT_INTEGER.equals(xValue) && Constants.DEFAULT_INTEGER.equals(smalli & Constants.DEFAULT_ONE)) {
                if (Constants.DEFAULT_INTEGER.equals(smallh)) {
                    ruleOneValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
                }

                if (smallh > Constants.DEFAULT_INTEGER) {
                    ruleOneValue.put(Constants.THREE_WAY_PART_A, smallh);
                }
                ruleOneValue.put(Constants.THREE_WAY_PART_B, smalli + Constants.DEFAULT_TWO);
            }

            if (Constants.DEFAULT_INTEGER.equals(xValue) && Constants.DEFAULT_ONE.equals(smalli & Constants.DEFAULT_ONE)) {
                ruleOneValue.put(Constants.THREE_WAY_PART_A, smallh + Constants.DEFAULT_ONE);
                ruleOneValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_TWO);
            }
            ruleOneValue.put(Constants.THREE_WAY_PART_C, Constants.DEFAULT_NEGATIVE);
            ruleOneValue.put(Constants.THREE_WAY_PART_D, Constants.DEFAULT_NEGATIVE);
        } catch (Exception e) {
            logger.error("小路==>规则一计算错误：", e);
        }
        return ruleOneValue;
    }

    /**
     * @Description: 规则二
     */
    private static Map<String, Integer> ruleTwoSmallWay(Integer pastNum, Integer twoPastNum, Integer smallh, Integer smalli) {
        Map<String, Integer> ruleTwoValue = new HashMap<String, Integer>();
        try {
            Integer xValue = pastNum - twoPastNum;

            if (xValue >= Constants.DEFAULT_ONE && (smalli & Constants.DEFAULT_ONE) == Constants.DEFAULT_ONE) {
                ruleTwoValue.put(Constants.THREE_WAY_PART_A, smallh);
                ruleTwoValue.put(Constants.THREE_WAY_PART_B, smalli + Constants.DEFAULT_TWO);
            }

            // 上期号码为双，新列为单数
            if (xValue >= Constants.DEFAULT_ONE && (smalli & Constants.DEFAULT_ONE) == Constants.DEFAULT_INTEGER) {
                ruleTwoValue.put(Constants.THREE_WAY_PART_A, smallh + Constants.DEFAULT_ONE);
                ruleTwoValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_ONE);
            }
            ruleTwoValue.put(Constants.THREE_WAY_PART_C, Constants.DEFAULT_NEGATIVE);
            ruleTwoValue.put(Constants.THREE_WAY_PART_D, Constants.DEFAULT_TWO);
        } catch (Exception e) {
            logger.error("小路==>规则二计算错误：", e);
        }
        return ruleTwoValue;
    }

    /**
     * @Description: 规则三
     */
    private static Map<String, Integer> ruleThreeSmallWay(Integer pastNum, Integer twoPastNum, Integer smallh, Integer smalli) {
        Map<String, Integer> ruleThreeValue = new HashMap<String, Integer>();
        try {
            // 计算当期X值
            Integer xValue = pastNum - twoPastNum;

            if (xValue < Constants.DEFAULT_ONE && (smalli & Constants.DEFAULT_ONE) == Constants.DEFAULT_ONE) {
                ruleThreeValue.put(Constants.THREE_WAY_PART_B, smalli + Constants.DEFAULT_TWO);

                if (Constants.DEFAULT_INTEGER.equals(smallh)) {
                    ruleThreeValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
                }

                if (smallh > Constants.DEFAULT_INTEGER) {
                    ruleThreeValue.put(Constants.THREE_WAY_PART_A, smallh);
                }
            }

            if (xValue >= Constants.DEFAULT_ONE && (smalli & Constants.DEFAULT_ONE) == Constants.DEFAULT_INTEGER) {
                if (Constants.DEFAULT_INTEGER.equals(smallh)) {
                    ruleThreeValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
                }

                if (smallh > Constants.DEFAULT_INTEGER) {
                    ruleThreeValue.put(Constants.THREE_WAY_PART_A, smallh);
                }
                ruleThreeValue.put(Constants.THREE_WAY_PART_B, smalli + Constants.DEFAULT_TWO);
            }

            if (xValue >= Constants.DEFAULT_ONE && (smalli & Constants.DEFAULT_ONE) == Constants.DEFAULT_ONE) {
                ruleThreeValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_TWO);
                ruleThreeValue.put(Constants.THREE_WAY_PART_A, smallh + Constants.DEFAULT_ONE);
            }

            if (xValue < Constants.DEFAULT_ONE && (smalli & Constants.DEFAULT_ONE) == Constants.DEFAULT_INTEGER) {
                ruleThreeValue.put(Constants.THREE_WAY_PART_A, smallh + Constants.DEFAULT_ONE);
                ruleThreeValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_ONE);
            }
            ruleThreeValue.put(Constants.THREE_WAY_PART_C, xValue);
            ruleThreeValue.put(Constants.THREE_WAY_PART_D, Constants.DEFAULT_NEGATIVE);
        } catch (Exception e) {
            logger.error("小路==>规则三计算错误：", e);
        }
        return ruleThreeValue;
    }

    /**
     * 获取当期和上一期高度差
     *
     * @param mainWayList
     * @return
     */
    private static Integer getSmallTwoGroupHeight(List<ThreeMainWayDs> mainWayList) {
        if (mainWayList.size() < Constants.DEFAULT_FOUR) {
            return Constants.DEFAULT_NEGATIVE;
        }
        // 获取上一期大路数据
        Integer onePastNum = mainWayList.get(Constants.DEFAULT_ONE).getMinb();
        Integer oneNumHeight = ThreeLargeSeedUtils.getWaybHeight(onePastNum);
        // 获取上三期大路数据
        Integer threePastNum = mainWayList.get(Constants.DEFAULT_THREE).getMinb();
        Integer threeNumHeight = ThreeLargeSeedUtils.getWaybHeight(threePastNum);
        // 计算  (B【An-1】+ math.floor(B【An-1】)%2)-(B【An-3】+ math.floor(B【An-3】)%2)
        Integer xValue = oneNumHeight - threeNumHeight;
        return xValue;
    }

    /**
     * 计算开始的第一个条件   B[A3] = 4或者3
     *
     * @param initialMainList
     * @return
     */
    private static boolean waybThridCondition(List<ThreeMainWayDs> initialMainList) {
        if (initialMainList.size() < Constants.DEFAULT_THREE) {
            return true;
        }
        // 计算条件  B[A3] = 4或者3，则开始计算, 否则 B【A4】=1或者2，则开始计算，否则等待下期
        ThreeMainWayDs threeMainWayDs = initialMainList.get(Constants.DEFAULT_TWO);

        if (null == threeMainWayDs) {
            return true;
        }
        Integer onePastNum = threeMainWayDs.getMinb(); // 大路第三期

        return onePastNum < Constants.DEFAULT_THREE;
    }

    /**
     * 计算开始的第二个条件   B【A4】=1或者2
     *
     * @param initialMainList
     * @return
     */
    private static boolean waybFourthCondition(List<ThreeMainWayDs> initialMainList) {
        if (initialMainList.size() < Constants.DEFAULT_FOUR) {
            return true;
        }
        // 计算条件  B[A3] = 4或者3，则开始计算, 否则 B【A4】=1或者2，则开始计算，否则等待下期
        ThreeMainWayDs fourMainWayDs = initialMainList.get(Constants.DEFAULT_THREE);

        if (null == fourMainWayDs) {
            return true;
        }
        Integer fourPastNum = fourMainWayDs.getMinb();// 大路第三期

        return fourPastNum < Constants.DEFAULT_ONE;
    }


}
