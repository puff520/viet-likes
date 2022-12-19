package com.likes.modules.admin.sys.controller;

import com.likes.common.BaseController;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.StatusCode;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.LogUtils;
import com.likes.common.util.StringUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: SysMaintenanceController
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/7/10 14:55
 */
@RestController
@RequestMapping(value = "/maintenance")
public class SysMaintenanceController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysMaintenanceController.class);

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping(name = "系统维护", value = "/index")
    public ResultInfo index() {
        try {
            Map<String, String> data = buildMaintenanceInfo(null, null, null);
            data.put("status", "1");
            if (redisTemplate.hasKey(RedisKeys.LIVE_SYSTEM_MAINTENANCE_STATUS)) {
                Map<String, String> maintenanceData = RedisBusinessUtil.get(RedisKeys.LIVE_SYSTEM_MAINTENANCE_STATUS);
                data.put("status", "0");
                data.put("startTime", maintenanceData.get("startTime"));
                data.put("mainTime", maintenanceData.get("mainTime"));
                data.put("ipList", maintenanceData.get("ipList"));
            }
            return ResultInfo.ok(data);
        } catch (Exception e) {
            logger.error("{}.index，进入系统维护出错:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.getInstance(getMaintenanceStatus(), StatusCode.FAIL.getCode(), "开启维护状态失败");
        }
    }

    @PostMapping(name = "开启维护", value = "/start")
    public ResultInfo start(String startTime, String mainTime, String ipList) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(startTime)) {
                return ResultInfo.error("请输入维护开始时间");
            }
            if (StringUtils.isEmpty(mainTime)) {
                return ResultInfo.error("请输入维护时长");
            }
            if (StringUtils.isEmpty(ipList)) {
                return ResultInfo.error("请输入IP白名单");
            }



            //设置系统维护状态
            LoginUser loginUser = getLoginAdmin();
            Map<String, String> data = buildMaintenanceInfo(startTime, mainTime, ipList);
            redisTemplate.opsForValue().set(RedisKeys.LIVE_SYSTEM_MAINTENANCE_STATUS, data);
            LogUtils.logUserModifyOperates("maintenance.start", data, loginUser);
        } catch (Exception e) {
            logger.error("{}.start，开启维护状态出错:{},startTime:{},mainTime:{},ipList:{}", getClass().getName(), e.getMessage(), startTime, mainTime, ipList, e);
            response = ResultInfo.error("开启维护状态失败");
        } finally {
            response.setData(getMaintenanceStatus());
        }
        return response;
    }

    @PostMapping(name = "更新维护", value = "/update")
    public ResultInfo update(String startTime, String mainTime, String ipList) {
        ResultInfo response = ResultInfo.ok(getMaintenanceStatus());
        try {
            Map<String, String> data = updateMaintenanceInfo(startTime, mainTime, ipList);
            if (CollectionUtil.isNotEmpty(data)) {
                LoginUser loginUser = getLoginAdmin();
                redisTemplate.opsForValue().set(RedisKeys.LIVE_SYSTEM_MAINTENANCE_STATUS, data);
                LogUtils.logUserModifyOperates("maintenance.update", data, loginUser);
            }
        } catch (Exception e) {
            logger.error("{}.update，更新维护状态出错:{},startTime:{},mainTime:{},ipList:{}", getClass().getName(), e.getMessage(), startTime, mainTime, ipList, e);
            response = ResultInfo.error("更新维护状态失败");
        } finally {
            response.setData(getMaintenanceStatus());
        }
        return response;
    }

    @PostMapping(name = "停止维护", value = "/stop")
    public ResultInfo stop() {
        ResultInfo response = ResultInfo.ok(getMaintenanceStatus());
        try {
            LoginUser loginUser = getLoginAdmin();
            redisTemplate.delete(RedisKeys.LIVE_SYSTEM_MAINTENANCE_STATUS);
            LogUtils.logUserModifyOperates("maintenance.stop", null, loginUser);
        } catch (Exception e) {
            logger.error("{}.stop，开启维护状态出错:{}", getClass().getName(), e.getMessage(), e);
            response = ResultInfo.error("停止维护状态失败");
        } finally {
            response.setData(getMaintenanceStatus());
        }
        return response;
    }

    /**
     * 获取系统维护状态（1：正常；0：维护）
     *
     * @return
     */
    private String getMaintenanceStatus() {
        String status = "1";
        if (redisTemplate.hasKey(RedisKeys.LIVE_SYSTEM_MAINTENANCE_STATUS)) {
            status = "0";
        }
        return status;
    }

    private Map<String, String> buildMaintenanceInfo(String startTime, String mainTime, String ipList) {
        Map<String, String> data = new HashMap<>();
        data.put("startTime", startTime);
        data.put("mainTime", mainTime);
        data.put("ipList", ipList);
        return data;
    }

    private Map<String, String> updateMaintenanceInfo(String startTime, String mainTime, String ipList) {
        Map<String, String> data = (Map<String, String>) redisTemplate.opsForValue().get(RedisKeys.LIVE_SYSTEM_MAINTENANCE_STATUS);
        if (null == data) {
            data = new HashMap<>();
        }
        if (StringUtils.isNotEmpty(startTime)) {
            data.put("startTime", startTime);
        }
        if (StringUtils.isNotEmpty(mainTime)) {
            data.put("mainTime", mainTime);
        }
        if (StringUtils.isNotEmpty(ipList)) {
            data.put("ipList", ipList);
        }
        return data;
    }

}
