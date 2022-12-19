package com.likes.common.enums;

/**
 * 状态码
 *
 * @author Qiang
 * @date 2017年7月27日 上午11:16:58
 */
public enum StatusCode {

    /**
     * app接口
     * 注意：业务代码返回统一为中文，由aop切换为英文提示
     * code码成功返回 1开头的为中文提示，3开头的为英文提示
     * 错误返回：-1
     */

    OK("1", "成功"),
    FAIL("-1", "失败"),
    OK_EN("3", "success"),
    NOPASS("99", "nopass"),

    LOGIN_ERROR("-10001", "无用户"),
    LOGIN_ERROR_EN("-10001", "No user"),

    SYSTEM_ERROR_TIP("-1", "系统问题，请联系客服！"),

    UNKNOW_ERROR("-1", "未知错误"),
    GOOGLECODE_ERROR("-1", "谷歌验证码错误"),
    GOOGLECODE_NOTVERIFY("-1", "该用户还没有绑定谷歌验证码"),
    SYSTEM_ERROR("-1", "未知错误"),
    UNKNOW_ERROR_EN("-3", "Unknown error"),
    SERVER_ERROR("500", "系统异常，请联系系统管理人员"),
    SERVER_ERROR_EN("500", "Service internal error"),
    SYSTEM_ERROR_EN("-1", "发帖子过快，请稍后再发"),
    SEND_RECOMMEND_EN("-1", "同一用户发帖频繁，请稍后再发"),
    SEND_RECOMMENDCOENT_EN("-1", "评论帖子频繁，请稍后评论"),
    SEND_ADMIRE_EN("-1", "点赞频繁，请稍后点赞"),
    SEND_PHOTORECOENT_EN("-1", "评论频繁，请稍后评论"),
    SEND_FOLLOW_EN("-1", "关注频繁，请稍后关注"),

    NO_DATA("4041", "没有相关数据"),
    NO_DATA_EN("4141", "no data"),

    SIGN_FAILED("1000", "签名不正确"),
    SIGN_FAILED_EN("3000", "signature error"),

    PARAM_ERROR("1001", "参数不正确"),
    PARAM_ERROR_EN("3001", "param error"),

    SENSITIVE_ERROR("1111", "包含敏感词汇"),
    SENSITIVE_ERROR_EN("3111", "param contains sensitive words"),

    MENU_STATUS_ERROR("1002", "上一级菜单状态为禁用，子菜单不能设置可用"),

    PHONE_ERROR("1004", "手机号码有误，请重新输入！"),
    PHONE_ERROR_EN("3004", "The phone number is wrong,Please reenter it!"),

    NAME_ERROR("1005", "名称有误，请重新输入！"),
    NAME_ERROR_EN("3005", "The name is wrong,Please reenter it!"),

    ACCOUNT_ERROR("1006", "帐号有误，请重新输入！"),
    ACCOUNT_ERROR_EN("3006", "Account number is wrong,Please reenter it!"),

    ACCOUNT_REPEAT("1007", "该帐号已存在！"),
    ACCOUNT_REPEAT_EN("3007", "Account has already existed!"),

    ACCOUNT_FREEZE("1111", "账号被冻结！请联系管理员"),
    ACCOUNT_FREEZE_EN("11111111", "Account frozen"),

    PASSWORD_ERROR("1008", "密码有误，请重新输入！"),
    PASSWORD_ERROR_NEED_CAPTCHA("1108", "密码有误，请重新输入！"),
    PASSWORD_ERROR_EN("3008", "The password is wrong,Please reenter it!"),

    ROLE_EMPTY("1009", "该角色不存在！"),

    INVALID_TOKEN("1010", "请先登录！"),
    INVALID_TOKEN_EN("3010", "TOKEN Checkout failure"),

    OVERDUE_TOKEN("1011", "登录已过期，请重新登录！"),
    OVERDUE_TOKEN_EN("3011", "TOKEN expiration,Please log in again"),

    EMAIL_FORM_ERROR("1012", "Email格式不正确！"),
    EMAIL_FORM_ERROR_EN("3012", "Incorrect mailbox format!"),

