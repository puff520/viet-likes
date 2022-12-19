package com.likes.common.mybatis.mapper;

import com.likes.common.model.*;
import com.likes.common.model.dto.RankDto;
import com.likes.common.model.request.TaskOrderRequest;
import com.likes.common.mybatis.entity.TaskOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;


public interface TaskOrderMapper extends Mapper<TaskOrder> {


    @Select("select count(1) from dz_task_order where task_id = #{taskId} and mem_no = #{memNo}  limit 1")
    int counMemTaskOrder(@Param("taskId") Long taskId, @Param("memNo") String memNo);


    @Select("select count(1) from dz_task_order where status = 3 and mem_no = #{accno}  and update_time BETWEEN #{dto.jtBeginTime} AND #{dto.jtEnDTime}")
    int countFinishOrderByMemNo(@Param("accno") String accno,@Param("dto")TimeReortDTO dto);

    @Select("select count(1) from dz_task_order where (status = 1  or  status = 2) and mem_no = #{accno}  and create_time BETWEEN #{dto.jtBeginTime} AND #{dto.jtEnDTime} ")
    int countSurplusOrderByMemNo(@Param("accno") String accno,@Param("dto")TimeReortDTO dto);

    @Select("select count(1) from dz_task_order where  mem_no = #{memNo}  and create_time BETWEEN #{beginTime} AND #{enTime} ")
    int counMemTodayTaskOrder(@Param("memNo") String memNo,@Param("beginTime") String beginTime,@Param("enTime") String enTime);

    @Select("select count(0) from dz_task_order where task_id = #{taskId}  and status = 3")
    int completeTaskOrderNumById(@Param("taskId") Long taskId);

    List<WaitReceiveDto> waitReceiveList(@Param("createUser") String createUser, @Param("status") Integer status);


    List<TaskOrderListDetail> taskOrderList(TaskOrderRequest request);

    TaskOrderReviewDetail selectReviewDetail(@Param("taskOrderId") Long taskOrderId);

    @Select("SELECT dto.id from dz_task_order dto   WHERE `status` = 2 AND ( send_status != 1 OR send_status IS NULL ) " +
            " and  dto.update_time >= now() -interval 48 HOUR " +
            " ")
    List<Long> selectWaitReviceIdList();


    @Select("SELECT\n" +
            "\ta.accno,\n" +
            "\ta.taskNum,\n" +
            "\ta.taskAmount,\n" +
            "\ta.create_time,\n" +
            "\tmb.headimg AS image,\n" +
            "\tmb.email AS userName \n" +
            "FROM\n" +
            "\t(\n" +
            "SELECT\n" +
            "\tIFNULL( sum( quantity ), 0.0 ) AS taskAmount,\n" +
            "\tcount( accno ) AS taskNum,\n" +
            "\taccno ,\n" +
            "  create_time\n" +
            "FROM\n" +
            "\tmem_goldchange \n" +
            "WHERE\n" +
            "\tchangetype = 200 \n" +
            "\tand create_time BETWEEN #{beginTime} AND #{endTime} \n" +
            "GROUP BY\n" +
            "\taccno HAVING taskAmount > 10 \n" +
            "\tORDER BY \tcreate_time DESC \n" +
            "\tlimit 50 \n" +
            "\t) a\n" +
            "\tINNER JOIN mem_baseinfo mb ON mb.accno = a.accno")
    List<RankDto> rankJobList(String beginTime,String endTime);


    @Select("SELECT\n" +
            "\ta.accno,\n" +
            "\ta.taskNum,\n" +
            "\ta.taskAmount,\n" +
            "\ta.create_time,\n" +
            "\tmb.headimg AS image,\n" +
            "\tmb.email AS userName \n" +
            "FROM\n" +
            "\t(\n" +
            "SELECT\n" +
            "\tIFNULL( sum( quantity ), 0.0 ) AS taskAmount,\n" +
            "\tcount( accno ) AS taskNum,\n" +
            "\taccno ,\n" +
            "  create_time\n" +
            "FROM\n" +
            "\tmem_goldchange \n" +
            "WHERE\n" +
            "\tchangetype = 301 \n" +
            "\tand create_time BETWEEN #{beginTime} AND #{endTime} \n" +
            "GROUP BY\n" +
            "\taccno \n" +
            "\tORDER BY create_time desc\n" +
            "\tlimit 50 \n" +
            "\t) a\n" +
            "\tINNER JOIN mem_baseinfo mb ON mb.accno = a.accno")
    List<RankDto> registerGiveList(String beginTime,String endTime);


}
