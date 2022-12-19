package com.likes.common.mybatis.mapperbean;



import com.likes.common.model.vo.activity.CpActivityInfoVo;
import com.likes.common.model.dto.start.CpActivityInfoDTO;
import com.likes.common.model.vo.activity.RedEnvelopVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CaiPiaoActivityBeanMapper {

    /**
     * 查询彩票活动标题
     *
     * @return
     */
    @Select("SELECT ACT_TITLE FROM `CP_ACTIVITY_INFO` WHERE IS_DELETED = 0")
    List<String> getTitleList();

    /**
     * 查询红包伪装金额
     */
    @Select("SELECT act_min_sham_amount as actMinShamAmount,act_max_sham_amount as actMaxShamAmount FROM cp_activity_info cp where cp.id = #{id}")
    Map<String, BigDecimal> getShamAmount(@Param("id") Integer id);

    /**
     * 查询红包记录
     */
    @Select("SELECT meb_nickname as mebNickname,receive_amount as receiveAmount FROM cp_activity_data cp where cp.act_id = #{actId} order by cp.id DESC limit 0,10")
    List<RedEnvelopVO> getRedEnvelopReceiveInfoList(@Param("actId") Integer actId);

    /**
     * 查询红包领取金额
     */
    @Select("SELECT receive_amount FROM `cp_activity_data` where act_id = #{actId} and meb_id = #{mebId} limit 0,1")
    BigDecimal getReceiveAmount(@Param("actId") Integer actId, @Param("mebId") Integer mebId);

    /**
     * 查询广告活动图功能所需要的跳转的活动信息
     */
    @Select("select id as id,act_into_page as actIntoPage,act_title as actTitle from cp_activity_info where act_status = 0 and is_deleted = 0 and act_end_time >= NOW()")
    List<CpActivityInfoDTO> getAdActivityInfo();


    @Select("<script>SELECT count(*) as count FROM `cp_activity_info` where is_deleted = 0"
            + "<if test='actType != null'> and act_type = #{actType}</if>"
            + "<if test='actStatus != null'> and act_status = #{actStatus}</if>"
            + "</script>")
    int count(@Param("actType") Integer actType, @Param("actStatus") Integer actStatus);

    @Select("<script>SELECT \r\n" +
            "(select sum(t1.receive_amount) from cp_activity_data t1 where t1.act_id = t2.id) as receiveAmount,\r\n" +
            "t2.id,\r\n" +
            "t2.act_out_banner as actOutBanner,\r\n" +
            "t2.act_in_banner as actInBanner,\r\n" +
            "t2.act_start_time as actStartTime,\r\n" +
            "t2.act_end_time as actEndTime,\r\n" +
            "t2.act_title AS actTitle,\r\n" +
            "t2.act_guide as actGuide,\r\n" +
            "t2.act_min_amount as actMinAmount,\r\n" +
            "t2.act_max_amount AS actMaxAmount,\r\n" +
            "t2.act_min_sham_amount AS actMinShamAmount,\r\n" +
            "t2.act_max_sham_amount AS actMaxShamAmount,\r\n" +
            "t2.act_type AS actType,\r\n" +
            "t2.act_status as actStatus,\r\n" +
            "t2.act_into_page as actIntoPage,\r\n" +
            "t2.is_deleted AS isDeleted,\r\n" +
            "t2.is_popup AS isPopup,\r\n" +
            "t2.create_time AS createTime,\r\n" +
            "t2.update_time AS updateTime,\r\n" +
            "t2.act_receive_limit_bet_amount AS actReceiveLimitBetAmount,\r\n" +
            "t2.act_receive_limit_amount AS actReceiveLimitAmount,\r\n" +
            "t2.is_today_charge_hundred AS isTodayChargeHundred,\r\n" +
            "t2.start_date AS startDate,\r\n" +
            "t2.stop_date AS stopDate,\r\n" +
            "t2.start_time AS startTime,\r\n" +
            "t2.stop_time AS stopTime,\r\n" +
            "t2.time_type AS timeType \r\n" +
            "FROM cp_activity_info t2\r\n" +
            " where t2.is_deleted = 0"
            + "<if test='actType != null'> and t2.act_type = #{actType}</if>"
            + "<if test='actStatus != null'> and t2.act_status = #{actStatus}</if>"
            + " ORDER BY t2.CREATE_TIME DESC limit #{pageNo},#{pageSize}</script>")
    List<CpActivityInfoVo> list(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("actType") Integer actType, @Param("actStatus") Integer actStatus);

}