    PHONE_FORM_ERROR("1013", "手机号码格式不正确！"),
    PHONE_FORM_ERROR_EN("3013", "The format of the phone number is not correct!"),

    CHECK_IDENTIFYING_CODE_ERROR("1014", "验证码已过期！"),
    CHECK_IDENTIFYING_CODE_ERROR_EN("3014", "Verification code verification failure!"),

    ACCOUNT_EMPTY("1015", "账号或密码错误！"),
    ACCOUNT_EMPTY_EN("3015", "Account number does not exist!"),

    FAILED("1016", "失败"),
    FAILED_EN("3016", "failed"),

    UPDATA_PASSWORD_SUCCESS("1017", "修改密码成功,请重新登录！"),
    UPDATA_PASSWORD_SUCCESS_EN("3017", "Revise the password successfully!"),

    AREAMARK_ERROR("1018", "AreaMark is not number!"),

    PASSWORD_FORM_ERROR("1019", "密码格式不正确！"),
    PASSWORD_FORM_ERROR_EN("3019", "The format of the password is not correct!"),

    CAPTCHA_EXCEED_MAXNUM_DAY("1020", "验证码发送超过了当天允许发送的最大次数!"),

    CAPTCHA_SEND_REPEAT("1021", "请勿频繁发送验证码!"),

    CAPTCHA_SEND_ERROR("1022", "验证码发送失败!"),

    BANNER_SOLD_OUT("1023", "亲，该活动已下架！"),

    NO_UPLOAD_FILE("1024", "请选择上传文件！"),
    NO_UPLOAD_FILE_EN("3024", "Please choose to upload a file!"),

    UPLOAD_IMG_FAILURE("1025", "上传失败，图片格式不正确，目前支持的图片格式为[jpg,png,gif,tif,bmp]"),
    UPLOAD_IMG_FAILURE_EN("3025", "Upload failure!"),

    PHONE_REPEAT("1026", "该手机号已存在！"),
    PHONE_REPEAT_EN("3026", "Phone has already existed!"),

    UPDATA_PAY_PASSWORD_SUCCESS("1027", "设置支付密码成功！"),
    UPDATA_PAY_PASSWORD_SUCCESS_EN("3027", "Revise the pay password successfully!"),

    PROMOTION_CODE_ERROR("1028", "邀请码错误！"),
    PROMOTION_CODE_ERROR_EN("3028", "Promotion code error!"),

    ACCOUNT_NICKNAME_REPEAT("1029", "该昵称已存在，请重新输入!"),

    ACCOUNT_NICKNAME_EMPTY("1029", "请设置昵称！"),

    ACCOUNT_NICKNAME_CHECK("1030", "nickname is invalid!"),

    RECOMMEND_LOCKED_OUT("2201", "亲，该帖子已被锁"),

    RECOMMEND_DELETE_OUT("2202", "该帖子已被删除"),

    TITLE_DONT_NULL("1031", "标题不能为空"),

    CONTENT_DONT_NULL("1032", "内容不能为空"),

    USER_DONT_POWER("1033", "该用户没有心水推荐权限"),

    COMMENTS_DONT_NULL("1034", "评论内容不能为空"),

    RECOMMEND_LOCKED_EDIT("1035", "锁贴已经不能编辑"),

    ADMIRE_IS_MANEY("1035", "点赞次数超多"),

    PAY_SERVER_ERROR("1036", "充值失败"),

    HYSTRIX_BAD_REQUEST("1037", "请求有误"),
    ACCESS_DENIED("1038", "访问受限"),

    ACTIVITY_END("1039", "此活动未开始或已截止！"),

    RECOMMEND_DONTFIND("1041", "帖子无效！"),

    RECOMMEND_LOCKED("1042", "该贴已锁！"),

    RECOMMEND_NO_POWER("1043", "该用户没有心水推荐权限！"),

    RED_PACK_END("1040", "红包截止！"),

