package com.likes.common.util.threeway;


import com.likes.common.constant.Constants;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: ThreeWayConversionUtils
 * @Description: 下三路接口数据反算公式
 * @author: HANS
 * @date: 2019年6月11日 下午4:11:13
 */
public class ThreeWayBackCalculationUtils {
    private static final Logger logger = LoggerFactory.getLogger(ThreeWayBackCalculationUtils.class);

    /**
     * 单，双 数据反算公式
     *
     * @return
     */
    public static String backCalculation(Integer numberB, String heLocation) {
        String result = Constants.DEFAULT_NULL;
        try {
            if ((numberB & Constants.DEFAULT_ONE) != Constants.DEFAULT_ONE) {
                int numCount = numberB / Constants.DEFAULT_TWO;
                // 和值为双
                result = getShowResult(numCount, Constants.BIGORSMALL_EVEN_NUMBER, heLocation);
            } else {
                int numCount = (numberB + Constants.DEFAULT_ONE) / Constants.DEFAULT_TWO;
                // 和值为单
                result = getShowResult(numCount, Constants.BIGORSMALL_ODD_NUMBER, heLocation);
            }

        } catch (Exception e) {
            logger.error("下三路反算单，双公式异常：", e);
        }
        return result;
    }

    /**
     * 大，小 数据反算公式
     *
     * @return
     */
    public static String backBigSmallCalculation(Integer numberB, String heLocation) {
        String dxResult = Constants.DEFAULT_NULL;
        try {
            if ((numberB & Constants.DEFAULT_ONE) != Constants.DEFAULT_ONE) {
                int numCount = numberB / Constants.DEFAULT_TWO;
                // 双为小
                dxResult = getShowResult(numCount, Constants.BIGORSMALL_SMALL, heLocation);
            } else {
                int numCount = (numberB + Constants.DEFAULT_ONE) / Constants.DEFAULT_TWO;
                // 单为大
                dxResult = getShowResult(numCount, Constants.BIGORSMALL_BIG, heLocation);
            }
        } catch (Exception e) {
            logger.error("下三路反算大，小公式异常：", e);
        }
        return dxResult;
    }

    /**
     * 龙，虎 数据反算公式
     *
     * @param numberB
     * @return
     */
    public static String backDragonAndtiger(Integer numberB) {
        String dtResult = Constants.DEFAULT_NULL;
        try {
            if (numberB < Constants.DEFAULT_TWO) {
                dtResult = Constants.PLAYRESULT_DRAGON;
                return dtResult;
            }
            int numCount = numberB / Constants.DEFAULT_TWO;

            if ((numberB & Constants.DEFAULT_ONE) != Constants.DEFAULT_ONE) {
                // 双为虎
                dtResult = getShowResult(numCount, Constants.PLAYRESULT_TIGER, Constants.DEFAULT_NULL);
            } else {
                // 单为龙
                dtResult = getShowResult(numCount + 1, Constants.PLAYRESULT_DRAGON, Constants.DEFAULT_NULL);
            }
        } catch (Exception e) {
            logger.error("下三路反算龙，虎公式异常：", e);
        }
        return dtResult;
    }

    /**
     * 家禽和野兽反算
     *
     * @param numberB
     * @return
     */
    public static String backAnimal(Integer numberB) {
        String qsResult = Constants.DEFAULT_NULL;
        try {
            if (numberB < Constants.DEFAULT_TWO) {
                qsResult = Constants.BIGORSMALL_POULTRY_SHORT;
                return qsResult;
            }
            int numCount = numberB / Constants.DEFAULT_TWO;

            if ((numberB & Constants.DEFAULT_ONE) != Constants.DEFAULT_ONE) {
                // 双为兽
                qsResult = getShowResult(numCount, Constants.BIGORSMALL_BEAST_SHORT, Constants.DEFAULT_NULL);
            } else {
                // 单为禽
                qsResult = getShowResult(numCount + 1, Constants.BIGORSMALL_POULTRY_SHORT, Constants.DEFAULT_NULL);
            }
        } catch (Exception e) {
            logger.error("下三路反算家禽，野兽公式异常：", e);
        }
        return qsResult;
    }

    /**
     * 结构计算，返回前端
     *
     * @param numCount
     * @param type
     * @return
     */
    private static String getShowResult(int numCount, String type, String heLocation) {
        StringBuffer resultBuf = new StringBuffer();
        Set<Integer> locationTotalSet = getHeLocation(heLocation);

        for (int num = 1; num <= numCount; num++) {
            if (CollectionUtil.isEmpty(locationTotalSet)) {
                resultBuf.append(type);
                continue;
            }
            boolean isHeFlag = locationTotalSet.contains(num);

            if (isHeFlag) {
                resultBuf.append(Constants.BIGORSMALL_SAME);
            } else {
                resultBuf.append(type);
            }
        }
        return resultBuf.toString();
    }

    /**
     * 获取和的位置
     *
     * @param heLocation
     */
    private static Set<Integer> getHeLocation(String heLocation) {
        Set<Integer> locationTotalSet = new HashSet<>();
        try {
            if (StringUtils.isEmpty(heLocation)) {
                return locationTotalSet;
            }
            String[] heArray = heLocation.split(",");

            if (null != heArray && heArray.length > Constants.DEFAULT_INTEGER) {
                for (String location : heArray) {
                    Integer index = Integer.valueOf(location);
                    locationTotalSet.add(index);
                }
            }
        } catch (Exception e) {
            logger.error("获取和位置异常：", e);
        }
        return locationTotalSet;
    }

    /**
     * 计算数字的单、双属性
     *
     * @param typeValue
     * @return
     */
    public static String calculationSigleAndDouble(Integer typeValue) {
        String calculationResult = Constants.DEFAULT_NULL;
        try {
            if ((typeValue & Constants.DEFAULT_ONE) != Constants.DEFAULT_ONE) {
                // 偶数
                calculationResult = Constants.BIGORSMALL_EVEN_NUMBER;
            } else {
                // 奇数
                calculationResult = Constants.BIGORSMALL_ODD_NUMBER;
            }
        } catch (Exception e) {
            logger.error("计算数字的单、双属性异常：", e);
        }
        return calculationResult;
    }

    /**
     *  计算数字的大、小属性
     * @param typeValue
     * @return
     */
    public static String calculationBigAndSmall(Integer typeValue) {
        String calculationResult = Constants.DEFAULT_NULL;
        try {
            if ((typeValue & Constants.DEFAULT_ONE) != Constants.DEFAULT_ONE) {
                // 偶数
                calculationResult = Constants.BIGORSMALL_SMALL;
            } else {
                // 奇数
                calculationResult = Constants.BIGORSMALL_BIG;
            }
        } catch (Exception e) {
            logger.error("计算数字的大、小属性异常：", e);
        }
        return calculationResult;
    }

    /**
     * 计算请求单双还是大小类型
     *
     * @param typeNum
     * @return
     */
    public static String getThreeWayLevel(Integer typeNum) {
        String groupResult = Constants.DEFAULT_NULL;
        Set<Integer> dsList = Constants.THREE_DS_GROUP;
        Set<Integer> dxList = Constants.THREE_DX_GROUP;

        if (dsList.contains(typeNum)) {
            groupResult = Constants.THREE_DS_LEVEL;
            return groupResult;
        }
        if (dxList.contains(typeNum)) {
            groupResult = Constants.THREE_DX_LEVEL;
            return groupResult;
        }
        return groupResult;
    }




}
