package com.likes.common.service.sys;

import com.likes.common.model.dto.sys.SysShortmsgDO;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.mybatis.entity.SysShortmsg;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

/**
 * ClassName: SysShortmsgService
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/6/19 21:26
 */
public interface SysShortmsgService {

    Integer getLimit(SysShortmsg sysShortmsgParam);

    Integer getCountDown(UsersRequest req);

    void insertSelective(SysShortmsgDO sysShortmsg);

    void insertSelectiveSysShortmsg(SysShortmsgDO sysShortmsg);

    Integer getMsgByParamCountDown(UsersRequest req);

    SysShortmsg getMsgByParam(UsersRequest req);

    Page<SysShortmsg> getList(SysShortmsg req, RowBounds rowBounds);

    Integer insertByParam(SysShortmsg req);

    Integer selectWaittime(SysShortmsg req);

    void updateByPrimaryKeySelective(SysShortmsg msg);
}
