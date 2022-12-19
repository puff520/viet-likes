package com.likes.modules.admin.users.service;

import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.GreatPerson;
import com.likes.common.service.BaseService;

import java.util.List;

/**
 * @author 阿布 牛人
 */
public interface GreatPersonService extends BaseService {
    /**
     * 牛人榜
     *
     * @return
     * @throws BusinessException
     */
    List<GreatPerson> getGreatPersonTop() throws BusinessException;

    /**
     * 牛人计划列表
     *
     * @return
     * @throws BusinessException
     */
    PageResult getList(PageBounds page) throws BusinessException;
//
//    Map<String, Object> getFollowOdd(Long orderid, LoginUser loginUser) throws BusinessException;


}
