package com.likes.common.mybatis.mapperext;

import com.likes.common.model.dto.BdBannerseatDO;
import com.likes.common.mybatis.entity.BdBannerseat;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BdBannerseatMapperExt {

    Page<BdBannerseatDO> getList(BdBannerseat req, RowBounds rowBounds);

    BdBannerseat getRepeat(BdBannerseat bannerseat);
}
