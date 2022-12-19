package com.likes.common.constant;

/**
 * @ClassName: AnchorRedisKeys
 * @Description: REDIS 主播端所有Key 集中类
 * @author: HANS
 * @date: 2019年11月1日 下午10:46:14
 */
public class AnchorRedisKeys {

    /**
     * 主播 ACCNO 与 MEMID 的映射关系
     */
    public static final String ANCHOR_ACCNO_MAP_MEMID = "ANCHOR_ACCNO_MAP_MEMID_";

    /**
     * 主播 UNIQUE_ID 与 MEMID 的映射关系
     */
    public static final String ANCHOR_UNIQUE_ID_MAP_MEMID = "ANCHOR_UNIQUE_ID_MAP_MEMID_";

    /**
     * 主播key  key后拼接id
     */
    public static final String ANCHOR_KEY = "ANCHOR_KEY_";

    /**
     * 系统用户缓存前缀
     */
    public static final String ANCHOR_SYSTEM_USER = "ANCHOR_SYSTEM_USER_";

    /**
     * 家族提现管理
     */
    public static final String ANCHOR_FAMILY_WITHDRAWAL_MANAGEMENT = "ANCHOR_FAMILY_WITHDRAWAL_MANAGEMENT_";

    /**
     * app收益报表列表
     */
    public static final String ANCHOR_FAMILY_WITHDRAWAL_MANAGEMENT_INCOME = "ANCHOR_FAMILY_WITHDRAWAL_MANAGEMENT_INCOME_";

    /**
     * app收益报表主播收入明细
     */
    public static final String ANCHOR_FAMILY_WITHDRAWAL_MANAGEMENT_INCOMEDET = "ANCHOR_FAMILY_WITHDRAWAL_MANAGEMENT_INCOMEDET_";

    /**
     * 家族成员key
     */
    public static final String ANCHOR_MEMFAMILYMEM = "ANCHOR_MEMFAMILYMEM_";

    /**
     * 家族长key
     */
    public static final String ANCHOR_FAMILY = "ANCHOR_FAMILY_";

    /**
     * 主播 session key
     */
    public static final String ANCHOR_SESSION_KEY = "ANCHOR_SESSION_KEY_";

    /**
     * 申请主播信息前缀
     */
    public static final String ANCHOR_CERTIFICATION = "ANCHOR_CERTIFICATION_";

    /**
     * 用户关注数key
     */
    public static final String ANCHOR_ATTENTION = "ANCHOR_ATTENTION_";

    /**
     * 用户申请主播信息前缀
     */
    public static final String LIVE_APP_USER_CERTIFICATION = "LIVE_APP_USER_CERTIFICATION_";

    public static final String LIVE_BAS_ANCHOR_PLATFORM_CONFIG_KEY = "ANCHOR_LIVE_BAS_ANCHOR_PLATFORM_CONFIG_KEY_";

    /**
     * 业务参数缓存
     */
    public static final String ANCHOR_BUS_PARAM_CODE = "ANCHOR_BUS_PARAM_CODE_";

    /**
     * 用户的上级关系前缀
     */
    public static final String ANCHOR_RELATIONSHIP = "ANCHOR_RELATIONSHIP_";

    public static final String ANCHOR_APP_USER_RELATIONSHIP = "ANCHOR_APP_USER_RELATIONSHIP_";

    public static final String ANCHOR_SYSTEM_INFO_VALUE_SUFFIX = "ANCHOR_INFO";

    /**
     * 礼物管理缓存
     */
    public static final String BAS_GIFT_LIST = "bas_gift_list_";

    /**
     * 主播房间key: key后拼接房间ID
     */
    public static final String BAS_ANCHOR_ROOM = "bas_anchor_room_";

    /**
     * 主播在线key
     */
    public static final String BAS_ANCHOR_ONLINE = "bas_anchor_online_";

    /**
     * 昨日在线人数缓存
     */
    public static final String BAS_DAY_STATICS_KEY = "bas_day_statics_key_";

    /**
     * 上月在线人数缓存
     */
    public static final String BAS_MONTH_STATICS_KEY = "bas_month_statics_key_";

    /**
     * 去年在线人数缓存
     */
    public static final String BAS_YEAR_STATICS_KEY = "bas_year_statics_key_";

    /**
     * 直播间排行榜缓存
     */
    public static final String ANCHOR_ROOM_BIG_MONEY_TOP_KEY = "ANCHOR_ROOM_BIG_MONEY_TOP_KEY_";
}
