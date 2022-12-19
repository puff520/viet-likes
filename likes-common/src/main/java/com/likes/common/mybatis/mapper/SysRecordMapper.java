package com.likes.common.mybatis.mapper;

import com.likes.common.model.SysRecord;
import com.likes.common.model.dto.sys.SysRecordDO;
import com.likes.common.model.request.SysRecordRequest;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRecordMapper extends Mapper<SysRecord> {

    List<SysRecordDO> getLst(SysRecordRequest req);
}
