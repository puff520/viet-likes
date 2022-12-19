package com.likes.common.service.sys;

import com.github.pagehelper.PageInfo;
import com.likes.common.model.LoginUser;
import com.likes.common.model.SysOperlog;
import com.likes.common.model.dto.sys.SysRecordDO;
import com.likes.common.model.request.SysOperLogRequest;
import com.likes.common.model.request.SysRecordRequest;
import com.likes.common.model.response.SysOperLogResponse;

public interface SysOperLogService {

    boolean batchDel(String ids, LoginUser loginUser);

    PageInfo<SysOperLogResponse> list(SysOperLogRequest req);
}
