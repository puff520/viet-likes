package com.likes.common.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.likes.common.model.LoginUser;
import com.likes.common.model.SysDomain;
import com.likes.common.model.request.SysDomainRequest;
import com.likes.common.mybatis.mapper.SysDomainMapper;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.sys.SysDomainService;
import com.likes.common.util.PageBaseInfo;
import com.likes.common.util.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SysDomainServiceImpl extends BaseServiceImpl implements SysDomainService {

    @Resource
    private SysDomainMapper sysDomainMapper;


    @Override
    public boolean add(SysDomainRequest request, LoginUser loginUser) {
        SysDomain sysDomain = new SysDomain();
        sysDomain.setInvitationCode(request.getInvitationCode());
        sysDomain.setIsDelete(false);
        sysDomain.setStatus(request.getStatus());
        sysDomain.setType(request.getType());
        sysDomain.setNote(request.getNote());
        sysDomain.setDomainName(request.getDomainName());
        sysDomain.setCreateTime(new Date());
        sysDomain.setCreateUser(loginUser.getAccno());
        return sysDomainMapper.insert(sysDomain) > 0;
    }

    @Override
    public boolean update(SysDomainRequest request, LoginUser loginUser) {
        SysDomain sysDomain = new SysDomain();
        sysDomain.setId(request.getId());
        sysDomain.setInvitationCode(request.getInvitationCode());
        sysDomain.setType(request.getType());
        sysDomain.setStatus(request.getStatus());
        sysDomain.setDomainName(request.getDomainName());
        sysDomain.setNote(request.getNote());
        sysDomain.setUpdateTime(new Date());
        sysDomain.setUpdateUser(loginUser.getAccno());
        return sysDomainMapper.updateByPrimaryKeySelective(sysDomain) > 0;
    }

    @Override
    public boolean delete(String ids, LoginUser loginUser) {
        String[] idArray = ids.split(",");
        List<String> strList = Stream.of(idArray).collect(Collectors.toList());
        List<Integer> idList = strList.stream().map(Integer::parseInt).collect(Collectors.toList());
        Example example = new Example(SysDomain.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", idList);
        return sysDomainMapper.deleteByExample(example) > 0;
    }

    @Override
    public PageBaseInfo<SysDomain> domainList(SysDomainRequest request) {
        Example example = new Example(SysDomain.class);
        if(StringUtils.isNotBlank(request.getDomainName())){
            example.createCriteria().andLike("domainName", "%"+request.getDomainName()+"%");
        }
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<SysDomain> list = sysDomainMapper.selectByExample(example);
        PageBaseInfo<SysDomain> pageList = new PageBaseInfo<>(list);
        return pageList;
    }
}