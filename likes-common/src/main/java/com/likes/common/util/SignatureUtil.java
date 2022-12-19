package com.likes.common.util;

import com.likes.common.util.encrypt.AESUtils;
import com.likes.common.util.encrypt.MD5;

import java.util.Date;

/**
 * @Author xiaoming
 * @ClassName 签名工具类
 * @Description
 * @Date 9:23 上午 6/18/20
 **/
public class SignatureUtil {

    public final static String APIURL = "apiurl";

    public final static String SITECODE = "sitecode";

    public final static String TIMESTAMP = "timestamp";

    /**
     * 获取签名sign
     *
     * @param sitecode 通知返回来的参数数组
     * @param apiurl   通知返回来的参数数组
     * @return 签名
     */
    public static String sign(String sitecode, String apiurl) {
        // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String prestr = getSignStr(sitecode, apiurl);
        // 生成签名结果
        String mysign = AESUtils.encryptData(MD5.md5(prestr), sitecode);
        return mysign;
    }

    /**
     * 校验签名
     *
     * @param sign 通知返回来的参数数组
     * @return 验证结果
     */
    public static boolean verify(String sign, String sitecode, String apiurl) {
        String mysign = sign(sitecode, apiurl);
        return mysign.equals(sign);
    }

    private static String getSignStr(String sitecode, String apiurl) {
        return sitecode + DateUtils.formatDate(new Date(), "yyyy-MM-dd") + apiurl;
    }
}
