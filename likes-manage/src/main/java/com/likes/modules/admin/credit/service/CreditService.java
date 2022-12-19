package com.likes.modules.admin.credit.service;


import com.github.pagehelper.PageInfo;
import com.likes.common.model.LoginUser;
import com.likes.common.model.TaskDetail;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.request.CreditRequest;
import com.likes.common.model.request.TaskRequest;
import com.likes.common.mybatis.entity.MemberCredit;

public interface CreditService {

    boolean operation(CreditRequest request, LoginUser loginUser);


    Integer selectCreditByMemNo(String  memNo);

}
