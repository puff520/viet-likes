package com.likes.common.service.money.impl;

import com.likes.common.model.request.PaySetRequest;
import com.likes.common.mybatis.entity.SysPayset;
import com.likes.common.mybatis.entity.SysPaysetExample;
import com.likes.common.mybatis.mapper.SysPaysetMapper;
import com.likes.common.mybatis.mapperext.sys.SysPaysetMapperExt;
import com.likes.common.service.money.SysPaysetService;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.github.pagehelper.Page;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysPaysetServiceImpl implements SysPaysetService {
    @Resource
    private SysPaysetMapper sysPaysetMapper;
    @Resource
    private SysPaysetMapperExt sysPaysetMapperExt;

    @Override
    public int countByExample(SysPaysetExample example) {
        return sysPaysetMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SysPaysetExample example) {

        int result = sysPaysetMapper.deleteByExample(example);
        if (result > 0) {
            RedisBusinessUtil.delPaysetCacheBySetType();
        }
        return result;
    }

    @Override
    public int deleteByPrimaryKey(Long paysetid) {
        int result = sysPaysetMapper.deleteByPrimaryKey(paysetid);
        if (result > 0) {
            RedisBusinessUtil.delPaysetCacheBySetType();
        }
        return result;
    }

    @Override
    public int insert(SysPayset record) {
        int result = sysPaysetMapper.insert(record);
        if (result > 0) {
            RedisBusinessUtil.delPaysetCacheBySetType();
        }
        return result;
    }

    @Override
    public int insertSelective(SysPayset record) {
        int result = sysPaysetMapper.insertSelective(record);
        if (result > 0) {
            RedisBusinessUtil.delPaysetCacheBySetType();
        }
        return result;
    }

    @Override
    public SysPayset selectOneByExample(SysPaysetExample example) {
        return sysPaysetMapper.selectOneByExample(example);
    }

    @Override
    public List<SysPayset> selectByExample(SysPaysetExample example) {
        return sysPaysetMapper.selectByExample(example);
    }

    @Override
    public SysPayset selectByPrimaryKey(Long paysetid) {
        return sysPaysetMapper.selectByPrimaryKey(paysetid);
    }

    @Override
    public int updateByExampleSelective(SysPayset record, SysPaysetExample example) {
        int result = sysPaysetMapper.updateByExampleSelective(record, example);
        if (result > 0) {
            RedisBusinessUtil.delPaysetCacheBySetType();
        }
        return result;
    }

    @Override
    public int updateByExample(SysPayset record, SysPaysetExample example) {
        int result = sysPaysetMapper.updateByExample(record, example);
        if (result > 0) {
            RedisBusinessUtil.delPaysetCacheBySetType();
        }
        return result;
    }

    @Override
    public int updateByPrimaryKeySelective(SysPayset record) {
        int result = sysPaysetMapper.updateByPrimaryKeySelective(record);
        if (result > 0) {
            RedisBusinessUtil.delPaysetCacheBySetType();
        }
        return result;
    }

    @Override
    public int updateByPrimaryKey(SysPayset record) {
        int result = sysPaysetMapper.updateByPrimaryKey(record);
        if (result > 0) {
            RedisBusinessUtil.delPaysetCacheBySetType();
        }
        return result;
    }

    /**
     * ????????????
     * @param paySetRequest
     * @param rowBounds ????????????
     * @return
     */
    @Override
    public Page<SysPayset> getList(PaySetRequest paySetRequest, RowBounds rowBounds) {
        return sysPaysetMapperExt.getList(paySetRequest,rowBounds);
    }

    /**
     * ??????????????????(ID)?????????????????????
     * @param sysPayset
     */
    @Override
    public void doAllJingyong(SysPayset sysPayset) {
        sysPaysetMapperExt.doAllJingyong(sysPayset);
        RedisBusinessUtil.delPaysetCacheBySetType();
    }

    /**
     * ????????????????????????
     * @param setname
     * @return
     */
    @Override
    public SysPayset getRepeate(String setname) {
        return sysPaysetMapperExt.getRepeate(setname);
    }

    /**
     * ??????????????????(????????????),??????????????????
     * @param sysPayset
     */
    @Override
    public void doAllJingyong2(PaySetRequest sysPayset) {
        sysPaysetMapperExt.doAllJingyong2(sysPayset);
        RedisBusinessUtil.delPaysetCacheBySetType();
    }

    /**
     * ?????????????????????????????????????????? setname
     * @param paySetRequest
     * @return
     */
    @Override
    public List<SysPayset> getAllList(PaySetRequest paySetRequest) {
        return sysPaysetMapperExt.getAllList(paySetRequest);
    }

    /**
     * ????????????
     * @param sysPayset
     */
    @Override
    public void doUpdate2(SysPayset sysPayset) {
        sysPaysetMapperExt.doUpdate2(sysPayset);
        RedisBusinessUtil.delPaysetCacheBySetType();
    }

    /**
     * ??????orderId ????????????????????? ?????????
     * @param orderid
     * @return
     */
    @Override
    public SysPayset getOneBySettypeByOrderid(Long orderid) {
        return sysPaysetMapperExt.getOneBySettypeByOrderid(orderid);
    }

    /**
     * ???????????????????????????????????????
     * @param settype 1 ???????????????2 ????????????
     * @return
     */
    @Override
    public SysPayset getUseOne(Integer settype) {
        SysPayset s = RedisBusinessUtil.getPaysetCacheBySetType(settype);
        if (ObjectUtils.isNotEmpty(s)) {
            return s;
        }
        s = sysPaysetMapperExt.getUseOne(settype);
        RedisBusinessUtil.addPaysetCacheBySetType(s);
        return s;
    }
}
