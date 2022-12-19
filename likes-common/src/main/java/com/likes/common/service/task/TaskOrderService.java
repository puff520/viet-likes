package com.likes.common.service.task;


import com.github.pagehelper.PageInfo;
import com.likes.common.model.LoginUser;
import com.likes.common.model.TaskOrderDetail;
import com.likes.common.model.TaskOrderListDetail;
import com.likes.common.model.TaskOrderReviewDetail;
import com.likes.common.model.request.TaskOrderRequest;

public interface TaskOrderService {

    TaskOrderDetail askOrderDetail(Long taskOrderId);

    TaskOrderReviewDetail taskOrderReviewDetail(Long taskOrderId);

    PageInfo<TaskOrderListDetail> taskOrderList(TaskOrderRequest request);
}
