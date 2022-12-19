package com.likes.common.mybatis.mapperext.tra;

import com.likes.common.mybatis.entity.TraPaymentinfo;

public interface TraPaymentinfoMapperExt {

    TraPaymentinfo findByOrderno(String orderid);
}