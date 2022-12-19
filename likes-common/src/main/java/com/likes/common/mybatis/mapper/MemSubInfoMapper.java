package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.MemSubInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface MemSubInfoMapper extends Mapper<MemSubInfo> {

    int insertBatch(@Param("list") Set<MemSubInfo> list);

    @Select("select count(1) from mem_sub_info where accno = #{accno}")
    int countAccNo(String accno);

    MemSubInfo selectMemSubInfoByAccNo(String accno);


    int updateTop1(MemSubInfo memSubInfo);

    int updateTop2(MemSubInfo memSubInfo);

    int updateTop3(MemSubInfo memSubInfo);

    @Select("INSERT INTO `likes`.`batch_log`(`accno`, `create_time`) VALUES (#{accno}, #{createTime})   ON DUPLICATE KEY UPDATE create_time = VALUES(create_time) ")
    void saveBatchLog(String accno, Date createTime);

}
