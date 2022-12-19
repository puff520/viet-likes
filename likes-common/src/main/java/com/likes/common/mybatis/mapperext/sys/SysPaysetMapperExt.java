package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.request.PaySetRequest;
import com.likes.common.mybatis.entity.SysPayset;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
@Mapper
public interface SysPaysetMapperExt {


	Page<SysPayset> getList(PaySetRequest paySetRequest, RowBounds rowBounds);

	void doAllJingyong(SysPayset sysPayset);

	SysPayset getRepeate(String setname);

	void doAllJingyong2(PaySetRequest sysPayset);

	List<SysPayset> getAllList(PaySetRequest paySetRequest);

	void doUpdate2(SysPayset sysPayset);
	
	/**
	 * 获取当前订单的支付设定
	 * @param orderid
	 * @return
	 */
	SysPayset getOneBySettypeByOrderid(Long orderid);
	SysPayset getUseOne(Integer settype);
}