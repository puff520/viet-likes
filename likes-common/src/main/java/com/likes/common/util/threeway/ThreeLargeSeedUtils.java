package com.likes.common.util.threeway;


import com.likes.common.constant.Constants;
import com.likes.common.mybatis.entity.ThreeLargeSeedDs;
import com.likes.common.mybatis.entity.ThreeMainWayDs;
import com.likes.common.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ThreeLargeSeedUtils
 * @Description: 下三路算法：大眼仔
 * @author: HANS
 * @date: 2019年6月11日 下午4:11:13
 */
public class ThreeLargeSeedUtils {
    private static final Logger logger = LoggerFactory.getLogger(ThreeLargeSeedUtils.class);

    /**
     * @Title: mainWay
     * @Description: 大眼仔  说明：如果大路没有，不用计算
     * @author HANS
     * @date 2019年8月28日下午6:43:32
     */
    public static Map<String, Integer> largeSeed(List<ThreeMainWayDs> initialMainList, List<ThreeMainWayDs> mainWayList, List<ThreeLargeSeedDs> largeSeeList) {
        Map<String, Integer> largeSeedValue = new HashMap<String, Integer>();
        try {
            if (CollectionUtils.isEmpty(initialMainList)) {
                return largeSeedValue;
            }
            if (CollectionUtils.isEmpty(mainWayList)) {
                return largeSeedValue;
            }
            // 计算条件  B[A2] = 4或者3，则开始计算, 否则 B【A3】=1或者2，则开始计算，否则等待下期
            boolean conditionOne = waybSecondCondition(initialMainList);
            boolean conditionTwo = waybThridCondition(initialMainList);

            if (conditionOne && conditionTwo) {
                return largeSeedValue;
            }
            // 获取上期大眼仔数据
            Integer largeD = Constants.DEFAULT_INTEGER;
            Integer largeE = Constants.DEFAULT_INTEGER;
            Integer ruleTwoFlag = null;
            Integer ruleThreeFlag = null;

            if (!CollectionUtils.isEmpty(largeSeeList)) {
                ThreeLargeSeedDs threeLargeSeedDs = largeSeeList.get(Constants.DEFAULT_INTEGER);

                if (null != threeLargeSeedDs) {
                    largeD = threeLargeSeedDs.getLarged() == null ? Constants.DEFAULT_INTEGER : threeLargeSeedDs.getLarged();
                    largeE = threeLargeSeedDs.getLargee() == null ? Constants.DEFAULT_INTEGER : threeLargeSeedDs.getLargee();
                    ruleTwoFlag = threeLargeSeedDs.getRuleTwoFlag();
                    ruleThreeFlag = threeLargeSeedDs.getRuleThreeFlag();
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
                largeSeedValue = ruleOneLargeSee(mainWayList, largeD, largeE);
            }
            if (CollectionUtil.isNotEmpty(largeSeedValue)) {
                return largeSeedValue;
            }
            /**
             * 照规则二条件： 上期是规则三计算， 且X>=1
             */
            if (mainWayList.size() < Constants.DEFAULT_TWO) {
                return largeSeedValue;
            }
            // 获取上一期大路数据
            Integer onePastNum = mainWayList.get(Constants.DEFAULT_ONE).getMinb();

            if ((null != ruleTwoFlag && ruleTwoFlag > Constants.DEFAULT_ONE) || (null != ruleThreeFlag && ruleThreeFlag >= Constants.DEFAULT_ONE)) {
                // 规则二
                largeSeedValue = ruleTwoLargeSee(pastNum, onePastNum, largeD, largeE);
            }
            if (CollectionUtil.isNotEmpty(largeSeedValue)) {
                return largeSeedValue;
            }
            /**
             * 照规则三条件： 判断 Bn 为单
             */
            largeSeedValue = ruleThreeLargeSee(pastNum, onePastNum, largeD, largeE);
        } catch (Exception e) {
            logger.error("计算大眼仔时异常：", e);
        }
        return largeSeedValue;
    }

    /**
     * @Description: 规则一
     */
    private static Map<String, Integer> ruleOneLargeSee(List<ThreeMainWayDs> mainWayList, Integer largeD, Integer largeE) {
        Map<String, Integer> largeRuleOneValue = new HashMap<>();
        try {
            Integer largeXalue = getLargeTwoGroupHeight(mainWayList);

            if (Constants.DEFAULT_INTEGER.equals(largeXalue) && Constants.DEFAULT_ONE.equals(largeE & Constants.DEFAULT_ONE)) {
                if (Constants.DEFAULT_INTEGER.equals(largeD)) {
                    largeRuleOneValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
                }

                if (largeD > Constants.DEFAULT_INTEGER) {
                    largeRuleOneValue.put(Constants.THREE_WAY_PART_A, largeD);
                }
                largeRuleOneValue.put(Constants.THREE_WAY_PART_B, largeE + Constants.DEFAULT_TWO);
            }

            if (Constants.DEFAULT_INTEGER.equals(largeXalue) && Constants.DEFAULT_INTEGER.equals(largeE & Constants.DEFAULT_ONE)) {
                largeRuleOneValue.put(Constants.THREE_WAY_PART_A, largeD + Constants.DEFAULT_ONE);
                largeRuleOneValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_ONE);
            }

            if (!Constants.DEFAULT_INTEGER.equals(largeXalue) && Constants.DEFAULT_INTEGER.equals(largeE & Constants.DEFAULT_ONE)) {
                if (Constants.DEFAULT_INTEGER.equals(largeD)) {
                    largeRuleOneValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
                }

                if (largeD > Constants.DEFAULT_INTEGER) {
                    largeRuleOneValue.put(Constants.THREE_WAY_PART_A, largeD);
                }
                largeRuleOneValue.put(Constants.THREE_WAY_PART_B, largeE + Constants.DEFAULT_TWO);
            }

            if (!Constants.DEFAULT_INTEGER.equals(largeXalue) && Constants.DEFAULT_ONE.equals(largeE & Constants.DEFAULT_ONE)) {
                largeRuleOneValue.put(Constants.THREE_WAY_PART_A, largeD + Constants.DEFAULT_ONE);
                largeRuleOneValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_TWO);
            }
            largeRuleOneValue.put(Constants.THREE_WAY_PART_C, Constants.DEFAULT_NEGATIVE);
            largeRuleOneValue.put(Constants.THREE_WAY_PART_D, Constants.DEFAULT_NEGATIVE);
        } catch (Exception e) {
            logger.error("大眼仔==>规则一计算错误：", e);
        }
        return largeRuleOneValue;
    }

    /**
     * @Description: 规则二
     */
    private static Map<String, Integer> ruleTwoLargeSee(Integer pastNum, Integer onePastNum, Integer largeD, Integer largeE) {
        Map<String, Integer> ruleTwoValue = new HashMap<String, Integer>();
        try {
            Integer xValue = Constants.DEFAULT_INTEGER;
            // 计算当期X值
            if ((pastNum & 1) == 1) {
                xValue = (pastNum + Constants.DEFAULT_ONE) - onePastNum;
            } else {
                xValue = pastNum - (onePastNum + Constants.DEFAULT_ONE);
            }

            if (xValue >= Constants.DEFAULT_ONE && (largeE & Constants.DEFAULT_ONE) == Constants.DEFAULT_ONE) {
                ruleTwoValue.put(Constants.THREE_WAY_PART_A, largeD);
                ruleTwoValue.put(Constants.THREE_WAY_PART_B, largeE + Constants.DEFAULT_TWO);
            }

            if (xValue >= Constants.DEFAULT_ONE && (largeE & Constants.DEFAULT_ONE) == Constants.DEFAULT_INTEGER) {
                ruleTwoValue.put(Constants.THREE_WAY_PART_A, largeD + Constants.DEFAULT_ONE);
                ruleTwoValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_ONE);
            }
            ruleTwoValue.put(Constants.THREE_WAY_PART_C, Constants.DEFAULT_NEGATIVE);
            ruleTwoValue.put(Constants.THREE_WAY_PART_D, Constants.DEFAULT_TWO);
        } catch (Exception e) {
            logger.error("大眼仔==>规则二计算错误：", e);
        }
        return ruleTwoValue;
    }

    /**
     * @Description: 规则三
     */
    private static Map<String, Integer> ruleThreeLargeSee(Integer pastNum, Integer onePastNum, Integer largeD, Integer largeE) {
        Map<String, Integer> ruleThreeValue = new HashMap<String, Integer>();
        try {
            // 计算当期X值
            Integer xValue = Constants.DEFAULT_INTEGER;
            // 计算当期X值
            if ((pastNum & 1) == 1) {
                xValue = (pastNum + Constants.DEFAULT_ONE) - onePastNum;
            } else {
                xValue = pastNum - (onePastNum + Constants.DEFAULT_ONE);
            }

            if (xValue >= Constants.DEFAULT_ONE && (largeE & Constants.DEFAULT_ONE) == Constants.DEFAULT_INTEGER) {
                if (Constants.DEFAULT_INTEGER.equals(largeD)) {
                    ruleThreeValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
                }

                if (largeD > Constants.DEFAULT_INTEGER) {
                    ruleThreeValue.put(Constants.THREE_WAY_PART_A, largeD);
                }
                ruleThreeValue.put(Constants.THREE_WAY_PART_B, largeE + Constants.DEFAULT_TWO);
            }

            if (xValue >= Constants.DEFAULT_ONE && (largeE & Constants.DEFAULT_ONE) == Constants.DEFAULT_ONE) {
                ruleThreeValue.put(Constants.THREE_WAY_PART_A, largeD + Constants.DEFAULT_ONE);
                ruleThreeValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_TWO);
            }

            if (xValue < Constants.DEFAULT_ONE && (largeE & Constants.DEFAULT_ONE) == Constants.DEFAULT_ONE) {
                if (Constants.DEFAULT_INTEGER.equals(largeD)) {
                    ruleThreeValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
                }

                if (largeD > Constants.DEFAULT_INTEGER) {
                    ruleThreeValue.put(Constants.THREE_WAY_PART_A, largeD);
                }
                ruleThreeValue.put(Constants.THREE_WAY_PART_B, largeE + Constants.DEFAULT_TWO);
            }

            if (xValue < Constants.DEFAULT_ONE && (largeE & Constants.DEFAULT_ONE) == Constants.DEFAULT_INTEGER) {
                ruleThreeValue.put(Constants.THREE_WAY_PART_A, largeD + Constants.DEFAULT_ONE);
                ruleThreeValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_ONE);
            }
            ruleThreeValue.put(Constants.THREE_WAY_PART_C, xValue);
            ruleThreeValue.put(Constants.THREE_WAY_PART_D, Constants.DEFAULT_NEGATIVE);
        } catch (Exception e) {
            logger.error("大眼仔==>规则三计算错误：", e);
        }
        return ruleThreeValue;
    }

    /**
     * 获取当期和上一期高度差
     *
     * @param mainWayList
     * @return
     */
    private static Integer getLargeTwoGroupHeight(List<ThreeMainWayDs> mainWayList) {
        if (mainWayList.size() < Constants.DEFAULT_THREE) {
            return Constants.DEFAULT_NEGATIVE;
        }
        // 获取上一期大路数据
        Integer onePastNum = mainWayList.get(Constants.DEFAULT_ONE).getMinb();
        Integer oneNumHeight = getWaybHeight(onePastNum);
        // 获取上二期大路数据
        Integer twoPastNum = mainWayList.get(Constants.DEFAULT_TWO).getMinb();
        Integer twoNumHeight = getWaybHeight(twoPastNum);
        // 计算  (B【An-1】+ math.floor(B【An-1】)%2)-(B【An-2】+ math.floor(B【An-2】)%2)
        Integer xValue = oneNumHeight - twoNumHeight;
        return xValue;
    }

    /**
     * 计算B路的高度
     *
     * @param bwayValue
     * @return
     */
    public static Integer getWaybHeight(Integer bwayValue) {
        Integer valueHeight = Constants.DEFAULT_INTEGER;
        try {
            if (bwayValue < Constants.DEFAULT_THREE) {
                return Constants.DEFAULT_ONE;
            }
            // Sn = n(a1+an)/2
            if ((bwayValue & Constants.DEFAULT_ONE) != Constants.DEFAULT_ONE) {
                // 和值为双
                valueHeight = bwayValue / Constants.DEFAULT_TWO;
            } else {
                // 和值为单
                valueHeight = (bwayValue + Constants.DEFAULT_ONE) / Constants.DEFAULT_TWO;
            }
        } catch (Exception e) {
            logger.error("计算大眼仔B路高度公式异常：", e);
        }
        return valueHeight;
    }

    /**
     * 计算开始的第一个条件   B[A2] = 4或者3
     *
     * @param initialMainList
     * @return
     */
    private static boolean waybSecondCondition(List<ThreeMainWayDs> initialMainList) {
        if (initialMainList.size() < Constants.DEFAULT_TWO) {
            return true;
        }
        // 计算条件  B[A2] = 4或者3，则开始计算, 否则 B【A3】=1或者2，则开始计算，否则等待下期
        ThreeMainWayDs twoMainWayDs = initialMainList.get(Constants.DEFAULT_ONE);

        if (null == twoMainWayDs) {
            return true;
        }
        Integer onePastNum = twoMainWayDs.getMinb(); // 大路第二期

        return onePastNum < Constants.DEFAULT_THREE;
    }

    /**
     * 计算开始的第二个条件   B【A3】=1或者2
     *
     * @param initialMainList
     * @return
     */
    private static boolean waybThridCondition(List<ThreeMainWayDs> initialMainList) {
        if (initialMainList.size() < Constants.DEFAULT_THREE) {
            return true;
        }
        ThreeMainWayDs threeMainWayDs = initialMainList.get(Constants.DEFAULT_TWO);

        if (null == threeMainWayDs) {
            return true;
        }
        Integer twoPastNum = threeMainWayDs.getMinb();// 大路第三期

        return twoPastNum < Constants.DEFAULT_ONE;
    }


}
