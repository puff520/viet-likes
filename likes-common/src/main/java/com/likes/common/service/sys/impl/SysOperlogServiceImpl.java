package com.likes.common.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.likes.common.model.LoginUser;
import com.likes.common.model.SysOperlog;
import com.likes.common.model.request.SysOperLogRequest;
import com.likes.common.model.response.SysOperLogResponse;
import com.likes.common.mybatis.mapper.SysOperlogMapper;
import com.likes.common.service.sys.SysOperLogService;
import com.likes.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SysOperlogServiceImpl implements SysOperLogService {

    private static final Logger logger = LoggerFactory.getLogger(SysOperlogServiceImpl.class);
    @Resource
    private HttpServletRequest request;
    @Resource
    private SysOperlogMapper sysOperlogMapper;


    @Override
    public PageInfo<SysOperLogResponse> list(SysOperLogRequest req) {
        List<SysOperLogResponse> pageList = new ArrayList<>();
        Example example = new Example(SysOperlog.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(req.getAcclogin())) {
            criteria.andLike("acclogin", "%" + req.getAcclogin() + "%");
        }
        if (StringUtils.isNotBlank(req.getOperationName())) {
            criteria.andEqualTo("operationName", req.getOperationName());
        }
        if (req.getStartTime() != null) {
            criteria.andGreaterThanOrEqualTo("createTime", req.getStartTime());
        }
        if (req.getEndTime() != null) {
            criteria.andLessThanOrEqualTo("createTime", req.getEndTime());
        }
        long  total =  PageHelper.count(()->sysOperlogMapper.selectByExample(example));
        PageHelper.startPage(req.getPageNo(), req.getPageSize());
        List<SysOperlog> list = sysOperlogMapper.selectByExample(example);
        list.forEach(item -> {
            SysOperLogResponse sysOperLogResponse = new SysOperLogResponse();
            BeanUtils.copyProperties(item, sysOperLogResponse);
            pageList.add(sysOperLogResponse);
        });
        PageInfo<SysOperLogResponse> pageInfo = new PageInfo<SysOperLogResponse>(pageList);
        pageInfo.setTotal(total);
        return pageInfo;
    }

    @Override
    public boolean batchDel(String ids, LoginUser loginUser) {
        String[] idArray = ids.split(",");
        List<String> strList = Stream.of(idArray).collect(Collectors.toList());
        List<Integer> idList = strList.stream().map(Integer::parseInt).collect(Collectors.toList());
        Example example = new Example(SysOperlog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", idList);
        int row = sysOperlogMapper.deleteByExample(example);
        return row > 0;
    }


}
