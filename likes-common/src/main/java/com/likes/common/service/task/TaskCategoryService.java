package com.likes.common.service.task;


import com.github.pagehelper.PageInfo;
import com.likes.common.model.LoginUser;
import com.likes.common.model.TaskCategoryDetail;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.request.TaskCategoryRequest;
import com.likes.common.mybatis.entity.TaskCategory;

import java.util.List;
import java.util.Map;

public interface TaskCategoryService {

    boolean createTaskCategory(TaskCategoryRequest request, LoginUser loginUser);

    boolean deleteTaskCategory(Integer taskId, LoginUser loginUser);

    boolean updateTaskCategory(TaskCategoryRequest request, LoginUser loginUser);

    TaskCategoryDetail TaskCategoryDetail(Integer bankid);

    PageInfo<TaskCategory> taskCategoryList(TaskCategoryRequest request, PageBounds page);

    Map<String,Object>  categoryList(String language);

    boolean bindMemRelated(TaskCategoryRequest request, LoginUser loginUser);

    boolean  isBindRelated(TaskCategoryRequest request, LoginUser loginUser);

    String findBindName(TaskCategoryRequest request, LoginUser loginUser);
}
