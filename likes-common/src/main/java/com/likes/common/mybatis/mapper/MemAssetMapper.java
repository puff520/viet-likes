package com.likes.common.mybatis.mapper;

import com.likes.common.model.vo.AssetProductVO;
import com.likes.common.mybatis.entity.MemAsset;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface MemAssetMapper extends Mapper<MemAsset> {

    List<AssetProductVO> assetList(String accno);

    int updateMemAssetAmount(BigDecimal amount, Long assetCfgId, String accno,Date takeTime,Date afterTakeTime);

    int updateMemAssetEarn(BigDecimal amount, BigDecimal yesterdayEarn, Long assetCfgId, String accno);


    int updateMemYuebaoEarn(BigDecimal amount, BigDecimal yesterdayEarn, String accno);

    int updateMemAssetTakeTime(Date takeTime, Long assetCfgId, String accno);

    @Select("SELECT\n" +
            "\tcount( 1 ) \n" +
            "FROM\n" +
            "\tmem_asset_record \n" +
            "WHERE\n" +
            "\tasset_cfg_id = #{assetCfgId} \n" +
            "\tAND accno = #{accno} \n" +
            "\tAND change_type = 1  \n" +
            "\tAND create_time BETWEEN CONCAT( CURDATE( ), ' 00:00:00' ) \n" +
            "\tAND CONCAT( CURDATE( ), ' 23:59:59' ) ")
    int countInto(Long assetCfgId, String accno);

    @Select("SELECT\n" +
            "\tcount( 1 ) \n" +
            "FROM\n" +
            "\tmem_asset_record \n" +
            "WHERE\n" +
            "\tasset_cfg_id = #{assetCfgId} \n" +
            "\tAND accno = #{accno} \n" +
            "\tAND change_type = 2  \n" +
            "\tAND create_time BETWEEN CONCAT( CURDATE( ), ' 00:00:00' ) \n" +
            "\tAND CONCAT( CURDATE( ), ' 23:59:59' ) ")
    int countTake(Long assetCfgId, String accno);
}


