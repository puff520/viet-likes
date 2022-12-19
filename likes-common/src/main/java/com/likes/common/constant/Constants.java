package com.likes.common.constant;

import com.likes.common.enums.threeway.ThreeWayTypeNumEnum;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Constants {

    /**
     * 人工存提订单类型: 0-存入
     */
    public static final Integer ART_ORDER_TYPE_DEPOSIT = 0;
    /**
     * 人工存提订单类型: 1-提出
     */
    public static final Integer ART_ORDER_TYPE_WITHDRAW = 1;
    public static final String DEPOSITS_NULL = "-";
    public static String LOCAL_ADDRESS = "";
    public static int THREAD_POOL_CORE_POOL_SIZE = 8;
    public static int THREAD_POOL_MAX_POOL_SIZE = THREAD_POOL_CORE_POOL_SIZE * 10;
    public static int THREAD_POOL_QUEUE_CAPACITY = 2000000;
    public static int THREAD_POOL_AWAIT_TERMINATION_SECONDS = 30;//等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止

    static {
        try {
            LOCAL_ADDRESS = InetAddress.getLocalHost().getHostAddress();
            THREAD_POOL_CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 10;
            if (THREAD_POOL_CORE_POOL_SIZE < 32) {
                THREAD_POOL_CORE_POOL_SIZE = 32;
            } else {
                THREAD_POOL_MAX_POOL_SIZE = THREAD_POOL_CORE_POOL_SIZE * 10;
            }
        } catch (Exception e) {
        }
    }

    // 默认编码
    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String ANDROID_STRING = "Android";
    public static final String IOS_STRING = "IOS";
    public static final String H5_STRING = "H5";
    public static final String WEB_STRING = "WEB";

    // token过期时间（秒）2周
    public static final Long TOKEN_EXPIRES = 86400 * 14L;

    public static String HEADER_USER_IP = "userIP";
    public static final String CLIENT_TYPE_STRING = "clientType";
    // 手机型号
    public static final String CLIENT_PHONE_MODEL = "phoneModel";
    public static String ATTR_USER_ID = "live-user-id";
    public static String ATTR_AGENT_USER_ID = "likes=agent-user-id";
    // 网易滑动验证启停标志：1，开启；0，关闭
    public static String FUNCTION_ENABLE_FLAG = "1";
    // 功能启停标识：1，开启；0，关闭
    public static String SWIPE_VERIFICATION_SWIFT_OPEN = "0";
    //银行卡启用状态  0：禁用；1：启用；2：自动启用
    public static Integer BANK_WORK_STATUS_START = 1;
    public static Integer BANK_WORK_STATUS_STOP = 0;

    //橘子 异步返回数据的类型，默认1 返回数据为表单数据（Content-Type: application/x-www-form-urlencoded; charset=utf-8），2 返回post json数据。
    public static String PAYMENT_STATUS_TWO = "2";


    /**
     * 默认页数
     */
    public static final int DEFAULT_PAGE_NO = 1;
    /**
     * 默认页大小
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 财务角色id
     */
    public static final int CAIWU_ACCOUNT_ROLE_ID = 10000006;

    // Aes加密解密key
    public static final String AES_KEY = "ruanjie2018@379gc!834gfg?d30RJcaipiao";


    // App端参数校验秘钥
    public static final String APP_KEY = "ruanjie2018@jlj34ij34lkj?d30RJcaipiao";


    // 定义存放在session中的用户
    public static final String ACCTOKEN_KEY = "acctoken";
    public static final String FEGIN_SIGN = "Fegin_sign";
    public static final String FEGIN_SIGN_VALUE = "45165bde56864010a3f0cfc8c18bc007";


    // 验证码有效时间（300秒)
    public static final Long CAPTCHA_VALID_SECONDS = 300L;


    // 长龙赔率缓存时间(分钟)
    public static final Integer DRAGON_ODDS_CACHE_TIME = 10;

    public static final String KJ_TENSSC = "kj_tenssc"; // 开奖号码推送--十分时时彩
    public static final String KJ_ONELHC = "kj_onelhc"; // 开奖号码推送--一分六合彩
    public static final String KJ_TENBJPKS = "kj_tenbjpks"; // 开奖号码推送--十分PK10

    public static final String KJ_FC3D = "kj_fc3d";// 开奖号码推送--体彩3d福彩

    public static final String KJ_XGLHC_RECOMMEND = "kj_xglhc_recommend"; // 开奖号码推荐--香港六合彩

    //来源(用于报表统计)
    public static final String SOURCE_WEB = "WEB";

    // 默认空值
    public static final String DEFAULT_NULL = "";
    // 默认空值
    public static final String DEFAULT_NICKNAME = "用户";
    // 默认空值
    public static final String DEFAULT_NAME = "";
    // 默认Long
    public static final Long DEFAULT_LONG = 0L;
    // 默认Integer -1
    public static final Integer DEFAULT_NEGATIVE = -1;
    // 默认Integer
    public static final Integer DEFAULT_INTEGER = 0;
    // 默认Integer one
    public static final Integer DEFAULT_ONE = 1;
    // 默认Integer TWO
    public static final Integer DEFAULT_TWO = 2;
    // 默认Integer THREE
    public static final Integer DEFAULT_THREE = 3;
    // 默认Integer FOUR
    public static final Integer DEFAULT_FOUR = 4;
    // 默认Integer FIVE
    public static final Integer DEFAULT_FIVE = 5;
    // 默认Integer DEFAULT_INTEGER
    public static final Integer DEFAULT_SIX = 6;
    // 默认Integer SERVER
    public static final Integer DEFAULT_SEVEN = 7;
    // 默认Float
    public static final float DEFAULT_FLOAT = 0;
    // 刷新Redis数据
    public static final Integer DEFAULT_REDIS_PAGESIZE = 10000;
    // 增加数据连接符号
    public static final String CONNECTIONSYMBOL = ",";
    // 同步用户数据数量
    public static final Integer DEFAULT_SYNONER_USER = 10;


    //排序
    public static final String APP_SORT_TYPE = "DESC";
    public static final String APP_SORT_NAME = "create_time";


    /***
     * ==========================================ACT============================================
     */
    public static final Integer DEFAULT_BIGORSMALL_MEDIAN = 810;// 开奖号码计算大小,中间值

    public static final String BIGORSMALL_BIG = "大";

    public static final String BIGORSMALL_BIG_NUMBER = "大单";


    public static final String CROWN_BIGORSMALL_BIG = "冠亚大";

    public static final String ZONG_BIGORSMALL_BIG = "总大";

    public static final String BIGORSMALL_SMALL = "小";


    public static final String TOTAL_BIGORSMALL_SMALL = "总和小";

    public static final String CROWN_BIGORSMALL_SMALL = "冠亚小";

    public static final String ZONG_BIGORSMALL_SMALL = "总小";

    public static final String BIGORSMALL_SAME = "和";

    public static final String BIGORSMALL_ODD_NUMBER = "单";// 奇数

    public static final String TOTAL_BIGORSMALL_ODD_NUMBER = "总和单";// 奇数

    public static final String CROWN_BIGORSMALL_ODD_NUMBER = "冠亚单";// 奇数

    public static final String ZONG_BIGORSMALL_ODD_NUMBER = "总单";// 奇数


    public static final String BIGORSMALL_EVEN_NUMBER = "双";// 偶数


    public static final String CROWN_BIGORSMALL_EVEN_NUMBER = "冠亚双";// 偶数

    public static final String ZONG_BIGORSMALL_EVEN_NUMBER = "总双";// 偶数

    public static final String JOIN_BIGORSMALL_EVEN_NUMBER = "合双";// 偶数

    public static final Integer BIGORSMALL_GOLD_START = 210;

    public static final Integer BIGORSMALL_GOLD_END = 695;

    public static final String BIGORSMALL_GOLD_TYPE = "金";

    public static final Integer BIGORSMALL_WOOD_START = 696;

    public static final Integer BIGORSMALL_WOOD_END = 763;

    public static final String BIGORSMALL_WOOD_TYPE = "木";

    public static final Integer BIGORSMALL_WATER_START = 764;

    public static final Integer BIGORSMALL_WATER_END = 855;

    public static final String BIGORSMALL_WATER_TYPE = "水";

    public static final Integer BIGORSMALL_FIRE_START = 856;

    public static final Integer BIGORSMALL_FIRE_END = 923;

    public static final String BIGORSMALL_FIRE_TYPE = "火";

    public static final Integer BIGORSMALL_SOIL_START = 924;

    public static final Integer BIGORSMALL_SOIL_END = 1410;

    public static final String BIGORSMALL_SOIL_TYPE = "土";

    public static final String PLAYRESULT_DRAGON = "龙";

    public static final String PLAYRESULT_TIGER = "虎";

    public static final String BIGORSMALL_POULTRY = "家禽";

    public static final String BIGORSMALL_POULTRY_SHORT = "禽";

    public static final String BIGORSMALL_BEAST = "野兽";

    public static final String BIGORSMALL_BEAST_SHORT = "兽";

    public static final String BIGORSMALL_RED = "红波";

    public static final String BIGORSMALL_GREEN = "绿波";

    public static final String BIGORSMALL_BLUE = "蓝波";

    /***************************** 六合彩号码 **************************************/
    public static final String BIGORSMALL_TWO = "02";
    public static final String BIGORSMALL_FORTY_FOUR = "44";
    public static final String BIGORSMALL_FORTY_EIGHT = "48";
    public static final String BIGORSMALL_FORTY_NINE = "49";


    public static final String LHC_PLAYWAY_ZT_TWO = "正2特";// 正2特
    public static final Integer LHC_BIGORSMALL_MEDIAN = 25;// 开奖号码计算大小,中间值


    /**
     * a账单号
     */
    public static final String AG_API_PARAM_BILLNO = "billno";

    /**
     * ======-xml 类型 =============== BR下注记录，EBR电子游戏下注记录，TR户口转账记录，GR游戏结果，LBR彩票下注记录，LGR彩票结果
     */
    public static final String AG_XML_DATA_TYPE_BR = "BR";
    public static final String AG_XML_DATA_TYPE_TR = "TR";
    public static final String AG_XML_DATA_TYPE_GR = "GR";
    public static final String THIRD_GAME_OUT = "0";
    /**
     * ==========================================AG END============================================
     */


    //0 真人 1 试玩
    public static final Integer DB_GAME_TYPE = 0;
    public static final Integer DB_GAME_DEMO_TYPE = 1;

    // 第三方游戏账号类型
    public static final String DB_ACCOUNT_TYPE = "T_DB";

    public static final String MG_EXIT_KEY = "MG_KEY_";

    public static final String MG_INIT_KEY = "MG_INIT_";

    /**
     * 推广随机码标志字符
     */
    public static final String SYSTEM_RANDOM_CODE_PROMOTION = "PROMOTION";


    /**
     * 银行是否能绑卡，0-禁用，1-启用
     */
    public static final int BANK_CARD_BINGDING_PROHABIT = 0;

    /**
     * 默认连接符
     */
    public static final String UNDERLINE_CONNECTOR = "_";

    /**
     * 默认统计期数: 100; 最大统计期数:1000
     */
    public static final int DEFAULT_STAT_ISSUES = 100;

    /* 订单状态 */
    public static final String ORDER_WAIT = "WAIT";
    // 心水数据放入ES中Type
    public static final String esType = "recommend";

    public static final String CLIENT_SOURCE_ANDROID = "Android";
    public static final String CLIENT_SOURCE_IOS = "iOS";
    public static final String CLIENT_SOURCE_H5 = "H5";
    public static final String CLIENT_SOURCE_WEB = "web";


    // 数据等级集合缓存时间
    public static final Integer DATA_LEVEL_TIME = 3;

    // 无效的贴子数据
    public static final Set<String> INVALIDARRAY = new HashSet<>();


    static {
        INVALIDARRAY.add("&nbsp");
    }

    // 用户数据缓存时间(秒)
    public static final Integer CUSTOMER_CACHE_XS_TIME = 10;


    // 支付回调成功
    public static final String PAY_CALLBACK_SUSSESS = "success";
    public static final String PAY_CALLBACK_OK = "ok";
    public static final String PAY_CALLBACK_BIG_OK = "OK";
    public static final String PAY_CALLBACK_BIG_SUCCESS = "SUCCESS";
    public static final String PAY_CALLBACK_STATUS = "000000";
    // 支付回调失败
    public static final String PAY_CALLBACK_FAIL = "fail";

    // 业务要求每个用户发帖子间隔时间为8分钟
    public static final int REQUEST_TIMEOUT_MILLIS = 480000;

    // 腾飞
    public static final String PAY_PLATFORM_THOUSAND_THIRTEEN = "10013";
    // 777支付
    public static final String PAY_PLATFORM_THOUSAND_SIXTEEN = "10016";
    // 熊猫
    public static final String PAY_PLATFORM_THOUSAND_SEVENTEEN = "10017";
    // 聚财厅
    public static final String PAY_PLATFORM_THOUSAND_EIGHTTEEN = "10018";
    // 富豪
    public static final String PAY_PLATFORM_TWENTY_ONE = "10021";
    // CK
    public static final String PAY_PLATFORM_TWENTY_THREE = "10023";
    // ff
    public static final String PAY_PLATFORM_TWENTY_FOUR = "10024";
    //超凡
    public static final String PAY_PLATFORM_TWENTY_SIX = "10026";
    //信达
    public static final String PAY_PLATFORM_TWENTY_SEVENTEEN = "10027";
    //喜付
    public static final String PAY_PLATFORM_TWENTY_EIGHTTEEN = "10028";
    //亿咖
    public static final String PAY_PLATFORM_TWENTY_NINE = "10029";
    //FY
    public static final String PAY_PLATFORM_THIRTY_ONE = "10031";
    //钉钉
    public static final String PAY_PLATFORM_THIRTY_TWO = "10032";
    //橘子
    public static final String PAY_PLATFORM_THIRTY_THREE = "10033";
    //渔夫
    public static final String PAY_PLATFORM_THIRTY_FOUR = "10034";
    //epay
    public static final String PAY_PLATFORM_THIRTY_FIVE = "10035";
    //路路通
    public static final String PAY_PLATFORM_THIRTY_SIX = "10036";
    //谷歌金服
    public static final String PAY_PLATFORM_THIRTY_SEVEN = "10037";
    //ESPAY
    public static final String PAY_PLATFORM_THIRTY_EIGHT = "10038";
    //33aa
    public static final String PAY_PLATFORM_THIRTY_NINE = "10039";
    //亿盛
    public static final String PAY_PLATFORM_FORTY = "10040";
    //四方
    public static final String PAY_PLATFORM_FORTY_ONE = "10041";
    //99支付
    public static final String PAY_PLATFORM_FORTY_TWO = "10042";
    //灿星支付
    public static final String PAY_PLATFORM_FORTY_THREE = "10043";
    //King支付
    public static final String PAY_PLATFORM_FORTY_FOUR = "10044";
    //ace
    public static final String PAY_PLATFORM_FORTY_FIVE = "10045";
    //新乐富
    public static final String PAY_PLATFORM_FORTY_SIX = "10046";
    //爱支付
    public static final String PAY_PLATFORM_FORTY_SEVEN = "10047";
    //AMK
    public static final String PAY_PLATFORM_FORTY_EIGHT = "10048";
    //默默支付
    public static final String PAY_PLATFORM_FORTY_NINE = "10049";
    //众宝支付
    public static final String PAY_PLATFORM_FORTY_ZB = "10050";


    //支付service 统一后缀
    public static final String PAY_PLATFORM_SERVER_SUFFIX = "PayService";
    //支付回调对象 后缀
    public static final String PAY_PLATFORM_NOTIFY_MODLE_SUFFIX = "Notify";
    //支付回调service 后缀
    public static final String PAY_PLATFORM_NOTIFY_SERVER_SUFFIX = "PayNotifyService";


    // 富豪扫码
    public static final String PAY_FH_SM = "sm";
    // 支付返回链接
    public static final Integer PAY_RETURN_CODE_ZERO = 0;
    // 支付返回HTML
    public static final Integer PAY_RETURN_CODE_ONE = 1;
    // 支付返回链接生成二维码
    public static final Integer PAY_RETURN_CODE_TWO = 2;
    // 聚财厅
    public static final String PAY_PLATFORM_THOUSAND_EIGHTEEN = "10018";

    /**
     * 默认银行卡收款账户最小值
     */
    public static final int DEFALT_MIN_RECHARGE_ACCOUNT_AMOUNT = 100;


    // 客户端会有发送 undefined
    public static final String UNDEFINED = "undefined";


    // *******************************心水推荐评论管理操作*****************************


    public static final String DELETEXSPL = "/lhcXspl/delete.json";


    /* 特码玩法- 正1特 */
    public static final int LHC_PLAY_Z1T = 120105;

    /* 特码玩法- 正2特 */
    public static final int LHC_PLAY_Z2T = 120106;

    /* 特码玩法- 正3特 */
    public static final int LHC_PLAY_Z3T = 120107;

    /* 特码玩法- 正4特 */
    public static final int LHC_PLAY_Z4T = 120108;

    /* 特码玩法- 正5特 */
    public static final int LHC_PLAY_Z5T = 120109;

    /* 特码玩法- 正6特 */
    public static final int LHC_PLAY_Z6T = 120110;

    /* 特码玩法- 二中特 */
    public static final int LHC_PLAY_2ZT = 120112;

    /* 特码玩法- 特串 */
    public static final int LHC_PLAY_TC = 120113;


    /* 特码玩法- 蓝波 */
    public static final int LHC_PLAY_BLUE = 120117;


    /* 特码玩法- 平特 */
    public static final int LHC_PLAY_PT = 120127;


    /* 福彩3D玩法- 2D */
    public static final int FC3D_PLAY_2D = 180206;


    /* 福彩3D玩法- 1D */
    public static final int FC3D_PLAY_1D = 180204;


    /* 海南七星彩玩法- 定位胆 */
    public static final int QXC_PLAY_DWD = 170305;

    /* 排列3/5 - 两面 */

    /* 排列3/5 - 定位胆 */
    public static final int PL35_PLAY_DWD = 170204;

    // ========================================================
    // 重庆时时彩
    public static final int LOTTERY_CQSSC = 1101;
    // 新疆时时彩
    public static final int LOTTERY_XJSSC = 1102;
    // 天津时时彩
    public static final int LOTTERY_TJSSC = 1103;
    // 六合彩
    public static final int LOTTERY_LHC = 1201;
    // 北京PK10
    public static final int LOTTERY_BJPKS = 1301;
    // 幸运飞艇
    public static final int LOTTERY_XYFT = 1401;
    // PCegg蛋蛋
    public static final int LOTTERY_PCEGG = 1501;
    // 大乐透
    public static final int LOTTERY_DLT = 1701;
    // 排列3/5
    public static final int LOTTERY_PL35 = 1702;
    // 海南七星彩
    public static final int LOTTERY_HNQXC = 1703;
    // 双色球
    public static final int LOTTERY_SSQ = 1801;
    // 福彩3D
    public static final int LOTTERY_FC3D = 1802;
    // 七乐彩
    public static final int LOTTERY_QLC = 1803;
    // 一分六合彩
    public static final int LOTTERY_ONELHC = 1202;
    // 德州时时彩
    public static final int LOTTERY_DZSSC = 1106;
    // 德州PKS
    public static final int LOTTERY_DZPKS = 1304;
    // 五分六合彩
    public static final int LOTTERY_FIVELHC = 1203;
    // 五分时时彩
    public static final int LOTTERY_FIVESSC = 1105;
    // 五分PKS
    public static final int LOTTERY_FIVEPKS = 1303;
    // 时时六合彩
    public static final int LOTTERY_AMLHC = 1204;
    // 十分时时彩
    public static final int LOTTERY_TENSSC = 1104;
    // 十分PKS
    public static final int LOTTERY_TENPKS = 1302;
    // 腾讯分分彩
    public static final int LOTTERY_TXFFC = 1601;
    // 澳洲ACT
    public static final int LOTTERY_AUSACT = 2201;
    // 澳洲时时彩
    public static final int LOTTERY_AUSSSC = 2202;
    // 澳洲PKS
    public static final int LOTTERY_AUSPKS = 2203;
    // 澳洲快三
    public static final int LOTTERY_AZKS = 2301;
    // 德洲快三
    public static final int LOTTERY_DZKS = 2302;
    // 德洲PC蛋蛋
    public static final int LOTTERY_DZPCEGG = 1502;
    // 德洲幸运飞艇
    public static final int LOTTERY_DZXYFT = 1402;
    // 新加坡六合彩
    public static final int LOTTERY_XJPLHC = 1205;

    // ================================== 下三路位置
    public static final String THREE_WAY_PART_A = "partA"; // A位
    public static final String THREE_WAY_PART_B = "partB"; // B位
    public static final String THREE_WAY_PART_C = "partC"; // C位
    public static final String THREE_WAY_PART_D = "partD"; // C位


    // ================================= 下三路层级名称
    public static final String THREE_DS_LEVEL = "DS";
    public static final String THREE_DX_LEVEL = "DX";

    // ================================= 下三路单双层级
    public static final Set<Integer> THREE_DS_GROUP = new HashSet<Integer>() {
        {
            add(ThreeWayTypeNumEnum.SPECIAL_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_ONE_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_TWO_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_THREE_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_FOUR_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_FIVE_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_SIX_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.SPECIAL_TOTAL_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_ONE_TOTAL_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_TWO_TOTAL_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_THREE_TOTAL_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_FOUR_TOTAL_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_FIVE_TOTAL_SINGLE_DOUBLE.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_SIX_TOTAL_SINGLE_DOUBLE.getValue());
        }
    };

    // ================================= 下三路大小层级
    public static final Set<Integer> THREE_DX_GROUP = new HashSet<Integer>() {
        {
            add(ThreeWayTypeNumEnum.SPECIAL_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_TOTAL_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_ONE_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_TWO_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_THREE_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_FOUR_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_FIVE_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_SIX_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.SPECIAL_TAIL_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_ONE_TAIL_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_TWO_TAIL_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_THREE_TAIL_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_FOUR_TAIL_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_FIVE_TAIL_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_SIX_TAIL_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_TAIL_SMALL_BIG.getValue());
            add(ThreeWayTypeNumEnum.POSITIVE_DRAGON_TIGER.getValue());
            add(ThreeWayTypeNumEnum.SPECIAL_ANIMAL.getValue());
        }
    };

    // ===============================中新支付============================
    public final static String PAY_RETURN_FAIL = "FAIL";
    // ===============================超凡支付============================
    public final static String PAY_RETURN_TRUE = "true";
    // ===============================喜付支付============================
    public final static String PAY_RETURN_STATUS = "{\"code\":\"SUCCESS\",\"msg\":\"message\"}";
    // ===============================创世支付============================
    public final static String PAY_STATUS_ONE = "1"; //成功
    public final static String PAY_RETURN_TWO = "2"; //失败

    public final static String PAY_ACE_RETURN_TWO = "2"; //成功

    public final static String PAY_PRAMS_STATUS_ONE = "200"; //成功
    // =============================代理===========================
    public final static String DAY_END_TIME = " 23:59:59";
    public final static BigDecimal ZERO = new BigDecimal("0");
    public final static String OFF = "off";
    public final static String ON = "on";


    public final static String PAY_RETURN_CX = "00"; //成功
    // ========================================= 聊天室
    public final static String CHAT_XYFT = "CHAT_XYFT"; // 幸运飞艇系列聊天室
    public final static String CHAT_NIUNIU = "CHAT_NIUNIU"; // 牛牛系列聊天室

    // 默认
    public static final List<Integer> playDefultList = Lists.newArrayList();

    // 六合彩系列
    public static final List<Integer> playLhcList = Lists.newArrayList(1201, 1202, 1203, 1204, 1205);

    // 澳洲系列
    public static final List<Integer> playACTList = Lists.newArrayList(2201, 2202, 2203);

    // 时时彩系
    public static final List<Integer> playSSCList = Lists.newArrayList(1101, 1102, 1103, 1104, 1105, 1106);

    // PK10系列
    public static final List<Integer> playPKSList = Lists.newArrayList(1301, 1302, 1303, 1304);

    // 幸运飞艇系列
    public static final List<Integer> playXYFTList = Lists.newArrayList(1401, 1402);

    // 番摊系列
    public static final List<Integer> playFTList = Lists.newArrayList(2001, 2002, 2003);

    // 牛牛系列
    public static final List<Integer> playNNList = Lists.newArrayList(1901, 1902, 1903);

    // PC蛋蛋系列
    public static final List<Integer> playPCGGList = Lists.newArrayList(1501, 1502);

    // 分分彩系列
    public static final List<Integer> playFFCList = Lists.newArrayList(1601);

    // 体彩系列
    public static final List<Integer> playTCList = Lists.newArrayList(1701, 1702, 1703);

    // 福彩系列
    public static final List<Integer> playFCList = Lists.newArrayList(1801, 1802, 1803);

    // 快三系列
    public static final List<Integer> playKSList = Lists.newArrayList(2301, 2302);

    // 新彩种系列2301:澳洲快三, 2302德州快三, 1402德州幸运飞艇, 1502德州pc蛋蛋,1205新加坡六合彩
    public static final List<Integer> NEW_LOTTERY_ID_LIST = Lists.newArrayList(2301, 2302, 1402, 1502, 1205);
    // 新彩种胆拖玩法的playid
    public static final List<Integer> NEW_LOTTERY_DT_PLAYID_LIST = Lists.newArrayList(230105, 230109, 230205, 230209);

    /**
     * 打和
     */
    public static final String HE = "HE";
    /**
     * 等待开奖
     */
    public static final String WAIT = "WAIT";
    /**
     * 撤单
     */
    public static final String BACK = "BACK";

    /**
     * 已经开奖状态
     */
    public static final String AUTO = "AUTO";

    // 聊天返回成功码
    public static final String RETURN_KEY = "200";

    // 下三路初始数据A路值
    public static final List<Integer> threeInitialList = Lists.newArrayList(1, 2, 3, 4, 5);


    /*************************代付*******************************************************/

    public static final String STATUS_AUTO = "AUTO";


    //操作类型
    public static final Integer EXTERNAL_TYPE = 110;


    /**
     * 基本字符常量
     */
    public static final String STR_ZERO = "0";
    public static final String STR_AT = "@";
    public static final String STRING_ONE = "1";
    public static final String STR_NULL = "null";

    public static int PLANTYPE_INDEX_SECOND = 2;  //某期计划第2条数据


    public static int GODPLAN_STATUS_CLOSE = 0;  //大神推荐关闭

    /**
     * 系统信息值，缓存后缀
     */
    public static final String SYSTEM_INFO_VALUE_SUFFIX = "_INFO";
    // 签到循坏
    public static final Integer SIGNCYCLE = 7;

    public static final String OPENID = "openid";
    // 订单AES 加密字符串
    public static final String ORDERAES = "PPAYORDER_10000";
    public static final String APP_LOGIN_INFO = "APP_LOGIN_INFO";// APP用户端登录
    public static final String ADMIN_LOGIN_INFO = "ADMIN_LOGIN_INFO";// 机构后台登录
    public static final String APP_LOGIN_AGENT_INFO = "APP_LOGIN_INFO";// APP用户端登录
    public static final String ADMIN_VERCODE = "ADMIN_VERCODE";// 机构后台图形验证码

    // 创建acctoken是，给 用户的accno 加一个前缀 区别是 web 还是 app用户
    public static final String YELLOW_WEB_LOGIN_INFO = "YELLOWWEB";// 聚合站点web登录
    // 用户设置 setAttribute

    public static final String SUCCESS_MSG = "success";
    public static final String FAIL_MSG = "fail";
    public static final String LOGINNUM = "LOGINNUM";
    public static final String LOGINNUMHOU = "LOGINNUMHOU";

    // 官方邀请码
    public static final String GUANFANGRECOMCODE = "guanfang_recomcode";
    /**
     * 直播stream加密key
     */
    public static final String STREAMKEY = "bblive&SRSKEY!2020";

    /**
     * ======================================USER============================================
     */

    // 账号状态 1正常 9禁止登陆
    public static final Integer ACCOUNT_ONE = 1;

    /**
     * 审核状态 1未审核 8审核通过 9审核未通过
     */
    public static final Integer CHECKSTATUS_8 = 8;


    /**
     * ======================================USER============================================
     */
    // 验证码有效期 分钟
    public static final String STATUS_SUCCESS = "0";
    public static final String STATUS_USED = "8";
    public static final String STATUS_FAILE = "9";


    /**
     * 短信是否发送开关 0 不发送  1 发送
     */
    public static final String SMS_REAL_OFF = "0";


    /**
     * 短信验证码文本
     */
    public static final String SMS_SEND_TEXT = " 您好，您的验证码是：";


    /**
     * 中国大陆
     */
    public static final String AREACODE_CHINA_MAINLAND_86 = "86";
    public static final String AREACODE_CHINA_MAINLAND_086 = "086";


    /**
     * 账号状态 1正常 9禁止登陆
     */
    public static final Integer ACCSTATUS_9 = 9;
    public static final Integer ACCSTATUS_1 = 1;

    public static final String LEVEL_ONE = "0";
    public static final String LEVEL_TWO = "1";

    /**
     * ======================================AWS============================================
     */
    /**
     * 头像 前缀key
     */
    public static final String LIVE_PHOTO_AVATAR = "avatar/";

    /**
     * 上传文件的路径
     */
    public static final String LIVE_FILE = "file/";
    /**
     * ======================================AWS============================================
     */


    /**
     * ======================================播币============================================
     */
    ////////////////////////////////////////// 消息提醒/////////////////////////////////////////////////////////////
    // 提醒信息类型：order订单提醒 pay 支付消息，auditvideo视频审核提醒 auditimg图文审核提醒 other通用提醒
    ////////////////////////////////////////// offline强制下线 notalk禁言
    public static final String RMTYPE_AUDITVIDEO = "auditvideo";
    public static final String RMTYPE_AUDITIMG = "auditimg";
    public static final String RMTYPE_COMMENT = "comment";
    public static final String RMTYPE_SYSTEM = "system";
    public static final String RMTYPE_OFFLINE = "offline";
    public static final String RMTYPE_NOTALK = "notalk";

    public static final Integer ISSEE_9 = 9;
    public static final Integer ISTODO_0 = 0;

    /**
     * ======================================播币============================================
     */
    // 充值比例 1元 = 1播币
    public static final Integer CHONGZHIBILIE = 1;

    // 订单类型 1在线支付 2线下支付 3在线提现 4线下提现 5彩票购彩 6彩票兑奖 7棋牌上分 8棋牌下分 9其他收入(发帖/推荐) 10其他支出(打赏) 11代理结算收入 12投注分成 13礼物分成
    public static final Integer ORDERTYPE1 = 1;
    public static final Integer ORDERTYPE2 = 2;
    public static final Integer ORDERTYPE4 = 4;
    public static final Integer ORDERTYPE5 = 5;
    public static final Integer ORDERTYPE9 = 9;
    /**
     * 代理结算收入
     */
    public static final Integer ORDERTYPE11 = 11;
    //代充人充值
    public static final Integer ORDERTYPE14 = 14;
    //代充人给会员充值
    public static final Integer ORDERTYPE15 = 15;
    public static final Integer ORDERTYPE16 = 16;

    /**
     * 订单类型: 16-代充存入
     */
    public static final Integer ORDER_TYPE_ART_IN = 16;
    /**
     * 订单类型: 17-代充提出
     */
    public static final Integer ORDER_TYPE_ART_OUT = 17;
    // 订单状态 ord01新订单 ord04待付款 ord05提现申请 ord06提现取消 ord07提现处理中 ord08已付款
    // ord09用户取消 ord10已评价 ord11已退款 ord12已提现 ord13充值失败 ord14t提现失败 ord99已过期  ord98 有注单数据
    public static final String ORDER_ORD04 = "ord04";
    public static final String ORDER_ORD05 = "ord05";
    public static final String ORDER_ORD06 = "ord06";
    public static final String ORDER_ORD07 = "ord07";
    public static final String ORDER_ORD08 = "ord08";
    public static final String ORDER_ORD081 = "ord081";
    public static final String ORDER_ORD09 = "ord09";
    public static final String ORDER_ORD12 = "ord12";
    public static final String ORDER_ORD13 = "ord13";
    public static final String ORDER_ORD15 = "ord15";
    public static final String ORDER_ORD14 = "ord14";
    public static final String ORDER_ORD98 = "ord98";
    public static final String ORDER_ORD30 = "ord30";
    public static final String ORDER_ORD99 = "ord99";
    // 结算状态 acc04待结算（不可提现部分） acc08已结算（可提现部分） acc16已提现 acc99已取消（不可结算）
    public static final String ORDER_ACC04 = "acc04";
    // 申请状态 1提交申请 2提现处理中 3已经失败 4已打款 8已到账 9已取消
    public static final Integer APYCSTATUS1 = 1;
    public static final Integer APYCSTATUS2 = 2;
    public static final Integer APYCSTATUS3 = 3;
    public static final Integer APYCSTATUS4 = 4;
    public static final Integer APYCSTATUS9 = 9;

    // 时时彩
    public static final String LOTTERY_SSC = "ssc";
    public static final String KS = "ks";

    // 超级管理员 角色ID
    public static final Long SUPERADMINSYSROLEID = 1L;
    /**
     * 系统参数启用状态0启用9未启用
     */
    public static final Integer STATUS_9 = 9;
    public static final Integer STATUS_0 = 0;

    // 直播间主播彩票分成比例
    public static final String BET_RATIO = "bet_ratio";
    // 密码隐藏
    public static final String PASSWORD_XING = "************";
    // 结算状态 acc04待结算（未打码） acc08已结算（已打码） acc99已取消（不可结算）
    public static final String ORDER_ACC08 = "acc08";
    public static final String ORDER_ACC99 = "acc99";


    public static final String NIUNIU = "niuniu";
    public static final String GONGZHUFU = "gongzhufu";
    public static final String NEWBIAOBAI = "newbiaobai";

    // 支付状态0支付成功/退款成功 1支付中/退款中 9支付失败/退款失败
    public static final Integer PAYSTATUS0 = 0;
    public static final Integer PAYSTATUS1 = 1;


    // 一分快三
    public static final Integer CAI_WEISHU = 4;
    public static final Double CAI_YI_JIANGE = 1.0;
    public static final String RMTYPE_SYSTEMNOTICE = "systemnotice";
    public static final Double CAI_SAN_JIANGE = 3.0;
    // 跟头前缀
    public static final String GENTOU = "gentou";
    public static final Double SIGNCYCLE_GOLDNUM_D = 0.25d;
    public static final Double SIGNCYCLE_GOLDNUM_MAXDAY_D = 2.0d;
    public static final String PLAY_06 = "06";


    /**
     * 投注排序
     */
    public static final String BET = "bet";

    public static final String ES_REPORT = "ES";


    /**
     * 是
     */
    public static final Integer YES = 0;

    /**
     * 否
     */
    public static final Integer NO = 9;

    /**
     * 启用
     */
    public static final Integer OPEN = 0;

    /**
     * 不启用
     */
    public static final Integer CLOSE = 9;

    //下分成功
    public static final String OUT_SUCCESS = "outsuccess";
    //下分失败
    public static final String OUT_FAIL = "outfail";

    /**
     * 代理结算打款
     */
    public static final String AGENT_DES = "代理结算打款";
    /**
     * 注册标识
     */
    public static final String REGIST = "regist";
    /**
     * 推荐
     */
    public static final String RECOMMEND = "recommend";

    /**
     * 动态文件格式
     */
    public static final String SVGA = "svga";

    public static String MESSAGE_NOTICE = "MESSAGE_NOTICE";


    /**
     * 第三方返水表参数 1开元  2 AG 3 电竞 4 AE
     */
    public static Integer THIRD_TYPE_TWO = 2;


    /**
     * 返水控制表参数 1 购彩 2 开元 3 AG 4 电竞 5 AE
     */

    //订单状态(0-处理中,1-成功,2-超过上限,3-余额不足,4-在地图内,无法执行操作,5-在游戏中,无法执行操作
    public static Integer AE_ORDER_ZERO = 0;
    public static Integer AE_ORDER_FIVE = 5;

    public static String D = "大";
    public static String X = "小";
    public static String DOUBLE = "双";


    /**
     * 非活跃状态(正常下播)
     */
    public static final String INACTIVE = "inactive";

    /**
     * 腾讯云直播地址
     */
    public static final String HTTP_PROFILE_END_POINT = "live.tencentcloudapi.com";


    /**
     * 帐户类型
     */
    public static final String ALIPAY = "支付宝";
    public static final String WECHAT = "微信";
    public static final String UNIONPAY = "银联";

    public static final int ACCOUNTTYPE_ALIPAY = 1;
    public static final int ACCOUNTTYPE_WECHAT = 2;
    public static final int ACCOUNTTYPE_UNIONPAY = 3;

    //首充
    public static final int PAY_SET_ONE = 1;
    //每次
    public static final int PAY_SET_TWO = 2;


    public static final String LIVE_BAS_ANCHOR_PLATFORM = "LIVE_BAS_ANCHOR_PLATFORM_";


    public static final int UNIQUE_CODE_GEN_SIZE = 200 * 10000;
    public static final int UNIQUE_CODE_LOAD_SIZE = 500;
    public static final int UNIQUE_CODE_BATCH_SIZE = 5000;


    public static final String PLANT_BOUNS = "plant_bouns";
    public static final String PLANT_OPERATE = "plant_operate";

    // 日志等级： 正常normal 系统错误error
    public static final String ERROR_LEVEL_ERROR = "error";


    public static final int EXP_FUND_STATISTICS_DAYS = 59;


    public static final String HEAD_IMG_CODE = "HEADIMG";


    public static final String COMPANY = "公司入款";
    public static final String PAYFOR = "代充充值";
    public static final String THIRDONLINE = "线上入款";
    public static final String ARTIFICIAL = "人工入款";
    public static final String CACULATE = "小计";


    public static final String HOME_REGISTERED = "registered";
    public static final String HOME_RECHARGEALLDATA = "rechargeAllData";
    public static final String HOME_PAYMENTALLDATA = "paymentAllData";
    public static final String HOME_ACTIVITYALLDATA = "activityAllData";
    public static final String HOME_CONSUMPTIONALLDATA = "consumptionAllData";
    public static final String HOME_LOTTERYALLDATA = "lotteryAllData";

    /**
     * 数据精度：0位小数
     */
    public static final int NUM_PRECISION_0 = 0;
    /**
     * 数据精度：2位小数
     */
    public static final int NUM_PRECISION_2 = 2;
    /**
     * 数据精度：3位小数
     */
    public static final int NUM_PRECISION_3 = 3;

    public static final Integer WORD_NINE = 9;

    public static final char REPLACE_CHART = '*';

    public static final String TRIAL_MONEY = "1000";

    public static final String THREE_PAY_CODE_WANTONG = "wantong";


    //http签名访问默认最大错误次数
    public static Integer HTTPSIGN_ERRCOUNT_DEFAULT = 10;
}
