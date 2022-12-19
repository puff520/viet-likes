package com.likes.common.mybatis.mapperext;

import com.likes.common.model.vo.chat.ReceiveInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RedPackMapperExt {

	List<ReceiveInfoVO> receiveInfoList(@Param("userId") Integer userId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);


}