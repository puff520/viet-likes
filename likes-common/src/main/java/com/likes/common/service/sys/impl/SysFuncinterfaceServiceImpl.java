package com.likes.common.service.sys.impl;

import com.likes.common.mybatis.entity.SysFuncinterface;
import com.likes.common.mybatis.mapper.SysFuncinterfaceMapper;
import com.likes.common.mybatis.mapperext.sys.SysFuncinterfaceMapperExt;
import com.likes.common.service.sys.SysFuncinterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lucien
 * @create 2020/6/19 17:04
 */
@Service
public class SysFuncinterfaceServiceImpl implements SysFuncinterfaceService {

    @Autowired
    private SysFuncinterfaceMapper sysFuncinterfaceMapper;
    @Autowired
    private SysFuncinterfaceMapperExt sysFuncinterfaceMapperExt;

    @Override
    public int deleteByModifydate(String liveManage) {
        return sysFuncinterfaceMapperExt.deleteByModifydate(liveManage);
    }

    @Override
    public SysFuncinterface getOneSysFuncinterface(String liveManage, String url) {
        return sysFuncinterfaceMapperExt.selectOne(liveManage,url);
    }

    @Override
    public int insertSelective(SysFuncinterface api) {
       return sysFuncinterfaceMapper.insertSelective(api);
    }

    @Override
    public int updateByPrimaryKeySelective(SysFuncinterface api) {
        return sysFuncinterfaceMapper.updateByPrimaryKeySelective(api);
    }

    @Override
    public List<String> getInterfaceUrlsByRole(List<Long> param) {
        return sysFuncinterfaceMapperExt.getInterfaceUrlsByRole(param);
    }

    @Override
    public List<SysFuncinterface> searchInterfaceBySfunname(String sfunname) {
        return sysFuncinterfaceMapperExt.searchInterfaceBySfunname(sfunname);
    }
}
