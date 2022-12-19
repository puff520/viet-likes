package com.likes.common.service.sys.impl;

import com.likes.common.model.dto.RemindInfoDO;
import com.likes.common.model.dto.sys.InfoRemindNumDO;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.mybatis.entity.InfSysremindinfo;
import com.likes.common.mybatis.mapper.InfSysremindinfoMapper;
import com.likes.common.mybatis.mapperext.InfSysremindinfoMapperExt;
import com.likes.common.service.sys.InfSysremindinfoService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class InfSysremindinfoServiceImpl implements InfSysremindinfoService {
    @Autowired
    private InfSysremindinfoMapper infSysremindinfoMapper;
    @Autowired
    private InfSysremindinfoMapperExt infSysremindinfoMapperExt;

    @Override
    public Integer getRmtypeNum(Map<String, Object> param) {
        return infSysremindinfoMapperExt.getRmtypeNum(param);
    }

    @Override
    public int updateRmtypeNum(Map<String, Object> param) {
        return infSysremindinfoMapperExt.updateRmtypeNum(param);
    }

    @Override
    public Page<InfSysremindinfo> myMsgList(UsersRequest usersRequest, RowBounds rowBounds) {
        return infSysremindinfoMapperExt.myMsgList(usersRequest, rowBounds);
    }

    @Override
    public Page<RemindInfoDO> getList(InfSysremindinfo req, RowBounds rowBounds) {
        return infSysremindinfoMapperExt.getList(req, rowBounds);
    }

    @Override
    public int insertAll(List<InfSysremindinfo> infSysremindinfoList) {
        return infSysremindinfoMapperExt.insertAll(infSysremindinfoList);
    }

    @Override
    public Integer getCount(String rmtype) {
        return infSysremindinfoMapperExt.getCount(rmtype);
    }

    @Override
    public Integer allUserSystemRemind(InfSysremindinfo infSysremindinfo) {
        return infSysremindinfoMapperExt.allUserSystemRemind(infSysremindinfo);
    }

    @Override
    public void systemRemindDelete(InfSysremindinfo infSysremindinfo) {
        infSysremindinfoMapperExt.systemRemindDelete(infSysremindinfo);
    }

    @Override
    public int insertSelective(InfSysremindinfo record) {
        return infSysremindinfoMapper.insertSelective(record);
    }

    @Override
    public InfSysremindinfo selectByPrimaryKey(Long rmdid) {
        return infSysremindinfoMapper.selectByPrimaryKey(rmdid);
    }

    @Override
    public List<InfoRemindNumDO> getNumByAccno(String accno) {

        return infSysremindinfoMapperExt.getNumByAccno(accno);
    }


}
