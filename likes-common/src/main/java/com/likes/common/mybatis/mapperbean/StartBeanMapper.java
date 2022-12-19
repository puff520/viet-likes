package com.likes.common.mybatis.mapperbean;

import com.likes.common.model.vo.*;
import com.likes.common.model.vo.activity.ActivityVO;
import com.likes.common.model.vo.start.AppNoticeVO;
import com.likes.common.mybatis.entity.AdBasic;
import com.likes.common.mybatis.mapperbean.provider.start.StartDynaSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 后台开始模块的动态查询接口
 */
public interface StartBeanMapper {

    /**
     * 根据条件查询移动公告
     *
     * @param pageNo
     * @param pageSize
     * @param title
     * @return
     */
    @SelectProvider(type = StartDynaSqlProvider.class, method = "listAppNotice")
    List<AppNoticeVO> listAppNotice(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("title") String title);


    /**
     * 根据id查询移动公告
     *
     * @param appNoticeId
     * @return
     */
    @Select("select a.id, a.category_id AS categoryId, a.title, a.author, a.start_date AS startDate, a.end_date AS endDate, a.newest, a.`status`, a.roll_status AS rollStatus,a.popup AS popup, a.issue_time AS issueTime, a.intro, a.message " +
            "from app_notice a where a.id = #{appNoticeId}")
    AppNoticeVO findAppNoticeById(@Param("appNoticeId") Integer appNoticeId);

    /**
     * 根据条件查询最新活动
     *
     * @param pageNo
     * @param pageSize
     * @param title
     * @param appId
     * @param versionId
     * @return
     */
    @SelectProvider(type = StartDynaSqlProvider.class, method = "listPopupActivity")
    List<PopupActivityVO> listPopupActivity(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("title") String title, @Param("appId") Integer appId, @Param("versionId") Integer versionId);

    /**
     * 根据条件查询最新活动数量
     *
     * @param title
     * @param appId
     * @param versionId
     * @return
     */
    @SelectProvider(type = StartDynaSqlProvider.class, method = "countPopupActivity")
    int countPopupActivity(@Param("title") String title, @Param("appId") Integer appId, @Param("versionId") Integer versionId);

    /**
     * 根据条件查询轮播图
     *
     * @param pageNo
     * @param pageSize
     * @param appId
     * @param versionId
     * @return
     */
    @SelectProvider(type = StartDynaSqlProvider.class, method = "listSlideshow")
    List<SlideshowVO> listSlideshow(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("appId") Integer appId, @Param("versionId") Integer versionId);

    /**
     * 根据条件查询轮播图数量
     *
     * @param appId
     * @param versionId
     * @return
     */
    @SelectProvider(type = StartDynaSqlProvider.class, method = "countSlideshow")
    int countSlideshow(@Param("appId") Integer appId, @Param("versionId") Integer versionId);

    /**
     * 根据条件查询App版本
     *
     * @param pageNo
     * @param pageSize
     * @param versionId
     * @return
     */
    @SelectProvider(type = StartDynaSqlProvider.class, method = "listAppVersion")
    List<AppVersionVO> listAppVersion(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("versionId") Integer versionId);

    /**
     * 根据条件查询App版本数量
     *
     * @param versionId
     * @return
     */
    @SelectProvider(type = StartDynaSqlProvider.class, method = "countAppVersion")
    int countAppVersion(@Param("versionId") Integer versionId);

    /**
     * 查询所有的APP
     *
     * @return
     */
    @Select("select id, share_url, two_code, android_url, ios_url, service_contract, service_qq1, service_qq2, h5_url from app")
    List<AppVO> findAllApp();

    /**
     * 根据查询APP版本
     *
     * @return
     */
    @Select("select a.id, a.number from app_version a where a.deleted = 0")
    List<AppVersionVO> listAppVersionVO();

    /**
     * 查询所有有效的精彩活动
     *
     * @return
     */
    @Select("SELECT a.id, a.title FROM activity a where a.deleted = 0 and NOW() BETWEEN CAST(a.start_time AS datetime) and CAST(a.end_time AS datetime)")
    List<ActivityVO> findAllActivity();

    @Select("SELECT a.id, a.title FROM activity a where (a.deleted = 0 and NOW() BETWEEN CAST(a.start_time AS datetime) and CAST(a.end_time AS datetime)) or a.id = #{id}")
    List<ActivityVO> findAllAndIdActivity(@Param("id") Integer id);

    /**
     * 根据id查找最新活动
     *
     * @param activityId
     * @return
     */
    @Select("SELECT p.id, p.app_id as appId, p.version_id as versionId, p.issue_time as issueTime, p.activity_id as activityId, p.message, p.title, p.status " +
            "FROM popup_activity p where p.id = #{activityId} and p.deleted = 0")
    PopupActivityVO getPopupActivityById(@Param("activityId") Integer activityId);


    /**
     * 查询所有有效的公告
     *
     * @return
     */
    @Select("SELECT a.id, a.title FROM app_notice a where a.deleted = 0 and NOW() BETWEEN CAST(a.start_date AS datetime) and CAST(a.end_date AS datetime)")
    List<AppNoticeVO> findAllAppNotice();

    @Select("SELECT a.id, a.title FROM app_notice a where (a.deleted = 0 and UNIX_TIMESTAMP(NOW()) BETWEEN a.start_date and a.end_date) or a.id = #{id}")
    List<AppNoticeVO> findAllAndIdAppNotice(@Param("id") Integer id);

    /**
     * 根据id查找轮播图
     *
     * @param activityId
     * @return
     */
    @Select("SELECT s.id, s.app_id as appId, s.version_id as versionId, s.name, s.type, s.jump_url as jumpUrl, s.photo_url as photoUrl,\n" +
            " s.issue_time as issueTime, s.sort from slideshow s where s.id = #{activityId} and s.deleted = 0 ")
    SlideshowVO getSlideshowById(@Param("activityId") Integer activityId);

    /**
     * 获取所有的VIP等级
     *
     * @return
     */
    @Select("SELECT v.id, v.name FROM vip_grade v where v.deleted = 0 ")
    List<VipGradeVO> findAllVipGrade();

    /**
     * 获取所有要展示的广告活动基本信息
     *
     * @return
     */
    @Select("SELECT a.id, a.title, a.start_time as startTime, a.end_time as endTime, a.sort FROM ad_basic a where a.deleted = 0 and a.`close` = 0 and ((NOW() BETWEEN CAST(a.start_time AS datetime) and CAST(a.end_time AS datetime)) or (a.hide = 0 and a.`close` = 0)) ORDER BY a.sort ASC ")
    List<AdBasic> findShowAdBasic();
}
