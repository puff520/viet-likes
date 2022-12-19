package com.likes.common.service.member;

import com.likes.common.mybatis.entity.MemCertification;
import com.likes.common.service.BaseService;

public interface MemCertificationService extends BaseService {

    /**
     * 根据accno查找主播认证表
     */
    MemCertification findByAccno(String accno);

    /**
     * 根据accno查找主播认证表
     */
    MemCertification findByAccnoByCache(String accno);

    /**
     * 插入主播认证表
     */
    int insertSelectiveMemCertification(MemCertification mCertification);

    /**
     * 根据accno查找主播认证表
     */
    MemCertification getMemCertificationOne(String accno);

    /**
     * 根更新主播认证表
     */
    int updateByPrimaryKeySelective(MemCertification record);
}
