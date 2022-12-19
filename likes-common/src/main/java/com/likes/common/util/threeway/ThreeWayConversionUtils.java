package com.likes.common.util.threeway;

import com.alibaba.fastjson.JSON;
import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.threeway.MainPlayTypeEnum;
import com.likes.common.enums.threeway.ThreeWayTypeNumEnum;
import com.likes.common.model.common.RequestInfo;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.result.*;
import com.likes.common.mybatis.entity.*;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ThreeWayConversionUtils
 * @Description: 下三路接口数据转换
 * @author: HANS
 * @date: 2019年6月11日 下午4:11:13
 */
public class ThreeWayConversionUtils {
    private static final Logger logger = LoggerFactory.getLogger(ThreeWayConversionUtils.class);

    /**
     * 大路单、双接口返回数据转换
     *
     * @param mainWaysList
     * @return
     */
    public static ResultInfo<ThreeWaySigleAndDoubleReturn> mainDsConversion(
            ResultInfo<ThreeWaySigleAndDoubleReturn> resultInfo, List<ThreeMainWayDs> mainWaysList) {
        List<String> mainList = new ArrayList<>();
        for (ThreeMainWayDs threeMainWay : mainWaysList) {
            Integer mainA = threeMainWay.getMina();
            Integer mainB = threeMainWay.getMinb();
            String heLocation = threeMainWay.getHeLocation();

            if (Constants.DEFAULT_INTEGER.equals(mainA) || Constants.DEFAULT_INTEGER.equals(mainB)) {
                continue;
            }
            // 单双转换
            String result = ThreeWayBackCalculationUtils.backCalculation(mainB, heLocation);

            if (StringUtils.isEmpty(result)) {
                continue;
            }
            mainList.add(result);
        }

        if (CollectionUtil.isEmpty(mainList)) {
            resultInfo = ThreeWayConversionUtils.defaultSigleAndDoubleReturn();
            return resultInfo;
        }
        // 组织返回数据
        Map<String, Integer> totalNumMap = totalOddAndEvenType(mainList);
        organizationOddAndEvenData(resultInfo, mainList, totalNumMap);
        return resultInfo;
    }

    /**
     * 大路大、小接口返回数据转换
     *
     * @param mainWaysList
     * @return
     */
    public static ResultInfo<ThreeWayBigAndSmallReturn> mainDxConversion(
            ResultInfo<ThreeWayBigAndSmallReturn> resultInfo, List<ThreeMainWayDx> mainWaysList,
            ThreeWayDTO threeWayDTO) {
        List<String> mainList = new ArrayList<>();
        // 参数类型
        Integer typeNumParam = threeWayDTO.getTypeNum();
        for (ThreeMainWayDx threeMainWayDx : mainWaysList) {
            Integer dxMainA = threeMainWayDx.getMina();
            Integer dxMainB = threeMainWayDx.getMinb();
            String heLocation = threeMainWayDx.getHeLocation();

            if (Constants.DEFAULT_INTEGER.equals(dxMainA) || Constants.DEFAULT_INTEGER.equals(dxMainB)) {
                continue;
            }
            String result = Constants.DEFAULT_NULL;

            // 家禽、野兽
            if (ThreeWayTypeNumEnum.SPECIAL_ANIMAL.getValue().equals(typeNumParam)) {
                result = ThreeWayBackCalculationUtils.backAnimal(dxMainB);
            } else if (ThreeWayTypeNumEnum.POSITIVE_DRAGON_TIGER.getValue().equals(typeNumParam)) {
                // 龙、虎
                result = ThreeWayBackCalculationUtils.backDragonAndtiger(dxMainB);
            } else {
                result = ThreeWayBackCalculationUtils.backBigSmallCalculation(dxMainB, heLocation);
            }

            if (StringUtils.isEmpty(result)) {
                continue;
            }
            mainList.add(result);
        }

        if (CollectionUtil.isEmpty(mainList)) {
            resultInfo = ThreeWayConversionUtils.defaultBigAndSmallReturn();
            return resultInfo;
        }
        // 返回数据组织
        Map<String, Integer> totalNumMap = totalSmallAndDoubleType(mainList, typeNumParam);
        organizationSmallAndDoubleData(resultInfo, mainList, totalNumMap);
        return resultInfo;
    }

