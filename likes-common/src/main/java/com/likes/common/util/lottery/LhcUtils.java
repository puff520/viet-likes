package com.likes.common.util.lottery;


import com.likes.common.model.vo.LhcWsdxVO;
import com.likes.common.constant.Constants;
import com.likes.common.model.dto.lottery.LotterySgModel;
import com.likes.common.model.vo.*;
import com.likes.common.model.vo.lottery.LhcCountVO;
import com.likes.common.model.vo.lottery.LhcLskjVO;
import com.likes.common.model.vo.lottery.LhcPhotoListVO;
import com.likes.common.model.vo.lottery.LhcPhotoVO;
import com.likes.common.mybatis.entity.*;
import com.likes.common.util.ArrayUtils;
import com.likes.common.util.CalendarUtil;
import com.likes.common.util.MathUtil;
import com.likes.common.util.StringUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 六合彩功能类
 *
 * @author lzy
 * @create 2018-07-19 15:25
 **/
public class LhcUtils {

    private static final Logger logger = LoggerFactory.getLogger(LhcUtils.class);
    /** 尾数 */
    public static final List<Integer> WEISHU = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    /** 红波 */
    public static final List<Integer> RED = Lists.newArrayList(1, 2, 7, 8, 12, 13, 18, 19, 23, 24, 29, 30, 34, 35, 40, 45, 46);
    /** 蓝波 */
    public static final List<Integer> BLUE = Lists.newArrayList(3, 4, 9, 10, 14, 15, 20, 25, 26, 31, 36, 37, 41, 42, 47, 48);
    /** 绿波 */
    public static final List<Integer> GREEN = Lists.newArrayList(5, 6, 11, 16, 17, 21, 22, 27, 28, 32, 33, 38, 39, 43, 44, 49);
    /** 所有球 */
    public static final List<Integer> BALLS = new ArrayList<>();
    /** 五行 */
    public static final List<String> WUXING = Lists.newArrayList("木", "水", "金", "火", "木", "土", "金", "火", "水", "土", "金", "木", "水", "土", "火", "木", "水", "金", "火", "木", "土", "金", "火", "水", "土", "金", "木", "水", "土", "火", "木");
    /** 生肖排序 */
    public static final ArrayList<String> SHENGXIAO = Lists.newArrayList("鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪");
    /** 家禽生肖 */
    public static final ArrayList<String> JIAQIN = Lists.newArrayList("牛", "马", "羊", "鸡", "狗", "猪");
    /** 野兽生肖 */
    public static final ArrayList<String> YESHOU = Lists.newArrayList("鼠", "虎", "兔", "龙", "蛇", "猴");
    /** 波色排序 */
    public static final String[] BOSE = new String[]{"红波", "蓝波", "绿波"};

    static {
        BALLS.addAll(RED);
        BALLS.addAll(BLUE);
        BALLS.addAll(GREEN);
        BALLS.sort(Integer::compareTo);
    }

    // 六合彩特码特码A玩法id
    private static final String PLAY_ID_TM_TMA = "01";
    // 六合彩特码特码A两面玩法id
    private static final String PLAY_ID_TM_TMA_LM = "02";
    // 六合彩正码正码A玩法id
    public final static String PLAY_ID_ZM_ZMA = "03";
    // 六合彩正码正码A两面玩法id
    //private String PLAY_ID_ZM_ZMA_LM = 244;
    // 六合彩正码正码1-6玩法id集合
    //private final List<Integer> PLAY_IDS_ZM_OTS = Lists.newArrayList(
    //	120405, 120406, 120407, 120408, 120409, 120410);
    // 六合彩正码正码1-6玩法id集合
    private static final String PLAY_IDS_ZM = "04";
    // 六合彩正码正码1玩法id
    //private String PLAY_ID_ZM_ONE = 120405;
    // 六合彩正码正码2玩法id
    //private String PLAY_ID_ZM_TWO = 120406;
    // 六合彩正码正码3玩法id
    //private String PLAY_ID_ZM_THREE = 120407;
    // 六合彩正码正码4玩法id
    //private String PLAY_ID_ZM_FOUR = 120408;
    // 六合彩正码正码5玩法id
    //private String PLAY_ID_ZM_FIVE = 120409;
    // 六合彩正码正码6玩法id
    //private String PLAY_ID_ZM_SIX = 120410;
    // 六合彩正特正(1-6)特玩法id集合
    private static final List<String> PLAY_IDS_ZT_OTS = Lists.newArrayList("05", "06", "07", "08", "09", "10");
    // 六合彩正特正一特玩法id
    private static final String PLAY_ID_ZT_ONE = "05";
    // 六合彩正特正一特玩法id
    private static final String PLAY_ID_ZT_TWO = "06";
    // 六合彩正特正一特玩法id
    private static final String PLAY_ID_ZT_THREE = "07";
    // 六合彩正特正一特玩法id
    private static final String PLAY_ID_ZT_FOUR = "08";
    // 六合彩正特正一特玩法id
    private static final String PLAY_ID_ZT_FIVE = "09";
    // 六合彩正特正一特玩法id
    private static final String PLAY_ID_ZT_SIX = "10";

    // 六合彩正特正(1-6)特两面玩法id集合
    private static final List<Integer> PLAY_IDS_ZT_OTS_LM = Lists.newArrayList(338, 339, 331, 335, 336, 337);
    // 六合彩正特正一特两面玩法id
    // private String PLAY_ID_ZT_ONE_LM = 338;
    // 六合彩正特正一特两面玩法id
    //private String PLAY_ID_ZT_TWO_LM = 339;
    // 六合彩正特正一特两面玩法id
    //private String PLAY_ID_ZT_THREE_LM = 331;
    // 六合彩正特正一特两面玩法id
    //private String PLAY_ID_ZT_FOUR_LM = 335;
    // 六合彩正特正一特两面玩法id
    //private String PLAY_ID_ZT_FIVE_LM = 336;
    // 六合彩正特正一特两面玩法id
    //private String PLAY_ID_ZT_SIX_LM = 337;

    // 六合彩连码三全中,二全中,特串玩法id集合
    private static final List<String> PLAY_IDS_LM_QZ = Lists.newArrayList("15", "14", "13");
    // 六合彩连码特串玩法id
    private static final String PLAY_ID_LM_TC = "13";
    // 六合彩连码二全中玩法id
    private static final String PLAY_ID_LM_EQZ = "14";
    // 六合彩连码三全中玩法id
    private static final String PLAY_ID_LM_SQZ = "15";

    // 六合彩连码三中二,二中特玩法id集合
    private static final List<String> PLAY_IDS_LM_EZ = Lists.newArrayList("11", "12");
    // 六合彩连码二中特玩法id
    private static final String PLAY_ID_LM_EZT = "12";
    // 六合彩连码三中二玩法id
    private static final String PLAY_ID_LM_SZE = "11";

    // 六合彩半波红波玩法id
    private static final String PLAY_ID_BB_RED = "16";
    // 六合彩半波蓝波玩法id
    private static final String PLAY_ID_BB_BLUE = "17";
    // 六合彩半波绿波玩法id
    private static final String PLAY_ID_BB_GREEN = "18";

    // 六合彩不中玩法id集合
    private static final List<String> PLAY_IDS_NO_OPEN = Lists.newArrayList("21", "22", "23", "24", "25", "26");
    // 六合彩五不中玩法id
    private static final String PLAY_ID_NO_OPEN_FIVE = "21";
    // 六合彩六不中玩法id
    private static final String PLAY_ID_NO_OPEN_SIX = "22";
    // 六合彩七不中玩法id
    private static final String PLAY_ID_NO_OPEN_SEVEN = "23";
    // 六合彩八不中玩法id
    private static final String PLAY_ID_NO_OPEN_EIGHT = "24";
    // 六合彩九不中玩法id
    private static final String PLAY_ID_NO_OPEN_NINE = "25";
    // 六合彩十不中玩法id
    private static final String PLAY_ID_NO_OPEN_TEN = "26";

    // 六合彩尾数全尾玩法id
    private static final String PLAY_ID_WS_QW = "19";
    // 六合彩尾数特尾玩法id
    private static final String PLAY_ID_WS_TW = "20";
    // 六合彩平特玩法id
    private static final String PLAY_ID_PT_PT = "27";
    // 六合彩特肖玩法id
    private static final String PLAY_ID_TX_TX = "28";
    // 六合彩六肖连中玩法id
    private static final String PLAY_ID_LX_LXLZ = "29";
    // 六合彩六肖连不中玩法id
    private static final String PLAY_ID_LX_LXLBZ = "30";

    // 六合彩连肖中玩法id集合
    private static final List<String> PLAY_IDS_LX_WIN = Lists.newArrayList("31", "33", "35");
    // 六合彩二连肖中玩法id
    private static final String PLAY_ID_LX_TWO_WIN = "31";
    // 六合彩三连肖中玩法id
    private static final String PLAY_ID_LX_THREE_WIN = "33";
    // 六合彩四连肖中玩法id
    private static final String PLAY_ID_LX_FOUR_WIN = "35";
    // 六合彩连肖不中玩法id集合
    private static final List<String> PLAY_IDS_LX_NO_WIN = Lists.newArrayList("32", "34", "36");
    // 六合彩二连肖不中玩法id
    private static final String PLAY_ID_LX_TWO_NO_WIN = "32";
    // 六合彩三连肖不中玩法id
    private static final String PLAY_ID_LX_THREE_NO_WIN = "34";
    // 六合彩四连肖不中玩法id
    private static final String PLAY_ID_LX_FOUR_NO_WIN = "36";

    // 六合彩连尾中玩法id集合
    private static final List<String> PLAY_IDS_LW_WIN = Lists.newArrayList("37", "39", "41");
    // 六合彩二连尾中玩法id
    private static final String PLAY_ID_LW_TWO_WIN = "37";
    // 六合彩三连尾中玩法id
    private static final String PLAY_ID_LW_THREE_WIN = "39";
    // 六合彩四连尾中玩法id
    private static final String PLAY_ID_LW_FOUR_WIN = "41";
    // 六合彩连尾不中玩法id集合
    private static final List<String> PLAY_IDS_LW_NO_WIN = Lists.newArrayList("38", "40", "42");
    // 六合彩二连尾不中玩法id
    private static final String PLAY_ID_LW_TWO_NO_WIN = "38";
    // 六合彩三连尾不中玩法id
    private static final String PLAY_ID_LW_THREE_NO_WIN = "40";
    // 六合彩四连尾不中玩法id
    private static final String PLAY_ID_LW_FOUR_NO_WIN = "42";

    // 六合彩1-6龙虎玩法id
    private static final String PLAY_ID_ONE_SIX_LH = "43";
    // 六合彩五行玩法id
    private static final String PLAY_ID_WUXING = "44";

    // 可能打和的投注信息
    private static final List<String> MAYBE_HE = Lists.newArrayList("大", "小", "单", "双", "合单", "合双", "尾大", "尾小", "家禽", "野兽");

    /**
     * 获取对应号码的五行
     *
     * @param num     号码
     * @param dateStr 日期 yyyy-MM-dd
     * @return
     */
    public static String getNumWuXing(int num, String dateStr) {
        //获取该日期的农历年份
        Date date = null;
        int year = 0;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            year = CalendarUtil.solarToLunar(dateStr.replace("-", ""));
//            LunarCalendar calendar = new LunarCalendar(date);
//            year = calendar.getYear();
        } catch (ParseException e) {
            logger.error("日期转换出错", e);
        } catch (Exception e) {
            logger.error("日历转换出错", e);
        }

        int index = (60 + ((year / 2) % 30) - (num / 2)) % 30;
        if (year % 2 == 1 && num % 2 == 0) {
            index += 1;
        }
        return WUXING.get(index);
    }

    public static String getNumBose(int number) {
        if (RED.contains(number)) {
            return "红波";
        } else if (BLUE.contains(number)) {
            return "蓝波";
        } else {
            return "绿波";
        }
    }

    public static List<String> getTotalLiangMian(String sgNumber) {
        String[] sgArr = sgNumber.split(",");
        List<String> result = new ArrayList<>();
        int total = 0;
        for (String num : sgArr) {
            total += Integer.valueOf(num);
        }
        if (total % 2 == 1) {
            result.add("总单");
        } else {
            result.add("总双");
        }
        if (total >= 175) {
            result.add("总大");
        } else {
            result.add("总小");
        }
        int wei = total % 10;
        if (wei >= 5) {
            result.add("总尾大");
        } else {
            result.add("总尾小");
        }
        if (Integer.valueOf(sgArr[0]) > Integer.valueOf(sgArr[6])) {
            result.add("龙");
        } else {
            result.add("虎");
        }
        return result;
    }

    /**
     * 获取特码的两面
     *
     * @param num
     * @param date
     * @return
     */
    public static List<String> getTemaLiangMian(int num, String date) {
        List<String> numLiangMianList = LhcPlayRule.getNumLiangMianList(num);
        if (num <= 10) {
            numLiangMianList.add("1-10");
        } else if (num > 10 && num <= 20) {
            numLiangMianList.add("11-20");
        } else if (num > 20 && num <= 30) {
            numLiangMianList.add("21-30");
        } else if (num > 30 && num <= 40) {
            numLiangMianList.add("31-40");
        } else if (num > 40 && num <= 49) {
            numLiangMianList.add("41-49");
        }
        String shengXiao = getShengXiao(num, date);
        if (JIAQIN.contains(shengXiao)) {
            numLiangMianList.add("家禽");
        } else {
            numLiangMianList.add("野兽");
        }
        return numLiangMianList;
    }


