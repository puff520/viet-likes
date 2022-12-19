package com.likes.common.mybatis.mapper;


import com.likes.common.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author zhengqingya
 * @description
 * @date 2021/01/13 10:11
 */
public interface UserMapper extends Mapper<User> {



    /**
     * 批量插入数据
     *
     * @param list list
     * @return void
     * @author zhengqingya
     * @date 2021/5/28 14:28
     */
    void insertBatch(@Param("list") List<User> list);

}
