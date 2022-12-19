package com.likes.common.mybatis.mapperbean;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface TxffcBeanMapper {

    //@Select("SELECT c.* from txffc_lottery_sg c order by c.issue DESC limit #{limit}")
    //List<TxffcLotterySg> selectDesc(@Param("limit") int limit);

    @Update("UPDATE `txffc_recommend` r LEFT JOIN `txffc_lottery_sg` s ON r.`issue` = s.`issue` SET r.`open_number` = CONCAT(s.`wan`, ',', s.`qian`, ',', s.`bai`, ',', s.`shi`, ',', s.`ge`) where r.`issue` = #{issue}")
    Integer updateRecommend(@Param("issue") String issue);

    @Update("UPDATE `txffc_kill_number` k LEFT JOIN `txffc_lottery_sg` s ON k.`issue` = s.`issue` SET k.`open_one` = s.`wan`, k.`open_two` = s.`qian`, k.`open_three` = s.`bai`, k.`open_four` = s.`shi`, k.`open_five` = s.`ge`")
    Integer updateKillNumber();

}
