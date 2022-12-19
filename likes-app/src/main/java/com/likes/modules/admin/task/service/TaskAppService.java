package com.likes.modules.admin.task.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.likes.common.enums.task.TaskOrderTypeEnum;
import com.likes.common.model.*;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.request.ReceiveTaskRequest;
import com.likes.common.model.request.SubmitTaskRequest;
import com.likes.common.model.request.TaskOrderRequest;
import com.likes.common.model.request.TaskRequest;
import com.likes.common.mybatis.entity.Task;
import com.likes.common.mybatis.entity.TaskOrder;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TaskAppService {

    PageInfo<TaskAppDto> taskAppList(TaskRequest request,LoginUser loginUser);

    PageInfo<TaskAppDto> myPubtaskList(TaskRequest request);

    TaskAppDetail taskAppDetail(Long taskId);

    List<TaskOrderDetail>  queryMemberOrderList(LoginUser LoginUser, TaskOrderRequest request);

    PageInfo<WaitReceiveDto>  waitReceiveList(LoginUser LoginUser, Integer status,Integer pageNo,Integer pageSize);

    boolean  reviceTask(LoginUser loginUser , ReceiveTaskRequest request);

    boolean  submitTask(LoginUser loginUser , SubmitTaskRequest request);

}
