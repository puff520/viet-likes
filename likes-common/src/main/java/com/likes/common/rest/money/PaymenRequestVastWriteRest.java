package com.likes.common.rest.money;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.model.common.ResultInfo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface PaymenRequestVastWriteRest {
	
	/**
	 * 支付订单
	 * @param data
	 * @return
	 */
	ResultInfo<JSONObject> paymentRequestByUser(Map<String, String> data);
	

}
