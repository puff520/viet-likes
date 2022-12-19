package com.likes.common.service.sys.impl;

import com.likes.common.model.dto.sys.SysFeedbackDO;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.SysFeedback;
import com.likes.common.mybatis.entity.SysFeedbackExample;
import com.likes.common.mybatis.mapper.SysFeedbackMapper;
import com.likes.common.mybatis.mapperext.sys.SysFeedbackMapperExt;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.sys.SysFeedbackService;
import com.likes.common.util.CommonUtils;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: SysFeedbackServiceImpl
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/6/19 21:43
 */
@Service
public class SysFeedbackServiceImpl implements SysFeedbackService {

    @Resource
    private SysFeedbackMapper sysFeedbackMapper;

    @Resource
    private SysFeedbackMapperExt sysFeedbackMapperExt;
    @Resource
    private MemBaseinfoService memBaseinfoService;

    @Override
    public int insert(SysFeedback sysFeedback) {
        if (null == sysFeedback) {
            return 0;
        }
        return sysFeedbackMapper.insert(sysFeedback);
    }

    @Override
    public int insertSelective(SysFeedback record) {
        if (null == record) {
            return 0;
        }
        return sysFeedbackMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysFeedback record) {
        if (null == record) {
            return 0;
        }
        return sysFeedbackMapper.updateByPrimaryKey(record);
    }

    @Override
    public int delete(Long id) {
        if (null == id || id <= 0) {
            return 0;
        }
        return sysFeedbackMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page<SysFeedbackDO> getList(SysFeedback req, RowBounds toRowBounds) {
        Page<SysFeedbackDO> page = null;
        if (null != req) {
            page = sysFeedbackMapperExt.getList(req, toRowBounds);
        }
        return null == page ? new Page<>() : page;
    }

    @Override
    public int updateSysFeedback(SysFeedback sysFeedback) {
        if (null == sysFeedback) {
            return 0;
        }
        return sysFeedbackMapperExt.updateSysFeedback(sysFeedback);
    }

    @Override
    public SysFeedback selectByPrimaryKey(Long id) {
        if (null == id || id <= 0) {
            return null;
        }
        return sysFeedbackMapper.selectByPrimaryKey(id);
    }

    @Override
    public void handFeedback() {
        SysFeedbackExample sysFeedbackExample = new SysFeedbackExample();
        List<SysFeedback> sysFeedbacks = sysFeedbackMapper.selectByExample(sysFeedbackExample);
        ThreadPoolExecutor threadPoolExecutor = CommonUtils.getThreadPoolExecutor(sysFeedbacks.size());
        for (SysFeedback sysFeedback : sysFeedbacks) {
            threadPoolExecutor.execute(() -> handId(sysFeedback));
        }

    }

    public void handId(SysFeedback sysFeedback) {
        if (sysFeedback.getAccno() != null && !("").equals(sysFeedback.getAccno())) {
            MemBaseinfo userByAccno = memBaseinfoService.getUserByAccno(sysFeedback.getAccno());
            if (userByAccno.getUniqueId() != null && !("").equals(userByAccno.getUniqueId())) {
                sysFeedback.setUniqueId(userByAccno.getUniqueId());
                sysFeedbackMapper.updateByPrimaryKeySelective(sysFeedback);
            }
        }
    }
}
