package com.likes.common.util.threeway;


import com.likes.common.constant.Constants;
import com.likes.common.mybatis.entity.ThreeMainWayDs;
import com.likes.common.mybatis.entity.ThreeStrongWayDs;
import com.likes.common.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ThreeBigEyeWayUtils
 * @Description: 下三路算法：曱甴路、小强路
 * @author: HANS
 * @date: 2019年6月11日 下午4:11:13
 */
public class ThreeBigEyeWayUtils {
    private static final Logger logger = LoggerFactory.getLogger(ThreeBigEyeWayUtils.class);

    /**
     * @Title: mainWay
     * @Description: 曱甴路、小强路
     * @author HANS
     * @date 2019年8月28日下午6:43:32
     */
    public static Map<String, Integer> bigEyeWay(List<ThreeMainWayDs> initialMainList, List<ThreeMainWayDs> mainWayList, List<ThreeStrongWayDs> bigWayList) {
        Map<String, Integer> strongValue = new HashMap<String, Integer>();
        try {
            if (CollectionUtils.isEmpty(initialMainList)) {
                return strongValue;
            }
            if (CollectionUtils.isEmpty(mainWayList)) {
                return strongValue;
            }
            // 计算条件  B[A4] = 4或者3，则开始计算, 否则 B【A5】=1或者2，则开始计算，否则等待下期
            boolean conditionOne = waybFourthCondition(initialMainList);
            boolean conditionTwo = waybFivethCondition(initialMainList);

            if (conditionOne && conditionTwo) {
                return strongValue;
            }
            // 获取上期曱甴路数据
            Integer bigk = Constants.DEFAULT_INTEGER;
            Integer bigl = Constants.DEFAULT_INTEGER;
            Integer ruleTwoFlag = null;
            Integer ruleThreeFlag = null;

            if (!CollectionUtils.isEmpty(bigWayList)) {
                ThreeStrongWayDs threeStrongWayDs = bigWayList.get(Constants.DEFAULT_INTEGER);

                if (null != threeStrongWayDs) {
                    bigk = threeStrongWayDs.getBigk() == null ? Constants.DEFAULT_INTEGER : threeStrongWayDs.getBigk();
                    bigl = threeStrongWayDs.getBigl() == null ? Constants.DEFAULT_INTEGER : threeStrongWayDs.getBigl();
                    ruleTwoFlag = threeStrongWayDs.getRuleTwoFlag();
                    ruleThreeFlag = threeStrongWayDs.getRuleThreeFlag();
                }
            }
            /**
             *  按照规则一条件： Bn = 1 或 2  计算
             */
            // 获取当期大路数据
            Integer pastNum = mainWayList.get(Constants.DEFAULT_INTEGER).getMinb();
            if (Constants.DEFAULT_ONE.equals(pastNum) || Constants.DEFAULT_TWO.equals(pastNum)) {
                // 规则一
                strongValue = ruleOneBigEye(mainWayList, bigk, bigl);
            }
            if (CollectionUtil.isNotEmpty(strongValue)) {
                return strongValue;
            }
            /**
             * 照规则二条件： 上期是规则三计算， 且X>=1
             */
            if (mainWayList.size() < Constants.DEFAULT_FOUR) {
                return strongValue;
            }
            // 获取上二期大路数据
            Integer threePastNum = mainWayList.get(Constants.DEFAULT_THREE).getMinb();

            if ((null != ruleTwoFlag && ruleTwoFlag > Constants.DEFAULT_ONE) || (null != ruleThreeFlag && ruleThreeFlag >= Constants.DEFAULT_ONE)) {
                // 规则二
                strongValue = ruleTwoBigEye(pastNum, threePastNum, bigk, bigl);
            }
            if (CollectionUtil.isNotEmpty(strongValue)) {
                return strongValue;
            }
            /**
             * 照规则三条件： 判断 Bn 为单
             */
            strongValue = ruleThreeBigEye(pastNum, threePastNum, bigk, bigl);
        } catch (Exception e) {
            logger.error("计算曱甴路时异常：", e);
        }
        return strongValue;
    }

