package com.likes.common.mybatis.mapperbean;

import com.likes.common.model.vo.BjpksSgVO;
import com.likes.common.mybatis.entity.BjpksLotterySg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface XyftBeanMapper {

    @Select("SELECT s.`number` FROM `xyft_lottery_sg` s WHERE s.`issue` LIKE #{date} and s.number is not null ORDER BY s.`issue` ASC")
    List<String> selectNumberByDate(@Param("date") String date);

    @Select("SELECT s.`number` FROM `xyft_lottery_sg` s WHERE s.`issue` LIKE #{date} and s.number is not null ORDER BY s.`issue` DESC")
    List<String> selectNumberByDateDesc(@Param("date") String date);

    @Select("SELECT s.`number` FROM `xyft_lottery_sg` s where s.number is not null ORDER BY s.`issue` DESC LIMIT #{size}")
    List<String> selectNumberLimitDesc(@Param("size") Integer size);

    @Select("SELECT s.`id`, s.`issue`, s.`number`, s.`time` FROM `xyft_lottery_sg` s WHERE s.`issue` LIKE #{date} and s.number is not null ORDER BY s.`issue` DESC")
    List<BjpksLotterySg> selectByDateDesc(@Param("date") String date);

//    @Select("SELECT s.`issue`, s.`number` FROM `xyft_lottery_sg` s where s.number is not null ORDER BY s.`issue` DESC LIMIT #{size}")
//    List<BjpksSgVO> selectLimitDesc(@Param("size") Integer size);

    @Update("UPDATE `xyft_recommend` r" +
            "  LEFT JOIN `xyft_lottery_sg` s ON r.`issue` = s.`issue` " +
            "SET " +
            "  r.`first` = CONCAT(SUBSTRING(s.`number`, 1, 2), '|', r.`first`)," +
            "  r.`second` = CONCAT(SUBSTRING(s.`number`, 4, 2), '|', r.`second`)," +
            "  r.`third` = CONCAT(SUBSTRING(s.`number`, 7, 2), '|', r.`third`)," +
            "  r.`fourth` = CONCAT(SUBSTRING(s.`number`, 10, 2), '|', r.`fourth`)," +
            "  r.`fifth` = CONCAT(SUBSTRING(s.`number`, 13, 2), '|', r.`fifth`)," +
            "  r.`sixth` = CONCAT(SUBSTRING(s.`number`, 16, 2), '|', r.`sixth`)," +
            "  r.`seventh` = CONCAT(SUBSTRING(s.`number`, 19, 2), '|', r.`seventh`)," +
            "  r.`eighth` = CONCAT(SUBSTRING(s.`number`, 22, 2), '|', r.`eighth`)," +
            "  r.`ninth` = CONCAT(SUBSTRING(s.`number`, 25, 2), '|', r.`ninth`)," +
            "  r.`tenth` = CONCAT(SUBSTRING(s.`number`, 28, 2), '|', r.`tenth`)," +
            "  r.`first_second` = CONCAT(CAST(SUBSTRING(s.`number`, 1, 2) AS SIGNED) + CAST(SUBSTRING(s.`number`, 4, 2) AS SIGNED), '|', r.`first_second`)" +
            "WHERE (LENGTH(r.`first`) - LENGTH(REPLACE(r.`first`, '|', ''))) < 3 AND s.`number` IS NOT NULL")
    Integer updateRecommend();

    @Update("UPDATE `xyft_kill_number` k LEFT JOIN `xyft_lottery_sg` s ON k.`issue` = s.`issue` SET k.`number` = s.`number`  where k.`issue` = #{issue}")
    Integer updateKillNumber(@Param("issue") String issue);

    @Select("SELECT s.`issue`, s.`number` FROM `xyft_lottery_sg` s where s.number is not null ORDER BY s.`issue` DESC LIMIT #{size}")
    List<BjpksSgVO> selectLimitDesc(@Param("size") Integer size);

}
