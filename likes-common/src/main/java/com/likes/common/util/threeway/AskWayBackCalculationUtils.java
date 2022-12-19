package com.likes.common.util.threeway;


import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.result.ThreeWayAskReturn;
import com.likes.common.model.dto.result.ThreeWayBigAndSmallReturn;
import com.likes.common.model.dto.result.ThreeWaySigleAndDoubleReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ThreeWayConversionUtils
 * @Description: 下三路问路工具类
 * @author: HANS
 * @date: 2019年6月11日 下午4:11:13
 */
public class AskWayBackCalculationUtils {
    private static final Logger logger = LoggerFactory.getLogger(AskWayBackCalculationUtils.class);

    public static ResultInfo<ThreeWayAskReturn> organizationOddAndEvenData(String playType, String pastType) {
        ResultInfo<ThreeWayAskReturn> resultInfo = ResultInfo.getInstance();
        try {
            // A路一样返回1条
            if (playType.equalsIgnoreCase(pastType)) {
                resultInfo = getAwaySameReturn(playType, Constants.DEFAULT_ONE);
                return resultInfo;
            } else {
                resultInfo = getAwaySameReturn(playType, Constants.DEFAULT_TWO);
                return resultInfo;
            }
        } catch (Exception e) {
            resultInfo = ThreeWayConversionUtils.defaultAskReturn();
            logger.error("问路单、双数据返回转换异常:param:[{}]", e);
        }
        return resultInfo;
    }

    /**
     * A路数字一样返回
     *
     * @param expected
     * @return
     */
    public static ResultInfo<ThreeWayAskReturn> getAwaySameReturn(String expected, Integer type) {
        ResultInfo<ThreeWayAskReturn> resultInfo = ResultInfo.getInstance();
        ThreeWayAskReturn data = new ThreeWayAskReturn();
        List<String> strongWayList = new ArrayList<>();
        strongWayList.add(expected);
        data.setListNum(type);
        data.setList(strongWayList);
        resultInfo.setData(data);
        resultInfo.setInfo(StatusCode.OK.getValue());
        return resultInfo;
    }

    /**
     * 单、双返回结果
     *
     * @param sigleAndDoubleData
     * @return
     */
    public static ResultInfo<ThreeWaySigleAndDoubleReturn> organizationOddAndEvenData(ThreeWaySigleAndDoubleReturn sigleAndDoubleData) {
        ResultInfo<ThreeWaySigleAndDoubleReturn> resultInfo = ResultInfo.getInstance();
        try {
            resultInfo.setData(sigleAndDoubleData);
            resultInfo.setInfo(StatusCode.OK.getValue());
            return resultInfo;
        } catch (Exception e) {
            logger.error("组织返回单、双数据异常：", e);
            resultInfo = ThreeWayConversionUtils.defaultSigleAndDoubleReturn();
        }
        return resultInfo;
    }

    /**
     * 大、小返回结果
     *
     * @param bigAndSmallData
     * @return
     */
    public static ResultInfo<ThreeWayBigAndSmallReturn> organizationSmallAndDoubleData(ThreeWayBigAndSmallReturn bigAndSmallData) {
        ResultInfo<ThreeWayBigAndSmallReturn> resultInfo = ResultInfo.getInstance();
        try {
            resultInfo.setData(bigAndSmallData);
            resultInfo.setInfo(StatusCode.OK.getValue());
            return resultInfo;
        } catch (Exception e) {
            logger.error("组织返回大、小数据异常：", e);
            resultInfo = ThreeWayConversionUtils.defaultBigAndSmallReturn();
        }
        return resultInfo;
    }

    /**
     * 问路返回结果
     *
     * @param askData
     * @return
     */
    public static ResultInfo<ThreeWayAskReturn> organizationAskWayData(ThreeWayAskReturn askData) {
        ResultInfo<ThreeWayAskReturn> resultInfo = ResultInfo.getInstance();
        try {
            resultInfo.setData(askData);
            resultInfo.setInfo(StatusCode.OK.getValue());
            return resultInfo;
        } catch (Exception e) {
            logger.error("组织返回问路数据异常：", e);
            resultInfo = ThreeWayConversionUtils.defaultAskReturn();
        }
        return resultInfo;
    }

}