    /**
     * 大眼仔单、双接口返回数据转换
     *
     * @param largeWaysList
     * @return
     */
    public static ResultInfo<ThreeWaySigleAndDoubleReturn> largeSeedDsConversion(
            ResultInfo<ThreeWaySigleAndDoubleReturn> resultInfo, List<ThreeLargeSeedDs> largeWaysList) {
        List<String> largeList = new ArrayList<>();
        for (ThreeLargeSeedDs threeLargeSeed : largeWaysList) {
            Integer largeD = threeLargeSeed.getLarged();
            Integer largeE = threeLargeSeed.getLargee();

            if (Constants.DEFAULT_INTEGER.equals(largeD) || Constants.DEFAULT_INTEGER.equals(largeE)) {
                continue;
            }
            // 数字换成单、双显示
            String largeResult = ThreeWayBackCalculationUtils.backCalculation(largeE, Constants.DEFAULT_NULL);

            if (StringUtils.isEmpty(largeResult)) {
                continue;
            }
            largeList.add(largeResult);
        }

        if (CollectionUtil.isEmpty(largeList)) {
            resultInfo = ThreeWayConversionUtils.defaultSigleAndDoubleReturn();
            return resultInfo;
        }
        // 组织数据返回
        Map<String, Integer> totalNumMap = totalOddAndEvenType(largeList);
        organizationOddAndEvenData(resultInfo, largeList, totalNumMap);
        return resultInfo;
    }

    /**
     * 大眼仔大、小接口返回数据转换
     *
     * @param largeWaysList
     * @return
     */
    public static ResultInfo<ThreeWayBigAndSmallReturn> largeSeedDxConversion(
            ResultInfo<ThreeWayBigAndSmallReturn> resultInfo, List<ThreeLargeSeedDx> largeWaysList,
            ThreeWayDTO threeWayDTO) {
        List<String> largeDxList = new ArrayList<>();
        Integer typeNumParam = threeWayDTO.getTypeNum();

        for (ThreeLargeSeedDx threeLargeSeedDx : largeWaysList) {
            Integer dxLargeD = threeLargeSeedDx.getLarged();
            Integer dxLargeE = threeLargeSeedDx.getLargee();

            if (Constants.DEFAULT_INTEGER.equals(dxLargeD) || Constants.DEFAULT_INTEGER.equals(dxLargeE)) {
                continue;
            }
            String largeResult = Constants.DEFAULT_NULL;

            if (ThreeWayTypeNumEnum.POSITIVE_DRAGON_TIGER.getValue().equals(typeNumParam)) {
                largeResult = ThreeWayBackCalculationUtils.backDragonAndtiger(dxLargeE);
            } else if (ThreeWayTypeNumEnum.SPECIAL_ANIMAL.getValue().equals(typeNumParam)) {
                // 家禽&野兽
                largeResult = ThreeWayBackCalculationUtils.backAnimal(dxLargeE);
            } else {
                largeResult = ThreeWayBackCalculationUtils.backBigSmallCalculation(dxLargeE, Constants.DEFAULT_NULL);
            }

            if (StringUtils.isEmpty(largeResult)) {
                continue;
            }
            largeDxList.add(largeResult);
        }

        if (CollectionUtil.isEmpty(largeDxList)) {
            resultInfo = ThreeWayConversionUtils.defaultBigAndSmallReturn();
            return resultInfo;
        }
        // 返回
        Map<String, Integer> totalNumMap = totalSmallAndDoubleType(largeDxList, typeNumParam);
        organizationSmallAndDoubleData(resultInfo, largeDxList, totalNumMap);
        return resultInfo;
    }

    /**
     * 小路单、双接口返回数据转换
     *
     * @param smallList
     * @return
     */
    public static ResultInfo<ThreeWaySigleAndDoubleReturn> smallDsConversion(
            ResultInfo<ThreeWaySigleAndDoubleReturn> resultInfo, List<ThreeSmallWayDs> smallList) {
        List<String> smallWayList = new ArrayList<>();
        for (ThreeSmallWayDs threeSmallWay : smallList) {
            Integer smallH = threeSmallWay.getSmallh();
            Integer smallI = threeSmallWay.getSmalli();

            if (Constants.DEFAULT_INTEGER .equals(smallH) || Constants.DEFAULT_INTEGER.equals(smallI)) {
                continue;
            }
            // 转换为单、双
            String result = ThreeWayBackCalculationUtils.backCalculation(smallI, Constants.DEFAULT_NULL);

            if (StringUtils.isEmpty(result)) {
                continue;
            }
            smallWayList.add(result);
        }

        if (CollectionUtil.isEmpty(smallWayList)) {
            resultInfo = ThreeWayConversionUtils.defaultSigleAndDoubleReturn();
            return resultInfo;
        }
        // 小路单、双返回数据
        Map<String, Integer> totalNumMap = totalOddAndEvenType(smallWayList);
        organizationOddAndEvenData(resultInfo, smallWayList, totalNumMap);
        return resultInfo;
    }

