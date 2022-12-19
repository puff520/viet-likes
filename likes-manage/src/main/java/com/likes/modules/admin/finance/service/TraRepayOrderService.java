package com.likes.modules.admin.finance.service;


import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.TraRepayOrderQuery;

public interface TraRepayOrderService {

    PageResult orderList(TraRepayOrderQuery query, PageBounds page);
}
