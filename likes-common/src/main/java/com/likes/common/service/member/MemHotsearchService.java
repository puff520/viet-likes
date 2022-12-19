package com.likes.common.service.member;

import com.likes.common.mybatis.entity.MemHotsearch;
import com.likes.common.service.BaseService;

import java.util.List;

public interface MemHotsearchService extends BaseService {

    /**
     * 搜索热词
     */
    MemHotsearch find(MemHotsearch m);

    /**
     * 更新搜索次数
     */
    void updatenum(MemHotsearch memHotsearch);

    /**
     * 搜索热词列表
     */
    List<MemHotsearch> getHotSearch(MemHotsearch memHotsearch);
}
