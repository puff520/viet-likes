package com.likes.common.util;

import com.likes.common.model.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 阿布
 * <p>
 * 会员等级计算方式
 */
public class LevelUtils {

    private final static Logger logger = LoggerFactory.getLogger(LevelUtils.class);

    private static final double a = 2.5;
    private static final double b = 18.0;
    private static final double c = 10.0;
    private static final int maxlevels = 101;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("等级" + (i + 1) + " " + func(i, a, b, c));
        }
//		System.out.println(getLevelScore(1));
//		System.out.println(getIntervalScore(1, 2));
//		System.out.println(getAllLevel(100).toString());
//		System.out.println(getAllLevelRewrite(maxlevels).toString());
//		long start = System.currentTimeMillis();
//		getLevel(260686);
//		getLevelRewrite(260686);
//
//		System.out.println("88888");
//		System.out.println(getLevelScoreRewrite(1));
//		System.out.println(getLevelScoreRewrite(2));
//		System.out.println(getIntervalScoreRewrite(1, 3));
//
//		getLevelRewrite(5);
//		System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 根据数 获取 当前等级 和 下一等级
     *
     * @param num
     * @return
     */
    public static List<Map<Integer, Double>> getLevel(double num) {
        List<Map<Integer, Double>> newret = new ArrayList<Map<Integer, Double>>();
        Map<Integer, Double> ret = getAllLevel(maxlevels);

//		ret.forEach((k, v) -> {
//			if (v > num) {
//				newret.put(k - 1, (Double) (ret.get(k - 1)));
//				newret.put(k, v);
//			}
//		});

        for (Map.Entry<Integer, Double> entry : ret.entrySet()) {
            Integer mapKey = entry.getKey();
            Double mapValue = entry.getValue();
            // System.out.println(mapKey+":"+mapValue);
            if (mapValue > num) {
                Map<Integer, Double> first = new HashMap<Integer, Double>();
                first.put(mapKey - 1, ret.get(mapKey - 1));
                newret.add(first);
                Map<Integer, Double> second = new HashMap<Integer, Double>();
                second.put(mapKey, mapValue);
                newret.add(second);
                break;
            }
        }

        return newret;
    }

    ///////////////////////////////////////////// 原始方法start/////////////////////////////////////////////////////////////////////////////////

    /**
     * 积分算法
     *
     * @param x
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static double func(double x, double a, double b, double c) {
        return Math.ceil(a * Math.pow(x, 3) + b * x + c);
    }

    /**
     * 等级所需积分
     *
     * @param level
     * @return
     */
    public static double getLevelScore(int level) {
        return func(level - 1, a, b, c);
    }

    /**
     * 2个等级 之间 所需要的 积分 差
     *
     * @param minlevel
     * @param maxlevel
     * @return
     */
    public static double getIntervalScore(int minlevel, int maxlevel) {
        return func(maxlevel - 1, a, b, c) - func(minlevel - 1, a, b, c);
    }

    /**
     * 查看每个等级 所需要的积分
     *
     * @param maxlevel
     * @return
     */
    public static Map<Integer, Double> getAllLevel(int maxlevel) {
        if (maxlevel >= 100) {
            maxlevel = 100;
        }
        Map<Integer, Double> ret = new HashMap<Integer, Double>();
        for (int i = 0; i < maxlevel; i++) {
            ret.put(i + 1, func(i, a, b, c));
        }
        return ret;
    }

/////////////////////////////////////////////原始方法end/////////////////////////////////////////////////////////////////////////////////

    // 等级从1 开始 ，所以后续等级从 1开始算

    /**
     * 等级所需积分
     *
     * @param level
     * @return
     */
    public static double getLevelScoreRewrite(int level) {
        if (level == 1) {
            return 0d;
        }
        return getLevelScore(level - 1);
    }

    /**
     * 等级差 所需积分
     *
     * @param minlevel
     * @param maxlevel
     * @return
     */
    public static double getIntervalScoreRewrite(int minlevel, int maxlevel) {
        return getLevelScoreRewrite(maxlevel) - getLevelScoreRewrite(minlevel);
    }

    /**
     * 查看每个等级 所需要的积分
     *
     * @param maxlevel
     * @return
     */
    public static Map<Integer, Double> getAllLevelRewrite(int maxlevel) {
        if (maxlevel >= 101) {
            maxlevel = 101;
        }
        Map<Integer, Double> ret = new HashMap<Integer, Double>();
        for (int i = 0; i < maxlevel; i++) {
            ret.put(i + 2, func(i, a, b, c));
        }
        return ret;
    }

    /**
     * 根据数 获取 当前等级 和 下一等级
     *
     * @param num
     * @return
     */
    public static List<Level> getLevelRewrite(double num) {
        List<Level> newret = new ArrayList<Level>();
        Map<Integer, Double> ret = getAllLevelRewrite(maxlevels);
        /*
         * ret.forEach((k,v)->{ if (v > num) { newret.put(k-1, (Double)(ret.get(k-1))); newret.put(k, v); } });
         */
        for (Map.Entry<Integer, Double> entry : ret.entrySet()) {
            Integer mapKey = entry.getKey();
            Double mapValue = entry.getValue();
            // System.out.println(mapKey+":"+mapValue);
            if (mapValue > num) {
                // Map<Integer, Double> first = new HashMap<Integer, Double>();
                // first.put(mapKey - 1, ret.get(mapKey - 1));
                Level first = new Level();
                first.setIndex(mapKey - 1);
                first.setScore(ret.get(mapKey - 1));
                newret.add(first);
                // Map<Integer, Double> second = new HashMap<Integer, Double>();
                // second.put(mapKey, mapValue);
                Level second = new Level();
                second.setIndex(mapKey);
                second.setScore(mapValue);
                newret.add(second);
                break;
            }

            // 如果已经到了顶级 默认都是最大级别
            double maxDouble = ret.get(maxlevels - 1);
            if (num >= maxDouble) {
                // Map<Integer, Double> first = new HashMap<Integer, Double>();
                // first.put(maxlevels -1 , ret.get(maxlevels - 1));
                Level first = new Level();
                first.setIndex(maxlevels - 1);
                first.setScore(ret.get(maxlevels - 1));
                newret.add(first);
                // Map<Integer, Double> second = new HashMap<Integer, Double>();
                // second.put(maxlevels -1 , ret.get(maxlevels - 1));
                Level second = new Level();
                second.setIndex(maxlevels - 1);
                second.setScore(ret.get(maxlevels - 1));
                newret.add(second);
            }

        }

        return newret;
    }

}
