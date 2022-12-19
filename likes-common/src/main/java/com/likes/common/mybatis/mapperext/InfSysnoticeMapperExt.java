package com.likes.common.mybatis.mapperext;

import com.likes.common.model.dto.InfSysnoticeDO;
import com.likes.common.mybatis.entity.InfSysnotice;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface InfSysnoticeMapperExt {

    List<InfSysnoticeDO> getInfSysnoticeList(Integer type);

    Page<InfSysnotice> getList(InfSysnotice infSysnotice, RowBounds rowBounds);

    void updateAppWorkStatus(Integer type);

    List<InfSysnoticeDO> selectNoticeList(Integer type);

    List<InfSysnoticeDO> selectAPPNoticeList(Integer type);
}