    SEND_RECOMMEND_FAIL("1044", "发帖失败，请联系客服！"),
    LIMIT_SEND_FAIL("1045", "发帖受限，请联系客服！"),

    NEED_TO_SET_ACCOUNT("1046", "请设置账号！"),
    NEED_TO_BOUND_MOBILE("1047", "请绑定手机号！"),

    DISABLED_ALL_FUNCTIONS("1048", "暂停服务，请联系客服后再试！"),
    DISABLED_FUNCTION("1049", "该功能暂停服务，请联系客服后再试！"),

    NON_AUTHORIZE("1050", "需成为充值账户！"),
    IP_RESTRICT("1051", "用户操作受限，请联系客服！"),
    RESTART_SERVER("1052", "服务启动中，请稍后再试！"),

    UPDATE_RECOMMEND_FAIL("1054", "修改帖子无效，请联系客服！"),

    PARAM_NULL("1055", "账号为空，请联系客服！"),
    OPERATE_REPEAT("1056", "Operation repetition!"),
    NON_LOGIN_REGISTER("1057", "请登录或注册！"),
    IS_VIRTUAL_MOBILE("1058", "当前号段违规禁止使用！"),
    TIMEOUT_NETWORK("1059", "Network timeout!"),

    OPERATION_FAILED("1060", "操作失败"),
    NEED_TO_UPGRADE_VERSION("2000", "当前版本过低，请升级到最新版本"),

    /** 聊天室禁用状态 */
    CHAT_ROOT_BANNED("4000", "违规已禁言！"),
    CHAT_ROOM_CONTENT_BANNED("4001", "违规已禁言！"),
    CHAT_ROOM_USER_BANNED("4002", "违规已禁言！"),
    SYSTEM_MAINTENANCE_CODE("9999", "系统维护状态"),
    LIVE_ERROR_100("100", ""),
    LIVE_ERROR_101("101", ""),
    LIVE_ERROR_102("102", ""),
    LIVE_ERROR_103("103", ""),
    LIVE_ERROR_104("104", ""),
    LIVE_ERROR_105("105", ""),
    LIVE_ERROR_106("106", ""),
    LIVE_ERROR_107("107", ""),
    LIVE_ERROR_108("108", ""),
    LIVE_ERROR_109("109", ""),
    LIVE_ERROR_110("110", ""),

    LIVE_ERROR_111("111", ""),
    LIVE_ERROR_112("112", ""),
    LIVE_ERROR_113("113", ""),
    LIVE_ERROR_114("114", ""),
    LIVE_ERROR_115("115", ""),
    LIVE_ERROR_116("116", ""),
    LIVE_ERROR_117("117", ""),
    LIVE_ERROR_118("118", ""),
    LIVE_ERROR_119("119", ""),

    LIVE_ERROR_120("120", ""),
    LIVE_ERROR_121("121", ""),
    LIVE_ERROR_122("122", ""),
    LIVE_ERROR_123("123", ""),
    LIVE_ERROR_124("124", ""),
    LIVE_ERROR_125("125", ""),
    LIVE_ERROR_126("126", ""),
    LIVE_ERROR_127("127", ""),
    LIVE_ERROR_128("128", ""),
    LIVE_ERROR_129("129", ""),
    LIVE_ERROR_130("130", ""),
    LIVE_ERROR_131("131", ""),
    LIVE_ERROR_132("132", ""),
    LIVE_ERROR_133("133", ""),
    LIVE_ERROR_134("134", ""),
    LIVE_ERROR_135("135", ""),
    LIVE_ERROR_136("136", ""),
    LIVE_ERROR_137("137", ""),
    LIVE_ERROR_138("138", ""),
    LIVE_ERROR_139("139", ""),
    LIVE_ERROR_140("140", ""),
    LIVE_ERROR_141("141", ""),
    LIVE_ERROR_142("142", ""),
    LIVE_ERROR_143("143", ""),
    LIVE_ERROR_144("144", ""),
    LIVE_ERROR_145("145", ""),
    LIVE_ERROR_146("146", ""),
    LIVE_ERROR_147("147", ""),
    LIVE_ERROR_148("148", ""),
    LIVE_ERROR_149("149", ""),
    LIVE_ERROR_150("150", ""),
    LIVE_ERROR_151("151", ""),
    LIVE_ERROR_152("152", ""),
    LIVE_ERROR_153("153", ""),
    LIVE_ERROR_154("154", ""),
    LIVE_ERROR_155("155", ""),
    LIVE_ERROR_156("156", ""),
    LIVE_ERROR_157("157", ""),
    LIVE_ERROR_158("158", ""),
    LIVE_ERROR_159("159", ""),
    LIVE_ERROR_160("160", ""),
    LIVE_ERROR_161("161", ""),
    LIVE_ERROR_162("162", ""),
    LIVE_ERROR_163("163", ""),
    LIVE_ERROR_164("164", ""),
    LIVE_ERROR_165("165", ""),
    LIVE_ERROR_166("166", ""),
    LIVE_ERROR_167("167", ""),
    LIVE_ERROR_168("168", ""),
    LIVE_ERROR_169("169", ""),
    LIVE_ERROR_170("170", ""),
    LIVE_ERROR_171("171", ""),
    LIVE_ERROR_172("172", ""),
    LIVE_ERROR_173("173", ""),

