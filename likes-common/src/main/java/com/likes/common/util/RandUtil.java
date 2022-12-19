package com.likes.common.util;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author lucien
 * @create 2020/8/20 1:06
 */
public class RandUtil {

    /**
     * 生成随机数字
     *
     * @param length
     * @return
     */
    public static String RandomNum(int length) {
        final int maxNum = 10;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        while (count < length) {
            // 生成随机数，取绝对值，防止生成负数
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为9
            if (i >= 0 && i < str.length) {
                sb.append(str[i]);
                count++;
            }
        }
        return sb.toString();
    }

    /**
     * 生成随机数字
     *
     * @param length
     * @param nonums 不包含的数字
     * @return
     */
    public static String RandomNum(int length, int... nonums) {
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String s = "0,1,2,3,4,5,6,7,8,9";
        if (nonums != null && nonums.length > 0) {
            for (int j = 0; j < nonums.length; j++)
                s = s.replaceAll(nonums[j] != 9 ? nonums[j] + "," : nonums[j] + "", "");
            str = s.split(",");
        }
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        while (count < length) {
            // 生成随机数，取绝对值，防止生成负数
            i = Math.abs(r.nextInt(str.length));
            if (i >= 0 && i < str.length) {
                sb.append(str[i]);
                count++;
            }
        }
        return sb.toString();
    }

    /**
     * 给定字符串中生成随机数字
     *
     * @param length
     * @param nums
     * @return
     */
    public static String RandomNum(int length, String nums) {
        if (org.apache.commons.lang.StringUtils.isEmpty(nums))
            return null;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        String[] str = nums.split(",");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        while (count < length) {
            // 生成随机数，取绝对值，防止生成负数
            i = Math.abs(r.nextInt(str.length));
            if (i >= 0 && i < str.length) {
                sb.append(str[i]);
                count++;
            }
        }
        return sb.toString();
    }

    /**
     * 给定字符串中生成随机不重复数字
     *
     * @param length
     * @param nums
     * @return
     */
    public static String RandomDifNum(int length, String nums) {
        if (org.apache.commons.lang.StringUtils.isEmpty(nums))
            return null;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        List<String> list = new ArrayList<String>(Arrays.asList(nums.split(",")));
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        while (count < length) {
            // 生成随机数，取绝对值，防止生成负数
            i = Math.abs(r.nextInt(list.size()));
            if (i >= 0 && i < list.size()) {
                sb.append(list.get(i));
                list.remove(i);
                count++;
            }
        }
        return sb.toString();
    }

    /**
     * 区间获取随机字符串
     *
     * @param length
     * @param max    最大值
     * @param min    最小值
     * @return
     */
    public static String RandomNumByReg(int length, int max, int min) {
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        List<Integer> list = new ArrayList<Integer>();
        if (max > 9)
            max = 9;
        for (int j = min; j <= max; j++)
            list.add(j);
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        while (count < length) {
            // 生成随机数，取绝对值，防止生成负数
            i = Math.abs(r.nextInt(list.size()));
            if (i >= 0 && i < list.size()) {
                sb.append(list.get(i));
                count++;
            }
        }
        return sb.toString();
    }

