package com.likes.common.mybatis.mapperext;

import org.apache.ibatis.annotations.Param;

public interface BankCardMapperExt {
    /**
     * 查询用户是否可添加银行卡，据系统配置的最高可绑定银行卡数量
     * @param memberId
     * @return
     */
    Integer checkAddBankCardAble(@Param("memberId") int memberId);
}