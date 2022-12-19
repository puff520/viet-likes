package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.dto.sys.SysPayaccountDO;
import com.likes.common.mybatis.entity.SysPayaccount;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface SysPayaccountMapperExt {


    List<SysPayaccountDO> getPayInfo(@Param("accounttype") Integer accounttype, @Param("memlevel") Integer memlevel);

    Page<SysPayaccountDO> getList(SysPayaccount sysPayaccount, RowBounds rowBounds);

    SysPayaccount getRepeat(SysPayaccount memBankaccount);

    void updateTotalAmount(@Param("accno") String accno, @Param("bankid")Long bankid, @Param("amount") BigDecimal amount);

    void clearTotalAmount(@Param("accno") String accno, @Param("bankid")Long bankid);


}