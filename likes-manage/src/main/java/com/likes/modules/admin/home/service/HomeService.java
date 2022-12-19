package com.likes.modules.admin.home.service;

import com.likes.common.model.dto.NameValueDO;
import com.likes.common.model.dto.report.HomeDataDO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface HomeService {

    Map<String, Object> getPeoples();

    Map<String, Object> getAddressDistributeds();

    List<NameValueDO> getLevelDistributedsTen();

    List<Map<Integer,String>> getNewOrder();

    List<NameValueDO> getLevelDistributedsFive();

    Long getCurrentOnlineNums();

    HomeDataDO manualHomeStatics(String startTime, String endTime);

    BigDecimal getAllUserGoldNum();
}