    /**
     * 小路大、小接口返回数据转换
     *
     * @param smallList
     * @return
     */
    public static ResultInfo<ThreeWayBigAndSmallReturn> smallDxConversion(
            ResultInfo<ThreeWayBigAndSmallReturn> resultInfo, List<ThreeSmallWayDx> smallList,
            ThreeWayDTO threeWayDTO) {
        List<String> smallDxWayList = new ArrayList<>();
        Integer typeNumParam = threeWayDTO.getTypeNum();
        for (ThreeSmallWayDx threeSmallWayDx : smallList) {
            Integer smallhDx = threeSmallWayDx.getSmallh();
            Integer smalliDx = threeSmallWayDx.getSmalli();

            if (Constants.DEFAULT_INTEGER.equals(smallhDx) || Constants.DEFAULT_INTEGER.equals(smalliDx)) {
                continue;
            }
            String result = Constants.DEFAULT_NULL;

            if (ThreeWayTypeNumEnum.POSITIVE_DRAGON_TIGER.getValue().equals(typeNumParam)) {
                result = ThreeWayBackCalculationUtils.backDragonAndtiger(smalliDx);
            } else if (ThreeWayTypeNumEnum.SPECIAL_ANIMAL.getValue().equals(typeNumParam)) {
                result = ThreeWayBackCalculationUtils.backAnimal(smalliDx);
            } else {
                result = ThreeWayBackCalculationUtils.backBigSmallCalculation(smalliDx, Constants.DEFAULT_NULL);
            }

            if (StringUtils.isEmpty(result)) {
                continue;
            }
            smallDxWayList.add(result);
        }

        if (CollectionUtil.isEmpty(smallDxWayList)) {
            resultInfo = ThreeWayConversionUtils.defaultBigAndSmallReturn();
            return resultInfo;
        }
        // 转换数据
        Map<String, Integer> totalNumMap = totalSmallAndDoubleType(smallDxWayList, typeNumParam);
        organizationSmallAndDoubleData(resultInfo, smallDxWayList, totalNumMap);
        return resultInfo;
    }

    /**
     * 曱甴路、小强路单、双接口返回数据转换
     *
     * @param strongList
     * @return
     */
    public static ResultInfo<ThreeWaySigleAndDoubleReturn> strongDsConversion(
            ResultInfo<ThreeWaySigleAndDoubleReturn> resultInfo, List<ThreeStrongWayDs> strongList) {
        List<String> strongWayList = new ArrayList<>();
        for (ThreeStrongWayDs threeBigWay : strongList) {
            Integer bigK = threeBigWay.getBigk();
            Integer bigL = threeBigWay.getBigl();

            if (Constants.DEFAULT_INTEGER .equals(bigK) || Constants.DEFAULT_INTEGER.equals(bigL)) {
                continue;
            }
            // 数字转换成单、双显示
            String result = ThreeWayBackCalculationUtils.backCalculation(bigL, Constants.DEFAULT_NULL);

            if (StringUtils.isEmpty(result)) {
                continue;
            }
            strongWayList.add(result);
        }
        if (CollectionUtil.isEmpty(strongWayList)) {
            resultInfo = ThreeWayConversionUtils.defaultSigleAndDoubleReturn();
            return resultInfo;
        }
        // 小强路数据转换
        Map<String, Integer> totalNumMap = totalOddAndEvenType(strongWayList);
        organizationOddAndEvenData(resultInfo, strongWayList, totalNumMap);
        return resultInfo;
    }

