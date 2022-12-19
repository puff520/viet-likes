package com.likes.common.service.member;


import com.likes.common.model.dto.member.MemGoldchangeDO;


public interface MemBaseinfoWriteService {

    /**
     * 修改用户余额
     *
     * @param change 改变记录
     * @return
     */
    boolean updateUserBalance(MemGoldchangeDO change) throws RuntimeException;

}
