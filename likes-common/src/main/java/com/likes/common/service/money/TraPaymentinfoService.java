package com.likes.common.service.money;

import com.likes.common.mybatis.entity.TraPaymentinfo;
import com.likes.common.mybatis.entity.TraPaymentinfoExample;

import java.util.List;

public interface TraPaymentinfoService {

    int countByExample(TraPaymentinfoExample example);

    int deleteByExample(TraPaymentinfoExample example);

    int deleteByPrimaryKey(Long payid);

    int insert(TraPaymentinfo record);

    int insertSelective(TraPaymentinfo record);

    TraPaymentinfo selectOneByExample(TraPaymentinfoExample example);

    List<TraPaymentinfo> selectByExample(TraPaymentinfoExample example);

    TraPaymentinfo selectByPrimaryKey(Long payid);

    int updateByExampleSelective(TraPaymentinfo record, TraPaymentinfoExample example);

    int updateByExample(TraPaymentinfo record, TraPaymentinfoExample example);

    int updateByPrimaryKeySelective(TraPaymentinfo record);

    int updateByPrimaryKey(TraPaymentinfo record);

    TraPaymentinfo findByOrderno(String orderid);
}