    /**
     * @Description: 规则一
     */
    private static Map<String, Integer> ruleOneBigEye(List<ThreeMainWayDs> mainWayList, Integer bigk, Integer bigl) {
        Map<String, Integer> ruleOneValue = new HashMap<String, Integer>();
        try {
            // 计算当期X值
            Integer xValue = getStrongTwoGroupHeight(mainWayList);

            if (!Constants.DEFAULT_INTEGER.equals(xValue) && Constants.DEFAULT_ONE.equals((bigl & Constants.DEFAULT_ONE))) {
                ruleOneValue.put(Constants.THREE_WAY_PART_A, bigk + Constants.DEFAULT_ONE);
                ruleOneValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_TWO);
            }


            if (Constants.DEFAULT_INTEGER.equals(xValue) && Constants.DEFAULT_ONE.equals(bigl & Constants.DEFAULT_ONE)) {
                ruleOneValue.put(Constants.THREE_WAY_PART_B, bigl + Constants.DEFAULT_TWO);

                if (Constants.DEFAULT_INTEGER.equals(bigk)) {
                    ruleOneValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
                }

                if (bigk > Constants.DEFAULT_INTEGER) {
                    ruleOneValue.put(Constants.THREE_WAY_PART_A, bigk);
                }
            }

            if (Constants.DEFAULT_INTEGER.equals(xValue) && Constants.DEFAULT_INTEGER.equals(bigl & Constants.DEFAULT_ONE)) {
                ruleOneValue.put(Constants.THREE_WAY_PART_A, bigk + Constants.DEFAULT_ONE);
                ruleOneValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_ONE);
            }

            if (!Constants.DEFAULT_INTEGER.equals(xValue) && Constants.DEFAULT_INTEGER.equals(bigl & Constants.DEFAULT_ONE)) {
                ruleOneValue.put(Constants.THREE_WAY_PART_B, bigl + Constants.DEFAULT_TWO);

                if (Constants.DEFAULT_INTEGER.equals(bigk)) {
                    ruleOneValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
                }

                if (bigk > Constants.DEFAULT_INTEGER) {
                    ruleOneValue.put(Constants.THREE_WAY_PART_A, bigk);
                }
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
    private static Map<String, Integer> ruleTwoBigEye(Integer pastNum, Integer threePastNum, Integer bigk, Integer bigl) {
        Map<String, Integer> ruleTwoValue = new HashMap<String, Integer>();
        try {
            Integer xValue = Constants.DEFAULT_INTEGER;
            // 计算当期X值
            if ((pastNum & 1) == 1) {
                xValue = (pastNum + Constants.DEFAULT_ONE) - threePastNum;
            } else {
                xValue = pastNum - (threePastNum + Constants.DEFAULT_ONE);
            }

            if (xValue >= Constants.DEFAULT_ONE && (bigl & Constants.DEFAULT_ONE) == Constants.DEFAULT_INTEGER) {
                ruleTwoValue.put(Constants.THREE_WAY_PART_A, bigk + Constants.DEFAULT_ONE);
                ruleTwoValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_ONE);
            }

            if (xValue >= Constants.DEFAULT_ONE && (bigl & Constants.DEFAULT_ONE) == Constants.DEFAULT_ONE) {
                ruleTwoValue.put(Constants.THREE_WAY_PART_A, bigk);
                ruleTwoValue.put(Constants.THREE_WAY_PART_B, bigl + Constants.DEFAULT_TWO);
            }
            ruleTwoValue.put(Constants.THREE_WAY_PART_C, Constants.DEFAULT_NEGATIVE);
            ruleTwoValue.put(Constants.THREE_WAY_PART_D, Constants.DEFAULT_TWO);
        } catch (Exception e) {
            logger.error("小路==>规则一计算错误：", e);
        }
        return ruleTwoValue;
    }

    /**
     * @Description: 规则三
     */
    private static Map<String, Integer> ruleThreeBigEye(Integer pastNum, Integer threePastNum, Integer bigk, Integer bigl) {
        Map<String, Integer> ruleThreeValue = new HashMap<String, Integer>();
        try {
            // 计算当期X值
            Integer xValue = Constants.DEFAULT_INTEGER;
            // 计算当期X值
            if ((pastNum & 1) == 1) {
                xValue = (pastNum + Constants.DEFAULT_ONE) - threePastNum;
            } else {
                xValue = pastNum - (threePastNum + Constants.DEFAULT_ONE);
            }

            if (xValue >= Constants.DEFAULT_ONE && (bigl & Constants.DEFAULT_ONE) == Constants.DEFAULT_ONE) {
                ruleThreeValue.put(Constants.THREE_WAY_PART_A, bigk + Constants.DEFAULT_ONE);
                ruleThreeValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_TWO);
                ruleThreeValue.put(Constants.THREE_WAY_PART_C, xValue);
            }

            if (xValue < Constants.DEFAULT_ONE && (bigl & Constants.DEFAULT_ONE) == Constants.DEFAULT_INTEGER) {
                ruleThreeValue.put(Constants.THREE_WAY_PART_A, bigk + Constants.DEFAULT_ONE);
                ruleThreeValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_ONE);
                ruleThreeValue.put(Constants.THREE_WAY_PART_C, xValue);
            }