    /**
     * 根据长度，和值获取随机匹配数
     *
     * @param length
     * @param sum    总和
     * @return
     */
    public static String RandomNumBySum(int length, int sum) {
        Integer[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        List<Integer> list = Arrays.asList(nums);
        StringBuffer num = new StringBuffer();
        if (sum >= 0) {
            if (length == 1 && sum <= 9) {
                for (Integer i : list) {
                    if (sum == i) {
                        num.append(i);
                        return num.toString();
                    }
                }
            } else if (length == 2 && sum <= 18) {
                List<Integer> list2 = new ArrayList<Integer>(list);
                Collections.shuffle(list);
                Collections.shuffle(list2);
                for (Integer i : list) {
                    for (Integer j : list2) {
                        if (sum == i + j) {
                            num.append(i).append(j);
                            return num.toString();
                        }
                    }
                }
            } else if (length == 3 && sum <= 27) {
                List<Integer> list2 = new ArrayList<Integer>(list);
                List<Integer> list3 = new ArrayList<Integer>(list);
                Collections.shuffle(list);
                Collections.shuffle(list2);
                Collections.shuffle(list3);
                for (Integer i : list) {
                    for (Integer j : list2) {
                        for (Integer k : list3)
                            if (sum == i + j + k) {
                                num.append(i).append(j).append(k);
                                return num.toString();
                            }
                    }
                }
            } else if (length == 4 && sum <= 36) {
                List<Integer> list2 = new ArrayList<Integer>(list);
                List<Integer> list3 = new ArrayList<Integer>(list);
                List<Integer> list4 = new ArrayList<Integer>(list);
                Collections.shuffle(list);
                Collections.shuffle(list2);
                Collections.shuffle(list3);
                Collections.shuffle(list4);
                for (Integer i : list) {
                    for (Integer j : list2) {
                        for (Integer k : list3) {
                            for (Integer n : list4) {
                                if (sum == i + j + k + n) {
                                    num.append(i).append(j).append(k).append(n);
                                    return num.toString();
                                }
                            }
                        }
                    }
                }
            } else if (length == 5 && sum <= 45) {
                List<Integer> list2 = new ArrayList<Integer>(list);
                List<Integer> list3 = new ArrayList<Integer>(list);
                List<Integer> list4 = new ArrayList<Integer>(list);
                List<Integer> list5 = new ArrayList<Integer>(list);
                Collections.shuffle(list);
                Collections.shuffle(list2);
                Collections.shuffle(list3);
                Collections.shuffle(list4);
                Collections.shuffle(list5);
                for (Integer i : list) {
                    for (Integer j : list2) {
                        for (Integer k : list3) {
                            for (Integer n : list4) {
                                for (Integer p : list5) {
                                    if (sum == i + j + k + n) {
                                        num.append(i).append(j).append(k).append(n).append(p);
                                        return num.toString();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return num.toString();
    }

    /**
     * 根据差值获取匹配值（最大值最小值区间数）
     *
     * @param length    长度
     * @param reducenum 差值
     * @return
     */
    public static String RandomNumByReduce(int length, int reducenum) {
        Integer[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        List<Integer> list = Arrays.asList(nums);
        Collections.shuffle(list);
        List<Integer> list2 = new ArrayList<Integer>(list);
        Collections.shuffle(list2);
        StringBuffer num = new StringBuffer();
        if (reducenum >= 0 && reducenum <= 9) {
            for (Integer i : list) {
                for (Integer j : list2) {
                    if (reducenum == Math.abs(j - i)) {
                        num.append(i).append(j);
                        for (int k = 2; k < length; k++) {
                            num.append(RandomNumByReg(1, j > i ? j : i, j > i ? i : j));
                        }
                        break;
                    }
                }
                if (!org.apache.commons.lang.StringUtils.isEmpty(num.toString()))
                    break;
            }
        }
        return num.reverse().toString();
    }

    /**
     * 获取快三随机数
     *
     * @param length
     * @return
     */
    public static String RandomQuickNum(int length) {
        final int maxNum = 6;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = { '1', '2', '3', '4', '5', '6' };
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        while (count < length) {
            // 生成随机数，取绝对值，防止生成负数
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                sb.append(str[i]);
                count++;
            }
        }
        return sb.toString();
    }

    /**
     * 生成随机数字和字母
     *
     * @param length
     * @return
     */
    public static String RandomString(int length) {
        String val = "";
        Random random = new Random();
        // 参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 生成uuid(去掉了-)
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成：五位随机数 + 当前年月日时分秒 yyMMddHHmmss 共17位
     *
     * @return
     */
    public static String getRandNo() {
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
        return rannum + str;// 当前时间
    }

    /**
     * 生存随机位数的数字
     *
     * @param length
     * @return
     */
    public static Long RandomSeconds(int length) {
        final int maxNum = 10;
        int i; // 生成的随机数
        int count = Math.abs(new Random().nextInt(length + 1));
        while (count == 0)
            count = Math.abs(new Random().nextInt(length + 1));
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        for (int k = 0; k < count; k++) {
            // 生成随机数，取绝对值，防止生成负数
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                sb.append(str[i]);
            }
        }
        return Long.parseLong(sb.toString());
    }

    /**
     * 获取排除字符后随机数
     *
     * @param nostr 排除项 数字，大小单双质合数
     * @return
     */
    public static Integer getRandomNums(String nostr) {
        String str = "0123456789";
        if (!org.apache.commons.lang.StringUtils.isEmpty(nostr)) {
            String[] noarr = nostr.split(",");
            for (String arr : noarr) {
                if (arr.contains("大")) {
                    str = str.replace("5", "");
                    str = str.replace("6", "");
                    str = str.replace("7", "");
                    str = str.replace("8", "");
                    str = str.replace("9", "");
                } else if (arr.contains("小")) {
                    str = str.replace("0", "");
                    str = str.replace("1", "");
                    str = str.replace("2", "");
                    str = str.replace("3", "");
                    str = str.replace("4", "");
                } else if (arr.contains("单")) {
                    str = str.replace("1", "");
                    str = str.replace("3", "");
                    str = str.replace("5", "");
                    str = str.replace("7", "");
                    str = str.replace("9", "");
                } else if (arr.contains("双")) {
                    str = str.replace("0", "");
                    str = str.replace("2", "");
                    str = str.replace("4", "");
                    str = str.replace("6", "");
                    str = str.replace("8", "");
                } else if (arr.contains("质")) {
                    str = str.replace("1", "");
                    str = str.replace("2", "");
                    str = str.replace("3", "");
                    str = str.replace("5", "");
                    str = str.replace("7", "");
                } else if (arr.contains("合")) {
                    str = str.replace("0", "");
                    str = str.replace("4", "");
                    str = str.replace("6", "");
                    str = str.replace("8", "");
                    str = str.replace("9", "");
                } else {
                    str = str.replace(arr, "");
                }
            }
            if (org.apache.commons.lang.StringUtils.isEmpty(str)) {
                // 排除项减1
                List<String> list = new ArrayList<String>(Arrays.asList(nostr.split(",")));
                list.remove(Math.abs(new Random().nextInt(list.size())));
                return getRandomNums(org.apache.commons.lang.StringUtils.join(list, ","));
            }
        }
        int index = Math.abs(new Random().nextInt(str.length() + 1));
        if (index == 0)
            index++;
        return Integer.parseInt(str.substring(index - 1, index));
    }

    /**
     * 获取PK拾一个1到10十个不重复数字随机排列的字符串 生成最大个数为10个
     * removeNum 删除数List
     * @return
     */
    public static List<Integer> RandomPkNum(List<Integer> removeNumList) {
        // 集合 ,临时集合temp存放1~10个数字
        ArrayList<Integer> temp = new ArrayList<Integer>();
        // list集合存放需要的数字
        ArrayList<Integer> list = new ArrayList<Integer>();
        // 给集合添加1~10的数字
        for (int i = 1; i < 11; i++) {
            temp.add(i);
        }
        // while循环 随即抽取集合的数字给数组
        while (true) {
            if (list.size() == 10) {
                break;
            }
            int it = temp.get((int) (Math.random() * 10));
            // 如果list存在这个数组就继续循环
            if (list.contains(it)) {
                continue;
            } else {
                // 如果list不存在这个数字，那么就把这个数字给数组，并且在list中加入这个数字
                list.add(it);
            }
        }
        if(removeNumList !=  null){
            for (Integer removeNum : removeNumList) {
                list.remove(removeNum);
            }
        }
        return list;
    }

    /**
     * 生成1-10随机数字
     *
     * @param length
     * @return
     */
    public static Integer RandomPkNum() {
        Random random = new Random();
        int result=random.nextInt(10);
        return result+1;
    }

    /**
     * 获取排除字符后随机数
     *
     * @param nostr 排除项 数字，大小单双数
     * @return
     */
    public static Integer getRandomPkNums(String nostr, List<Integer> removeNumList) {
        List<Integer> list =  new ArrayList<>();
        for (int i =1; i<= 10;i++){
            list.add(i);
        }
        list.removeAll(removeNumList);
        if (!org.apache.commons.lang.StringUtils.isEmpty(nostr)) {
            String[] noarr = nostr.split(",");
            for (String arr : noarr) {
                if (arr.contains("大")) {
                    List<Integer> arrList = Arrays.asList(6, 7, 8, 9, 10);
                    list.removeAll(arrList);
                } else if (arr.contains("小")) {
                    List<Integer> arrList = Arrays.asList(1, 2, 3, 4, 5);
                    list.removeAll(arrList);
                } else if (arr.contains("单")) {
                    List<Integer> arrList = Arrays.asList(1, 3, 5, 7, 9);
                    list.removeAll(arrList);
                } else if (arr.contains("双")) {
                    List<Integer> arrList = Arrays.asList(2, 4, 6, 8, 10);
                    list.removeAll(arrList);
                } else {
                    list.remove(Integer.valueOf(arr.trim()));
                }
            }
            if (list == null || list.size() < 1) {
                // 排除项减1
                List<String> listNostr = new ArrayList<String>(Arrays.asList(nostr.split(",")));
                listNostr.remove(Math.abs(new Random().nextInt(listNostr.size())));
                return getRandomPkNums(StringUtils.join(listNostr, ","), removeNumList);
            }
        }
        int index = Math.abs(new Random().nextInt(list.size() + 1));
        if (index == 0)
            index++;
        return list.get(index - 1);
    }

    /**
     * 获取不重复数字随机排列的字符串
     * number 生成数字个数
     * removeNum 删除数List
     * maxNux 1-maxNum个数字里生成number个数字
     * @return
     */
    public static List<Integer> RandomNum(int number, List<Integer> removeNumList, int maxNum) {
        // 集合 ,临时集合temp存放数字
        ArrayList<Integer> temp = new ArrayList<Integer>();
        // list集合存放需要的数字
        ArrayList<Integer> list = new ArrayList<Integer>();
        // 给集合添加1~maxNum的数字
        for (int i = 1; i <= maxNum; i++) {
            temp.add(i);
        }
        // while循环 随即抽取集合的数字给数组
        while (true) {
            if (list.size() == number) {
                break;
            }
            int it = temp.get((int) (Math.random() * maxNum));
            // 如果list存在这个数组就继续循环
            if (list.contains(it) || removeNumList.contains(it)) {
                continue;
            } else {
                // 如果list不存在这个数字，那么就把这个数字给数组，并且在list中加入这个数字
                list.add(it);
            }
        }
        return list;
    }
}
