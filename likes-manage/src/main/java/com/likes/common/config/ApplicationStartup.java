package com.likes.common.config;

import com.likes.common.constant.ModuleConstant;
import com.likes.common.mybatis.entity.SysFuncinterface;
import com.likes.common.mybatis.entity.SysReffuncinitfc;
import com.likes.common.service.sys.SysFuncinterfaceService;
import com.likes.common.service.sys.SysReffuncinitfcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 阿布 接口权限初始化 (根据ofsystem sfunname 前后台权限自动关联)
 */
@Component
@Profile("test")
//@Profile("dev")
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private SysReffuncinitfcService sysReffuncinitfcService;

    @Autowired
    private SysFuncinterfaceService sysFuncinterfaceService;

    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 在容器加载完毕后
        logger.info("初始化接口权限...................");
        Date now = new Date();
        // 删除无效记录
        this.sysFuncinterfaceService.deleteByModifydate(ModuleConstant.LIVE_MANAGE);
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                hashMap.put("url", url);
                SysFuncinterface api = this.sysFuncinterfaceService.getOneSysFuncinterface(ModuleConstant.LIVE_MANAGE, url);
                if (api == null) {
                    api = new SysFuncinterface();
                    api.setOfsystem(ModuleConstant.LIVE_MANAGE); // 所属系统
                    api.setItfcname(info.getName()); // 接口名称
                    api.setItfcurl(url); // 接口地址
                    api.setItfcdesc(method.getMethod().getDeclaringClass().getName()); // 接口说明
                    this.sysFuncinterfaceService.insertSelective(api);
                } else {
                    api.setItfcname(info.getName()); // 接口名称
                    api.setItfcurl(url); // 接口地址
                    api.setItfcdesc(method.getMethod().getDeclaringClass().getName()); // 接口说明
                    this.sysFuncinterfaceService.updateByPrimaryKeySelective(api);
                }
                // logger.info("{}:{}", info.getName(), url);
            }
        }
        // 所有关联数据（名称关联）
        this.sysReffuncinitfcService.deleteByFunctionModifydate(ModuleConstant.LIVE_MANAGE, now);
        List<SysReffuncinitfc> list = this.sysReffuncinitfcService.getSysReffuncinitfcList(ModuleConstant.LIVE_MANAGE);
        for (SysReffuncinitfc sr : list) {
            if (this.sysReffuncinitfcService.updateSysReffuncinitfc(sr) <= 0) {
                this.sysReffuncinitfcService.insertSelective(sr);
            }
        }

    }
}
