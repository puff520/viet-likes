package com.likes.common.enums.sms;

import java.util.LinkedList;
import java.util.List;

/**
 * 短信通道
 */
public enum SmsEnum {

    LINKAI("SMS_LINKAI", "凌凯"),
    MEILIAN("SMS_MEILIAN", "美联"),
    RONGLIANYUN("SMS_RONGLIANYUN", "容联云通讯"),
    ALIYUN("SMS_ALIYUN", "阿里云"),
    JUHESHUJU("SMS_JUHESHUJU", "聚合数据"),
    YUNPIAN("SMS_YUNPIAN", "云片"),
    WANGJIAN("SMS_WANGJIAN", "网健"),
    YUNXINSHI("SMS_YUNXINSHI", "云信使"),
    YUNZHIXUN("SMS_YUNZHIXUN_OLD", "云之讯"),
    JIGUAN("SMS_JIGUANG_OLD", "极光");


    private final String code;
    private final String desc;

    SmsEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static List<String> getAll() {
        List<String> list = new LinkedList<>();
        for (SmsEnum smsEnum : SmsEnum.values()) {
            list.add(smsEnum.getCode());
        }
        return list;
    }
}
