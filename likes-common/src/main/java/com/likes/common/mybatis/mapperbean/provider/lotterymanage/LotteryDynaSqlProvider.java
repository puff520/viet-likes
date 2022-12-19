package com.likes.common.mybatis.mapperbean.provider.lotterymanage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 后台彩种管理模块动态sql
 *
 * @author lzy
 * @create 2018-08-13 15:11
 **/
@Component
public class LotteryDynaSqlProvider {

    public String countLhcPhoto(final Map<String, Object> map) {
        String issue = (String) map.get("issue");
        Integer oneId = (Integer) map.get("oneId");
        Integer twoId = (Integer) map.get("twoId");
        return new org.apache.ibatis.jdbc.SQL() {
            {
                SELECT(" count(l.id) ");
                FROM("lhc_photo l ");
                LEFT_OUTER_JOIN("lhc_photo_category c on l.category_id = c.id ");
                WHERE("l.deleted = 0 ");
                if (oneId != null) {
                    WHERE("c.parent_id = #{oneId}");
                }
                if (twoId != null) {
                    WHERE("l.category_id = #{twoId}");
                }
                if (StringUtils.isNotBlank(issue)) {
                    WHERE("l.issue like #{issue}");
                }
            }
        }.toString();
    }


    public String listLhcPhoto(final Map<String, Object> map) {
        String issue = (String) map.get("issue");
        Integer oneId = (Integer) map.get("oneId");
        Integer twoId = (Integer) map.get("twoId");
        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");
        return new org.apache.ibatis.jdbc.SQL() {
            {
                SELECT(" l.id, c.name as categoryName, l.issue, l.url, l.create_time as createTime");
                FROM("lhc_photo l ");
                LEFT_OUTER_JOIN("lhc_photo_category c on l.category_id = c.id ");
                WHERE("l.deleted = 0 ");
                if (oneId != null) {
                    WHERE("c.parent_id = #{oneId}");
                }
                if (twoId != null) {
                    WHERE("l.category_id = #{twoId}");
                }
                if (StringUtils.isNotBlank(issue)) {
                    WHERE("l.issue like #{issue}");
                }
                ORDER_BY("l.id DESC limit #{pageNo},#{pageSize}");
            }
        }.toString();
    }


    public String listLhcPhotoCategory(final Map<String, Object> map) {
        String name = (String) map.get("name");
        Integer parentId = (Integer) map.get("parentId");
        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");
        return new org.apache.ibatis.jdbc.SQL() {
            {
                SELECT(" c.id, c.parent_id as parentId, c.name,c.sort, c.create_time as createTime, cc.name as parentName ");
                FROM("lhc_photo_category c ");
                LEFT_OUTER_JOIN("lhc_photo_category cc on c.parent_id = cc.id ");
                WHERE("c.deleted = 0 ");
                if (StringUtils.isNotBlank(name)) {
                    WHERE("c.name like #{name}");
                }
                if (parentId != null) {
                    WHERE("c.parent_id = #{parentId}");
                }
                ORDER_BY("c.parent_id ASC,c.create_time desc limit #{pageNo},#{pageSize}");
            }
        }.toString();
    }


    public String countLhcPhotoCategory(final Map<String, Object> map) {
        String name = (String) map.get("name");
        Integer parentId = (Integer) map.get("parentId");
        return new org.apache.ibatis.jdbc.SQL() {
            {
                SELECT(" count(c.id) ");
                FROM("lhc_photo_category c ");
                WHERE("c.deleted = 0 ");
                if (StringUtils.isNotBlank(name)) {
                    WHERE("c.name like #{name}");
                }
                if (parentId != null) {
                    WHERE("c.parent_id = #{parentId}");
                }
            }
        }.toString();
    }
}
