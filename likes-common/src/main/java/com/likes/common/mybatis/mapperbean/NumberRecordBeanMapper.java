package com.likes.common.mybatis.mapperbean;

import com.likes.common.model.vo.GatherFailureRecordVO;
import com.likes.common.model.vo.lottery.NoLotteryRecordVO;
import com.likes.common.model.vo.NumberAbnormalRecordVO;
import com.likes.common.mybatis.mapperbean.provider.startlotto.NumberRecordDynaSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface NumberRecordBeanMapper {

    /**
     * 根据条件查询开奖号码跳开记录
     * @param pageNo
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param status
     * @param lotteryId
     * @param issue
     * @return
     */
    @SelectProvider(type = NumberRecordDynaSqlProvider.class, method = "listNoLotteryRecord")
    List<NoLotteryRecordVO> listNoLotteryRecord(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("status") String status, @Param("lotteryId") Integer lotteryId, @Param("issue") String issue);

    /**
     * 根据条件查询开奖号码跳开记录数量
     * @param startTime
     * @param endTime
     * @param status
     * @param lotteryId
     * @param issue
     * @return
     */
    @SelectProvider(type = NumberRecordDynaSqlProvider.class, method = "countNoLotteryRecord")
    int countNoLotteryRecord(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("status") String status, @Param("lotteryId") Integer lotteryId, @Param("issue") String issue);

    /**
     * 根据条件查询开奖号码采集失败记录
     * @param pageNo
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param source
     * @param lotteryId
     * @param issue
     * @return
     */
    @SelectProvider(type = NumberRecordDynaSqlProvider.class, method = "listGatherFailureRecord")
    List<GatherFailureRecordVO> listGatherFailureRecord(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("source") String source, @Param("lotteryId") Integer lotteryId, @Param("issue") String issue);

    /**
     * 根据条件查询开奖号码采集失败记录数量
     * @param startTime
     * @param endTime
     * @param source
     * @param lotteryId
     * @param issue
     * @return
     */
    @SelectProvider(type = NumberRecordDynaSqlProvider.class, method = "countGatherFailureRecord")
    int countGatherFailureRecord(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("source") String source, @Param("lotteryId") Integer lotteryId, @Param("issue") String issue);

    /**
     * 根据条件查询开奖号码异常记录
     * @param pageNo
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param source
     * @param lotteryId
     * @param issue
     * @return
     */
    @SelectProvider(type = NumberRecordDynaSqlProvider.class, method = "listNumberAbnormalRecord")
    List<NumberAbnormalRecordVO> listNumberAbnormalRecord(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("source") String source, @Param("lotteryId") Integer lotteryId, @Param("issue") String issue);

    /**
     * 根据条件查询开奖号码异常记录
     * @param startTime
     * @param endTime
     * @param source
     * @param lotteryId
     * @param issue
     * @return
     */
    @SelectProvider(type = NumberRecordDynaSqlProvider.class, method = "countNumberAbnormalRecord")
    int countNumberAbnormalRecord(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("source") String source, @Param("lotteryId") Integer lotteryId, @Param("issue") String issue);

}
