package com.likes.common.mybatis.mapper;

import com.likes.common.model.TaskAppDetail;
import com.likes.common.model.TaskAppDto;
import com.likes.common.model.TaskDetail;
import com.likes.common.model.dto.MemDoTaskDTO;
import com.likes.common.model.request.TaskRequest;
import com.likes.common.mybatis.entity.Task;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TaskMapper extends Mapper<Task> {


    @Select("select *  from dz_task where id  = #{taskId}")
    Task selectTaskById(@Param("taskId") Long taskId);

    List<TaskDetail> selectTaskList(TaskRequest request);

    List<TaskAppDto> selectTaskAppList(TaskRequest request);


    TaskAppDetail selectTaskAppDetail( @Param("taskId") Long taskId);


    @Select("select count(1) from dz_task where task_type =1")
    int countMustTask();

    Boolean deleteTasks(@Param("ids") List<Integer> ids);


    MemDoTaskDTO getDoTaskInf(@Param("startTime") String startTime, @Param("endTime") String endTime);

    Integer getDoTaskMemberNum(@Param("startTime") String startTime,@Param("isnew") Boolean isnew);

    @Update("update dz_task set apply_num  = apply_num +1 ,surplus_num  = surplus_num -1  where id = #{id} ")
    int  updateApplyNum(@Param("id") Long id);

    @Update("update dz_task set `status`  = 3  where id = #{id} ")
    int  updateStatus(@Param("id") Long id );






}
