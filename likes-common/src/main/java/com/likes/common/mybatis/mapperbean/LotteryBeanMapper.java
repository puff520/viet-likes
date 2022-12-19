package com.likes.common.mybatis.mapperbean;

import com.likes.common.model.dto.lottery.LotterySgModel;
import com.likes.common.model.vo.lottery.OptionSelectVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 各彩种相关查询
 *
 * @author ShaoMing
 * @version 1.0.0
 * @date 2019/1/16 11:06
 */
public interface LotteryBeanMapper {

    @Select("<script>" +
            "SELECT COUNT(s.`id`)" +
            "FROM `cqssc_lottery_sg` s" +
            " WHERE s.`open_status` = 'AUTO'" +
            " AND s.`cpk_number` != s.`kcw_number`" +
            " <if test='date != \"\"'> AND s.`date` = #{date}</if>" +
            "</script>")
    Integer countCqsscOpenException(@Param("date") String date);

    @Select("<script>" +
            "SELECT s.`issue`, CONCAT(s.`wan`, ',', s.`qian`, ',', s.`bai`, ',', s.`shi`, ',', s.`ge`) sg, s.`cpk_number` cpkNumber, s.`kcw_number` kcwNumber, s.`time` openTime, s.`ideal_time` `date` " +
            "FROM `cqssc_lottery_sg` s" +
            " WHERE s.`open_status` = 'AUTO'" +
            " AND s.`cpk_number` != s.`kcw_number`" +
            " <if test='date != \"\"'> AND s.`date` = #{date}</if>" +
            " ORDER BY s.`ideal_time` DESC" +
            " LIMIT #{pageNo}, #{pageSize}" +
            "</script>")
    List<LotterySgModel> queryCqsscOpenExceptionList(@Param("date") String date, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);


    @Select("<script>" +
            "SELECT COUNT(s.`id`)" +
            "FROM `xjssc_lottery_sg` s" +
            " WHERE s.`open_status` = 'AUTO'" +
            " AND s.`cpk_number` != s.`kcw_number`" +
            " <if test='date != \"\"'> AND s.`date` = #{date}</if>" +
            "</script>")
    Integer countXjsscOpenException(@Param("date") String date);