    LIVE_ERROR_201("201", ""),
    LIVE_ERROR_202("202", ""),
    LIVE_ERROR_203("203", ""),
    LIVE_ERROR_204("204", ""),
    LIVE_ERROR_205("205", ""),
    LIVE_ERROR_206("206", ""),
    LIVE_ERROR_207("207", ""),
    LIVE_ERROR_208("208", ""),
    LIVE_ERROR_209("209", ""),
    LIVE_ERROR_210("210", ""),

    LIVE_ERROR_401("401", ""),
    LIVE_ERROR_402("402", ""),
    LIVE_ERROR_403("403", ""),
    LIVE_ERROR_404("404", ""),
    LIVE_ERROR_405("405", ""),
    LIVE_ERROR_406("406", ""),
    LIVE_ERROR_407("407", ""),
    LIVE_ERROR_408("408", ""),
    LIVE_ERROR_409("409", ""),
    LIVE_ERROR_410("410", ""),
    LIVE_ERROR_411("411", ""),
    LIVE_ERROR_412("412", ""),
    LIVE_ERROR_413("413", ""),
    LIVE_ERROR_414("414", ""),
    LIVE_ERROR_415("415", ""),
    LIVE_ERROR_429("429", "访问过快"),

    LIVE_ERROR_501("501", ""),
    LIVE_ERROR_502("502", ""),
    LIVE_ERROR_503("503", ""),
    LIVE_ERROR_504("504", ""),
    LIVE_ERROR_505("505", ""),
    LIVE_ERROR_506("506", ""),
    LIVE_ERROR_507("507", ""),
    LIVE_ERROR_508("508", ""),
    LIVE_ERROR_509("509", ""),

    LIVE_ERROR_601("601", ""),
    LIVE_ERROR_602("602", ""),
    LIVE_ERROR_603("603", ""),
    LIVE_ERROR_604("604", ""),
    LIVE_ERROR_605("605", ""),

    LIVE_ERROR_995("995", ""),
    LIVE_ERROR_996("996", ""),
    LIVE_ERROR_997("997", ""),
    LIVE_ERROR_998("998", ""),
    LIVE_ERROR_999("999", ""),
    LIVE_ERROR_1000("1000", ""),
    LIVE_ERROR_1031("1031", ""),
    LIVE_ERROR_1032("1032", ""),
    LIVE_ERROR_1100("1100", ""),
    LIVE_ERROR_1101("1101", ""),
    LIVE_ERROR_1102("1102", ""),
    LIVE_ERROR_1103("1103", ""),
    LIVE_ERROR_1104("1104", ""),
    LIVE_ERROR_1105("1105", ""),
    LIVE_ERROR_1106("1106", ""),
    LIVE_ERROR_1107("1107", ""),
    LIVE_ERROR_11071("11071", ""),
    LIVE_ERROR_11072("11072", ""),
    LIVE_ERROR_1108("1108", ""),
    LIVE_ERROR_11081("11081", ""),
    LIVE_ERROR_11082("11082", ""),
    LIVE_ERROR_11083("11083", ""),
    LIVE_ERROR_11084("11084", ""),
    LIVE_ERROR_11085("11085", ""),
    LIVE_ERROR_11086("11086", ""),
    LIVE_ERROR_1109("1109", ""),
    LIVE_ERROR_1110("1110", ""),

