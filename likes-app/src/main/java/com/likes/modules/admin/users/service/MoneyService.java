package com.likes.modules.admin.users.service;

import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.UsersRequest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public interface MoneyService {

    Map<String, Object> myLevel(String accno);

    Map<String, Object> moneyAddress(String accno);

    PageResult myIncomeAndExpensesList(UsersRequest req, PageBounds page);

    HashMap<String, Object> myPageCount(String accno);



}
