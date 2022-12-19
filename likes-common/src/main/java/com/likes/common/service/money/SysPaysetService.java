package com.likes.common.service.money;

import com.likes.common.model.request.PaySetRequest;
import com.likes.common.mybatis.entity.SysPayset;
import com.likes.common.mybatis.entity.SysPaysetExample;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysPaysetService {
    int countByExample(SysPaysetExample example);

    int deleteByExample(SysPaysetExample example);

    int deleteByPrimaryKey(Long paysetid);

    int insert(SysPayset record);

    int insertSelective(SysPayset record);

    SysPayset selectOneByExample(SysPaysetExample example);

    List<SysPayset> selectByExample(SysPaysetExample example);

    SysPayset selectByPrimaryKey(Long paysetid);

    int updateByExampleSelective(SysPayset record, SysPaysetExample example);

    int updateByExample(SysPayset record, SysPaysetExample example);

    int updateByPrimaryKeySelective(SysPayset record);

    int updateByPrimaryKey(SysPayset record);

    Page<SysPayset> getList(PaySetRequest paySetRequest, RowBounds rowBounds);

    void doAllJingyong(SysPayset sysPayset);

    SysPayset getRepeate(String setname);

    void doAllJingyong2(PaySetRequest sysPayset);

    List<SysPayset> getAllList(PaySetRequest paySetRequest);

    void doUpdate2(SysPayset sysPayset);

    SysPayset getOneBySettypeByOrderid(Long orderid);

    SysPayset getUseOne(Integer settype);
}
