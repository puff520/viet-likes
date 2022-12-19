package com.likes.modules.admin.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.SysParameterEnum;
import com.likes.common.enums.ValidateMethodEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.OperatorDO;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.likes.common.mybatis.entity.SysInfolog;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.service.sys.SysInfologService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.BaseUtil;
import com.likes.common.util.JsonUtil;
import com.likes.common.util.StringUtils;
import com.likes.common.util.http.HttpClientUtil;
import com.likes.common.util.uploadFile.FileNameUtils;
import com.likes.common.util.uploadFile.FileUtils2;
import com.likes.modules.admin.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
@Service
public class CommonServiceImpl extends BaseServiceImpl implements CommonService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysParamService sysParamService;
    @Resource
    private MemLoginService memLoginService;
    @Resource
    private SysInfologService sysInfologService;
    @Resource
    private SysBusParamService sysBusParamService;

    /**
     * @param pcode
     * @return
     */
    @Override
    public List<SysBusparameter> getParamList(String pcode) {
        return this.sysBusParamService.selectByParedubpcode(pcode);
    }

    @Override
    public List<OperatorDO> getOperatorList() {

        return memLoginService.getOperatorList();
    }

    @Override
    public void insertSelective(SysInfolog sysInfolog) {
        sysInfolog.setOptip(BaseUtil.getUserIp(request));
        sysInfolog.setServerip(request.getLocalAddr());
        sysInfologService.insert(sysInfolog);
    }

    @Override
    public ResultInfo getSiteNotice(PageBounds pageBounds) {
        ResultInfo response = ResultInfo.fail("获取站点公告失败");
        try {
            Map<String, String> req = new HashMap<String, String>();
            SysParameter sp = sysParamService.getByCode(SysParameterEnum.PLATFORM_NAME.getCode());
            if (sp == null || StringUtils.isEmpty(sp.getSysparamvalue())) {
                return ResultInfo.fail("缺少平台标识参数异常");
            }
            req.put("sitecode", sp.getSysparamvalue());
            sp = sysParamService.getByCode(SysParameterEnum.SUPER_GATEWAY.getCode());
            if (sp == null || StringUtils.isEmpty(sp.getSysparamvalue())) {
                return ResultInfo.fail("超级后台网关参数异常");
            }
            req.put("pageNo", pageBounds.getPageNo().toString());
            req.put("pageSize", pageBounds.getPageSize().toString());

            String json = HttpClientUtil.formPostWithSign(sp.getSysparamvalue() + ValidateMethodEnum.SUPER_SYSNOTEICE_SITENOTICE.getMethodName(), ValidateMethodEnum.SUPER_SYSNOTEICE_SITENOTICE.getMethodName(), req);
            log.info("获取超级后台接口数据 url={} ,jsonstr = {}", (sp.getSysparamvalue() + ValidateMethodEnum.SUPER_SYSNOTEICE_SITENOTICE.getMethodName()), json);
            if (StringUtils.isNotEmpty(json)) {
                response = JsonUtil.fromJson(json, ResultInfo.class);
            }
        } catch (Exception e) {
            response = ResultInfo.fail("获取站点公告异常");
        }
        return response;
    }

}
