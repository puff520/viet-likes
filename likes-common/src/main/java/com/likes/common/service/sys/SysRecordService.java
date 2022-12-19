package com.likes.common.service.sys;

import com.github.pagehelper.PageInfo;
import com.likes.common.model.dto.sys.SysRecordDO;
import com.likes.common.model.request.SysRecordRequest;

import java.util.List;

public interface SysRecordService {
    PageInfo<SysRecordDO> getLst(SysRecordRequest req);
}
