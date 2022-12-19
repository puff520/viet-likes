package com.likes.common.service.member;

import com.likes.common.model.LoginUser;
import com.likes.common.mybatis.entity.BasRobotset;

/**
 * @author lucien
 * @create 2020/8/19 19:51
 */
public interface BasRobotsetService {

    BasRobotset detail();

    int insertSelective(BasRobotset b);

    int updateByPrimaryKey(BasRobotset basRobotset);
}