    @Select("<script>" +
            "SELECT s.`issue`, CONCAT(s.`wan`, ',', s.`qian`, ',', s.`bai`, ',', s.`shi`, ',', s.`ge`) sg, s.`cpk_number` cpkNumber, s.`kcw_number` kcwNumber, s.`time` openTime, s.`ideal_time` `date` " +
            "FROM `xjssc_lottery_sg` s" +
            " WHERE s.`open_status` = 'AUTO'" +
            " AND s.`cpk_number` != s.`kcw_number`" +
            " <if test='date != \"\"'> AND s.`date` = #{date}</if>" +
            " ORDER BY s.`ideal_time` DESC" +
            " LIMIT #{pageNo}, #{pageSize}" +
            "</script>")
    List<LotterySgModel> queryXjsscOpenExceptionList(@Param("date") String date, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    @Select("<script>" +
            "SELECT COUNT(s.`id`)" +
            "FROM `txffc_lottery_sg` s" +
            " WHERE s.`open_status` = 'AUTO'" +
            " AND s.`cpk_number` != s.`kcw_number`" +
            " <if test='date != \"\"'> AND s.`date` = #{date}</if>" +
            "</script>")
    Integer countTxffcOpenException(@Param("date") String date);

    @Select("<script>" +
            "SELECT s.`issue`, CONCAT(s.`wan`, ',', s.`qian`, ',', s.`bai`, ',', s.`shi`, ',', s.`ge`) sg, s.`cpk_number` cpkNumber, s.`kcw_number` kcwNumber, s.`time` openTime, s.`ideal_time` `date` " +
            "FROM `txffc_lottery_sg` s" +
            " WHERE s.`open_status` = 'AUTO'" +
            " AND s.`cpk_number` != s.`kcw_number`" +
            " <if test='date != \"\"'> AND s.`date` = #{date}</if>" +
            " ORDER BY s.`ideal_time` DESC" +
            " LIMIT #{pageNo}, #{pageSize}" +
            "</script>")
    List<LotterySgModel> queryTxffcOpenExceptionList(@Param("date") String date, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    @Select("<script>" +
            "SELECT COUNT(s.`id`)" +
            "FROM `pcegg_lottery_sg` s" +
            " WHERE s.`open_status` = 'AUTO'" +
            " AND s.`cpk_number` != s.`kcw_number`" +
            " <if test='date != \"\"'> AND s.`ideal_time` like #{date}</if>" +
            "</script>")
    Integer countPcddOpenException(@Param("date") String date);

    @Select("<script>" +
            "SELECT s.`issue`, s.`number` sg, s.`cpk_number` cpkNumber, s.`kcw_number` kcwNumber, s.`time` openTime, s.`ideal_time` `date`" +
            "FROM `pcegg_lottery_sg` s" +
            " WHERE s.`open_status` = 'AUTO'" +
            " AND s.`cpk_number` != s.`kcw_number`" +
            " <if test='date != \"\"'> AND s.`ideal_time` like #{date}</if>" +
            " ORDER BY s.`ideal_time` DESC" +
            " LIMIT #{pageNo}, #{pageSize}" +
            "</script>")
    List<LotterySgModel> queryPcddOpenExceptionList(@Param("date") String date, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    @Select("<script>" +
            "SELECT COUNT(s.`id`)" +
            "FROM `bjpks_lottery_sg` s" +
            " WHERE s.`open_status` = 'AUTO'" +
            " AND s.`cpk_number` != s.`kcw_number`" +
            " <if test='date != \"\"'> AND s.`ideal_time` like #{date}</if>" +
            "</script>")
    Integer countBjpksOpenException(@Param("date") String date);

    @Select("<script>" +
            "SELECT s.`issue`, s.`number` sg, s.`cpk_number` cpkNumber, s.`kcw_number` kcwNumber, s.`time` openTime, s.`ideal_time` `date`" +
            "FROM `bjpks_lottery_sg` s" +
            " WHERE s.`open_status` = 'AUTO'" +
            " AND s.`cpk_number` != s.`kcw_number`" +
            " <if test='date != \"\"'> AND s.`ideal_time` like #{date}</if>" +
            " ORDER BY s.`ideal_time` DESC" +
            " LIMIT #{pageNo}, #{pageSize}" +
            "</script>")
    List<LotterySgModel> queryBjpksOpenExceptionList(@Param("date") String date, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    @Select("<script>" +
            "SELECT COUNT(s.`id`)" +
            "FROM `xyft_lottery_sg` s" +
            " WHERE s.`open_status` = 'AUTO'" +
            " AND s.`cpk_number` != s.`kcw_number`" +
            " <if test='date != \"\"'> AND s.`issue` like #{date}</if>" +
            "</script>")
    Integer countXyftOpenException(@Param("date") String date);

    @Select("<script>" +
            "SELECT s.`issue`, s.`number` sg, s.`cpk_number` cpkNumber, s.`kcw_number` kcwNumber, s.`time` openTime, s.`ideal_time` `date`" +
            "FROM `xyft_lottery_sg` s" +
            " WHERE s.`open_status` = 'AUTO'" +
            " AND s.`cpk_number` != s.`kcw_number`" +
            " <if test='date != \"\"'> AND s.`issue` like #{date}</if>" +
            " ORDER BY s.`ideal_time` DESC" +
            " LIMIT #{pageNo}, #{pageSize}" +
            "</script>")
    List<LotterySgModel> queryXyftOpenExceptionList(@Param("date") String date, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    @Select( " select kk.lottery_id as value ,kk.`name`  from lottery kk where kk.lottery_id in ( ${ids} ) and kk.is_delete = 0 " )
    List<OptionSelectVo> queryLiveLotteryList(String ids);
}