    LIVE_ERROR_1111("1111", ""),
    LIVE_ERROR_1112("1112", ""),
    LIVE_ERROR_1113("1113", ""),
    LIVE_ERROR_1114("1114", ""),
    LIVE_ERROR_1115("1115", ""),
    LIVE_ERROR_1116("1116", ""),
    LiVE_ERROR_1117("1117",""),
    LIVE_ERROR_1118("1118", ""),
    LIVE_ERROR_1119("1119", ""),

    LIVE_ERROR_1120("1120", ""),
    LIVE_ERROR_1121("1121", ""),
    LIVE_ERROR_1122("1122", ""),
    LIVE_ERROR_1123("1123", ""),

    LIVE_ERROR_1130("1130", ""),
    LIVE_ERROR_1148("1148", ""),
    LIVE_ERROR_1149("1149", ""),
    LIVE_ERROR_1150("1150", ""),
    LIVE_ERROR_1151("1151", ""),
    LIVE_ERROR_1152("1152", ""),
    LIVE_ERROR_1153("1153", ""),
    LIVE_ERROR_1154("1154", ""),
    LIVE_ERROR_1155("1155", ""),
    LIVE_ERROR_1156("1156", ""),
    LIVE_ERROR_1157("1157", ""),
    LIVE_ERROR_1158("1158", ""),
    LIVE_ERROR_1159("1159", ""),

    LIVE_ERROR_1160("1160", ""),
    LIVE_ERROR_1161("1161", ""),

    LIVE_ERROR_1190("1190", ""),
    LIVE_ERROR_1199("1199", ""),

    LIVE_ERROR_1200("1200", ""),
    LIVE_ERROR_1201("1201", ""),
    LIVE_ERROR_1202("1202", ""),
    LIVE_ERROR_1203("1203", ""),
    LIVE_ERROR_1204("1204", ""),
    LIVE_ERROR_1205("1205", ""),
    LIVE_ERROR_1206("1206", ""),
    LIVE_ERROR_1207("1207", ""),
    LIVE_ERROR_1208("1208", ""),
    LIVE_ERROR_1209("1209", ""),
    LIVE_ERROR_1210("1210", ""),

    LIVE_ERROR_1211("1211", ""),
    LIVE_ERROR_1212("1212", ""),
    LIVE_ERROR_1213("1213", ""),
    LIVE_ERROR_1214("1214", ""),
    LIVE_ERROR_1215("1215", ""),
    LIVE_ERROR_1216("1216", ""),
    LIVE_ERROR_1217("1217", ""),
    LIVE_ERROR_1218("1218", ""),
    LIVE_ERROR_1219("1219", ""),
    LIVE_ERROR_1220("1220", ""),

    LIVE_ERROR_1300("1300", ""),
    LIVE_ERROR_1301("1301", ""),
    LIVE_ERROR_1302("1302", ""),
    LIVE_ERROR_1303("1303", ""),

    LIVE_ERROR_1404("1404", ""),

    LIVE_ERROR_1901("1901", ""),
    LIVE_ERROR_1902("1902", ""),
    LIVE_ERROR_1903("1903", ""),
    LIVE_ERROR_1904("1904", ""),
    LIVE_ERROR_1905("1905", ""),

    LIVE_ERROR_2000("2000", ""),
    LIVE_ERROR_2001("2001", ""),
    LIVE_ERROR_2002("2002", ""),
    LIVE_ERROR_2003("2003", ""),
    LIVE_ERROR_2004("2004", ""),

