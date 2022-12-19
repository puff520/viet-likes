package com.likes.common.service.sys;

import com.likes.common.model.dto.sys.SysFeedbackDO;
import com.likes.common.mybatis.entity.SysFeedback;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

/**
 * ClassName: SysFeedbackService
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/6/19 21:43
 */
public interface SysFeedbackService {

    int insert(SysFeedback sysFeedback);

    int insertSelective(SysFeedback record);

    Page<SysFeedbackDO> getList(SysFeedback req, RowBounds toRowBounds);

    int updateSysFeedback(SysFeedback sysFeedback);

    int updateByPrimaryKey(SysFeedback record);

    int delete(Long id);

    SysFeedback selectByPrimaryKey(Long id);

    void handFeedback();
}
