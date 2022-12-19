
/**
 * Description
 *
 * @author kevin
 * @version 2020年4月14日 下午2:27:22
 */

package com.likes.common.util;

import com.likes.common.constant.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumUtils {
    // 用ascii码
    // 不能判断小数点有几个 ：1.1.1 也会返回true
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57) {
                if (chr != 46)
                    return false;
            }
        }
        return true;
    }

    // 正则表达式 : 完美

    /**
     * 判断字符串是否为数字(浮点类型也包括)
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    /**
     * 判断字符串是否为纯数字
     *
     * @param str
     * @return boolean
     */
    public static boolean isDigit(String str) {
        String reg = "^[0-9]*$";
        return str.matches(reg);
    }

    /**
     * 字符串格式化长度不足补0
     *
     * @param num       格式化数字
     * @param strLength 长度
     * @return
     */
    public static String addZeroForNum(int num, int strLength) {
        String reg = "%0" + strLength + "d";
        return String.format(reg, num);
    }


    /**
     * 数据精度：不保留小数点，默认舍
     *
     * @param number
     * @return
     */
    public static BigDecimal precision0down(BigDecimal number) {
        return round(number, Constants.NUM_PRECISION_0, RoundingMode.DOWN);
    }

    /**
     * 数据精度：保留2位小数，默认舍
     *
     * @param number
     * @return
     */
    public static BigDecimal precision2down(BigDecimal number) {
        return round(number, Constants.NUM_PRECISION_2, RoundingMode.DOWN);
    }

    /**
     * 数据精度：保留3位小数，默认舍
     *
     * @param number
     * @return
     */
    public static BigDecimal precision3down(BigDecimal number) {
        return round(number, Constants.NUM_PRECISION_3, RoundingMode.DOWN);
    }

    /**
     * 四舍五入算法
     *
     * @param number
     * @param scale        精度
     * @param roundingMode 舍入模式
     * @return
     */
    private static BigDecimal round(BigDecimal number, int scale, RoundingMode roundingMode) {
        if (null == number) {
            number = BigDecimal.ZERO;
        }

        if (scale < 0) {
            scale = 0;
        }

        if (null == roundingMode) {
            roundingMode = RoundingMode.HALF_UP;
        }

        return number.setScale(scale, roundingMode);
    }

    public static void main(String[] args) {
//        System.out.println(isNumeric("1234567890"));
//        System.out.println(isNumeric("98.00"));
//        System.out.println(isNumeric("aa98.00"));

//        System.out.println(isNumber("1234567890"));
//        System.out.println(isNumber("98.00"));
//        System.out.println(isNumber("aa98.00"));

//        System.out.println(new BigDecimal("0.00"));
//        System.out.println(new BigDecimal("0.000"));

		System.out.println(precision2down(null));
		System.out.println(precision2down(new BigDecimal("1.3446")));
		System.out.println(precision2down(new BigDecimal("1.3456")));
		System.out.println(precision3down(new BigDecimal("1.3454")));
		System.out.println(precision3down(new BigDecimal("1.3488")));
    }

}
