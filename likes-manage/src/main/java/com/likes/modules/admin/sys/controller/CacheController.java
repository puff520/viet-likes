package com.likes.modules.admin.sys.controller;

import com.likes.common.BaseController;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.service.sys.SysParamService;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * @author lzy
 * @create 2018-07-13 10:43
 **/
@RestController
@RequestMapping("/cache")
public class CacheController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CacheController.class);
    @Resource
    private SysParamService sysParamService;
    @Resource
    private SysBusParamService sysBusParamService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 缓存的查询
     */
    @PostMapping(name = "缓存的查询", value = "/listCache")
    public ResultInfo listCache(String key, String type, String field, Long start, Long end) {
        logger.info("listCache:{},type:{},field:{},start:{},end:{}...", key, type, field, start, end);
        try {
            Object result = null;
            if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(type)) {
                key = key.trim();
                switch (type) {
                    case "string":
                        if (key.contains("*")) {
                            Set indistinctKeys = redisTemplate.keys(key);
                            Map values = new HashMap();
                            Iterator iterator = indistinctKeys.iterator();
                            while (iterator.hasNext()) {
                                Object keyObj = iterator.next();
                                values.put(keyObj, redisTemplate.opsForValue().get(keyObj));
                            }
                            if (CollectionUtil.isNotEmpty(values)) {
                                result = values;
                            }
                        } else {
                            result = redisTemplate.opsForValue().get(key.trim());
                        }
                        break;
                    case "list":
                        if (null == start || start < 0) {
                            start = 0L;
                        }
                        if (null == end || end < start && end != -1) {
                            end = start + 1;
                        }

                        Long size = redisTemplate.opsForList().size(key);
                        end = end >= size ? size - 1 : end;
                        result = redisTemplate.opsForList().range(key, start, end);
                        break;
                    case "map":
                        if (StringUtils.isNotEmpty(field)) {
                            Map<String, Object> map = new HashMap<>();
                            for (String fieldKey : field.split(",")) {
                                map.put(fieldKey, redisTemplate.opsForHash().get(key, fieldKey.trim()));
                            }
                            result = map;
                        }
                        break;
                    default:
                        logger.info("listCache:{},不支持的类型:{},field:{},start:{},end:{}...", key, type, field, start, end);
                        break;
                }
            }
            return ResultInfo.ok(result);
        } catch (Exception e) {
            logger.error("{}.listCache,出错:{},params:{}", getClass().getName(), e.getMessage(), "key=" + key, e);
            return ResultInfo.error("缓存的查询出错");
        }
    }

    /**
     * 缓存的刷新
     */
    @PostMapping(name = "缓存的刷新", value = "/flushCache")
    public ResultInfo flushCache(String key) {
        logger.info("flushCache:{}...", key);
        try {
            if (StringUtils.isEmpty(key)) {
                return ResultInfo.paramsError();
            }
            String[] keyArray = key.split(",");
            Set<String> keys = new HashSet<>();
            for (String item : keyArray) {
                if (StringUtils.isNotEmpty(item)) {
                    if (item.contains("*")) {
                        Set indistinctKeys = redisTemplate.keys(item);
                        keys.addAll(indistinctKeys);
                    } else {
                        keys.add(item.trim());
                    }
                }
            }
            if (CollectionUtil.isNotEmpty(keys)) {
                redisTemplate.delete(keys);
                LogUtils.logUserModifyOperates(getClass().getName() + ".flushCache", "delCacheKeys=" + key, getLoginAdmin());
            }
            return ResultInfo.ok(true);
        } catch (Exception e) {
            logger.error("{}.flushCache,出错:{},params:{}", getClass().getName(), e.getMessage(), "key=" + key, e);
            return ResultInfo.error("缓存的刷新出错");
        }
    }

    @PostMapping(name = "删除指定类型缓存", value = "/delCacheByType")
    public ResultInfo delCacheByType(String type) {
        try {
            type = StringUtils.isEmpty(type) ? "" : type.trim();
            switch (type) {
                case "ALL_LOTTERY":
                    RedisBusinessUtil.deleteLotteryCaches();
                    break;
                case "SYS_PARAMS":
                    sysParamService.deleteAllCaches();
                    break;
                case "SYS_BUS_PARAMS":
                    sysBusParamService.deleteAllCaches();
                    break;
                default:
                    logger.info("delCacheByType 不支持的类型：{}", type);
                    break;
            }
            LogUtils.logUserModifyOperates(getClass().getName() + ".delCacheByType", "type=" + type, getLoginAdmin());
            return ResultInfo.ok(null);
        } catch (Exception e) {
            logger.error("{}.delCacheByType,出错:{},params:{}", getClass().getName(), e.getMessage(), "type=" + type, e);
            return ResultInfo.error("删除指定类型缓存出错");
        }
    }

    @PostMapping(name = "添加字符型缓存", value = "/addStringCache")
    public ResultInfo addStringCache(String key, String value) {
        try {
            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
                return ResultInfo.paramsError();
            }
            RedisBusinessUtil.set(key, value);

            Map data = new HashMap();
            data.put("key", key);
            data.put("value", value);

            LogUtils.logUserModifyOperates(getClass().getName() + ".addStringCache", "key=" + key + ";value=" + value, getLoginAdmin());
            return ResultInfo.ok(null);
        } catch (Exception e) {
            logger.error("{}.addStringCache,出错:{},params:{}", getClass().getName(), e.getMessage(), "key=" + key + ";value=" + value, e);
            return ResultInfo.error("添加字符型缓存出错");
        }
    }

}
