package com.likes.common.service.sys;

import com.likes.common.mybatis.entity.SysErrorlog;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

/**
 * ClassName: SysErrorlogService
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/6/19 20:17
 */
public interface SysErrorlogService {

    void insert(SysErrorlog sysErrorlog);

    void insert(String Systemname, String modulename, String content, String optuser, String level, String orginfo);

    SysErrorlog selectByMd5(String orginfo);

    Page<SysErrorlog> getList(SysErrorlog req, RowBounds rowBounds);
}