            if (xValue < Constants.DEFAULT_ONE && (bigl & Constants.DEFAULT_ONE) == Constants.DEFAULT_ONE) {
                if (Constants.DEFAULT_INTEGER.equals(bigk)) {
                    ruleThreeValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
                }

                if (bigk > Constants.DEFAULT_INTEGER) {
                    ruleThreeValue.put(Constants.THREE_WAY_PART_A, bigk);
                }
                ruleThreeValue.put(Constants.THREE_WAY_PART_C, xValue);
                ruleThreeValue.put(Constants.THREE_WAY_PART_B, bigl + Constants.DEFAULT_TWO);
            }

            if (xValue >= Constants.DEFAULT_ONE && (bigl & Constants.DEFAULT_ONE) == Constants.DEFAULT_INTEGER) {
                if (Constants.DEFAULT_INTEGER.equals(bigk)) {
                    ruleThreeValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
                }

                if (bigk > Constants.DEFAULT_INTEGER) {
                    ruleThreeValue.put(Constants.THREE_WAY_PART_A, bigk);
                }
                ruleThreeValue.put(Constants.THREE_WAY_PART_C, xValue);
                ruleThreeValue.put(Constants.THREE_WAY_PART_B, bigl + Constants.DEFAULT_TWO);
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
    private static Integer getStrongTwoGroupHeight(List<ThreeMainWayDs> mainWayList) {
        if (mainWayList.size() < Constants.DEFAULT_FIVE) {
            return Constants.DEFAULT_NEGATIVE;
        }
        // 获取上三期大路数据
        Integer onePastNum = mainWayList.get(Constants.DEFAULT_THREE).getMinb();
        Integer oneNumHeight = ThreeLargeSeedUtils.getWaybHeight(onePastNum);
        // 获取上四期大路数据
        Integer threePastNum = mainWayList.get(Constants.DEFAULT_FOUR).getMinb();
        Integer threeNumHeight = ThreeLargeSeedUtils.getWaybHeight(threePastNum);
        // 计算  (B【An-3】+ math.floor(B【An-3】)%2)-(B【An-4】+ math.floor(B【An-4】)%2)
        Integer xValue = oneNumHeight - threeNumHeight;
        return xValue;
    }

    /**
     * 计算开始的第一个条件   B[A4] = 4或者3
     *
     * @param initialMainList
     * @return
     */
    private static boolean waybFourthCondition(List<ThreeMainWayDs> initialMainList) {
        if (initialMainList.size() < Constants.DEFAULT_FOUR) {
            return true;
        }
        // 计算条件  B[A4] = 4或者3，则开始计算, 否则 B【A5】=1或者2，则开始计算，否则等待下期
        ThreeMainWayDs fourMainWayDs = initialMainList.get(Constants.DEFAULT_THREE);

        if (null == fourMainWayDs) {
            return true;
        }
        Integer fourPastNum = fourMainWayDs.getMinb(); // 大路第三期

        return fourPastNum < Constants.DEFAULT_THREE;
    }

    /**
     * 计算开始的第二个条件   B【A5】=1或者2
     *
     * @param initialMainList
     * @return
     */
    private static boolean waybFivethCondition(List<ThreeMainWayDs> initialMainList) {
        if (initialMainList.size() < Constants.DEFAULT_FIVE) {
            return true;
        }
        // 计算条件  B[A4] = 4或者3，则开始计算, 否则 B【A5】=1或者2，则开始计算，否则等待下期
        ThreeMainWayDs fiveMainWayDs = initialMainList.get(Constants.DEFAULT_FOUR);

        if (null == fiveMainWayDs) {
            return true;
        }
        Integer fivePastNum = fiveMainWayDs.getMinb();// 大路第三期

        return fivePastNum < Constants.DEFAULT_ONE;
    }


}
