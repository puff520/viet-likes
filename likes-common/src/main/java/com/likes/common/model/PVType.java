/**
 *
 */
package com.likes.common.model;

/**
 * 页面浏览量 类型
 *
 * @author abu
 *
 */
public enum PVType {

    /**
     * 普通活动浏览量
     */
    act_normalactivity,
    /**
     * 社区课程浏览量
     */
    act_courseactivity,
    /**
     * 主题活动浏览量
     */
    act_comthemeactivity,
    /**
     * 投票浏览量
     */
    act_voteinfo,
    /**
     * 比赛浏览量
     */
    act_contestactivity,
    /**
     * 专业教育浏览量
     */
    act_degreegraduated,
    /**
     * 资讯公告浏览量
     */
    newsnotice,
    /**
     * 区级主题活动
     */
    act_comareathemeactivity,
    /**
     * 场馆
     */
    spc_areaspaceinfo;

    public static PVType get(String objtype) {
        if ("normalactivity".endsWith(objtype)) {// 普通活动浏览量
            return PVType.act_normalactivity;

        } else if ("courseactivity".endsWith(objtype)) {// 社区课程浏览量
            return PVType.act_courseactivity;

        } else if ("comthemeactivity".endsWith(objtype)) {// 公益活动浏览量
            return PVType.act_comthemeactivity;

        } else if ("voteinfo".endsWith(objtype)) {// 投票浏览量
            return PVType.act_voteinfo;

        } else if ("contestactivity".endsWith(objtype)) {// 比赛浏览量
            return PVType.act_contestactivity;

        } else if ("newsnotice".endsWith(objtype)) {// 资讯公告浏览量
            return PVType.newsnotice;

        } else if ("comareathemeactivity".endsWith(objtype)) {// 社区活动
            return PVType.act_comareathemeactivity;

        } else if ("spc_areaspaceinfo".endsWith(objtype)) {// 处理场馆
            return PVType.spc_areaspaceinfo;

        }
        return null;
    }

}
