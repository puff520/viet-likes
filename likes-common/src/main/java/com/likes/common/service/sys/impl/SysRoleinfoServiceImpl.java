package com.likes.common.service.sys.impl;

import com.likes.common.model.dto.sys.SysRoleinfoForRoleDO;
import com.likes.common.mybatis.entity.SysRoleinfo;
import com.likes.common.mybatis.mapper.SysRoleinfoMapper;
import com.likes.common.mybatis.mapperext.sys.SysRoleinfoMapperExt;
import com.likes.common.service.sys.SysRoleinfoService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lucien
 * @create 2020/6/19 17:02
 */
@Service
public class SysRoleinfoServiceImpl implements SysRoleinfoService {
    @Autowired
    private SysRoleinfoMapper sysRoleinfoMapper;
    @Autowired
    private SysRoleinfoMapperExt sysRoleinfoMapperExt;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int updateByPrimaryKeySelective(SysRoleinfo sysRoleinfo) {
        return sysRoleinfoMapper.updateByPrimaryKeySelective(sysRoleinfo);
    }

    @Override
    public int insertSelective(SysRoleinfo sysRoleinfo) {
        return sysRoleinfoMapper.insertSelective(sysRoleinfo);
    }

    @Override
    public SysRoleinfo selectByPrimaryKey(Long sysroleid) {
        return sysRoleinfoMapper.selectByPrimaryKey(sysroleid);
    }

    @Override
    public SysRoleinfo getRoleByAccno(String accno) {
        return sysRoleinfoMapperExt.getRoleByAccno(accno);
    }

    @Override
    public SysRoleinfo checkDuplicates(SysRoleinfo sysRoleinfo) {
        return sysRoleinfoMapperExt.repeat(sysRoleinfo);
    }

    @Override
    public Page<SysRoleinfoForRoleDO> roleList(SysRoleinfo sysRoleinfo, RowBounds toRowBounds) {
        return sysRoleinfoMapperExt.roleList(sysRoleinfo, toRowBounds);
    }
}
