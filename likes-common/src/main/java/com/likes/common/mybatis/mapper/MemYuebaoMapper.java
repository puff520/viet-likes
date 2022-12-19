package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.MemYuebao;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;

public interface MemYuebaoMapper extends Mapper<MemYuebao> {


    List<MemYuebao> yuebaoUserList();

    MemYuebao selectMemYuebaoByAccno(String accno);

    int updateMemYuebaoAmount(BigDecimal amount, String accno);


}

