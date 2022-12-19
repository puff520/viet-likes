package com.likes.common.service.task;


import com.github.pagehelper.PageInfo;
import com.likes.common.model.LoginUser;
import com.likes.common.model.TaskDetail;
import com.likes.common.model.request.TaskRequest;

public interface TaskService {

    boolean createTask(TaskRequest request, LoginUser loginUser);

    boolean deleteTask(String ids, LoginUser loginUser);

    boolean updateTask(TaskRequest request, LoginUser loginUser);

    boolean openLink(Long taskOrderId, LoginUser loginUser);

    TaskDetail TaskDetail(Long bankid);

    PageInfo<TaskDetail> taskList(TaskRequest request);
}
