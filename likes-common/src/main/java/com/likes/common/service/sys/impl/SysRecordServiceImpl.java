package com.likes.common.service.sys.impl;

import com.github.pagehelper.PageInfo;
import com.likes.common.model.dto.sys.SysRecordDO;
import com.likes.common.model.request.SysRecordRequest;
import com.likes.common.mybatis.mapper.SysRecordMapper;
import com.likes.common.service.sys.SysRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRecordServiceImpl implements SysRecordService {

    @Resource
    private SysRecordMapper sysRecordMapper;

    @Override
    public PageInfo<SysRecordDO> getLst(SysRecordRequest req) {
        List<SysRecordDO> tmp = sysRecordMapper.getLst(req);
        PageInfo pageInfo = new PageInfo(tmp);
        return  pageInfo;
    }
}
