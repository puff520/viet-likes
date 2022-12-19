package com.likes.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class UdunBody {


    private String  merchantId;
    private String  mainCoinType;
    private String  callUrl;


    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMainCoinType() {
        return mainCoinType;
    }

    public void setMainCoinType(String mainCoinType) {
        this.mainCoinType = mainCoinType;
    }

    public String getCallUrl() {
        return callUrl;
    }

    public void setCallUrl(String callUrl) {
        this.callUrl = callUrl;
    }
}
