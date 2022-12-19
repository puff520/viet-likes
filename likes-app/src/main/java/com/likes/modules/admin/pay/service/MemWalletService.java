package com.likes.modules.admin.pay.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.mybatis.entity.DzCoin;
import com.uduncloud.sdk.domain.Trade;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface MemWalletService {


    void checkAddress(String mainCoinType, String moneyAddress);

   DzCoin getCoinType(String coinName);

   String getMemMoneyAddress(String coinName, LoginUser loginUser);
   List<DzCoin> coinList(LoginUser loginUser);

   boolean  submitRecharge(String coinName, BigDecimal amount, String moneyAddress,LoginUser loginUser);
   boolean  submitWithdraw(String coinName,String businessId, BigDecimal amount, String moneyAddress,LoginUser loginUser);

   String udunCallBack(Trade trade);

   void modifyWithdrawStatus(Trade trade);

   void withdrawArrived(Trade trade);




}