//    /**
//     * 历史开奖
//     * @param info 倒序的开奖结果和期数
//     * @param sort 排序
//     * @return
//     */
//    public static List<List<MapStringVO>> lishiKaiJiang(List<LotterySgModel> info, Integer sort) {
//        List<List<MapStringVO>> result = new ArrayList<>();
//        if (CollectionUtils.isEmpty(info)) {
//            return result;
//        }
//
//        ArrayList<MapStringVO> data;
//        for (LotterySgModel model : info) {
//            data = new ArrayList<>();
//            String date = model.getDate();
//            String issue = model.getIssue();
//            data.add(new MapStringVO(date, issue));
//
//            String sg = model.getCpkSg();
//            String[] balls = sg.split(",");
//
//            //当期的时间所属的生肖
//            String shengXiao = getShengXiao(date);
//            int index = SHENGXIAO.indexOf(shengXiao);
//            for (int j = 0; j < 7; j++) {
//                int postion = (49 - Integer.valueOf(balls[j]) + index) % 12;
//                data.add(new MapStringVO(balls[j],  SHENGXIAO.get(postion)));
//            }
//            result.add(data);
//        }
//        if (sort != null && sort == 1) {
//            Collections.reverse(result);
//        }
//        return result;
//    }

    /**
     * 历史开奖 new  createBy shaoming
     *
     * @param info 倒序的开奖结果和期数
     * @return
     */
    public static List<List<MapStringVO>> lishiKaiJiang(List<LhcLotterySg> info) {
        List<List<MapStringVO>> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(info)) {
            return result;
        }

        ArrayList<MapStringVO> data;
        for (LhcLotterySg model : info) {
            data = new ArrayList<>();
            String date = model.getTime();
            String issue = model.getIssue();
            data.add(new MapStringVO(date, issue));

            String sg = model.getNumber() == null ? Constants.DEFAULT_NULL : model.getNumber();
            String[] balls = sg.split(",");
            if (balls.length != 7) {
                continue;
            }
            //当期的时间所属的生肖
            String shengXiao = getShengXiao(date);
            int index = SHENGXIAO.indexOf(shengXiao);
            for (int j = 0; j < 7; j++) {
                if (StringUtils.isEmpty(balls[j])) {
                    continue;
                }
                int postion = (49 - Integer.valueOf(balls[j]) + index) % 12;
                data.add(new MapStringVO(balls[j], SHENGXIAO.get(postion)));
            }
            result.add(data);
        }
        return result;
    }


    /**
     * 开奖列表
     *
     * @param sgList
     * @return
     */
    public static List<Map<String, Object>> lishiSg(List<LhcLotterySg> sgList) {
        if (sgList == null || sgList.size() == 0) {
            return new ArrayList<>();
        }
        int totalIssue = sgList.size();
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < totalIssue; i++) {
            Map<String, Object> map = new HashMap<>();
            LhcLotterySg lhcLotterySg = sgList.get(i);
            String date = lhcLotterySg.getTime();
            String number = lhcLotterySg.getNumber() == null ? Constants.DEFAULT_NULL : lhcLotterySg.getNumber();
            String[] balls = number.split(",");
            if (balls.length != 7) {
                continue;
            }
            map.put("issue", lhcLotterySg.getIssue());
            map.put("time", date);
            map.put("number", balls);
            map.put("numberstr", number);
            //当期的时间所属的生肖
            String shengXiao = getShengXiao(date);
            int index = SHENGXIAO.indexOf(shengXiao);
            ArrayList<String> shengXiaoList = new ArrayList<>();
            for (int j = 0; j < 7; j++) {

                if (StringUtils.isEmpty(balls[j])) {
                    continue;
                }

                int postion = (49 - Integer.valueOf(balls[j]) + index) % 12;
                shengXiaoList.add(SHENGXIAO.get(postion));
            }
            map.put("shengxiao", shengXiaoList);
            map.put("shengxiao2", LhcUtils.getNumberZodiac(number, lhcLotterySg.getTime()));
            result.add(map);
        }

        return result;
    }

    public static List<Map<String, Object>> lishionelhcSg(List<OnelhcLotterySg> sgList) {
        if (sgList == null || sgList.size() == 0) {
            return new ArrayList<>();
        }
        int totalIssue = sgList.size();
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < totalIssue; i++) {
            Map<String, Object> map = new HashMap<>();
            OnelhcLotterySg lhcLotterySg = sgList.get(i);
            String date = lhcLotterySg.getTime();
            String number = lhcLotterySg.getNumber() == null ? Constants.DEFAULT_NULL : lhcLotterySg.getNumber();
            String[] balls = number.split(",");
//            if (balls.length != 7) {
//                continue;
//            }
            map.put("issue", lhcLotterySg.getIssue());
            map.put("time", lhcLotterySg.getTime());

//            if(StringUtils.isEmpty(lhcLotterySg.getTime())){
//                map.put("time", lhcLotterySg.getIdealTime());
//            }else{
//                map.put("time", lhcLotterySg.getTime());
//            }

//            if(StringUtils.isEmpty(number)){
//                map.put(Constants.SGSIGN, 0);
//            }else{
//                map.put(Constants.SGSIGN, 1);
//                map.put("number", balls);
//                map.put("numberstr", number);
//                //当期的时间所属的生肖
//                String shengXiao = getShengXiao(date);
//                int index = SHENGXIAO.indexOf(shengXiao);
//                ArrayList<String> shengXiaoList = new ArrayList<>();
//                for (int j = 0; j < 7; j++) {
//                    if (StringUtils.isEmpty(balls[j])) {
//                        continue;
//                    }
//
//                    int postion = (49 - Integer.valueOf(balls[j]) + index) % 12;
//                    shengXiaoList.add(SHENGXIAO.get(postion));
//                }
//                map.put("shengxiao", shengXiaoList);
//                map.put("shengxiao2", LhcUtils.getNumberZodiac(number, lhcLotterySg.getTime()));
//            }

            map.put("number", balls);
            map.put("numberstr", number);
            //当期的时间所属的生肖
            String shengXiao = getShengXiao(date);
            int index = SHENGXIAO.indexOf(shengXiao);
            ArrayList<String> shengXiaoList = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                if (StringUtils.isEmpty(balls[j])) {
                    continue;
                }

                int postion = (49 - Integer.valueOf(balls[j]) + index) % 12;
                shengXiaoList.add(SHENGXIAO.get(postion));
            }
            map.put("shengxiao", shengXiaoList);
            map.put("shengxiao2", LhcUtils.getNumberZodiac(number, lhcLotterySg.getTime()));

            result.add(map);
        }

        return result;
    }

    public static List<Map<String, Object>> lishifivelhcSg(List<FivelhcLotterySg> sgList) {

        if (CollectionUtils.isEmpty(sgList)) {
            return new ArrayList<>();
        }

        int totalIssue = sgList.size();
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < totalIssue; i++) {
            Map<String, Object> map = new HashMap<>();
            FivelhcLotterySg lhcLotterySg = sgList.get(i);
            String date = lhcLotterySg.getTime();
            String number = lhcLotterySg.getNumber() == null ? Constants.DEFAULT_NULL : lhcLotterySg.getNumber();
            String[] balls = number.split(",");
//            if (balls.length != 7) {
//                continue;
//            }
            map.put("issue", lhcLotterySg.getIssue());
            map.put("time", lhcLotterySg.getTime());

//            if(StringUtils.isEmpty(lhcLotterySg.getTime())){
//                map.put("time", lhcLotterySg.getIdealTime());
//            }else{
//                map.put("time", lhcLotterySg.getTime());
//            }

//            if(StringUtils.isEmpty(number)){
//                map.put(Constants.SGSIGN, 0);
//            }else{
//                map.put(Constants.SGSIGN, 1);
//                map.put("number", balls);
//                map.put("numberstr", number);
//                //当期的时间所属的生肖
//                String shengXiao = getShengXiao(date);
//                int index = SHENGXIAO.indexOf(shengXiao);
//                ArrayList<String> shengXiaoList = new ArrayList<>();
//                for (int j = 0; j < 7; j++) {
//
//                    if (StringUtils.isEmpty(balls[j])) {
//                        continue;
//                    }
//                    int postion = (49 - Integer.valueOf(balls[j]) + index) % 12;
//                    shengXiaoList.add(SHENGXIAO.get(postion));
//                }
//                map.put("shengxiao", shengXiaoList);
//                map.put("shengxiao2", LhcUtils.getNumberZodiac(number, lhcLotterySg.getTime()));
//            }

            map.put("number", balls);
            map.put("numberstr", number);
            //当期的时间所属的生肖
            String shengXiao = getShengXiao(date);
            int index = SHENGXIAO.indexOf(shengXiao);
            ArrayList<String> shengXiaoList = new ArrayList<>();
            for (int j = 0; j < 7; j++) {

                if (StringUtils.isEmpty(balls[j])) {
                    continue;
                }
                int postion = (49 - Integer.valueOf(balls[j]) + index) % 12;
                shengXiaoList.add(SHENGXIAO.get(postion));
            }
            map.put("shengxiao", shengXiaoList);
            map.put("shengxiao2", LhcUtils.getNumberZodiac(number, lhcLotterySg.getTime()));

            result.add(map);
        }

        return result;
    }

    public static List<Map<String, Object>> lishiamlhcSg(List<AmlhcLotterySg> sgList) {
        if (sgList == null || sgList.size() == 0) {
            return new ArrayList<>();
        }
        int totalIssue = sgList.size();
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < totalIssue; i++) {
            Map<String, Object> map = new HashMap<>();
            AmlhcLotterySg lhcLotterySg = sgList.get(i);
            String date = lhcLotterySg.getTime();
            String number = lhcLotterySg.getNumber() == null ? Constants.DEFAULT_NULL : lhcLotterySg.getNumber();
            String[] balls = number.split(",");
//            if (balls.length != 7) {
//                continue;
//            }
            map.put("issue", lhcLotterySg.getIssue());
            map.put("time", lhcLotterySg.getTime());

//            if(StringUtils.isEmpty(lhcLotterySg.getTime())){
//                map.put("time", lhcLotterySg.getIdealTime());
//            }else{
//                map.put("time", lhcLotterySg.getTime());
//            }

//            if(StringUtils.isEmpty(number)){
//                map.put(Constants.SGSIGN, 0);
//            }else{
//                map.put(Constants.SGSIGN, 1);
//                map.put("number", balls);
//                map.put("numberstr", number);
//                //当期的时间所属的生肖
//                String shengXiao = getShengXiao(date);
//                int index = SHENGXIAO.indexOf(shengXiao);
//                ArrayList<String> shengXiaoList = new ArrayList<>();
//                for (int j = 0; j < 7; j++) {
//                    if (StringUtils.isEmpty(balls[j])) {
//                        continue;
//                    }
//
//                    int postion = (49 - Integer.valueOf(balls[j]) + index) % 12;
//                    shengXiaoList.add(SHENGXIAO.get(postion));
//                }
//                map.put("shengxiao", shengXiaoList);
//                map.put("shengxiao2", LhcUtils.getNumberZodiac(number, lhcLotterySg.getTime()));
//            }

            map.put("number", balls);
            map.put("numberstr", number);
            //当期的时间所属的生肖
            String shengXiao = getShengXiao(date);
            int index = SHENGXIAO.indexOf(shengXiao);
            ArrayList<String> shengXiaoList = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                if (StringUtils.isEmpty(balls[j])) {
                    continue;
                }

                int postion = (49 - Integer.valueOf(balls[j]) + index) % 12;
                shengXiaoList.add(SHENGXIAO.get(postion));
            }
            map.put("shengxiao", shengXiaoList);
            map.put("shengxiao2", LhcUtils.getNumberZodiac(number, lhcLotterySg.getTime()));

            result.add(map);
        }

        return result;
    }

    /**
     * 期次详情
     *
     * @param sg
     * @return
     */
    public static Map<String, Object> sgDetails(LhcLotterySg sg) {
        Map<String, Object> map = new HashMap<>();
        map.put("issue", sg.getIssue());
        String number = sg.getNumber() == null ? Constants.DEFAULT_NULL : sg.getNumber();
        String[] balls = number.split(",");
        map.put("number", balls);
        //当期的时间所属的生肖
        String date = sg.getTime();
        map.put("time", date);
        String shengXiao = getShengXiao(date);
        int index = SHENGXIAO.indexOf(shengXiao);
        ArrayList<String> shengXiaoList = new ArrayList<>();
        for (int j = 0; j < 7; j++) {
            if (StringUtils.isEmpty(balls[j])) {
                continue;
            }
            int postion = (49 + index - Integer.valueOf(balls[j])) % 12;
            shengXiaoList.add(SHENGXIAO.get(postion));
        }
        map.put("shengxiao", shengXiaoList);
        map.put("tema", balls[6]);
        String zhengma = new StringBuffer()
                .append(balls[0]).append(",")
                .append(balls[1]).append(",")
                .append(balls[2]).append(",")
                .append(balls[3]).append(",")
                .append(balls[4]).append(",")
                .append(balls[5]).toString();
        map.put("zhengma", zhengma);
        map.put("zhengmaOne", numDetalis(balls[0]));
        map.put("zhengmaTwo", numDetalis(balls[1]));
        map.put("zhengmaThree", numDetalis(balls[2]));
        map.put("zhengmaFour", numDetalis(balls[3]));
        map.put("zhengmaFive", numDetalis(balls[4]));
        map.put("zhengmaSix", numDetalis(balls[5]));
        map.put("zhengOne", balls[0]);
        map.put("zhengTwo", balls[1]);
        map.put("zhengThree", balls[2]);
        map.put("zhengFour", balls[3]);
        map.put("zhengFive", balls[4]);
        map.put("zhengSix", balls[5]);
        map.put("banbo", LhcPlayRule.getNumBanbo(Integer.valueOf(balls[6]), "/"));
        StringBuffer quanwei = new StringBuffer();
        for (int j = 0; j < 6; j++) {
            if (StringUtils.isEmpty(balls[j])) {
                continue;
            }

            int ge = Integer.valueOf(balls[j]) % 10;
            quanwei.append(ge).append("/");
        }
        map.put("quanwei", quanwei.substring(0, quanwei.length() - 1));
        map.put("tewei", Integer.valueOf(balls[6]) % 10);

        StringBuffer pingte = new StringBuffer();
        for (int j = 0; j < 6; j++) {
            pingte.append(shengXiaoList.get(j)).append("/");
        }
        map.put("pingte", pingte.substring(0, pingte.length() - 1));
        map.put("texiao", shengXiaoList.get(6));
        map.put("wuxing", getNumWuXing(Integer.valueOf(balls[6]), date));
        return map;
    }


    public static String numDetalis(String num) {
        int number = Integer.valueOf(num);
        int shi = number / 10;
        int ge = number % 10;
        int he = shi + ge;
        String result;
        if (number == 49) {
            result = "和";
        } else if (number > 24) {
            result = "大";
        } else {
            result = "小";
        }
        if (number == 49) {
            result += ",和";
        } else if (number % 2 == 1) {
            result += "单";
        } else {
            result += "双";
        }
        if (he % 2 == 1) {
            result += ",合单";
        } else {
            result += ",合双";
        }
        if (ge > 4) {
            result += ",尾大";
        } else {
            result += ",尾小";
        }
        if (RED.contains(number)) {
            result += ",红波";
        } else if (BLUE.contains(number)) {
            result += ",蓝波";
        } else {
            result += ",绿波";
        }
        return result;
    }

    /**
     * 资讯统计
     *
     * @param info 倒序的开奖结果和期数
     * @return
     */
    public static List<MapListVO> ziXunTongJi(List<LotterySgModel> info) {
        if (info == null || info.size() == 0) {
            return new ArrayList<>();
        }

        //特码出现最多的号码
        int[] temaNumInArr = new int[49];
        //特码遗漏最多的号码
        Integer[] temaNumOutArr = new Integer[49];
        //正码出现最多的号码
        int[] zhengmaNumInArr = new int[49];
        //正码遗漏最多的号码
        Integer[] zhengmaNumOutArr = new Integer[49];
        //特码出现最多的生肖
        int[] temaSxInArr = new int[12];
        //特码遗漏最多的生肖
        Integer[] temaSxOutArr = new Integer[12];
        //正码出现最多的生肖
        int[] zhengmaSxInArr = new int[12];
        //正码遗漏最多的生肖
        Integer[] zhengmaSxOutArr = new Integer[12];
        //特码出现最多的波色,[红,蓝,绿]
        int[] temaBsInArr = new int[3];
        //特码遗漏最多的波色
        Integer[] temaBsOutArr = new Integer[3];
        //正码出现最多的波色
        int[] zhengmaBsInArr = new int[3];
        //正码遗漏最多的波色
        Integer[] zhengmaBsOutArr = new Integer[3];
        //特码出现最多的尾数
        int[] temaWsInArr = new int[10];
        //特码遗漏最多的尾数
        Integer[] temaWsOutArr = new Integer[10];

        int totalIssue = info.size();
        for (int i = 0; i < totalIssue; i++) {
            LotterySgModel lotterySgModel = info.get(i);
            String sg = lotterySgModel.getSg();
            String date = lotterySgModel.getDate();
            String[] balls = sg.split(",");
            //当期的时间所属的生肖
            String shengXiao = getShengXiao(date);
            int postion = SHENGXIAO.indexOf(shengXiao);
            //特码统计
            int tema = Integer.valueOf(balls[6]);
            //特码号码统计
            temaNumInArr[tema - 1] += 1;
            if (temaNumOutArr[tema - 1] == null) {
                temaNumOutArr[tema - 1] = i;
            }
            //特码生肖统计
            int index = (49 + postion - tema) % 12;
            temaSxInArr[index] += 1;
            if (temaSxOutArr[index] == null) {
                temaSxOutArr[index] = i;
            }
            //特码波色统计
            if (RED.contains(tema)) {
                temaBsInArr[0] += 1;
                if (temaBsOutArr[0] == null) {
                    temaBsOutArr[0] = i;
                }
            }
            if (BLUE.contains(tema)) {
                temaBsInArr[1] += 1;
                if (temaBsOutArr[1] == null) {
                    temaBsOutArr[1] = i;
                }
            }
            if (GREEN.contains(tema)) {
                temaBsInArr[2] += 1;
                if (temaBsOutArr[2] == null) {
                    temaBsOutArr[2] = i;
                }
            }

            //特码尾数统计
            int temaWs = tema % 10;
            temaWsInArr[temaWs] += 1;
            if (temaWsOutArr[temaWs] == null) {
                temaWsOutArr[temaWs] = i;
            }

            //正码统计
            for (int j = 0; j < 6; j++) {
                //正码号码统计
                int zhengma = Integer.valueOf(balls[j]);
                zhengmaNumInArr[zhengma - 1] += 1;
                if (zhengmaNumOutArr[zhengma - 1] == null) {
                    zhengmaNumOutArr[zhengma - 1] = i;
                }
                //正码生肖统计
                int index2 = (49 + postion - zhengma) % 12;
                zhengmaSxInArr[index2] += 1;
                if (zhengmaSxOutArr[index2] == null) {
                    zhengmaSxOutArr[index2] = i;
                }
                //正码波色统计
                if (RED.contains(zhengma)) {
                    zhengmaBsInArr[0] += 1;
                    if (zhengmaBsOutArr[0] == null) {
                        zhengmaBsOutArr[0] = i;
                    }
                }
                if (BLUE.contains(zhengma)) {
                    zhengmaBsInArr[1] += 1;
                    if (zhengmaBsOutArr[1] == null) {
                        zhengmaBsOutArr[1] = i;
                    }
                }
                if (GREEN.contains(zhengma)) {
                    zhengmaBsInArr[2] += 1;
                    if (zhengmaBsOutArr[2] == null) {
                        zhengmaBsOutArr[2] = i;
                    }
                }
            }
        }

        //数据封装
        //特码出现最多的号码
        ArrayList<String> temaNumIn = new ArrayList<>();
        int[] temaNumInIndex = ArrayUtils.getSortIndex(temaNumInArr);
        for (int i = 0; i < 10; i++) {
            temaNumIn.add(temaNumInIndex[i] + 1 + "");
        }
        //特码遗漏最多的号码
        ArrayList<String> temaNumOut = new ArrayList<>();
        int[] temaNumOutIndex = ArrayUtils.getSortIndex(temaNumOutArr, totalIssue);
        for (int i = 0; i < 10; i++) {
            temaNumOut.add(temaNumOutIndex[i] + 1 + "");
        }
        //正码出现最多的号码
        ArrayList<String> zhengmaNumIn = new ArrayList<>();
        int[] zhengmaNumInIndex = ArrayUtils.getSortIndex(zhengmaNumInArr);
        for (int i = 0; i < 10; i++) {
            zhengmaNumIn.add(zhengmaNumInIndex[i] + 1 + "");
        }
        //正码遗漏最多的号码
        ArrayList<String> zhengmaNumOut = new ArrayList<>();
        int[] zhengmaNumOutIndex = ArrayUtils.getSortIndex(zhengmaNumOutArr, totalIssue);
        for (int i = 0; i < 10; i++) {
            zhengmaNumOut.add(zhengmaNumOutIndex[i] + 1 + "");
        }
        //特码出现最多的生肖
        ArrayList<String> temaSxIn = new ArrayList<>();
        int[] temaSxInIndex = ArrayUtils.getSortIndex(temaSxInArr);
        for (int i = 0; i < 6; i++) {
            temaSxIn.add(SHENGXIAO.get(temaSxInIndex[i]));
        }
        //特码遗漏最多的生肖
        ArrayList<String> temaSxOut = new ArrayList<>();
        int[] temaSxOutIndex = ArrayUtils.getSortIndex(temaSxOutArr, totalIssue);
        for (int i = 0; i < 6; i++) {
            temaSxOut.add(SHENGXIAO.get(temaSxOutIndex[i]));
        }
        //正码出现最多的生肖
        ArrayList<String> zhengmaSxIn = new ArrayList<>();
        int[] zhengmaSxInIndex = ArrayUtils.getSortIndex(zhengmaSxInArr);
        for (int i = 0; i < 6; i++) {
            zhengmaSxIn.add(SHENGXIAO.get(zhengmaSxInIndex[i]));
        }
        //正码遗漏最多的生肖
        ArrayList<String> zhengmaSxOut = new ArrayList<>();
        int[] zhengmaSxOutIndex = ArrayUtils.getSortIndex(zhengmaSxOutArr, totalIssue);
        for (int i = 0; i < 6; i++) {
            zhengmaSxOut.add(SHENGXIAO.get(zhengmaSxOutIndex[i]));
        }
        //特码出现最多的波色,[红,蓝,绿]
        ArrayList<String> temaBsIn = new ArrayList<>();
        int[] temaBsInIndex = ArrayUtils.getSortIndex(temaBsInArr);
        for (int i = 0; i < 3; i++) {
            temaBsIn.add(BOSE[temaBsInIndex[i]]);
        }
        //特码遗漏最多的波色
        ArrayList<String> temaBsOut = new ArrayList<>();
        int[] temaBsOutIndex = ArrayUtils.getSortIndex(temaBsOutArr, totalIssue);
        for (int i = 0; i < 3; i++) {
            temaBsOut.add(BOSE[temaBsOutIndex[i]]);
        }
        //正码出现最多的波色
        ArrayList<String> zhengmaBsIn = new ArrayList<>();
        int[] zhengmaBsInIndex = ArrayUtils.getSortIndex(zhengmaBsInArr);
        for (int i = 0; i < 3; i++) {
            zhengmaBsIn.add(BOSE[zhengmaBsInIndex[i]]);
        }
        //正码遗漏最多的波色
        ArrayList<String> zhengmaBsOut = new ArrayList<>();
        int[] zhengmaBsOutIndex = ArrayUtils.getSortIndex(zhengmaBsOutArr, totalIssue);
        for (int i = 0; i < 3; i++) {
            zhengmaBsOut.add(BOSE[zhengmaBsOutIndex[i]]);
        }
        //特码出现最多的尾数
        ArrayList<String> temaWsIn = new ArrayList<>();
        int[] temaWsInIndex = ArrayUtils.getSortIndex(temaWsInArr);
        for (int i = 0; i < 5; i++) {
            temaWsIn.add(temaWsInIndex[i] + "尾");
        }
        //特码遗漏最多的尾数
        ArrayList<String> temaWsOut = new ArrayList<>();
        int[] temaWsOutIndex = ArrayUtils.getSortIndex(temaWsOutArr, totalIssue);
        for (int i = 0; i < 5; i++) {
            temaWsOut.add(temaWsOutIndex[i] + "尾");
        }

        ArrayList<MapListVO> result = new ArrayList<>();
        result.add(new MapListVO("temaNumIn", temaNumIn));
        result.add(new MapListVO("temaNumOut", temaNumOut));
        result.add(new MapListVO("zhengmaNumIn", zhengmaNumIn));
        result.add(new MapListVO("zhengmaNumOut", zhengmaNumOut));
        result.add(new MapListVO("temaSxIn", temaSxIn));
        result.add(new MapListVO("temaSxOut", temaSxOut));
        result.add(new MapListVO("zhengmaSxIn", zhengmaSxIn));
        result.add(new MapListVO("zhengmaSxOut", zhengmaSxOut));
        result.add(new MapListVO("temaBsIn", temaBsIn));
        result.add(new MapListVO("temaBsOut", temaBsOut));
        result.add(new MapListVO("zhengmaBsIn", zhengmaBsIn));
        result.add(new MapListVO("zhengmaBsOut", zhengmaBsOut));
        result.add(new MapListVO("temaWsIn", temaWsIn));
        result.add(new MapListVO("temaWsOut", temaWsOut));

        return result;
    }

    /**
     * 特码历史冷热图
     *
     * @param info 倒序的开奖结果
     * @return
     */
    public static List<List<MapVO>> temaLenReTu(List<String> info) {
        int[] re = new int[49];
        Integer[] len = new Integer[49];
        int totalIssue = info.size();
        for (int i = 0; i < totalIssue; i++) {
            String[] balls = info.get(i).split(",");
            Integer ballNum = Integer.valueOf(balls[6]);
            re[ballNum - 1] += 1;
            if (len[ballNum - 1] == null) {
                len[ballNum - 1] = i;
            }
        }
        for (int j = 0; j < 49; j++) {
            if (len[j] == null) {
                len[j] = totalIssue;
            }
        }
        List<List<MapVO>> lists = new ArrayList<>(2);
        lists.add(shuziListOneStart(re));
        lists.add(shuziListOneStart(len));
        return lists;
    }

    /**
     * 特码历史冷热图 web
     *
     * @param info 倒序的开奖结果
     * @return
     */
    public static Map<String, List<MapVO>> temaLenReTuWeb(List<String> info) {
        int[] re = new int[49];
        Integer[] len = new Integer[49];
        int totalIssue = info.size();
        for (int i = 0; i < totalIssue; i++) {
            String[] balls = info.get(i).split(",");
            Integer ballNum = Integer.valueOf(balls[6]);
            re[ballNum - 1] += 1;
            if (len[ballNum - 1] == null) {
                len[ballNum - 1] = i;
            }
        }
        for (int j = 0; j < 49; j++) {
            if (len[j] == null) {
                len[j] = totalIssue;
            }
        }
        Map<String, List<MapVO>> result = new HashMap<>();
        result.put("retu", shuziListOneStart(re));
        result.put("lentu", shuziListOneStart(len));
        return result;
    }

    /**
     * 正码历史冷热图
     *
     * @param info 倒序的开奖结果
     * @return
     */
    public static List<List<MapVO>> zhengmaLenReTu(List<String> info) {
        int[] re = new int[49];
        Integer[] len = new Integer[49];
        int totalIssue = info.size();
        for (int i = 0; i < totalIssue; i++) {
            String[] balls = info.get(i).split(",");
            for (int j = 0; j < 6; j++) {
                Integer ballNum = Integer.valueOf(balls[j]);
                re[ballNum - 1] += 1;
                if (len[ballNum - 1] == null) {
                    len[ballNum - 1] = i;
                }
            }

        }
        for (int j = 0; j < 49; j++) {
            if (len[j] == null) {
                len[j] = totalIssue;
            }
        }
        List<List<MapVO>> lists = new ArrayList<>(2);
        lists.add(shuziListOneStart(re));
        lists.add(shuziListOneStart(len));
        return lists;
    }

    /**
     * 正码历史冷热图 web
     *
     * @param info 倒序的开奖结果
     * @return
     */
    public static Map<String, List<MapVO>> zhengmaLenReTuWeb(List<String> info) {
        int[] re = new int[49];
        Integer[] len = new Integer[49];
        int totalIssue = info.size();
        for (int i = 0; i < totalIssue; i++) {
            String[] balls = info.get(i).split(",");
            for (int j = 0; j < 6; j++) {
                Integer ballNum = Integer.valueOf(balls[j]);
                re[ballNum - 1] += 1;
                if (len[ballNum - 1] == null) {
                    len[ballNum - 1] = i;
                }
            }

        }
        for (int j = 0; j < 49; j++) {
            if (len[j] == null) {
                len[j] = totalIssue;
            }
        }
        Map<String, List<MapVO>> result = new HashMap<>();
        result.put("retu", shuziListOneStart(re));
        result.put("lentu", shuziListOneStart(len));
        return result;
    }

    /**
     * 尾数大小
     *
     * @param info 正序的开奖结果和期数
     * @return
     */
    public static List<List<String>> weishuDaXiao(List<LotterySgModel> info) {
        int totalIssue = info.size();
        ArrayList<List<String>> sgs = new ArrayList<>(totalIssue);
        for (int i = 0; i < totalIssue; i++) {
            LotterySgModel lotterySgModel = info.get(i);
            ArrayList<String> sg = new ArrayList<>(8);
            sg.add(lotterySgModel.getIssue());
            String[] balls = lotterySgModel.getSg().split(",");
            for (int j = 0; j < 7; j++) {
                sg.add(getWsDaXiao(Integer.valueOf(balls[j])));
            }
            sgs.add(sg);
        }
        return sgs;
    }

    /**
     * 尾数大小
     *
     * @param info 正序的开奖结果和期数
     * @return
     */
    public static List<LhcWsdxVO> weishuDaXiaoWeb(List<LhcLotterySg> info) {
        int totalIssue = info.size();
        ArrayList<LhcWsdxVO> sgs = new ArrayList<>(totalIssue);
        for (int i = 0; i < totalIssue; i++) {

            LhcLotterySg lhcLotterySg = info.get(i);
            String[] balls = lhcLotterySg.getNumber().split(",");
            StringBuffer zhengma = new StringBuffer();
            int total = 0;
            for (int j = 0; j < 6; j++) {
                zhengma.append(getWsDaXiao(Integer.valueOf(balls[j]))).append(",");
                total += Integer.valueOf(balls[j]);
            }
            int tema = Integer.valueOf(balls[6]);
            LhcWsdxVO lhcWsdxVO = new LhcWsdxVO();
            lhcWsdxVO.setIssue(lhcLotterySg.getYear() + lhcLotterySg.getIssue());
            lhcWsdxVO.setDate(lhcLotterySg.getTime());
            lhcWsdxVO.setZhengMa(zhengma.substring(0, zhengma.length() - 1));
            lhcWsdxVO.setTeMa(getWsDaXiao(tema));
            lhcWsdxVO.setTeMaDx(getDaXiao(tema));
            lhcWsdxVO.setTeMaDs(getDanShuang(tema));
            lhcWsdxVO.setTotal(total + tema);
            sgs.add(lhcWsdxVO);
        }
        return sgs;
    }

    /**
     * 连码走势
     *
     * @param info 正序的开奖结果和期数
     * @return
     */
    public static List<List<String>> lianMaZouShi(List<LotterySgModel> info) {
        int totalIssue = info.size();
        ArrayList<List<String>> sgs = new ArrayList<>(totalIssue);
        for (int i = 0; i < totalIssue; i++) {
            LotterySgModel lotterySgModel = info.get(i);
            ArrayList<String> sg = new ArrayList<>(8);
            sg.add(lotterySgModel.getIssue());
            String[] balls = lotterySgModel.getSg().split(",");
            for (int j = 0; j < 7; j++) {
                sg.add(balls[j]);
            }
            sgs.add(sg);
        }
        return sgs;
    }

    /**
     * 连码走势
     *
     * @param info 正序的开奖结果和期数
     * @return
     */
    public static List<LhcWsdxVO> lianMaZouShiWeb(List<LhcLotterySg> info) {
        int totalIssue = info.size();
        ArrayList<LhcWsdxVO> sgs = new ArrayList<>(totalIssue);
        for (int i = 0; i < totalIssue; i++) {
            LhcLotterySg lhcLotterySg = info.get(i);
            String[] balls = lhcLotterySg.getNumber().split(",");
            StringBuffer zhengma = new StringBuffer();
            int total = 0;
            for (int j = 0; j < 6; j++) {
                zhengma.append(balls[j]).append(",");
                total += Integer.valueOf(balls[j]);
            }
            int tema = Integer.valueOf(balls[6]);
            LhcWsdxVO lhcWsdxVO = new LhcWsdxVO();
            lhcWsdxVO.setIssue(lhcLotterySg.getYear() + lhcLotterySg.getIssue());
            lhcWsdxVO.setDate(lhcLotterySg.getTime());
            lhcWsdxVO.setZhengMa(zhengma.substring(0, zhengma.length() - 1));
            lhcWsdxVO.setTeMa(balls[6]);
            lhcWsdxVO.setTeMaDx(getDaXiao(tema));
            lhcWsdxVO.setTeMaDs(getDanShuang(tema));
            lhcWsdxVO.setTotal(total + tema);
            sgs.add(lhcWsdxVO);
        }
        return sgs;
    }

    /**
     * 连肖走势
     *
     * @param info 正序的开奖结果和期数和开奖时间
     * @return
     */
    public static List<List<String>> lianXiaoZouShi(List<LotterySgModel> info) {
        int totalIssue = info.size();
        ArrayList<List<String>> sgs = new ArrayList<>(totalIssue);
        for (int i = 0; i < totalIssue; i++) {
            LotterySgModel lotterySgModel = info.get(i);
            String date = lotterySgModel.getDate();
            ArrayList<String> sg = new ArrayList<>(8);
            sg.add(lotterySgModel.getIssue());
            String[] balls = lotterySgModel.getSg().split(",");
            for (int j = 0; j < 7; j++) {
                sg.add(getShengXiao(Integer.valueOf(balls[j]), date));
            }
            sgs.add(sg);
        }
        return sgs;
    }

    /**
     * 连肖走势
     *
     * @param info 正序的开奖结果和期数和开奖时间
     * @return
     */
    public static List<LhcWsdxVO> lianXiaoZouShiWeb(List<LhcLotterySg> info) {
        int totalIssue = info.size();
        ArrayList<LhcWsdxVO> sgs = new ArrayList<>(totalIssue);
        for (int i = 0; i < totalIssue; i++) {
            LhcLotterySg lhcLotterySg = info.get(i);
            String date = lhcLotterySg.getTime();
            String[] balls = lhcLotterySg.getNumber().split(",");
            StringBuffer zhengma = new StringBuffer();
            int total = 0;
            for (int j = 0; j < 6; j++) {
                zhengma.append(getShengXiao(Integer.valueOf(balls[j]), date)).append(",");
                total += Integer.valueOf(balls[j]);
            }
            int tema = Integer.valueOf(balls[6]);
            LhcWsdxVO lhcWsdxVO = new LhcWsdxVO();
            lhcWsdxVO.setIssue(lhcLotterySg.getYear() + lhcLotterySg.getIssue());
            lhcWsdxVO.setDate(date);
            lhcWsdxVO.setZhengMa(zhengma.substring(0, zhengma.length() - 1));
            lhcWsdxVO.setTeMa(getShengXiao(Integer.valueOf(balls[6]), date));
            lhcWsdxVO.setTeMaDx(getDaXiao(tema));
            lhcWsdxVO.setTeMaDs(getDanShuang(tema));
            lhcWsdxVO.setTotal(total + tema);
            sgs.add(lhcWsdxVO);
        }
        return sgs;
    }

    /**
     * 生肖查询
     *
     * @param info 正序的开奖结果和期数和开奖时间
     * @return
     */
    public static List<List<String>> shengxiaoChaXun(List<LotterySgModel> info) {
        int totalIssue = info.size();
        ArrayList<List<String>> sgs = new ArrayList<>(totalIssue);
        for (int i = 0; i < totalIssue; i++) {
            LotterySgModel lotterySgModel = info.get(i);
            String date = lotterySgModel.getDate();
            ArrayList<String> sg = new ArrayList<>(8);
            sg.add(lotterySgModel.getIssue());
            String[] balls = lotterySgModel.getSg().split(",");
            for (int j = 0; j < 7; j++) {
                sg.add(getShengXiao(Integer.valueOf(balls[j]), date));
            }
            sgs.add(sg);
        }
        return sgs;
    }

    /**
     * 家禽野兽
     *
     * @param info 正序的开奖结果和期数和开奖时间
     * @return
     */
    public static List<List<String>> jiaQinYeShou(List<LotterySgModel> info) {
        int totalIssue = info.size();
        ArrayList<List<String>> sgs = new ArrayList<>(totalIssue);
        for (int i = 0; i < totalIssue; i++) {
            LotterySgModel lotterySgModel = info.get(i);
            String date = lotterySgModel.getDate();
            ArrayList<String> sg = new ArrayList<>(8);
            sg.add(lotterySgModel.getIssue());
            String[] balls = lotterySgModel.getSg().split(",");
            for (int j = 0; j < 7; j++) {
                sg.add(JIAQIN.contains(getShengXiao(Integer.valueOf(balls[j]), date)) ? "家" : "野");
            }
            sgs.add(sg);
        }
        return sgs;
    }

    /**
     * 家禽野兽
     *
     * @param info 正序的开奖结果和期数和开奖时间
     * @return
     */
    public static List<LhcWsdxVO> jiaQinYeShouWeb(List<LhcLotterySg> info) {
        int totalIssue = info.size();
        ArrayList<LhcWsdxVO> sgs = new ArrayList<>(totalIssue);
        for (int i = 0; i < totalIssue; i++) {
            LhcLotterySg lhcLotterySg = info.get(i);
            String date = lhcLotterySg.getTime();
            String[] balls = lhcLotterySg.getNumber().split(",");
            StringBuffer zhengma = new StringBuffer();
            int total = 0;
            for (int j = 0; j < 6; j++) {
                zhengma.append(JIAQIN.contains(getShengXiao(Integer.valueOf(balls[j]), date)) ? "家" : "野").append(",");
                total += Integer.valueOf(balls[j]);
            }
            int tema = Integer.valueOf(balls[6]);
            LhcWsdxVO lhcWsdxVO = new LhcWsdxVO();
            lhcWsdxVO.setIssue(lhcLotterySg.getYear() + lhcLotterySg.getIssue());
            lhcWsdxVO.setDate(date);
            lhcWsdxVO.setZhengMa(zhengma.substring(0, zhengma.length() - 1));
            lhcWsdxVO.setTeMa(JIAQIN.contains(getShengXiao(Integer.valueOf(balls[6]), date)) ? "家" : "野");
            lhcWsdxVO.setTeMaDx(getDaXiao(tema));
            lhcWsdxVO.setTeMaDs(getDanShuang(tema));
            lhcWsdxVO.setTotal(total + tema);
            sgs.add(lhcWsdxVO);
        }
        return sgs;
    }

    /**
     * 判断六合彩号码的大小
     *
     * @param num
     * @return
     */
    public static String getDaXiao(int num) {
        if (num <= 24) {
            return "小";
        } else {
            return "大";
        }
    }

    /**
     * 判断六合彩号码的单双
     *
     * @param num
     * @return
     */
    public static String getDanShuang(int num) {
        if (num % 2 == 1) {
            return "单";
        } else {
            return "双";
        }
    }

    /**
     * 判断六合彩号码尾数的大小
     *
     * @param num
     * @return
     */
    public static String getWsDaXiao(int num) {
        if (num % 10 < 5) {
            return "小";
        } else {
            return "大";
        }
    }

    /**
     * 生肖特码热图
     *
     * @param info 开奖结果
     * @return
     */
    public static List<MapVO> shengXiaoTemaRe(List<LotterySgModel> info) {
        int[] arr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (LotterySgModel sg : info) {
            String date = sg.getDate();
            String ball = sg.getSg();
            String[] balls = ball.split(",");
            Integer ballNum = Integer.valueOf(balls[6]);
            String shengXiao = getShengXiao(date);
            int postion = SHENGXIAO.indexOf(shengXiao);
            int index = (49 + postion - ballNum) % 12;
            arr[index] += 1;
        }
        return shengXiaoList(arr);
    }

    /**
     * 生肖特码冷图
     *
     * @param info 开奖结果
     * @return
     */
    public static List<MapVO> shengXiaoTemaLeng(List<LotterySgModel> info) {
        Integer[] arr = new Integer[12];
        int totalIssue = info.size();
        for (int i = 0; i < totalIssue; i++) {
            LotterySgModel sg = info.get(i);
            String date = sg.getDate();
            String ball = sg.getSg();
            String[] balls = ball.split(",");
            Integer ballNum = Integer.valueOf(balls[6]);
            String shengXiao = getShengXiao(date);
            int postion = SHENGXIAO.indexOf(shengXiao);
            int index = (49 + postion - ballNum) % 12;
            if (arr[index] == null) {
                arr[index] = i;
            }
            //如果统计大于80期，判断是否可以退出循环
            if (i > 80) {
                boolean tag = true;
                for (int k = 0; k < 12; k++) {
                    if (arr[k] == null) {
                        tag = false;
                    }
                }
                if (tag) {
                    break;
                }
            }
        }
        for (int j = 0; j < 12; j++) {
            if (arr[j] == null) {
                arr[j] = totalIssue;
            }
        }
        return shengXiaoList(arr);
    }

    /**
     * 生肖正码热图
     *
     * @param info 开奖结果
     * @return
     */
    public static List<MapVO> shengXiaoZhengmaRe(List<LotterySgModel> info) {
        int[] arr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (LotterySgModel sg : info) {
            String date = sg.getDate();
            String ball = sg.getSg();
            String shengXiao = getShengXiao(date);
            int postion = SHENGXIAO.indexOf(shengXiao);
            String[] balls = ball.split(",");
            for (int j = 0; j < 6; j++) {
                Integer ballNum = Integer.valueOf(balls[j]);
                int index = (49 + postion - ballNum) % 12;
                arr[index] += 1;
            }
        }
        return shengXiaoList(arr);
    }

    /**
     * 生肖特码冷图
     *
     * @param info 开奖结果
     * @return
     */
    public static List<MapVO> shengXiaoZhengmaLeng(List<LotterySgModel> info) {
        Integer[] arr = new Integer[12];
        int totalIssue = info.size();
        for (int i = 0; i < totalIssue; i++) {
            LotterySgModel sg = info.get(i);
            String date = sg.getDate();
            String ball = sg.getSg();
            String[] balls = ball.split(",");
            String shengXiao = getShengXiao(date);
            int postion = SHENGXIAO.indexOf(shengXiao);
            for (int j = 0; j < 6; j++) {
                Integer ballNum = Integer.valueOf(balls[j]);
                int index = (49 + postion - ballNum) % 12;
                if (arr[index] == null) {
                    arr[index] = i;
                }
            }

            //如果统计大于80期，判断是否可以退出循环
            if (i > 80) {
                boolean tag = true;
                for (int k = 0; k < 12; k++) {
                    if (arr[k] == null) {
                        tag = false;
                    }
                }
                if (tag) {
                    break;
                }
            }
        }
        for (int j = 0; j < 12; j++) {
            if (arr[j] == null) {
                arr[j] = totalIssue;
            }
        }
        return shengXiaoList(arr);
    }

    /**
     * 波色特码热图
     *
     * @param info 开奖结果
     * @return
     */
    public static List<MapVO> boseTemaRe(List<String> info) {
        int red = 0;
        int blue = 0;
        int green = 0;
        for (String ball : info) {
            String[] balls = ball.split(",");
            Integer ballNum = Integer.valueOf(balls[6]);
            if (RED.contains(ballNum)) {
                red++;
            }
            if (BLUE.contains(ballNum)) {
                blue++;
            }
            if (GREEN.contains(ballNum)) {
                green++;
            }
        }
        List<MapVO> list = new ArrayList<>(3);
        list.add(new MapVO("红波", red));
        list.add(new MapVO("蓝波", blue));
        list.add(new MapVO("绿波", green));
        return list;
    }

    /**
     * 波色特码冷图
     *
     * @param info 按时间倒序的开奖结果
     * @return
     */
    public static List<MapVO> boseTemaLen(List<String> info) {
        int red = 0;
        int blue = 0;
        int green = 0;
        boolean redFlag = false;
        boolean blueFlag = false;
        boolean greenFlag = false;
        for (String ball : info) {
            String[] balls = ball.split(",");
            Integer ballNum = Integer.valueOf(balls[6]);
            if (RED.contains(ballNum)) {
                redFlag = true;
                if (!blueFlag) {
                    blue++;
                }
                if (!greenFlag) {
                    green++;
                }
            }
            if (BLUE.contains(ballNum)) {
                blueFlag = true;
                if (!redFlag) {
                    red++;
                }
                if (!greenFlag) {
                    green++;
                }
            }
            if (GREEN.contains(ballNum)) {
                greenFlag = true;
                if (!redFlag) {
                    red++;
                }
                if (!blueFlag) {
                    blue++;
                }
            }

            if (redFlag && blueFlag && greenFlag) {
                break;
            }
        }
        List<MapVO> list = new ArrayList<>(3);
        list.add(new MapVO("红波", red));
        list.add(new MapVO("蓝波", blue));
        list.add(new MapVO("绿波", green));
        return list;
    }

    /**
     * 波色正码码热图
     *
     * @param info 开奖结果
     * @return
     */
    public static List<MapVO> boseZhengmaRe(List<String> info) {
        int red = 0;
        int blue = 0;
        int green = 0;
        for (String ball : info) {
            String[] balls = ball.split(",");
            for (int i = 0; i < 6; i++) {
                Integer ballNum = Integer.valueOf(balls[i]);
                if (RED.contains(ballNum)) {
                    red++;
                }
                if (BLUE.contains(ballNum)) {
                    blue++;
                }
                if (GREEN.contains(ballNum)) {
                    green++;
                }
            }
        }
        List<MapVO> list = new ArrayList<>(3);
        list.add(new MapVO("红波", red));
        list.add(new MapVO("蓝波", blue));
        list.add(new MapVO("绿波", green));
        return list;
    }

    private static String[] arrContrast(String[] arr1, String[] arr2) {
        List<String> list = new LinkedList<String>();
        for (String str : arr1) {                //处理第一个数组,list里面的值为1,2,3,4
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        for (String str : arr2) {      //如果第二个数组存在和第一个数组相同的值，就删除
            list.remove(str);
        }
        String[] result = {};   //创建空数组
        return list.toArray(result);    //List to Array
    }

    /**
     * 波色正码冷图
     *
     * @param info 按时间倒序的开奖结果
     * @return
     */
    public static List<MapVO> boseZhengmaLen(List<String> info) {
        int red = 0;
        int blue = 0;
        int green = 0;
        boolean redFlag = false;
        boolean blueFlag = false;
        boolean greenFlag = false;
        String[] all = new String[49];
        for (int i = 0; i < 49; i++) {
            all[i] = i + 1 + "";
        }
        for (String ball : info) {
            String[] balls = ball.split(",");
            String[] arrResult = arrContrast(all, balls);
            for (int i = 0; i < arrResult.length; i++) {
                Integer ballNum = Integer.valueOf(arrResult[i]);
                if (RED.contains(ballNum)) {
                    red++;
                }
                if (BLUE.contains(ballNum)) {
                    blue++;
                }
                if (GREEN.contains(ballNum)) {
                    green++;
                }
            }


            if (redFlag && blueFlag && greenFlag) {
                break;
            }
        }
        List<MapVO> list = new ArrayList<>(3);
        list.add(new MapVO("红波", red));
        list.add(new MapVO("蓝波", blue));
        list.add(new MapVO("绿波", green));
        return list;
    }

    /**
     * 特码两面分析图
     *
     * @param info 开奖结果
     * @return
     */
    public static ArrayList<MapVO> temaLiangMian(List<String> info) {
        ArrayList<MapVO> result = new ArrayList<>();
        int[] count = new int[8]; //大,小,单,双,合单,合双,尾大,尾小
        for (String ball : info) {
            String[] balls = ball.split(",");
            Integer ballNum = Integer.valueOf(balls[6]);
            if (LhcPlayRule.DA.contains(ballNum)) {
                count[0]++;
            }
            if (LhcPlayRule.XIAO.contains(ballNum)) {
                count[1]++;
            }
            if (LhcPlayRule.DAN.contains(ballNum)) {
                count[2]++;
            }
            if (LhcPlayRule.SHUANG.contains(ballNum)) {
                count[3]++;
            }
            if (LhcPlayRule.HESHUDAN.contains(ballNum)) {
                count[4]++;
            }
            if (LhcPlayRule.HESHUSHUANG.contains(ballNum)) {
                count[5]++;
            }
            if (LhcPlayRule.WEIDA.contains(ballNum)) {
                count[6]++;
            }
            if (LhcPlayRule.WEIXIAO.contains(ballNum)) {
                count[7]++;
            }

        }
        result.add(new MapVO("大", count[0]));
        result.add(new MapVO("小", count[1]));
        result.add(new MapVO("单", count[2]));
        result.add(new MapVO("双", count[3]));
        result.add(new MapVO("合单", count[4]));
        result.add(new MapVO("合双", count[5]));
        result.add(new MapVO("尾大", count[6]));
        result.add(new MapVO("尾小", count[7]));

        return result;
    }

    /**
     * 特码两面分析图
     *
     * @param info 开奖结果
     * @return
     */
    public static List<LhcCountVO> temaLiangMianWeb(List<String> info) {
        int[] count = new int[8]; //大,小,单,双,合单,合双,尾大,尾小
        int[] noOpenCount = new int[16]; // 遗漏统计
        for (String ball : info) {
            String[] balls = ball.split(",");
            Integer ballNum = Integer.valueOf(balls[6]);
            if (LhcPlayRule.DA.contains(ballNum)) {
                count[0]++;
                if (noOpenCount[0] > noOpenCount[1]) {
                    noOpenCount[1] = noOpenCount[0];
                }
                noOpenCount[0] = 0;
            } else {
                noOpenCount[0]++;
            }
            if (LhcPlayRule.XIAO.contains(ballNum)) {
                count[1]++;
                if (noOpenCount[2] > noOpenCount[3]) {
                    noOpenCount[3] = noOpenCount[2];
                }
                noOpenCount[2] = 0;
            } else {
                noOpenCount[2]++;
            }
            if (LhcPlayRule.DAN.contains(ballNum)) {
                count[2]++;
                if (noOpenCount[4] > noOpenCount[5]) {
                    noOpenCount[5] = noOpenCount[4];
                }
                noOpenCount[4] = 0;
            } else {
                noOpenCount[4]++;
            }
            if (LhcPlayRule.SHUANG.contains(ballNum)) {
                count[3]++;
                if (noOpenCount[6] > noOpenCount[7]) {
                    noOpenCount[7] = noOpenCount[6];
                }
                noOpenCount[6] = 0;
            } else {
                noOpenCount[6]++;
            }
            if (LhcPlayRule.HESHUDAN.contains(ballNum)) {
                count[4]++;
                if (noOpenCount[8] > noOpenCount[9]) {
                    noOpenCount[9] = noOpenCount[8];
                }
                noOpenCount[8] = 0;
            } else {
                noOpenCount[8]++;
            }
            if (LhcPlayRule.HESHUSHUANG.contains(ballNum)) {
                count[5]++;
                if (noOpenCount[10] > noOpenCount[11]) {
                    noOpenCount[11] = noOpenCount[10];
                }
                noOpenCount[10] = 0;
            } else {
                noOpenCount[10]++;
            }
            if (LhcPlayRule.WEIDA.contains(ballNum)) {
                count[6]++;
                if (noOpenCount[12] > noOpenCount[13]) {
                    noOpenCount[13] = noOpenCount[12];
                }
                noOpenCount[12] = 0;
            } else {
                noOpenCount[12]++;
            }
            if (LhcPlayRule.WEIXIAO.contains(ballNum)) {
                count[7]++;
                if (noOpenCount[14] > noOpenCount[15]) {
                    noOpenCount[15] = noOpenCount[14];
                }
                noOpenCount[14] = 0;
            } else {
                noOpenCount[14]++;
            }

        }

        List<LhcCountVO> result = new ArrayList<>();
        String[] types = {"大", "小", "单", "双", "合单", "合双", "尾大", "尾小"};
        for (int i = 0; i < 8; i++) {
            result.add(new LhcCountVO(count[i], noOpenCount[i * 2] > noOpenCount[i * 2 + 1] ? noOpenCount[i * 2] : noOpenCount[i * 2 + 1], types[i]));
        }

        return result;
    }

    /**
     * 正码总分
     *
     * @param sg
     * @return
     */
    public static List<LhcCountVO> zhengmaZongFenWeb(List<String> sg) {

        int[] count = new int[8]; //大,小,单,双,合单,合双,尾大,尾小
        int[] noOpenCount = new int[16]; // 遗漏统计
        for (String ball : sg) {
            String[] balls = ball.split(",");
            int num1 = Integer.valueOf(balls[0]);
            int num2 = Integer.valueOf(balls[1]);
            int num3 = Integer.valueOf(balls[2]);
            int num4 = Integer.valueOf(balls[3]);
            int num5 = Integer.valueOf(balls[4]);
            int num6 = Integer.valueOf(balls[5]);
            int num7 = Integer.valueOf(balls[6]);
            int he = num1 + num2 + num3 + num4 + num5 + num6 + num7;
            if (he >= 175) {
                count[0] += 1;
                if (noOpenCount[0] > noOpenCount[1]) {
                    noOpenCount[1] = noOpenCount[0];
                }
                noOpenCount[0] = 0;
                noOpenCount[2]++;
            } else {
                count[1] += 1;
                if (noOpenCount[2] > noOpenCount[3]) {
                    noOpenCount[3] = noOpenCount[2];
                }
                noOpenCount[2] = 0;
                noOpenCount[0]++;
            }
            if (he % 2 == 1) {
                count[2] += 1;
                if (noOpenCount[4] > noOpenCount[5]) {
                    noOpenCount[5] = noOpenCount[4];
                }
                noOpenCount[4] = 0;
                noOpenCount[6]++;
            } else {
                count[3] += 1;
                if (noOpenCount[6] > noOpenCount[7]) {
                    noOpenCount[7] = noOpenCount[6];
                }
                noOpenCount[6] = 0;
                noOpenCount[4]++;
            }
            int wei = he % 10;
            if (wei % 2 == 1) {
                count[4] += 1;
                if (noOpenCount[8] > noOpenCount[9]) {
                    noOpenCount[9] = noOpenCount[8];
                }
                noOpenCount[8] = 0;
                noOpenCount[10]++;
            } else {
                count[5] += 1;
                if (noOpenCount[10] > noOpenCount[11]) {
                    noOpenCount[11] = noOpenCount[10];
                }
                noOpenCount[10] = 0;
                noOpenCount[8]++;
            }
            if (wei >= 5) {
                count[6] += 1;
                if (noOpenCount[12] > noOpenCount[13]) {
                    noOpenCount[13] = noOpenCount[12];
                }
                noOpenCount[12] = 0;
                noOpenCount[14]++;
            } else {
                count[7] += 1;
                if (noOpenCount[14] > noOpenCount[15]) {
                    noOpenCount[15] = noOpenCount[14];
                }
                noOpenCount[14] = 0;
                noOpenCount[12]++;
            }
        }

        List<LhcCountVO> result = new ArrayList<>();
        String[] types = {"大", "小", "单", "双", "合单", "合双", "尾大", "尾小"};
        for (int i = 0; i < 8; i++) {
            result.add(new LhcCountVO(count[i], noOpenCount[i * 2] > noOpenCount[i * 2 + 1] ? noOpenCount[i * 2] : noOpenCount[i * 2 + 1], types[i]));
        }

        return result;
    }

    /**
     * 正码总分
     *
     * @param sg
     * @return
     */
    public static List<MapVO> zhengmaZongFen(List<String> sg) {
        ArrayList<MapVO> result = new ArrayList<>();
        int[] count = new int[8]; //大,小,单,双,尾单,尾双,尾大,尾小
        for (String ball : sg) {
            String[] balls = ball.split(",");
            int num1 = Integer.valueOf(balls[0]);
            int num2 = Integer.valueOf(balls[1]);
            int num3 = Integer.valueOf(balls[2]);
            int num4 = Integer.valueOf(balls[3]);
            int num5 = Integer.valueOf(balls[4]);
            int num6 = Integer.valueOf(balls[5]);
            int num7 = Integer.valueOf(balls[6]);
            int he = num1 + num2 + num3 + num4 + num5 + num6 + num7;
            if (he >= 175) {
                count[0] += 1;
            } else {
                count[1] += 1;
            }
            if (he % 2 == 1) {
                count[2] += 1;
            } else {
                count[3] += 1;
            }
            int wei = he % 10;
            if (wei % 2 == 1) {
                count[4] += 1;
            } else {
                count[5] += 1;
            }
            if (wei >= 5) {
                count[6] += 1;
            } else {
                count[7] += 1;
            }

        }
        result.add(new MapVO("大", count[0]));
        result.add(new MapVO("小", count[1]));
        result.add(new MapVO("单", count[2]));
        result.add(new MapVO("双", count[3]));
        result.add(new MapVO("尾单", count[4]));
        result.add(new MapVO("尾双", count[5]));
        result.add(new MapVO("尾大", count[6]));
        result.add(new MapVO("尾小", count[7]));

        return result;
    }

    /**
     * 六合彩号码波段正码
     *
     * @param sg
     * @return
     */
    public static List<MapVO> zhengmaBoDuan(List<String> sg) {

        int[] count = new int[5];
        for (String ball : sg) {
            String[] balls = ball.split(",");
            for (int i = 0; i < 6; i++) {
                Integer ballNum = Integer.valueOf(balls[i]);
                if (ballNum <= 10) {
                    count[0]++;
                } else if (ballNum <= 20) {
                    count[1]++;
                } else if (ballNum <= 30) {
                    count[2]++;
                } else if (ballNum <= 40) {
                    count[3]++;
                } else {
                    count[4]++;
                }
            }

        }
        return boduanList(count);
    }

    /**
     * 六合彩号码波段特码
     *
     * @param sg
     * @return
     */
    public static List<MapVO> temaBoDuan(List<String> sg) {
        int[] count = new int[5];
        for (String ball : sg) {
            String[] balls = ball.split(",");
            Integer ballNum = Integer.valueOf(balls[6]);
            if (ballNum <= 10) {
                count[0]++;
            } else if (ballNum <= 20) {
                count[1]++;
            } else if (ballNum <= 30) {
                count[2]++;
            } else if (ballNum <= 40) {
                count[3]++;
            } else {
                count[4]++;
            }
        }
        return boduanList(count);
    }

    /**
     * 特码尾数热图
     *
     * @param info 开奖结果
     * @return
     */
    public static ArrayList<MapVO> temaWeiRe(List<String> info) {
        int[] arr = new int[10];
        for (String ball : info) {
            String[] balls = ball.split(",");
            Integer ballNum = Integer.valueOf(balls[6]) % 10;
            arr[ballNum] += 1;
        }
        return weishuList(arr);
    }

    /**
     * 特码尾数冷图
     *
     * @param info 按时间倒序的开奖结果
     * @return
     */
    public static ArrayList<MapVO> temaWeiLen(List<String> info) {
        Integer[] arr = new Integer[10];
        int totalIssue = info.size();
        for (int i = 0; i < totalIssue; i++) {
            String[] balls = info.get(i).split(",");
            Integer ballNum = Integer.valueOf(balls[6]) % 10;
            if (arr[ballNum] == null) {
                arr[ballNum] = i;
            }
            //判断是否可以退出循环，30只是预测所有尾号都开过的期数
            if (i > 30) {
                boolean tag = true;
                for (int j = 0; j < 10; j++) {
                    if (arr[j] == null) {
                        tag = false;
                    }
                }
                if (tag) {
                    break;
                }
            }
        }

        for (int j = 0; j < 10; j++) {
            if (arr[j] == null) {
                arr[j] = totalIssue;
            }
        }
        return weishuList(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9]);
    }

    /**
     * 正码尾数热图
     *
     * @param info 开奖结果
     * @return
     */
    public static ArrayList<MapVO> zhengmaWeiRe(List<String> info) {
        int[] count = new int[10];
        for (String ball : info) {
            String[] balls = ball.split(",");
            for (int i = 0; i < 6; i++) {
                Integer ballNum = Integer.valueOf(balls[i]) % 10;
                count[ballNum]++;
            }
        }
        return weishuList(count);
    }

    /**
     * 正码尾数冷图
     *
     * @param info 开奖结果
     * @return
     */
    public static ArrayList<MapVO> zhengmaWeiLen(List<String> info) {

        int a0 = 0;
        int a1 = 0;
        int a2 = 0;
        int a3 = 0;
        int a4 = 0;
        int a5 = 0;
        int a6 = 0;
        int a7 = 0;
        int a8 = 0;
        int a9 = 0;
        boolean tag0 = false;
        boolean tag1 = false;
        boolean tag2 = false;
        boolean tag3 = false;
        boolean tag4 = false;
        boolean tag5 = false;
        boolean tag6 = false;
        boolean tag7 = false;
        boolean tag8 = false;
        boolean tag9 = false;
        for (String ball : info) {
            String[] balls = ball.split(",");
            for (int i = 0; i < 6; i++) {
                Integer ballNum = Integer.valueOf(balls[i]) % 10;
                switch (ballNum) {
                    case 0:
                        tag0 = true;
                        break;
                    case 1:
                        tag1 = true;
                        break;
                    case 2:
                        tag2 = true;
                        break;
                    case 3:
                        tag3 = true;
                        break;
                    case 4:
                        tag4 = true;
                        break;
                    case 5:
                        tag5 = true;
                        break;
                    case 6:
                        tag6 = true;
                        break;
                    case 7:
                        tag7 = true;
                        break;
                    case 8:
                        tag8 = true;
                        break;
                    case 9:
                        tag9 = true;
                        break;
                    default:
                        break;
                }
            }

            if (!tag0) {
                a0++;
            }
            if (!tag1) {
                a1++;
            }
            if (!tag2) {
                a2++;
            }
            if (!tag3) {
                a3++;
            }
            if (!tag4) {
                a4++;
            }
            if (!tag5) {
                a5++;
            }
            if (!tag6) {
                a6++;
            }
            if (!tag7) {
                a7++;
            }
            if (!tag8) {
                a8++;
            }
            if (!tag9) {
                a9++;
            }

            if (tag0 && tag1 && tag2 && tag3 && tag4 && tag5 && tag6 && tag7 && tag8 && tag9) {
                break;
            }

        }
        return weishuList(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9);
    }


    public static ArrayList<MapVO> weishuList(int a0, int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9) {
        ArrayList<MapVO> result = new ArrayList<>(10);
        result.add(new MapVO("0", a0));
        result.add(new MapVO("1", a1));
        result.add(new MapVO("2", a2));
        result.add(new MapVO("3", a3));
        result.add(new MapVO("4", a4));
        result.add(new MapVO("5", a5));
        result.add(new MapVO("6", a6));
        result.add(new MapVO("7", a7));
        result.add(new MapVO("8", a8));
        result.add(new MapVO("9", a9));
        return result;
    }

    public static ArrayList<MapVO> weishuList(int[] arr) {
        ArrayList<MapVO> result = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            result.add(new MapVO(i + "", arr[i]));
        }
        return result;
    }

    /**
     * 生成MapVO的type是号码波段的List
     *
     * @param arr 长度为5的数组
     * @return
     */
    public static ArrayList<MapVO> boduanList(int[] arr) {
        ArrayList<MapVO> result = new ArrayList<>();

        result.add(new MapVO("1-10段", arr[0]));
        result.add(new MapVO("11-20段", arr[1]));
        result.add(new MapVO("21-30段", arr[2]));
        result.add(new MapVO("31-40段", arr[3]));
        result.add(new MapVO("41-49段", arr[4]));

        return result;
    }

    /**
     * 生成MapVO的type从生肖鼠开始的List
     *
     * @param arr
     * @return
     */
    public static ArrayList<MapVO> shengXiaoList(int[] arr) {
        ArrayList<MapVO> result = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            result.add(new MapVO(SHENGXIAO.get(i), arr[i]));
        }
        return result;
    }

    /**
     * 生成MapVO的type从生肖鼠开始的List
     *
     * @param arr
     * @return
     */
    public static ArrayList<MapVO> shengXiaoList(Integer[] arr) {
        ArrayList<MapVO> result = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            result.add(new MapVO(SHENGXIAO.get(i), arr[i]));
        }
        return result;
    }

    /**
     * 生成MapVO的type从数字o开始的List
     *
     * @param arr
     * @return
     */
    public static ArrayList<MapVO> shuziList(int[] arr) {
        int len = arr.length;
        ArrayList<MapVO> result = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            result.add(new MapVO(i + "", arr[i]));
        }
        return result;
    }

    /**
     * 生成MapVO的type从数字o开始的List
     *
     * @param arr
     * @return
     */
    public static ArrayList<MapVO> shuziList(Integer[] arr) {
        int len = arr.length;
        ArrayList<MapVO> result = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            result.add(new MapVO(i + "", arr[i]));
        }
        return result;
    }

    /**
     * 生成MapVO的type从数字1开始的List
     *
     * @param arr
     * @return
     */
    public static ArrayList<MapVO> shuziListOneStart(int[] arr) {
        int len = arr.length;
        ArrayList<MapVO> result = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            result.add(new MapVO(i + 1 + "", arr[i]));
        }
        return result;
    }

    /**
     * 生成MapVO的type从数字1开始的List
     *
     * @param arr
     * @return
     */
    public static ArrayList<MapVO> shuziListOneStart(Integer[] arr) {
        int len = arr.length;
        ArrayList<MapVO> result = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            result.add(new MapVO(i + 1 + "", arr[i]));
        }
        return result;
    }

    /**
     * 根据公历日期获取号码对应的生肖
     *
     * @param number  号码
     * @param dateStr 日期yyyy-MM-dd
     * @return
     */
    public static List<String> getNumberShengXiao(String number, String dateStr) {
        ArrayList<String> data = new ArrayList<>();
        String[] balls = number.split(",");
        //当期的时间所属的生肖
        String shengXiao = getShengXiao(dateStr);
        int index = SHENGXIAO.indexOf(shengXiao);
        for (int j = 0; j < 7; j++) {
            if (StringUtils.isEmpty(balls[j])) {
                continue;
            }
            int postion = (49 + index - Integer.valueOf(balls[j])) % 12;
            data.add(SHENGXIAO.get(postion));
        }
        return data;
    }

    /**
     * 根据公历日期获取号码对应的生肖,返回字符串
     *
     * @param number  号码
     * @param dateStr 日期yyyy-MM-dd
     * @return
     */
    public static String getNumberZodiac(String number, String dateStr) {
        if (StringUtils.isEmpty(number)) {
            return Constants.DEFAULT_NULL;
        }
        StringBuffer zodiacBuf = new StringBuffer();
        String[] balls = number.split(",");

        if (null == balls || balls.length < 0) {
            return Constants.DEFAULT_NULL;
        }
        //当期的时间所属的生肖
        String shengXiao = getShengXiao(dateStr);
        int index = SHENGXIAO.indexOf(shengXiao);

        for (int j = 0; j < balls.length; j++) {
            if (StringUtils.isEmpty(balls[j])) {
                continue;
            }
            int postion = (49 + index - Integer.valueOf(balls[j])) % 12;

            if (postion >= 0 && postion <= 11) {
                zodiacBuf.append(SHENGXIAO.get(postion));
            } else {
                zodiacBuf.append(Constants.DEFAULT_NULL);
            }

            if (j < 6) {
                zodiacBuf.append(",");
            }
        }
        return zodiacBuf.toString();
    }

    /**
     * 修改返回号码为字符串
     *
     * @param number 号码
     * @return
     */
    public static String getNumberString(String number) {
        if (StringUtils.isEmpty(number)) {
            return Constants.DEFAULT_NULL;
        }
        // 定义返回字符
        StringBuffer numberBuf = new StringBuffer();
        String[] balls = number.split(",");

        if (null == balls || balls.length < 0) {
            return Constants.DEFAULT_NULL;
        }

        for (int i = 0; i < balls.length; i++) {
            numberBuf.append(balls[i]);

            if (i < 6) {
                numberBuf.append(",");
            }
        }
        return numberBuf.toString();
    }

    /**
     * 根据公历日期获取号码对应的五行
     *
     * @param number  号码
     * @param dateStr 日期yyyy-MM-dd
     * @return
     */
    public static List<String> getNumberWuXing(String number, String dateStr) {
        ArrayList<String> data = new ArrayList<>();
        String[] balls = number.split(",");

        if (null == balls || balls.length < 0) {
            return data;
        }
        for (int j = 0; j < 7; j++) {
            data.add(getNumWuXing(Integer.valueOf(balls[j]), dateStr));
        }
        return data;
    }

    /**
     * 根据公历日期获取号码对应的生肖
     *
     * @param num     号码
     * @param dateStr 日期yyyy-MM-dd
     * @return
     */
    public static String getShengXiao(int num, String dateStr) {
        //获取该日期的年份生肖
        String animalsYear = getShengXiao(dateStr);
        int index = SHENGXIAO.indexOf(animalsYear);
        if (index < 0) {
            return "";
        }
        int postion = (49 + index - num) % 12;
        return SHENGXIAO.get(postion);
    }

    /**
     * 根据公历日期获取对应的生肖
     *
     * @param dateStr 日期yyyy-MM-dd
     * @return
     */
    public static String getShengXiao(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return Constants.DEFAULT_NULL;
        }
        try {
            Integer year = CalendarUtil.solarToLunar(dateStr.replace("-", ""));
            String animalsYear = SHENGXIAO.get((year - 4) % 12);
            return animalsYear;
        } catch (Exception e) {
            logger.error("获取生肖出错:{}", e);
            for (int i = 0; i < 3; i++) {
                try {
                    Integer year = CalendarUtil.solarToLunar(dateStr.replace("-", ""));
                    String animalsYear = SHENGXIAO.get((year - 4) % 12);
                    return animalsYear;
                } catch (Exception e1) {
                    logger.error("再次获取生肖出错:{},{}", i, e);
                }
            }
            return Constants.DEFAULT_NULL;
        }
    }

    public static List<LhcPhotoListVO> getPhotoListVO(List<LhcPhoto> lhcPhotos) {
        if (lhcPhotos == null || lhcPhotos.size() == 0) {
            return null;
        }
        ArrayList<LhcPhotoListVO> result = new ArrayList<>();
        LhcPhotoListVO listVO = null;
        List<LhcPhotoVO> photos = null;
        String year = "";
        for (int i = 0; i < lhcPhotos.size(); i++) {
            LhcPhoto lhcPhoto = lhcPhotos.get(i);
            String year1 = lhcPhoto.getIssue().substring(0, 4);
            //如果年份改变,就创建一条新数据
            if (!year.equals(year1)) {
                year = year1;
                if (listVO != null) {
                    listVO.setPhotoList(photos);
                    result.add(listVO);
                }
                listVO = new LhcPhotoListVO();
                listVO.setYear(year1);
                photos = new ArrayList<>();
            }
            LhcPhotoVO photoVO = new LhcPhotoVO();
            photoVO.setIssue(Integer.valueOf(lhcPhoto.getIssue().substring(4)));
            photoVO.setUrl(lhcPhoto.getUrl());
            photos.add(photoVO);
        }
        listVO.setPhotoList(photos);
        result.add(listVO);
        return result;
    }

    /**
     * AI算法推荐号码
     * <p>
     * (年+日)%49+1
     * (年日)%49+1
     * (月+日)%49+1
     * (月日)%49+1
     * (日+日)%49+1
     * (日日)%49+1
     * (年月日)%49+1
     *
     * @param date
     * @return
     */
    public static List<Integer> aiNum(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        String[] dateArr = date.split("-");
        int year = Integer.valueOf(dateArr[0]);
        int month = Integer.valueOf(dateArr[1]);
        int day = Integer.valueOf(dateArr[2]);
        Integer num1 = (year + day) % 49 + 1;
        Integer num2 = Integer.valueOf((dateArr[0] + dateArr[2])) % 49 + 1;
        Integer num3 = (month + day) % 49 + 1;
        Integer num4 = Integer.valueOf((dateArr[1] + dateArr[2])) % 49 + 1;
        Integer num5 = (day + day) % 49 + 1;
        Integer num6 = Integer.valueOf((dateArr[2] + dateArr[2])) % 49 + 1;
        Integer num7 = Integer.valueOf((dateArr[0] + dateArr[1] + dateArr[2])) % 49 + 1;
        List<Integer> list = new ArrayList<>();
        list.add(num1);
        list.add(num2);
        list.add(num3);
        list.add(num4);
        list.add(num5);
        list.add(num6);
        list.add(num7);
        list = noRepeat(list, date);

        return list;
    }

    /**
     * AI算法推荐号码
     * <p>
     * (年+日+年A+日A)%49+1
     * (年日年A日A)%49+1
     * (月+日+月A+日A)%49+1
     * (月日月A日A)%49+1
     * (日+日+日A+日A)%49+1
     * (日日日A日A)%49+1
     * (年月日年A月A日A)%49+1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static List<Integer> aiNum(String date1, String date2) {
        if (StringUtils.isBlank(date1)) {
            return getSevenNum();
        }
        if (StringUtils.isBlank(date2)) {
            return aiNum(date1);
        }
        String[] dateArr1 = date1.split("-");
        String[] dateArr2 = date2.split("-");
        int year1 = Integer.valueOf(dateArr1[0]);
        int month1 = Integer.valueOf(dateArr1[1]);
        int day1 = Integer.valueOf(dateArr1[2]);
        int year2 = Integer.valueOf(dateArr2[0]);
        int month2 = Integer.valueOf(dateArr2[1]);
        int day2 = Integer.valueOf(dateArr2[2]);
        Integer num1 = (year1 + day1 + year2 + day2) % 49 + 1;
        Integer num2 = (int) (Long.valueOf((dateArr1[0] + dateArr1[2] + dateArr2[0] + dateArr2[2])) % 49) + 1;
        Integer num3 = (month1 + day1 + month2 + day2) % 49 + 1;
        Integer num4 = Integer.valueOf((dateArr1[1] + dateArr1[2] + dateArr2[1] + dateArr2[2])) % 49 + 1;
        Integer num5 = (day1 + day1 + day2 + day2) % 49 + 1;
        Integer num6 = Integer.valueOf((dateArr1[2] + dateArr1[2] + dateArr2[2] + dateArr2[2])) % 49 + 1;
        Integer num7 = (int) (Long.valueOf((dateArr1[0] + dateArr1[1] + dateArr1[2] + dateArr2[0] + dateArr2[1] + dateArr2[2])) % 49) + 1;
        List<Integer> list = new ArrayList<>();
        list.add(num1);
        list.add(num2);
        list.add(num3);
        list.add(num4);
        list.add(num5);
        list.add(num6);
        list.add(num7);
        String date = dateArr1[0] + dateArr2[1] + dateArr2[2];
        list = noRepeat(list, date);
        return list;
    }


    /**
     * 随机获取七个（1-49）不同的值
     */
    public static List<Integer> getSevenNum() {

        ArrayList<Integer> list = new ArrayList<>(7);
        Random random = new Random();
        while (list.size() < 7) {
            int num = random.nextInt(49) + 1;
            if (!list.contains(num)) {
                list.add(num);
            }
        }
        return list;
    }


    /**
     * 根据日期获取七个不同的值
     *
     * @param date
     */
    public static List<Integer> getListlikeSet(String date) {

        if (StringUtils.isBlank(date)) {
            return null;
        }
        String[] dateArr = date.split("-");
        int year = Integer.valueOf(dateArr[0]);
        int month = Integer.valueOf(dateArr[1]);
        int day = Integer.valueOf(dateArr[2]);
        Integer num1 = (year + day) % 7 + 1;
        ArrayList<Integer> list = new ArrayList<>(7);
        for (int i = 0; i < 6; i++) {
            list.add(month + num1 * i);
        }
        list.add((month + num1 * 7) % 49 + 1);
        return list;
    }

    /**
     * 把list重复的值替换掉
     *
     * @param list
     * @param date
     * @return
     */
    public static List<Integer> noRepeat(List<Integer> list, String date) {
        HashSet<Integer> set = new HashSet<>(list);
        int size = list.size();
        if (set.size() == set.size()) {
            return list;
        }
        List<Integer> data = getListlikeSet(date);
        ArrayList<Integer> newList = new ArrayList<>(7);
        for (int i = 0; i < size; i++) {
            int lastIndex = list.indexOf(list.get(i));
            if (i != lastIndex) {
                for (int j = 0; j < 7; j++) {
                    if (!list.contains(data.get(j))) {
                        newList.add(data.get(i));
                    }
                }
            } else {
                newList.add(list.get(i));
            }
        }
        return newList;
    }