    /**
     * 曱甴路、小强路大、小接口返回数据转换
     *
     * @param strongList
     * @return
     */
    public static ResultInfo<ThreeWayBigAndSmallReturn> strongDxConversion(
            ResultInfo<ThreeWayBigAndSmallReturn> resultInfo, List<ThreeStrongWayDx> strongList,
            ThreeWayDTO threeWayDTO) {
        Integer typeNumParam = threeWayDTO.getTypeNum();
        List<String> strongWayList = new ArrayList<>();
        for (ThreeStrongWayDx threeStrongWayDx : strongList) {
            Integer strongDxk = threeStrongWayDx.getBigk();
            Integer strongDxl = threeStrongWayDx.getBigl();

            if (Constants.DEFAULT_INTEGER.equals(strongDxk) || Constants.DEFAULT_INTEGER.equals(strongDxl)) {
                continue;
            }
            String result = Constants.DEFAULT_NULL;

            if (ThreeWayTypeNumEnum.POSITIVE_DRAGON_TIGER.getValue().equals(typeNumParam)) {
                result = ThreeWayBackCalculationUtils.backDragonAndtiger(strongDxl);
            } else if (ThreeWayTypeNumEnum.SPECIAL_ANIMAL.getValue().equals(typeNumParam)) {
                result = ThreeWayBackCalculationUtils.backAnimal(strongDxl);
            } else {
                result = ThreeWayBackCalculationUtils.backBigSmallCalculation(strongDxl, Constants.DEFAULT_NULL);
            }

            if (StringUtils.isEmpty(result)) {
                continue;
            }
            strongWayList.add(result);
        }

        if (CollectionUtil.isEmpty(strongWayList)) {
            resultInfo = ThreeWayConversionUtils.defaultBigAndSmallReturn();
            return resultInfo;
        }
        // 转化数据
        Map<String, Integer> totalNumMap = totalSmallAndDoubleType(strongWayList, typeNumParam);
        organizationSmallAndDoubleData(resultInfo, strongWayList, totalNumMap);
        return resultInfo;
    }

