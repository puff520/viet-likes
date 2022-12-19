/**
 *
 */
package com.likes.modules.admin.common.service;

import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.OperatorDO;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.likes.common.mybatis.entity.SysInfolog;
import com.likes.common.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
public interface CommonService extends BaseService {

    /**
     * @param pcode
     * @return
     */
    List<SysBusparameter> getParamList(String pcode);

    /**
     * 获取官方账号APP
     * @return
     */
    List<OperatorDO> getOperatorList();

    /**
     * 插入日志
     * @param sysInfolog
     */
    void insertSelective(SysInfolog sysInfolog);

    /**
     * 获取超级后台站点公告
     * @param pageBounds
     * @return
     */
    ResultInfo getSiteNotice(PageBounds pageBounds);


}
