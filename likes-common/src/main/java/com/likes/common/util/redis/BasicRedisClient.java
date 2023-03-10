//package com.likes.common.util.redis;
//
//import com.alibaba.fastjson.JSONObject;
//import com.likes.common.constant.RedisKeys;
//import com.likes.common.util.DateUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class BasicRedisClient {
//
//    private static final Logger logger = LoggerFactory.getLogger(BasicRedisClient.class);
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//    /** 统计用户离线时间 */
//    private RedisBigMap userOfflineRedisBigMap;
//
//    /**
//     * 批量删除对应的value
//     *
//     * @param keys
//     */
//    public void remove(final String... keys) {
//        Set keySet = new HashSet();
//        for (String key : keys) {
////            remove(key);
//            if (null == key || "".equals(key.trim())) {
//                continue;
//            }
//            keySet.add(key);
//        }
//        if (keySet.size() > 0) {
//            redisTemplate.delete(keySet);
//        }
//    }
//
//    public void remove(final String key) {
//        if (exists(key)) {
//            redisTemplate.delete(key);
//        }
//    }
//
//    /**
//     * 判断缓存中是否有对应的value
//     *
//     * @param key
//     * @return
//     */
//    public boolean exists(final String key) {
//        return redisTemplate.hasKey(key);
//    }
//
//    /**
//     * 更新key对应的失效时间
//     *
//     * @param key
//     * @param expireTime
//     * @return
//     */
//    public boolean updateExpire(final String key, Long expireTime) {
//        if (exists(key)) {
//            return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//        }
//        return false;
//    }
//
//    /**
//     * 读取缓存
//     *
//     * @param key
//     * @return
//     */
//    public Object get(final String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    /**
//     * 写入缓存  - 字符串类型
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public boolean set(final String key, Object value) {
//        return set(key, value, null);
//    }
//
//    public boolean set(final String key, Object value, Long expireTime) {
//        boolean result = false;
//        try {
//            if (null != expireTime && expireTime > 0) {
//                redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
//            } else {
//                redisTemplate.opsForValue().set(key, value);
//            }
//            result = true;
//        } catch (Exception e) {
//            logger.error("---Redis Set key:{} Object:{} ERROR: {}", key, value, e);
//        }
//        return result;
//    }
//
//    /**
//     * 写入缓存 - Set集合
//     *
//     * @param key
//     * @param set
//     * @return
//     */
//    public boolean add(final String key, Set<Object> set, Long expireTime) {
//        boolean result = false;
//        try {
//            if (null != expireTime && expireTime > 0) {
//                redisTemplate.opsForSet().add(key, set, expireTime, TimeUnit.SECONDS);
//            } else {
//                redisTemplate.opsForSet().add(key, set);
//            }
//            result = true;
//        } catch (Exception e) {
//            logger.error("---Redis add key:{} Object:{} ERROR: {}", key, set, e);
//        }
//        return result;
//    }
//
//
//    public boolean setField(String key, String field, Object value, Long expireTime) {
//        boolean result = false;
//        try {
//            redisTemplate.opsForHash().put(key, field, value);
//            if (null != expireTime && expireTime > 0) {
//                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//            }
//            result = true;
//        } catch (Exception e) {
//            logger.error("---Redis setField key:{} field:{} object:{} ERROR: {}", key, field, value, e);
//        }
//        return result;
//    }
//
//    public Object getField(String key, String field) {
//        return redisTemplate.opsForHash().get(key, field);
//    }
//
//    public Long removeField(String key, Object... fields) {
//        return redisTemplate.opsForHash().delete(key, fields);
//    }
//
//    public boolean existsField(String key, String field) {
//        return redisTemplate.opsForHash().hasKey(key, field);
//    }
//
//    public void incrField(String key, String field, Long delta) {
//        redisTemplate.opsForHash().increment(key, field, delta);
//    }
//
//    public void incrField(String key, String field) {
//        redisTemplate.opsForHash().increment(key, field, 1L);
//    }
//
//    //=============================common============================
//
//    /**
//     * 指定缓存失效时间
//     *
//     * @param key  键
//     * @param time 时间(秒)
//     * @return
//     */
//    public boolean expire(String key, long time) {
//        try {
//            if (time > 0) {
//                redisTemplate.expire(key, time, TimeUnit.SECONDS);
//            }
//            return true;
//        } catch (Exception e) {
//            logger.error("expire for key:{},time:{} occur error:{}", key, time, e);
//            return false;
//        }
//    }
//
//    /**
//     * 根据key 获取过期时间
//     *
//     * @param key 键 不能为null
//     * @return 时间(秒) 返回0代表为永久有效 失效时间为负数，说明该主键未设置失效时间（失效时间默认为-1）
//     */
//    public long getExpire(String key) {
//        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
//    }
//
//    /**
//     * 判断key是否存在
//     *
//     * @param key 键
//     * @return true 存在 false 不存在
//     */
//    public boolean hasKey(String key) {
//        try {
//            return redisTemplate.hasKey(key);
//        } catch (Exception e) {
//            logger.error("hasKey for key:{}, occur error:{}", key, e);
//            return false;
//        }
//    }
//
//    /**
//     * 删除缓存
//     *
//     * @param keys 可以传一个值 或多个
//     */
//    public void del(String... keys) {
//        if (keys != null && keys.length > 0) {
//            if (keys.length == 1) {
//                redisTemplate.delete(keys[0]);
//            } else {
//                redisTemplate.delete(CollectionUtils.arrayToList(keys));
//            }
//            logger.info("delete redis cache for keys:{} success.", Arrays.toString(keys));
//        }
//    }
//
//    //============================String=============================
//
//    /**
//     * 普通缓存获取
//     *
//     * @param key 键
//     * @return 值
//     */
//    public Object getValue(String key) {
//        return key == null ? null : redisTemplate.opsForValue().get(key);
//    }
//
//    /**
//     * 普通缓存放入
//     *
//     * @param key   键
//     * @param value 值
//     * @return true成功 false失败
//     */
//    public boolean setValue(String key, Object value) {
//        try {
//            redisTemplate.opsForValue().set(key, value);
//            return true;
//        } catch (Exception e) {
//            logger.error("setValue for key:{},value:{} occur error:{}", key, value, e);
//            return false;
//        }
//
//    }
//
//    /**
//     * 普通缓存放入并设置时间
//     *
//     * @param key   键
//     * @param value 值
//     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
//     * @return true成功 false 失败
//     */
//    public boolean setValue(String key, Object value, long time) {
//        try {
//            if (time > 0) {
//                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
//            } else {
//                setValue(key, value);
//            }
//            return true;
//        } catch (Exception e) {
//            logger.error("setValue for key:{},value:{},time:{} occur error:{}", key, value, time, e);
//            return false;
//        }
//    }
//
//    /**
//     * 递增 此时value值必须为int类型 否则报错
//     *
//     * @param key   键
//     * @param delta 要增加几(大于0)
//     * @return
//     */
//    public long incr(String key, long delta) {
//        if (delta < 0) {
//            throw new RuntimeException("递增因子必须大于0");
//        }
//        return redisTemplate.opsForValue().increment(key, delta);
//    }
//
//    /**
//     * 递减
//     *
//     * @param key   键
//     * @param delta 要减少几(小于0)
//     * @return
//     */
//    public long decr(String key, long delta) {
//        if (delta < 0) {
//            throw new RuntimeException("递减因子必须大于0");
//        }
//        return redisTemplate.opsForValue().increment(key, -delta);
//    }
//
//    //================================Map=================================
//
//    /**
//     * HashGet
//     *
//     * @param key  键 不能为null
//     * @param item 项 不能为null
//     * @return 值
//     */
//    public Object hGet(String key, String item) {
//        return redisTemplate.opsForHash().get(key, item);
//    }
//
//    /**
//     * 获取hashKey对应的所有键值
//     *
//     * @param key 键
//     * @return 对应的多个键值
//     */
//    public Map<Object, Object> hMGet(String key) {
//        return redisTemplate.opsForHash().entries(key);
//    }
//
//    /**
//     * HashSet
//     *
//     * @param key 键
//     * @param map 对应多个键值
//     * @return true 成功 false 失败
//     */
//    public boolean hSet(String key, Map<String, Object> map) {
//        try {
//            redisTemplate.opsForHash().putAll(key, map);
//            return true;
//        } catch (Exception e) {
//            logger.error("hSet for key:{},map:{} occur error:{}", key, JSONObject.toJSONString(map), e);
//            return false;
//        }
//    }
//
//    /**
//     * HashSet 并设置时间
//     *
//     * @param key  键
//     * @param map  对应多个键值
//     * @param time 时间(秒)
//     * @return true成功 false失败
//     */
//    public boolean hMSet(String key, Map<String, Object> map, long time) {
//        try {
//            redisTemplate.opsForHash().putAll(key, map);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            logger.error("hMSet for key:{},map:{},time:{} occur error:{}", key, JSONObject.toJSONString(map), time, e);
//            return false;
//        }
//    }
//
//    /**
//     * 向一张hash表中放入数据,如果不存在将创建
//     *
//     * @param key   键
//     * @param item  项
//     * @param value 值
//     * @return true 成功 false失败
//     */
//    public boolean hSet(String key, String item, Object value) {
//        try {
//            redisTemplate.opsForHash().put(key, item, value);
//            return true;
//        } catch (Exception e) {
//            logger.error("hSet for key:{},item:{},value:{} occur error:{}", key, item, value, e);
//            return false;
//        }
//    }
//
//    /**
//     * 向一张hash表中放入数据,如果不存在将创建
//     *
//     * @param key   键
//     * @param item  项
//     * @param value 值
//     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
//     * @return true 成功 false失败
//     */
//    public boolean hSet(String key, String item, Object value, long time) {
//        try {
//            redisTemplate.opsForHash().put(key, item, value);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            logger.error("hSet for key:{},item:{},value:{},time:{} occur error:{}", key, item, value, time, e);
//            return false;
//        }
//    }
//
//    /**
//     * 删除hash表中的值
//     *
//     * @param key  键 不能为null
//     * @param item 项 可以使多个 不能为null
//     */
//    public void hDel(String key, Object... item) {
//        redisTemplate.opsForHash().delete(key, item);
//    }
//
//    /**
//     * 判断hash表中是否有该项的值
//     *
//     * @param key  键 不能为null
//     * @param item 项 不能为null
//     * @return true 存在 false不存在
//     */
//    public boolean hHasKey(String key, String item) {
//        return redisTemplate.opsForHash().hasKey(key, item);
//    }
//
//    /**
//     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
//     *
//     * @param key  键
//     * @param item 项
//     * @param by   要增加几(大于0)
//     * @return
//     */
//    public double hIncr(String key, String item, double by) {
//        return redisTemplate.opsForHash().increment(key, item, by);
//    }
//
//    /**
//     * hash递减
//     *
//     * @param key  键
//     * @param item 项
//     * @param by   要减少记(小于0)
//     * @return
//     */
//    public double hDecr(String key, String item, double by) {
//        return redisTemplate.opsForHash().increment(key, item, -by);
//    }
//
//    //============================set=============================
//
//    /**
//     * 根据key获取Set中的所有值
//     *
//     * @param key 键
//     * @return
//     */
//    public Set<Object> sGet(String key) {
//        try {
//            return redisTemplate.opsForSet().members(key);
//        } catch (Exception e) {
//            logger.error("sGet for key:{} occur error:{}", key, e);
//            return null;
//        }
//    }
//
//    /**
//     * 根据value从一个set中查询,是否存在
//     *
//     * @param key   键
//     * @param value 值
//     * @return true 存在 false不存在
//     */
//    public boolean sHasKey(String key, Object value) {
//        try {
//            return redisTemplate.opsForSet().isMember(key, value);
//        } catch (Exception e) {
//            logger.error("sHasKey for key:{},value:{} occur error:{}", key, value, e);
//            return false;
//        }
//    }
//
//    /**
//     * 将数据放入set缓存
//     *
//     * @param key    键
//     * @param values 值 可以是多个
//     * @return 成功个数
//     */
//    public long sSet(String key, Object... values) {
//        try {
//            return redisTemplate.opsForSet().add(key, values);
//        } catch (Exception e) {
//            logger.error("sSet for key:{},values:{} occur error:{}", key, JSONObject.toJSONString(values), e);
//            return 0;
//        }
//    }
//
//    /**
//     * 将set数据放入缓存
//     *
//     * @param key    键
//     * @param time   时间(秒)
//     * @param values 值 可以是多个
//     * @return 成功个数
//     */
//    public long sSetAndTime(String key, long time, Object... values) {
//        try {
//            Long count = redisTemplate.opsForSet().add(key, values);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return count;
//        } catch (Exception e) {
//            logger.error("sSetAndTime for key:{},time:{},values:{} occur error:{}", key, time, JSONObject.toJSONString(values), e);
//            return 0;
//        }
//    }
//
//    /**
//     * 获取set缓存的长度
//     *
//     * @param key 键
//     * @return
//     */
//    public long sGetSetSize(String key) {
//        try {
//            return redisTemplate.opsForSet().size(key);
//        } catch (Exception e) {
//            logger.error("sGetSetSize for key:{}, occur error:{}", key, e);
//            return 0;
//        }
//    }
//
//    /**
//     * 移除值为value的
//     *
//     * @param key    键
//     * @param values 值 可以是多个
//     * @return 移除的个数
//     */
//    public long setRemove(String key, Object... values) {
//        try {
//            return redisTemplate.opsForSet().remove(key, values);
//        } catch (Exception e) {
//            logger.error("setRemove for key:{},values:{} occur error:{}", key, JSONObject.toJSONString(values), e);
//            return 0;
//        }
//    }
//    //===============================list=================================
//
//    /**
//     * 获取list缓存的内容
//     *
//     * @param key   键
//     * @param start 开始
//     * @param end   结束  0 到 -1代表所有值
//     * @return
//     */
//    public List<Object> lGet(String key, long start, long end) {
//        try {
//            return redisTemplate.opsForList().range(key, start, end);
//        } catch (Exception e) {
//            logger.error("lGet for key:{},start:{},end:{} occur error:{}", key, start, end, e);
//            return null;
//        }
//    }
//
//    /**
//     * 获取list缓存的长度
//     *
//     * @param key 键
//     * @return
//     */
//    public long lGetListSize(String key) {
//        try {
//            return redisTemplate.opsForList().size(key);
//        } catch (Exception e) {
//            logger.error("lGetListSize for key:{} occur error:{}", key, e);
//            return 0;
//        }
//    }
//
//    /**
//     * 通过索引 获取list中的值
//     *
//     * @param key   键
//     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
//     * @return
//     */
//    public Object lGetIndex(String key, long index) {
//        try {
//            return redisTemplate.opsForList().index(key, index);
//        } catch (Exception e) {
//            logger.error("lGetIndex for key:{},index:{} occur error:{}", key, index, e);
//            return null;
//        }
//    }
//
//    /**
//     * 将list放入缓存
//     *
//     * @param key   键
//     * @param value 值
//     * @return
//     */
//    public boolean lSet(String key, Object value) {
//        try {
//            redisTemplate.opsForList().rightPush(key, value);
//            return true;
//        } catch (Exception e) {
//            logger.error("lSet for key:{},value:{} occur error:{}", key, value, e);
//            return false;
//        }
//    }
//
//    /**
//     * 将list放入缓存
//     *
//     * @param key   键
//     * @param value 值
//     * @param time  时间(秒)
//     * @return
//     */
//    public boolean lSet(String key, Object value, long time) {
//        try {
//            redisTemplate.opsForList().rightPush(key, value);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            logger.error("lSet for key:{},time:{},value:{} occur error:{}", key, time, value, e);
//            return false;
//        }
//    }
//
//    /**
//     * 将list放入缓存
//     *
//     * @param key   键
//     * @param value 值
//     * @return
//     */
//    public boolean lSet(String key, List<Object> value) {
//        try {
//            redisTemplate.opsForList().rightPushAll(key, value);
//            return true;
//        } catch (Exception e) {
//            logger.error("lSet for key:{},value:{} occur error:{}", key, JSONObject.toJSONString(value), e);
//            return false;
//        }
//    }
//
//    /**
//     * 将list放入缓存
//     *
//     * @param key   键
//     * @param value 值
//     * @param time  时间(秒)
//     * @return
//     */
//    public boolean lSet(String key, List<Object> value, long time) {
//        try {
//            redisTemplate.opsForList().rightPushAll(key, value);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            logger.error("lSet for key:{},time:{},value:{} occur error:{}", key, time, JSONObject.toJSONString(value), e);
//            return false;
//        }
//    }
//
//    /**
//     * 根据索引修改list中的某条数据
//     *
//     * @param key   键
//     * @param index 索引
//     * @param value 值
//     * @return
//     */
//    public boolean lUpdateIndex(String key, long index, Object value) {
//        try {
//            redisTemplate.opsForList().set(key, index, value);
//            return true;
//        } catch (Exception e) {
//            logger.error("lUpdateIndex for key:{},index:{},value:{} occur error:{}", key, index, value, e);
//            return false;
//        }
//    }
//
//    /**
//     * 移除N个值为value
//     *
//     * @param key   键
//     * @param count 移除多少个
//     * @param value 值
//     * @return 移除的个数
//     */
//    public long lRemove(String key, long count, Object value) {
//        try {
//            Long remove = redisTemplate.opsForList().remove(key, count, value);
//            return remove;
//        } catch (Exception e) {
//            logger.error("lRemove for key:{},count:{},value:{} occur error:{}", key, count, value, e);
//            return 0;
//        }
//    }
//
//
//    /**
//     * 缓存用户离线时间
//     *
//     * @param lists
//     */
//    public void cacheUserOfflineTime(List<String>... lists) {
//        if (null == lists || lists.length == 0) {
//            return;
//        }
//        if (null == userOfflineRedisBigMap) {
//            userOfflineRedisBigMap = new RedisBigMap();
//        }
//        Set<String> userIds = new HashSet<>();
//        for (List<String> list : lists) {
//            userIds.addAll(list);
//        }
//        String offlineTime = DateUtils.formatDate(new Date());
//        Iterator<String> iterator = userIds.iterator();
//        while (iterator.hasNext()) {
//            userOfflineRedisBigMap.put(RedisKeys.STAT_USER_OFFLINE_TIME_MAP, iterator.next(), offlineTime);
//        }
//    }
//
//    /**
//     * 获取用户离线时间
//     *
//     * @param uids
//     * @return Map[uid, offlineTime]
//     */
//    public Map<Integer, String> getUserOfflineTime(List<Integer> uids) {
//        Map<Integer, String> map = new HashMap<>();
//        if (null == uids || uids.size() == 0) {
//            return map;
//        }
//        if (null == userOfflineRedisBigMap) {
//            return map;
//        }
//        for (Integer uid : uids) {
//            map.put(uid, (String) userOfflineRedisBigMap.get(RedisKeys.STAT_USER_OFFLINE_TIME_MAP, uid.toString()));
//        }
//        return map;
//    }
//}
//
