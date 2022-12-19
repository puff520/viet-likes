package com.likes.common.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.likes.common.model.LoginUser;
import com.likes.common.model.MemRelatedBind;
import com.likes.common.model.TaskCategoryDetail;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.request.TaskCategoryRequest;
import com.likes.common.mybatis.entity.TaskCategory;
import com.likes.common.mybatis.mapper.MemRelatedBindMapper;
import com.likes.common.mybatis.mapper.TaskCategoryMapper;
import com.likes.common.service.task.TaskCategoryService;
import com.likes.common.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class TaskCategoryServiceImpl implements TaskCategoryService {

    @Resource
    private TaskCategoryMapper taskCategoryMapper;

    @Resource
    private MemRelatedBindMapper memRelatedBindMapper;

    @Override
    public boolean createTaskCategory(TaskCategoryRequest request, LoginUser loginUser) {
        TaskCategory taskCategory = new TaskCategory();
        BeanUtils.copyProperties(request, taskCategory);
        taskCategory.setCreateTime(new Date());
        taskCategory.setCreateUser(loginUser.getAccno());
        int row = taskCategoryMapper.insertSelective(taskCategory);
        return row > 0;
    }

    @Override
    public boolean deleteTaskCategory(Integer taskId, LoginUser loginUser) {
        return taskCategoryMapper.deleteByPrimaryKey(taskId) > 0;
    }

    @Override
    public boolean updateTaskCategory(TaskCategoryRequest request, LoginUser loginUser) {
        TaskCategory taskCategory = new TaskCategory();
        BeanUtils.copyProperties(request, taskCategory);
        taskCategory.setUpdateUser(loginUser.getAccno());
        taskCategory.setUpdateTime(new Date());
        return taskCategoryMapper.updateByPrimaryKeySelective(taskCategory) > 0;
    }

    @Override
    public TaskCategoryDetail TaskCategoryDetail(Integer bankid) {
        TaskCategory taskCategory = new TaskCategory();
        taskCategory.setId(bankid);
        TaskCategory taskCategoryVo = taskCategoryMapper.selectByPrimaryKey(taskCategory);
        TaskCategoryDetail taskCategoryDetail = new TaskCategoryDetail();
        BeanUtils.copyProperties(taskCategoryVo, taskCategoryDetail);
        return taskCategoryDetail;
    }

    @Override
    public PageInfo<TaskCategory> taskCategoryList(TaskCategoryRequest request, PageBounds page) {
        TaskCategory taskCategory = new TaskCategory();
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<TaskCategory> list = taskCategoryMapper.select(taskCategory);
        list.sort(Comparator.comparingInt(TaskCategory::getSort));
        PageInfo<TaskCategory> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Map<String, Object> categoryList(String language) {
        Map<String, Object> resultMap = new HashMap<>();
        TaskCategory taskCategory = new TaskCategory();
        taskCategory.setStatus(1);
        List<TaskCategory> list = taskCategoryMapper.select(taskCategory);
        list.forEach(item -> {
                if(StringUtils.isNotBlank(language)){
                    if (language.equals("en")) {
                        item.setName(item.getEnName());
                        item.setDescription(item.getEnDescription());
                    } else if (language.equals("sp")) {
                        item.setName(item.getSpName());
                        item.setDescription(item.getSpDescription());
                    } else if (language.equals("ab")) {
                        item.setName(item.getAbName());
                        item.setDescription(item.getAbDescription());
                    } else if (language.equals("fn")) {
                        item.setName(item.getFnName());
                        item.setDescription(item.getFnDescription());

                    } else if (language.equals("in")) {
                        item.setName(item.getInName());
                        item.setDescription(item.getInDescription());
                    }
                }
        });
        list.sort(Comparator.comparingInt(TaskCategory::getSort));
        resultMap.put("ordinaryTask", list);
        return resultMap;
    }

    @Override
    public boolean bindMemRelated(TaskCategoryRequest request, LoginUser loginUser) {
        MemRelatedBind memRelatedBind = new MemRelatedBind();
        memRelatedBind.setCategoryId(request.getId());
        memRelatedBind.setMemAccno(loginUser.getAccno());
        MemRelatedBind bindVo = memRelatedBindMapper.selectOne(memRelatedBind);
        if (bindVo != null) {
            memRelatedBind.setUpdateUser(loginUser.getAccno());
            memRelatedBind.setCategoryNo(request.getCategoryNo());
            memRelatedBind.setUpdateTime(new Date());
            memRelatedBind.setBindId(bindVo.getBindId());
            return memRelatedBindMapper.updateByPrimaryKeySelective(memRelatedBind) > 0;

        }
        memRelatedBind.setCreateUser(loginUser.getAccno());
        memRelatedBind.setCategoryNo(request.getCategoryNo());
        memRelatedBind.setCreateTime(new Date());
        return memRelatedBindMapper.insertSelective(memRelatedBind) > 0;
    }

    @Override
    public boolean isBindRelated(TaskCategoryRequest request, LoginUser loginUser) {
        MemRelatedBind memRelatedBind = new MemRelatedBind();
        memRelatedBind.setCategoryId(request.getId());
        memRelatedBind.setMemAccno(loginUser.getAccno());
        MemRelatedBind bindVo = memRelatedBindMapper.selectOne(memRelatedBind);
        return bindVo != null;
    }

    @Override
    public String findBindName(TaskCategoryRequest request, LoginUser loginUser) {
        MemRelatedBind memRelatedBind = new MemRelatedBind();
        memRelatedBind.setCategoryId(request.getId());
        memRelatedBind.setMemAccno(loginUser.getAccno());
        MemRelatedBind bindVo = memRelatedBindMapper.selectOne(memRelatedBind);
        if (null == bindVo || StringUtils.isBlank(bindVo.getCategoryNo())) {
            return null;
        }
        return bindVo.getCategoryNo();
    }

}
