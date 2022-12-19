package com.likes.common.mybatis.mapperext;

import com.likes.common.model.vo.lottery.BetRestrictVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RestrictMapperExt {

	List<BetRestrictVo> queryRestrictList(@Param("lotteryId") Integer lotteryId, @Param("number") Integer number);

}
