package com.likes.common.service.member.impl;

import com.likes.common.mybatis.entity.MemHotsearch;
import com.likes.common.mybatis.mapperext.member.MemHotsearchMapperExt;
import com.likes.common.service.member.MemHotsearchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemHotsearchServiceImpl implements MemHotsearchService {

    @Resource
    private MemHotsearchMapperExt memHotsearchMapperExt;

    /**
     * 搜索热词
     */
    @Override
    public MemHotsearch find(MemHotsearch m) {
        return memHotsearchMapperExt.find(m);
    }

    /**
     * 更新搜索次数
     */
    @Override
    public void updatenum(MemHotsearch memHotsearch) {
        memHotsearchMapperExt.updatenum(memHotsearch);
    }

    /**
     * 搜索热词列表
     */
    @Override
    public List<MemHotsearch> getHotSearch(MemHotsearch memHotsearch) {
        return memHotsearchMapperExt.getHotSearch(memHotsearch);
    }
}
