package com.likes.common.mybatis.mapperext.tra;

import com.likes.common.model.dto.TraApplyauditDO;
import com.likes.common.mybatis.entity.TraApplyaudit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TraApplyauditMapperExt {


	List<TraApplyauditDO> getByApycid(Long apycid);
	
	List<TraApplyaudit> getListById(Long apyid);
    void doDelJihe(Long apycid);
}