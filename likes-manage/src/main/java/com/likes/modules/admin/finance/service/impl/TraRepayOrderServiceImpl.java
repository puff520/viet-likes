package com.likes.modules.admin.finance.service.impl;

import com.likes.common.model.TraRepayOrderDO;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.TraRepayOrderQuery;

import com.likes.common.mybatis.mapper.TraRepayorderMapper;
import com.likes.modules.admin.finance.service.TraRepayOrderService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TraRepayOrderServiceImpl implements TraRepayOrderService {

    @Resource
    private TraRepayorderMapper traRepayorderMapper;

    @Override
    public PageResult orderList(TraRepayOrderQuery query, PageBounds page) {
        if (query != null) {
            query.setOrderno(query.getOrderno() == null ? null : query.getOrderno().trim());
            query.setAcclogin(query.getAcclogin() == null ? null : query.getAcclogin().trim());
            query.setNickname(query.getNickname() == null ? null : query.getNickname().trim());
        }
        Page<TraRepayOrderDO> list = traRepayorderMapper.selectOrderList(query, page.toRowBounds());
        return PageResult.getPageResult(page, list);
    }
}
