package com.likes.common.service.sys;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.InfSysnoticeDO;
import com.likes.common.mybatis.entity.InfSysnotice;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface InfSysnoticeService {
    PageResult getList(InfSysnotice infSysnotice, PageBounds page);

    Long doSave(InfSysnotice infSysnotice, LoginUser loginUser);

    Long doUpdate(InfSysnotice infSysnotice, LoginUser loginUser);

    String doDel(InfSysnotice infSysnotice, LoginUser loginUser);

    String doWorkStatus(InfSysnotice infSysnotice);

    List<InfSysnoticeDO> getInfSysnoticeList(Integer type);

    Page<InfSysnotice> getList(InfSysnotice infSysnotice, RowBounds rowBounds);

    void updateAppWorkStatus(Integer type);

    List<InfSysnoticeDO> selectNoticeList(Integer type);

    List<InfSysnoticeDO> selectAPPNoticeList(Integer type);
}