//=======================以下是六合彩的所有判断是否中奖的方法================================

    /**
     * 获取六合彩中奖注数(一种玩法,有高低两种赔率)
     * (适用玩法:连肖中,连尾不中,连码三中二,连码二中特)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @param playId    玩法id
     * @param dateStr   开奖时间 yyyy-MM-dd
     * @return
     */
    public static List<Integer> isWinByOnePlayTwoOdds(String betNumber, String sgNumber, Integer playId, String dateStr, Integer lotteryId) {
        if (LhcUtils.generationLHCPlayIdList(PLAY_IDS_LX_WIN, lotteryId).contains(playId)) {
            // 连肖中玩法
            return isWinByLxWin(betNumber, sgNumber, playId, dateStr, lotteryId);
        } else if (LhcUtils.generationLHCPlayIdList(PLAY_IDS_LW_NO_WIN, lotteryId).contains(playId)) {
            // 连尾不中玩法
            return isWinByLwNoWin(betNumber, sgNumber, playId, lotteryId);
        } else if (LhcUtils.generationLHCPlayIdList(PLAY_IDS_LM_EZ, lotteryId).contains(playId)) {
            // 连码三中二,连码二中特
            return isWinLianMaEz(betNumber, sgNumber, playId, lotteryId);
        }
        return null;
    }

    /**
     * 获取六合彩中奖注数(一种玩法,只有一种赔率)
     * (适用玩法: 连码(三全中,二全中,特串,不中,连肖不中), 连尾中, 1-6龙虎, 五行)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @param playId    玩法id
     * @param dateStr   开奖时间 yyyy-MM-dd
     * @return
     */
    public static int isWinByOnePlayOneOdds(String betNumber, String sgNumber, Integer playId, String dateStr, Integer lotteryId) {
        if (LhcUtils.generationLHCPlayIdList(PLAY_IDS_LX_NO_WIN, lotteryId).contains(playId)) {
            // 连肖不中玩法
            return isWinByLxNoWin(betNumber, sgNumber, playId, dateStr, lotteryId);
        } else if (LhcUtils.generationLHCPlayIdList(PLAY_IDS_LW_WIN, lotteryId).contains(playId)) {
            // 连尾中玩法
            return isWinByLwWin(betNumber, sgNumber, playId, lotteryId);
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_ONE_SIX_LH, lotteryId).equals(playId)) {
            // 1-6龙虎玩法
            return isWinByOneSixLh(betNumber, sgNumber);
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_WUXING, lotteryId).equals(playId)) {
            // 五行玩法
            return isWinByWx(betNumber, sgNumber, dateStr);
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_LX_LXLZ, lotteryId).equals(playId) ||
                LhcUtils.generationLHCPlayId(PLAY_ID_LX_LXLBZ, lotteryId).equals(playId)) {
            // 六肖连中 或者 六肖连不中
            return isWinByLiuXiao(betNumber, sgNumber, playId, dateStr, lotteryId);
        } else if (LhcUtils.generationLHCPlayIdList(PLAY_IDS_NO_OPEN, lotteryId).contains(playId)) {
            // 不中,如五不中,六不中...
            return isWinByNoOpen(betNumber, sgNumber, playId, lotteryId);
        } else if (LhcUtils.generationLHCPlayIdList(PLAY_IDS_LM_QZ, lotteryId).contains(playId)) {
            // 连码三全中,二全中,特串
            return isWinLianMaQz(betNumber, sgNumber, playId, lotteryId);
        } else if (LhcUtils.generationLHCPlayIdList(PLAY_IDS_ZT_OTS, lotteryId).contains(playId)) {
            // 正特正(1-6)特
            return isWinZhengTeOneToSix(betNumber, sgNumber, playId, lotteryId);
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_TM_TMA, lotteryId).equals(playId)) {
            // 特码特码A
            return isWinZhengTeOneToSix(betNumber, sgNumber, playId, lotteryId);
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_ZM_ZMA, lotteryId).equals(playId)) {
            // 正码正码A
            return isWinByNum(betNumber, sgNumber);
        }
        return 0;
    }

    /**
     * 判断六合彩是否中奖,中奖返回中奖信息,不中则返回null(一种玩法,多种赔率)
     * (适用玩法:尾数,平特,特肖,半波)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @param playId    玩法id
     * @param dateStr   开奖时间 yyyy-MM-dd
     * @return
     */
    public static String isWinByOnePlayManyOdds(String betNumber, String sgNumber, Integer playId, String dateStr, Integer lotteryId) {
        String[] betNumArr = betNumber.split("@")[1].split(",");
        String[] sgArr = sgNumber.split(",");
        StringBuffer winStr = new StringBuffer();
        if (LhcUtils.generationLHCPlayId(PLAY_ID_WS_QW, lotteryId).equals(playId)) {
            // 尾数全尾
            List<Integer> sgList = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                sgList.add(Integer.valueOf(sgArr[i]) % 10);
            }
            for (String betNum : betNumArr) {
                if (sgList.contains(Integer.parseInt(betNum.replace("尾", "")))) {
                    winStr.append(betNum).append(",");
                }
            }
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_WS_TW, lotteryId).equals(playId)) {
            // 尾数特尾
            int tw = Integer.valueOf(sgArr[6]) % 10;
            for (String betNum : betNumArr) {
                if (tw == Integer.parseInt(betNum.replace("尾", ""))) {
                    winStr.append(betNum).append(",");
                }
            }
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_PT_PT, lotteryId).equals(playId)) {
            logger.info("平特信息：");
            // 平特
            List<String> sgList = LhcUtils.getNumberShengXiao(sgNumber, dateStr);
            String sgs = "";
            for (String sg : sgList) {
                sgs = sgs + sg;
            }
            for (String betNum : betNumArr) {
                if (sgs.contains(betNum)) {
                    winStr.append(betNum).append(",");
                }
            }

            StringBuffer winStr1 = new StringBuffer();
            for (String betNum : betNumArr) {
                if (sgList.contains(betNum)) {
                    winStr1.append(betNum).append(",");
                }
            }
            if (!winStr.toString().equals(winStr1.toString())) {
                logger.info("两边判断信息不准{}，{}", winStr, winStr1);
            }

            String nums = "";
            for (String num : betNumArr) {
                nums = nums + num;
            }
            try {
                String sgsUtf8 = new String(sgs.getBytes(StandardCharsets.UTF_8));
                String sgsUnicode = new String(sgsUtf8.getBytes(), StandardCharsets.UTF_8);
                String sgsGbk = new String(sgsUnicode.getBytes("GBK"));
                String numsUtf8 = new String(nums.getBytes(StandardCharsets.UTF_8));
                String numsUnicode = new String(numsUtf8.getBytes(), StandardCharsets.UTF_8);
                String numsGbk = new String(numsUnicode.getBytes("GBK"));
                logger.info("编码信息:{},{},{},{},{},{}", sgsUtf8, sgsUnicode, sgsGbk, numsUtf8, numsUnicode, numsGbk);
            } catch (Exception e) {
                logger.error("编码转换出错：{}", e);
            }
            logger.info("平特信息：{},{},{},{},{},{},{},{},{}", sgNumber, sgs, nums, winStr, betNumber, sgNumber, playId, dateStr, lotteryId);
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_TX_TX, lotteryId).equals(playId)) {
            // 特肖
            String tx = LhcUtils.getShengXiao(Integer.valueOf(sgArr[6]), dateStr);
            for (String betNum : betNumArr) {
                if (tx.equals(betNum)) {
                    winStr.append(betNum).append(",");
                }
            }
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_BB_RED, lotteryId).equals(playId) ||
                LhcUtils.generationLHCPlayId(PLAY_ID_BB_BLUE, lotteryId).equals(playId) ||
                LhcUtils.generationLHCPlayId(PLAY_ID_BB_GREEN, lotteryId).equals(playId)) {
            // 半波红波,蓝波,绿波
            List<String> numBanboList = LhcPlayRule.getNumBanboList(Integer.valueOf(sgArr[6]));
            for (String betNum : betNumArr) {
                if (numBanboList.contains(betNum)) {
                    winStr.append(betNum).append(",");
                }
            }
        } else if (LhcUtils.generationLHCPlayIdList(PLAY_IDS_ZT_OTS, lotteryId).contains(playId) || LhcUtils.generationLHCPlayId(PLAY_IDS_ZM, lotteryId).equals(playId)) {
            // 正特正(1-6)特 两面 或者 正码1-6
            int index = 6;
            /**if (PLAY_ID_ZT_ONE_LM.equals(playId) || PLAY_ID_ZM_ONE.equals(playId)) {
             index = 0;
             } else if (PLAY_ID_ZT_TWO_LM.equals(playId) || PLAY_ID_ZM_TWO.equals(playId)) {
             index = 1;
             } else if (PLAY_ID_ZT_THREE_LM.equals(playId) || PLAY_ID_ZM_THREE.equals(playId)) {
             index = 2;
             } else if (PLAY_ID_ZT_FOUR_LM.equals(playId) || PLAY_ID_ZM_FOUR.equals(playId)) {
             index = 3;
             } else if (PLAY_ID_ZT_FIVE_LM.equals(playId) || PLAY_ID_ZM_FIVE.equals(playId)) {
             index = 4;
             } else if (PLAY_ID_ZT_SIX_LM.equals(playId) || PLAY_ID_ZM_SIX.equals(playId)) {
             index = 5;
             }**/

            if (LhcUtils.generationLHCPlayId(PLAY_ID_ZT_ONE, lotteryId).equals(playId) || betNumber.indexOf("正码一") >= 0) {
                index = 0;
            } else if (LhcUtils.generationLHCPlayId(PLAY_ID_ZT_TWO, lotteryId).equals(playId) || betNumber.indexOf("正码二") >= 0) {
                index = 1;
            } else if (LhcUtils.generationLHCPlayId(PLAY_ID_ZT_THREE, lotteryId).equals(playId) || betNumber.indexOf("正码三") >= 0) {
                index = 2;
            } else if (LhcUtils.generationLHCPlayId(PLAY_ID_ZT_FOUR, lotteryId).equals(playId) || betNumber.indexOf("正码四") >= 0) {
                index = 3;
            } else if (LhcUtils.generationLHCPlayId(PLAY_ID_ZT_FIVE, lotteryId).equals(playId) || betNumber.indexOf("正码五") >= 0) {
                index = 4;
            } else if (LhcUtils.generationLHCPlayId(PLAY_ID_ZT_SIX, lotteryId).equals(playId) || betNumber.indexOf("正码六") >= 0) {
                index = 5;
            }
            List<String> numLiangMianList = LhcPlayRule.getNumLiangMianList(Integer.valueOf(sgArr[index]));
            numLiangMianList.add(sgArr[index]);
            for (String betNum : betNumArr) {
                if (numLiangMianList.contains(betNum)) {
                    winStr.append(betNum).append(",");
                }
                if ("49".equals(sgArr[index])) {
                    if ("大".equals(betNum) || "小".equals(betNum) || "单".equals(betNum) || "双".equals(betNum) || "合单".equals(betNum) || "合双".equals(betNum)
                            || "尾大".equals(betNum) || "尾小".equals(betNum)) {
                        winStr.append(betNum + "和单").append(",");
                    }
                }
            }
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_ZM_ZMA, lotteryId).equals(playId)) {
            // 正码正码A两面
            List<String> totalLiangMian = LhcUtils.getTotalLiangMian(sgNumber);
            for (String betNum : betNumArr) {
                if (totalLiangMian.contains(betNum)) {
                    winStr.append(betNum).append(",");
                }
            }
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_TM_TMA_LM, lotteryId).equals(playId)) {
            // 特码特码A两面
            List<String> temaLiangMian = LhcUtils.getTemaLiangMian(Integer.valueOf(sgArr[6]), dateStr);
            for (String betNum : betNumArr) {
                if (temaLiangMian.contains(betNum)) {
                    winStr.append(betNum).append(",");
                }
            }
        }
        if (winStr.length() > 0) {
            return winStr.substring(0, winStr.length() - 1);
        }
        return null;
    }

    /**
     * 获取六合彩中奖注数
     * (适用玩法:连码三中二,连码二中特)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @param playId    玩法id
     * @return
     */
    public static List<Integer> isWinLianMaEz(String betNumber, String sgNumber, Integer playId, Integer lotteryId) {
        String[] betNumArr = betNumber.split("@")[1].split(",");
        String[] sgArr = sgNumber.split(",");
        List<Integer> sgList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            sgList.add(Integer.valueOf(sgArr[i]));
        }
        int tema = Integer.valueOf(sgArr[6]);
        boolean tag = false;
        List<Integer> openNum = new ArrayList<>();
        for (String betNum : betNumArr) {
            if (sgList.contains(Integer.valueOf(betNum))) {
                openNum.add(Integer.valueOf(betNum));
            }
            if (tema == Integer.valueOf(betNum)) {
                tag = true;
            }
        }

        List<Integer> wins = new ArrayList<>(2); //默认第一个值是高赔率的中奖注数
        int size = openNum.size();
        int win1 = 0; // 高赔率的中奖注数
        int win2 = 0; // 低赔率的中奖注数
        if (LhcUtils.generationLHCPlayId(PLAY_ID_LM_EZT, lotteryId).equals(playId)) {
            // 连码二中特
            if (tag) {
                win1 = size;
            }
            win2 = MathUtil.countCnm(size, 2);
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_LM_SZE, lotteryId).equals(playId)) {
            // 连码三中二
            win1 = MathUtil.countCnm(size, 3);
            win2 = MathUtil.countCnm(size, 2) * (betNumArr.length - size);
        }
        wins.add(win1);
        wins.add(win2);
        return wins;
    }


    /**
     * 获取六合彩中奖注数
     * (适用玩法:正码正码A)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @return
     */
    public static int isWinByNum(String betNumber, String sgNumber) {
        String[] betNumArr = betNumber.split("@")[1].split(",");
        String[] sgArr = sgNumber.split(",");
        int winCount = 0;

        // 正码正码A
        List<Integer> sgList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            sgList.add(Integer.valueOf(sgArr[i]));
        }
        for (String betNum : betNumArr) {
            if (sgList.contains(Integer.valueOf(betNum))) {
                ++winCount;
            }
        }

        return winCount;
    }


    /**
     * 获取六合彩中奖注数
     * (适用玩法:正特正(1-6)特, 特码特码A)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @param playId    玩法id
     * @return
     */
    public static int isWinZhengTeOneToSix(String betNumber, String sgNumber, Integer playId, Integer lotteryId) {
        String[] sgArr = sgNumber.split(",");
        int index = 6;
        if (LhcUtils.generationLHCPlayId(PLAY_ID_ZT_ONE, lotteryId).equals(playId)) {
            index = 0;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_ZT_TWO, lotteryId).equals(playId)) {
            index = 1;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_ZT_THREE, lotteryId).equals(playId)) {
            index = 2;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_ZT_FOUR, lotteryId).equals(playId)) {
            index = 3;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_ZT_FIVE, lotteryId).equals(playId)) {
            index = 4;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_ZT_SIX, lotteryId).equals(playId)) {
            index = 5;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_TM_TMA, lotteryId).equals(playId)) {
            index = 6;
        }

        int winCount = 0;
        if (betNumber.contains("两面")) {
            int number = Integer.valueOf(sgArr[index]);
            String open = LhcUtils.numDetalis(number + "");
            String betNum = String.valueOf(betNumber.split("@")[1]);

            if (open.contains(betNum)) {
                winCount = 1;
            }
        } else {
            String number = sgArr[index];
            List<String> betList = Arrays.asList(betNumber.split("@")[1].split(","));

            if (betList.contains(number)) {
                winCount = 1;
            }
        }

        return winCount;
    }

    /**
     * 获取六合彩中奖注数
     * (适用玩法:连码三全中,二全中,特串)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @param playId    玩法id
     * @return
     */
    public static int isWinLianMaQz(String betNumber, String sgNumber, Integer playId, Integer lotteryId) {
        String[] betNumArr = betNumber.split("@")[1].split(",");
        String[] sgArr = sgNumber.split(",");
        List<Integer> sgList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            sgList.add(Integer.valueOf(sgArr[i]));
        }
        int tema = Integer.valueOf(sgArr[6]);
        boolean tag = false;
        List<Integer> openNum = new ArrayList<>();
        for (String betNum : betNumArr) {
            if (sgList.contains(Integer.valueOf(betNum))) {
                openNum.add(Integer.valueOf(betNum));
            }
            if (tema == Integer.valueOf(betNum)) {
                tag = true;
            }
        }
        if (LhcUtils.generationLHCPlayId(PLAY_ID_LM_TC, lotteryId).equals(playId) && tag) {
            return openNum.size();
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_LM_EQZ, lotteryId).equals(playId)) {
            return MathUtil.countCnm(openNum.size(), 2);
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_LM_SQZ, lotteryId).equals(playId)) {
            return MathUtil.countCnm(openNum.size(), 3);
        }
        return 0;
    }

    /**
     * 获取六合彩中奖注数
     * (适用玩法:不中)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @param playId    玩法id
     * @return
     */
    public static int isWinByNoOpen(String betNumber, String sgNumber, Integer playId, Integer lotteryId) {
        String[] betNumArr = betNumber.split("@")[1].split(",");
        String[] sgArr = sgNumber.split(",");
        List<Integer> sgList = new ArrayList<>();
        for (String sg : sgArr) {
            sgList.add(Integer.valueOf(sg));
        }
        List<String> noOpenNum = new ArrayList<>();
        for (String betNum : betNumArr) {
            if (!sgList.contains(Integer.valueOf(betNum))) {
                noOpenNum.add(betNum);
            }
        }
        int m = 50; //初始化m
        if (LhcUtils.generationLHCPlayId(PLAY_ID_NO_OPEN_FIVE, lotteryId).equals(playId)) {
            m = 5;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_NO_OPEN_SIX, lotteryId).equals(playId)) {
            m = 6;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_NO_OPEN_SEVEN, lotteryId).equals(playId)) {
            m = 7;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_NO_OPEN_EIGHT, lotteryId).equals(playId)) {
            m = 8;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_NO_OPEN_NINE, lotteryId).equals(playId)) {
            m = 9;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_NO_OPEN_TEN, lotteryId).equals(playId)) {
            m = 10;
        }
        return MathUtil.countCnmIsLong(noOpenNum.size(), m);
    }

    /**
     * 获取六合彩中奖注数
     * (适用玩法:六肖)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @param playId    玩法id
     * @param dateStr   开奖时间 yyyy-MM-dd
     * @return
     */
    public static int isWinByLiuXiao(String betNumber, String sgNumber, Integer playId, String dateStr, Integer lotteryId) {
        String teXiao = LhcUtils.getShengXiao(Integer.valueOf(sgNumber.split(",")[6]), dateStr);
        String[] betNumArr = betNumber.split("@")[1].split(",");
        List<String> noOpenSx = new ArrayList<>();
        for (String betNum : betNumArr) {
            if (!teXiao.equals(betNum)) {
                noOpenSx.add(betNum);
            }
        }
        if (betNumArr.length != noOpenSx.size() && LhcUtils.generationLHCPlayId(PLAY_ID_LX_LXLZ, lotteryId).equals(playId)) {
            // return MathUtil.countCnm(betNumArr.length, noOpenSx.size() + 1);
            return MathUtil.countCnm(betNumArr.length, 6) - MathUtil.countCnm(betNumArr.length - 1, 6);
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_LX_LXLBZ, lotteryId).equals(playId)) {
            return MathUtil.countCnm(noOpenSx.size(), 6);
        }
        return 0;
    }

    /**
     * 获取六合彩中奖注数
     * (适用玩法:连肖中)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @param playId    玩法id
     * @param dateStr   开奖时间 yyyy-MM-dd
     * @return
     */
    public static List<Integer> isWinByLxWin(String betNumber, String sgNumber, Integer playId, String dateStr, Integer lotteryId) {
        // 当期开出的生肖
        List<String> numberShengXiao = LhcUtils.getNumberShengXiao(sgNumber, dateStr);
        // 当期的低赔率的生肖
        String shengXiao = LhcUtils.getShengXiao(dateStr);
        String[] betNumArr = betNumber.split("@")[1].split(",");

        List<String> openSx = new ArrayList<>();
        for (String betNum : betNumArr) {
            if (numberShengXiao.contains(betNum)) {
                openSx.add(betNum);
            }
        }

        List<Integer> wins = new ArrayList<>(2); //默认第一个值是高赔率的中奖注数
        int size = openSx.size();
        int win1 = 0; // 高赔率的中奖注数
        int win2 = 0; // 低赔率的中奖注数

        int playWins = 2;
        if (LhcUtils.generationLHCPlayId(PLAY_ID_LX_TWO_WIN, lotteryId).equals(playId)) {
            playWins = 2;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_LX_THREE_WIN, lotteryId).equals(playId)) {
            playWins = 3;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_LX_FOUR_WIN, lotteryId).equals(playId)) {
            playWins = 4;
        }

        if (size >= playWins) {
            // 中奖了
            if (openSx.contains(shengXiao)) {
                // 中了低赔率的
                win1 = MathUtil.countCnm(size - 1, playWins);
                win2 = MathUtil.countCnm(size - 1, playWins - 1);
            } else {
                win1 = MathUtil.countCnm(size, playWins);
            }
        }

        wins.add(win1);
        wins.add(win2);

        return wins;
    }

    /**
     * 获取六合彩中奖注数
     * (适用玩法:连肖不中)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @param playId    玩法id
     * @param dateStr   开奖时间 yyyy-MM-dd
     * @return
     */
    public static int isWinByLxNoWin(String betNumber, String sgNumber, Integer playId, String dateStr, Integer lotteryId) {
        List<String> numberShengXiao = LhcUtils.getNumberShengXiao(sgNumber, dateStr);
        String[] betNumArr = betNumber.split("@")[1].split(",");
        List<String> noOpenSx = new ArrayList<>();
        for (String betNum : betNumArr) {
            if (!numberShengXiao.contains(betNum)) {
                noOpenSx.add(betNum);
            }
        }
        if (LhcUtils.generationLHCPlayId(PLAY_ID_LX_TWO_NO_WIN, lotteryId).equals(playId)) {
            return MathUtil.countCnm(noOpenSx.size(), 2);
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_LX_THREE_NO_WIN, lotteryId).equals(playId)) {
            return MathUtil.countCnm(noOpenSx.size(), 3);
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_LX_FOUR_NO_WIN, lotteryId).equals(playId)) {
            return MathUtil.countCnm(noOpenSx.size(), 4);
        }
        return 0;
    }

    /**
     * 获取六合彩中奖注数
     * (适用玩法:连尾中)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @param playId    玩法id
     * @return
     */
    public static int isWinByLwWin(String betNumber, String sgNumber, Integer playId, Integer lotteryId) {
        String[] betNumArr = betNumber.split("@")[1].split(",");
        String[] sgArr = sgNumber.split(",");
        List<Integer> sgList = new ArrayList<>();
        for (String sg : sgArr) {
            sgList.add(Integer.valueOf(sg) % 10);
        }
        List<String> openNum = new ArrayList<>();
        for (String betNum : betNumArr) {
            if (sgList.contains(Integer.valueOf(betNum.replace("尾", "")))) {
                openNum.add(betNum);
            }
        }
        if (LhcUtils.generationLHCPlayId(PLAY_ID_LW_TWO_WIN, lotteryId).equals(playId)) {
            return MathUtil.countCnm(openNum.size(), 2);
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_LW_THREE_WIN, lotteryId).equals(playId)) {
            return MathUtil.countCnm(openNum.size(), 3);
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_LW_FOUR_WIN, lotteryId).equals(playId)) {
            return MathUtil.countCnm(openNum.size(), 4);
        }
        return 0;
    }

    /**
     * 获取六合彩中奖注数
     * (适用玩法:连尾不中)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @param playId    玩法id
     * @return
     */
    public static List<Integer> isWinByLwNoWin(String betNumber, String sgNumber, Integer playId, Integer lotteryId) {
        String[] betNumArr = betNumber.split("@")[1].split(",");
        String[] sgArr = sgNumber.split(",");
        List<Integer> sgList = new ArrayList<>();
        for (String sg : sgArr) {
            sgList.add(Integer.valueOf(sg) % 10);
        }
        List<String> noOpenNum = new ArrayList<>();
        for (String betNum : betNumArr) {
            if (!sgList.contains(Integer.valueOf(betNum.replace("尾", "")))) {
                noOpenNum.add(betNum);
            }
        }

        List<Integer> wins = new ArrayList<>(2); //默认第一个值是高赔率的中奖注数
        int size = noOpenNum.size();
        int win1 = 0; // 高赔率的中奖注数
        int win2 = 0; // 低赔率的中奖注数

        int playWins = 2;
        if (LhcUtils.generationLHCPlayId(PLAY_ID_LW_TWO_NO_WIN, lotteryId).equals(playId)) {
            playWins = 2;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_LW_THREE_NO_WIN, lotteryId).equals(playId)) {
            playWins = 3;
        } else if (LhcUtils.generationLHCPlayId(PLAY_ID_LW_FOUR_NO_WIN, lotteryId).equals(playId)) {
            playWins = 4;
        }

        if (size >= playWins) {
            // 中奖了
            if (noOpenNum.contains("0尾")) {
                // 中了高赔率的
                win1 = MathUtil.countCnm(size - 1, playWins);
                // 中了低赔率的
                win2 = MathUtil.countCnm(size - 1, playWins - 1);
            } else {
                // 中了高赔率的
                win1 = MathUtil.countCnm(size, playWins);
            }
        }

        wins.add(win1);
        wins.add(win2);

        return wins;

    }

    /**
     * 获取六合彩中奖注数
     * (适用玩法:1-6龙虎)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @return
     */
    public static int isWinByOneSixLh(String betNumber, String sgNumber) {
        // 校验参数
        if (StringUtils.isBlank(betNumber) || StringUtils.isBlank(sgNumber)) {
            return 0;
        }
        // 分析投注内容
        String[] bet = betNumber.split("@");
        String numStr = bet[1].substring(1, 4);
        String betType = bet[1].substring(0, 1);
        String[] num = numStr.split("-");
        String[] sgNum = sgNumber.split(",");
        int one = Integer.valueOf(num[0]);
        int two = Integer.valueOf(num[1]);
        String str;
        if (sgNum[one - 1].compareTo(sgNum[two - 1]) > 0) {
            str = "龙";
        } else {
            str = "虎";
        }
        return str.equals(betType) ? 1 : 0;

//        String[] betNumArr = betNumber.split(";");
//        String longStr = betNumArr[0].length() > 2 ? betNumArr[0].substring(2) : "";
//        String huStr = betNumArr[1].length() > 2 ? betNumArr[1].substring(2) : "";
//        String[] sgArr = sgNumber.split(",");
//        List<Integer> sgList = new ArrayList<>();
//        for (String sg : sgArr) {
//            sgList.add(Integer.valueOf(sg));
//        }
//        int count = 0;
//        if (StringUtils.isNotBlank(longStr)) {
//            String[] longArr = longStr.split(",");
//            for (String betStr : longArr) {
//                int one = Integer.valueOf(betStr.charAt(0) + "");
//                int two = Integer.valueOf(betStr.charAt(2) + "");
//                if (sgList.get(one - 1) > sgList.get(two - 1)) {
//                    ++count;
//                }
//            }
//        }
//
//        if (StringUtils.isNotBlank(huStr)) {
//            String[] huArr = huStr.split(",");
//            for (String betStr : huArr) {
//                int one = Integer.valueOf(betStr.charAt(0) + "");
//                int two = Integer.valueOf(betStr.charAt(2) + "");
//                if (sgList.get(one - 1) < sgList.get(two - 1)) {
//                    ++count;
//                }
//            }
//        }
//
//        return count;
    }

    /**
     * 获取六合彩中奖注数
     * (适用玩法:五行)
     *
     * @param betNumber 投注号码
     * @param sgNumber  开奖号码
     * @param dateStr   开奖时间 yyyy-MM-dd
     * @return
     */
    public static int isWinByWx(String betNumber, String sgNumber, String dateStr) {
        String wuXing = LhcUtils.getNumWuXing(Integer.valueOf(sgNumber.split(",")[6]), dateStr);
        String[] betNumArr = betNumber.split("@")[1].split(",");
        for (String betNum : betNumArr) {
            if (wuXing.equals(betNum)) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 1分、5分、时时、香港六合彩 玩法ID生成
     *
     * @param playNumber
     * @param
     * @return
     */
    public static Integer generationLHCPlayId(String playNumber, Integer lotteryId) {
        return Integer.parseInt(lotteryId + playNumber);
    }

    /**
     * 1分、5分、时时、香港六合彩 玩法ID生成
     *
     * @param
     * @param
     * @return
     */
    public static List<Integer> generationLHCPlayIdList(List<String> playNumbers, Integer lotteryId) {
        List<Integer> replaceToNewPlayId = new ArrayList<Integer>();
        for (String number : playNumbers) {
            replaceToNewPlayId.add(Integer.parseInt(lotteryId + number));
        }
        return replaceToNewPlayId;
    }


    /**
     * web历史开奖
     *
     * @param info 倒序的开奖结果和期数
     * @return
     */
    public static List<LhcLskjVO> lishiKaiJiangWeb(List<LhcLotterySg> info) {
        if (info == null || info.size() == 0) {
            return new ArrayList<>();
        }
        int totalIssue = info.size();
        ArrayList<LhcLskjVO> result = new ArrayList<>();
        for (int i = 0; i < totalIssue; i++) {
            LhcLskjVO data = new LhcLskjVO();
            LhcLotterySg lotterySgModel = info.get(i);
            String date = lotterySgModel.getTime();
            String issue = lotterySgModel.getIssue();
            String year = lotterySgModel.getYear();
            data.setIssue(year + issue);
            data.setTime(date);
            String sg = lotterySgModel.getNumber();
            String[] balls = sg.split(",");
            //当期的时间所属的生肖
            String shengXiao = getShengXiao(date);
            int index = SHENGXIAO.indexOf(shengXiao);
            data.setZhengOne(balls[0]);
            data.setZhengOneSx(SHENGXIAO.get((49 - Integer.valueOf(balls[0]) + index) % 12) + "/" + getNumWuXing(Integer.valueOf(balls[0]), date));
            data.setZhengTwo(balls[1]);
            data.setZhengTwoSx(SHENGXIAO.get((49 - Integer.valueOf(balls[1]) + index) % 12) + "/" + getNumWuXing(Integer.valueOf(balls[1]), date));
            data.setZhengThree(balls[2]);
            data.setZhengThreeSx(SHENGXIAO.get((49 - Integer.valueOf(balls[2]) + index) % 12) + "/" + getNumWuXing(Integer.valueOf(balls[2]), date));
            data.setZhengFour(balls[3]);
            data.setZhengFourSx(SHENGXIAO.get((49 - Integer.valueOf(balls[3]) + index) % 12) + "/" + getNumWuXing(Integer.valueOf(balls[3]), date));
            data.setZhengFive(balls[4]);
            data.setZhengFiveSx(SHENGXIAO.get((49 - Integer.valueOf(balls[4]) + index) % 12) + "/" + getNumWuXing(Integer.valueOf(balls[4]), date));
            data.setZhengSix(balls[5]);
            data.setZhengSixSx(SHENGXIAO.get((49 - Integer.valueOf(balls[5]) + index) % 12) + "/" + getNumWuXing(Integer.valueOf(balls[5]), date));
            data.setTeMa(balls[6]);
            data.setTeMaSx(SHENGXIAO.get((49 - Integer.valueOf(balls[6]) + index) % 12) + "/" + getNumWuXing(Integer.valueOf(balls[6]), date));
            int total = 0;
            for (int j = 0; j < 7; j++) {
                total += Integer.valueOf(balls[j]);
            }
            data.setTotal(total);
            if (total >= 175) {
                data.setTotalDx("总大");
            } else {
                data.setTotalDx("总小");
            }
            if (total % 2 == 1) {
                data.setTotalDs("总双");
            } else {
                data.setTotalDs("总双");
            }
            int tema = Integer.valueOf(balls[6]);
            if (tema > 24) {
                data.setTeMaDx("大");
            } else {
                data.setTeMaDx("小");
            }
            if (tema % 2 == 1) {
                data.setTeMaDs("单");
            } else {
                data.setTeMaDs("双");
            }
            data.setTeMaWs(tema % 10 + "尾");
            data.setTeMaHs(tema / 10 + tema % 10);

            data.setTeMaJy(JIAQIN.contains(getShengXiao(tema, date)) ? "家禽" : "野兽");
            result.add(data);
        }

        return result;
    }
}