    LIVE_ERROR_2100("2100", ""),
    LIVE_ERROR_2101("2101", ""),
    LIVE_ERROR_2102("2102", ""),
    LIVE_ERROR_2103("2103", ""),
    LIVE_ERROR_2104("2104", ""),
    LIVE_ERROR_2105("2105", ""),
    LIVE_ERROR_2106("2106", ""),
    LIVE_ERROR_2107("2107", ""),
    LIVE_ERROR_2108("2108", ""),
    LIVE_ERROR_2109("2109", ""),
    LIVE_ERROR_2110("2110", ""),

    LIVE_ERROR_2201("2201", ""),
    LIVE_ERROR_2202("2202", ""),
    LIVE_ERROR_2203("2203", ""),
    LIVE_ERROR_2204("2204", ""),
    LIVE_ERROR_2205("2205", ""),
    LIVE_ERROR_2206("2206", ""),

    LIVE_ERROR_1131("1131", ""),

    LIVE_ERROR_9971("9971", ""),

    //    LIVE_ERROR_1000("1000", ""),//直播的1000范围的code换为10000
//    LIVE_ERROR_1001("1001", ""),
//    LIVE_ERROR_1002("1002", ""),
//    LIVE_ERROR_1003("1003", ""),

    LIVE_ERROR_10000("10000", ""),
    LIVE_ERROR_10001("10001", ""),
    LIVE_ERROR_10002("10002", ""),
    LIVE_ERROR_10003("10003", ""),
    LIVE_ERROR_10004("10004", ""),
    LIVE_ERROR_10005("10005", ""),
    LIVE_ERROR_10006("10006", ""),
    LIVE_ERROR_10007("10007", ""),
    LIVE_ERROR_10008("10008", ""),
    LIVE_ERROR_10009("10009", ""),
    LIVE_ERROR_10010("10010", ""),
    LIVE_ERROR_10011("10011", ""),
    LIVE_ERROR_10012("10012", ""),
    LIVE_ERROR_10013("10013", ""),
    LIVE_ERROR_10014("10014", ""),
    LIVE_ERROR_10015("10015", ""),

    LIVE_ERROR_11000("11000", ""),
    LIVE_ERROR_11001("11001", ""),
    LIVE_ERROR_11002("11002", ""),
    LIVE_ERROR_11003("11003", ""),
    LIVE_ERROR_11004("11004", ""),
    LIVE_ERROR_11005("11005", ""),
    LIVE_ERROR_11006("11006", ""),
    LIVE_ERROR_110061("110061", ""),
    LIVE_ERROR_11007("11007", ""),
    LIVE_ERROR_11008("11008", ""),
    LIVE_ERROR_11009("11009",""),



    LIVE_ERROR_11010("11010", ""),
    LIVE_ERROR_11011("11011", ""),
    LIVE_ERROR_11111("11111", ""),
    LIVE_ERROR_10099("10099", ""),

    LIVE_ERROR_12000("12000", ""),
    LIVE_ERROR_12001("12001", ""),
    LIVE_ERROR_12002("12002", ""),
    LIVE_ERROR_12012("12012", ""),
    LIVE_ERROR_12021("12021", ""),

    LIVE_ERROR_21001("21001", ""),
    LIVE_ERROR_21002("21002", ""),
    LIVE_ERROR_21003("21003", ""),
    LIVE_ERROR_21004("21004", ""),
    LIVE_ERROR_21005("21005", ""),
    LIVE_ERROR_21006("21006", ""),
    LIVE_ERROR_21007("21007", ""),
    LIVE_ERROR_21008("21008", ""),
    LIVE_ERROR_21009("21009", ""),

    LIVE_ERROR_21011("21011", ""),
    LIVE_ERROR_21016("21016", ""),
    LIVE_ERROR_21017("21017", ""),
    LIVE_ERROR_21054("21054", ""),
    LIVE_ERROR_21099("21099", ""),

    LIVE_ERROR_110010("110010", ""),
    LIVE_ERROR_110011("110011", ""),
    LIVE_ERROR_110012("110012", ""),

