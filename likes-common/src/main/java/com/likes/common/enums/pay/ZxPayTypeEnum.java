package com.likes.common.enums.pay;

/**
 * @ClassName ZxPayTypeEnum
 * @Description 中新支付类型
 * @Author yeezy
 * @Date 2019/11/8 13:41
 * @Version 1.0
 **/
public enum ZxPayTypeEnum {
/*1:支付宝wap支付
2:支付宝扫码支付
3:微信wap支付
4:微信扫码支付
5:微信公众号支付
6:QQwap支付
7:微信刷卡
8:快捷支付
9:网银网关
10:银联刷卡
11:银联wap
12:个人网关
13:个人码支付宝扫码
14:个人码支付宝wap
15:聚合码扫码
16:云闪付
17:收银台
18:支付宝wap（小额）
19:微信wap（小额）*/
      ZHI_FU_BAO_WAP(1, "支付宝wap支付",200,5000,"9:00-22:30"),
//      ZHI_FU_SCAN_CODE(2, "支付宝扫码支付"),
//    WEI_XIN_BAO_WAP(3, "微信wap支付"),
//    WEI_XIN_SCAN_CODE(4, "微信扫码支付"),
//    WEI_XIN_PUBLIC_NO(5, "微信公众号支付"),
//    QQ_WAP(6, "QQwap支付"),
//    WEI_XIN_SWIPE(7, "微信刷卡"),
//      QUICK_PAY(8, "快捷支付"),
//    ONLINE_BANK_GATEWAY(9, "网银网关"),
//    UNION_PAY_SWIPE(10, "银联刷卡"),
//    UNION_PAY_WAP(11, "银联wap"),
//    PERSON_GATEWAY(12, "个人网关"),
      PERSON_ZHI_FU_BAO_SCAN(13, "个人码支付宝扫码",300,10000,"all"),
//    PERSON_ZHI_FU_BAO_WAP(14, "个人码支付宝wap"),
//    AGGREGATE_CODE(15, "聚合码扫码"),
//    YUN_SHAN_FU(16, "云闪付"),
//    CHECKOUT_COUNTER(17, "收银台"),
      MIN_ZHI_FU_BAO_WAP(18, "支付宝wap（小额）",50,100,"9:00-23:00"),
      MIN_WEI_XIN_WAP(19, "微信wap（小额）",50,100,"9:00-23:00"),
    ;
    private int code;
    private final String desc;  //描述
    private int minAmount; //最小金额
    private int maxAmount;  //最大金额
    private String payTime; //可以支付的时间段

    ZxPayTypeEnum(int code, String desc,int minAmount,int maxAmount,String payTime) {
        this.code = code;
        this.desc = desc;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.payTime = payTime;
    }

    ZxPayTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public final static String getDescByCode(int code) {
        if(code < 1 || code >19) {
            throw new RuntimeException("中新支付没有"+code+"这种支付渠道");
        }
        ZxPayTypeEnum[] values = values();
        for(ZxPayTypeEnum item : values) {
            if(item.code == code) {
                return item.desc;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
