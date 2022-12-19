package com.likes.common.service.sys;

import com.likes.common.constant.ModuleConstant;
import com.likes.common.model.dto.sys.SysInfologDO;
import com.likes.common.mybatis.entity.SysInfolog;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

/**
 * ClassName: SysInfologService
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/6/19 19:01
 */
public interface SysInfologService {

    void insert(SysInfolog sysInfolog);

    default void insertAppLog(String accno, String modelName, String optContent, Double longitude, Double latitude, String origin) {
        insert(accno, ModuleConstant.LIVE_APP, modelName, optContent, longitude, latitude, origin);
    }

    void insert(String accno, String systemName, String modelName, String optContent, Double longitude, Double latitude, String origin);

    void insert(String accno, String systemName, String modelName, String optContent, String optIp, String serverIp, Double longitude, Double latitude, String origin);

    Page<SysInfologDO> getList(SysInfolog req, RowBounds rowBounds);
}