    LIVE_ERROR_110013("110013", ""),
    LIVE_ERROR_111111("111111", "系统繁忙，请稍后再试"),
    LIVE_ERROR_111112("111112", "账户不存在"),

    // 房管
    LIVE_ERROR_111113("111113", "主播未登录"),
    LIVE_ERROR_111114("111114", "被任命房管的会员标识号为空"),
    LIVE_ERROR_111115("111115", "被任命房管的会员账户不存在"),
    LIVE_ERROR_111116("111116", "主播房间信息不存在"),
    LIVE_ERROR_111117("111117", "添加房管信息失败"),
    LIVE_ERROR_111118("111118", "要取消房管的会员标识号为空"),
    LIVE_ERROR_111119("111119", "要取消房管的会员账户不存在"),
    LIVE_ERROR_111120("111120", "房管信息不存在"),
    LIVE_ERROR_111121("111121", "取消房管失败"),
    LIVE_ERROR_111122("111122", "主播未开播"),
    LIVE_ERROR_111123("111123", "该会员已被任命为房管"),
    LIVE_ERROR_111124("111124", "最多任命3个房管"),
    LIVE_ERROR_111125("111125", "被任命房管的会员已离开房间"),
    LIVE_ERROR_111126("111126", "您未登录"),
    LIVE_ERROR_111127("111127", "要禁言的会员标识号为空"),
    LIVE_ERROR_111128("111128", "要禁言的会员账户不存在"),
    LIVE_ERROR_111129("111129", "要禁言的会员已离开房间"),
    LIVE_ERROR_111130("111130", "要禁入的会员标识号为空"),
    LIVE_ERROR_111131("111131", "要禁入的会员账户不存在"),
    LIVE_ERROR_111132("111132", "要禁入的会员已离开房间"),

    // APP版本管理
    LIVE_ERROR_111200("111200", "没有APP版本记录"),
    LIVE_ERROR_111201("111201", "美颜版本为空"),
    LIVE_ERROR_111202("111202", "清除所有的isNew状态失败"),
    LIVE_ERROR_111203("111203", "添加APP版本记录失败"),
    LIVE_ERROR_111204("111204", "更新APP版本失败"),
    LIVE_ERROR_111205("111205", "没有最新的版本"),


    LIVE_ERROR_130001("130001", "已超出单笔最高限额"),
    LIVE_ERROR_130002("130002", "未满足单笔最低限额"),
    LIVE_ERROR_130003("130003", "请输入图形验证码"),
    LIVE_ERROR_130004("130004", "图像验证码错误"),
    LIVE_ERROR_130005("130005", "无效验证码"),
    LIVE_ERROR_130006("130006", "验证码错误"),
    LIVE_ERROR_130007("130007", "您的信用积分小于50，已呗限制提现"),

    UPLOAD_FILE_ERROR_130008("130008", "文件上传地址找不到！"),
    UPLOAD_IMG_FAILURE_130009("130009", "上传失败，图片格式不正确，目前支持的图片格式为[jpg,png,jpeg,JPG,PNG,JPEG]"),
    UPLOAD_VOIDE_FAILURE_130010("130010", "上传失败，视频格式不正确，目前支持的视频格式为[mp4,avi]"),
    UPLOAD_VOIDE_FAILURE_130011("130011", "目前只支持！[文件类型-0：图片、1：视频、2：其他文件]"),
    LIVE_ERROR_130012("130012", "目前只支持！[文件类型-0：图片、1：视频、2：其他文件]"),
    ACROSS_THE_LEVEL_130013("130013", "不能向上越等级购买会员！"),
    MEMBER_EXPIRE_ERROR_130014("130014", "会员已到期，请重新购买！")
    ;



    private final String code;
    private final String value;

    StatusCode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static StatusCode getStatusCode(String code) {
        if (null == code || "".equals(code.trim())) {
            return null;
        }
        for (StatusCode statusCode : values()) {
            if (code.equals(statusCode.getCode())) {
                return statusCode;
            }
        }
        return null;
    }

    public Integer toIntValue() {
        try {
            return Integer.parseInt(this.getCode());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
