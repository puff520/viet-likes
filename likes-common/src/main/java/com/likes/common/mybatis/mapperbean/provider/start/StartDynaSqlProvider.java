package com.likes.common.mybatis.mapperbean.provider.start;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 后台开始模块动态sql
 *
 * @author lzy
 * @create 2018-06-08 10:42
 **/
@Component
public class StartDynaSqlProvider {

    /**
     * 根据条件查询移动公告sql
     * @param map
     * @return
     */
    public String listAppNotice(final Map<String, Object> map) {
        String title = (String) map.get("title");
        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");

        return new org.apache.ibatis.jdbc.SQL() {
            {
                SELECT(" a.id, c.`name` AS categoryName, a.title, a.author, a.start_date AS startDate, a.end_date AS endDate, a.newest, a.`status`, a.roll_status AS rollStatus,a.popup AS popup, a.issue_time AS issueTime ");
                FROM("app_notice a ");
                LEFT_OUTER_JOIN("app_notice_category c ON a.category_id = c.id ");

                if (StringUtils.isNotBlank(title)) {
                    WHERE("a.deleted = 0 and a.title like '%"+title+"%'");
                }else{
                    WHERE("a.deleted = 0 ");
                }
                ORDER_BY("a.id DESC limit "+pageNo+","+pageSize);
            }
        }.toString();

    }


    /**
     * 根据条件查询最新活动sql
     * @param map
     * @return
     */
    public String listPopupActivity(final Map<String, Object> map) {
        String title = (String) map.get("title");
        Integer appId = (Integer) map.get("appId");
        Integer versionId = (Integer) map.get("versionId");
        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");
        return new org.apache.ibatis.jdbc.SQL() {
            {
                SELECT(" p.id, a.`name` as appName, v.number as versionNumber, p.title, ac.title as activityName, p.`status`, p.issue_time as issueTime ");
                FROM("popup_activity p ");
                LEFT_OUTER_JOIN("app a on a.id = p.app_id ");
                LEFT_OUTER_JOIN("app_version v on v.id = p.version_id ");
                LEFT_OUTER_JOIN("activity ac on ac.id = p.activity_id ");
                WHERE("p.deleted = 0 ");
                if (appId != null) {
                    WHERE("p.app_id = #{appId}");
                }
                if (versionId != null) {
                    WHERE("p.version_id = #{versionId}");
                }
                if (StringUtils.isNotBlank(title)) {
                    WHERE("p.title like #{title}");
                }
                ORDER_BY("p.id DESC limit #{pageNo},#{pageSize}");
            }
        }.toString();
    }

    /**
     * 根据条件查询最新活动数量sql
     * @param map
     * @return
     */
    public String countPopupActivity(final Map<String, Object> map) {
        String title = (String) map.get("title");
        Integer appId = (Integer) map.get("appId");
        Integer versionId = (Integer) map.get("versionId");
        return new org.apache.ibatis.jdbc.SQL() {
            {
                SELECT(" count(p.id) ");
                FROM("popup_activity p ");
                LEFT_OUTER_JOIN("app a on a.id = p.app_id ");
                LEFT_OUTER_JOIN("app_version v on v.id = p.version_id ");
                LEFT_OUTER_JOIN("activity ac on ac.id = p.activity_id ");
                WHERE("p.deleted = 0 ");
                if (appId != null) {
                    WHERE("p.app_id = #{appId}");
                }
                if (versionId != null) {
                    WHERE("p.version_id = #{versionId}");
                }
                if (StringUtils.isNotBlank(title)) {
                    WHERE("p.title like #{title}");
                }
            }
        }.toString();
    }

    /**
     * 根据条件查询轮播图sql
     * @param map
     * @return
     */
    public String listSlideshow(final Map<String, Object> map) {
        Integer appId = (Integer) map.get("appId");
        Integer versionId = (Integer) map.get("versionId");
        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");
        return new org.apache.ibatis.jdbc.SQL() {
            {
                SELECT(" s.id, s.type, a.`name` as appName, v.number as versionNumber, s.name, s.photo_url as photoUrl, s.sort, s.issue_time as issueTime ");
                FROM("slideshow s ");
                LEFT_OUTER_JOIN("app a on a.id = s.app_id ");
                LEFT_OUTER_JOIN("app_version v on v.id = s.version_id ");
                WHERE("s.deleted = 0 ");
                if (appId != null) {
                    WHERE("s.app_id = #{appId}");
                }
                if (versionId != null) {
                    WHERE("(s.version_id = #{versionId} or s.version_id is null)");
                }
                ORDER_BY("s.id DESC limit #{pageNo},#{pageSize}");
            }
        }.toString();
    }

    /**
     * 根据条件查询轮播图数量sql
     * @param map
     * @return
     */
    public String countSlideshow(final Map<String, Object> map) {
        Integer appId = (Integer) map.get("appId");
        Integer versionId = (Integer) map.get("versionId");
        return new org.apache.ibatis.jdbc.SQL() {
            {
                SELECT(" count(s.id) ");
                FROM("slideshow s ");
                LEFT_OUTER_JOIN("app a on a.id = s.app_id ");
                LEFT_OUTER_JOIN("app_version v on v.id = s.version_id ");
                WHERE("s.deleted = 0 ");
                if (appId != null) {
                    WHERE("s.app_id = #{appId}");
                }
                if (versionId != null) {
                    WHERE("(s.version_id = #{versionId} or s.version_id is null)");
                }
            }
        }.toString();
    }

    /**
     * 根据条件查询App版本sql
     * @param map
     * @return
     */
    public String listAppVersion(final Map<String, Object> map) {
        Integer versionId = (Integer) map.get("versionId");
        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");

        String text = new org.apache.ibatis.jdbc.SQL() {
            {
                SELECT(" a.id, a.number as number,a.andriod_name as andriodName,a.ios_name as iosName, a.andriod_size as andriodSize, a.ios_size as iosSize," +
                        " a.notice_status as noticeStatus, " +
                        "a.message, a.andriod_down_url as andriodDownUrl,a.ios_down_url as iosDownUrl, " +
                        "(SELECT b.number from app_version b where b.deleted = 0 ORDER by b.id DESC LIMIT 0,1) as newestNumber ");
                FROM("app_version a ");
                WHERE("a.deleted = 0 ");
                if (versionId != null) {
                    WHERE("a.id = #{versionId}");
                }
                ORDER_BY("a.number DESC limit #{pageNo},#{pageSize}");
            }
        }.toString();
        return text;
    }

    /**
     * 根据条件查询App版本数量sql
     * @param map
     * @return
     */
    public String countAppVersion(final Map<String, Object> map) {
        Integer versionId = (Integer) map.get("versionId");
        return new org.apache.ibatis.jdbc.SQL() {
            {
                SELECT(" count(a.id)");
                FROM("app_version a ");
                WHERE("a.deleted = 0 ");
                if (versionId != null) {
                    WHERE("a.id = #{versionId}");
                }
            }
        }.toString();

    }
}