    /**
     * 判断下三路咨询接口参数非空
     *
     * @param requestInfo
     * @return
     */
    public static boolean validateConversionParam(RequestInfo<ThreeWayDTO> requestInfo) {
        try {
            if (null == requestInfo) {
                return false;
            }
            ThreeWayDTO threeWayDTO = requestInfo.getData();

            if (StringUtils.isEmpty(threeWayDTO.getYear())) {
                logger.error("下三路咨询接口判断参数年份为空");
                return true;
            }
            if (null == threeWayDTO.getTypeNum() || Constants.DEFAULT_INTEGER.equals(threeWayDTO.getTypeNum())) {
                logger.error("下三路问路接口判断类型为空");
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("下三路咨询接口判断参数异常", e);
            return true;
        }
    }


    /**
     * 判断露珠图咨询接口参数非空
     *
     * @param requestInfo
     * @return
     */
    public static boolean validateByParam(RequestInfo<ThreeWayDTO> requestInfo) {
        try {
            if (null == requestInfo) {
                return false;
            }
            ThreeWayDTO threeWayDTO = requestInfo.getData();

            if (StringUtils.isEmpty(threeWayDTO.getYear())) {
                logger.error("露珠图咨询接口判断参数年份为空");
                return true;
            }
            if (null == threeWayDTO.getTypeNum()) {
                logger.error("露珠图接口判断类型为空");
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("露珠图咨询接口判断参数异常", e);
            return true;
        }
    }


    /**
     * 判断参数不为空
     *
     * @return
     */
    public static boolean validateParam(RequestInfo<ThreeWayAskDTO> requestInfo) {
        try {
            if (null == requestInfo) {
                return true;
            }
            ThreeWayAskDTO threeWayDTO = requestInfo.getData();

            if (null == threeWayDTO.getTypeNum() || Constants.DEFAULT_INTEGER.equals(threeWayDTO.getTypeNum())) {
                logger.error("下三路问路接口判断类型为空");
                return true;
            }
            if (StringUtils.isEmpty(threeWayDTO.getYear())) {
                logger.error("下三路问路接口判断参数年份为空");
                return true;
            }
            if (null == threeWayDTO.getPlayType() || Constants.DEFAULT_INTEGER.equals(threeWayDTO.getPlayType())) {
                logger.error("下三路问路接口判断参数名称为空");
                return true;
            }
            // 类型变更
            Integer playType = threeWayDTO.getPlayType();

            // 大、家禽、龙、
            if (MainPlayTypeEnum.BIG.getValue() == playType || MainPlayTypeEnum.POULTRY.getValue() == playType || MainPlayTypeEnum.DRAGON.getValue() == playType) {
                threeWayDTO.setPlayType(Constants.DEFAULT_ONE);
            }

            // 小、野兽、虎
            if (MainPlayTypeEnum.SMALL.getValue() == playType || MainPlayTypeEnum.BEAST.getValue() == playType || MainPlayTypeEnum.TIGER.getValue() == playType) {
                threeWayDTO.setPlayType(Constants.DEFAULT_TWO);
            }
            return false;
        } catch (Exception e) {
            logger.error("下三路问路接口判断参数异常", e);
            return true;
        }
    }

    /**
     * 默认返回下三路接口数据 2：大、小
     *
     * @return
     */
    public static ResultInfo<ThreeWayBigAndSmallReturn> defaultBigAndSmallReturn() {
        ResultInfo<ThreeWayBigAndSmallReturn> resultInfo = ResultInfo.getInstance();
        ThreeWayBigAndSmallReturn data = new ThreeWayBigAndSmallReturn();
        List<String> list = new ArrayList<>();
        data.setBigTotal(Constants.DEFAULT_INTEGER);
        data.setSmallTotal(Constants.DEFAULT_INTEGER);
        data.setHeTotal(Constants.DEFAULT_INTEGER);
        data.setList(list);
        resultInfo.setData(data);
        resultInfo.setInfo(StatusCode.OK.getValue());
        return resultInfo;
    }

    /**
     * 默认返回下三路接口数据 1: 单、双
     *
     * @return
     */
    public static ResultInfo<ThreeWaySigleAndDoubleReturn> defaultSigleAndDoubleReturn() {
        ResultInfo<ThreeWaySigleAndDoubleReturn> resultInfo = ResultInfo.getInstance();
        ThreeWaySigleAndDoubleReturn data = new ThreeWaySigleAndDoubleReturn();
        List<String> list = new ArrayList<>();
        data.setSigleTotal(Constants.DEFAULT_INTEGER);
        data.setDoubleTotal(Constants.DEFAULT_INTEGER);
        data.setHeTotal(Constants.DEFAULT_INTEGER);
        data.setList(list);
        resultInfo.setData(data);
        resultInfo.setInfo(StatusCode.OK.getValue());
        return resultInfo;
    }

    /**
     * 默认返回下三路问路接口数据
     *
     * @return
     */
    public static ResultInfo<ThreeWayAskReturn> defaultAskReturn() {
        ResultInfo<ThreeWayAskReturn> resultInfo = ResultInfo.getInstance();
        ThreeWayAskReturn data = new ThreeWayAskReturn();
        data.setList(new ArrayList<String>());
        data.setListNum(Constants.DEFAULT_INTEGER);
        resultInfo.setData(data);
        resultInfo.setInfo(StatusCode.OK.getValue());
        return resultInfo;
    }

    /**
     * 组织返回单、双数据
     *
     * @param resultInfo
     * @param totalNumMap
     */
    public static void organizationOddAndEvenData(ResultInfo<ThreeWaySigleAndDoubleReturn> resultInfo,
                                                  List<String> mainList, Map<String, Integer> totalNumMap) {
        ThreeWaySigleAndDoubleReturn data = new ThreeWaySigleAndDoubleReturn();
        data.setSigleTotal(totalNumMap.get(Constants.BIGORSMALL_ODD_NUMBER));
        data.setDoubleTotal(totalNumMap.get(Constants.BIGORSMALL_EVEN_NUMBER));
        data.setHeTotal(totalNumMap.get(Constants.BIGORSMALL_SAME));
        data.setList(mainList);
        resultInfo.setData(data);
        resultInfo.setInfo(StatusCode.OK.getValue());
    }

    /**
     * 统计单、双类型数量
     *
     * @param typeList
     * @return
     */
    public static Map<String, Integer> totalOddAndEvenType(List<String> typeList) {
        Map<String, Integer> totalNumMap = new HashMap<>();
        String sourceInfo = JSON.toJSONString(typeList);
        // 单
        Integer sigleTotal = StringUtils.countMatches(sourceInfo, Constants.BIGORSMALL_ODD_NUMBER);
        totalNumMap.put(Constants.BIGORSMALL_ODD_NUMBER, sigleTotal);
        // 双
        Integer doubleTotal = StringUtils.countMatches(sourceInfo, Constants.BIGORSMALL_EVEN_NUMBER);
        totalNumMap.put(Constants.BIGORSMALL_EVEN_NUMBER, doubleTotal);
        // 和
        Integer heTotal = StringUtils.countMatches(sourceInfo, Constants.BIGORSMALL_SAME);
        totalNumMap.put(Constants.BIGORSMALL_SAME, heTotal);
        return totalNumMap;
    }

    /**
     * 组织返回大、小数据
     *
     * @param resultInfo
     * @param mainList
     * @param totalNumMap
     */
    public static void organizationSmallAndDoubleData(ResultInfo<ThreeWayBigAndSmallReturn> resultInfo,
                                                      List<String> mainList, Map<String, Integer> totalNumMap) {
        ThreeWayBigAndSmallReturn data = new ThreeWayBigAndSmallReturn();
        data.setBigTotal(totalNumMap.get(Constants.BIGORSMALL_BIG));
        data.setSmallTotal(totalNumMap.get(Constants.BIGORSMALL_SMALL));
        data.setHeTotal(totalNumMap.get(Constants.BIGORSMALL_SAME));
        data.setList(mainList);
        resultInfo.setData(data);
        resultInfo.setInfo(StatusCode.OK.getValue());
    }

    /**
     * 统计大、小类型数量
     *
     * @param typeList
     * @return
     */
    public static Map<String, Integer> totalSmallAndDoubleType(List<String> typeList, Integer typeNumParam) {
        Map<String, Integer> totalNumMap = new HashMap<>();
        String sourceInfo = JSON.toJSONString(typeList);
        Integer bigTotal = Constants.DEFAULT_INTEGER;
        Integer smallTotal = Constants.DEFAULT_INTEGER;
        // 和
        Integer heTotal = StringUtils.countMatches(sourceInfo, Constants.BIGORSMALL_SAME);
        totalNumMap.put(Constants.BIGORSMALL_SAME, heTotal);

        if (ThreeWayTypeNumEnum.SPECIAL_ANIMAL.getValue().equals(typeNumParam)) {
            // 家禽
            bigTotal = StringUtils.countMatches(sourceInfo, Constants.BIGORSMALL_POULTRY_SHORT);
            // 野兽
            smallTotal = StringUtils.countMatches(sourceInfo, Constants.BIGORSMALL_BEAST_SHORT);
        } else if (ThreeWayTypeNumEnum.POSITIVE_DRAGON_TIGER.getValue().equals(typeNumParam)) {
            // 龙
            bigTotal = StringUtils.countMatches(sourceInfo, Constants.PLAYRESULT_DRAGON);
            // 虎
            smallTotal = StringUtils.countMatches(sourceInfo, Constants.PLAYRESULT_TIGER);
        } else {
            // 大
            bigTotal = StringUtils.countMatches(sourceInfo, Constants.BIGORSMALL_BIG);
            // 小
            smallTotal = StringUtils.countMatches(sourceInfo, Constants.BIGORSMALL_SMALL);
        }
        totalNumMap.put(Constants.BIGORSMALL_BIG, bigTotal);
        totalNumMap.put(Constants.BIGORSMALL_SMALL, smallTotal);
        return totalNumMap;
    }

    /**
     * 波色默认返回
     *
     * @return
     */
    public static ResultInfo<BeadWayColorReturn> defaultColorReturn() {
        ResultInfo<BeadWayColorReturn> resultInfo = ResultInfo.getInstance();
        BeadWayColorReturn data = new BeadWayColorReturn();
        List<String> list = new ArrayList<>();
        data.setBlueTotal(Constants.DEFAULT_INTEGER);
        data.setGreenTotal(Constants.DEFAULT_INTEGER);
        data.setRedTotal(Constants.DEFAULT_INTEGER);
        data.setList(list);
        resultInfo.setData(data);
        resultInfo.setInfo(StatusCode.OK.getValue());
        return resultInfo;
    }

    /**
     * 生肖默认返回
     *
     * @return
     */
    public static ResultInfo<BeadWayAttributeReturn> defaultAttributeReturn() {
        ResultInfo<BeadWayAttributeReturn> resultInfo = ResultInfo.getInstance();
        BeadWayAttributeReturn data = new BeadWayAttributeReturn();
        List<String> list = new ArrayList<>();
        data.setList(list);
        resultInfo.setData(data);
        resultInfo.setInfo(StatusCode.OK.getValue());
        return resultInfo;
    }

    public static ResultInfo<BeadWayFiveReturn> defaultWxReturn() {
        ResultInfo<BeadWayFiveReturn> resultInfo = ResultInfo.getInstance();
        BeadWayFiveReturn data = new BeadWayFiveReturn();
        List<String> list = new ArrayList<>();
        data.setWoodTotal(Constants.DEFAULT_INTEGER);
        data.setWaterTotal(Constants.DEFAULT_INTEGER);
        data.setFireTotal(Constants.DEFAULT_INTEGER);
        data.setGoldTotal(Constants.DEFAULT_INTEGER);
        data.setSoilTotal(Constants.DEFAULT_INTEGER);
        data.setList(list);
        resultInfo.setData(data);
        resultInfo.setInfo(StatusCode.OK.getValue());
        return resultInfo;
    }

}
