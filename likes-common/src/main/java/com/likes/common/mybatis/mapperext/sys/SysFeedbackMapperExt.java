package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.dto.sys.SysFeedbackDO;
import com.likes.common.mybatis.entity.SysFeedback;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

public interface SysFeedbackMapperExt {

    Page<SysFeedbackDO> getList(SysFeedback req, RowBounds toRowBounds);

    int updateSysFeedback(SysFeedback sysFeedback);
}
