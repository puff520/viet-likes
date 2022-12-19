package com.likes.common.util.threeway;

import com.likes.common.constant.Constants;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: ThreeMainWayUtils
 * @Description: 下三路算法：大路
 * @author: HANS
 * @date: 2019年6月11日 下午4:11:13
 */
public class ThreeMainWayUtils {
    private static final Logger logger = LoggerFactory.getLogger(ThreeMainWayUtils.class);

    /**
     * @Title: mainWay
     * @Description: 大路
     * @author HANS
     * @date 2019年8月28日下午6:43:32
     */
    public static Map<String, Integer> mainWay(boolean first, String type, String pastType, Map<String, Integer> pastNumMap) {
        Map<String, Integer> mainWayValue = new HashMap<String, Integer>();
        try {
            // 第一期
            if (first) {
                mainWayValue = firstIssue(type);
                return mainWayValue;
            }
            mainWayValue = otherIssue(type, pastType, pastNumMap);
            return mainWayValue;
        } catch (Exception e) {
            logger.error("计算大路算法时异常：", e);
        }
        return mainWayValue;
    }

    /**
     * @Description:第一期开奖计算
     */
    private static Map<String, Integer> firstIssue(String type) {
        Map<String, Integer> mainWayValue = new HashMap<String, Integer>();

        if (StringUtils.isEmpty(type)) {
            return mainWayValue;
        }
        // 单
        if (Constants.BIGORSMALL_ODD_NUMBER == type) {
            mainWayValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
            mainWayValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_ONE);
        }
        // 双
        if (Constants.BIGORSMALL_EVEN_NUMBER == type) {
            mainWayValue.put(Constants.THREE_WAY_PART_A, Constants.DEFAULT_ONE);
            mainWayValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_TWO);
        }
        mainWayValue.put(Constants.THREE_WAY_PART_C, Constants.DEFAULT_INTEGER);
        return mainWayValue;
    }

    /**
     * @Description:除过第一期，其他计算方法 说明：大路计算公式
     */
    private static Map<String, Integer> otherIssue(String type, String pastType, Map<String, Integer> pastNumMap) {
        Map<String, Integer> mainWayValue = new HashMap<String, Integer>();

        if (CollectionUtil.isEmpty(pastNumMap)) {
            return mainWayValue;
        }
        // 单
        if (Constants.BIGORSMALL_ODD_NUMBER == type) {
            mainWayValue = getNextForNumIsSigle(pastType, pastNumMap);
        }

        // 双
        if (Constants.BIGORSMALL_EVEN_NUMBER == type) {
            mainWayValue = getNextForNumIsDouble(pastType, pastNumMap);
        }

        // 和
        if (Constants.BIGORSMALL_SAME == type) {
            mainWayValue = getNextForNumIsAnd(pastType, pastNumMap);
        }
        return mainWayValue;
    }

    /**
     * @Description: 当前期是单，计算大路值
     */
    private static Map<String, Integer> getNextForNumIsSigle(String pastType, Map<String, Integer> pastNumMap) {
        Map<String, Integer> mainWayValue = new HashMap<>();
        // 获取上期数据
        Integer pastIndex = pastNumMap.get(Constants.THREE_WAY_PART_A);
        Integer pastNum = pastNumMap.get(Constants.THREE_WAY_PART_B);
        // 上期单这期单
        if (Constants.BIGORSMALL_ODD_NUMBER == pastType) {
            pastNum += Constants.DEFAULT_TWO;
            mainWayValue.put(Constants.THREE_WAY_PART_A, pastIndex);
            mainWayValue.put(Constants.THREE_WAY_PART_B, pastNum);
        }

        // 上期双这期单
        if (Constants.BIGORSMALL_EVEN_NUMBER == pastType) {
            pastIndex += Constants.DEFAULT_ONE;
            mainWayValue.put(Constants.THREE_WAY_PART_A, pastIndex);
            mainWayValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_ONE);
        }
        mainWayValue.put(Constants.THREE_WAY_PART_C, Constants.DEFAULT_INTEGER);
        return mainWayValue;
    }

    /**
     * @Description: 当前期是双，计算大路值
     */
    private static Map<String, Integer> getNextForNumIsDouble(String pastType, Map<String, Integer> pastNumMap) {
        Map<String, Integer> mainWayValue = new HashMap<>();
        // 获取上期数据
        Integer pastIndex = pastNumMap.get(Constants.THREE_WAY_PART_A);
        Integer pastNum = pastNumMap.get(Constants.THREE_WAY_PART_B);
        // 上期双这期双
        if (Constants.BIGORSMALL_EVEN_NUMBER == pastType) {
            pastNum += Constants.DEFAULT_TWO;
            mainWayValue.put(Constants.THREE_WAY_PART_A, pastIndex);
            mainWayValue.put(Constants.THREE_WAY_PART_B, pastNum);
        }

        // 上期单这期双
        if (Constants.BIGORSMALL_ODD_NUMBER == pastType) {
            pastIndex += Constants.DEFAULT_ONE;
            mainWayValue.put(Constants.THREE_WAY_PART_A, pastIndex);
            mainWayValue.put(Constants.THREE_WAY_PART_B, Constants.DEFAULT_TWO);
        }
        mainWayValue.put(Constants.THREE_WAY_PART_C, Constants.DEFAULT_INTEGER);
        return mainWayValue;
    }

    /**
     * @Description: 当前期是和，计算大路值
     */
    private static Map<String, Integer> getNextForNumIsAnd(String pastType, Map<String, Integer> pastNumMap) {
        Map<String, Integer> mainWayValue = new HashMap<String, Integer>();
        // 获取上期数据
        Integer pastIndex = pastNumMap.get(Constants.THREE_WAY_PART_A);
        Integer pastNum = pastNumMap.get(Constants.THREE_WAY_PART_B);
        // 记录当前和的位置下标
        Integer location = Constants.DEFAULT_INTEGER;
        pastNum += Constants.DEFAULT_TWO;
        // 上期单，这期单
        if (Constants.BIGORSMALL_ODD_NUMBER == pastType) {
            // 计算位置下标
            location = getLocationNum(pastNum);
        }
        // 上期双，这期双
        if (Constants.BIGORSMALL_EVEN_NUMBER == pastType) {
            // 计算下标
            location = getLocationNum(pastNum);
        }
        mainWayValue.put(Constants.THREE_WAY_PART_A, pastIndex);
        mainWayValue.put(Constants.THREE_WAY_PART_B, pastNum);
        // 记录打和的位置
        mainWayValue.put(Constants.THREE_WAY_PART_C, location);
        return mainWayValue;
    }

    /**
     * 计算当前号码在走势图位置下标
     *
     * @param pastNum
     * @return
     */
    private static Integer getLocationNum(Integer pastNum) {
        Integer location = Constants.DEFAULT_INTEGER;
        int numCount = pastNum / Constants.DEFAULT_TWO;

        if ((pastNum & Constants.DEFAULT_ONE) != Constants.DEFAULT_ONE) {
            location = numCount;
        } else {
            location = numCount + 1;
        }
        return location;
    }

}
