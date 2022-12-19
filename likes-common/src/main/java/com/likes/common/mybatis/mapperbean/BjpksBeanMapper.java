package com.likes.common.mybatis.mapperbean;

import com.likes.common.model.vo.BjpksSgVO;
import com.likes.common.mybatis.entity.BjpksLotterySg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BjpksBeanMapper {

    @Select("SELECT b.number FROM bjpks_lottery_sg b where b.time like #{date} and b.number is not null")
    List<String> selectNumberByDate(@Param("date") String date);

    @Select("SELECT b.number FROM bjpks_lottery_sg b where b.time like #{date} and b.number is not null order by ideal_time DESC")
    List<String> selectNumberByDateDesc(@Param("date") String date);

    @Select("SELECT b.number FROM bjpks_lottery_sg b where b.number is not null ORDER BY b.ideal_time desc limit #{issue}")
    List<String> selectNumberLimitDesc(@Param("issue") Integer issue);

    @Select("SELECT b.id, b.issue, b.number, b.time FROM bjpks_lottery_sg b where b.time like #{date} and b.number is not null order by b.ideal_time desc")
    List<BjpksLotterySg> selectByDateDesc(@Param("date") String date);

//    @Select("SELECT b.issue, b.number FROM bjpks_lottery_sg b where b.number is not null ORDER BY b.ideal_time desc limit #{issue}")
//    List<BjpksSgVO> selectLimitDesc(@Param("issue") Integer issue);

    @Update("UPDATE `bjpks_recommend` r" +
            "  LEFT JOIN `bjpks_lottery_sg` s ON r.`issue` = s.`issue` " +
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

    @Update("UPDATE `bjpks_kill_number` k LEFT JOIN `bjpks_lottery_sg` s ON k.`issue` = s.`issue` SET k.`number` = s.`number`")
    Integer updateKillNumber();

    @Select("SELECT s.`issue` FROM ${tableName} s where s.ideal_time > #{date} ORDER BY s.`issue` ASC LIMIT 0,1")
    List<String> selectByTableName(@Param("tableName") String tableName, @Param("date") String date);


    @Select("SELECT b.issue, b.number FROM bjpks_lottery_sg b where b.number is not null ORDER BY b.ideal_time desc limit #{issue}")
    List<BjpksSgVO> selectLimitDesc(@Param("issue") Integer issue);
}
