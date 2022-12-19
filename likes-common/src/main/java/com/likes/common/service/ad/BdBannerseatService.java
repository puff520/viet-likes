package com.likes.common.service.ad;

import com.likes.common.model.dto.BdBannerseatDO;
import com.likes.common.model.response.AdResponse;
import com.likes.common.mybatis.entity.BdBannerseat;
import com.likes.common.service.BaseService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface BdBannerseatService extends BaseService {

    Page<BdBannerseatDO> getList(BdBannerseat req, RowBounds rowBounds);

    BdBannerseat getRepeat(BdBannerseat bannerseat);

    BdBannerseat selectByPrimaryKey(Long bseatid);

    int insertSelective(BdBannerseat record);

    int updateByPrimaryKeySelective(BdBannerseat record);

    List<BdBannerseat> getBseatId(String seatcode);
}
