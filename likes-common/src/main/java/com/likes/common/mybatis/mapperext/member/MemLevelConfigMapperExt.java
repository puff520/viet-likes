package com.likes.common.mybatis.mapperext.member;


import com.likes.common.model.dto.member.MemLevelConfigDto;
import com.likes.common.mybatis.entity.MemLevelConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MemLevelConfigMapperExt {

    /**
     * 依据充值金额 查询出当前等级和下一个等级
     *
     * @param amount
     * @return
     */
    List<MemLevelConfig> selectMemLevelConfigByAmount(Double amount);

    List<MemLevelConfig> selectMemLevlConfigByLevelSeq();

    List<MemLevelConfig> selectMemLevlConfigDownLevelSeq(Integer leveseq);

    List<Map<String, Object>> getMemLevelList();

    /**
     * 按会员标识号查询会员等级配置
     *
     * @param accno
     * @return
     */
    @Select("SELECT mlc.`level` as `level`, mlc.level_seq as levelSeq, mlc.promote_amount AS promoteAmount, mlc.do_task_times as doTaskTimes from mem_level_config  mlc INNER JOIN  mem_level ml on mlc.id = ml.level_config_id WHERE ml.accno = #{accno}" +
            "and ml.expire_time > NOW()  ORDER BY ml.create_time DESC  LIMIT 1 ")
    MemLevelConfig selectMemLevel(@Param("accno") String accno);

    @Select("select mlc.`level` as `level`  from mem_level_config mlc  where mlc.level_seq  = #{levelSeq} ")
    MemLevelConfig selectMemLevelBySeq(@Param("levelSeq") Integer levelSeq);

    MemLevelConfig getNotExpireUserMaxLevelInfoByAccno(String accno);


    List<MemLevelConfigDto> GetMemLevelCountByAccno( );
}
