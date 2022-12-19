package com.likes.common.mybatis.mapper;

import com.github.pagehelper.Page;
import com.likes.common.model.vo.YuebaoRecordVO;
import com.likes.common.mybatis.entity.MemYuebaoRecord;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;

public interface MemYuebaoRecordMapper extends Mapper<MemYuebaoRecord> {

    Page<YuebaoRecordVO> operateList(String accno, Integer changeType, RowBounds rowBounds);


    @Select(" SELECT\n" +
            "\t accno \n" +
            "FROM\n" +
            "\tmem_yuebao_record \n" +
            "WHERE\n" +
            "\tid IN (  " +
            "SELECT max(id)  from mem_yuebao_record WHERE accno not in (" +
            " SELECT accno  from mem_asset_record WHERE change_type = 3" +
            " and asset_cfg_id = #{assetCfgId} " +
            "  and create_time BETWEEN #{beginTime} AND #{endTime} " +
            ") and asset_cfg_id = #{assetCfgId}" +
            " AND create_time < #{beginTime} group by accno ) and after_amount >= 1  ")
    List<String> yueBaoAccList(String beginTime, String endTime,Long assetCfgId);


    @Select(" SELECT count(1)  from mem_asset_record WHERE change_type = 3" +
            " and accno =#{accno} and  asset_cfg_id = #{assetCfgId}" +
            "  and create_time BETWEEN #{beginTime} AND #{endTime}")
    Integer countYuebaoDividend(String beginTime, String endTime,String accno,Long assetCfgId);


    @Select("select IFNULL(after_amount,0.000) from  mem_yuebao_record where create_time <= #{timeStr} " +
            " and  asset_cfg_id = #{assetCfgId} and accno = #{accno} order by id desc limit 1 ")
    BigDecimal yesterdayAmount(String accno,Long assetCfgId,String timeStr);


    @Select("select IFNULL(change_amount,0.000) from mem_yuebao_record where change_type = 3" +
            "  and create_time BETWEEN #{beginTime} AND #{endTime}" +
            " and  asset_cfg_id = #{assetCfgId} and accno = #{accno}  and change_type = 3")
    BigDecimal yesterdayEarn(String accno,Long assetCfgId, String beginTime, String endTime);


    @Select("select IFNULL(sum(change_amount),0.000) from mem_yuebao_record where create_time >= #{beginTime} " +
            " and create_time <= #{endTime} and accno =#{accno}" +
            "  and  asset_cfg_id = #{assetCfgId} and change_type = 3 ")
    BigDecimal selectIncomeByTime(String accno,Long assetCfgId, String beginTime, String endTime);


    @Select("select IFNULL(after_amount,0.000) from  mem_yuebao_record where create_time <= #{timeStr} " +
            " and  asset_cfg_id = #{assetCfgId}  and accno =#{accno} and change_type = 2 order by id desc limit 1 ")
    BigDecimal intoAmount(String accno,Long assetCfgId,String timeStr);

}
