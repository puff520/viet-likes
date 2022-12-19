package com.likes.common.mybatis.mapperext;

import com.likes.common.model.request.AdRequest;
import com.likes.common.model.response.AdResponse;
import com.likes.common.model.dto.BdBannerpicinfoDO;
import com.likes.common.mybatis.entity.BdBannerpicinfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

public interface BdBannerpicinfoMapperExt {

    Page<AdResponse> adList(AdRequest req, RowBounds rowBounds);

    Page<BdBannerpicinfoDO> adsList(BdBannerpicinfo req, RowBounds rowBounds);
}
