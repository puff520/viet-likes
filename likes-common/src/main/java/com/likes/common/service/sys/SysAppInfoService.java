package com.likes.common.service.sys;

import com.likes.common.model.dto.AppinfoDO;
import com.likes.common.model.dto.sys.SysAppInfoDO;
import com.likes.common.model.request.SysAppInfoReq;
import com.likes.common.mybatis.entity.SysAppInfo;
import com.likes.common.mybatis.entity.SysAppInfoExample;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysAppInfoService {

    /**
     * 直接获取最新版的APP版本的下载链接
     *
     * @return
     */
    List<AppinfoDO> getMaxXin();

    Page<SysAppInfoDO> getList(SysAppInfo req, RowBounds rowBounds);

    void noNew(Integer appType);

    SysAppInfo getSysAppInfoLast(SysAppInfoReq req);

    int insertSelective(SysAppInfo record);

    SysAppInfo selectByPrimaryKey(Long appid);

    int updateByPrimaryKeySelective(SysAppInfo record);

}
