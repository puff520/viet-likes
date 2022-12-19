package com.likes.common.config;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.util.robin.SmoothWeightedRoundRobin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: CacheConfig
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/7/10 20:21
 */
public class CacheConfigs {

    private static final Logger logger = LoggerFactory.getLogger(CacheConfigs.class);

    private static final ReentrantLock pollWsLock = new ReentrantLock();

    // 直播服务器代理平滑加权轮询算法
    public static SmoothWeightedRoundRobin srsserver = new SmoothWeightedRoundRobin();

    // tencentlive推流服务器
    public static SmoothWeightedRoundRobin tenpushserver = new SmoothWeightedRoundRobin();

    // tencentlive拉流服务器
    public static SmoothWeightedRoundRobin tenpullserver = new SmoothWeightedRoundRobin();

    public static String SecretId;

    public static String SecretKey;

    public static String CallbackKey;
    public static String platform;

    public static List<String> wsList = new ArrayList<>();

    private static int wsPoolIndex = 0;

    /**
     * 轮询获取ws地址
     *
     * @return
     */
    public static String poolWsUrl() {
        try {
            pollWsLock.lock();
            wsList = null == wsList ? new ArrayList<>() : wsList;
            int size = wsList.size();
            if (size == 0) {
                return "";
            }
            if (size == 1) {
                return wsList.get(0);
            }
            wsPoolIndex = wsPoolIndex >= size ? 0 : wsPoolIndex;
            String wsUrl = wsList.get(wsPoolIndex);
            wsPoolIndex++;
            return wsUrl;
        } catch (Exception e) {
            logger.error("poolWsUrl error. wsList:{}", JSONObject.toJSONString(wsList), e);
            return "";
        } finally {
            pollWsLock.unlock();
        }
    }
}
