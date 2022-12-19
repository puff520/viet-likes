package com.likes.common.enums;

public enum KaiYuanErrorCode {

    // 0 成功
    SUCCESS("0", "成功"),
    // 1 TOKEN 丢失（重新调用登录接口获取）
    ERROR_NOTOKEN("1", "TOKEN丢失(重新调用登录接口获取)"),
    // 2 渠道不存在（请检查渠道 ID 是否正确）
    ERROR_NOQUDAO("2", "渠道不存在(请检查渠道ID是否正确)"),
    // 3 验证时间超时（请检查 timestamp 是否正确）
    ERROR_YANZHENGTIMEOUT("3", "验证时间超时(请检查timestamp是否正确)"),
    // 4 验证错误
    ERROR_YANZHENGCUOWU("4", "验证错误"),
    // 5 渠道白名单错误（请联系客服添加服务器白名单）
    ERROR_NOQUDAOBAIMINGDAN("5", "渠道白名单错误(请联系客服添加服务器白名单)"),
    //6  验证字段丢失（请检查参数完整性）
    ERROR_YANZHENGZIDUANDIUSHI("6", "验证字段丢失(请检查参数完整性)"),
    //8  不存在的请求（请检查子操作类型是否正确）
    ERROR_NOCUNZAIQINGQIU("8", "不存在的请求(请检查子操作类型是否正确)"),
    //15 渠道验证错误（1.MD5key 值是否正确；2.生成 key 值中的 timestamp 与参数中的是否一致；3. 生成 key 值中的 timestamp 与代理编号以字符串形式拼接）
    ERROR_QUDAOYANZHEGCUOWU("15", "渠道验证错误(1.MD5key 值是否正确；2.生成 key 值中的 timestamp 与参数中的是否壹致；3. 生成 key 值中的 timestamp 与代理编号以字符串形式拼接)"),
    //16  数据不存在（当前没有注单）
    ERROR_SHUJUBUCUNZAI("16", "数据不存在(当前没有注单)"),
    //20  账号禁用
    ERROR_ZHANGHAOJINYONG("20", "账号禁用"),
    //22  AES 解密失败
    ERROR_AESJIEMISHIBAI("22", "AES解密失败"),
    //24  渠道拉取数据超过时间范围
    ERROR_QUDAODATATIMEOUT("24", "渠道拉取数据超过时间范围"),
    //26  订单号不存在
    ERROR_NOORDERNO("26", " 订单号不存在"),
    //27  数据库异常
    ERROR_DATABASEEXCETION("27", " 数据库异常"),
    //28  ip 禁用
    ERROR_IPJINGYONG("28", "ip禁用"),
    //29  订单号与订单规则不符
    ERROR_ORDERNOBUFU("29", "订单号与订单规则不符"),
    //30  获取玩家在线状态失败
    ERROR_USERONLINESTATUS("30", "获取玩家在线状态失败"),
    //31  更新的分数小于或者等于 0
    ERROR_NODAYULING("31", "更新的分数小于或者等于 0"),
    //32  更新玩家信息失败
    ERROR_UPDATEUSERINFO("32", "更新玩家信息失败"),
    //33  更新玩家金币失败
    ERROR_UPDATEUSERGOLDNUM("33", "更新玩家金币失败"),
    //34  订单重复
    ERROR_ORDERREPEATE("34", "订单重复"),
    //35  获取玩家信息失败（请调用登录接口创建账号）
    ERROR_GETUSERINGO("35", "获取玩家信息失败(请调用登录接口创建账号)"),
    //36  KindID 不存在
    ERROR_NOKINDID("36", "KindID不存在"),
    //37  登录瞬间禁止下分，导致下分失败
    ERROR_NOSHANGFENORXIAFEN("37", "登录瞬间禁止下分，导致下分失败"),
    //38  余额不足导致下分失败
    ERROR_YUEBUZU("38", "余额不足导致下分失败"),
    //39  禁止同一账号登录带分、上分、下分并发请求，后一个请求被拒
    ERROR_JINGZHIDUOQINGQIU("39", "禁止同壹账号登录带分、上分、下分并发请求，后壹个请求被拒"),
    //40  单次上下分数量不能超过一千万
    ERROR_MOREMONEY("40", "单次上下分数量不能超过壹千万"),
    //41  拉取对局汇总统计时间范围有误
    ERROR_FANWEIYOUWEI("41", "拉取对局汇总统计时间范围有误"),
    //42  代理被禁用
    ERROR_JINGZHIAGENT("42", "代理被禁用"),
    //43  拉单过于频繁(两次拉单时间间隔必须大于 5 秒)
    ERROR_DUOCIQINGQIU("43", "拉单过于频繁(两次拉单时间间隔必须大于 5 秒)"),
    //44  订单正在处理中
    ERROR_ORDERING("44", "订单正在处理中"),
    //45  参数错误
    ERROR_PARAME("45", "参数错误"),
    //注册会员账号系统异常
    ERROR_REGISTEREXCEPTION("1001", "注册会员账号系统异常"),
    //代理商金额不足
    ERROR_AGENTSHANGMONEYBUZU("1002", "代理商金额不足"),


    /** 可以传入参数 */
    LOGINERROR("10000000", "还剩%s次锁定用户");

    private String errorCode;
    private String errorMessage;

    KaiYuanErrorCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String[] params;

    public KaiYuanErrorCode withParams(String... params) {

        this.params = params;

        return this;

    }

    public String getMessage() {

        if (params != null) {

            return String.format(errorMessage, params);

        }
        return null;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public static String getValue(String value) {
        KaiYuanErrorCode[] businessModeEnums = values();
        for (KaiYuanErrorCode businessModeEnum : businessModeEnums) {
            if (businessModeEnum.getErrorCode().equals(value)) {
                return businessModeEnum.getErrorCode();
            }
        }
        return null;
    }

    public static String getDesc(String value) {
        KaiYuanErrorCode[] businessModeEnums = values();
        for (KaiYuanErrorCode businessModeEnum : businessModeEnums) {
            if (businessModeEnum.getErrorCode().equals(value)) {
                return businessModeEnum.getErrorMessage();
            }
        }
        return null;
    }

	/*public static void main(String[] args) {
	System.out.println(KaiYuanErrorCode.getDesc("5"));
	System.out.println(KaiYuanErrorCode.ERROR_GETUSERINGO.getErrorMessage());
	System.out.println(KaiYuanErrorCode.LOGINERROR.withParams(String.valueOf(555)).getMessage());
	}*/

}
