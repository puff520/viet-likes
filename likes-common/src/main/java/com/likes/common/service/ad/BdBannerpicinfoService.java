package com.likes.common.service.ad;

import com.likes.common.model.dto.BdBannerpicinfoDO;
import com.likes.common.model.request.AdRequest;
import com.likes.common.model.response.AdResponse;
import com.likes.common.mybatis.entity.BdBannerpicinfo;
import com.likes.common.service.BaseService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;


public interface BdBannerpicinfoService extends BaseService {

    Page<AdResponse> adList(AdRequest req, RowBounds rowBounds);

    Page<BdBannerpicinfoDO> adsList(BdBannerpicinfo req, RowBounds rowBounds);

    int insertSelective(BdBannerpicinfo record);

    BdBannerpicinfo selectByPrimaryKey(Long bannerpicid);

    int updateByPrimaryKey(BdBannerpicinfo record);

    int updateByPrimaryKeySelective(BdBannerpicinfo record);
}
