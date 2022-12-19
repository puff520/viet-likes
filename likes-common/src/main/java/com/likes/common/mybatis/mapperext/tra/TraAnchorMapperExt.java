package com.likes.common.mybatis.mapperext.tra;

import com.likes.common.model.dto.TraAnchorDO;
import com.likes.common.model.request.FamilyIncarnateRequest;
import com.likes.common.model.vo.TraAnchorVO;
import com.likes.common.mybatis.entity.TraAnchor;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface TraAnchorMapperExt {

	void insertMany(List<TraAnchor> allTraAnchorList);

	List<TraAnchor> getListByApycid(Long apycid);

	Page<TraAnchorDO> getincarnateHistoryByFamily(FamilyIncarnateRequest req, RowBounds rowBounds);

	Page<TraAnchorDO> getincarnateHistoryByApycid(FamilyIncarnateRequest req, RowBounds rowBounds);

	List<TraAnchorDO> getAnchorIncomeList(FamilyIncarnateRequest req);

	Page<TraAnchorVO> getTraAnchorDOList(List<Long> objects, RowBounds toRowBounds);

	Page<TraAnchorVO> getTraAnchorList(List<Long> objects, RowBounds toRowBounds);
}