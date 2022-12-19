package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.dto.member.UserDO;
import com.likes.common.mybatis.entity.SysBduserrole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysBduserroleMapperExt {
    List<UserDO> getZuneiList(Long sysroleid);

    List<UserDO> getZuwaiList();

    SysBduserrole checkExist(SysBduserrole sys);

    void delBduserrole(SysBduserrole sysBduserrole);
}