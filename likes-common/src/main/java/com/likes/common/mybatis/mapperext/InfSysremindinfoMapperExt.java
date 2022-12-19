package com.likes.common.mybatis.mapperext;

import com.likes.common.model.dto.RemindInfoDO;
import com.likes.common.model.dto.sys.InfoRemindNumDO;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.mybatis.entity.InfSysremindinfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface InfSysremindinfoMapperExt {

    Integer getRmtypeNum(Map<String, Object> param);

    int updateRmtypeNum(Map<String, Object> param);

    Page<InfSysremindinfo> myMsgList(UsersRequest usersRequest, RowBounds rowBounds);

    Page<RemindInfoDO> getList(InfSysremindinfo req, RowBounds rowBounds);

    int insertAll(List<InfSysremindinfo> infSysremindinfoList);

    Integer getCount(String rmtype);

    Integer allUserSystemRemind(InfSysremindinfo infSysremindinfo);

    void systemRemindDelete(InfSysremindinfo infSysremindinfo);

    List<InfoRemindNumDO> getNumByAccno(String accno);

}
